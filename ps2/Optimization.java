/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        // TODO: Implement this
        // special check for the one-element array
        if(dataArray.length == 1) {
            return dataArray[0];
        }
        // if the first element is greater than the second element, then the array goes down first and then goes up.
        // In this case, the maximum element must be either the first element or the last.
        if(dataArray[0] > dataArray[1] || dataArray.length == 2) {
            return Math.max(dataArray[0], dataArray[dataArray.length - 1]);
        }
        // otherwise, we apply binary search on the array. Let the maximum element be the pivot, the binary search
        // In this case is similar to the gradient ascending algorithm. We repeatedly jump between left and right of
        // the pivot until we reach it.
        int l = 0;
        int r = dataArray.length - 1;
        int maxElement = 0;
        while(l <= r) {
            int mid = (l + r) >> 1;
            int curElement = dataArray[mid];
            int leftElement = dataArray[mid - 1];
            int rightElement = dataArray[mid + 1];
            if(curElement > leftElement && curElement > rightElement) {
                maxElement = curElement;
                break;
            } else if(curElement > leftElement) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return maxElement;
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
