
public class RomanNumerals {

	/*
	 * write a method that receives a string *correctly* representing a number in
	 * Roman Numerals and returns its value.
	 */

	public static String cleanup(String n) {
		n = n.trim().toLowerCase();
		return n.replace("iv", "f").replace("ix", "n").replace("xl", "F").replace("xc", "N").replace("cd", "4")
				.replace("cm", "9");
	}

	public static int translateChar(String n) {
		switch (n.substring(0, 1)) {
			case "i": return 1;
			case "v": return 5;
			case "x": return 10;
			case "l": return 50;
			case "c": return 100;
			case "d": return 500;
			case "m": return 1000;
			case "f": return 4;
			case "n": return 9;
			case "F": return 40;
			case "N": return 90;
			case "4": return 400;
			case "9": return 900;
			default: return 0;
		}
	}

	public static int RomanToInt(String n) {
		if (n.length() < 1) {
			return 0;
		}

		if (n.length() == 1) {
			return translateChar(n);
		}

		return translateChar(n) + RomanToInt(n.substring(1));

	}

	public static void main(String[] args) {
		System.out.println(RomanToInt(cleanup("ix"))); // 9
		System.out.println(RomanToInt(cleanup("MDCCLXXVI"))); // 1776
		System.out.println(RomanToInt(cleanup("MCMLIV"))); // 1954
		System.out.println(RomanToInt(cleanup("MCMXC"))); // 1990
		System.out.println(RomanToInt(cleanup("MMXIV"))); // 2014
		System.out.println(RomanToInt(cleanup("MMXVIII"))); // 2018
		System.out.println(RomanToInt(cleanup("mcDlxiV"))); // 1464
	}

}
