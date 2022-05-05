package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType

class CreationShakeCast(
    val value: CreationShakeValue,
    val castTarget: CreationShakeType,
) : CreationShakeValue {
    override val type: CreationShakeType
        get() = castTarget

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "cast",
            "value" to value.toJson(),
            "castTarget" to castTarget.toJson(),
        )
    }
}