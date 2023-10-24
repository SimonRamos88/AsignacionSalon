
package com.mycompany.treeb;

public abstract class NTreeNode {
    
    //Atributos
    
    public int b;
    public int size = 0;
    public NTreeNode parent;
    public NTreeNode next;
    public NTreeNode prev;
    
    //Constructor
    
    public NTreeNode(int b){
        this.b = b;
        this.parent = null;
        this.next = null;
        this.prev = null;
    }
    
    //Metodos
    
    public boolean isFull(){
        return this.b == this.size;
    }

    public abstract boolean isMinorThan(NTreeNode node);

    public abstract Data updateRange();

    public abstract NTreeNode add(Data data);
    
    public abstract boolean isLeaf();
    
    public abstract String stringTo();
    
}
