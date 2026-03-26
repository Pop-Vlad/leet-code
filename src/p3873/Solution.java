package p3873;

import java.util.HashMap;
import java.util.Map;

class Solution {

	private int find(int p, int[] parent) {
		if (parent[p] == p) {
			return p;
		} else {
			parent[p] = find(parent[p], parent);
			return parent[p];
		}
	}

	private void union(int i, int j, int[] parent, int[] size) {
		int rootI = find(i, parent);
		int rootJ = find(j, parent);
		if (rootI != rootJ) {
			if (size[rootI] < size[rootJ]) {
				parent[rootI] = rootJ;
				size[rootJ] += size[rootI];
			} else {
				parent[rootJ] = rootI;
				size[rootI] += size[rootJ];
			}
		}
	}

	public int maxActivated(int[][] points) {
		int n = points.length;
		int[] parent = new int[n];
		int[] size = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		Map<Integer, Integer> xMap = new HashMap<>();
		Map<Integer, Integer> yMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			int x = points[i][0];
			int y = points[i][1];

			if (xMap.containsKey(x)) {
				union(i, xMap.get(x), parent, size);
			} else {
				xMap.put(x, i);
			}

			if (yMap.containsKey(y)) {
				union(i, yMap.get(y), parent, size);
			} else {
				yMap.put(y, i);
			}
		}

		int g1 = 0; // size of the largest group
		int g2 = 0; // size of the second-largest group

		for (int i = 0; i < n; i++) {
			if (parent[i] == i) {
				int g = size[i]; // size of current group
				if (g > g1) {
					g2 = g1;
					g1 = g;
				} else if (g > g2) {
					g2 = g;
				}
			}
		}

		return g1 + g2 + 1;
	}
}