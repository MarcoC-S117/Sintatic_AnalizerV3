package Sintatic;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// analize with lexical the file to search valid expressions
		String cadena;
		int posicion;
		leerArchivo texto = new leerArchivo();
		String Tok = "";
		@SuppressWarnings("unused")
		String valor = null;
		String validString = "";
		texto.leer();
		ArrayList<String> cadenasValidas = texto.getCadenasValidas();
		ArrayList<Integer> pos = texto.getPosicion();
		for (int i = 0; i < cadenasValidas.size(); i++) {
			if (texto.cadenaValida(cadenasValidas.get(i))) {
				cadena = cadenasValidas.get(i);
				posicion = pos.get(i);
				System.out.println(cadena);
				System.out.println("Cadena " + (i + 1) + " termina en la linea " + posicion + " del archivo.");
				System.out.println();
				FA_Begin_0_End1 automata_DFA_3_2 = new FA_Begin_0_End1();
				FA_End_11 automata_DFA_3_3 = new FA_End_11();
				FA_11 automata_DFA_3_4 = new FA_11();
				if (cadena.equals("*") || cadena.equals("+")) {
					System.out.println("Operador encontrado en la linea " + posicion + " del archivo.");
					Tok = "Operador";
					valor = "Operador";
					validString += cadena;

				} else if (cadena.equals("(") || cadena.equals(")")) {
					System.out.println("Separador encontrado en la linea " + posicion + " del archivo.");
					Tok = "Separador";
					valor = "Separador";
					validString += cadena;

				} else if (automata_DFA_3_2.process(cadena) || automata_DFA_3_3.process(cadena)
						|| automata_DFA_3_4.process(cadena)) {
					// check if the string is accepted by DFA_3_2
					if (automata_DFA_3_2.process(cadena)) {
						Tok = "a";
						valor = String.valueOf(binaryToDecimal(cadena));
						System.out.println(Tok + ":" + automata_DFA_3_2.getTransitions());
						System.out.println("Final state: {q" + automata_DFA_3_2.estado() + "}");
						System.out.println();
					}
					// check if the string is accepted by DFA_3_3
					if (automata_DFA_3_3.process(cadena)) {
						Tok = "b";
						valor = null;
						System.out.println(Tok + ":" + automata_DFA_3_3.getTransitions());
						System.out.println("Final state: {q" + automata_DFA_3_2.estado() + "}");
						System.out.println();
					}
					if (automata_DFA_3_4.process(cadena)) {
						Tok = "c";
						valor = null;
						System.out.println(Tok + ":" + automata_DFA_3_4.getTransitions());
						System.out.println("Final state: {q" + automata_DFA_3_4.estado() + "}");
						System.out.println();
					}
					// if no automaton accepted the string, show an error message
					if ((automata_DFA_3_2.process(cadena) && !automata_DFA_3_3.process(cadena)
							&& !automata_DFA_3_4.process(cadena))
							|| (!automata_DFA_3_2.process(cadena) && automata_DFA_3_3.process(cadena)
									&& !automata_DFA_3_4.process(cadena))
							|| (!automata_DFA_3_2.process(cadena) && !automata_DFA_3_3.process(cadena)
									&& automata_DFA_3_4.process(cadena))) {
						System.out.println("The string is accepted by one FA.");
					} else if ((automata_DFA_3_2.process(cadena) && automata_DFA_3_3.process(cadena)
							&& !automata_DFA_3_4.process(cadena))
							|| (automata_DFA_3_2.process(cadena) && !automata_DFA_3_3.process(cadena)
									&& automata_DFA_3_4.process(cadena))
							|| (!automata_DFA_3_2.process(cadena) && automata_DFA_3_3.process(cadena)
									&& automata_DFA_3_4.process(cadena))) {
						System.out.println("The string is accepted by two FAs.");
					} else if (automata_DFA_3_2.process(cadena) && automata_DFA_3_3.process(cadena)
							&& automata_DFA_3_4.process(cadena)) {
						System.out.println("The string is accepted by all three FAs.");
					}
					System.out.println("El valor decimal es: " + binaryToDecimal(cadena));
					validString += Tok;
				}
				if (!(automata_DFA_3_2.process(cadena) || automata_DFA_3_3.process(cadena)
						|| automata_DFA_3_4.process(cadena)) && !(cadena.equals("(") || cadena.equals(")"))
						&& !(cadena.equals("*") || cadena.equals("+"))) {
					System.out.println("The string is not accepted by any FA.");
					Tok = "Not valid String";
					valor = null;
				}
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------");
		}

		// add final symbol to String with valid tokens
		validString += "°";
		System.out.println(validString);
		// analize with Sintatic the string
		Analizer parser = new Analizer(validString);
		if (parser.S()) {
			System.out.println("The string \"" + validString + "\" belongs to the grammar (language).");
		} else {
			System.out.println("The string \"" + validString + "\" does not belong to the grammar (language).");
			parser.syntaxError();
		}

	}

	public static int binaryToDecimal(String binary) {
		int decimal = 0;
		int power = 0;
		for (int i = binary.length() - 1; i >= 0; i--) {
			if (binary.charAt(i) == '1') {
				decimal += Math.pow(2, power);
			}
			power++;
		}
		return decimal;
	}

}
