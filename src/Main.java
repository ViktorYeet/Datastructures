public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> mybintree= new BinaryTree<Integer>();
        Integer[] values = new Integer[20];

        for(Integer value : values) {
            mybintree.pushItem(value);
        }
        System.out.println(mybintree);
    }
}