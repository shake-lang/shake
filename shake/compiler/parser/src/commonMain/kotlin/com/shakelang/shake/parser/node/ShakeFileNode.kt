package com.shakelang.shake.parser.node

import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeFileNode(map: PositionMap, val children: Array<ShakeFileChildNode>) : ShakeNodeImpl(map) {

    constructor(map: PositionMap, children: List<ShakeFileChildNode>) : this(map, children.toTypedArray())

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "children" to children.map { it.json }
        )
}
