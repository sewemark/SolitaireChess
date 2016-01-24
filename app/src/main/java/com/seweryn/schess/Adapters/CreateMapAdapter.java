package com.seweryn.schess.Adapters;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.R;
import com.seweryn.schess.Static.Lodash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sew on 2015-11-20.
 */
public class CreateMapAdapter extends  BoardAdapter {


    private  int[][] boardToCreate;
    private final Context context;
    private int width;
    private int height;
    public CreateMapAdapter(Context context, int _width, int _height) {
        super(context, _width, _height);

        this.context = context;
        this.width = _width;
        this.height = _height;
    }

    public  void setPieceOnPosition(int position, int pieceId){
        Vector vector = Vector.convertToVector(width, height, position);
        boardToCreate[vector.getY()][vector.getX()] =pieceId;
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
        Vector position = Vector.convertToVector(width, height, i);
        int tabValue = boardToCreate[position.getY()][position.getX()];
        //int tempValue = board01[1][2];
        if(tabValue>0) {
            resource = Lodash.getResource(tabValue);
            v.findViewById(R.id.grid_item_piece).setBackgroundResource(resource);
            v.findViewById(R.id.grid_item_piece).setTag(tabValue);
        }else if(tabValue==0){
            resource = Lodash.getResource(tabValue);
            v.findViewById(R.id.grid_item_piece).setBackgroundResource(0);
            v.findViewById(R.id.grid_item_piece).setTag(tabValue);

            //imageView.getLayoutParams().width= 60;
           // imageView.getLayoutParams().height= 60;

        }
        ImageView imageView = (ImageView)v.findViewById(R.id.grid_item_piece);
        imageView.getLayoutParams().width= dpToPx((int)Math.ceil(60.0 * (4.0/this.height)));
        imageView.getLayoutParams().height= dpToPx((int)Math.ceil(60.0 * (4.0/this.width)));

        if (item.name.equals("WhiteField")) {
            picture.setImageResource(R.drawable.shape_white);
        } else {
            picture.setImageResource(R.drawable.shape);
        }
        //name.setText(item.name);
        return v;
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
        }


    }
    public  int[][] getCreateBoard(){
        return boardToCreate;
    }
    public void setBoardToCreate(int[][]val){
        if(val!=null)
            boardToCreate= val;
    }
}

