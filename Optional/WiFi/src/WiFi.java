import java.util.Arrays;

class WiFi {
    private final static double eps = 0.01;


    /**
     * Implement your solution here
     */
//    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {
//        int cnt = 1;
//        for(int i = 0; i < houses.length; ++i) {
//            for(int j = i + 1; j < houses.length; ++j) {
//                if(houses[j] - houses[i] > 2 * distance) {
//                    i = j - 1;
//                    cnt++;
//                    break;
//                }
//            }
//        }
//        return cnt <= numOfAccessPoints;
//    }
//
//    /**
//     * Implement your solution here
//     */
//    public static double computeDistance(int[] houses, int numOfAccessPoints) {
//        if(houses.length == 0 || houses.length == 1) {
//            return 0.0;
//        }
//        Arrays.sort(houses);
//        double l = 0;
//        double r = houses[houses.length - 1];
//        while(r - l >= eps) {
//            double mid = (l + r) / 2;
//            if(coverable(houses, numOfAccessPoints, mid)) {
//                r = mid;
//            } else {
//                l = mid;
//            }
//        }
//        return r;
//    }



    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {
        Arrays.sort(houses);
        int cnt = 1;
        double cur = houses[0] + distance;
        for (int i = 1;i < houses.length;i++) {
            if (houses[i] - cur > distance) {
                cnt++;
                cur = houses[i] + distance;
            }
        }
        return cnt <= numOfAccessPoints;
    }
    public static double computeDistance(int[] houses, int numOfAccessPoints) {
        Arrays.sort(houses);
        double l = 0;
        double r = houses[houses.length- 1];
        double ans = 0;
        while (r - l > 0.5) {
            double mid = (l + r) / 2;
            //System.out.println(mid);
            if (coverable(houses, numOfAccessPoints, mid)) {
                r = mid;
                ans = mid;
            }
            else {
                l = mid;
            }
        }
        return ans;
    }

//    public static int[]  generateArray(int len,int max){
//        int[] arr = new int[len];
//        int[] used = new int[1000];
//        for(int i = 0; i < arr.length ; i++) {
//            int element = (int) (Math.random() * max) + 1;
//            if(used[element] != 1) {
//                arr[i] = element;
//                used[element] = 1;
//            }
//        }
//        return arr;
//    }

//    public static void main(String[] args) {
//        for(int i = 0; i < 10000; ++i) {
//            int[] houses = generateArray(100, 500);
//            int numOfAccessPoints = (int) (Math.random() * 50) + 1;
//            double ans1 = computeDistance(houses, numOfAccessPoints);
//            double ans2 = computeDistance1(houses, numOfAccessPoints);
//
//            if(Math.abs(ans1 - ans2) > 0.5) {
//                System.out.println(ans1);
//                System.out.println(ans2);
//                for(int house : houses) {
//                    System.out.print(house + " ");
//                }
//                System.out.println();
//                System.out.println(numOfAccessPoints);
//                System.out.println();
//            }
//        }
//    }


}
