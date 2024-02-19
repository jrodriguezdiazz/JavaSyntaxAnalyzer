package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Principal  {
    public static void main(String[] args) throws Exception {
        String flexLexerPath = "src/code/Lexer.flex";
        String flexCupPath = "src/code/LexerCup.flex";
        String[] cupOptions = {"-parser", "Syntax", "src/code/Syntax.cup"};

        generateLexerAndParser(flexLexerPath, flexCupPath, cupOptions);
    }

    private static void generateLexerAndParser(String lexerPath, String cupLexerPath, String[] cupOptions) throws IOException, Exception {
        generateFile(lexerPath);
        generateFile(cupLexerPath);
        java_cup.Main.main(cupOptions);

        moveFileToDirectory("sym.java", "src/code/sym.java");
        moveFileToDirectory("Syntax.java", "src/code/Syntax.java");
    }

    private static void generateFile(String filePath) throws IOException, Exception {
        File file = new File(filePath);
        JFlex.Main.generate(file);
    }

    private static void moveFileToDirectory(String sourceFileName, String destinationFileName) throws IOException {
        Path sourcePath = Paths.get(sourceFileName);
        Path destinationPath = Paths.get(destinationFileName);

        if (Files.exists(destinationPath)) {
            Files.delete(destinationPath);
        }
        Files.move(sourcePath, destinationPath);
    }
}
