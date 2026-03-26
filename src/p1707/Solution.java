package p1707;

import java.util.*;

public class Solution {

	private class TrieNode {

		TrieNode left;
		TrieNode right;
		Integer value;
	}

	public int[] maximizeXor(int[] nums, int[][] queries) {
		int n = nums.length;
		Arrays.sort(nums);

		int m = queries.length;
		List<int[]> queriesList = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			queriesList.add(new int[]{queries[i][0], queries[i][1], i}); // [q, max, i]
		}
		queriesList.sort(Comparator.comparingInt(q -> q[1]));
		queriesList.toArray(queries);

		int[] answers = new int[m];

		// build bit trie while querying; only build the trie up to tme max value in query
		TrieNode root = new TrieNode();
		int posInNums = 0;
		int[] query;
		for (int i = 0; i < m; i++) {
			query = queries[i];
			int ans = -1;

			// build trie
			while (posInNums < n && nums[posInNums] <= query[1]) {
				TrieNode node = root;
				for (int bitIdx = 31; bitIdx >= 0; bitIdx--) {
					if (((nums[posInNums] >> bitIdx) & 1) == 0) {
						// bit is 0
						if (node.left == null) {
							node.left = new TrieNode();
						}
						node = node.left;
					} else {
						// bit is 1
						if (node.right == null) {
							node.right = new TrieNode();
						}
						node = node.right;
					}
				}
				node.value = nums[posInNums];
				posInNums++;
			}

			// query the trie
			TrieNode node = root;
			if (posInNums > 0) {
				for (int bitIdx = 31; bitIdx >= 0; bitIdx--) {
					if ((query[0] >> bitIdx & 1) == 0) {
						// prefer 1 branch
						if (node.right != null) {
							node = node.right;
						} else {
							node = node.left;
						}
					} else {
						// prefer 0 branch
						if (node.left != null) {
							node = node.left;
						} else {
							node = node.right;
						}
					}
				}
				if (node.value != null) {
					ans = query[0] ^ node.value;
				}
			}

			answers[query[2]] = ans;
		}

		return answers;
	}
}