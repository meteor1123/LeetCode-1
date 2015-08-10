/*
	Move Zero With Minimum Swap
*/


public class Solution {
	public void moveZero(int[] arr) {
		int start = 0;
		int end = arr.length - 1;

	while (start < end) {
		if (arr[start] == 0 && arr[end] != 0) {
			swap(arr, start, end);
			start++;
			end--;
		} else {
			if (arr[start] != 0) {
				start++;
			}
			if (arr[end] != 0) {
				end--;
			}
		}
	}

	public void swap(int[] A, int start, int end) {
		int temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
	}
}