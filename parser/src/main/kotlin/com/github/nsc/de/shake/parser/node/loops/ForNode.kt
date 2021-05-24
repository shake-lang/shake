package com.github.nsc.de.shake.parser.node.loops

import com.github.nsc.de.shake.parser.node.Node
import com.github.nsc.de.shake.parser.node.Tree
import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class ForNode(map: PositionMap, val body: Tree, val declaration: Node, val condition: ValuedNode, val round: Node) :
    Node(map) {
    override fun toString(): String = "ForNode{body=$body, declaration=$declaration, condition=$condition, round=$round}"
}