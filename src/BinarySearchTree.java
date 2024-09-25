public class BinarySearchTree<T extends Comparable<T>> implements DataStructure<T> {
    private Node<T> root;

    private class Node<T extends Comparable<T>> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        void addChild(Node<T> child) {
            int cmp = child.data.compareTo(this.data);
            if (cmp == 0) return;
            else if(cmp < 0) {
                if (left != null) {
                    left.addChild(child);
                    return;
                }
                this.left = child;
            }
            else if (cmp > 0) {
                if (right != null) {
                    right.addChild(child);
                    return;
                }
                this.right = child;
            }
        }

        public String toString(String indent) {
            String s = new String();
            s += data.toString();
            if(left != null) s += "\n" + indent +left.toString(indent + "-");
            if(right != null) s += "\n" + indent + right.toString(indent + "-");
            return s;
        }

        void printRecursiveNode(String indent) {
            System.out.println(indent + this.data.toString());
            if(left != null) left.printRecursiveNode(indent+ "l.");
            if(right != null) right.printRecursiveNode(indent + "r.");
        }

        
    }
    public BinarySearchTree() {}
    public void pushItem(T data) {
        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        }
        else root.addChild(newNode);
    }

    @Override
    public String toString() {
        return root.toString("-");
    }

    public void printTree() {
        root.printRecursiveNode("");
    }
}
