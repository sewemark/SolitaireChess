package com.seweryn.schess;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sew on 2015-11-20.
 */
public class CreateMapAdapter extends  BaseAdapter {


    private  int[][] boardToCreate;
    Map<Integer, PieceType> map = new HashMap<Integer, PieceType>();
    private final List<Item> boardFileds = new ArrayList<Item>();
    private final LayoutInflater boardLayoutInflater;
    private final Context context;
    private int width;
    private int height;
    public CreateMapAdapter(Context context, int _width, int _height) {

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
        boardToCreate[vector.getX()][vector.getY()] =pieceId;
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
        int tabValue = boardToCreate[position.getX()][position.getY()];
        //int tempValue = board01[1][2];
        if(tabValue>0) {
            map.get(tabValue);
            resource = GameBoardAdapter.getResource(tabValue);
            v.findViewById(R.id.grid_item_piece).setBackgroundResource(resource);
            v.findViewById(R.id.grid_item_piece).setTag(tabValue);
        }

        if (item.name.equals("WhiteField")) {
            picture.setImageResource(R.drawable.shape_white);
        } else {
            picture.setImageResource(R.drawable.shape);
        }
        //name.setText(item.name);
        return v;
    }

    private static class Item {
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

        public boolean removePiece(int destinationPosition) {
            return false;
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

