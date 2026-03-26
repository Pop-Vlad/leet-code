package p2096;

import java.util.*;

class Solution {

	public String getDirections(TreeNode root, int startValue, int destValue) {
		List<Integer> pathToStart = pathToValue(root, startValue);
		Collections.reverse(pathToStart);
		List<Integer> pathToDest = pathToValue(root, destValue);
		Collections.reverse(pathToDest);

		int commonPath = commonPathLength(pathToStart, pathToDest);
		pathToStart = pathToStart.subList(commonPath, pathToStart.size());
		pathToDest = pathToDest.subList(commonPath, pathToDest.size());
		List<Integer> path = invertPath(pathToStart);
		path.addAll(pathToDest);
		return path.stream()
				.map(i -> {
					if (i == 0) {
						return "U";
					} else if (i == 1) {
						return "L";
					} else {
						return "R";
					}
				})
				.reduce((s, t) -> s + t).orElse("");
	}

	public List<Integer> pathToValue(TreeNode currentNode, int value) {
		if (currentNode.val == value) {
			return new ArrayList<>();
		}
		if (currentNode.left != null) {
			List<Integer> path = pathToValue(currentNode.left, value);
			if (path != null) {
				path.add(1);
				return path;
			}
		}
		if (currentNode.right != null) {
			List<Integer> path = pathToValue(currentNode.right, value);
			if (path != null) {
				path.add(2);
				return path;
			}
		}
		return null;
	}

	public int commonPathLength(List<Integer> pathToStart, List<Integer> pathToDest) {
		int minLength = Math.min(pathToStart.size(), pathToDest.size());
		int pos = 0;
		while (pos < minLength) {
			if (!Objects.equals(pathToStart.get(pos), pathToDest.get(pos))) {
				break;
			}
			pos++;
		}
		return pos;
	}

	public List<Integer> invertPath(List<Integer> path) {
		return new ArrayList<>(Collections.nCopies(path.size(), 0));
	}
}