package p3864;

public class Solution {

	private long countOnes(int[] arr, int start, int end) {
		int count = 0;
		for (int i = start; i < end; i++) {
			if (arr[i] == 1) {
				count++;
			}
		}
		return count;
	}

	private long getCost(int[] arr, int l, int r, long encCost, long flatCost) {
		int L = r - l;
		if (L % 2 != 0) {
			// odd number of elements. Cannot split array
			long numOnes = countOnes(arr, l, r);
			if (numOnes == 0) {
				return flatCost;
			} else {
				return L * numOnes * encCost;
			}
		} else {
			// even number of elems
			int mid = (l + r) / 2;
			long onesLeft = countOnes(arr, l, mid);
			long onesRight = countOnes(arr, mid, r);
			if (onesLeft == 0 && onesRight == 0) {
				// arr not worth splitting
				return flatCost;
			} else if (onesLeft > 0 && onesRight > 0) {
				// arr always worth splitting
				return getCost(arr, l, mid, encCost, flatCost) + getCost(arr, mid, r, encCost, flatCost);
			} else {
				long numOnes = onesLeft + onesRight;
				long unsplitCost = L * numOnes * encCost;
				long splitCost = flatCost;
				if (onesLeft == 0) {
					splitCost += getCost(arr, mid, r, encCost, flatCost);
				} else {
					splitCost += getCost(arr, l, mid, encCost, flatCost);
				}
				return Math.min(unsplitCost, splitCost);
			}
		}
	}

	public long minCost(String s, int encCost, int flatCost) {
		int[] arr = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			arr[i] = s.charAt(i) - '0';
		}

		return getCost(arr, 0, s.length(), encCost, flatCost);
	}
}