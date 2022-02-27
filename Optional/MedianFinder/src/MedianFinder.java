import java.util.Collections;
import java.util.PriorityQueue;


public class MedianFinder {

    // TODO: Include your data structures here
    PriorityQueue<Integer> lowQ = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> highQ = new PriorityQueue<>();

    public MedianFinder() {
        // TODO: Construct/Initialise your data structures here
    }

    public void balance() {
        while (lowQ.size() > highQ.size() + 1) {
            int e = lowQ.poll();
            highQ.add(e);
        }
        while (highQ.size() > lowQ.size() + 1) {
            int e = highQ.poll();
            lowQ.add(e);
        }
    }

    public void insert(int x) {
        // TODO: Implement your insertion operation here
        if (lowQ.size() == 0 || x <= lowQ.peek()) {
            lowQ.add(x);
        } else if (highQ.size() == 0 || x >= highQ.peek()) {
            highQ.add(x);
        } else {
            lowQ.add(x);
        }
        balance();
    }

    public int getMedian() {
        // TODO: Implement your getMedian operation here
        int e;
        if (lowQ.size() <= highQ.size()) {
            e = highQ.poll();
        } else {
            e = lowQ.poll();
        }
        balance();
        return e;
    }

//    public static void main(String[] args) {
//        MedianFinder finder = new MedianFinder();
//
//        finder.insert(5);
//        finder.insert(7);
//        finder.insert(2);
//        System.out.println("Median: " + finder.getMedian());
//        finder.insert(3);
//        finder.insert(9);
//        System.out.println("Median: " + finder.getMedian());
//        finder.insert(8);
//        finder.insert(4);
//        finder.insert(6);
//        System.out.println("Median: " + finder.getMedian());
//        finder.insert(1);
//        System.out.println("Median: " + finder.getMedian());
//        finder.insert(10);
//        finder.insert(1);
//        System.out.println("Median: " + finder.getMedian());
//    }

}
