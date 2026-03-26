package p3213;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

	class Pair implements Comparable<Pair> {
		int position;
		int cost;

		public Pair(int position, int cost) {
			this.position = position;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair pair) {
			return this.cost - pair.cost; // Least cost
			//return - this.position + pair.position; // Closest to finish
			//return this.cost / this.position - pair.cost / pair.position; // Most likely to be part of solution?
		}
	}

	class TrieNode {
		TrieNode[] childNode;
		boolean wordEnd;
		int cost;

		TrieNode() {
			childNode = new TrieNode[26];
			wordEnd = false;
			cost = Integer.MAX_VALUE;
		}
	}

	class Trie {
		TrieNode root;

		Trie() {
			root = new TrieNode();
		}

		// Function to insert a key into the Trie
		void insert(String key, int cost) {
			TrieNode currentNode = root;
			for (int i = 0; i < key.length(); i++) {
				int index = key.charAt(i) - 'a';
				if (currentNode.childNode[index] == null) {
					currentNode.childNode[index]
							= new TrieNode();
				}
				currentNode = currentNode.childNode[index];
			}
			currentNode.wordEnd = true;
			currentNode.cost = Math.min(currentNode.cost, cost);
		}

		// Function to search for words that the key starts with in the Trie
		List<Pair> search(String key) {
			List<Pair> results = new ArrayList<>();
			TrieNode currentNode = root;
			for (int i = 0; i < key.length(); i++) {
				int index = key.charAt(i) - 'a';
				if (currentNode.childNode[index] == null) {
					break;
				}
				currentNode = currentNode.childNode[index];
				if (currentNode.wordEnd) {
					results.add(new Pair(i + 1, currentNode.cost));
				}
			}
			return results;
		}
	}

	public int minimumCost(String target, String[] words, int[] costs) {

		Trie index = new Trie();
		for (int i = 0; i < words.length; i++) {
			index.insert(words[i], costs[i]);
		}

		PriorityQueue<Pair> queue = new PriorityQueue<>();
		queue.add(new Pair(0, 0));
		int targetLen = target.length();
		int[] costToPositionCache = new int[targetLen + 1];
		for (int i = 1; i <= targetLen; i++) {
			costToPositionCache[i] = Integer.MAX_VALUE;
		}

		int minCost = Integer.MAX_VALUE;
		int numPolls = 0;
		while (!queue.isEmpty()) {
			Pair currentConstruct = queue.poll();
			numPolls++;

			if (currentConstruct.position > targetLen || currentConstruct.cost > costToPositionCache[currentConstruct.position]) {
				continue;
			}

			if (currentConstruct.position == targetLen) {
				minCost = currentConstruct.cost;
				break;
			}

			List<Pair> matches = index.search(target.substring(currentConstruct.position, targetLen));
			for (Pair p : matches) {
				p.position = currentConstruct.position + p.position;
				p.cost = currentConstruct.cost + p.cost;
				if (p.cost < costToPositionCache[p.position]) {
					costToPositionCache[p.position] = p.cost;
					queue.add(p);
				}
			}
		}

		System.out.println(numPolls);
		return minCost == Integer.MAX_VALUE ? -1 : minCost;
	}
}