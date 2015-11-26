package com.seweryn.schess;


import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GameBoardAdapter extends BaseAdapter {
     private final int[][] board01 = {{0, 0, 0, 0},
             {0, 0, 1, 0},
             {0, 5, 4, 0},
             {2, 0 , 3, 0}};
     Map<Integer,PieceType> map =  new HashMap<Integer,PieceType>();
     private final List<Item> boardFileds = new ArrayList<Item>();
     private final LayoutInflater boardLayoutInflater;
     private final Context context;
     MoveLogic logic = new MoveLogic();

     public GameBoardAdapter(Context context, int size) {
         this.context = context;
         map.put(1,PieceType.KING);
         map.put(2,PieceType.TOWER);
         map.put(3,PieceType.PAWN);
         map.put(4,PieceType.BISHOP);
         map.put(5,PieceType.HORSE);
         boardLayoutInflater = LayoutInflater.from(context);
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 int fieldId = i + j;
                 if (i% 2 ==0) {
                     {
                         if(j%2 ==0){
                             boardFileds.add(new Item("WhiteField", R.drawable.shape_white, fieldId));
                         }
                         else{
                             boardFileds.add(new Item("BlueField", R.drawable.shape_white, fieldId));
                         }
                     }
                 } else {
                     if(j%2 ==0){
                         boardFileds.add(new Item("BlueField", R.drawable.shape, fieldId));
                     }
                     else{
                         boardFileds.add(new Item("WhiteField", R.drawable.shape, fieldId));
                     }
                 }
             }
         }
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
         int resource=0;
         if (v == null) {
             v = boardLayoutInflater.inflate(R.layout.grid_item_blue, viewGroup, false);
             v.setTag(R.id.picture, v.findViewById(R.id.picture));
         }
         v.setOnDragListener(new MyDragListener());
          Vector position = Vector.convertToVecotr(4, 4, i);
         int tabValue = board01[position.getX()][position.getY()];
        //int tempValue = board01[1][2];
         if(tabValue>0){
         map.get(tabValue);
             resource = this.getResource(tabValue);
             v.findViewById(R.id.grid_item_piece).setBackgroundResource(resource);
             v.findViewById(R.id.grid_item_piece).setTag(tabValue);
             v.findViewById(R.id.grid_item_piece).setOnTouchListener(new MyTouchListener());
         }
         picture = (ImageView) v.getTag(R.id.picture);
         Item item = getItem(i);
         if (item.name.equals("WhiteField")) {
             picture.setImageResource(R.drawable.shape_white);
         } else {
             picture.setImageResource(R.drawable.shape);
         }
         //name.setText(item.name);
         return v;
     }
    public static int getResource(int tabValue){
       int resource = 0;
        switch (tabValue){
            case 1: resource =R.drawable.kingpiece;
                break;
            case 2: resource =R.drawable.towerpiece;
                break;
            case 3: resource=R.drawable.pawnpiece;
                break;
            case 4: resource=R.drawable.bishoppiece;
                break;
            case 5: resource =R.drawable.horsepiece;
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
            resource = R.drawable.tower_white;
        else if(pieceType == PieceType.PAWN)
            resource=R.drawable.pawn_white;
        else if(pieceType == PieceType.BISHOP)
             resource = R.drawable.bishop_white;
        else if(pieceType == PieceType.HORSE)
             resource = R.drawable.horse_white;

        return  resource;
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
     public void setHintsBackground(GridView gridView,int position, boolean onOrOff, PieceType pieceType){

         int[] possiblePositions = logic.PossibleMoves(4,4,Vector.convertToVecotr(4,4,position),pieceType);
         for(int i =0;i<possiblePositions.length;i++){
             FrameLayout item2 = (FrameLayout)gridView.getChildAt(possiblePositions[i]);
             if(onOrOff ==true ) {
                        View view = item2.findViewById(R.id.hint_image);
                        if(view!=null){

                            view.setBackgroundResource(getResource(pieceType));
                        }
             }
             else{
                 //item2.findViewById(R.id.grid_item_piece).setBackgroundResource(R.drawable.kingpiece);
                 View view = item2.findViewById(R.id.hint_image);
                 if(view != null){
                     view.setBackgroundResource(0);
                 }
             }
         }
     }
     private final class MyTouchListener implements View.OnTouchListener {

         public boolean onTouch(View view, MotionEvent motionEvent) {
             if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                 ClipData data = ClipData.newPlainText("", "");
                 View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                 view.startDrag(data, shadowBuilder, view, 0);
                 view.setVisibility(View.INVISIBLE);
                // System.out.println("dsaddsadasdkasjdlasdlkasjdlasdas");
                // System.out.println(v);

                 ViewGroup owner = (ViewGroup) view.getParent();
                 GridView owner2 = (GridView)owner.getParent();
                 int test = (int)view.getTag();
                 setHintsBackground(owner2,owner2.getPositionForView(view) ,true,map.get((int)view.getTag()));
                 //System.out.println(possiblePositions.length);
                 System.out.println();
                 System.out.println(owner2.getTag());
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
                     // Dropped, reassign View to ViewGroup
                     View view = (View) event.getLocalState();
                     ViewGroup owner = (ViewGroup) view.getParent();
                     GridView owner2 = (GridView)owner.getParent();
                     int position = owner2.getPositionForView(view);
                     owner.removeView(view);
                     //  view.setBackgroundResource(0);
                    //view.setTag("");
                     int[] possiblePositions = logic.PossibleMoves(4,4,Vector.convertToVecotr(4,4,position),map.get((int)view.getTag()));
                     setHintsBackground((GridView) owner.getParent(), position, false, map.get((int) view.getTag()));

                     FrameLayout container = (FrameLayout) v;
                     int destinationPosition =  owner2.getPositionForView(container);
                     boolean flag=false;
                     for(int i =0;i<possiblePositions.length;i++){
                         if(possiblePositions[i] == destinationPosition){
                             flag=true;
                         }
                     }
                     if(flag) {
                        if(removePiece(destinationPosition)){
                              container.removeViewAt(1);
                        }
                         container.addView(view);
                     }
                     else{
                         owner.addView(view);
                     }
                     view.setVisibility(View.VISIBLE);

                     break;
                 case DragEvent.ACTION_DRAG_ENDED:
                     v.setBackgroundDrawable(normalShape);
                 default:
                     break;
             }
             return true;
         }
         public boolean removePiece(int destinationPosition){
             Vector destinationVector = Vector.convertToVecotr(4,4,destinationPosition);
             int destinationPieceValue = board01[destinationVector.getX()][destinationVector.getY()];
             if(destinationPosition!=0){
                 board01[destinationVector.getX()][destinationVector.getY()]=0;
                 return true;
             }
             return  false;
         }
     }
 }
