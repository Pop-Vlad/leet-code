package p273;

import java.util.Map;

class Solution {

	Map<Integer, String> tensMap = Map.of(
			2, "Twenty",
			3, "Thirty",
			4, "Forty",
			5, "Fifty",
			6, "Sixty",
			7, "Seventy",
			8, "Eighty",
			9, "Ninety");

	Map<Integer, String> unitsMap = Map.of(
			1, "One",
			2, "Two",
			3, "Three",
			4, "Four",
			5, "Five",
			6, "Six",
			7, "Seven",
			8, "Eight",
			9, "Nine");

	Map<Integer, String> teensMap = Map.of(
			0, "Ten",
			1, "Eleven",
			2, "Twelve",
			3, "Thirteen",
			4, "Fourteen",
			5, "Fifteen",
			6, "Sixteen",
			7, "Seventeen",
			8, "Eighteen",
			9, "Nineteen");

	public String numberToWords(int num) {
		if (num == 0) {
			return "Zero";
		}

		int units = num % 1000;
		int thousands = num / 1000 % 1000;
		int millions = num / 1000_000 % 1000;
		int billions = num / 1000_000_000;

		String billionsString = convertNumberPart(billions);
		String millionsString = convertNumberPart(millions);
		String thousandsString = convertNumberPart(thousands);
		String unitsString = convertNumberPart(units);

		StringBuilder sb = new StringBuilder();
		if (billionsString != null) {
			sb.append(billionsString).append("Billion ");
		}
		if (millionsString != null) {
			sb.append(millionsString).append("Million ");
		}
		if (thousandsString != null) {
			sb.append(thousandsString).append("Thousand ");
		}
		if (unitsString != null) {
			sb.append(unitsString);
		}
		return sb.toString().trim();
	}

	public String convertNumberPart(int part) {
		if (part == 0) {
			return null;
		}
		int hundreds = part / 100;
		int tens = part / 10 % 10;
		int units = part % 10;

		StringBuilder sb = new StringBuilder();
		if (hundreds > 0) {
			sb.append(unitsMap.get(hundreds)).append(" Hundred ");
		}
		if (tens >= 2) {
			sb.append(tensMap.get(tens)).append(" ");
			if(units > 0) {
				sb.append(unitsMap.get(units)).append(" ");
			}
		} else if (tens == 1) {
			sb.append(teensMap.get(units)).append(" ");
		} else {
			if(units > 0) {
				sb.append(unitsMap.get(units)).append(" ");
			}
		}
		return sb.toString();
	}
}