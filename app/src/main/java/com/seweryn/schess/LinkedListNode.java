package com.seweryn.schess;

import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by sew on 2015-12-07.
 */
public class LinkedListNode {
    public int [][]board;
    public LinkedListNode parent;
    public List<LinkedListNode> childes;
    private  boolean isRoot = false;
    public  LinkedListNode(int[][] _board, LinkedListNode _parent,boolean _isRoot ){
        this.board = _board;
        this.parent = _parent;
        this.isRoot = _isRoot;
        }

}
