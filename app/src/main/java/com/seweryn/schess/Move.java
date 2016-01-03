package com.seweryn.schess;

import android.view.View;

/**
 * Created by sew on 2016-01-02.
 */
public class Move {
    public Move(View _view,int _pieceType, int _betatedPieceType, int _sourcePostion, int _destinationPostion){
        this.beatedPieceType = _betatedPieceType;
        this.pieceType = _pieceType;
        this.destinatioPosition = _destinationPostion;
        this.sourPositon = _sourcePostion;
        this.view = _view;

    }
    public int pieceType;
    public View view;
    public int beatedPieceType;
    public int sourPositon;
    public int destinatioPosition;
}
