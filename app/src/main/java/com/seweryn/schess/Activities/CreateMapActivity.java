package com.seweryn.schess.Activities;

/**
 * Created by sew on 2015-11-20.
 */
import android.app.Activity;
import android.app.ProgressDialog;
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
import com.seweryn.schess.Controllers.IBoardLogicController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Controllers.SCBoardLogicController;
import com.seweryn.schess.Controllers.SCDatabaseContextController;
import com.seweryn.schess.Dialogs.PuzzleHardnessClasificationDialog;
import com.seweryn.schess.Dialogs.PuzzleHardnessDialog;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.IPuzzleTypeCalsificator;
import com.seweryn.schess.SearchAlgoritm.DFSTree;
import com.seweryn.schess.SearchAlgoritm.ISearchTree;
import com.seweryn.schess.SearchAlgoritm.PuzzleTypeCalsificator;
import com.seweryn.schess.R;
import com.seweryn.schess.SearchAlgoritm.SolutionFinder;
import com.seweryn.schess.Static.Lodash;


public class CreateMapActivity extends Activity {
    private  LayoutInflater boardLayoutInflater;
    private Context context;
    private PopupWindow pwindo;
    private IDatabaseContextController databaseContextController;
    private IBoardLogicController boardLogiController;
    private IPuzzleTypeCalsificator puzzleTypeCalsificator;
    private  ProgressDialog ringProgressDialog;
    private final  int defaltWidth =4;
    private final  int defaltHeight =4;
    private int boardWidth;
    private int boardHeight;

    public  CreateMapActivity(){
        context =this;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_map_main);
        final GridView gridView = (GridView) findViewById(R.id.createMapGridView);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        getBoardSizeFromExtras();
        gridView.setNumColumns(boardWidth);
        injectControllers();
        final CreateMapAdapter gridViewAdapter = new CreateMapAdapter(this,boardLogiController,boardWidth,boardHeight);
        gridView.setAdapter(gridViewAdapter);
        gridViewAdapter.notifyDataSetChanged();
        boardLayoutInflater = LayoutInflater.from(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                    try {
                        View layout = boardLayoutInflater.inflate(R.layout.add_piece_popup,(ViewGroup)v.findViewById(R.id.popup));
                        int popupWindowWidth =Lodash.dpToPx(220, getBaseContext());
                        int popupWindowHeight =Lodash.dpToPx(400,getBaseContext());
                        pwindo = new PopupWindow(layout, popupWindowWidth, popupWindowHeight, true);
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    showRingProgressDialog();
                    ISearchTree handler = SolutionFinder.findSolution(new DFSTree(gridViewAdapter.boardLogicController.getBoard()));
                    closeRingProgressDialog();
                    if (handler.getNumberOfResults() <= 0) {
                        shnoNoSolutionDialog();
                    }
                    else {
                        PuzzleType type = puzzleTypeCalsificator.clasify(handler.getNumberOfResults(),handler.getTreeWidth(),handler.getTreeLeaves());
                        showClasificationDialog(type);
                        databaseContextController.save(type, gridViewAdapter.boardLogicController.getBoard());

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void injectControllers() {
        puzzleTypeCalsificator = new PuzzleTypeCalsificator();
        boardLogiController = new SCBoardLogicController().getBoardLogicController();
        databaseContextController = new SCDatabaseContextController().getDatabaseContextContrller(this);
    }

    private  void getBoardSizeFromExtras(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boardHeight = (int)extras.get("BoardHeight");
            boardWidth = (int)extras.get("BoardWidth");

        }
    }
    private void showClasificationDialog(PuzzleType type) {
        PuzzleHardnessClasificationDialog dialog = new
                PuzzleHardnessClasificationDialog();
        dialog.setPuzleType(type.toString());
        dialog.show(getFragmentManager(), "Board was classified");
    }

    private void shnoNoSolutionDialog() {
        PuzzleHardnessDialog dialog = new
                PuzzleHardnessDialog();
        dialog.show(getFragmentManager(), "There is no solution for this board");
    }
    private void showRingProgressDialog(){

            ringProgressDialog = ProgressDialog.show(CreateMapActivity.this, "Please wait ...", "Downloading Image ...", true);
            ringProgressDialog.setCancelable(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Here you should write your time consuming task...
                        // Let the progress ring for 10 seconds...
                        Thread.sleep(10000);
                    } catch (Exception e) {
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();
        }
    private void closeRingProgressDialog(){
        ringProgressDialog.dismiss();
    }
}

