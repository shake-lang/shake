package io.github.shakelang.shake.shasambly

import io.github.shakelang.parseutils.bytes.stream
import io.github.shakelang.shake.shasambly.generator.shas.ShasGenerator
import io.github.shakelang.shake.shasambly.generator.simple.shasambly
import io.github.shakelang.shake.shasambly.generator.simple.util.array.createSavedSizeLocalByteArray
import io.github.shakelang.shake.shasambly.interpreter.ShasamblyInterpreter
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

fun main() {

    val code = shasambly {

        incrStack(0x007f)
        val arr = createSavedSizeLocalByteArray(0, 10)
        arr.storeElement(0, 10)
        arr.storeElement(1, 11)
        arr.storeElement(2, 12)
        arr.storeElement(3, 13)
        arr.getElement(2)
        natives.printByte()
        natives.printLineEnding()
        arr.getElement(1)
        natives.printByte()
        natives.printLineEnding()
        arr.free()

        /*
        natives.declareGlobal(8)
        i_store_local(0)
        lpush(1000)
        i_get_local(0)
        l_store_global_dynamic()
        i_get_local(0)
        natives.printInt()
        natives.printLineEnding()
        i_get_local(0)
        l_get_global_dynamic()
        natives.printLong()
        natives.printLineEnding()
        i_get_local(0)
        natives.freeGlobal(8)
        natives.declareGlobal(4)
        natives.printInt()
        natives.printLineEnding()
        natives.declareGlobal(4)
        natives.printInt()
        natives.printLineEnding()
        natives.declareGlobal(4)
        natives.printInt()
        natives.printLineEnding()
        */

        /*
        ipush(0x00000000)
        i_store_local(0x0000)

        whileLoop({
            // Condition
            i_get_local(0x0000)
            ipush(2000)
            ismaller()
        }) {
            i_get_local(0x0000)
            ipush(1)
            iadd()
            i_store_local(0x0000)
            i_get_local(0x0000)

            natives.printInt()
            bpush(' '.code.toByte())
            natives.printUtf8()
            //natives.printLineEnding()
        }
        natives.printLineEnding()


        // Hello World
        "Hello World!\n".forEach {
            bpush(it.code.toByte())
            natives.printUtf8()
        }

        natives.printLineEnding()
        */


    }

    val file = File("test.shasx")
    if(file.exists()) file.delete()
    println("Write code bytes to ${file.absolutePath}...")
    val os = BufferedOutputStream(FileOutputStream(file))
    os.write(code)
    os.flush()
    os.close()

    val generator = ShasGenerator(code.stream())
    val output = File("test.shas")
    println("Generating file \"${output.absolutePath}\"...")
    val out = FileOutputStream(output).buffered()
    generator.dumpParse(out)
    out.flush()
    out.close()

    val interpreter = ShasamblyInterpreter(
        1024, code, 0
    )
    interpreter.finish()
    //println(interpreter)
}