package com.seweryn.schess.Models.ViewModels;

import android.view.View;

/**
 * Created by sew on 2016-01-02.
 */
public class Move {
    public Move(View _view,int _beatingPieceTypeValue, int _defeatedPieceTypeValue, int _orignialPiecePosition, int _destinationPiecePosition){
        this.defeatedPieceTypeValue = _defeatedPieceTypeValue;
        this.beatingPieceTypeValue = _beatingPieceTypeValue;
        this.destinationPiecePosition = _destinationPiecePosition;
        this.orignialPiecePosition = _orignialPiecePosition;
        this.view = _view;

    }
    public Move(int _beatingPieceTypeValue, int _defeatedPieceTypeValue, int _orignialPiecePosition, int _destinationPiecePosition){
        this.defeatedPieceTypeValue = _defeatedPieceTypeValue;
        this.beatingPieceTypeValue = _beatingPieceTypeValue;
        this.destinationPiecePosition = _destinationPiecePosition;
        this.orignialPiecePosition = _orignialPiecePosition;

    }

    public int beatingPieceTypeValue;
    public int defeatedPieceTypeValue;
    public int orignialPiecePosition;
    public int destinationPiecePosition;
    public View view;
}
