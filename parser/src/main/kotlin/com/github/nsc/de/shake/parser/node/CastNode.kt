package com.github.nsc.de.shake.parser.node

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

@Suppress("unused")
class CastNode(map: PositionMap, val value: ValuedNode, val castTarget: CastTarget) : ValuedNode(map) {

    class CastTarget @JvmOverloads constructor(val type: CastTargetType, val subtype: IdentifierNode? = null) {

        constructor(type: IdentifierNode?) : this(CastTargetType.OBJECT, type)

        enum class CastTargetType {
            BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR, STRING, OBJECT
        }

        override fun toString(): String {
            return subtype?.toString() ?: type.toString()
        }

        companion object {
            @JvmField
            val BYTE = CastTarget(CastTargetType.BYTE)
            @JvmField
            val SHORT = CastTarget(CastTargetType.SHORT)
            @JvmField
            val INTEGER = CastTarget(CastTargetType.INT)
            @JvmField
            val LONG = CastTarget(CastTargetType.LONG)
            @JvmField
            val FLOAT = CastTarget(CastTargetType.FLOAT)
            @JvmField
            val DOUBLE = CastTarget(CastTargetType.DOUBLE)
            @JvmField
            val BOOLEAN = CastTarget(CastTargetType.BOOLEAN)
            @JvmField
            val CHAR = CastTarget(CastTargetType.CHAR)
            @JvmField
            val STRING = CastTarget(CastTargetType.STRING)
        }
    }
}