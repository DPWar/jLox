package com.craftinginterpreters.lox;

class Token {
    // Define variables (cannot be changed after being assigned)
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    // Define Token Object, set values
    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    // Method to return useful information about the Token
    public String toString() {
        return type + " " + lexeme + " " + literal + " " + line;
    }
}
