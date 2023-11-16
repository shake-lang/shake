package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeVariableDecreaseNode(map: PositionMap, val variable: ShakeValuedNode, val operatorPosition: Int) :
    ShakeValuedStatementNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "variable" to variable.json)
}
