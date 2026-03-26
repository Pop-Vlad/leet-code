package p3878;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	public long countGoodSubarrays(int[] nums) {
		int n = nums.length;
		int[] L = new int[n];
		int[] R = new int[n];
		Arrays.fill(R, n - 1);

		int[] prevOne = new int[32]; // last index where this bit is 1
		int[] nextOne = new int[32]; // next index where this bit is 1
		Arrays.fill(prevOne, -1);
		Arrays.fill(nextOne, n);

		for (int i = 0; i < n; i++) {
			for (int bit = 0; bit < 32; bit++) {
				if ((nums[i] & (1 << bit)) == 0) {
					// current num has a 0 at location bit
					L[i] = Math.max(L[i], prevOne[bit] + 1); // Limit interval for the num such that it does not contain a 1
				} else {
					// current num has a 1 at location bit
					prevOne[bit] = i; // mark last num at which 1 was found
				}
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			for (int bit = 0; bit < 32; bit++) {
				if ((nums[i] & (1 << bit)) == 0) {
					// current num has a 0 at location bit
					R[i] = Math.min(R[i], nextOne[bit] - 1); // Limit interval for the num such that it does not contain a 1
				} else {
					// current num has a 1 at location bit
					nextOne[bit] = i; // mark next num at which 1 was found
				}
			}
		}

		Map<Integer, Integer> lastOccurrence = new HashMap<>();
		long count = 0;
		for (int i = 0; i < n; i++) {
			int l = L[i];
			int r = R[i];

			if (lastOccurrence.containsKey(nums[i])) {
				if (l <= lastOccurrence.get(nums[i])) {
					l = lastOccurrence.get(nums[i]) + 1;
				}
			}
			lastOccurrence.put(nums[i], i);

			count += (long) (i - l + 1) * (r - i + 1);
		}

		return count;
	}
}