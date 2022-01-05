@file:Suppress("deprecation")
package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.characters.source.CharacterSource
import io.github.shakelang.shake.lexer.token.Token
import io.github.shakelang.shake.lexer.token.TokenType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TokenBasedTokenInputStreamTests {

    @Test
    fun testNextType() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testNextType()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testNextType()"),
                intArrayOf()
            )
        )

        assertEquals(TokenType.KEYWORD_INT, dbtis.nextType())
        assertEquals(2, dbtis.actualEnd)
        assertEquals(TokenType.IDENTIFIER, dbtis.nextType())
        assertEquals(4, dbtis.actualEnd)
        assertEquals(TokenType.ASSIGN, dbtis.nextType())
        assertEquals(6, dbtis.actualEnd)
        assertEquals(TokenType.INTEGER, dbtis.nextType())
        assertEquals(9, dbtis.actualEnd)
        assertEquals(TokenType.SEMICOLON, dbtis.nextType())
        assertEquals(10, dbtis.actualEnd)

    }

    @Test
    fun testNextValue() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testNextValue()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testNextValue()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.nextValue())
        assertEquals(2, dbtis.actualEnd)
        assertEquals("i", dbtis.nextValue())
        assertEquals(4, dbtis.actualEnd)
        assertEquals(null, dbtis.nextValue())
        assertEquals(6, dbtis.actualEnd)
        assertEquals("10", dbtis.nextValue())
        assertEquals(9, dbtis.actualEnd)
        assertEquals(null, dbtis.nextValue())
        assertEquals(10, dbtis.actualEnd)

    }

    @Test
    fun testNextToken() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testNextToken()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testNextToken()"),
                intArrayOf()
            )
        )

        var next = dbtis.next()
        assertEquals(TokenType.KEYWORD_INT, next.type)
        assertEquals(2, next.end)
        assertEquals(null, next.value)
        assertEquals(0, next.start)
        next = dbtis.next()
        assertEquals(TokenType.IDENTIFIER, next.type)
        assertEquals(4, next.end)
        assertEquals("i", next.value)
        assertEquals(4, next.start)
        next = dbtis.next()
        assertEquals(TokenType.ASSIGN, next.type)
        assertEquals(6, next.end)
        assertEquals(null, next.value)
        assertEquals(6, next.start)
        next = dbtis.next()
        assertEquals(TokenType.INTEGER, next.type)
        assertEquals(9, next.end)
        assertEquals("10", next.value)
        assertEquals(8, next.start)
        next = dbtis.next()
        assertEquals(TokenType.SEMICOLON, next.type)
        assertEquals(10, next.end)
        assertEquals(null, next.value)
        assertEquals(10, next.start)
    }

    @Test
    fun testGetType() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetType()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetType()"),
                intArrayOf()
            )
        )

        assertEquals(TokenType.KEYWORD_INT, dbtis.getType(0))
        assertEquals(TokenType.IDENTIFIER, dbtis.getType(1))
        assertEquals(TokenType.ASSIGN, dbtis.getType(2))
        assertEquals(TokenType.INTEGER, dbtis.getType(3))
        assertEquals(TokenType.SEMICOLON, dbtis.getType(4))

    }

    @Test
    fun testGetEnd() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetEnd()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetEnd()"),
                intArrayOf()
            )
        )

        assertEquals(2, dbtis.getEnd(0))
        assertEquals(4, dbtis.getEnd(1))
        assertEquals(6, dbtis.getEnd(2))
        assertEquals(9, dbtis.getEnd(3))
        assertEquals(10, dbtis.getEnd(4))

    }

    @Test
    fun testGetStart() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetStart()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetStart()"),
                intArrayOf()
            )
        )

        assertEquals(0, dbtis.getStart(0))
        assertEquals(4, dbtis.getStart(1))
        assertEquals(6, dbtis.getStart(2))
        assertEquals(8, dbtis.getStart(3))
        assertEquals(10, dbtis.getStart(4))

    }

    @Test
    fun testGetValue() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetValue()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetValue()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.getValue(0))
        assertEquals("i", dbtis.getValue(1))
        assertEquals(null, dbtis.getValue(2))
        assertEquals("10", dbtis.getValue(3))
        assertEquals(null, dbtis.getValue(4))

    }

    @Test
    fun testGetHasValue() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testGetHasValue()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testGetHasValue()"),
                intArrayOf()
            )
        )

        assertEquals(false, dbtis.getHasValue(0))
        assertEquals(true, dbtis.getHasValue(1))
        assertEquals(false, dbtis.getHasValue(2))
        assertEquals(true, dbtis.getHasValue(3))
        assertEquals(false, dbtis.getHasValue(4))

    }

    @Test
    fun testSkip() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testSkip()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testSkip()"),
                intArrayOf()
            )
        )

        assertEquals(dbtis.position, -1)
        dbtis.skip()
        assertEquals(dbtis.position, 0)
        dbtis.skip(2)
        assertEquals(dbtis.position, 2)
        dbtis.skip()
        assertEquals(dbtis.position, 3)
        dbtis.skip()
        assertEquals(dbtis.position, 4)
        assertEquals("Input already finished", assertFailsWith<Error> { dbtis.skip() }.message)

    }

    @Test
    fun testSetPosition() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testSetPosition()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testSetPosition()"),
                intArrayOf()
            )
        )

        assertEquals(dbtis.position, -1)
        dbtis.position = 0
        assertEquals(dbtis.position, 0)
        assertEquals(2, dbtis.actualEnd)
        assertEquals(null, dbtis.actualValue)
        dbtis.position = 1
        assertEquals(dbtis.position, 1)
        assertEquals(4, dbtis.actualEnd)
        assertEquals("i", dbtis.actualValue)
        dbtis.position = 2
        assertEquals(dbtis.position, 2)
        assertEquals(6, dbtis.actualEnd)
        assertEquals(null, dbtis.actualValue)
        dbtis.position = 3
        assertEquals(dbtis.position, 3)
        assertEquals(9, dbtis.actualEnd)
        assertEquals("10", dbtis.actualValue)
        dbtis.position = 4
        assertEquals(dbtis.position, 4)
        assertEquals(10, dbtis.actualEnd)
        assertEquals(null, dbtis.actualValue)
        dbtis.position = 3
        assertEquals(dbtis.position, 3)
        assertEquals(9, dbtis.actualEnd)
        assertEquals("10", dbtis.actualValue)

    }

    @Test
    fun testHas() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHas()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testHas()"),
                intArrayOf()
            )
        )

        assertEquals(true, dbtis.has(1))
        assertEquals(true, dbtis.has(2))
        assertEquals(true, dbtis.has(3))
        assertEquals(true, dbtis.has(4))
        assertEquals(true, dbtis.has(5))
        assertEquals(false, dbtis.has(6))
        dbtis.skip(2)
        assertEquals(true, dbtis.has(1))
        assertEquals(true, dbtis.has(2))
        assertEquals(true, dbtis.has(3))
        assertEquals(false, dbtis.has(4))

    }

    @Test
    fun testHasNext() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHasNext()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testHasNext()"),
                intArrayOf()
            )
        )

        assertEquals(true, dbtis.hasNext())
        dbtis.skip(2)
        assertEquals(true, dbtis.hasNext())
        dbtis.skip(3)
        assertEquals(false, dbtis.hasNext())

    }

    @Test
    fun testHasNextWithEmpty() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHasNextWithEmpty()",
            arrayOf(),
            PositionMap.PositionMapImpl(
                CharacterSource.from("", "TokenBasedTokenInputStreamTests#testHasNextWithEmpty()"),
                intArrayOf()
            )
        )

        assertEquals(false, dbtis.hasNext())

    }

    @Test
    fun testHasNextWithEmptyAndPosition() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testHasNextWithEmptyAndPosition()",
            arrayOf(),
            PositionMap.PositionMapImpl(
                CharacterSource.from("", "TokenBasedTokenInputStreamTests#testHasNextWithEmptyAndPosition()"),
                intArrayOf()
            )
        )

        assertEquals(false, dbtis.hasNext())
        assertEquals(-1, dbtis.position)

    }

    @Test
    fun testActualEnd() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualEnd()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualEnd()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(2, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(4, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(6, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(9, dbtis.actualEnd)
        dbtis.skip()
        assertEquals(10, dbtis.actualEnd)

    }

    @Test
    fun testActualStart() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualStart()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualStart()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(0, dbtis.actualStart)
        dbtis.skip()
        assertEquals(4, dbtis.actualStart)
        dbtis.skip()
        assertEquals(6, dbtis.actualStart)
        dbtis.skip()
        assertEquals(8, dbtis.actualStart)
        dbtis.skip()
        assertEquals(10, dbtis.actualStart)

    }

    @Test
    fun testActualType() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualType()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualType()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(TokenType.KEYWORD_INT, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.IDENTIFIER, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.ASSIGN, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.INTEGER, dbtis.actualType)
        dbtis.skip()
        assertEquals(TokenType.SEMICOLON, dbtis.actualType)

    }

    @Test
    fun testActualValue() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActualValue()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActualValue()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        assertEquals(null, dbtis.actualValue)
        dbtis.skip()
        assertEquals("i", dbtis.actualValue)
        dbtis.skip()
        assertEquals(null, dbtis.actualValue)
        dbtis.skip()
        assertEquals("10", dbtis.actualValue)
        dbtis.skip()
        assertEquals(null, dbtis.actualValue)

    }

    @Test
    fun testActual() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testActual()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testActual()"),
                intArrayOf()
            )
        )

        dbtis.skip()
        var actual = dbtis.actual
        assertEquals(TokenType.KEYWORD_INT, actual.type)
        assertEquals(null, actual.value)
        assertEquals(2, actual.end)
        assertEquals(0, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.IDENTIFIER, actual.type)
        assertEquals("i", actual.value)
        assertEquals(4, actual.end)
        assertEquals(4, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.ASSIGN, actual.type)
        assertEquals(null, actual.value)
        assertEquals(6, actual.end)
        assertEquals(6, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.INTEGER, actual.type)
        assertEquals("10", actual.value)
        assertEquals(9, actual.end)
        assertEquals(8, actual.start)
        dbtis.skip()
        actual = dbtis.actual
        assertEquals(TokenType.SEMICOLON, actual.type)
        assertEquals(null, actual.value)
        assertEquals(10, actual.end)
        assertEquals(10, actual.start)

    }

    @Test
    fun testPeekType() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekType()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekType()"),
                intArrayOf()
            )
        )

        assertEquals(TokenType.KEYWORD_INT, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.IDENTIFIER, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.ASSIGN, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.INTEGER, dbtis.peekType())
        dbtis.skip()
        assertEquals(TokenType.SEMICOLON, dbtis.peekType())

    }

    @Test
    fun testPeekEnd() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekEnd()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekEnd()"),
                intArrayOf()
            )
        )

        assertEquals(2, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(4, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(6, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(9, dbtis.peekEnd())
        dbtis.skip()
        assertEquals(10, dbtis.peekEnd())

    }

    @Test
    fun testPeekStart() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekStart()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekStart()"),
                intArrayOf()
            )
        )

        assertEquals(0, dbtis.peekStart())
        dbtis.skip()
        assertEquals(4, dbtis.peekStart())
        dbtis.skip()
        assertEquals(6, dbtis.peekStart())
        dbtis.skip()
        assertEquals(8, dbtis.peekStart())
        dbtis.skip()
        assertEquals(10, dbtis.peekStart())

    }

    @Test
    fun testPeekValue() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekValue()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekValue()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.peekValue())
        dbtis.skip()
        assertEquals("i", dbtis.peekValue())
        dbtis.skip()
        assertEquals(null, dbtis.peekValue())
        dbtis.skip()
        assertEquals("10", dbtis.peekValue())
        dbtis.skip()
        assertEquals(null, dbtis.peekValue())

    }

    @Test
    fun testPeek() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeek()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeek()"),
                intArrayOf()
            )
        )

        var token = dbtis.peek()
        assertEquals(TokenType.KEYWORD_INT, token.type)
        assertEquals(null, token.value)
        assertEquals(0, token.start)
        assertEquals(2, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        dbtis.skip()
        token = dbtis.peek()
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)

    }

    @Test
    fun testPeekType2() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekType2()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekType2()"),
                intArrayOf()
            )
        )

        assertEquals(TokenType.KEYWORD_INT, dbtis.peekType(1))
        assertEquals(TokenType.IDENTIFIER, dbtis.peekType(2))
        assertEquals(TokenType.ASSIGN, dbtis.peekType(3))
        assertEquals(TokenType.INTEGER, dbtis.peekType(4))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(5))
        dbtis.skip()
        assertEquals(TokenType.IDENTIFIER, dbtis.peekType(1))
        assertEquals(TokenType.ASSIGN, dbtis.peekType(2))
        assertEquals(TokenType.INTEGER, dbtis.peekType(3))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(4))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(TokenType.ASSIGN, dbtis.peekType(1))
        assertEquals(TokenType.INTEGER, dbtis.peekType(2))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(3))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(TokenType.INTEGER, dbtis.peekType(1))
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(2))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)
        dbtis.skip()
        assertEquals(TokenType.SEMICOLON, dbtis.peekType(1))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekType(5) }.message)

    }

    @Test
    fun testPeekEnd2() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekEnd2()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekEnd2()"),
                intArrayOf()
            )
        )

        assertEquals(2, dbtis.peekEnd(1))
        assertEquals(4, dbtis.peekEnd(2))
        assertEquals(6, dbtis.peekEnd(3))
        assertEquals(9, dbtis.peekEnd(4))
        assertEquals(10, dbtis.peekEnd(5))
        dbtis.skip()
        assertEquals(4, dbtis.peekEnd(1))
        assertEquals(6, dbtis.peekEnd(2))
        assertEquals(9, dbtis.peekEnd(3))
        assertEquals(10, dbtis.peekEnd(4))
        assertFailsWith<Error> { dbtis.peekEnd(5) }
        dbtis.skip()
        assertEquals(6, dbtis.peekEnd(1))
        assertEquals(9, dbtis.peekEnd(2))
        assertEquals(10, dbtis.peekEnd(3))
        assertFailsWith<Error> { dbtis.peekEnd(4) }
        assertFailsWith<Error> { dbtis.peekEnd(5) }
        dbtis.skip()
        assertEquals(9, dbtis.peekEnd(1))
        assertEquals(10, dbtis.peekEnd(2))
        assertFailsWith<Error> { dbtis.peekEnd(3) }
        assertFailsWith<Error> { dbtis.peekEnd(4) }
        assertFailsWith<Error> { dbtis.peekEnd(5) }
        dbtis.skip()
        assertEquals(10, dbtis.peekEnd(1))
        assertFailsWith<Error> { dbtis.peekEnd(2) }
        assertFailsWith<Error> { dbtis.peekEnd(3) }
        assertFailsWith<Error> { dbtis.peekEnd(4) }
        assertFailsWith<Error> { dbtis.peekEnd(5) }

    }

    @Test
    fun testPeekStart2() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekStart2()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekStart2()"),
                intArrayOf()
            )
        )

        assertEquals(0, dbtis.peekStart(1))
        assertEquals(4, dbtis.peekStart(2))
        assertEquals(6, dbtis.peekStart(3))
        assertEquals(8, dbtis.peekStart(4))
        assertEquals(10, dbtis.peekStart(5))
        dbtis.skip()
        assertEquals(4, dbtis.peekStart(1))
        assertEquals(6, dbtis.peekStart(2))
        assertEquals(8, dbtis.peekStart(3))
        assertEquals(10, dbtis.peekStart(4))
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertEquals(6, dbtis.peekStart(1))
        assertEquals(8, dbtis.peekStart(2))
        assertEquals(10, dbtis.peekStart(3))
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertEquals(8, dbtis.peekStart(1))
        assertEquals(10, dbtis.peekStart(2))
        assertFailsWith<Error> { dbtis.peekStart(3) }
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertEquals(10, dbtis.peekStart(1))
        assertFailsWith<Error> { dbtis.peekStart(2) }
        assertFailsWith<Error> { dbtis.peekStart(3) }
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }
        dbtis.skip()
        assertFailsWith<Error> { dbtis.peekStart(1) }
        assertFailsWith<Error> { dbtis.peekStart(2) }
        assertFailsWith<Error> { dbtis.peekStart(3) }
        assertFailsWith<Error> { dbtis.peekStart(4) }
        assertFailsWith<Error> { dbtis.peekStart(5) }

    }

    @Test
    fun testPeekValue2() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeekValue2()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeekValue2()"),
                intArrayOf()
            )
        )

        assertEquals(null, dbtis.peekValue(1))
        assertEquals("i", dbtis.peekValue(2))
        assertEquals(null, dbtis.peekValue(3))
        assertEquals("10", dbtis.peekValue(4))
        assertEquals(null, dbtis.peekValue(5))
        dbtis.skip()
        assertEquals("i", dbtis.peekValue(1))
        assertEquals(null, dbtis.peekValue(2))
        assertEquals("10", dbtis.peekValue(3))
        assertEquals(null, dbtis.peekValue(4))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals(null, dbtis.peekValue(1))
        assertEquals("10", dbtis.peekValue(2))
        assertEquals(null, dbtis.peekValue(3))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals("10", dbtis.peekValue(1))
        assertEquals(null, dbtis.peekValue(2))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals(null, dbtis.peekValue(1))
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)
        dbtis.skip()
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(1) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peekValue(5) }.message)

    }

    @Test
    fun testPeek2() {

        val dbtis = TokenBasedTokenInputStream(
            "TokenBasedTokenInputStreamTests#testPeek2()",
            arrayOf(
                Token(TokenType.KEYWORD_INT, 2),
                Token(TokenType.IDENTIFIER, "i", 4),
                Token(TokenType.ASSIGN, 6),
                Token(TokenType.INTEGER, "10", 9),
                Token(TokenType.SEMICOLON, 10),
            ),
            PositionMap.PositionMapImpl(
                CharacterSource.from("int i = 10;", "TokenBasedTokenInputStreamTests#testPeek2()"),
                intArrayOf()
            )
        )

        var token = dbtis.peek(1)
        assertEquals(TokenType.KEYWORD_INT, token.type)
        assertEquals(null, token.value)
        assertEquals(0, token.start)
        assertEquals(2, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = dbtis.peek(3)
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(4)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(5)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.IDENTIFIER, token.type)
        assertEquals("i", token.value)
        assertEquals(4, token.start)
        assertEquals(4, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(3)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(4)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.ASSIGN, token.type)
        assertEquals(null, token.value)
        assertEquals(6, token.start)
        assertEquals(6, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(3)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.INTEGER, token.type)
        assertEquals("10", token.value)
        assertEquals(8, token.start)
        assertEquals(9, token.end)
        token = dbtis.peek(2)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

        dbtis.skip()

        token = dbtis.peek(1)
        assertEquals(TokenType.SEMICOLON, token.type)
        assertEquals(null, token.value)
        assertEquals(10, token.start)
        assertEquals(10, token.end)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(2) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(3) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(4) }.message)
        assertEquals("Not enough tokens left", assertFailsWith<Error> { dbtis.peek(5) }.message)

    }

}