package com.pizzacutter;

import java.io.IOException;

public class Solver {
    
    public static void solve(String fileName) throws IOException {
        PizzaCutter pizzaCutter = new PizzaCutter();
        pizzaCutter.run(fileName);
        pizzaCutter.printFileResult();
    }
    
//    public static void visualization() throws IOException {
//        PizzaCutter pizzaCutter = new PizzaCutter();
//        pizzaCutter.visualizeRun();
//    }
    
    public static void main(String args[]) throws Exception {
        // example file
        //solve("src//com//pizzacutter//data//a_example.in");
        
        // small file
        //solve("src//com//pizzacutter//data//b_small.in");
        
        // medium file
        //solve("src//com//pizzacutter//data//c_medium.in");
        
        // big file
        solve("src//com//pizzacutter//data//d_big.in");
        
        //visualization();
    }
    
    
}
