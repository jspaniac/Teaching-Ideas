import java.util.*;
public class IntTree {
    private IntTreeNode overallRoot;

    public IntTree(Integer[][] vals) {
        for (int i = 0; i < vals.length; i++) {
            if (vals[i].length != Math.pow(2, i)) {
                throw new IllegalArgumentException("Layer " + (i + 1) + " has the incorrect length " + 
                                                   "(" + vals[i].length + " instead of " + (int)(Math.pow(2, i)) + ")");
            }
            overallRoot = constructLayer(overallRoot, vals[i], 0, vals[i].length);
        }
    }

    private IntTreeNode constructLayer(IntTreeNode root, Integer[] vals, int i, int j) {
        if (j - i == 1 && vals[i] != null) {
            return new IntTreeNode(vals[i], null, null);
        } else if (root == null) {
            return null;
        }
        int midpoint = i + ((j - i) / 2);
        root.left = constructLayer(root.left, vals, i, midpoint);
        root.right = constructLayer(root.right, vals, midpoint, j);
        return root;
    }

    public String toString() {
        return toString(overallRoot, "");
    }

    private String toString(IntTreeNode root, String indent) {
        if (root == null) {
            return "";
        } else {
            String ret = "";
            if (root.right != null) {
                ret += toString(root.right, indent + "   ") + "\n";
            }
            ret += indent + root.data;
            if (root.left != null) {
                ret += "\n" + toString(root.left, indent + "   ");
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        IntTree tree = new IntTree(new Integer[][]{{1},
                                                   {2, 3},
                                                   {4, null, 5, 6},
                                                   {null, 7, null, null, 8, null, 9, 10},
                                                   {null, null, 11, null, null, null, null, null, null, null, null, null, 12, 12, null, null}});
        System.out.println(tree);
    }

    private static class IntTreeNode {
        public int data;
        public IntTreeNode left;
        public IntTreeNode right;

        public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}