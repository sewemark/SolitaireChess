package com.seweryn.schess.Activities;

/**
 * Created by sew on 2015-11-20.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.seweryn.schess.Adapters.CreateMapAdapter;
import com.seweryn.schess.Adapters.CreateMapPopupListViewAdapter;
import com.seweryn.schess.Controllers.BoardLogicController;
import com.seweryn.schess.Controllers.DatabaseContextController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Dialogs.PuzzleHardnessClasificationDialog;
import com.seweryn.schess.Dialogs.PuzzleHardnessDialog;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.DFSTree;
import com.seweryn.schess.ISearchTree;
import com.seweryn.schess.PuzzleTypeCalsificator;
import com.seweryn.schess.R;
import com.seweryn.schess.SolutionFinder;
import com.seweryn.schess.Static.IPuzzleTypeCalsificator;


public class CreateMapActivity extends Activity {
    private  LayoutInflater boardLayoutInflater;
    private PopupWindow pwindo;
    private IDatabaseContextController databaseContextController;
    private IPuzzleTypeCalsificator puzzleTypeCalsificator;
    Context context;
    public  CreateMapActivity(){
        context =this;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_map_main);
        databaseContextController = new DatabaseContextController(this);
        puzzleTypeCalsificator = new PuzzleTypeCalsificator();
        final GridView gridView = (GridView) findViewById(R.id.createMapGridView);
        Bundle extras = getIntent().getExtras();
        int boardWidth=4;
        int boardHeight=4;
        if (extras != null) {
            boardHeight = (int)extras.get("BoardHeight");
            boardWidth = (int)extras.get("BoardWidth");

        }
        gridView.setNumColumns(boardWidth);
        final CreateMapAdapter gridViewAdapter = new CreateMapAdapter(this,new BoardLogicController(),boardWidth,boardHeight);
        gridView.setAdapter(gridViewAdapter);
        gridViewAdapter.notifyDataSetChanged();
        boardLayoutInflater = LayoutInflater.from(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                    try {
                        View layout = boardLayoutInflater.inflate(R.layout.add_piece_popup,(ViewGroup)v.findViewById(R.id.popup));
                        pwindo = new PopupWindow(layout, 300, 370, true);
                        ListView popupListView = (ListView)layout.findViewById(R.id.addPieceListView);
                        CreateMapPopupListViewAdapter listViewAdapter = new CreateMapPopupListViewAdapter(CreateMapActivity.this,PieceType.values());
                        popupListView.setAdapter(listViewAdapter);
                        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
                        popupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                                gridViewAdapter.boardLogicController.setPieceAtPosition(position, i);
                                gridViewAdapter.notifyDataSetChanged();

                            }
                        });
                        Button btnClosePopup = (Button) layout.findViewById(R.id.popupCloseButton);
                        btnClosePopup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pwindo.dismiss();

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    ISearchTree handler = SolutionFinder.findSolution(new DFSTree(gridViewAdapter.boardLogicController.getBoard()));
                    int solutionNumber = handler.getNumberOfResults();
                    System.out.println("solution number " + solutionNumber);
                    System.out.println(handler.getTreeLeaves());

                    if (solutionNumber <= 0) {
                        System.out.println("Nie ma rozwiazania");
                        PuzzleHardnessDialog dialog = new
                                PuzzleHardnessDialog();
                        dialog.show(getFragmentManager(), "dialog");
                    } else {
                        PuzzleType type = puzzleTypeCalsificator.clasify(solutionNumber,handler.getTreeWidth(),handler.getTreeLeaves());
                        PuzzleHardnessClasificationDialog dialog = new
                                PuzzleHardnessClasificationDialog();
                        dialog.setPuzleType(type.toString());
                        dialog.show(getFragmentManager(), "dialog");
                        databaseContextController.save(type, gridViewAdapter.boardLogicController.getBoard());

                    }
                } catch (Exception e) {
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

