package com.shakelang.shake.parser.node

import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeNamespaceNode(
    map: PositionMap,
    val parts: Array<String>
) : ShakeNodeImpl(map) {
    override fun toJson(): Map<String, *> {
        return mapOf(
            "type" to "namespace",
            "parts" to parts
        )
    }

    override fun toString(): String {
        return parts.joinToString(".")
    }
}