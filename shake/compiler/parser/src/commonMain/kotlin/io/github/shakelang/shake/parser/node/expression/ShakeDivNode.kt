package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode

class ShakeDivNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: Char
        get() = '/'

    override fun toJson(): Map<String, *> = mapOf("name" to "DivNode", "left" to left, "right" to right)
}