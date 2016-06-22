package com.seweryn.schess.SearchAlgoritm;

import com.seweryn.schess.Controllers.BoardLogicController;
import com.seweryn.schess.Controllers.IBoardLogicController;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Logic.IMoveRulesLogic;
import com.seweryn.schess.Logic.MoveRulesLogic;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.Static.Lodash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sew on 2015-12-07.
 */
public class SearchTreeNode {
    public int [][]board;
    public SearchTreeNode parent;
    public List<SearchTreeNode> childes;
    public List<Integer> possibleMoves;
    private PieceType currentPiecType;
    private boolean wasExpanded;
    private IMoveRulesLogic logic;
    private Vector currentPiece;
    private  boolean isRoot = false;
    IBoardLogicController boardLogicController;
    public SearchTreeNode(int[][] _board, IMoveRulesLogic _logic, SearchTreeNode _parent, boolean _isRoot, int destinationPosition){
        this.board = _board;
        this.parent = _parent;
        this.isRoot = _isRoot;
        this.boardLogicController = new BoardLogicController();
        this.boardLogicController.setBoard(board);
        this.logic = new MoveRulesLogic();
        this.childes = new ArrayList<SearchTreeNode>();
        this.wasExpanded =false;
        if(destinationPosition>=0) {
            this.currentPiece = Vector.convertToVector(board[0].length, board.length, destinationPosition);
        }
        else currentPiece= null;
    }
    /**
     * expand child elements of a tree leave
     * @return  void
     */
    public void expandChild(){
        this.wasExpanded=true;
        Vector[] allPieces = boardLogicController.getPiecePositions();

        for(int j=0;j<allPieces.length;j++) {
            this.setPiece(allPieces[j]);
            this.setPieceType(Lodash.getPiecType(board[allPieces[j].getY()][allPieces[j].getX()]));
            this.possibleMoves = Arrays.asList(logic.getMoveStrategy(board[0].length, board.length, getPiceType()).getMoveForPiece(getCurrentPiece()));
            if (this.possibleMoves.size() <= 0) {

            } else {
                for (int i = 0; i < possibleMoves.size(); i++) {
                    Integer destinationPosition = possibleMoves.get(i);
                    if (boardLogicController.canMove(destinationPosition, currentPiece, currentPiecType)) {
                        SearchTreeNode nextChild = new SearchTreeNode(boardLogicController.performMove(destinationPosition, currentPiece),logic, this, false, destinationPosition);
                        this.childes.add(nextChild);
                    }
                }
            }
        }
    }
    public  boolean isRoot(){return this.isRoot;}
    public Vector getCurrentPiece(){
        return  currentPiece;
    }
    public boolean wasExpanded(){
        return  this.wasExpanded;
    }
    public void setPiece(Vector val){
        this.currentPiece = val;
    }
    public PieceType getPiceType(){
        return this.currentPiecType;
    }
    public void setPieceType(PieceType val){
        this.currentPiecType = val;
    }
}
