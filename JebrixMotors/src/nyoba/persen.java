/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nyoba;

/**
 *
 * @author dell
 */
import java.util.Scanner;
 
public class persen {
    public static void main(String[] args){
       int total, score; 
       float percentage;
       Scanner inputNumScanner = new Scanner(System.in);
 
       System.out.println("Masukkan nilai total atau maksimum: ");       
       total = inputNumScanner.nextInt();
 
       System.out.println("Masukkan nilai yang didapat: ");
       score = inputNumScanner.nextInt();
 
       percentage = (score * 100/ total);
 
       System.out.println("Persentasenya adalah = " + percentage + " %");
    }
}
