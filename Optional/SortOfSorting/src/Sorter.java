class Sorter {
//    public static boolean isGreaterThan(String str1, String str2) {
//
//    }
    public static void sortStrings(String[] arr) {
        // TODO: implement your sorting function here
        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr.length - 1; ++j) {
                String str1 = arr[j];
                String str2 = arr[j + 1];
                String newStr1, newStr2;
                if(str1.length() < 2) {
                    newStr1 = str1;
                } else {
                    newStr1 = str1.substring(0, 2);
                }
                if(str2.length() < 2) {
                    newStr2 = str2;
                } else {
                    newStr2 = str2.substring(0, 2);
                }
                if(newStr1.compareTo(newStr2) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
