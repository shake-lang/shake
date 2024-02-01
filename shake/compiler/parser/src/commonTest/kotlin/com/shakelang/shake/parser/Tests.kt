package com.shakelang.shake.parser

import com.shakelang.shake.parser.test.ShakeParserTestInput
import com.shakelang.shake.parser.test.ShakeParserTestOutput
import com.shakelang.util.logger.debug
import com.shakelang.util.shason.json
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Tests : FreeSpec({

    val debugger = debug("shake").child("parser", "tests")

    ShakeParserTestInput.forEachFile {

        val inputFile = it
        // get the output file
        val outputFile = (
            ShakeParserTestOutput[
                it.path
                    .replace(".shake", ".json")
                    .split("/", limit = 2).last(),
            ]
                ?: error("Outfile for ${it.path} not found")
            ).toFile()

        debugger("Generating test for ${json.stringify(inputFile.path)} with output ${json.stringify(outputFile.path)}")

        // execute the test
        inputFile.path {
            val ast = json.stringify(ParserTestUtil.parse(inputFile.path, inputFile.contentsAsString()).json)
            val expected = json.stringify(json.parse(outputFile.contentsAsString()).toJsonObject().toMap())
            ast shouldBe expected
        }
    }

})
