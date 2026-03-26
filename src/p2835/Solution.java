package p2835;

import java.util.ArrayList;
import java.util.List;

class Solution {

	int[] bitCount = new int[32];

	public int minOperations(List<Integer> nums, int target) {

		// Check if it is possible to obtain the subsequence
		long sum = 0;
		for (int num : nums) {
			sum += num;
			bitCount[Integer.numberOfTrailingZeros(num)]++;
		}
		if (sum < target) {
			return -1;
		}

		int numOp = 0;
		int accumulator = 0;

		for (int i = 0; i < 32; i++) {
			int targetBit = target % 2;
			int lastBitPosition = Integer.numberOfTrailingZeros(target);

			target = target >>> 1;
		}

		return numOp;
	}
}

/*
 class Solution {
	public int minOperations(List<Integer> nums, int target) {

		// Check if it is possible to obtain the subsequence
		long sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum < target) {
			return -1;
		}

		// Find exponents of 2 in the sequence and target
		List<Integer> sequenceExponents = exponents(nums);
		List<Integer> targetExponents = decompose(target);

		int seqIdx = 0;
		int targetIdx = 0;
		int numOp = 0;
		int accumulator = 0;

		while (targetIdx < targetExponents.size()) {
			if (targetExponents.get(targetIdx).intValue() == sequenceExponents.get(seqIdx)) {
				seqIdx++;
				targetIdx++;
				continue;
			}

			if (targetExponents.get(targetIdx) > sequenceExponents.get(seqIdx)) {
				accumulator += 1 << sequenceExponents.get(seqIdx);
				if (accumulator >= 1 << targetExponents.get(targetIdx)) {
					accumulator -= 1 << targetExponents.get(targetIdx);
					targetIdx++;
				}
				seqIdx++;
				continue;
			}

			if (targetExponents.get(targetIdx) < sequenceExponents.get(seqIdx)) {
				// Perform operation
				int oldValue = sequenceExponents.get(seqIdx);
				sequenceExponents.set(seqIdx, oldValue - 1);
				sequenceExponents.add(seqIdx, oldValue - 1);
				numOp++;
			}
		}

		return numOp;
	}

	public List<Integer> exponents(List<Integer> nums) {
		List<Integer> exponents = new ArrayList<>();
		for (int num : nums) {
			int e = 0;
			while (num > 1) {
				num = num >> 1;
				e++;
			}
			exponents.add(e);
		}
		exponents.sort(Integer::compareTo);
		return exponents;
	}

	public List<Integer> decompose(int target) {
		List<Integer> exponents = new ArrayList<>();
		int e = 0;
		while (target > 0) {
			if (target % 2 == 1) {
				exponents.add(e);
			}
			target = target >> 1;
			e++;
		}
		return exponents;
	}
}*/