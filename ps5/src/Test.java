public class Test {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        s.append('a');
        s.append('b');
        s.append('c');
        System.out.println(s);
        s.deleteCharAt(s.length() - 1);
        System.out.println(s);
    }
}
