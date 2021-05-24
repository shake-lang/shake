package com.github.nsc.de.shake.parser.node

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class IfNode(map: PositionMap, val body: Tree, val elseBody: Tree?, val condition: ValuedNode) : Node(map) {

    constructor(map: PositionMap, body: Tree, condition: ValuedNode) : this(map, body, null, condition)

    override fun toString(): String {
        return "IfNode{" +
                "body=" + body +
                ", condition=" + condition +
                '}'
    }
}