package tree;
import java.util.LinkedList;
import java.util.Queue;

public class IsBalancedTree {
    //or complete tree


    //complete tree left to right hieght differ by at most 1
    //left first then right
    public static boolean isComplete(Node root) {
        //bfs
        if(root==null) return true;
        boolean fully_complete=true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            Node cur = queue.poll();
            if(cur.left==null){
                fully_complete=false;

            }else{
                if(!fully_complete) return false;
                queue.add(cur.left);
            }

            if(cur.right==null){
                fully_complete=false;
            }else{
                if(!fully_complete){
                    return false;
                }
                queue.add(cur.right);
            }

        }
        return true;
    }
    public static boolean isCompleteSimplified(Node root) {
        //bfs
        if(root==null) return true;
        boolean fully_complete=true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            Node cur = queue.poll();
            if(cur==null){
                fully_complete=false;

            }else{
                if(!fully_complete) return false;
                queue.add(cur.left);
                queue.add(cur.right);
            }

        }
        return true;
    }


    //a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //true balanced, false complete
    //recursively compare depth
    public static boolean isBalanced(Node root){
        Reference<Boolean> balanced = new Reference<Boolean>(true);
        getDepth(root,balanced);
        return (boolean)balanced.val;

    }
    static class Reference<T>{
        public T val;
        public Reference(T a){
            val=a;
        }
    }

    public static int getDepth(Node root, Reference balanced){
        if(root==null) return 0;
        int left = getDepth(root.left,balanced);
        int right = getDepth(root.right,balanced);

        if(Math.abs(left-right)>1){
            balanced.val=false;
        }

        return Math.max(left,right)+1;
    }

    //another similar recursive
    static class Result<T1,T2>{
        T1 val1;
        T2 val2;
        public Result(T1 a, T2 b){
            val1=a;
            val2=b;
        }
    }
    public static Result<Integer,Boolean> helper(Node root){
        if(root==null) return new Result<Integer, Boolean>(0,true);

        Result<Integer,Boolean> res = new Result<>(0,true);
        Result<Integer,Boolean> left = helper(root.left);
        Result<Integer,Boolean> right = helper(root.right);

        if(Math.abs(left.val1-right.val1)>1 || !left.val2 || !right.val2){
            res.val2=false;
        }
        res.val1 = Math.max(left.val1,right.val1)+1;
        return res;
    }


    public static void main(String[] args){
        Node bst =  new Node(5);
        bst.left = new Node(0);
        bst.left.right = new Node(1);//wrong node
        bst.left.left = new Node(1);//wrong node
        bst.right = new Node(9);
//        bst.right.left = new Result(6);
        bst.right.right = new Node(10);
//        bst.right.right.right = new Result(10);
        System.out.println(isComplete(bst));
        System.out.println(isCompleteSimplified(bst));
        System.out.println(isBalanced(bst));

    }

}
