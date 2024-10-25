public class TwoThreeTree<K extends Comparable<K>, V> implements DataStructure<K, V> {

    private Node root;

    private class Node {

        private K key, secondKey; //second key is always the higher one
        private V value, secondValue;
        private Node left, right, middle;
        private Node parent;

        boolean isTwoNode;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            isTwoNode = true;
        }

        private void addChild(Node child) {
            if (child == null) {throw new NullPointerException("child is null");}

            int cmp = child.key.compareTo(key);

            if(this.isLeaf()) {
                this.absorb(child);
                return;
            }
            if(cmp < 0) {
                this.left.addChild(child);
            }
            else if(cmp > 0) {
                this.right.addChild(child);
            }
            else {
                this.value = child.value;
            }
        }

        private void absorb(Node node) {
            if (node == null) {throw new NullPointerException("node to be absorbed cannot be null");}
            if(this.isTwoNode) {
                int cmp = node.key.compareTo(this.key);
                if(cmp < 0) { // key is always smaller than second key, but key is the default used for twoNodes
                    this.secondKey = this.key;
                    this.secondValue = this.value;
                    this.key = node.key;
                    this.value = node.value;

                    // if the node had children they will be the LEFT and MIDDLE children
                    this.left = node.left;
                    this.middle = node.right;
                }
                else if(cmp > 0) {
                    this.secondKey = node.key;
                    this.secondValue = node.value;

                    // if the node had children they will be the MIDDLE and RIGHT children
                    this.middle = node.left;
                    this.right = node.right;
                }
                //cmp == 0, overwrite the values since the keys were the same
                else this.value = node.value;

                this.isTwoNode = false;
            }
            else { // this is a threeNode we are trying to absorb into so we split instead
                split(node);
            }

        }

        /**
         *
         * @param node node yet to be added
         */
        private void split(Node node) {
            if(node == null) {throw new NullPointerException("node to be split cannot be null");}
            if(this.isTwoNode) {throw new IllegalStateException("must be a fourNode to split");}

            //figure out what node should be the new parent
            int cmp1 = node.key.compareTo(key);
            int cmp2 = node.key.compareTo(secondKey);

            if(cmp1 < 0) { // new node should be the leftmost node
                Node parent = new Node(this.key, this.value);
                parent.left = node;
                parent.right = new Node(this.secondKey, this.secondValue);

                // the rightmost node inherits this node's rightmost children, we don't care about left since that should be the new node added
                parent.right.left = this.middle;
                parent.right.right = this.right;

                node.parent = parent;
                parent.right.parent = parent;

                if(this.parent != null) this.parent.absorb(parent); //otherwise we are at the root node
                else root = parent;
            }
            else if(cmp2 > 0) { // new node should be the rightmost node
                Node parent = new Node(this.secondKey, this.secondValue);
                parent.right = node;
                parent.left = new Node(this.key, this.value);

                // the leftmost node inherits this node's leftmost children, we don't care about right since that should be the new node added
                parent.left.left = this.left;
                parent.left.right = this.middle;

                node.parent = parent;
                parent.left.parent = parent;

                if(this.parent != null) this.parent.absorb(parent); // otherwise we are at the root node
                else root = parent;
            }
            else { // new node is the node to be lifted
                Node left = new Node(this.key, this.value);
                Node right = new Node(this.secondKey, this.secondValue);

                // left inherits the left child of node as its right child, and right inherits the right child of node as its left child
                // since node was the middle before and is the treeNode to be lifted
                left.left = this.left;
                left.right = node.left;
                right.left = node.right;
                right.right = this.right;

                node.left = left;
                node.right = right;

                left.parent = node;
                right.parent = node;

                if(this.parent != null) this.parent.absorb(node);
                else root = node;
            }
        }

        private boolean isLeaf() {
            return left == null && middle == null && right == null;
        }
    }

    @Override
    public void pushItem(K key, V data) {
        Node newNode = new Node(key, data);
        if(root != null) root.addChild(newNode);
        else root = newNode;
    }

    @Override
    public V getItem(K key) {
        Node currentNode = root;
        while (currentNode != null) {
            if(currentNode.isTwoNode) {
                int cmp = key.compareTo(currentNode.key);
                if(cmp == 0) return currentNode.value;
                else if(cmp < 0) {
                    currentNode = currentNode.left;
                }
                else if(cmp > 0) {
                    currentNode = currentNode.right;
                }
            }
            else {
                int cmp1 = key.compareTo(currentNode.key);
                int cmp2 = key.compareTo(currentNode.secondKey);

                if(cmp1 == 0) return currentNode.value;
                else if(cmp2 == 0) return currentNode.secondValue;

                else if(cmp1 < 0) {
                    currentNode = currentNode.left;
                }
                else if(cmp2 > 0) {
                    currentNode = currentNode.right;
                }
                else {
                    currentNode = currentNode.middle;
                }
            }
        }
        return null;
    }
}
