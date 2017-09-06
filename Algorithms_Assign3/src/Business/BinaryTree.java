/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author SumedhSaraf
 */
public class BinaryTree {

    class Node {

        Node left, right;
        int value;
        int status;

        public Node() {
            left = null;
            right = null;
            value = 0;
            status = 0;
        }

        public Node(int value, int status) {
            left = null;
            right = null;
            this.value = value;
            this.status = status;
        }

        public void setLeft(Node n) {
            left = n;
        }

        public void setRight(Node n) {
            right = n;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

    //The End of the BTNODE
    private Node root;
//    private Boolean entered;
//    private Node tmp;
//    public Boolean enteredCheck = false;
    public Boolean checker = true;
    public boolean relChecker = true;
    public static int Rcounter = 0;
    public boolean r1 = true;
    public boolean r2 = true;

//    public boolean resetCounter=false;
    /* Constructor */
    public BinaryTree() {
        root = null;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int data, int status) {
        root = insert(root, data, status);
//         System.out.println("The root is : " +root.getValue());
    }

    private Node insert(Node node, int data, int status) {
        if (node == null) {
            node = new Node(data, status);
        } else {
            node.left = insert(node.left, data, status);
            node.right = insert(node.right, data, status);

        }
        return node;
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node r) {
        if (r == null) {
            return 0;
        } else {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    public void insertBlock(int n) {

        for (; n > 0; n--) {
            int data = (int) Math.pow(2, n);
            insert(data, 1);

        }
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(Node r) {
        if (r != null) {
            System.out.print(r.getValue() + "||");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node r) {
        if (r != null) {
            inorder(r.getLeft());
            System.out.println("value:  " + r.getValue() + "   status:  " + r.getStatus());
            inorder(r.getRight());
        }
    }

    public boolean request1(int val) {
        checker = true;
        return (traversenew(root, val));
    }

    public void release(int val) {
        relChecker = true;
        rel(root, val);
        relUpper(root, val);
    }

    public void relUpper(Node root, int val) {

        if (root != null) {
            relUpper(root.left, val);
            relUpper(root.right, val);
            //Visit the node by Printing the node data    
            if (root.value == val || root.value > val) {
                if (root.left != null && root.right != null && root.getLeft().getStatus() == 1 && root.getRight().getStatus() == 1) {
                    root.setStatus(1);
                }

            }
        }

    }

    public void rel(Node root, int val) {

        if (root != null) {

            if (root.getValue() == val && root.getStatus() == 0 && relChecker) {
                lowerTreeRel(root);
                relChecker = false;
            }
            rel(root.left, val);
            rel(root.right, val);
        }
    }

    public boolean traversenew(Node root, int val) {

        if (root != null) {

            if (root.getValue() == val && checker && root.getStatus() == 1) {
                root.setStatus(0);
                lowerTree(root);
                Rcounter++;
                checker = false;
            }

            if (root.getValue() != val && checker && val < root.getValue()) {
                root.setStatus(0);
            }
            traversenew(root.left, val);
            traversenew(root.right, val);
        }
        if (checker == false) {
            return true;
        } else {
            return false;
        }

    }

    void lowerTree(Node root) {
        if (root.left != null) {
            lowerTree(root.left);
        }

        root.setStatus(0);

        if (root.right != null) {
            lowerTree(root.right);
        }
        root.setStatus(0);
    }

    void lowerTreeRel(Node root) {
        if (root.left != null) {
            lowerTreeRel(root.left);
        }

        root.setStatus(1);

        if (root.right != null) {
            lowerTreeRel(root.right);
        }
        root.setStatus(1);

    }


public void request2(int val) {
     if (val == 2)
     {
         request1(2);
         return;
     }
       r1 = true;
       traversenew2(root.left, val/2);
       r2 = true;
      traversenew3(root.right, val/2);
   }
   
   public boolean traversenew2(Node root, int val) {

   if(root !=  null) {  
 
     traversenew2(root.left,val);  
     traversenew2(root.right,val);  
       
     if (root.getValue() == val && r1 && root.getStatus() == 1 )
     {
          root.setStatus(0);
          lowerTree(root);
          r1 = false;
     }
     
         if (root.getValue() != val && r1 && val <  root.getValue())
     {
         root.setStatus(0);
     }
             if (r1 == false) {
            return true;
        } else {
            return false;
        }
         
 
   }  
   return true;
   }
   public boolean traversenew3(Node root, int val) {

   if(root !=  null) {
       
     traversenew3(root.right,val);
     traversenew3(root.left,val);  
 
       
     if (root.getValue() == val && r2 && root.getStatus() == 1 )
     {
          root.setStatus(0);
          lowerTree(root);
          r2 = false;
     }
     
         if (root.getValue() != val && r2 && val <  root.getValue())
     {
         root.setStatus(0);
     }
             if (r2 == false) {
            return true;
        } else {
            return false;
        }
 
    


   
   }
      return true;
   }
          
}   





















