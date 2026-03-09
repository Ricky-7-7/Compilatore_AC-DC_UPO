package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import token.Token;
import token.TokenType;

public class TestToken {

	@Test
	void CostruttoreToken() {
		Token t1=new Token(7, TokenType.INT,"9");
		assertEquals(7,t1.getRiga());
		assertEquals(TokenType.INT,t1.getTipo());
		assertEquals("9",t1.getVal());
	}

	@Test
	void CostruttoreToken1() {
		Token t1=new Token(7, TokenType.PLUS);
		assertEquals(7,t1.getRiga());
		assertEquals(TokenType.PLUS,t1.getTipo());
	}

	@Test
	void Test_To_string() {
		Token t1=new Token(7, TokenType.INT,"9");
		assertEquals("<INT, r:7, val:9>",t1.toString());
	}

}
