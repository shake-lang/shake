package com.shakelang.shake.parser.node

import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeIdentifierNode : ShakeNodeImpl {
    val parent: ShakeValuedNode?
    val name: String
    val position: Int

    constructor(map: PositionMap, parent: ShakeValuedNode?, name: String, position: Int) : super(map) {
        this.parent = parent
        this.name = name
        this.position = position
    }

    constructor(map: PositionMap, name: String, position: Int) : super(map) {
        this.position = position
        parent = null
        this.name = name
    }

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "parent" to (parent?.json),
            "name" to name
        )
}
