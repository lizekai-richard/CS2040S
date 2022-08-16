import static org.junit.Assert.*;

import org.junit.Test;

public class OptimizationTest {

    /* Tests for Problem 1 */

    @Test
    public void testOptimization1() {
        int[] dataArray = {1, 3, 5, 7, 9, 11, 10, 8, 6, 4};
        assertEquals(Optimization.searchMax(dataArray), 11);
    }

    @Test
    public void testOptimization2() {
        int[] dataArray = {67, 65, 43, 42, 23, 17, 9, 100};
        assertEquals(Optimization.searchMax(dataArray), 100);
    }

    @Test
    public void testOptimization3() {
        int[] dataArray = {4, -100, -80, 15, 20, 25, 30};
        assertEquals(Optimization.searchMax(dataArray), 30);
    }

    @Test
    public void testOptimization4() {
        int[] dataArray = {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84,
                83};
        assertEquals(Optimization.searchMax(dataArray), 100);
    }

    @Test
    public void testOptimization5() {
        int[] dataArray = {2, 3};
        assertEquals(Optimization.searchMax(dataArray), 3);
    }

    @Test
    public void testOptimization6() {
        int[] dataArray = {3, 2};
        assertEquals(Optimization.searchMax(dataArray), 3);
    }

    @Test
    public void testOptimization7() {
        int[] dataArray = {-100, -45, -32, -21, -10, 10, 32, 25, 12, 1, 0, -900};
        assertEquals(Optimization.searchMax(dataArray), 32);
    }

    @Test
    public void testOptimization8() {
        int[] dataArray = {100, 90, 80, 70, 60, 50, 40, 1, 2, 3, 4, 5, 6, 7, 8};
        assertEquals(Optimization.searchMax(dataArray), 100);
    }

    @Test
    public void testOptimization9() {
        int[] dataArray = {6};
        assertEquals(Optimization.searchMax(dataArray), 6);
    }

}