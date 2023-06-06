package Sintatic;

public class Analizer {

	private String input; // String de entrada
	private int currentIndex; // Índice actual en el String

	public Analizer(String input) {
		this.input = input;
		this.currentIndex = 0;
	}

	public boolean S() {
		boolean valid = false;
		if ((input.charAt(currentIndex) == 'a' && input.charAt(currentIndex + 1) == 'a')) {
			return false;
		}
		if (currentIndex < input.length() && (input.charAt(currentIndex) == 'a' || input.charAt(currentIndex) == 'b')) {
			if (B()) {
				valid = false;
				currentIndex++;
				if (currentIndex < input.length() && input.charAt(currentIndex) == '°') {
					valid = true;
				}
			} else {
				currentIndex++;
				valid = S();
			}

		} else if (currentIndex < input.length() && input.charAt(currentIndex) == '(') {
			currentIndex++;
			if (input.charAt(currentIndex) == 'a') {
				currentIndex++;
				if (currentIndex < input.length() && input.charAt(currentIndex) == ')') {
					currentIndex++;
					valid = S();
				} else {
					valid = false;

				}
			} else {
				valid = false;
			}
		} else if (currentIndex < input.length()
				&& (input.charAt(currentIndex) == '*' || input.charAt(currentIndex) == '+')) {
			if (input.charAt(currentIndex + 1) != '*' && input.charAt(currentIndex + 1) != '+'
					&& input.charAt(currentIndex + 1) != '(' && input.charAt(currentIndex + 1) != ')') {
				currentIndex++;
				valid = S();
			}
		}

		return valid;
	}

	public boolean B() {
		boolean valid = false;
		if (input.charAt(currentIndex) == 'b') {
			valid = true;
		}
		return valid;
	}

	public void syntaxError() {
		System.out.println("Syntax error at:");
		System.out.println(input);
		for (int i = 0; i <= currentIndex; i++) {
			System.out.print(" ");
		}
		System.out.println("^");
	}

}
