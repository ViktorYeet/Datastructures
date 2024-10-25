public class BinarySearchTree<K extends Comparable<K>, T> implements DataStructure<K, T> {
    private Node root;

    private class Node {
        private K key;
        private T data;
        private Node left;
        private Node right;

        public Node(K key, T data) {
            this.key = key;
            this.data = data;
        }

        void addChild(Node child) {
            int cmp = child.key.compareTo(this.key);
            if (cmp == 0) { // if the key is the same overwrite with the new value
                this.data = child.data;
            }
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
            String s = data.toString();
            if(left != null) s += "\n" + indent +left.toString(indent + "-");
            if(right != null) s += "\n" + indent + right.toString(indent + "-");
            return s;
        }

        void printRecursiveNode(String indent) {
            System.out.println(indent + this.key.toString());
            if(left != null) left.printRecursiveNode(indent+ "l.");
            if(right != null) right.printRecursiveNode(indent + "r.");
        }

        
    }
    public BinarySearchTree() {}

    public void pushItem(K key, T data) {
        Node newNode = new Node(key, data);
        if (root == null) {
            root = newNode;
        }
        else root.addChild(newNode);
    }

    public T getItem(K key) {
        Node current = root;
        do {
            int cmp = key.compareTo(current.key);
            if(cmp < 0) current = current.left;
            else if(cmp > 0) current = current.right;
            else return current.data; // cmp == 0
        } while(current != null);
        return null;
    }

    @Override
    public String toString() {
        return root.toString("-");
    }

    public void printTree() {
        root.printRecursiveNode("");
    }
}
