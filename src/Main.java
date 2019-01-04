// Multumim echipei de CPL pentru acest schelet Main

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ImpLexer lexer = null;
        CommonTokenStream tokenStream = null;
        ImpParser parser = null;
        ParserRuleContext globalTree = null;

        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;

        // Deschidem fisierul input pentru a incepe parsarea
        CharStream input = CharStreams.fromFileName("input");

        // Definim Lexer-ul
        lexer = new ImpLexer(input);

        // Obtinem tokenii din input
        tokenStream = new CommonTokenStream(lexer);

        // Definim Parser-ul
        parser = new ImpParser(tokenStream);

        // Incepem parsarea
        ParserRuleContext tree = parser.prog();

        // Vizitam AST-ul
        FileWriter file = new FileWriter(new File("arbore"));
        MyVisitor visitor = new MyVisitor(file);
        visitor.visit(tree);
        file.close();
    }
}
