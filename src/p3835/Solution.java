package p3835;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

	public long countSubarrays(int[] nums, long k) {
		int n = nums.length;
		long count = 0;

		Deque<Integer> maxDeque = new ArrayDeque<>();
		Deque<Integer> minDeque = new ArrayDeque<>();

		int l = 0;
		for (int r = 0; r < n; r++) {
			// expand window to the right
			while (!maxDeque.isEmpty() && nums[maxDeque.getLast()] < nums[r]) {
				maxDeque.removeLast(); // remove elems smaller than new elem
			}
			maxDeque.addLast(r);
			while (!minDeque.isEmpty() && nums[minDeque.getLast()] > nums[r]) {
				minDeque.removeLast(); // remove elems greater than new elem
			}
			minDeque.addLast(r);

			while (((long) nums[maxDeque.getFirst()] - nums[minDeque.getFirst()]) * (r - l + 1) > k) {
				// shrink window from the left
				l++;
				if (!maxDeque.isEmpty() && maxDeque.getFirst() < l) {
					maxDeque.removeFirst(); // remove max elem when in goes outside window
				}
				if (!minDeque.isEmpty() && minDeque.getFirst() < l) {
					minDeque.removeFirst(); // remove max elem when in goes outside window
				}
			}

			count += r - l + 1;
		}

		return count;
	}
}