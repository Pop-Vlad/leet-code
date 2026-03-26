package p1707;


import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(Arrays.toString(solution.maximizeXor(new int[]{5, 2, 4, 6, 6, 3},
				new int[][]{{12, 4}, {8, 1}, {6, 3}})));
	}
}
