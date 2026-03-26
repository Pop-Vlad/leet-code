package p239;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

	private void addBack(Deque<Integer> q, int i, int[] nums) {
		while (!q.isEmpty() && nums[q.getLast()] < nums[i]) {
			q.removeLast(); // remove elems smaller than newly added elem
		}
		q.addLast(i);
	}

	private int getMax(Deque<Integer> q, int i) {
		while (q.getFirst() < i) {
			q.removeFirst(); // remove elems no longer in window
		}
		return q.getFirst();
	}

	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length - k + 1; // nr of windows
		int[] max = new int[n];

		Deque<Integer> q = new ArrayDeque<>();
		// initialize q
		for (int i = 0; i < k; i++) {
			addBack(q, i, nums);
		}
		max[0] = nums[getMax(q, 0)];

		for (int i = 1; i < n; i++) {
			addBack(q, i + k - 1, nums); // add new number entering window
			max[i] = nums[getMax(q, i)];
		}

		return max;
	}
}