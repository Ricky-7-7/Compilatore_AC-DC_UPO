package scanner;

@SuppressWarnings("serial")
public class LexicalException extends Exception {

	// Costruttore per carattere illegale generico
	public LexicalException(int riga, char c) {
		super("Errore lessicale a riga " + riga + ": carattere illegale '" + c + "'");
	}

	// Costruttore per errori con messaggio personalizzato e numero
	public LexicalException(int riga, String numero, String motivo) {
		super("Errore lessicale a riga " + riga + ": " + motivo + " - '" + numero + "'");
	}

	// Costruttore per errori generici con solo messaggio
	public LexicalException(int riga, String messaggio) {
		super("Errore lessicale a riga " + riga + ": " + messaggio);
	}
}

