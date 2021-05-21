/**
 * The main command-line CLI for Shake
 */

@file:JvmName("ShakeCli")

package com.github.nsc.de.shake.cli

import com.github.nsc.de.shake.generators.java.JavaGenerator
import com.github.nsc.de.shake.generators.json.JsonGenerator
import com.github.nsc.de.shake.interpreter.Interpreter
import com.github.nsc.de.shake.lexer.Lexer
import com.github.nsc.de.shake.parser.Parser
import com.github.nsc.de.shake.parser.node.Tree
import com.github.nsc.de.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.nsc.de.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.nsc.de.shake.util.characterinput.position.PositionMap
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

/**
 * The interpreter for interpreting the code
 */
val interpreter = Interpreter()

/**
 * The json-generator for generating json form the input code
 */
val json = JsonGenerator()

/**
 * The java-generator for generating java form the input code
 */
val java = JavaGenerator()

/**
 * Is the program in debug mode?
 */
var DEBUG = false

/**
 * The version of the program
 */
const val VERSION = "0.1.0"

/**
 * The Main-Method for the ShakeCli
 *
 * @param args The arguments for the main cli
 * @throws IOException This exception is thrown, when there is a problem with the file given as argument
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Throws(IOException::class)
fun main(args: Array<String>) {

    // Create a parser for the arguments
    val argumentParser = CliArgumentParser()

    // Define the options for the argumentParser
    argumentParser
        .option("generator", "g", 1, arrayOf<String?>("interpreter"))
        .option("debug", "d")
        .option("target", "t", 1, arrayOf(null))


    // Parse the arguments given to the main-method
    val arguments = argumentParser.parse(args)

    // Detect debug mode (check if debug option is set ("--debug"))
    DEBUG = arguments.getOption("debug")!!.isGiven

    // Get the generator ("--generator")
    val generator = arguments.getOption("generator")!!.values!!.get(0)

    // If no normal argument is given, then we will open a prompt for entering code
    when(arguments.arguments.size) {

        0 -> {
            // Info message for Shake console
            println(
                "# Shake version $VERSION ${if (DEBUG) "in debug mode " else ""}\n" +
                        "# Enter Shake code below to execute!\n" +
                        "# Using $generator to execute code"
            )

            // Just create a infinite loop for reading from the console
            while (true) {

                // Try & Catch to prevent the console from crashing when
                // entering wrong code
                try {

                    // Print input-request-line-start
                    print("\n$ > ")

                    // request the input from the console and create a StringCharacterInputStream from it
                    val chars: CharacterInputStream = SourceCharacterInputStream("<Console>", readLine()!!)

                    // parse the CharacterInputStream into a Tree
                    val pr = parse(chars)

                    // execute the tree using the specified generator
                    execute(pr, generator, null, arguments.getOption("target")!!.values!![0])
                } catch (t: Throwable) {
                    // When an error occurs while executing the code, just print it's stack and continue
                    t.printStackTrace()
                }
            }
        }

        1 -> {
            val src = arguments.arguments[0]

            // read the contents of the file
            val file = String(Files.readAllBytes(Paths.get(src)))

            // Create a new StringCharacterInputStream from the file's contents
            val chars: CharacterInputStream = SourceCharacterInputStream(
                "<File: " + arguments.arguments[0] + ">", file
            )

            // Parse the CharacterInputStream
            val pr = parse(chars)

            // Execute the Tree using the specified generator
            execute(pr, generator, src, arguments.getOption("target")!!.values!![0])
        }

        else -> throw Error("There are only 0-1 arguments allowed")
    }
}

/**
 * This function parses a [CharacterInputStream] into a Tree
 *
 * @param in the [CharacterInputStream] to parse
 * @return the parsed [Tree]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
private fun parse(`in`: CharacterInputStream): ParseResult {

    // Create a new Lexer from the CharacterInputStream
    val lexer = Lexer(`in`)

    // Generate the tokens using the lexer
    val tokens = lexer.makeTokens()

    // if debug is enabled we print out the lexer-tokens
    if (DEBUG) println("[DEBUG] Lexer-Tokens: $tokens")

    // Create a new Parser from the tokens
    val parser = Parser(tokens)

    // Let the parser parse the tokens into a Tree
    val tree = parser.parse()

    // If debug is enabled we print out the tree
    if (DEBUG) println("[DEBUG] Parsed Tree: $tree", )

    // return the Tree
    return ParseResult(tree, tokens.map)
}

/**
 * This function executes a [Tree] using a specified generator
 *
 * @param pr the [ParseResult] to execute
 * @param generator the generator to use (just give the name of it)
 * @param src the source file of the program
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Throws(IOException::class)
private fun execute(pr: ParseResult, generator: String?, src: String?, target: String?) {
    if (src != null && !src.endsWith(".shake")) throw Error("Shake file names have to end with extension \".shake\"")
    val targetFile = src?.substring(0, src.length - 6)
    val baseName = if (src != null) targetFile!!.split("[\\\\/](?=[^\\\\/]+$)").toTypedArray()[1] else null
    when (generator) {
        "interpreter" -> println(">> ${interpreter.visit(pr.tree)}")
        "json" ->
            if (src == null) println(">> ${json.visit(pr.tree)}")
            else writeFile(File(target ?: targetFile + json.extension), json.visit(pr.tree).toString())
        "beauty-json", "bjson" ->
            if (src == null) println(">> ${json.visitTree(pr.tree).toString(2)}")
            else writeFile(File(target ?: targetFile + json.extension), json.visitTree(pr.tree).toString(2))
        "java" ->
            if (src == null) println(">> ${java.visitProgram(pr.tree, "CliInput").toString("", "  ")}%n")
            else writeFile(File(target ?: targetFile + java.extension), java.visitProgram(pr.tree, baseName).toString("", "  "))
        else -> throw Error("Unknown generator!")
    }
}

@Throws(IOException::class)
private fun writeFile(f: File, content: String) {
    println("Generating file \"${f.absolutePath}\"...")
    f.parentFile.mkdirs()
    val writer = BufferedWriter(FileWriter(f))
    writer.write(content)
    writer.close()
}

private class ParseResult(val tree: Tree, val map: PositionMap)