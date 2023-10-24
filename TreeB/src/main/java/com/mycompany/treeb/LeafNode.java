
package com.mycompany.treeb;

public class LeafNode extends NTreeNode {
    public Data[] data;
    
    //Constructor
    public LeafNode(int b){
        super(b);
        this.data = new Data[b];
        for( int i=0; i<b; i++){
            this.data[i] = null;
        }
    }
    
    @Override
    public boolean isMinorThan(NTreeNode node){
        LeafNode leaf = (LeafNode) node;
        
        if(leaf.size == 0){
            return false;
        }else if( this.size == 0){
            return true;
        }else{
            return this.data[0].compareTo( leaf.data[0] ) < 0;
        }
    }
    
   
    
    @Override
    public Data updateRange(){
        return this.data[ this.size-1];
    }
    
    @Override
    public boolean isLeaf(){
        return true;
    }
     
    
    @Override
    public NTreeNode add(Data data){
    if(isFull()){
      // Split
      int b2 = this.b/2;
      int n2 = this.b-b2;
      LeafNode right = new LeafNode(this.b);
      for(int i=0; i<b2; i++){
        right.data[i] = this.data[i+n2];
      } // copia los valores de la derecha al nodo right

      right.size = b2;
      this.size = n2;
      right.next = this.next;
      this.next = right;
      right.prev = this;

      // ACTUALIZAR EL RANGO DE LOS NODOS

      if(data.compareTo(right.data[0]) < 0){ //data < rigth.data[0]
        this.add(data);
      }else{
        right.add(data);
      }


      // Informar al padre del split
      if(this.parent != null){
        InnerNode parents = (InnerNode) this.parent;
        return parents.split(right);
      }else{
        InnerNode p = new InnerNode(this.b);
        p.children[0] = this;
        p.children[1] = right;

        //ACTUALIZAR BIEN EL RANGO

        p.range = right.data[ right.size -1];
        this.parent = p;
        right.parent = p;
        p.size = 2;
        return p;
      }
    }else{
      //todo corregir rangos

      // Add data into array
      int m=this.size/2;
      int a = 0;
      int b = this.size;
      while(a<b){          
        if(data.compareTo( this.data[m]) < 0){ //data < this.data[0]
          b=m;
        }else{ a = m+1; } //CORRECTITUD DE ESTO?
        m = (a+b)/2;
      } 

      // Correr datos desde posicion (b)
      int now = b;
      int l = this.size -1;
      Data aux = this.data[now];
      while(now < l){
        aux = this.data[now + 1];
        this.data[now+1] = this.data[now] ;
        now ++;

      }
      this.data[this.size] = aux;

      // agregar dat en posicion ...
      this.data[b] = data;
      this.size++;
      return this;
    }
  }
    
    
    @Override
    public String stringTo(){
        String res = "padre: "; 
        if(this.parent != null){
            res+= this.parent.stringTo() + " ";
        }else{
            res += "null ";
        }
        res += "data: ";
        for(int i=0; i<this.size; i ++){
            res += this.data[i].stringTo() + " ";
        }
        
        res += " ";
        return res;
    }
}
