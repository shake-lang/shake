package com.shakelang.util.shason.processing

import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.lexer.token.Token
import com.shakelang.util.parseutils.lexer.token.TokenContext
import com.shakelang.util.parseutils.lexer.token.TokenFactory
import com.shakelang.util.parseutils.lexer.token.TokenType
import com.shakelang.util.parseutils.lexer.token.stream.FactoryTokenInputStream
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

class JsonTokenContext : TokenContext<JsonTokenContext, JsonTokenType, JsonToken, JsonTokenInputStream>()

/**
 * A [JsonToken] is a Token that should be parsed by the [JsonParser] generated by the [JsonLexer]
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
class JsonToken(
    type: JsonTokenType,
    start: Int,
    end: Int,
    value: String,
    context: JsonTokenContext,
) : Token<
    JsonToken,
    JsonTokenType,
    JsonTokenInputStream,
    JsonTokenContext,
    >(type, value, start, end, context)

/**
 * Token-types for the [JsonToken]s
 * @since 0.1.0
 * @version 0.1.0
 */
enum class JsonTokenType(
    override val value: String?,
) : TokenType {

    /**
     * A [LCURL] [JsonTokenType] represents a '{' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    LCURL("{"),

    /**
     * A [RCURL] [JsonTokenType] represents a '}' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    RCURL("}"),

    /**
     * A [LSQUARE] [JsonTokenType] represents a '[' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    LSQUARE("["),

    /**
     * A [RSQUARE] [JsonTokenType] represents a ']' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    RSQUARE("]"),

    /**
     * A [COMMA] [JsonTokenType] represents a ',' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    COMMA(","),

    /**
     * A [COLON] [JsonTokenType] represents a ':' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    COLON(":"),

    /**
     * A [TRUE] [JsonTokenType] represents a 'true' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    TRUE("true"),

    /**
     * A [FALSE] [JsonTokenType] represents a 'false' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    FALSE("false"),

    /**
     * A [NULL] [JsonTokenType] represents a 'null' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    NULL("null"),

    /**
     * A [STRING] [JsonTokenType] represents a string (e.g. "hello world") in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    STRING(null), // e.g. "hello world"

    /**
     * A [DOUBLE] [JsonTokenType] represents a doubles (e.g. '0.1') in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    DOUBLE(null), // e.g. 0.1

    /**
     * A [INT] [JsonTokenType] represents a integers (e.g. '42') in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    INT(null), // e.g. 42

    /**
     * A [EOF] [JsonTokenType] represents the end of the file
     * @since 0.1.0
     * @version 0.1.0
     */
    EOF(""), // end of file
    ;

    val length: Int = value?.length ?: -1

    /**
     * Get the length of the [JsonTokenType]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun length(value: String?): Int = if (hasValue) value!!.length else length

    /**
     * Does the [JsonTokenType] has a value?
     * @since 0.1.0
     * @version 0.1.0
     */
    private val hasValue: Boolean = this.length < 0
}

/**
 * A [JsonTokenInputStream] provides the [JsonToken]s for a Parser. It is
 * created by the [JsonLexer]
 * @since 0.1.0
 * @version 0.1.0
 */
interface JsonTokenInputStream : TokenInputStream<
    JsonTokenInputStream,
    JsonTokenType,
    JsonToken,
    JsonTokenContext,
    >

/**
 * Implementation of [JsonTokenInputStream]
 * @since 0.1.0
 * @version 0.1.0
 */
class JsonTokenInputStreamImpl(
    tokens: TokenFactory<JsonToken>,
    map: PositionMap,
) : JsonTokenInputStream, FactoryTokenInputStream<
    JsonTokenInputStream,
    JsonTokenType,
    JsonToken,
    JsonTokenContext,
    >(tokens, map, JsonTokenType.EOF)
