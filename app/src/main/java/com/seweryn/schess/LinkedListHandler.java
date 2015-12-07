package com.seweryn.schess;

/**
 * Created by sew on 2015-12-07.
 */
public class LinkedListHandler {
    private LinkedListNode rootNode;
    private LinkedListNode currentNode;
    private MoveLogic logic;
    public LinkedListHandler(int[][]board){
        rootNode = new LinkedListNode(board,null,true);
        currentNode  = rootNode;
        logic = new MoveLogic();
    }
    public void exapndChilds(){
        for(int i=0;i < this.currentNode.board )
    }
}

