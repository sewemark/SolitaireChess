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

import com.seweryn.schess.Controllers.IBoardLogicController;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.R;
import com.seweryn.schess.Static.Lodash;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;


public class BoardAdapter extends  BaseAdapter {


    protected   int[][] board;
    private final List<Item> boardFileds = new ArrayList<Item>();
    protected final LayoutInflater boardLayoutInflater;
    protected final Context context;
    protected int width;
    protected int height;
    private IBoardLogicController boardLogicController;


    public BoardAdapter(Context _context,IBoardLogicController _boardLogicController, int _width, int _height) {
        this.context = _context;
        this.width = _width;
        this.height = _height;
        boardLayoutInflater = LayoutInflater.from(context);
        this.boardLogicController = _boardLogicController;
        setBackgroundFields();
    }
    /**
     * checks proper board background
     * goal position
     * @return  void
     */
    private void setBackgroundFields(){
        for (int i = 0; i < height; i++) {
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
    /**
     * method that converts dp to Px
     * @param int dp value in dp
     * @return  int value in interger
     */
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
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
        int resource;
        if (v == null ||  v.findViewById(R.id.grid_item_piece)==null) {
            v = boardLayoutInflater.inflate(R.layout.grid_item_blue, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
        }
        picture = (ImageView) v.getTag(R.id.picture);
        Item item = getItem(i);
        Vector position = Vector.convertToVector(width, height, i);
        int tabValue = boardLogicController.getBoard()[position.getY()][position.getX()];
        if(tabValue>0) {
            resource = Lodash.getResource(tabValue);
            v.findViewById(R.id.grid_item_piece).setBackgroundResource(resource);
            v.findViewById(R.id.grid_item_piece).setTag(tabValue);
        }else if(tabValue==0){
            resource = Lodash.getResource(tabValue);
            v.findViewById(R.id.grid_item_piece).setBackgroundResource(0);
            v.findViewById(R.id.grid_item_piece).setTag(tabValue);
        }

        ImageView imageView = (ImageView)v.findViewById(R.id.grid_item_piece);
        imageView.getLayoutParams().width= dpToPx((int)Math.ceil(60.0 * (4.0/this.height)));
        imageView.getLayoutParams().height= dpToPx((int)Math.ceil(60.0 * (4.0/this.width)));
        ImageView hintImageView = (ImageView)v.findViewById(R.id.hint_image);
        hintImageView.getLayoutParams().width= dpToPx((int)Math.ceil(60.0 * (4.0/this.width)));
        hintImageView.getLayoutParams().height= dpToPx((int)Math.ceil(50.0 * (4.0/this.height)));
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
            }
    }
}

