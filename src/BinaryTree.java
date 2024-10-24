public class BinaryTree<T> /*implements DataStructure<T>*/{
    private Node<T> rootNode = null;

    private class Node<T> {
        T data;
        Node<T> parent;
        Node<T> left;
        Node<T> right;
        int subtreeSize = 0;
        public Node(T data) {
            this.data = data;
        }

        void addChild(Node<T> child) {
            if(left == null) {
                left = child;
            }
            else if (right == null) {
                right = child;
            }
            else if(left.subtreeSize <= right.subtreeSize) {
                left.addChild(child);
            }
            else right.addChild(child);
            increaseSize();
        }

        private void increaseSize() {
            subtreeSize++;
            if(parent != null) parent.increaseSize();
        }

        public String toString(String indent) {
            String s = new String();
            s += data.toString();
            if(left != null) s += "\n" + indent +left.toString(indent + "-");
            if(right != null) s += "\n" + indent + right.toString(indent + "-");
            return s;
        }
    }

    /**
     * create a new Node with the data and add it to the tree
     * @param data
     */

    public void pushItem(T data) {
        Node<T> newNode = new Node(data);
        if (rootNode == null) {
            rootNode = newNode;
            return;
        }
        rootNode.addChild(newNode);
    }

    @Override
    public String toString() {
        return rootNode.toString("-");
    }
}
