public class SortingTester {
    public static boolean checkSort(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] array = new KeyValuePair[size];
        for(int i = 0; i < size; ++i) {
            int randomNumber = (int) (Math.random() * size);
            array[i] = new KeyValuePair(randomNumber, i);
        }
        sorter.sort(array);
        boolean flag = true;
        for(int i = 0; i < size - 1; ++i) {
            int curKey = array[i].getKey();
            int nextKey = array[i + 1].getKey();
            if(curKey > nextKey) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean isStable(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] array = new KeyValuePair[size];
        for(int i = 0; i < size / 2; ++i) {
            array[i] = new KeyValuePair(i, i);
            array[size - i - 1] = new KeyValuePair(i, size - i - 1);
        }
        sorter.sort(array);
        boolean flag = true;
        for(int i = 0; i < size - 1; ++i) {
            int curKey = array[i].getKey();
            int curValue = array[i].getValue();
            int nextKey = array[i + 1].getKey();
            int nextValue = array[i + 1].getValue();
            if(curKey == nextKey) {
                if(curValue > nextValue) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public static void checkBestCase(ISort sorter, int size) {
        StopWatch watch = new StopWatch();
        KeyValuePair[] array = new KeyValuePair[size];
        for(int i = 0; i < size; ++i) {
            array[i] = new KeyValuePair(i, i);
        }
        watch.start();
        sorter.sort(array);
        watch.stop();
        System.out.println("Best case time used: " + watch.getTime());
    }

    public static void checkWorstCase(ISort sorter, int size) {
        StopWatch watch = new StopWatch();
        KeyValuePair[] array = new KeyValuePair[size];
        for(int i = 0; i < size; ++i) {
            array[i] = new KeyValuePair(size - i, i);
        }
        watch.start();
        sorter.sort(array);
        watch.stop();
        System.out.println("Worst case Time used: " + watch.getTime());
    }

    public static void main(String[] args) {
        // TODO: implement this
        System.out.println("Interrogating A...");
        ISort A = new SorterA();
        System.out.println("Is a sort algorithm? : " + checkSort(A, 10000));
        System.out.println("Is stable? : " + isStable(A, 10000));
        checkBestCase(A, 10000);
        checkWorstCase(A, 10000);

        System.out.println("Interrogating B...");
        ISort B = new SorterB();
        System.out.println("Is a sort algorithm? : " + checkSort(B, 10000));
        System.out.println("Is stable? : " + isStable(B, 10000));
        checkBestCase(B, 10000);
        checkWorstCase(B, 10000);

        System.out.println("Interrogating C...");
        ISort C = new SorterC();
        System.out.println("Is a sort algorithm? : " + checkSort(C, 10000));
        System.out.println("Is stable? : " + isStable(C, 10000));
        checkBestCase(C, 10000);
        checkWorstCase(C, 10000);

        System.out.println("Interrogating D...");
        ISort D = new SorterD();
        System.out.println("Is a sort algorithm? : " + checkSort(D, 10000));
        System.out.println("Is stable? : " + isStable(D, 10000));
        checkBestCase(D, 10000);
        checkWorstCase(D, 10000);

        System.out.println("Interrogating E...");
        ISort E = new SorterE();
        System.out.println("Is a sort algorithm? : " + checkSort(E, 10000));
        System.out.println("Is stable? : " + isStable(E, 10000));
        checkBestCase(E, 10000);
        checkWorstCase(E, 10000);

        System.out.println("Interrogating F...");
        ISort F = new SorterF();
        System.out.println("Is a sort algorithm? : " + checkSort(F, 10000));
        System.out.println("Is stable? : " + isStable(F, 10000));
        checkBestCase(F, 10000);
        checkWorstCase(F, 10000);


        System.out.println("Performing additional comparison between D and E");
        checkBestCase(D, 50000);
        checkWorstCase(D, 50000);

        checkBestCase(E, 50000);
        checkWorstCase(E, 50000);

    }
}
