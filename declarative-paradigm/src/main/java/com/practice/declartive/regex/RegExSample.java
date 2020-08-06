package com.practice.declartive.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSample {

	public static void main(String[] args) {

		// 1.Regular expression in different approaches:
		diffApproachForRegEx();

		//2.Samples in different categories:
		samples();

		regExHackersRankProb();

		tokenizeUsingSplit("ac;bd;def;e", ";");
	}

	public static void diffApproachForRegEx() {
		System.out.println("Different way of validating Pattern:");
		// 1st way
		Pattern p = Pattern.compile(".*");
		Matcher m = p.matcher("as");
		System.out.println("1st way: " + m.matches());

		// 2nd way
		System.out.println("2nd way: " + Pattern.compile(".").matcher("as").matches());

		// 3rd way
		System.out.println("3rd way: " + Pattern.matches(".", "as"));
		System.out.println();
	}

	/* Matcher class has the following utility methods: find(), start(), group(). 
	 * 	find() method returns true until there is a match for the regular expression in the string. 
	 * 	start() method gives the starting index of the match. 
	 * 	group() method returns the matching part of the string.
	 */
	private static void regex(String regex, String str) {
		System.out.println("Str: " + str + " RegEx: " + regex);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			System.out.println("Index: " + matcher.start() + " Matching Part: " + matcher.group());
		}
		System.out.println("Is Pattern Match? " + Pattern.matches(regex, str));
		System.out.println();
	}

	public static void samples() {
		simplRegExSamples();

		metacharacters();

		multipleCharacters(); //or repetitions

		groupCharsRegEx();

		commonSamples1();
		commonSamples2();
		commonSamples3();

	}

	private static void simplRegExSamples() {
		regex("12", "122345612");
	}

	/*
	 * Certain characters escaped by \ have special meaning in regular expressions. For example, /s matches a whitespace character
	 * Use single slash(\) or double slash(\\) for certain characters.  
	 */
	private static void metacharacters() {
		//Single Slash:
		regex("\\s", "12 1234 123 "); //Space character - \s
		regex("\\d", "12 12"); //Digit - \d
		regex("\\w", "ab 12 _"); //Word character (letter, digits or underscore) - \w

		//Double Slash: 
		//to look for characters . or * in a regular expression, then you should escape them. 
		//Example: If I want to look for ...(3 dots), we should use ... To represent ... as string we should put two 's instead of 1.
		regex("\\.\\.\\.", "...a....b...c");
		regex("\\*\\*\\*", "***a****b***c");

		System.out.println("metacharacters d....");// \\d means digit

		regex("\\d", "abc");// false (non-digit)
		regex("\\d", "1");// true (digit and comes once)
		regex("\\d", "4443");// false (digit but comes more than once)
		regex("\\d", "323abc");// false (digit and char)

		System.out.println("metacharacters D....");// \\D means non-digit

		regex("\\D", "abc");// false (non-digit but comes more than once)
		regex("\\D", "1");// false (digit)
		regex("\\D", "4443");// false (digit)
		regex("\\D", "323abc");// false (digit and char)
		regex("\\D", "m");// true (non-digit and comes once)

		System.out.println("metacharacters D with quantifier....");
		regex("\\D*", "mak");// true (non-digit and may come 0 or more times)
	}

	//Regular Expressions for Multiple Characters
	private static void multipleCharacters() {
		//"+" -> 1 or more characters. For example a+ looks for 1 or more character a's.
		regex("a+", "aaabaayza");

		//"+ combinations" ->Regular expressions can be joined together to look for a combination. 
		//Note: a+b+ looks 1 or more a's and 1 or more b's next to each other. Notice that only a's or only b's do not match.
		regex("a+b+", "aabcacaabbbcbb");

		//"*" -> 0 or more repetitions
		regex("a+b*c+", "abcdacdabdbc");

		//"?" -> 0 or 1 repetitions
		regex("a+b*c?", "adabdabcdabccd");
		regex("a+?", "aaaaab");

		//"^" -> ^a looks for anything other than a
		regex("[^a]+", "bcadefazyx");

		//"." -> matches any character
		regex("a.c", "abca ca!cabbc");

		//"[]" -> Look for one or more characters from a to z (only small case).
		regex("[a-z]+", "abcZ2xyzN1yza");
		regex("[^abcd]+a", "efgazyazyzb");

		System.out.println("Quantifier Samples:");
		System.out.println("? quantifier ....");
		regex("[amn]?", "a");// true (a or m or n comes one time)
		regex("[amn]?", "amn");// false (a comes more than one time)

		System.out.println("+ quantifier ....");
		regex("[amn]+", "a");// true (a or m or n once or more times)
		regex("[amn]+", "aammmnn");// true (a or m or n comes more than once)
		regex("[amn]+", "amzzta");// false (z and t are not matching pattern)

		System.out.println("n quantifier ....");
		regex("[amn]{5}", "aammmnn");// false (X occurs 5 times only)
		regex("[amn]{3,8}", "a");// false (X occurs at least 3 times but less than 8 times)

		System.out.println("* quantifier ....");
		regex("[amn]*", "ammfmna");// true (a or m or n may come zero or more times)
	}

	private static void groupCharsRegEx() {
		System.out.println("RegEx Character Class:");
		regex("[amn]", "abcd");// false (not a or m or n)
		regex("[amn]", "a");// true (among a or m or n)
		regex("[amn]", "amn");// false (among a or m or n)
		regex("[amn]", "ammmna");// false (m and a comes more than once)

		regex("[^amn]", "1");// true (Any character except a or m or n)

		regex("[a-zA-Z]", "s"); // true (a through z or A through Z, inclusive (range))
		regex("[a-zA-Z]", "fsfs"); // false (it accepts only one char)

		regex("[a-d[M-P]]", "O"); // true (a through d, or m through p: [a-dm-p] (union))
		regex("[a-d[M-P]]", "f"); // false

		regex("[a-z&&[def]]", "f"); // true (d, e, or f (intersection))
		regex("[a-z&&[^bc]]", "b");// false (a through z, except for b and c: [ad-z] (subtraction))
		regex("[a-z&&[^m-p]]", "m");// false (a through z, and not m through p: [a-lq-z](subtraction))
	}

	private static void commonSamples1() {
		regex("[a-zA-Z0-9]{6}", "arun32");// true
		regex("[a-zA-Z0-9]{6}", "kkvarun32");// false (more than 6 char)
		regex("[a-zA-Z0-9]{6}", "JA2Uk2");// true
		regex("[a-zA-Z0-9]{6}", "arun$2");// false ($ is not matched)
	}

	private static void commonSamples2() {
		// RegEx: input should contains one or more digit
		System.out.println("Example 1:");
		String input = "This order was placed for QT3000! OK?";
		String pattern = "(.*)(\\d+)(.*)";
		regex(pattern, input);

		input = " Derek Banas CA 12345 PA (412)555-1212 john_smith@gmail.com";
		// RegEx: Find the word in the input
		System.out.println("\nExample 2:");
		regex("\\s[A-Za-z]{2,20}\\s", input);

		// RegEx: Find the digits
		System.out.println("\nExample 3:");
		regex("\\s\\d{5}\\s", input);

		// RegEx: Find the digits
		System.out.println("\nExample 4:");
		regex("[a-zA-Z0-9._%-]+@+gmail+\\.[A-Za-z]{2,4}", input);
	}

	private static void commonSamples3() {
		System.out.println("by character classes and quantifiers ...");
		regex("[789]{1}[0-9]{9}", "9953038949");// true
		regex("[789][0-9]{9}", "9953038949");// true

		regex("[789][0-9]{9}", "99530389490");// false (11 characters)
		regex("[789][0-9]{9}", "6953038949");// false (starts from 6)
		regex("[789][0-9]{9}", "8853038949");// true

		System.out.println("by metacharacters ...");
		regex("[789]{1}\\d{9}", "8853038949");// true
		regex("[789]{1}\\d{9}", "3853038949");// false (starts from 3)
	}

	public static void regExHackersRankProb() {
		String namePattern = "[a-z]{1,20}";
		String emailPattern = "[a-z.]{1,40}+@gmail+.[a-z]{3}";
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		List<String> data = new ArrayList<>();
		for (int a0 = 0; a0 < N; a0++) {
			String firstName = in.next();
			String emailID = in.next();
			if (isValidInput(emailPattern, emailID) && isValidInput(namePattern, firstName)) {
				data.add(firstName);
			}
		}
		Collections.sort(data);
		data.forEach(System.out::println);
		in.close();
	}

	private static boolean isValidInput(String regEx, String input) {
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	//Tokenizing: 	Tokenizing means splitting a string into several sub strings based on delimiters. 
	public static void tokenizeUsingSplit(String str, String regex) {
		String[] tokens = str.split(regex);
		System.out.println(Arrays.toString(tokens));
	}

	//Tokenizing using Scanner Class
	private static void tokenizeUsingScanner(String string, String regex) {
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(regex);
		List<String> matches = new ArrayList<String>();
		while (scanner.hasNext()) {
			matches.add(scanner.next());
		}
		System.out.println(matches);
	}

}
