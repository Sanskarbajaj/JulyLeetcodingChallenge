class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
      if(root == null) return new ArrayList();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while(!q.isEmpty()){
           int count =  q.size();
            List<Integer> level = new ArrayList<>();
           for(int i=1;i<=count;i++){
               TreeNode n = q.poll();
               level.add(n.val);
               if(n.left != null) q.add(n.left);
               if(n.right != null) q.add(n.right);
           }
           result.add(level);
        }
      Collections.reverse(result);
    return result;}
}