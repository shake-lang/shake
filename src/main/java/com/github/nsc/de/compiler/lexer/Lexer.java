package com.github.nsc.de.compiler.lexer;

import static com.github.nsc.de.compiler.util.HelpFunctions.asList;
import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.util.CompilerError;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private static final List<Character> NUMBERS = asList("0123456789");
    private static final List<Character> NUMBERS_DOT = asList("0123456789.");
    private static final List<Character> WHITESPACE = asList(" \t");
    private static final List<Character> IDENTIFIER = asList("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789");
    private static final List<Character> IDENTIFIER_START = asList("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_");

    private final CharacterInputStream in;

    public Lexer(CharacterInputStream in) {
        this.in = in;
    }

    public TokenInputStream makeTokens() {
        List<Token> tokens = new ArrayList<>();
        while(this.in.hasNext()) {
            char next = this.in.next();

            // Whitespace
            if(WHITESPACE.contains(next)) continue;

            // Linebreaks
            if(next == '\n') tokens.add(new Token(TokenType.LINE_SEPARATOR));

            // Punctuation
            else if(next == ';') tokens.add(new Token(TokenType.SEMICOLON));
            else if(next == ',') tokens.add(new Token(TokenType.COMMA));
            else if(next == '.') tokens.add(new Token(TokenType.DOT));

            // Numbers
            else if(NUMBERS.contains(next)) tokens.add(makeNumber());

            // Identifiers
            else if(IDENTIFIER_START.contains(next)) tokens.add(makeIdentifier());

            // Operator assign
            else if (this.in.peek(0,2).equals("**=")) { tokens.add(new Token(TokenType.POW_ASSIGN, "**=")); in.skip(2); }
            else if (this.in.peek(0,1).equals("^=")) { tokens.add(new Token(TokenType.POW_ASSIGN, "^=")); in.skip(); }
            else if (this.in.peek(0,1).equals("/=")) { tokens.add(new Token(TokenType.DIV_ASSIGN)); in.skip(); }
            else if (this.in.peek(0,1).equals("*=")) { tokens.add(new Token(TokenType.MUL_ASSIGN)); in.skip(); }
            else if (this.in.peek(0,1).equals("-=")) { tokens.add(new Token(TokenType.SUB_ASSIGN)); in.skip(); }
            else if (this.in.peek(0,1).equals("+=")) { tokens.add(new Token(TokenType.ADD_ASSIGN)); in.skip(); }

            else if (this.in.peek(0,1).equals("++")) { tokens.add(new Token(TokenType.INCR)); in.skip(); }
            else if (this.in.peek(0,1).equals("--")) { tokens.add(new Token(TokenType.DECR)); in.skip(); }

            // Math operators
            else if (next == '*' && this.in.hasNext() && in.peek() == '*') { tokens.add(new Token(TokenType.POW, "**")); in.skip(); }
            else if (next == '^') tokens.add(new Token(TokenType.POW));
            else if (next == '/') tokens.add(new Token(TokenType.DIV));
            else if (next == '*') tokens.add(new Token(TokenType.MUL));
            else if (next == '-') tokens.add(new Token(TokenType.SUB));
            else if (next == '+') tokens.add(new Token(TokenType.ADD));

            // Logical operators
            else if (next == '|' && this.in.hasNext() && in.peek() == '|') { tokens.add(new Token(TokenType.LOGICAL_OR)); in.skip(); }
            else if (next == '&' && this.in.hasNext() && in.peek() == '&') { tokens.add(new Token(TokenType.LOGICAL_AND)); in.skip(); }

            else if (next == '=' && this.in.hasNext() && in.peek() == '=') { tokens.add(new Token(TokenType.EQ_EQUALS)); in.skip(); }
            else if (next == '>' && this.in.hasNext() && in.peek() == '=') { tokens.add(new Token(TokenType.BIGGER_EQUALS)); in.skip(); }
            else if (next == '<' && this.in.hasNext() && in.peek() == '=') { tokens.add(new Token(TokenType.SMALLER_EQUALS)); in.skip(); }
            else if (next == '>') tokens.add(new Token(TokenType.BIGGER));
            else if (next == '<') tokens.add(new Token(TokenType.SMALLER));

            // Assign
            else if (next == '=') tokens.add(new Token(TokenType.ASSIGN));

            // Brackets
            else if (next == '(') tokens.add(new Token(TokenType.LPAREN));
            else if (next == ')') tokens.add(new Token(TokenType.RPAREN));

            else if (next == '{') tokens.add(new Token(TokenType.LCURL));
            else if (next == '}') tokens.add(new Token(TokenType.RCURL));
            else throw new UnrecognisedTokenError("Unrecognised Token: " + next, this.in.getPosition(), this.in.getPosition());
        }
        return new TokenInputStream(this.in.getSource(), tokens);
    }

    private Token makeNumber() {
        StringBuilder numStr = new StringBuilder();
        boolean dot = false;
        numStr.append(in.actual());
        while(in.hasNext() && NUMBERS_DOT.contains(in.peek())) {
            if(in.peek() == '.') {
                if(dot) break;
                dot = true;
            }
            numStr.append(in.next());
        }
        return dot ? new Token(TokenType.DOUBLE, Double.parseDouble(numStr.toString())) : new Token(TokenType.INTEGER, Integer.parseInt(numStr.toString()));

    }

    private Token makeIdentifier() {
        StringBuilder identifier = new StringBuilder();
        identifier.append(in.actual());
        while(in.hasNext() && IDENTIFIER.contains(in.peek())) {
            identifier.append(in.next());
        }

        String result = identifier.toString();
        // Keywords
        switch (result) {
            case "var":
                return new Token(TokenType.KEYWORD_VAR);
            case "dynamic":
                return new Token(TokenType.KEYWORD_DYNAMIC);
            case "byte":
                return new Token(TokenType.KEYWORD_BYTE);
            case "short":
                return new Token(TokenType.KEYWORD_SHORT);
            case "int":
                return new Token(TokenType.KEYWORD_INT);
            case "long":
                return new Token(TokenType.KEYWORD_LONG);
            case "float":
                return new Token(TokenType.KEYWORD_FLOAT);
            case "double":
                return new Token(TokenType.KEYWORD_DOUBLE);
            case "char":
                return new Token(TokenType.KEYWORD_CHAR);
            case "boolean":
                return new Token(TokenType.KEYWORD_BOOLEAN);
            case "function":
                return new Token(TokenType.KEYWORD_FUNCTION);
            case "true":
                return new Token(TokenType.KEYWORD_TRUE);
            case "false":
                return new Token(TokenType.KEYWORD_FALSE);
            case "do":
                return new Token(TokenType.KEYWORD_DO);
            case "while":
                return new Token(TokenType.KEYWORD_WHILE);
            case "for":
                return new Token(TokenType.KEYWORD_FOR);
            case "if":
                return new Token(TokenType.KEYWORD_IF);
            case "else":
                return new Token(TokenType.KEYWORD_ELSE);
        }
        return new Token(TokenType.IDENTIFIER, identifier.toString());

    }

    public static class LexerError extends CompilerError {
        public LexerError (String message, String name, String details, Position start, Position end) {
            super(message, name, details, start, end);
        }
        public LexerError (String name, String details, Position start, Position end) {
            super("Error occurred in lexer: " + name + ", " + details + " in " + start.getSource() + ":" + start.getLine() + ":" + start.getColumn(),name, details, start, end);
        }

        @Override
        public String toString() {
            return "Error occurred in lexer: " + getName() + ", " + getDetails() + " in " + getStart().getSource() + ":" + getStart().getLine() + ":" + getStart().getColumn();
        }
    }

    public static class UnrecognisedTokenError extends LexerError {
        public UnrecognisedTokenError (String details, Position start, Position end) {
            super("UnrecognisedTokenError", details, start, end);
        }
    }
}