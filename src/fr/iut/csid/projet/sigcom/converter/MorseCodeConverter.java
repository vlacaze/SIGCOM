package fr.iut.csid.projet.sigcom.converter;

public class MorseCodeConverter {
	static final String DOT = "DOT"; //son court
	static final String DASH = "DASH"; //son long
	static final String GAP = "GAP"; // espace
	static final String LETTER_GAP = "LETTER_GAP"; // espace entre deux lettres
	static final String WORD_GAP = "WORD_GAP"; // espace entre deux mots
	
//	TABLE DE CARACTERES DE A-Z
	private static final String[][] LETTERS = new String[][] {
	/* A */new String[] { DOT, GAP, DASH },
	/* B */new String[] { DASH, GAP, DOT, GAP, DOT, GAP, DOT },
	/* C */new String[] { DASH, GAP, DOT, GAP, DASH, GAP, DOT },
	/* D */new String[] { DASH, GAP, DOT, GAP, DOT },
	/* E */new String[] { DOT },
	/* F */new String[] { DOT, GAP, DOT, GAP, DASH, GAP, DOT },
	/* G */new String[] { DASH, GAP, DASH, GAP, DOT },
	/* H */new String[] { DOT, GAP, DOT, GAP, DOT, GAP, DOT },
	/* I */new String[] { DOT, GAP, DOT },
	/* J */new String[] { DOT, GAP, DASH, GAP, DASH, GAP, DASH },
	/* K */new String[] { DASH, GAP, DOT, GAP, DASH },
	/* L */new String[] { DOT, GAP, DASH, GAP, DOT, GAP, DOT },
	/* M */new String[] { DASH, GAP, DASH },
	/* N */new String[] { DASH, GAP, DOT },
	/* O */new String[] { DASH, GAP, DASH, GAP, DASH },
	/* P */new String[] { DOT, GAP, DASH, GAP, DASH, GAP, DOT },
	/* Q */new String[] { DASH, GAP, DASH, GAP, DOT, GAP, DASH },
	/* R */new String[] { DOT, GAP, DASH, GAP, DOT },
	/* S */new String[] { DOT, GAP, DOT, GAP, DOT },
	/* T */new String[] { DASH },
	/* U */new String[] { DOT, GAP, DOT, GAP, DASH },
	/* V */new String[] { DOT, GAP, DOT, GAP, DOT, GAP, DASH },
	/* W */new String[] { DOT, GAP, DASH, GAP, DASH },
	/* X */new String[] { DASH, GAP, DOT, GAP, DOT, GAP, DASH },
	/* Y */new String[] { DASH, GAP, DOT, GAP, DASH, GAP, DASH },
	/* Z */new String[] { DASH, GAP, DASH, GAP, DOT, GAP, DOT }, };
	
//	TABLE DES CHIFFRES DE 0-9
	private static final String[][] NUMBERS = new String[][] {
	/* 0 */new String[] { DASH, GAP, DASH, GAP, DASH, GAP, DASH, GAP, DASH },
	/* 1 */new String[] { DOT, GAP, DASH, GAP, DASH, GAP, DASH, GAP, DASH },
	/* 2 */new String[] { DOT, GAP, DOT, GAP, DASH, GAP, DASH, GAP, DASH },
	/* 3 */new String[] { DOT, GAP, DOT, GAP, DOT, GAP, DASH, GAP, DASH },
	/* 4 */new String[] { DOT, GAP, DOT, GAP, DOT, GAP, DOT, GAP, DASH },
	/* 5 */new String[] { DOT, GAP, DOT, GAP, DOT, GAP, DOT, GAP, DOT },
	/* 6 */new String[] { DASH, GAP, DOT, GAP, DOT, GAP, DOT, GAP, DOT },
	/* 7 */new String[] { DASH, GAP, DASH, GAP, DOT, GAP, DOT, GAP, DOT },
	/* 8 */new String[] { DASH, GAP, DASH, GAP, DASH, GAP, DOT, GAP, DOT },
	/* 9 */new String[] { DASH, GAP, DASH, GAP, DASH, GAP, DASH, GAP, DOT }, };
	
//	TABLE DES ERREURS DE CARACTERES
	private static final String[] ERROR_GAP = new String[] { GAP };


//	RETOURNE LE SCHEMA A JOUER POUR UN CARACTERE
	static String[] pattern(char c) {
		if (c >= 'A' && c <= 'Z') {
			return LETTERS[c - 'A'];
		}
		if (c >= 'a' && c <= 'z') {
			return LETTERS[c - 'a'];
		} else if (c >= '0' && c <= '9') {
			return NUMBERS[c - '0'];
		} else {
			return ERROR_GAP;
		}
	}

//	RETOURNE LE SCHEMA A JOUER POUR PLUSIEURS CARACTERES
	public static String[] pattern(String str) {
		boolean lastWasWhitespace;
		int strlen = str.length();
		// Calculate how String our array needs to be.
		int len = 1;
		lastWasWhitespace = true;
		for (int i = 0; i < strlen; i++) {
			char c = str.charAt(i);
			if (Character.isWhitespace(c)) {
				if (!lastWasWhitespace) {
					len++;
					lastWasWhitespace = true;
				}
			} else {
				if (!lastWasWhitespace) {
					len++;
				}
				lastWasWhitespace = false;
				len += pattern(c).length;
			}
		}
		// Generate the pattern array. Note that we put an extra element of 0
		// in at the beginning, because the pattern always starts with the
		// pause
		String[] result = new String[len + 1];
		result[0] = WORD_GAP;
		int pos = 1;
		lastWasWhitespace = true;
		for (int i = 0; i < strlen; i++) {
			char c = str.charAt(i);
			if (Character.isWhitespace(c)) {
				if (!lastWasWhitespace) {
					result[pos] = WORD_GAP;
					pos++;
					lastWasWhitespace = true;
				}
			} else {
				if (!lastWasWhitespace) {
					result[pos] = LETTER_GAP;
					pos++;
				}
				lastWasWhitespace = false;
				String[] letter = pattern(c);
				System.arraycopy(letter, 0, result, pos, letter.length);
				pos += letter.length;
			}
		}
		return result;
	}
}
