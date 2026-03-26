package p1381;

class CustomStack {

	private int[] data;
	private int size;

	public CustomStack(int maxSize) {
		data = new int[maxSize];
	}

	public void push(int x) {
		if (size < data.length) {
			data[size] = x;
			size++;
		}
	}

	public int pop() {
		if (size > 0) {
			size--;
			return data[size];
		} else {
			return -1;
		}
	}

	public void increment(int k, int val) {
		int endPosition = Math.min(k, size);
		for (int i = 0; i < endPosition; i++) {
			data[i] += val;
		}
	}
}