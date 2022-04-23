package io.github.shakelang.shake.processor.program.code.statements

import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.values.ShakeValue

class ShakeIf (

    val condition: ShakeValue,
    val body: ShakeCode,
    val elseBody: ShakeCode? = null

) : ShakeStatement {

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "if",
            "condition" to condition.toJson(),
            "body" to body.toJson(),
            "else" to elseBody?.toJson()
        )
    }

}