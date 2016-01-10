package com.seweryn.schess;

import android.app.Activity;
import android.app.ActivityManager;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by sew on 2015-12-07.
 */
public class LinkedListHandler  implements Runnable {
    private LinkedListNode rootNode;
    private LinkedListNode currentNode;
    private List<Solution> solutions;
    private int maxDepth;
    private int maxWidth;
    public  int expadnedCount=0;
    private int NumOfSolutions;
    public int numOfSol =-1;
    public LinkedListHandler(int[][]board){
        rootNode = new LinkedListNode(board,null,true,-1);
        solutions = new ArrayList<>();
        currentNode  = rootNode;
        maxDepth = 0;
        maxWidth = 0;
    }
    @Override
    public void run() {
     this.numOfSol=    this.DFSSearch();
    }

    private int DFSSearch(){

       if(this.currentNode.WasExpanded() ==false) {
           this.currentNode.expandChild();
            this.expadnedCount++;
          if(this.currentNode.childes.size()==0 && this.currentNode.IsRoot()==true)
              return 0;
           maxDepth++;
           if(this.currentNode.childes.size()>maxWidth){
               maxWidth=this.currentNode.childes.size();
           }
       }

        if(this.currentNode.childes.size()>0){
            this.currentNode = this.currentNode.childes.get(0);
            this.currentNode.parent.childes.remove(0);
            // this.currentNode.childes.remove(0);
            System.out.println("Child DFS");
            System.out.println(expadnedCount);
            DFSSearch();
        }
        else{
             if(checkIfSolutionFounded()) {
                 NumOfSolutions++;
                 LinkedListNode pointer = this.currentNode;
                 Solution solution =new Solution();
                 while (pointer.parent !=null){
                     solution.boards.add(pointer.board);
                     pointer = pointer.parent;
                 }
                 solutions.add(0,solution);
             }
             if(this.currentNode.parent !=null && this.currentNode.IsRoot() ==false){

                 this.currentNode.childes.removeAll(this.currentNode.childes);

                  this.currentNode = this.currentNode.parent;

                 System.out.println(expadnedCount);
                 System.out.println("Up trre DFS");
                 if(expadnedCount>=287){
                  // System.out.println(ActivityManager.getMyMemoryState(););
                     System.out.println("lalal");
                 }
                 DFSSearch();
             }
            else{
                 //koniec algorytymu
                 return NumOfSolutions;
             }
        }
        return  NumOfSolutions;
    }

    public boolean checkIfSolutionFounded(){
        int numOfPieces =0;
        for(int i=0;i < this.currentNode.board.length;i++){
            for(int j=0; j<this.currentNode.board[0].length;j++){
                if(this.currentNode.board[i][j] !=0){
                    numOfPieces++;
                }
            }
        }
        return numOfPieces==1 ? true : false;
    }
    public List<Solution> getSolutions(){
        return  this.solutions;
    }
    public int getTreeDepth(){
       return this.maxDepth;
    }
    public int getMaxWidth(){
        return this.maxWidth;
    }

}

