package com.github.nsc.de.shake.lexer.token

/**
 * These are the different types of tokens, that the lexer creates
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
object TokenType {
    private val NAMES = arrayOf(
        "IDENTIFIER",           //  0
        "INTEGER",              //  1
        "DOUBLE",               //  2
        "CHARACTER",            //  3
        "STRING",               //  4
        "COMMA",                //  5
        "DOT",                  //  6
        "SEMICOLON",            //  7
        "ADD",                  //  8
        "SUB",                  //  9
        "MUL",                  // 10
        "DIV",                  // 11
        "MOD",                  // 12
        "POW",                  // 13
        "EQ_EQUALS",            // 14
        "BIGGER_EQUALS",        // 15
        "SMALLER_EQUALS",       // 16
        "BIGGER",               // 17
        "SMALLER",              // 18
        "LOGICAL_OR",           // 19
        "LOGICAL_XOR",          // 20
        "LOGICAL_AND",          // 21
        "LINE_SEPARATOR",       // 22
        "ASSIGN",               // 23
        "INCR",                 // 24
        "DECR",                 // 25
        "ADD_ASSIGN",           // 26
        "SUB_ASSIGN",           // 27
        "MUL_ASSIGN",           // 28
        "DIV_ASSIGN",           // 29
        "MOD_ASSIGN",           // 30
        "POW_ASSIGN",           // 31
        "LPAREN",               // 32
        "RPAREN",               // 33
        "LCURL",                // 34
        "RCURL",                // 35
        "KEYWORD_DO",           // 36
        "KEYWORD_WHILE",        // 37
        "KEYWORD_FOR",          // 38
        "KEYWORD_IF",           // 39
        "KEYWORD_ELSE",         // 40
        "KEYWORD_TRUE",         // 41
        "KEYWORD_FALSE",        // 42
        "KEYWORD_CLASS",        // 43
        "KEYWORD_EXTENDS",      // 44
        "KEYWORD_IMPLEMENTS",   // 45
        "KEYWORD_STATIC",       // 46
        "KEYWORD_FINAL",        // 47
        "KEYWORD_PUBLIC",       // 48
        "KEYWORD_PROTECTED",    // 49
        "KEYWORD_PRIVATE",      // 50
        "KEYWORD_NEW",          // 51
        "KEYWORD_FUNCTION",     // 52
        "KEYWORD_RETURN",       // 53
        "KEYWORD_VAR",          // 54
        "KEYWORD_CONST",        // 55
        "KEYWORD_DYNAMIC",      // 56
        "KEYWORD_BYTE",         // 57
        "KEYWORD_SHORT",        // 58
        "KEYWORD_INT",          // 59
        "KEYWORD_LONG",         // 60
        "KEYWORD_FLOAT",        // 61
        "KEYWORD_DOUBLE",       // 62
        "KEYWORD_CHAR",         // 63
        "KEYWORD_BOOLEAN",      // 64
        "KEYWORD_IMPORT",       // 65
        "KEYWORD_VOID",         // 66
        "KEYWORD_CONSTRUCTOR",  // 67
        "KEYWORD_AS"            // 68
    )
    private val TOKEN_LENGTH = byteArrayOf( // The length of the tokens
        //          TokenType           Index
        //         -------------------------
        -1,     //  IDENTIFIER            0
        -1,     //  INTEGER               1
        -1,     //  DOUBLE                2
        -1,     //  CHARACTER             3
        -1,     //  STRING                4
        1,      //  COMMA                 5
        1,      //  DOT                   6
        1,      //  SEMICOLON             7
        1,      //  ADD                   8
        1,      //  SUB                   9
        1,      //  MUL                  10
        1,      //  DIV                  11
        1,      //  MOD                  12
        2,      //  POW                  13
        2,      //  EQ_EQUALS            14
        2,      //  BIGGER_EQUALS        15
        2,      //  SMALLER_EQUALS       16
        1,      //  BIGGER               17
        1,      //  SMALLER              18
        2,      //  LOGICAL_OR           19
        2,      //  LOGICAL_XOR          20
        2,      //  LOGICAL_AND          21
        1,      //  LINE_SEPARATOR       22
        1,      //  ASSIGN               23
        2,      //  INCR                 24
        2,      //  DECR                 25
        2,      //  ADD_ASSIGN           26
        2,      //  SUB_ASSIGN           27
        2,      //  MUL_ASSIGN           28
        2,      //  DIV_ASSIGN           29
        2,      //  MOD_ASSIGN           30
        3,      //  POW_ASSIGN           31
        1,      //  LPAREN               32
        1,      //  RPAREN               33
        1,      //  LCURL                34
        1,      //  RCURL                35
        2,      //  KEYWORD_DO           36
        5,      //  KEYWORD_WHILE        37
        3,      //  KEYWORD_FOR          38
        2,      //  KEYWORD_IF           39
        4,      //  KEYWORD_ELSE         40
        4,      //  KEYWORD_TRUE         41
        5,      //  KEYWORD_FALSE        42
        5,      //  KEYWORD_CLASS        43
        7,      //  KEYWORD_EXTENDS      44
        10,     //  KEYWORD_IMPLEMENTS   45
        6,      //  KEYWORD_STATIC       46
        5,      //  KEYWORD_FINAL        47
        6,      //  KEYWORD_PUBLIC       48
        9,      //  KEYWORD_PROTECTED    49
        7,      //  KEYWORD_PRIVATE      50
        3,      //  KEYWORD_NEW          51
        8,      //  KEYWORD_FUNCTION     52
        6,      //  KEYWORD_RETURN       53
        3,      //  KEYWORD_VAR          54
        5,      //  KEYWORD_CONST        55
        7,      //  KEYWORD_DYNAMIC      56
        4,      //  KEYWORD_BYTE         57
        5,      //  KEYWORD_SHORT        58
        3,      //  KEYWORD_INT          59
        4,      //  KEYWORD_LONG         60
        5,      //  KEYWORD_FLOAT        61
        6,      //  KEYWORD_DOUBLE       62
        4,      //  KEYWORD_CHAR         63
        7,      //  KEYWORD_BOOLEAN      64
        6,      //  KEYWORD_IMPORT       65
        4,      //  KEYWORD_VOID         66
        11,     //  KEYWORD_CONSTRUCTOR  67
        2       // KEYWORD_AS            68
    )

    /**
     * Identifier for variables, functions and classes
     */
    const val IDENTIFIER: Byte = 0


    // *****************************************************
    // Assign-Types
    /**
     * A number that does not contain decimal places
     */
    const val INTEGER: Byte = 1

    /**
     * A number that does contain decimal places
     */
    const val DOUBLE: Byte = 2

    /**
     * A character
     */
    const val CHARACTER: Byte = 3

    /**
     * A string ("a string")
     */
    const val STRING: Byte = 4


    // *****************************************************
    // Punctuation
    /**
     * Token ','
     * A comma for separating values
     */
    const val COMMA: Byte = 5

    /**
     * Token '.'
     * A dot that is not inside of a double for sub-identifiers
     */
    const val DOT: Byte = 6

    /**
     * Token ';'
     * A semicolon as separator for statements
     */
    const val SEMICOLON: Byte = 7


    // *****************************************************
    // Math Operators
    /**
     * Token '+' for adding values
     */
    const val ADD: Byte = 8

    /**
     * Token '-' for subtracting values
     */
    const val SUB: Byte = 9

    /**
     * Token '*' for multiplying values
     */
    const val MUL: Byte = 10

    /**
     * Token '/' for dividing values
     */
    const val DIV: Byte = 11

    /**
     * Token '%' for modulo operations
     */
    const val MOD: Byte = 12

    /**
     * Token '**' for pow operations
     */
    const val POW: Byte = 13


    // *****************************************************
    // Logical Operators
    /**
     * Token '==' for comparison
     */
    const val EQ_EQUALS: Byte = 14

    /**
     * Token '&gt;=' for comparison
     */
    const val BIGGER_EQUALS: Byte = 15

    /**
     * Token '&lt;=' for comparison
     */
    const val SMALLER_EQUALS: Byte = 16

    /**
     * Token '&gt;' for comparison
     */
    const val BIGGER: Byte = 17

    /**
     * Token '&lt;' for comparison
     */
    const val SMALLER: Byte = 18

    /**
     * Token '||' (logical or operator)
     */
    const val LOGICAL_OR: Byte = 19

    /**
     * Token '||' (logical or operator)
     */
    const val LOGICAL_XOR: Byte = 20

    /**
     * Token '&amp;&amp;' (logical and operator)
     */
    const val LOGICAL_AND: Byte = 21
    // *****************************************************
    // Separators
    /**
     * Token '\n' (separator that can be used instead of a semicolon or that will be ignored)
     */
    const val LINE_SEPARATOR: Byte = 22
    // *****************************************************
    // Assign
    /**
     * Token '=' for assigning values to variable
     */
    const val ASSIGN: Byte = 23

    /**
     * Token '++' for increasing variable values
     */
    const val INCR: Byte = 24

    /**
     * Token '--' for decreasing variable values
     */
    const val DECR: Byte = 25

    /**
     * Token '+=' for add-assigning values to a variable
     */
    const val ADD_ASSIGN: Byte = 26

    /**
     * Token '-=' for subtract-assigning values to a variable
     */
    const val SUB_ASSIGN: Byte = 27

    /**
     * Token '*=' for multiply-assigning values to a variable
     */
    const val MUL_ASSIGN: Byte = 28

    /**
     * Token '/=' for divide-assigning values to a variable
     */
    const val DIV_ASSIGN: Byte = 29

    /**
     * Token '%=' for modulo-assigning values to a variable
     */
    const val MOD_ASSIGN: Byte = 30

    /**
     * Token '^=' or '**=' for divide-assigning values to a variable
     */
    const val POW_ASSIGN: Byte = 31
    // *****************************************************
    // Brackets
    /**
     * Token '('
     */
    const val LPAREN: Byte = 32

    /**
     * Token ')'
     */
    const val RPAREN: Byte = 33

    /**
     * Token '{'
     */
    const val LCURL: Byte = 34

    /**
     * Token '}'
     */
    const val RCURL: Byte = 35


    // *****************************************************
    // Keywords
    /**
     * Keyword "do" for do-while-loops
     */
    const val KEYWORD_DO: Byte = 36

    /**
     * Keyword "while" for while-loops and do-while-loops
     */
    const val KEYWORD_WHILE: Byte = 37

    /**
     * Keyword "for" for for-loops
     */
    const val KEYWORD_FOR: Byte = 38

    /**
     * Keyword "if" for if-clauses
     */
    const val KEYWORD_IF: Byte = 39

    /**
     * Keyword "else" for if-else-clauses
     */
    const val KEYWORD_ELSE: Byte = 40

    /**
     * Keyword "true" for boolean-true-values
     */
    const val KEYWORD_TRUE: Byte = 41

    /**
     * Keyword "false" for boolean-false-values
     */
    const val KEYWORD_FALSE: Byte = 42
    // Declarations
    /**
     * Keyword "class" for declaring classes
     */
    const val KEYWORD_CLASS: Byte = 43

    /**
     * Keyword "extends" for setting a super-class in a class-declaration
     */
    const val KEYWORD_EXTENDS: Byte = 44

    /**
     * Keyword "implements" for implementing interfaces in a class-declaration
     */
    const val KEYWORD_IMPLEMENTS: Byte = 45

    /**
     * Keyword "static" for creating statics inside of classes
     */
    const val KEYWORD_STATIC: Byte = 46

    /**
     * Keyword "final" for creating a constant function, variable or class
     */
    const val KEYWORD_FINAL: Byte = 47

    /**
     * Keyword "public" for defining public functions, variables and classes
     */
    const val KEYWORD_PUBLIC: Byte = 48

    /**
     * Keyword "protected" for defining protected functions, variables and classes
     */
    const val KEYWORD_PROTECTED: Byte = 49

    /**
     * Keyword "private" for defining private functions, variables and classes
     */
    const val KEYWORD_PRIVATE: Byte = 50

    /**
     * Keyword "new" for creating new instances of a class
     */
    const val KEYWORD_NEW: Byte = 51

    /**
     * Keyword "function" for declaring functions
     */
    const val KEYWORD_FUNCTION: Byte = 52

    /**
     * Keyword "return" for returning function-results
     */
    const val KEYWORD_RETURN: Byte = 53


    // *****************************************************
    // Variable Declaration
    /**
     * Keyword "var" or "let" for declaring variables
     */
    const val KEYWORD_VAR: Byte = 54

    /**
     * Keyword "const" for declaring final variables
     */
    const val KEYWORD_CONST: Byte = 55

    /**
     * Keyword "dynamic" as return type for functions or for declaring variables
     */
    const val KEYWORD_DYNAMIC: Byte = 56

    /**
     * Keyword "byte" as return type for functions or for declaring variables
     */
    const val KEYWORD_BYTE: Byte = 57

    /**
     * Keyword "short" as return type for functions or for declaring variables
     */
    const val KEYWORD_SHORT: Byte = 58

    /**
     * Keyword "int" as return type for functions or for declaring variables
     */
    const val KEYWORD_INT: Byte = 59

    /**
     * Keyword "long" as return type for functions or for declaring variables
     */
    const val KEYWORD_LONG: Byte = 60

    /**
     * Keyword "float" as return type for functions or for declaring variables
     */
    const val KEYWORD_FLOAT: Byte = 61

    /**
     * Keyword "double" as return type for functions or for declaring variables
     */
    const val KEYWORD_DOUBLE: Byte = 62

    /**
     * Keyword "char" as return type for functions or for declaring variables
     */
    const val KEYWORD_CHAR: Byte = 63

    /**
     * Keyword "boolean" as return type for functions or for declaring variables
     */
    const val KEYWORD_BOOLEAN: Byte = 64

    /**
     * Keyword "import" for import statements
     */
    const val KEYWORD_IMPORT: Byte = 65

    /**
     * Keyword "void" for functions returning nothing
     */
    const val KEYWORD_VOID: Byte = 66

    /**
     * Keyword "constructor" for constructor style 1
     */
    const val KEYWORD_CONSTRUCTOR: Byte = 67

    /**
     * Keyword "as" for casting
     */
    const val KEYWORD_AS: Byte = 68
    @JvmStatic
    fun getName(b: Byte): String {
        return NAMES[b.toInt()]
    }

    fun hasValue(b: Byte): Boolean {
        return b < 5
    }

    fun getTokenLength(b: Byte): Byte {
        return TOKEN_LENGTH[b.toInt()]
    }

    fun getTokenLength(b: Byte, value: String?): Int {
        if (TOKEN_LENGTH[b.toInt()] != (-1).toByte()) return TOKEN_LENGTH[b.toInt()]
            .toInt() else if (b == STRING || b == CHARACTER) return value!!.length + 2
        return value!!.length
    }
}