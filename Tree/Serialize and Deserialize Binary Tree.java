/*
	Serialize and Deserialize Binary Tree
	Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

	Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

	For example, you may serialize the following tree
*/





public class Codec {
	public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> q = new LinkedList<> ();
        q.offer (root) ;
        StringBuilder sb = new StringBuilder ();      
        while (!q.isEmpty()) {
            int size = q.size ();
            for (int i = 0 ; i < size ; ++i) {
                TreeNode cur = q.poll();
                if (cur != null) {
                    q.offer (cur.left);
                    q.offer (cur.right);
                }
                String val = cur == null ? "#" : String.valueOf(cur.val) ;
                sb.append (val);
                sb.append (",");
            }
        }
        sb.setLength (sb.length() - 1);
        return sb.toString();
    }
	// Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length () == 0) {
            return null ;
        }
        String [] val = data.split(",");
        TreeNode root = new TreeNode (Integer.parseInt (val[0]));
        Queue<TreeNode> q = new LinkedList<> ();
        q.offer(root) ;
        for (int i = 1 ; i < val.length ; i += 2) {
            TreeNode cur = q.poll();
            if (!"#".equals(val[i])) {
                TreeNode left = new TreeNode (Integer.parseInt (val[i]));
                cur.left = left ;
                q.offer (left);
            }
            if (i + 1 < data.length() && !"#".equals(val[i + 1])) {
                TreeNode right = new TreeNode (Integer.parseInt (val[i + 1]));
                cur.right = right ;
                q.offer (right);
            }
        }
        return root ;
    }
}




//Recursive Method, DFS, preorder
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#").append(" ");
        } else {
            sb.append(node.val).append(" ");
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] strs = data.split(" ");
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(strs));
        return buildTree(queue);
    }
    
    public TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("#")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(queue);
            node.right = buildTree(queue);
            return node;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));