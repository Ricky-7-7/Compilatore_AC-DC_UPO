package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scanner.LexicalException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

public class TestScanner {

	private static final String DATA_DIR = "src/test/data/testScanner/";

	@Test
	void testEOF() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "testEOF.txt");
		Token t = scanner.nextToken();
		assertEquals(TokenType.EOF, t.getTipo());
		assertTrue(t.getRiga() == 4);
	}

	@Test
	void testCaratteriSkip() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "caratteriSkip");
		Token t = scanner.nextToken();
		assertEquals(TokenType.EOF, t.getTipo());
	}

	@Test
	void testInt() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "testInt.txt");

		Exception e = assertThrows(LexicalException.class, () -> {
			scanner.nextToken();
		});
		assertEquals("Errore lessicale a riga 1: numero con zero iniziale non permesso - '0050'", e.getMessage());

		Token t = scanner.nextToken();
		assertEquals(TokenType.INT, t.getTipo());
		assertEquals(2, t.getRiga());
		assertEquals("698", t.getVal());
	}

	@Test
	void testFloat() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "testFloat.txt");

		Exception e = assertThrows(LexicalException.class, () -> {
			scanner.nextToken();
		});
		assertEquals("Errore lessicale a riga 1: numero con zero iniziale non permesso - '098.8095'", e.getMessage());

		Exception e1 = assertThrows(LexicalException.class, () -> {
			scanner.nextToken();
		});
		assertEquals("Errore lessicale a riga 2: numero float deve avere almeno una cifra dopo il punto - '0.'", e1.getMessage());

		Exception e2 = assertThrows(LexicalException.class, () -> {
			scanner.nextToken();
		});
		assertEquals("Errore lessicale a riga 3: numero float deve avere almeno una cifra dopo il punto - '98.'", e2.getMessage());

		Token t = scanner.nextToken();
		assertEquals(TokenType.FLOAT, t.getTipo());
		assertEquals(5, t.getRiga());
		assertEquals("89.99999", t.getVal());
	}

	@Test
	void testId() throws Exception{
		Scanner scanner =  new Scanner(DATA_DIR + "testId.txt");

		Token t = scanner.nextToken();
		assertEquals(TokenType.ID, t.getTipo());
		assertEquals(1, t.getRiga());
		assertEquals("jskjdsf2jdshkf", t.getVal());

		Token t1 = scanner.nextToken();
		assertEquals(TokenType.ID, t1.getTipo());
		assertEquals(2, t1.getRiga());
		assertEquals("printl", t1.getVal());

		Token t2 = scanner.nextToken();
		assertEquals(TokenType.ID, t2.getTipo());
		assertEquals(4, t2.getRiga());
		assertEquals("ffloat", t2.getVal());

		Token t3 = scanner.nextToken();
		assertEquals(TokenType.ID, t3.getTipo());
		assertEquals(6, t3.getRiga());
		assertEquals("hhhjj", t3.getVal());
	}

	@Test
	void testKeywords() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "testKeywords.txt");

		Token t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.PRINT);

		Token t1 = scanner.nextToken();
		assertEquals(t1.getTipo(), TokenType.TYFLOAT);

		Token t2 = scanner.nextToken();
		assertEquals(t2.getTipo(), TokenType.TYINT);
	}

	@Test
	void testIdKeyWords() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "testIdKeyWords.txt");

		int CountId = 0;
		int CountKeyWords = 0;

		Token t;
		do {
			t = scanner.nextToken();

			if(t.getTipo() == TokenType.PRINT || t.getTipo() == TokenType.TYFLOAT || t.getTipo() == TokenType.TYINT)
				CountKeyWords++;

			if(t.getTipo() == TokenType.ID)
				CountId++;

		} while (t.getTipo() != TokenType.EOF);

		assertEquals(4, CountKeyWords);
		assertEquals(4, CountId);
	}

	@Test
	void testOpsDels() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "testOpsDels.txt");

		int CountDels = 0;
		int CountOp = 0;
		int CountOpAssign = 0;

		Token t;
		do {
			t = scanner.nextToken();

			if(t.getTipo() == TokenType.PLUS || t.getTipo() == TokenType.MINUS || t.getTipo() == TokenType.TIMES || t.getTipo() == TokenType.DIVIDE)
				CountOp++;

			if(t.getTipo() == TokenType.SEMI || t.getTipo() == TokenType.ASSIGN)
				CountDels++;

			if(t.getTipo() == TokenType.OP_ASSIGN)
				CountOpAssign++;

		} while (t.getTipo() != TokenType.EOF);

		assertEquals(5, CountOp);
		assertEquals(4, CountOpAssign);
		assertEquals(3, CountDels);
	}

	@Test
	void testGenerale() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "testGenerale.txt");

		Exception e = assertThrows(LexicalException.class, () -> {
			while (true) {
				Token t = scanner.nextToken();
			}
		});

		assertEquals("Errore lessicale a riga 3: numero float deve avere almeno una cifra dopo il punto - '5.'", e.getMessage());
	}

	@Test
	void testCaratteriNonCaratteri() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "caratteriNonCaratteri.txt");

		Exception e = assertThrows(LexicalException.class, () -> {
			while (true) {
				Token t = scanner.nextToken();
			}
		});

		assertEquals("Errore lessicale a riga 1: carattere illegale '^'", e.getMessage());
	}

	@Test
	void testErroriNumbers() throws Exception {
		Scanner scanner = new Scanner(DATA_DIR + "erroriNumbers.txt");

		Token t = scanner.nextToken();
		t = scanner.nextToken();

		Exception e = assertThrows(LexicalException.class, () -> {
			Token t1 = scanner.nextToken();
		});
		assertEquals("Errore lessicale a riga 3: numero float con più di 5 cifre decimali - '123.121212'", e.getMessage());

		Exception e1 = assertThrows(LexicalException.class, () -> {
			Token t2 = scanner.nextToken();
		});
		assertEquals("Errore lessicale a riga 5: numero float con più di un punto decimale - '123.123.123'", e1.getMessage());

	}

	@Test
	void testPeekToken() throws Exception {
		Scanner s = new Scanner(DATA_DIR + "testGenerale.txt");
		assertEquals(TokenType.TYINT,s.peekToken().getTipo());
		assertEquals(TokenType.TYINT,s.nextToken().getTipo());
		assertEquals(TokenType.ID,s.peekToken().getTipo());
		assertEquals(TokenType.ID,s.nextToken().getTipo());
	}
}
