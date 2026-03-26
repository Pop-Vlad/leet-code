package p1044;

import java.math.BigInteger;
import java.util.*;

class Solution {

	private final long prime = BigInteger.probablePrime(48, new Random()).longValue();
	private final int base = 256;

	public String longestDupSubstring(String s) {

		String result = "";

		int left = 1;
		int right = s.length();

		while (left < right) {
			int m = left + (right - left) / 2;
			String currentDuplicate = findDupStringOfLength(s, m);
			if (currentDuplicate != null) {
				left = m + 1;
				result = currentDuplicate;
			} else {
				right = m;
			}
		}
		return result;
	}

	public String findDupStringOfLength(String s, int length) {
		long firstCharPower = 1;
		for (int i = 1; i < length; i++) {
			firstCharPower = (firstCharPower * base) % prime;
		}

		Set<Long> hashes = new HashSet<>();
		long currentHash = hash(s.substring(0, length));
		hashes.add(currentHash);

		for (int i = 1; i <= s.length() - length; i++) {
			currentHash = nextHash(currentHash, s.charAt(i - 1), s.charAt(i + length - 1), firstCharPower);
			boolean isNewHash = hashes.add(currentHash);
			if (!isNewHash) {
				return s.substring(i, i + length);
			}
		}

		return null;
	}

	public long hash(String s) {
		long h = 0L;
		for (int i = 0; i < s.length(); i++) {
			h = (h * base + s.charAt(i)) % prime;
		}
		return h;
	}

	public long nextHash(long h, char toRemove, char toAdd, long power) {
		h = (h + prime - toRemove * power % prime) % prime;
		h = (h * base + toAdd) % prime;
		return h;
	}
}
