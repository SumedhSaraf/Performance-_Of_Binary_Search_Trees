/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Random;
import java.util.Stack;

/**
 *
 * @author 	Sumedh
 */
public class MainMethod {

    public static void main(String args[]) {

        Stack stack = new Stack();
        int failedcount = 0;
        int request = 10000;
        Random rand = new Random();

        BinaryTree BT = new BinaryTree();

        BT.insertBlock(4);
        System.out.println("\n**----------**");
//        BT.request1(2);
       BT.request2(16);
            //BT.request1(2);
        BT.inorder();
        
//        BT.inorder();
        for (int i = 1; i < request; i++) {
            int data = (int) Math.pow(2, (rand.nextInt(4 - 1) + 1));
            boolean check = BT.request1(data);
            stack.push(data);
            if (i % 100 == 0) {
                while (!stack.isEmpty()) {
                    int rel = (int) stack.pop();
                    BT.release(rel);
                }
            }

            if (!check) {
                failedcount++;
            }

        }
       
        System.out.println("The total number of request is : " + request);
        System.out.println("The failed count is : " + failedcount);

    }
}
