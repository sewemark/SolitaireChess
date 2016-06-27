package com.seweryn.schess.Fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.seweryn.schess.Activities.GameActivity;
import com.seweryn.schess.Adapters.ChoosMapListViewAdapter;
import com.seweryn.schess.Models.ViewModels.DataContainer;
import com.seweryn.schess.Models.DatabaseObject;
import com.seweryn.schess.R;

import java.util.ArrayList;

/**
 * Created by sew on 2016-01-16.
 */
public class PuzzleTypeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.puzzletype_fragment, container, false);
        ListView easyGridView = (ListView)rootView.findViewById(R.id.chooseMapList);

        DatabaseObject[] databaseObjects = (DatabaseObject[])getArguments().getSerializable("list");
        if(databaseObjects!=null) {
            ArrayList<DataContainer> lista = new ArrayList<DataContainer>();
            for(int i =0;i<databaseObjects.length;i++){
                lista.add(new DataContainer(databaseObjects[i].getFileName(), databaseObjects[i].isPuzzleSolved(),databaseObjects[i].wasHintsUsed()));
            }

            ChoosMapListViewAdapter adapter = new ChoosMapListViewAdapter(this.getActivity(),lista);
            easyGridView.setAdapter(adapter);
            easyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                Intent chooseBoard = new Intent(getActivity(), GameActivity.class);
                TextView boardNameTextView = (TextView)v.findViewById(R.id.boardName);
                chooseBoard.putExtra("boardName", (boardNameTextView.getText()));
                chooseBoard.putExtra("boardType", getArguments().getString("type"));;
                getActivity().startActivity(chooseBoard);
                startActivity(chooseBoard);

                }
            });
        }
        return rootView;
    }

}
