package com.seweryn.schess;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.seweryn.schess.Activities.GameActivity;
import com.seweryn.schess.Adapters.ChoosMapListViewAdapter;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DataContainer;
import com.seweryn.schess.Models.DatabaseObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sew on 2016-01-16.
 */
public class PuzzleTypeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.puzzletype_fragment, container, false);

        ListView easyGridView = (ListView)rootView.findViewById(R.id.chooseMapList);
      //  String[] easyPuzzles = controller.getPuzzleListByType(PuzzleType.EASY);
        //this.helperFunction(easyPuzzles,"E");
        //String[] mediumPuzzles = controller.getPuzzleListByType(PuzzleType.MEDIUM);
        //this.helperFunction(mediumPuzzles, "M");
        DatabaseObject[] databaseObjects = (DatabaseObject[])getArguments().getSerializable("list");
        if(databaseObjects!=null) {
            ArrayList<DataContainer> lista = new ArrayList<DataContainer>();
            //lista.add(new DataContainer("",false));
            for(int i =0;i<databaseObjects.length;i++){
                lista.add(new DataContainer(databaseObjects[i].getFileName(), databaseObjects[i].isPuzzleSolved(),databaseObjects[i].wasHintsUsed()));
            }

            ChoosMapListViewAdapter adapter = new ChoosMapListViewAdapter(this.getActivity(),lista);
            easyGridView.setAdapter(adapter);
            easyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                FragmentActivity fra = getActivity();
                Intent chooseBoard = new Intent(getActivity(), GameActivity.class);
                TextView boardNameTextView = (TextView)v.findViewById(R.id.boardName);
                chooseBoard.putExtra("boardName", (boardNameTextView.getText()));
               String val = getArguments().getString("type");
                chooseBoard.putExtra("boardType", getArguments().getString("type"));;

                getActivity().startActivity(chooseBoard);
                startActivity(chooseBoard);

                }
            });
        }
        return rootView;
    }

}
