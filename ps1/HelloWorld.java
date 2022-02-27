public class HelloWorld {
    public static String getMyName() {
        return "ZeKai";
    }
    public static String getFavoriteAlgorithm() {
        return "Support Vector Machine";
    }
    public static String getAdditionalIntroduction() {
        return "Hi, I'm Li ZeKai from China. Hope to see you soon!";
    }
    public static String getAnswer1A() {
        return "3125";
    }
    public static String getAnswer1B() {
        return "argA to the power of argB";
    }
    public static void main(String[] args) {
        String myName = getMyName();
        String favoriteAlgorithm = getFavoriteAlgorithm();
        String additionalIntroduction = getAdditionalIntroduction();
        String answer1A = getAnswer1A();
        String answer1B = getAnswer1B();
        System.out.println("My name is " + myName);
        System.out.println("My favorite algorithm is " + favoriteAlgorithm);
        System.out.println(additionalIntroduction);
        System.out.println("Answer for Problem 1.a: " + answer1A);
        System.out.println("Answer for Problem 1.b: " + answer1B);
    }
}
