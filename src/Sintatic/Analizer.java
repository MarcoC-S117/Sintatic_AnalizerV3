package Sintatic;

public class Analizer {

	private String input; // String de entrada
	private int currentIndex; // Índice actual en el String
	private Boolean valid = false;

	public Analizer(String input) {
		this.input = input;
		this.currentIndex = 0;
	}

	public boolean E() {
		if (currentIndex == input.length() - 1) {
			// Se alcanzó el final de la cadena, no hay más caracteres para procesar
			return true;
		}		
	
		if (input.charAt(currentIndex) == 'a' || input.charAt(currentIndex) == 'b') {
			currentIndex++;
			if (currentIndex < input.length() && (input.charAt(currentIndex) == 'a' || input.charAt(currentIndex) == 'b')) {
				// Dos 'a' o 'b' consecutivos, no es válido
				return false;
			}
			return E();
		} else if (input.charAt(currentIndex) == '+' || input.charAt(currentIndex) == '*') {
			currentIndex++;
			if (currentIndex >= input.length() || input.charAt(currentIndex) == '+' || input.charAt(currentIndex) == '*') {
				// Dos operadores seguidos, no es válido
				return false;
			}
			return E();
		} else if (input.charAt(currentIndex) == '(') {
			currentIndex++;
			boolean isValid = E();
			if (currentIndex >= input.length() || input.charAt(currentIndex) != ')') {
				// Falta el paréntesis de cierre, no es válido
				return false;
			} else if (currentIndex + 1 < input.length() && (input.charAt(currentIndex + 1) == '+' || input.charAt(currentIndex + 1) == '*')) {
				currentIndex += 2;
				return E();
			}
			
			if (currentIndex < input.length() && (input.charAt(currentIndex) == 'a' || input.charAt(currentIndex) == 'b')) {
				// Dos 'a' o 'b' consecutivos después del paréntesis, no es válido
				return false;
			}
			isValid = isValid && E(); // Llamar a E() después de verificar el paréntesis de cierre
			return isValid;
		} else {
			// Carácter no válido
			return false;
		}
		
	}
	


	public void syntaxError() {
		System.out.println("Syntax error at:");
		System.out.println(input);
		for (int i = 0; i < currentIndex; i++) {
			System.out.print(" ");
		}
		System.out.println("^");
	}

}
