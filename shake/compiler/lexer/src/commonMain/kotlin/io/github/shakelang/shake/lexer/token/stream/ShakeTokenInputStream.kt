package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.lexer.token.stream.TokenInputStream
import io.github.shakelang.shake.lexer.token.ShakeToken
import io.github.shakelang.shake.lexer.token.ShakeTokenType

/**
 * A [ShakeTokenInputStream] provides the [ShakeToken]s for a Parser. It is
 * created by the [io.github.shakelang.shake.lexer.ShakeLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
interface ShakeTokenInputStream : TokenInputStream<ShakeTokenType, ShakeToken> {
    fun skipIgnorable(): ShakeTokenInputStream {
        while (hasNext() && peek().type == ShakeTokenType.LINE_SEPARATOR) {
            skip()
        }
        return this
    }
}