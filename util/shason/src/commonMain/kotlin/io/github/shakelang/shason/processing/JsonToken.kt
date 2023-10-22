package io.github.shakelang.shason.processing

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.lexer.token.TokenType
import io.github.shakelang.parseutils.lexer.token.stream.TokenBasedTokenInputStream
import io.github.shakelang.parseutils.lexer.token.stream.TokenInputStream
import io.github.shakelang.shake.lexer.token.Token

/**
 * A [JsonToken] is a Token that should be parsed by the [JsonParser] generated by the [JsonLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class JsonToken : Token<JsonTokenType> {

    constructor(
        type: JsonTokenType,
        start: Int,
        end: Int = start,
        value: String? = null,
    ) : super(type, value, start, end)


    /**
     * Constructor for [JsonToken]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(type: JsonTokenType, start: Int, value: String? = null) : this(type, start, start, value)

    /**
     * Has the [JsonToken] a value?
     */
    val hasValue: Boolean get() = this.value != null
}

/**
 * Token-types for the [JsonToken]s
 */
enum class JsonTokenType(
    private val length: Int
) : TokenType {

    /**
     * A [LCURL] [JsonTokenType] represents a '{' in the source
     */
    LCURL(1),              // '{'

    /**
     * A [RCURL] [JsonTokenType] represents a '}' in the source
     */
    RCURL(1),              // '}'

    /**
     * A [LSQUARE] [JsonTokenType] represents a '[' in the source
     */
    LSQUARE(1),            // '['

    /**
     * A [RSQUARE] [JsonTokenType] represents a ']' in the source
     */
    RSQUARE(1),            // ']'

    /**
     * A [COMMA] [JsonTokenType] represents a ',' in the source
     */
    COMMA(1),              // ','

    /**
     * A [COLON] [JsonTokenType] represents a ':' in the source
     */
    COLON(1),              // ':'

    /**
     * A [TRUE] [JsonTokenType] represents a 'true' in the source
     */
    TRUE(4),

    /**
     * A [FALSE] [JsonTokenType] represents a 'false' in the source
     */
    FALSE(5),

    /**
     * A [STRING] [JsonTokenType] represents a string (e.g. "hello world") in the source
     */
    STRING(-1),

    /**
     * A [DOUBLE] [JsonTokenType] represents a double (e.g. '0.1') in the source
     */
    DOUBLE(-1),

    /**
     * A [INT] [JsonTokenType] represents a integer (e.g. '42') in the source
     */
    INT(-1),
    ;

    override fun length(value: String?): Int = if(hasValue) value!!.length else length
    override val hasValue: Boolean get() = this.length < 0
}

/**
 * A [JsonTokenInputStream] provides the [JsonToken]s for a Parser. It is
 * created by the [io.github.shakelang.shake.util.json.JsonLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface JsonTokenInputStream : TokenInputStream<JsonTokenType, JsonToken>

class JsonTokenInputStreamImpl(
    tokens: Array<JsonToken>,
    map: PositionMap
) : JsonTokenInputStream, TokenBasedTokenInputStream<JsonTokenType, JsonToken> (tokens, map)