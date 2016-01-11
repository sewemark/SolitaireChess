package com.seweryn.schess.Adapters;

/**
 * Created by sew on 2015-11-21.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.R;
import com.seweryn.schess.Static.Lodash;

public class CreateMapPopupListViewAdapter  extends ArrayAdapter<PieceType> {
        private final Context context;
        private final PieceType[] values;

        public CreateMapPopupListViewAdapter(Context context, PieceType[] values) {
            super(context, R.layout.add_piece_popup_item, values);
            this.context = context;
            this.values = values;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.add_piece_popup_item, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.label);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView.setText(values[position].toString());
            imageView.setImageResource(Lodash.getResource(values[position]));
            return rowView;
        }
}
