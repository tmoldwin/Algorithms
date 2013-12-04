import java.util.Arrays;

/**
 * @author Toviah
 * A classic merge sort implementation
 */
public class MergeSort {
	public static int[] merge(int[] left, int[] right) {
		int[] newArray = new int[left.length + right.length];
		int leftIndex = 0;
		int rightIndex = 0;
		int newIndex = 0;
		while (leftIndex < left.length || rightIndex < right.length) {
			if ((rightIndex == right.length)
					|| ((leftIndex < left.length) && (left[leftIndex] < right[rightIndex]))) {
				newArray[newIndex] = left[leftIndex];
				leftIndex++;
			} else if ((leftIndex == left.length)
					|| (rightIndex < right.length && (right[rightIndex] < left[leftIndex]))) {
				newArray[newIndex] = right[rightIndex];
				rightIndex++;
			}
			newIndex++;
		}
		return newArray;
	}

	public static int[] mergeSort(int[] input) {
		if (input.length == 1) {
			return input;
		} else {
			int midpoint = input.length / 2;
			int[] left = Arrays.copyOfRange(input, 0, midpoint);
			int[] right = Arrays.copyOfRange(input, midpoint, input.length);
			left = mergeSort(left);
			right = mergeSort(right);

			return merge(left, right);
		}
	}

	public static void main(String[] args) {
		int[] a = mergeSort(new int[] { 38, 27, 43, 3, 9, 82, 10 });
		// int[] a = mergeSort(new int[] {3, 9, 82, 10 });
		System.out.println(Arrays.toString(a));
	}
}
