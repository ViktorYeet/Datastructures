import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> mybintree = new BinarySearchTree<Integer, String>();
        String[] testdata = {"Hello", "world", ",", "what", "a", "wonderful", "world", "it", "is!"}; // 9 things
        Integer[] testInts = new Integer[testdata.length];

        Random rand = new Random();
        for (int i = 0; i < testInts.length; i++) {
            testInts[i] = rand.nextInt(testInts.length);
        }
//        for(Integer value : values) {
//            mybintree.pushItem(value);
//        }
        for(int i = 0; i < testdata.length; i++) {
            mybintree.pushItem(testInts[i], testdata[i]);
        }
        mybintree.printTree();
        System.out.println(mybintree.toString());
    }
}