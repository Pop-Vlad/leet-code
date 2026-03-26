package p3219;

import java.util.Arrays;

class Solution {

	public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
		Arrays.sort(horizontalCut);
		Arrays.sort(verticalCut);

		long cost = 0;
		int i = m - 2;
		int j = n - 2;
		int vCutMultiplier = 1;
		int hCutMultiplier = 1;

		while (i >= 0 && j >= 0) {
			if (horizontalCut[i] > verticalCut[j]) {
				// cut horizontally
				cost += (long) horizontalCut[i] * hCutMultiplier;
				vCutMultiplier++;
				i--;
			} else {
				// cut vertically
				cost += (long) verticalCut[j] * vCutMultiplier;
				hCutMultiplier++;
				j--;
			}
		}

		while (i >= 0) {
			// cut remaining horizontal cuts
			cost += (long) horizontalCut[i] * hCutMultiplier;
			i--;
		}

		while (j >= 0) {
			// cut remaining vertical cuts
			cost += (long) verticalCut[j] * vCutMultiplier;
			j--;
		}

		return cost;
	}
}