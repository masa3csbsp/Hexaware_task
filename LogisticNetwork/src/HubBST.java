
public class HubBST {

    // Node structure
    class Node {
        String hubCode;
        Node left, right;

        Node(String code) {
            hubCode = code;
            left = right = null;
        }
    }

    Node root;

    // Insert a hub into BST
    public Node insert(Node root, String code) {

        if (root == null) {
            return new Node(code);
        }

        if (code.compareToIgnoreCase(root.hubCode) < 0) {
            root.left = insert(root.left, code);
        }
        else if (code.compareToIgnoreCase(root.hubCode) > 0) {
            root.right = insert(root.right, code);
        }

        return root;
    }

    // Search for a hub
    public boolean search(Node root, String code) {

        if (root == null) {
            return false;
        }

        int comparison = code.compareToIgnoreCase(root.hubCode);

        if (comparison == 0) {
            return true;
        }
        else if (comparison < 0) {
            return search(root.left, code);
        }
        else {
            return search(root.right, code);
        }
    }

    // Calculate tree height (in edges)
    public int height(Node root) {

        if (root == null) {
            return -1;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Inorder traversal
    public void inorder(Node root) {

        if (root != null) {
            inorder(root.left);
            System.out.print(root.hubCode + " ");
            inorder(root.right);
        }
    }

    // Get root node
    public Node getRoot() {
        return root;
    }

    // Insert using the root
    public void addHub(String code) {
        root = insert(root, code);
    }
}
