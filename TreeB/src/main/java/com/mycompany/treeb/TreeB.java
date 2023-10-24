
package com.mycompany.treeb;


public class TreeB {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        NTree t = new NTree(3);
        
        t.add(new Data(7));
        t.add(new Data(8));
        t.add(new Data(11));
        t.add(new Data(13));
        t.add(new Data(12));
        //t.add(new Data(10));
        //t.add(new Data(9));
        //t.add(new Data(15));
        /*t.add( new Data(6));
        t.add( new Data(7));
        t.add( new Data(8));
        t.add( new Data(11));
        t.add( new Data(13));
        t.add( new Data(9));*/
       
        t.stringTo();
        
        
    }
}
