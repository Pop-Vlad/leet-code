package p3213;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Solution solution = new Solution();
		BufferedReader br = new BufferedReader(new FileReader("/home/vlad/IntellijProjects/LeetCode/src/p3213/input.txt"));
		List<String> lines = br.lines().collect(Collectors.toList());
		String target = lines.get(0).replace("\"", "");
		List<String> wordsList = Arrays.stream(lines.get(1).split(","))
				.map(w -> w.replace("\"", ""))
				.map(String::strip)
				.collect(Collectors.toList());
		String[] words = new String[wordsList.size()];
		wordsList.toArray(words);
		List<Integer> costsList = Arrays.stream(lines.get(2).split(","))
				.map(String::strip)
				.map(Integer::valueOf)
				.collect(Collectors.toList());
		int[] costs = costsList.stream()
				.mapToInt(i -> i)
				.toArray();
		System.out.println(solution.minimumCost(target, words, costs));
		//System.out.println(solution.minimumCost("abcdef", new String[]{"abdef","abc","d","def","ef"}, new int[]{100,1,1,10,5}));
	}
}
