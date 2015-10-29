package com.seweryn.schess;


import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.*;


import java.util.ArrayList;
import java.util.List;

 public final class GameBoardAdapter extends BaseAdapter {
     private final int[][] board01 = {{0, 0, 0, 0},
             {0, 1, 0, 0},
             {0, 0, 0, 0},
             {0, 0, 0, 0}};
     private final List<Item> boardFileds = new ArrayList<Item>();
     private final LayoutInflater boardLayoutInflater;
     private final Context context;

     public GameBoardAdapter(Context context, int size) {
         this.context = context;
         boardLayoutInflater = LayoutInflater.from(context);
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 int fieldId = i + j;
                 if (i% 2 ==0 &&j % 2 == 0) {
                     boardFileds.add(new Item("WhiteField", R.drawable.shape_white, fieldId));
                 } else {
                     boardFileds.add(new Item("BlueField", R.drawable.shape, fieldId));
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

         if (v == null) {
             v = boardLayoutInflater.inflate(R.layout.grid_item_blue, viewGroup, false);
             v.setTag(R.id.picture, v.findViewById(R.id.picture));

         }
         v.setOnDragListener(new MyDragListener());

         if (i == 1) {
             v.findViewById(R.id.grid_item_piece).setBackgroundResource(R.drawable.kingpiece);
             v.findViewById(R.id.grid_item_piece).setTag("king");

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
                 ClipData data = ClipData.newPlainText("", "");
                 View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                 view.startDrag(data, shadowBuilder, view, 0);
                 view.setVisibility(View.INVISIBLE);
                 System.out.println("dsaddsadasdkasjdlasdlkasjdlasdas");
                 System.out.println(view.getTag());
                 ViewGroup owner = (ViewGroup) view.getParent();
                 GridView owner2 = (GridView)owner.getParent();
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
                     owner.removeView(view);
                     FrameLayout container = (FrameLayout) v;
                     container.addView(view);
                     view.setVisibility(View.VISIBLE);
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
