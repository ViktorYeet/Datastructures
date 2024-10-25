import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        TwoThreeTree<Integer, String> mybintree = new TwoThreeTree<Integer, String>();
        String[] testdata = {"Hello", "world", ",", "what", "a", "wonderful", "world", "it", "is!"}; // 9 things
        //Collections.shuffle(Arrays.asList(testdata));

        for(int i = 0; i < testdata.length; i++) {
            mybintree.pushItem(i, testdata[i]);
        }

        System.out.println(mybintree.getItem(5));
        System.out.println(mybintree.toString());
    }
}