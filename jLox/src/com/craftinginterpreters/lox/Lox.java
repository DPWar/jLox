package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    static boolean hadError = false;


    public static void main(String[] args) throws IOException {
        // Ensures that the argument length while running the script is 1 (source code)
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        // If the ars length == 1 - Running through the command line
        } else if (args.length == 1) {
            runFile(args[0]);
        // Else running interactively, can enter code as you wish
        } else {
            runPrompt();
        }
    }

    // Running through command line
    private static void runFile(String path) throws IOException {
        // Create a byte array of all bytes in file path
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        // Pass all data into the run function as a string
        run(new String(bytes, Charset.defaultCharset()));
    }

    // Running interactively
    private static void runPrompt() throws IOException {
        // Reader for the user input
        InputStreamReader input = new InputStreamReader(System.in);
        // Reader for the terminal
        BufferedReader reader = new BufferedReader(input);

        // Infinite loop until line == null
        for (; ; ) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false; // Reset error, so program does not get killed after 1 error
        }
    }

    // Creates a list of tokens and prints them (for now)
    private static void run(String source) {
        // Create a Scanner (lexer) and pass in source code
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now, we will just print the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }

        if (hadError) System.exit(65);
    }

    static void error(int line, String message) {
        report(line, message);
    }

    private static void report(int line, String message) {
        System.err.println("[line " + line + "]" + "Error :" + message);
        hadError = true;
    }
}
