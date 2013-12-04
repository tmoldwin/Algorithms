import java.util.ArrayList;

/**
 * @author Toviah
 * An implementation of the classic Quicksort.
 */
public class Quicksort {

	public static ArrayList<Integer> sort(ArrayList<Integer> array) {
	

		if (array.size() <= 1) {
			return array;
		}
		
		int pivotIndex = array.size() - 1;
		int pivot = array.get(pivotIndex);
		
		ArrayList<Integer> before = new ArrayList<Integer>();
		ArrayList<Integer> after = new ArrayList<Integer>();

		
		for (int i = 0; i < array.size() - 1; i++) { // move items around pivot,
														// go up to pivot
			int value = array.get(i);
			if (value <= pivot) {
				before.add(value);
			} else {
				after.add(value);
			}

		}

		ArrayList<Integer> sortedBefore = sort(before);
		ArrayList<Integer> sortedAfter = sort(after);

		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : sortedBefore) {
			result.add(i);
		}
		result.add(pivot);
		for (Integer i : sortedAfter) {
			result.add(i);
		}

		return result;

	}
	
	public static void main(String[] args){
		ArrayList<Integer> myArray = new ArrayList<Integer>();
		myArray.add(3);
		myArray.add(1);
		myArray.add(5);
		myArray.add(4);
		myArray.add(6);
		myArray.add(2);
		myArray.add(3);
		myArray.add(8);
		myArray.add(10);
		myArray.add(7);
		
		ArrayList<Integer> result = sort(myArray);
		for(Integer i:result){
			System.out.println(i+" ");
		}
	}
}
