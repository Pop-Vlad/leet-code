package p828;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

	Map<Character, List<Integer>> index = new HashMap<>();

	public int uniqueLetterString(String s) {

		//Build index
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			index.putIfAbsent(c, new ArrayList<>());
			index.get(c).add(i);
		}

		int count = 0;
		for (char c : index.keySet()) {
			List<Integer> positions = index.get(c);
			// Add dummy position after end of string
			positions.add(s.length());

			count += (positions.get(0) + 1) * (positions.get(1) - positions.get(0));
			for (int i = 1; i < positions.size() - 1; i++) {
				count += (positions.get(i) - positions.get(i - 1)) * (positions.get(i + 1) - positions.get(i));
			}
		}

		return count;
	}
}