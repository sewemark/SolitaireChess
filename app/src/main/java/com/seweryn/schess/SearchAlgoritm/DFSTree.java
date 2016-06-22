package com.seweryn.schess.SearchAlgoritm;

import com.seweryn.schess.Logic.MoveRulesLogic;
import com.seweryn.schess.Models.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sew on 2015-12-07.
 */
public class DFSTree implements ISearchTree<Solution> {
    private SearchTreeNode rootNode;
    private SearchTreeNode currentNode;
    private List<Solution> solutions;
    private final  int NO_SOLUTION =0;
    private int maxDepth;
    private int maxWidth;
    public  int expadnedCount=0;
    private int numOfSolutions;
    public int numOfSol =-1;
    public DFSTree(int[][] board){
        rootNode = new SearchTreeNode(board, new MoveRulesLogic(),null,true,-1);
        solutions = new ArrayList<>();
        currentNode  = rootNode;
        maxDepth = 0;
        maxWidth = 0;
    }
    @Override
    public void run() {
     this.numOfSol=  this.search();
    }

    /**
     * searches a tree to find solutions for a board
     * @return int number of solutions for a board
     */
    public int search(){
        try {
            if (!this.currentNode.wasExpanded()) {
                this.currentNode.expandChild();

                if (checkIfRootHasChild()) {
                    return NO_SOLUTION;
                }
                updateTreeProperties();
            }

            if (this.currentNode.childes.size() > 0) {
                this.currentNode = this.currentNode.childes.get(0);
                this.currentNode.parent.childes.remove(0);
                search();
            } else {
                if (checkIfSolutionFounded()) {
                    numOfSolutions++;
                    extractSolution();
                }
                if (checkIfCanGoTowardsRoot()) {
                    this.currentNode.childes.removeAll(this.currentNode.childes);
                    this.currentNode = this.currentNode.parent;
                    search();
                } else {
                    return numOfSolutions;
                }
            }
            return numOfSolutions;
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        finally{
            return numOfSolutions;
        }
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

    /**
     * extracts solution
     * @return  void
     */
    private  void extractSolution(){
        SearchTreeNode pointer = this.currentNode;
        Solution solution =new Solution();
        while (pointer !=null){
            solution.boards.add(pointer.board);
            pointer = pointer.parent;
        }
        solutions.add(0,solution);
    }

    /**
     * update tree properties
     * @return  void
     */
    private void updateTreeProperties(){
        maxDepth++;
        expadnedCount++;
        if(this.currentNode.childes.size()>maxWidth){
            maxWidth=this.currentNode.childes.size();
        }
    }

    /**
     * checks if node is a root node
     * @return  boolean value that indicates if node is a root node
     */
    private boolean checkIfRootHasChild(){
        return  this.currentNode.childes.size()==0 && this.currentNode.isRoot()==true;
    }

    /**
     * checks if a node is not root and has parent
     * @return  boolean value that indicates if backtracking can be performed
     */
    private boolean checkIfCanGoTowardsRoot(){
       return this.currentNode.parent !=null && !this.currentNode.isRoot();
    }

    /**
     * checks if a board is a win board
     * @return  boolean value that indicates if the board is  win board
     */
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

