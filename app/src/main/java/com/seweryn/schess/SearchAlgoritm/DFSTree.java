package com.seweryn.schess.SearchAlgoritm;

import com.seweryn.schess.Logic.MoveRulesLogic;
import com.seweryn.schess.Models.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sew on 2015-12-07.
 */
public class DFSTree implements ISearchTree<Solution> {
    private LinkedListNode rootNode;
    private LinkedListNode currentNode;
    private List<Solution> solutions;
    private final  int NO_SOLUTION =0;
    private int maxDepth;
    private int maxWidth;
    public  int expadnedCount=0;
    private int NumOfSolutions;
    public int numOfSol =-1;
    public DFSTree(int[][] board){
        rootNode = new LinkedListNode(board, new MoveRulesLogic(),null,true,-1);
        solutions = new ArrayList<>();
        currentNode  = rootNode;
        maxDepth = 0;
        maxWidth = 0;
    }
    @Override
    public void run() {
     this.numOfSol=  this.Search();
    }

    public int Search(){

       if(!this.currentNode.WasExpanded()) {
               this.currentNode.expandChild();

               if(checkIfRootHasChild()) {
                  return NO_SOLUTION;
              }
              updateTreeProperties();
       }

        if(this.currentNode.childes.size()>0){
            this.currentNode = this.currentNode.childes.get(0);
            this.currentNode.parent.childes.remove(0);
            Search();
        }
        else{
             if(checkIfSolutionFounded()) {
                 NumOfSolutions++;
                 extractSolution();
             }
             if(checkIfCanGoTowardsRoot()){
                  this.currentNode.childes.removeAll(this.currentNode.childes);
                  this.currentNode = this.currentNode.parent;
                  Search();
             }
            else{
                 return NumOfSolutions;
             }
        }
        return  NumOfSolutions;
    }

    @Override
    public int getNumberOfResults() {
        return numOfSol;
    }

    @Override
    public int getTreeWidth() {
        return  maxWidth;
    }

    @Override
    public int getTreeHeight() {
        return maxDepth;
    }

    @Override
    public int getTreeLeaves() {
        return expadnedCount;
    }

    @Override
    public List<Solution> getSolutoins() {
        return solutions;
    }

    private  void extractSolution(){
        LinkedListNode pointer = this.currentNode;
        Solution solution =new Solution();
        while (pointer !=null){
            solution.boards.add(pointer.board);
            pointer = pointer.parent;
        }
        solutions.add(0,solution);
    }

    private void updateTreeProperties(){
        maxDepth++;
        expadnedCount++;
        if(this.currentNode.childes.size()>maxWidth){
            maxWidth=this.currentNode.childes.size();
        }
    }

    private boolean checkIfRootHasChild(){
        return  this.currentNode.childes.size()==0 && this.currentNode.IsRoot()==true;
    }

    private boolean checkIfCanGoTowardsRoot(){
       return this.currentNode.parent !=null && !this.currentNode.IsRoot();
    }

    private boolean checkIfSolutionFounded(){
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

