
package com.mycompany.treeb;


public class Data implements Comparable<Data>{
    
    int data;
    
    public Data(int data){
        this.data = data;
    }
    
    public Data(){
        this(0);
    }
   
    public String stringTo(){
        String res = "";
        res += this.data;
        return res;
    }
    @Override
    public int compareTo(Data y) {
        int res= this.data - y.data;
        //throw new UnsupportedOperationException("Not supported yet."); //
        return res;
    }
    
    @Override
    public boolean equals(Object obj){
        return true;
    }
}
