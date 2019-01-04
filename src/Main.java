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

        // Open file to start parsing
        CharStream input = CharStreams.fromFileName("input");

        // Defining lexer
        lexer = new ImpLexer(input);

        // Obtaining input tokens
        tokenStream = new CommonTokenStream(lexer);

        // Defining parser
        parser = new ImpParser(tokenStream);

        // Start parsing
        ParserRuleContext tree = parser.prog();

        // Visiting the AST
        FileWriter file = new FileWriter(new File("arbore"));
        MyVisitor visitor = new MyVisitor(file);
        visitor.visit(tree);
        file.close();
    }
}
