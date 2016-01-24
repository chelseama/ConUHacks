import java.util.ArrayList;

public class helperMethods {
	public static String removeDashes(String input) {
		return input.replaceAll("-", "");
	}

	public static String removeNumbers(String input) {
		return input.replaceAll("[0-9]", "");
	}

	public static String removePipes(String input) {
		return input.replaceAll("\\d|\\|", "");
	}

	public static String removeAmpersands(String input) {
		return input.replaceAll("&", "");
	}

	public static String removeSlash(String input) {
		return input.replaceAll("/", "");
	}
	
	public static String removeEndOfLine(String input){
		return input.replaceAll("(\\r|\\n)", "");
	}

	public static String[] parseArrayList(ArrayList<String> input) {

		String[] output = new String[500];
		String tempInput;
		String temp[] = null;
		int k = 0;
		for (int i = 0; i < input.size(); i++) {
			tempInput = removeEndOfLine(removeSlash(removeAmpersands(removePipes(removeNumbers(removeDashes(input.get(i)))))));
			// This could've been done with regex
			if (tempInput != null && !tempInput.isEmpty()) {
				temp = tempInput.split("\\s+");

			}
			for (int i1 = 0; i1 < temp.length; i1++) {
				if (k < 500) {
					if (temp[i1] != null) {
						output[k++] = temp[i1];
					}
				}
			}

		}

		return output;

	}
}
