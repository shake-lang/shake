package io.github.shakelang.shake.shasambly

import io.github.shakelang.parseutils.bytes.stream
import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.shasambly.generator.shas.ShasCompiler
import io.github.shakelang.shake.shasambly.generator.shas.ShasGenerator
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
    if(args.isEmpty()) throw Error("Expecting at least one argument")
    if(args.size > 2) throw Error("Expecting maximum 2 arguments")
    if(!args[0].endsWith(".shas")) throw Error("Input file has to end with .shas")
    val f = File(args[0])
    val inputStream = FileInputStream(f).buffered()
    val contents = inputStream.readAllBytes().map { it.toInt().toChar() }.toCharArray()
    inputStream.close()
    val source = CharacterSource.from(contents, args[0])
    val chars = SourceCharacterInputStream(source)
    val compiler = ShasCompiler(chars)
    val output = if(args.size == 1) args[0] else args[1]
    println("Generating file \"${File(output).absolutePath}\"...")
    val out = FileOutputStream(output).buffered()
    val generator = ShasGenerator(compiler.parse().stream())
    generator.dumpParse(out)
    out.flush()
    out.close()
}