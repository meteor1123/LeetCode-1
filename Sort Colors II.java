/*
	Lintcode: Sort Colors II
*/

//Counting sort O(KN)
class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length == 0 || k <= 1) 
            return;
        int l = 0, r = colors.length-1;
        int pivot = r;
            while (true) {
                while (l < r && colors[l] < colors[pivot]) {
                    r--;
                }
                while (l < r && colors[l] == cnt) {
                    l++;
                }
                if (l == r) break;
                swap(colors, l, r);
            }
            l++;
            r = colors.length-1;
            if (l == r) break;
    }
    
    public void swap(int[] colors, int l, int r) {
        int temp = colors[l];
        colors[l] = colors[r];
        colors[r] = temp;
    }
}

//quick sort
class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors==null || colors.length==0 || k<=1) {
            return;
        }
        quickSort(colors, 0, colors.length-1);
    }
    
    public void quickSort(int[] colors, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = r;
        int pos = partition(colors, l, r, pivot);
        quickSort(colors, l, pos - 1);
        quickSort(colors, pos + 1, r);
    }
    
    public int partition(int[] colors, int start, int end, int pivot) {
        int l = start, r = end;
        while (true) {
            while (l < r && colors[l] < colors[pivot]) {
                l++;
            }
            while (l < r && colors[r] >= colors[pivot]) {
                r--;
            }
            if (l == r) break;
            swap(colors, l, r);
        }
        swap(colors, l, end);
        return l;
    }
    
    public void swap(int[] colors, int l, int r) {
        int temp = colors[l];
        colors[l] = colors[r];
        colors[r] = temp;
    }
}