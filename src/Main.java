public class Main {
    public static void main(String[] args) {
        TwoThreeTree<String, Integer> mybintree = new TwoThreeTree<>();
        String[] testdata = {"Hello", "world", ",", "what", "a", "wonderful", "world", "it", "is!"}; // 9 things
        //Collections.shuffle(Arrays.asList(testdata));

        for(int i = 0; i < testdata.length; i++) {
            mybintree.pushItem(testdata[i], i);
        }

        System.out.println(mybintree.getItem("wonderful"));
        System.out.println(mybintree);
    }
}