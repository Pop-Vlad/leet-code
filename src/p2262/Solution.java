package p2262;

public class Solution {

	private final int[] lastCharacterOccurrence = new int[256];

	public long appealSum(String s) {

		// Initialize index
		for (int i = 'a'; i <= 'z'; i++) {
			lastCharacterOccurrence[i] = -1;
		}

		int l = s.length();
		long count = 0;

		for (int i = 0; i < l; i++) {
			char c = s.charAt(i);
			count += (long) (i - lastCharacterOccurrence[c]) * (l - i);
			lastCharacterOccurrence[c] = i;
		}

		return count;
	}
}