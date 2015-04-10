package com.alg1;

public class moveZero {
	

	//swap and the part of non-zero is  ordered
	public void moveZeroEnd (int[] arr) {
		int len = arr.length;
		for (int i = 0; int j = 0; j < len; j++) {
			if (arr[j] != 0) {
				if (i < j) {
					swap(arr, i, j);
				}
				i++
			}
		}
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//non-swap
	public void moveZeroEnd (int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				//count is used to record the amount of non-zero number
				arr[count++] = arr[i];
			}
		}
		while (count < arr.length) {
			arr[count++] = 0;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		moveZero test = new moveZero();
		int arr[] = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9};
		for (int i : arr) {
			System.out.print(i);
		}
		System.out.println("");
		test.moveZeroEnd(arr);
		for (int i : arr) {
			System.out.print(i);
		}
	}

}
