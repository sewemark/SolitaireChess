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
import android.widget.TextView;

import com.seweryn.schess.Activities.GameActivity;
import com.seweryn.schess.Enums.PuzzleType;
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

        GridView easyGridView = (GridView)rootView.findViewById(R.id.chooseMapGridView);
      //  String[] easyPuzzles = controller.getPuzzleListByType(PuzzleType.EASY);
        //this.helperFunction(easyPuzzles,"E");
        //String[] mediumPuzzles = controller.getPuzzleListByType(PuzzleType.MEDIUM);
        //this.helperFunction(mediumPuzzles, "M");
        DatabaseObject[] databaseObjects = (DatabaseObject[])getArguments().getSerializable("list");
        if(databaseObjects!=null) {
            ArrayList<String> lista = new ArrayList<String>();
            for(int i =1;i<=databaseObjects.length;i++){
                lista.add(String.valueOf(i));
            }

            //lista.addAll(0, Arrays.asList(easyPuzzles));
            //lista.addAll(Arrays.asList(mediumPuzzles));
            // String[] easyPuzzles = new String[]{"bla","bla","bla"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, lista);
            easyGridView.setAdapter(adapter);
            easyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                FragmentActivity fra = getActivity();
                Intent chooseBoard = new Intent(getActivity(), GameActivity.class);

                chooseBoard.putExtra("boardName", ((TextView) v).getText() + String.valueOf(getArguments().getChar("puzzleType")));
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
