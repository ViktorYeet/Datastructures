import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> mybintree = new BinarySearchTree<Integer>();
        Integer[] values = new Integer[1000];
        Random rand = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt(values.length);
        }

        for(Integer value : values) {
            mybintree.pushItem(value);
        }
        mybintree.printTree();
    }
}