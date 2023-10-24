
package com.mycompany.treeb;

public class InnerNode extends NTreeNode {
    
    //Atributos
    public NTreeNode[] children;
    public Data range;
    
    //Constructor
    public InnerNode(int b){
        super(b);
        this.children = new NTreeNode[b];       
        for( int i=0; i<this.b; i++){
            this.children[i] = null;
        }
        this.range = new Data();
    }
   
    //Metodos
    
    @Override
    public boolean isMinorThan(NTreeNode node){
        InnerNode inner = (InnerNode) node;
        return this.range.compareTo(inner.range) < 0;
    }
    
    @Override
    public Data updateRange(){
      return this.range;
    }
    
    public void addNode(NTreeNode node, int pos ){
        this.children[pos] = node;
    }
    
    @Override
    public NTreeNode add(Data data){
        //actualizamos el rango
        // dato > this.rango
        if(data.compareTo(this.range) > 0 ){
            this.range = data;
        }
        if(this.children[0].isLeaf() ){ //Si los hijos son hojas       
            /*
            int i = 0;
            LeafNode leaf = (LeafNode) this.children[0];
            // i < size -1 && data > leaf.data[ leaf.size - 1 ]
            while( i < ( this.size -1) && ( data.compareTo(leaf.data[ leaf.size -1] ) > 0 )){
                i ++;
                leaf = (LeafNode) this.children[i];
            }
            
            return leaf.add(data);*/
            int m=this.size/2;
            System.out.println("m: " + m);
            int a = 0;
            int b = this.size;
            LeafNode leaf;
            while(a<b){ 
              leaf = (LeafNode) this.children[m];
              if(data.compareTo( leaf.data[ leaf.size - 1] ) < 0){ //data < leaf.data[size -1]
                b=m;
              }else{ a = m+1; } //CORRECTITUD DE ESTO?
              m = (a+b)/2;
            } 
            if(m>=this.size){
                m = this.size-1;
            }
            
            System.out.println("El m es aqui: " + m);
            leaf = (LeafNode) this.children[m];
            
            NTreeNode n = leaf.add(data);
            if( n.parent != null ){
                return n.parent;
            }
           
            return  n ;
            
        }else{ //Si los hijos son nodos internos
            
            /*
            int i = 0;
            InnerNode inner = (InnerNode) this.children[0];
            // i < size -1 && data > inner.range
            while( i < ( this.size -1) && inner.range.compareTo(data) > 0 ){
                i ++;
                inner = (InnerNode) this.children[i];
            }
            
            return inner.add(data);*/
            
            int m=this.size/2;
            int a = 0;
            int b = this.size;
            InnerNode inner;
            while(a<b){ 
              inner = (InnerNode) this.children[m];
              if(data.compareTo( inner.range ) < 0){ //data < inner.range
                b=m;
              }else{ a = m+1; } //CORRECTITUD DE ESTO?
              m = (a+b)/2;
            } 
            if(m>=this.size){
                m = this.size-1;
            }
            inner = (InnerNode) this.children[m];
            
            NTreeNode n =  inner.add(data);
            if(n.parent != null){
                return n.parent;
            }
           return n;
            
        }
      
    }
    
    @Override
    public boolean isLeaf(){
        return false;
    }
    
    public NTreeNode split(NTreeNode right){
        
        if(isFull()){

            // Split

            int b2 = this.b/2;
            int n2 = this.b-b2;
            InnerNode pRight = new InnerNode(this.b);

            for(int i=0; i<b2; i++){
                pRight.children[i] = this.children[i+n2];
                pRight.children[i].parent = pRight;
                //System.out.println("Ch: " + pRight.children[i].stringTo());
            } // copia los hijos de la derecha al nodo pRight

            pRight.size = b2;
            pRight.next = this.next;
            pRight.prev = this;
            pRight.range = this.range;

            this.size = n2;  
            this.next = pRight;
            //this.range = pRight.children[0].updateRange();
            this.range = this.children[ this.size -1].updateRange();

            if(right.isMinorThan( this.children[0] )){
                this.split(right); //Se mete donde corresponde, cae al caso !isFull
            }else{
                pRight.split(right);
            }

            if(this.parent != null){
                InnerNode parents = (InnerNode) this.parent;
                return parents.split(pRight);
            }else{
                InnerNode p = new InnerNode(this.b);
                p.children[0] = this;
                p.children[1] = pRight;

                //ACTUALIZAR BIEN EL RANGO

                p.range = pRight.updateRange();
                this.parent = p;
                pRight.parent = p;
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
                if( right.isMinorThan( this.children[m] )){ //right < children[m]
                  b=m;
                }else{ a = m+1; } //CORRECTITUD DE ESTO?
                m = (a+b)/2;
            } 
            
            System.out.println("El m en el split: " + m);

            // Correr datos desde posicion (b)
            int now = b;
            int l = this.size -1;
            NTreeNode aux = this.children[now];
            while(now < l){
                aux = this.children[now + 1];
                this.children[now+1] = this.children[now] ;
                now ++;

            }
            this.children[this.size] = aux;
            // agregar dat en posicion ...
            this.children[b] = right;
            right.parent = this;
            //this.range = this.children[this.size].updateRange(); //el rango es el de mas a la drecha
            this.size++;
            return this;

    }
  }
    
    @Override
    public String stringTo(){
    
        String res = "rango: " + this.range.stringTo();        
        return res;
    }
}

