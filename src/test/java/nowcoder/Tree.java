package nowcoder;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by ziyue on 2018/4/6.
 */


//Definition for binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}



public class Tree {


    /**
     * Definition for binary tree
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {


        if(pre==null||in==null)return null;
        TreeNode head=null;
        if(pre.length>0){
            head  = new TreeNode(pre[0]);
        }
        HashMap<Integer,Integer> inMap = new HashMap<>();

        for(int i=0;i<in.length;++i){
            inMap.put(in[i],i);
        }

        int [] index = new int[1];
        index[0]=1;

        reConstructPre(head,pre,1,inMap,in,0,inMap.get(head.val)-1);
        reConstructPre(head,pre,1,inMap,in,inMap.get(head.val)+1,in.length-1);

        return head;
    }
    public void reConstructPre(TreeNode treeNode,
                               int [] pre,int index,
                               HashMap<Integer,Integer> inMap,
            int [] in,int inStart,int inEnd){

        if(inStart>inEnd||treeNode==null) return ;
        if(index>=pre.length||inMap.get(pre[index])>inEnd)return ;
        TreeNode treeNode_tmp = null;

        while(inMap.get(pre[index])<inStart){
            ++index;
            if(index>=pre.length||inMap.get(pre[index])>inEnd)return ;
        }


        if(inMap.get(pre[index])<inMap.get(treeNode.val)){
            treeNode.left= new TreeNode(pre[index]);
            treeNode_tmp=treeNode.left;
        }else if(inMap.get(pre[index])>inMap.get(treeNode.val)){
            treeNode.right= new TreeNode(pre[index]);
            treeNode_tmp=treeNode.right;
        }
        reConstructPre(treeNode_tmp,
                pre,index+1,inMap,
                in,inStart,inMap.get(treeNode_tmp.val)-1);
        reConstructPre(treeNode_tmp,
                pre,index+1,inMap,
                in,inMap.get(treeNode_tmp.val)+1,inEnd);
    }





    @Test
    public void test(){
        int []pre={1,2,4,7,3,5,6,8};
        int []in={4,7,2,1,5,3,8,6};

        //int []pre={1,2,3,4,5,6,7};
        //int []in={3,2,4,1,6,5,7};
        TreeNode treeNode= reConstructBinaryTree(pre,in);
        System.out.println(treeNode);
    }

}




