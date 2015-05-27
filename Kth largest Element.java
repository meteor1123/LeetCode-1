/*
	Find K-th largest element in an array.

	Note
	You can swap elements in the array

	Example
	In array [9,3,2,4,8], the 3rd largest element is 4

	In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

	Challenge
	O(n) time, O(1) space
*/

public class Solution {
	//param k : description of k
	//param numbers : array of numbers
	//return: description of return
	public int kthLargestElement(int k, ArrayList<Integer> numbers) {
		if (k > numbers.size()) {
			return 0;
		}
		return helper(numbers.size() - k + 1, numbers, 0, numbers.size() - 1);
	}

	public int helper(int k, ArrayList<Integer> numbers, int start, int end) {
		int l = start;
		int r = end;
		int pivot = end;
		while (true) {
			//双路快排
			while (numbers.get(l) < numbers.get(pivot) && l < r) {
				l++;
			}
			while (numbers.get(r) >= numbers.get(pivot) && l < r) {
				r--;
			}
			if (l == r) {
				break;
			}
			swap(numbers, l, r);
		}
		swap(numbers, l, end); // l here is the first one which is bigger than pivot, swap it with the pivot
		if (l + 1 == k) {// Why l ?since i is the index of array, if we want to check the ith position, we need to plus 1
			return numbers.get(l);
		//if l + 1 < k , then we make the left side move to right
		} else if (l + 1 < k) {
			return helper(k, numbers, l + 1, end);
		//if l + 1 > k ,then we make the right side move to left 
		} else return helper(k, numbers, start , l - 1);
	}

	public void swap(ArrayList<Integer> numbers, int l, int r) {
		int temp = numbers.get(l);
		numbers.set(l, numbers.get(r).intValue());
		numbers.set(r, temp);
	}

	/*
	*  Array
	*
	*
	*/
	public int kthLargestElement(int k , int[] arr) {
		if (k > arr.length) {
			return -1;
		}
		return helper(arr.length - k + 1, arr, 0, arr.length - 1);
		//find the Kth larest which means find len - k small 
		//due to the arr index is from 0 to 9, so need to plus 1;
	}

	public int helper(int k, int[] arr, int start, int end) {
		int l = start;
		int r = end;
		int pivot = end;
		while (true) {
			while (l < r && arr[l] < arr[pivot]) {
				l++;
			}
			while (l < r && arr[r] >= arr[pivot]) {
				r--;
			}
			if (l == k) {
				break;
			}
			swap(arr, l, r);
		}
		swap(arr, l , end);
		if (l + 1 == k) {
			return arr[l];
		} else if (l + 1 < k) {
			helper(k, arr, l + 1, end);
		} else {
			helper(k, arr, start, l - 1);
		}
	}
	public void swap(int[] arr, int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}
}