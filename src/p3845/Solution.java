package p3845;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

	private class TrieNode {
		TrieNode[] children = new TrieNode[2];
		int numDescendants = 0;
	}

	private class Trie {

		TrieNode root = new TrieNode();

		public void add(int num) {
			TrieNode node = root;
			for (int i = 31; i >= 0; i--) {
				int bit = (num >> i) & 1;
				if (node.children[bit] == null) {
					node.children[bit] = new TrieNode();
				}
				node = node.children[bit];
				node.numDescendants++;
			}
		}

		public void remove(int num) {
			TrieNode node = root;
			for (int i = 31; i >= 0; i--) {
				int bit = (num >> i) & 1;
				node = node.children[bit];
				node.numDescendants--;
			}
		}

		public int findMaxXor(int num) {
			TrieNode node = root;
			int result = 0;
			for (int i = 31; i >= 0; i--) {
				int bit = (num >> i) & 1;
				if (node.children[1 - bit] != null && node.children[1 - bit].numDescendants > 0) {
					node = node.children[1 - bit];
					result = result | (1 << i);
				} else {
					node = node.children[bit];
				}
			}
			return result;
		}
	}

	public int maxXor(int[] nums, int k) {
		int n = nums.length;

		Deque<Integer> maxDeque = new ArrayDeque<>();
		Deque<Integer> minDeque = new ArrayDeque<>();
		int[] prefixXor = new int[n + 1];

		Trie trie = new Trie();
		int maxXor = 0;
		int l = 0;
		for (int r = 0; r < n; r++) {
			prefixXor[r + 1] = prefixXor[r] ^ nums[r];

			// add new value to the deques
			while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[r]) {
				maxDeque.pollLast();
			}
			maxDeque.addLast(r);
			while (!minDeque.isEmpty() && nums[minDeque.peekLast()] > nums[r]) {
				minDeque.pollLast();
			}
			minDeque.addLast(r);
			trie.add(prefixXor[r]);

			while (l < r && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
				// shrink window from the left
				if (!maxDeque.isEmpty() && maxDeque.peekFirst() == l) {
					maxDeque.pollFirst();
				}
				if (!minDeque.isEmpty() && minDeque.peekFirst() == l) {
					minDeque.pollFirst();
				}
				trie.remove(prefixXor[l]);
				l++;
			}

			int xor = trie.findMaxXor(prefixXor[r + 1]);
			if (xor > maxXor) {
				maxXor = xor;
			}
		}

		return maxXor;
	}
}