import static org.junit.Assert.*;

public class SorterTest {

    @org.junit.Test
    public void sortStringsValidityTest1() {
        String[] arr = {"cc", "aa", "bb", "ad", "de", "dc"};
        String[] expectedArr = {"aa", "ad", "bb", "cc", "dc", "de"};
        Sorter.sortStrings(arr);
        assertArrayEquals(expectedArr, arr);
    }
    @org.junit.Test
    public void sortStringsValidityTest2() {
        String[] arr = {"Mozart", "Beethoven", "Bach"};
        String[] expectedArr = {"Bach", "Beethoven", "Mozart"};
        Sorter.sortStrings(arr);
        assertArrayEquals( expectedArr, arr);

    }

    @org.junit.Test
    public void sortStringsValidityTest3() {
        String[] arr = {"Hilbert", "Godel", "Poincare", "Ramanujan", "Pochhammmer"};
        String[] expectedArr = {"Godel", "Hilbert", "Poincare", "Pochhammmer", "Ramanujan"};
        Sorter.sortStrings(arr);
        assertArrayEquals(expectedArr, arr);
    }


    @org.junit.Test
    public void sortStringsStabilityTest() {
        // using the third character as a marker for relative position amongst duplicates since
        // problem description only sorts based on the first two characters
        String[] arr = { "ad1", "de1", "cc1", "aa1", "bb1", "ad2", "cc2", "de2", "dc1"};
        String[] expectedArr =  {"aa1", "ad1", "ad2", "bb1", "cc1", "cc2", "dc1", "de1", "de2"};
        Sorter.sortStrings(arr);
        assertArrayEquals(expectedArr, arr);
    }

    @org.junit.Test
    public void sortStringsValidityTest4() {
        // using the third character as a marker for relative position amongst duplicates since
        // problem description only sorts based on the first two characters
        String[] arr = { "CNN", "LSTM", "BERT", "TextCNN", "BiLSTM", "Transformer", "Elmo", "CharCNN", "Word2Vec"};
        String[] expectedArr =  {"BERT", "BiLSTM", "CNN", "CharCNN", "Elmo", "LSTM", "TextCNN", "Transformer", "Word2Vec"};
        Sorter.sortStrings(arr);
        assertArrayEquals(expectedArr, arr);
    }

    @org.junit.Test
    public void sortStringsValidityTest5() {
        // using the third character as a marker for relative position amongst duplicates since
        // problem description only sorts based on the first two characters
        String[] arr = { "Richard", "Jack", "Mary", "John", "Johnson", "Tony", "Emma", "Ross", "Monica", "Christina"};
        String[] expectedArr =  {"Christina", "Emma", "Jack", "John", "Johnson", "Mary", "Monica", "Richard", "Ross", "Tony"};
        Sorter.sortStrings(arr);
        assertArrayEquals(expectedArr, arr);
    }

    @org.junit.Test
    public void sortStringsValidityTest6() {
        // using the third character as a marker for relative position amongst duplicates since
        // problem description only sorts based on the first two characters
        String[] arr = { "a", "c", "bb", "d", "ef", "ae", "c", "bd", "da", "ce"};
        String[] expectedArr =  {"a", "ae", "bb", "bd", "c", "c", "ce", "d", "da", "ef"};
        Sorter.sortStrings(arr);
        assertArrayEquals(expectedArr, arr);
    }

}