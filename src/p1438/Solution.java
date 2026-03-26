package p1438;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

	public int longestSubarray(int[] nums, int limit) {
		int n = nums.length;

		Deque<Integer> maxDeque = new ArrayDeque<>();
		Deque<Integer> minDeque = new ArrayDeque<>();

		int result = 0, aux = 0;
		int l = 0;
		for (int r = 0; r < n; r++) {
			// Expand window to the right
			while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[r]) {
				maxDeque.pollLast();
			}
			maxDeque.addLast(r);
			while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[r]) {
				minDeque.pollLast();
			}
			minDeque.addLast(r);

			while (l <= r && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
				// Shrink window from the left
				if (!maxDeque.isEmpty() && maxDeque.peekFirst() == l) {
					maxDeque.pollFirst();
				}
				if (!minDeque.isEmpty() && minDeque.peekFirst() == l) {
					minDeque.pollFirst();
				}
				l++;
			}

			aux = r - l + 1;
			if (result < aux) {
				result = aux;
			}
		}

		return result;
	}
}