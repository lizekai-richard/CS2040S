import static org.junit.Assert.*;

public class WiFiTest {

    @org.junit.Test
    public void computeDistance1() {
        int[] houses = {3, 1, 10};
        int numAccessPoints = 2;
        assertEquals(1.0, WiFi.computeDistance(houses, numAccessPoints), 0.5);
    }

    @org.junit.Test
    public void computeDistance2() {
        int[] houses = {1, 3, 5, 8, 10};
        int numAccessPoints = 3;
        assertEquals(1.0, WiFi.computeDistance(houses, numAccessPoints), 0.5);
    }

    @org.junit.Test
    public void computeDistance3() {
        int[] houses = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numAccessPoints = 4;
        assertEquals(1.0, WiFi.computeDistance(houses, numAccessPoints), 0.5);
    }

    @org.junit.Test
    public void computeDistance4() {
        int[] houses = {15, 17, 17, 24, 37, 42, 43, 45, 62, 76, 81, 90, 90, 91, 96};
        int numAccessPoints = 1;
        assertEquals(40.0, WiFi.computeDistance(houses, numAccessPoints), 0.5);
    }

    @org.junit.Test
    public void computeDistance5() {
        int[] houses = {7, 10, 13, 19, 21, 26, 27, 38, 47, 56, 61, 68, 85, 90, 91};
        int numAccessPoints = 5;
        assertEquals(6.0, WiFi.computeDistance(houses, numAccessPoints), 0.5);
    }

    @org.junit.Test
    public void coverable1() {
        int[] houses = {1, 3, 10};
        int numAccessPoints = 2;
        assertTrue(WiFi.coverable(houses, numAccessPoints, 1.0));
    }

    @org.junit.Test
    public void coverable2() {
        int[] houses = {1, 3, 10};
        int numAccessPoints = 2;
        assertFalse(WiFi.coverable(houses, numAccessPoints, 0.5));
    }

    @org.junit.Test
    public void coverable3() {
        int[] houses = {1, 3, 5, 8, 10};
        int numAccessPoints = 3;
        assertTrue(WiFi.coverable(houses, numAccessPoints, 2.5));
    }

    @org.junit.Test
    public void coverable4() {
        int[] houses = {1, 3, 7, 10, 14, 20, 45};
        int numAccessPoints = 5;
        assertTrue(WiFi.coverable(houses, numAccessPoints, 5.0));
    }

    @org.junit.Test
    public void coverable5() {
        int[] houses = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numAccessPoints = 3;
        assertFalse(WiFi.coverable(houses, numAccessPoints, 1.0));
    }

    @org.junit.Test
    public void coverable6() {
        int[] houses = {7, 10, 13, 19, 21, 26, 27, 38, 47, 56, 61, 68, 85, 90, 91};
        int numAccessPoints = 5;
        assertTrue(WiFi.coverable(houses, numAccessPoints, 6.5));
    }
}