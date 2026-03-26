package p421;

public class Solution {

	class TrieNode {
		Integer value;
		TrieNode left;
		TrieNode right;
	}

	public int findMaximumXOR(int[] nums) {
		int n = nums.length;

		TrieNode root = new TrieNode();
		for (int i = 0; i < n; i++) {
			TrieNode node = root;
			for (int bitIdx = 31; bitIdx >= 0; bitIdx--) {
				if ((nums[i] & (1 << bitIdx)) == 0) {
					// bit is 0; go on left branch
					if (node.left == null) {
						node.left = new TrieNode();
					}
					node = node.left;
				} else {
					// bit is 1; go on right branch
					if (node.right == null) {
						node.right = new TrieNode();
					}
					node = node.right;
				}
			}
			node.value = nums[i];
		}

		int maxXor = 0;
		for (int i = 0; i < n; i++) {
			// find a match that maximizes the XOR for each element
			TrieNode node = root;
			int num = nums[i];
			for (int bitIdx = 31; bitIdx >= 0; bitIdx--) {
				if (node.left == null) {
					// can only choose right
					node = node.right;
					continue;
				}
				if (node.right == null) {
					// can only choose left
					node = node.left;
					continue;
				}
				if ((num & (1 << bitIdx)) == 0) {
					// bit is 0; go on right branch
					node = node.right;
				} else {
					// bit is 1; go on left branch
					node = node.left;
				}
			}

			int xor = num ^ node.value;
			if (xor > maxXor) {
				maxXor = xor;
			}
		}

		return maxXor;
	}
}