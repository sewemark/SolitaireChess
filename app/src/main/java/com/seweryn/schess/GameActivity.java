package com.seweryn.schess;

import android.app.Activity;

/**
 * Created by sew on 2015-10-25.
 */

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class GameActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard_main);


        // findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
        // findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
        // findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
        // findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());
        // findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
        // findViewById(R.id.topright).setOnDragListener(new MyDragListener());
        // findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
        // findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
        // ImageView myImage = (ImageView) findViewById(R.id.myimage1);
        // myImage.setAlpha(127);*/
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setNumColumns(4);
        gridView.setAdapter(new GameBoardAdapter(this, 4));


    }
}