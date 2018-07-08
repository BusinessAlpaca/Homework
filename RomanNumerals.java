
public class RomanNumerals {

	/* write a method that receives a string *correctly* representing a number in
	 * Roman Numerals and returns its value.
	 */
	
	public static String cleanup(String n) {
		n = n.trim().toLowerCase();
		return n.replace("iv", "f").replace("ix", "n").replace("xl", "F").replace("xc", "N").replace("cd", "4").replace("cm",  "9");
	}
	
	public static int translateChar (String n) {		
		if (n.substring(0, 1).equals("i")) return 1;
		if (n.substring(0, 1).equals("v")) return 5;
		if (n.substring(0, 1).equals("x")) return 10;
		if (n.substring(0, 1).equals("l")) return 50;
		if (n.substring(0, 1).equals("c")) return 100;
		if (n.substring(0, 1).equals("d")) return 500;
		if (n.substring(0, 1).equals("m")) return 1000;
		if (n.substring(0, 1).equals("f")) return 4;
		if (n.substring(0, 1).equals("n")) return 9;
		if (n.substring(0, 1).equals("F")) return 40;
		if (n.substring(0, 1).equals("N")) return 90;
		if (n.substring(0, 1).equals("4")) return 400;
		if (n.substring(0, 1).equals("9")) return 900;
		return 0;
	}
	
	public static int RomanToInt (String n) {
		if (n.length() < 1) {
			return 0;
		}
		
		if (n.length() == 1) {
			return translateChar(n);
		}
		
		return translateChar(n) + RomanToInt(n.substring(1));
		
	}
	
	public static void main(String[] args) {		
		System.out.println(RomanToInt(cleanup("ix"))); //9
		System.out.println(RomanToInt(cleanup("MDCCLXXVI"))); //1776
		System.out.println(RomanToInt(cleanup("MCMLIV"))); //1954
		System.out.println(RomanToInt(cleanup("MCMXC"))); //1990
		System.out.println(RomanToInt(cleanup("MMXIV"))); //2014
		System.out.println(RomanToInt(cleanup("MMXVIII"))); //2018
	}

}
