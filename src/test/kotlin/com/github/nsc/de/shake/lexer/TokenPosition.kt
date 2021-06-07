package com.github.shakelang.shake.lexer

import com.github.shakelang.shake.lexer.token.TokenType
import com.github.shakelang.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TokenPosition {

    @Test
    @Suppress("deprecation")
    fun testGetBasicPosition() {
        val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "var test = 10;")
        val lexer = Lexer(chars)
        val input = lexer.makeTokens()
        Assertions.assertSame(TokenType.KEYWORD_VAR, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        var start = input.map.resolve(input.peek().start)
        var end = input.map.resolve(input.peek().end)
        Assertions.assertSame(0, start.index)
        Assertions.assertSame(1, start.column)
        Assertions.assertSame(1, start.line)
        Assertions.assertSame(2, end.index)
        Assertions.assertSame(3, end.column)
        Assertions.assertSame(1, end.line)
        input.skip()
        Assertions.assertSame(TokenType.IDENTIFIER, input.peek().type)
        Assertions.assertEquals("test", input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(4, start.index)
        Assertions.assertSame(5, start.column)
        Assertions.assertSame(1, start.line)
        Assertions.assertSame(7, end.index)
        Assertions.assertSame(8, end.column)
        Assertions.assertSame(1, end.line)
        input.skip()
        Assertions.assertSame(TokenType.ASSIGN, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(9, start.index)
        Assertions.assertSame(10, start.column)
        Assertions.assertSame(1, start.line)
        Assertions.assertSame(9, end.index)
        Assertions.assertSame(10, end.column)
        Assertions.assertSame(1, end.line)
        input.skip()
        Assertions.assertSame(TokenType.INTEGER, input.peek().type)
        Assertions.assertEquals("10", input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(11, start.index)
        Assertions.assertSame(12, start.column)
        Assertions.assertSame(1, start.line)
        Assertions.assertSame(12, end.index)
        Assertions.assertSame(13, end.column)
        Assertions.assertSame(1, end.line)
        input.skip()
        Assertions.assertSame(TokenType.SEMICOLON, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(13, start.index)
        Assertions.assertSame(14, start.column)
        Assertions.assertSame(1, start.line)
        Assertions.assertSame(13, end.index)
        Assertions.assertSame(14, end.column)
        Assertions.assertSame(1, end.line)
    }

    @Test
    @Suppress("deprecation")
    fun testGetMultiLinePosition() {
        val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "var test\n  = \n10;")
        val lexer = Lexer(chars)
        val input = lexer.makeTokens()
        Assertions.assertSame(TokenType.KEYWORD_VAR, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        var start = input.map.resolve(input.peek().start)
        var end = input.map.resolve(input.peek().end)
        Assertions.assertSame(0, start.index)
        Assertions.assertSame(1, start.column)
        Assertions.assertSame(1, start.line)
        Assertions.assertSame(2, end.index)
        Assertions.assertSame(3, end.column)
        Assertions.assertSame(1, end.line)
        input.skip()
        Assertions.assertSame(TokenType.IDENTIFIER, input.peek().type)
        Assertions.assertEquals("test", input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(4, start.index)
        Assertions.assertSame(5, start.column)
        Assertions.assertSame(1, start.line)
        Assertions.assertSame(7, end.index)
        Assertions.assertSame(8, end.column)
        Assertions.assertSame(1, end.line)
        input.skip()
        Assertions.assertSame(TokenType.LINE_SEPARATOR, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(8, start.index)
        Assertions.assertSame(0, start.column)
        Assertions.assertSame(2, start.line)
        Assertions.assertSame(8, end.index)
        Assertions.assertSame(0, end.column)
        Assertions.assertSame(2, end.line)
        input.skip()
        Assertions.assertSame(TokenType.ASSIGN, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(11, start.index)
        Assertions.assertSame(3, start.column)
        Assertions.assertSame(2, start.line)
        Assertions.assertSame(11, end.index)
        Assertions.assertSame(3, end.column)
        Assertions.assertSame(2, end.line)
        input.skip()
        Assertions.assertSame(TokenType.LINE_SEPARATOR, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(13, start.index)
        Assertions.assertSame(1, start.column)
        Assertions.assertSame(3, start.line)
        Assertions.assertSame(13, end.index)
        Assertions.assertSame(1, end.column)
        Assertions.assertSame(3, end.line)
        input.skip()
        Assertions.assertSame(TokenType.INTEGER, input.peek().type)
        Assertions.assertEquals("10", input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(14, start.index)
        Assertions.assertSame(2, start.column)
        Assertions.assertSame(3, start.line)
        Assertions.assertSame(15, end.index)
        Assertions.assertSame(3, end.column)
        Assertions.assertSame(3, end.line)
        input.skip()
        Assertions.assertSame(TokenType.SEMICOLON, input.peek().type)
        Assertions.assertSame(null, input.peek().value)
        start = input.map.resolve(input.peek().start)
        end = input.map.resolve(input.peek().end)
        Assertions.assertSame(16, start.index)
        Assertions.assertSame(4, start.column)
        Assertions.assertSame(3, start.line)
        Assertions.assertSame(16, end.index)
        Assertions.assertSame(4, end.column)
        Assertions.assertSame(3, end.line)
    }
}