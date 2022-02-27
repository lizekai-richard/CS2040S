import java.util.Arrays;

class InversionCounter {

    public static long countSwaps(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        } else {
            int mid = arr.length / 2;
            int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
            int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);

            long leftInversions = countSwaps(leftArr);
            for(int i = 0; i < mid; ++i) {
                arr[i] = leftArr[i];
            }

            long rightInversions = countSwaps(rightArr);
            for(int i = mid; i < arr.length; ++i) {
                arr[i] = rightArr[i - mid];
            }

            long curInversions = mergeAndCount(arr, 0, mid - 1, mid, arr.length - 1);
            return  leftInversions + rightInversions + curInversions;
        }
    }

    /**
     * Given an input array so that arr[left1] to arr[right1] is sorted and arr[left2] to arr[right2] is sorted
     * (also left2 = right1 + 1), merges the two so that arr[left1] to arr[right2] is sorted, and returns the
     * minimum amount of adjacent swaps needed to do so.
     */
    public static long mergeAndCount(int[] arr, int left1, int right1, int left2, int right2) {
        long cnt = 0;
        int[] temp = new int[right2 - left1 + 1];
        int tempLen = 0;
        int lPointer = left1, rPointer = left2;
        while(lPointer <= right1 && rPointer <= right2) {
            if(arr[lPointer] <= arr[rPointer]) {
                temp[tempLen] = arr[lPointer];
                tempLen++;
                lPointer++;
            } else {
                cnt += (right1 - lPointer + 1);
                temp[tempLen] = arr[rPointer];
                tempLen++;
                rPointer++;
            }
        }
        while(lPointer <= right1) {
            temp[tempLen] = arr[lPointer];
            lPointer++;
            tempLen++;
        }
        while(rPointer <= right2) {
            temp[tempLen] = arr[rPointer];
            rPointer++;
            tempLen++;
        }
        for(int i = left1, j = 0; i <= right2 && j < tempLen; ++i, ++j) {
            arr[i] = temp[j];
        }
        return cnt;
    }
}
