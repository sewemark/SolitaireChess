package com.seweryn.schess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sew on 2015-12-07.
 */
public class LinkedListHandler {
    private LinkedListNode rootNode;
    private LinkedListNode currentNode;


    private int NumOfSolutions;

    public LinkedListHandler(int[][]board){
        rootNode = new LinkedListNode(board,null,true,-1);
        currentNode  = rootNode;
    }
    public int DFSSearch(){

       if(this.currentNode.WasExpanded() ==false) {
           this.currentNode.expandChild();
       }

        if(this.currentNode.childes.size()>0){
            this.currentNode = this.currentNode.childes.get(0);
            this.currentNode.parent.childes.remove(0);
            // this.currentNode.childes.remove(0);
            DFSSearch();
        }
        else{
             if(checkIfSolutionFounded()) {
                 NumOfSolutions++;
             }
             if(this.currentNode.parent !=null && this.currentNode.IsRoot() ==false){
                  this.currentNode = this.currentNode.parent;
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

}

