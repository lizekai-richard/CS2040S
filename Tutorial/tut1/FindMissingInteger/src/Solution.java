import java.util.Arrays;

public class Solution {
    public static int findMissingNumber(int[] arr, int n) {
        Arrays.sort(arr);
        int l = 0;
        int r = arr.length - 1;
        while(l < r) {
            int midIndex = (l + r) / 2;
            int midValue = midIndex + 1;
            if(midValue == arr[midIndex]) {
                l = midIndex + 1;
            } else {
                r = midIndex;
            }
        }
        return l + 1;
    }

    public static int[] generateArray(int n) {
        int[] arr = new int[n];
        int len = 0;
        int missingNumber = (int) (Math.random() * (n - 1)) + 1;
        for(int i = 1; i <= n; ++i) {
            if(i != missingNumber) {
                arr[len++] = i;
            }
        }
        arr[n - 1] = missingNumber;
        return arr;
    }

    public static void main(String[] args) {
        for(int i = 10; i <= 10000; ++i) {
            int[] arr = generateArray(i);
            Arrays.sort(arr);
            int res = findMissingNumber(arr, 10);
            if(res != arr[i - 1]) {
                System.out.println("Error!");
            }
        }
    }
}
