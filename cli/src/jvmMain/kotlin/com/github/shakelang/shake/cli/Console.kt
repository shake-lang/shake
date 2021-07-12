package com.github.shakelang.shake.cli

import com.github.shakelang.shake.util.Promise
import java.util.*

actual fun readLine(message: String): Promise<String?> =
    Promise { rs, _ ->
        run {
            print(message)
            val s = Scanner(System.`in`)
            val nextLine = s.nextLine()
            s.close()
            rs(nextLine)
        }
    }


actual fun readLine(): Promise<String?> =
    Promise { rs, _ -> rs(kotlin.io.readLine()!!) }