/*
    Binary Tree Level Order Traversal II
    Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

    For example:
    Given binary tree {3,9,20,#,#,15,7},
        3
       / \
      9  20
        /  \
       15   7
    [
      [15,7],
      [9,20],
      [3]
    ]

*/

//BFS
public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> res =  new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int curNum = 1;
        int nextNum = 0;
        ArrayList<Integer> levelres = new ArrayList<Integer>();
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            curNum--;
            levelres.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
                nextNum++;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextNum++;
            }
            
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                res.add(0, levelres); //和1最关键的区别之处！
                levelres = new ArrayList<Integer>();
            }
        }
        return res;
    }
}

//DFS 
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return res;
        dfs(root, res, 0);
        return res;
    }
    
    public void dfs(TreeNode root, ArrayList<ArrayList<Integer>> res, int depth) {
        if (root == null)
            return;
        if (depth >= res.size()) 
            res.add(0, new ArrayList<Integer>());
        //关键res.size() - depth - 1
        res.get(res.size() - depth - 1).add(root.val);
        dfs(root.left, res, depth + 1);
        dfs(root.right, res, depth + 1);
    }