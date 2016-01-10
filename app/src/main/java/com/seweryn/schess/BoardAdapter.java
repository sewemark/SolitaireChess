package com.seweryn.schess;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardAdapter extends  BaseAdapter {


    protected   int[][] board;
    private final List<Item> boardFileds = new ArrayList<Item>();
    protected final LayoutInflater boardLayoutInflater;
    protected final Context context;
    protected int width;
    protected int height;
    public BoardAdapter(Context context, int _width, int _height) {

        this.context = context;
        this.width = _width;
        this.height = _height;
        boardLayoutInflater = LayoutInflater.from(context);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                int fieldId = i + j;
                if (i % 2 == 0) {
                    {
                        if (j % 2 == 0) {
                            boardFileds.add(new Item("WhiteField", R.drawable.shape_white, fieldId));
                        } else {
                            boardFileds.add(new Item("BlueField", R.drawable.shape_white, fieldId));
                        }
                    }
                } else {
                    if (j % 2 == 0) {
                        boardFileds.add(new Item("BlueField", R.drawable.shape, fieldId));
                    } else {
                        boardFileds.add(new Item("WhiteField", R.drawable.shape, fieldId));
                    }
                }
            }
        }
    }

    public  void setPieceOnPosition(int position, int pieceId){
        Vector vector = Vector.convertToVecotr(width, height, position);
        board[vector.getX()][vector.getY()] =pieceId;
    }
    @Override
    public int getCount() {
        return boardFileds.size();
    }

    @Override
    public Item getItem(int i) {
        return boardFileds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return boardFileds.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;
        int resource;
        if (v == null) {
            v = boardLayoutInflater.inflate(R.layout.grid_item_blue, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
        }
        picture = (ImageView) v.getTag(R.id.picture);
        Item item = getItem(i);
        Vector position = Vector.convertToVecotr(width, height, i);
        int tabValue = board[position.getX()][position.getY()];
        if(tabValue>0) {
            resource = GameBoardAdapter.getResource(tabValue);
            v.findViewById(R.id.grid_item_piece).setBackgroundResource(resource);
            v.findViewById(R.id.grid_item_piece).setTag(tabValue);
        }
        if (item.name.equals("WhiteField")) {
            picture.setImageResource(R.drawable.shape_white);
        } else {
            picture.setImageResource(R.drawable.shape);
        }
        return v;
    }

    protected static class Item {
        public final String name;
        public final int fieldId;
        public final int drawableId;

        Item(String name, int drawableId, int fieldId) {
            this.name = name;
            this.fieldId = fieldId;
            this.drawableId = drawableId;
        }
    }

    private final class MyTouchListener implements View.OnTouchListener {




        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                return true;
            } else {
                return false;
            }
        }
    }




        class MyDragListener implements View.OnDragListener {
            Drawable enterShape = context.getResources().getDrawable(R.drawable.shape_droptarget);
            Drawable normalShape = context.getResources().getDrawable(R.drawable.shape);

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundDrawable(enterShape);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundDrawable(normalShape);
                        break;
                    case DragEvent.ACTION_DROP:
                        System.out.println("dass");
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setBackgroundDrawable(normalShape);
                    default:
                        break;
                }
                return true;
            }      public boolean removePiece(int destinationPosition) {
            return false;
        }

    }
    public static int getResource(int tabValue){
        int resource = 0;
        switch (tabValue){
            case 1: resource =R.drawable.kingpiece;
                break;
            case 2: resource =R.drawable.rockpiece;
                break;
            case 3: resource=R.drawable.pawnpiece;
                break;
            case 4: resource=R.drawable.bishoppiece;
                break;
            case 5: resource =R.drawable.knightpiece;
                break;
            case 6: resource=R.drawable.queenpiece;
            default:
                break;
        }
        return  resource;
    }
    public static int getResource(PieceType pieceType){
        int resource = 0;

        if(pieceType ==PieceType.KING)
            resource =R.drawable.king_white;
        else if(pieceType == PieceType.TOWER)
            resource = R.drawable.rock_white;
        else if(pieceType == PieceType.PAWN)
            resource=R.drawable.pawn_white;
        else if(pieceType == PieceType.BISHOP)
            resource = R.drawable.bishop_white;
        else if(pieceType == PieceType.HORSE)
            resource = R.drawable.knight_white;
        else if(pieceType == PieceType.QUEEN)
            resource = R.drawable.queen_white;

        return  resource;
    }
    public  int[][] getCreateBoard(){
        return board;
    }
    public void setBoardToCreate(int[][]val){
        if(val!=null)
            board= val;
    }
}

