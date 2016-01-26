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


    public IBoardLogicController boardLogicController;
    private final Context context;
    private int width;
    private int height;
    public CreateMapAdapter(Context context,IBoardLogicController _boardLogicController, int _width, int _height) {
        super(context,_boardLogicController, _width,_height);
        boardLogicController = _boardLogicController;
        this.context = context;
        this.width = _width;
        this.height = _height;
        boardLogicController.setBoard(new int[height][width]);
        boardLogicController.initializeBoard();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return super.getView(i, view,viewGroup);
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

