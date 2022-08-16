public class TreeNode {
    private final int key;
    private final int value;

    private final TreeNode leftTree;
    private final TreeNode rightTree;

    private final TreeNode parent;

    public TreeNode root = null;

    public TreeNode(int key ,int value) {
        this.key = key;
        this.value = value;
        this.leftTree = null;
        this.rightTree = null;
        this.parent = null;
    }


    public void insert(TreeNode parentNode, TreeNode newNode) {
        if(newNode.key < this.key) {
            if(this.leftTree != null) {
                this.leftTree.insert(this, newNode);
            }
        } else if(newNode.key > this.key){
            if(this.rightTree != null) {
                this.rightTree.insert(this, newNode);
            }
        } else {
            System.out.println("Node already exists!");
        }
    }

    public TreeNode search(int key) {
        if(this.key < key) {
            if(this.rightTree != null) {
                return rightTree.search(key);
            } else {
                return null;
            }
        } else if(this.key > key){
            if(this.leftTree != null) {
                return leftTree.search(key);
            } else {
                return null;
            }
        }
        return this;
    }

    public int size() {
        int leftSize = 0;
        int rightSize = 0;
        if(leftTree != null) {
            leftSize = leftTree.size();
        }
        if(rightTree != null) {
            rightSize = rightTree.size();
        }
        return leftSize + rightSize + 1;
    }

    public boolean contains(int key) {
        boolean leftContain = false;
        boolean rightContain = false;
        if(key < this.key) {
            if (this.leftTree != null) {
                leftContain = leftTree.contains(key);
            }
        }  else if(key > this.key) {
            if (this.rightTree != null) {
                rightContain = rightTree.contains(key);
            }
        } else {
            return true;
        }
        return leftContain || rightContain;
    }

    public TreeNode findMin() {
        if(this.leftTree != null) {
            return this.leftTree.findMin();
        }
        return this;
    }

    public TreeNode successor() {
        if(this.rightTree != null) {
            return rightTree.findMin();
        }

        TreeNode parent = this.parent;
        TreeNode child = this;

        while((parent != null) && (child == parent.rightTree)) {
            child = parent;
            parent = child.parent;
        }

        return parent;
    }

    public int height() {
        int leftHeight = -1;
        int rightHeight = -1;
        if(leftTree != null) {
            leftHeight = leftTree.height();
        }
        if(rightTree != null) {
            rightHeight = rightTree.height();
        }
        return leftHeight + rightHeight + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(67, 67);
        for(int i = -20; i <= 20 && i != 0; i++) {
            TreeNode newNode = new TreeNode(67 + i, 67 + i);
            root.insert(root, newNode);
        }
    }
}
