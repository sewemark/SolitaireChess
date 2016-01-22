package com.seweryn.schess.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seweryn.schess.Models.DataContainer;
import com.seweryn.schess.R;

import java.util.ArrayList;

/**
 * Created by sew on 2016-01-18.
 */
public class ChoosMapListViewAdapter extends ArrayAdapter<DataContainer> {
    private Activity context;
    ArrayList<DataContainer> tabsInfo;
    public ChoosMapListViewAdapter(Activity context, ArrayList<DataContainer> _tabsInfo) {
        super(context, R.layout.choose_map_listview_item,_tabsInfo);
        this.context = context;
        this.tabsInfo =_tabsInfo;
    }
    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.choose_map_listview_item, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.boardName);
        ImageView wasSolvedImage = (ImageView) rowView.findViewById(R.id.wasSolved);

        txtTitle.setText(tabsInfo.get(position).boardname);
        if(tabsInfo.get(position).wasSolved){
            wasSolvedImage.setBackgroundResource(R.drawable.checked);
        }else{
            wasSolvedImage.setBackgroundResource(0);
        }
        return rowView;

    };

}
