public class SwapWord {

	public static String swapPair(String str) {
		//Handle case where empty or 1 character string is passed
		if (str.length() < 2){
			return str;
		}
		else {
			return swap(str.charAt(0), str.charAt(1)) + swapPair(str.substring(2));
		}
		
	}

	private static String swap(char c1, char c2) {
		char tmp = c1;
		c1 = c2;
		c2 = tmp;
		return Character.toString(c1)+Character.toString(c2);
	}

	public static void main(String[] args) {
		System.out.println(swapPair("abcdefg"));
	}

}
