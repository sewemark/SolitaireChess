package com.seweryn.schess.Activities;

/**
 * Created by sew on 2015-11-20.
 */
import android.app.Activity;
import android.content.Context;
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
import com.seweryn.schess.Controllers.DatabaseContextController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Dialogs.PuzzleHardnessClasificationDialog;
import com.seweryn.schess.Dialogs.PuzzleHardnessDialog;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.DFSTree;
import com.seweryn.schess.ISearchTree;
import com.seweryn.schess.R;
import com.seweryn.schess.SolutionFinder;


public class CreateMapActivity extends Activity {
    private  LayoutInflater boardLayoutInflater;
    private PopupWindow pwindo;
    private IDatabaseContextController databaseContextController;
    Context context;
    public  CreateMapActivity(){
        context =this;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_map_main);
        databaseContextController = new DatabaseContextController(this);

        final GridView gridView = (GridView) findViewById(R.id.createMapGridView);
        Bundle extras = getIntent().getExtras();
        int boardWidth=4;
        int boardHeight=4;
        if (extras != null) {
            boardHeight = (int)extras.get("BoardHeight");
            boardWidth = (int)extras.get("BoardWidth");

        }
        gridView.setNumColumns(boardWidth);
        final CreateMapAdapter gridViewAdapter = new CreateMapAdapter(this,boardWidth,boardHeight);
        gridView.setAdapter(gridViewAdapter);
        try {
            gridViewAdapter.setBoardToCreate(new int[boardHeight][boardWidth]);
            gridViewAdapter.notifyDataSetChanged();
        }
        catch (Exception e){

        }
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
                                gridViewAdapter.setPieceOnPosition(position, i);
                                gridViewAdapter.notifyDataSetChanged();
                                //  Toast.makeText(CreateMapActivity.this, "myPos " + i, Toast.LENGTH_LONG).show();
                            }
                        });
                         Button btnClosePopup = (Button) layout.findViewById(R.id.popupCloseButton);
                        // btnClosePopup.setOnClickListener(cancel_button_click_listener);
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
        Button savePuzzleButton = (Button) findViewById(R.id.savePuzzleButton);
        // btnClosePopup.setOnClickListener(cancel_button_click_listener);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    ISearchTree handler= SolutionFinder.findSolution(new DFSTree(gridViewAdapter.getCreateBoard()));

                    int solutionNumber = handler.getNumberOfResults();
                    System.out.println("solution number " + solutionNumber);
                    System.out.println(handler.getTreeLeaves());

                    if(solutionNumber<=0){
                        System.out.println("Nie ma rozwiazania");
                        PuzzleHardnessDialog dialog = new
                                PuzzleHardnessDialog();
                        dialog.show(getFragmentManager(),"dialog");
                    }
                    else{
                        double wage = solutionNumber *0.3 + handler.getTreeWidth()*0.4 + handler.getTreeHeight()*0.3;
                        PuzzleType type;
                        if(wage<=10){
                            type= PuzzleType.EASY;
                        }else if(wage>10 && wage<=14){
                            type=PuzzleType.MEDIUM;
                        }else if(wage<16 && wage>14){
                            type= PuzzleType.HARD;
                        }else{
                            type = PuzzleType.VERYHARD;
                        }
                        PuzzleHardnessClasificationDialog dialog = new
                                PuzzleHardnessClasificationDialog();
                        dialog.setPuzleType(type.toString());
                        dialog.show(getFragmentManager(), "dialog");

                        databaseContextController.save(type, gridViewAdapter.getCreateBoard());

                    }
                }
                catch(Exception e){
                }
            }
        });

        savePuzzleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                databaseContextController.save(PuzzleType.EASY, gridViewAdapter.getCreateBoard());
            }
        });

    }
}

