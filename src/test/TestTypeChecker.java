package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import scanner.Scanner;
import symbolTable.SymbolTable;
import visitor.TypeCheckingVisitor;
import visitor.type.ErrorType;
import visitor.type.OkType;

class TestTypeChecker {

    private static final String DATA_DIR = "src/test/data/testTypeChecker/";
    private TypeCheckingVisitor typeChecker;

    @BeforeEach
    void setUp() {
        typeChecker = new TypeCheckingVisitor();
        SymbolTable.clearTable();
    }

    @Test
    void testDicRipetute() throws Exception {
        Parser parser = new Parser(new Scanner(DATA_DIR + "1_dicRipetute.txt"));
        NodeProgram result = parser.parse();
        typeChecker.visit(result);

        assertEquals(new ErrorType("Errore semantico: a già dichiarato!"), typeChecker.getResType());
    }

    @Test
    void testIdNonDec() throws Exception {
        Parser parser = new Parser(new Scanner(DATA_DIR + "2_idNonDec.txt"));
        NodeProgram result = parser.parse();
        typeChecker.visit(result);

        assertEquals(new ErrorType("Errore semantico: b non è stato dichiarato!"), typeChecker.getResType());
    }

    @Test
    void testIdNonDec2() throws Exception {
        Parser parser = new Parser(new Scanner(DATA_DIR + "3_idNonDec.txt"));
        NodeProgram result = parser.parse();
        typeChecker.visit(result);

        assertEquals(new ErrorType("Errore semantico: c non è stato dichiarato!"), typeChecker.getResType());

    }

    @Test
    void testTipoNonCompatibile() throws Exception {
        Parser parser = new Parser(new Scanner(DATA_DIR + "4_tipoNonCompatibile.txt"));
        NodeProgram result = parser.parse();
        typeChecker.visit(result);

        assertEquals(new ErrorType("Errore semantico: assegnamento a tipo non corrispondente!"),
                typeChecker.getResType());

    }

    @Test
    void testCorretto() throws Exception {
        Parser parser = new Parser(new Scanner(DATA_DIR + "5_corretto.txt"));
        NodeProgram result = parser.parse();
        typeChecker.visit(result);

        assertEquals(OkType.class, typeChecker.getResType().getClass());

    }

    @Test
    void testCorretto2() throws Exception {
        Parser parser = new Parser(new Scanner(DATA_DIR + "6_corretto.txt"));
        NodeProgram result = parser.parse();
        typeChecker.visit(result);

        assertEquals(OkType.class, typeChecker.getResType().getClass());

    }

    @Test
    void testCorretto3() throws Exception {
        Parser parser = new Parser(new Scanner(DATA_DIR + "7_corretto.txt"));
        NodeProgram result = parser.parse();
        typeChecker.visit(result);

        assertEquals(OkType.class, typeChecker.getResType().getClass());
    }
}
