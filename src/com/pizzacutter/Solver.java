package com.pizzacutter;

import java.io.IOException;

public class Solver {
    
    public static void solve(String fileName) throws IOException {
        PizzaCutter pizzaCutter = new PizzaCutter();
        pizzaCutter.readPizza(fileName);
        pizzaCutter.cutPizza();
        System.out.println("Slices: ");
        System.out.println(pizzaCutter.getSlicesResult());
        System.out.println("Whole pizza square: " + pizzaCutter.getCols()*pizzaCutter.getRows());
        System.out.println("Slices square: " + pizzaCutter.getTotalSlicesSquare());
    }
    
    public static void solveTest() throws IOException {
        PizzaCutter pizzaCutter = new PizzaCutter();
        pizzaCutter.testFiles();
        System.out.println("Slices: ");
        System.out.println(pizzaCutter.getSlicesResult());
        System.out.println("Whole pizza square: " + pizzaCutter.getCols()*pizzaCutter.getRows());
        System.out.println("Slices square: " + pizzaCutter.getTotalSlicesSquare());
    }
    
    public static void main(String args[]) throws Exception {
        // example file
        //solve("src//com//pizzacutter//data//a_example.in");
        
        // small file
        //solve("src//com//pizzacutter//data//b_small.in");
        
        // medium file
        //solve("src//com//pizzacutter//data//c_medium.in");
        
        // big file
        //solve("src//com//pizzacutter//data//d_big.in");
        
        solveTest();
    }
    
    
}
