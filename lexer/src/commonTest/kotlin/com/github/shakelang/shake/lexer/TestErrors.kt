package com.github.shakelang.shake.lexer

import com.github.shakelang.shake.lexer.Lexer.LexerError
import com.github.shakelang.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.shakelang.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import kotlin.test.*

class TestErrors {
    @Test
    fun testStringMustEndError() {

        // String type 1 not finished
        val error = assertFailsWith(LexerError::class) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"test")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        assertSame(4, error.start.index)
        assertSame(4, error.end.index)
        assertEquals("String must end with a '\"'", error.details)
        assertEquals("<tests>:1:5", error.marker.source)
        assertEquals("1  \"test", error.marker.preview)
        assertEquals("       ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testUnknownEscapeSequenceError() {

        // Unknown escape sequence (using \a here)
        val error = assertFailsWith(LexerError::class) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\a\"")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        assertSame(2, error.start.index)
        assertSame(2, error.end.index)
        assertEquals("Unknown escape sequence '\\a'", error.details)
        assertEquals("<tests>:1:3", error.marker.source)
        assertEquals("1  \"\\a\"", error.marker.preview)
        assertEquals("     ^", error.marker.marker)


        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testExpectHexCharacterError() {

        // Wrong input to unicode character
        val error = assertFailsWith(LexerError::class) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\"\\uatea\"")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        assertSame(4, error.start.index)
        assertSame(4, error.end.index)
        assertEquals("Expecting hex char", error.details)
        assertEquals("<tests>:1:5", error.marker.source)
        assertEquals("1  \"\\uatea\"", error.marker.preview)
        assertEquals("       ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }

    @Test
    fun testUnexpectedTokenError() {

        // Unexpected Token
        val error = assertFailsWith(LexerError::class) {
            val chars: CharacterInputStream = SourceCharacterInputStream("<tests>", "\u00dc")
            val lexer = Lexer(chars)
            lexer.makeTokens()
        }

        // System.out.println(error.toString());
        assertSame(0, error.start.index)
        assertSame(0, error.end.index)
        assertEquals("Unrecognised Token: '\u00dc'", error.details)
        assertEquals("UnexpectedTokenError", error.exceptionName)
        assertEquals("<tests>:1:1", error.marker.source)
        assertEquals("1  \u00dc", error.marker.preview)
        assertEquals("   ^", error.marker.marker)

        // System.out.println(Formatting.FGColor.GREEN + "\u2705 Correct error was thrown" + Formatting.RESET);
    }
}