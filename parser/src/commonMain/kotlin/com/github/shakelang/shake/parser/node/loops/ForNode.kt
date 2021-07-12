package com.github.shakelang.shake.parser.node.loops

import com.github.shakelang.shake.parser.node.Node
import com.github.shakelang.shake.parser.node.Tree
import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class ForNode(map: PositionMap, val body: Tree, val declaration: Node, val condition: ValuedNode, val round: Node) :
    Node(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "ForNode",
            "body" to body.json,
            "declaration" to declaration.json,
            "condition" to condition.json,
            "round" to round.json
        )
}