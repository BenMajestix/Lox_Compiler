/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lox;

/**
 *
 * @author benbartel
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class lox {
    static boolean hadError = false;


    public static void main(String[] args) throws IOException {
        //Wenn es mehr als ein Arg gibt, System exit
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            //Bei einem Arg, also einem eingegebenen Filenamen, dann startet das Programm
            //Es wird dann noch der Dateiname mitgegeben
            runFile(args[0]);
        } else {
            //Ohne Arg kommt man in einen Modus wo man eine Line code nach der anderen eingeben kann.
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        //Liest die Datei, welcher der Nutzer mitgegeben hat, und speichert den Inhalt
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        //Läuft die Funkt run() und gibt den Datei inhalt mit.
        run(new String(bytes, Charset.defaultCharset()));

        //Wenn es einen Fehler gab stoppt das Programm.
        if (hadError) System.exit(65);
    }


    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);

            hadError = false;
        }
    }


    private static void run(String source) {
        Scan scanner = new Scan(source);
        //Macht eine Liste aller Tokens, also bestimmte bauteile des Codes, und speichert die.
        //Tokens sind zB.: , ; variableName if else while ...
        List<Token> tokens = scanner.scanTokens();
        //Printed alle Tokens nacheinander aus.
        for (Token token : tokens) {
            System.out.println(token);
        }
    }


    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}


