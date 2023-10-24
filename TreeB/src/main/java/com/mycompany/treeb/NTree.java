
package com.mycompany.treeb;

import java.util.LinkedList;
import java.util.Queue;

public class NTree {
    
    public NTreeNode root = null;
    public int b;
    
    public NTree(int b){
        this.b = b;
    }
    
    public void add(Data data){
        if(this.root == null ){
            this.root = new LeafNode(this.b);
        }
        
        this.root = this.root.add(data);
    }
    
    
    public void stringTo(){    
        if(root!=null){
            Queue<NTreeNode> q = new LinkedList<>();
            String res = "";
            q.offer(root);
            
            while(!q.isEmpty()){
                NTreeNode node = q.poll();
                if(node.size != 0){
                    for(int i=0;i<node.size;i++){
                        if(!node.isLeaf()){
                            InnerNode n = (InnerNode) node;
                            q.offer(n.children[i]);
                        }
                        
                    }
                }
                
                res += node.stringTo() + " ";
            }
            System.out.println(res);
        }
        
        
    }
    //todo ver estructura del arbol
}
