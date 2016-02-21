package com.seweryn.schess.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.seweryn.schess.R;
import com.seweryn.schess.Activities.MainMenuActivity;

/**
 * Created by sew on 2015-12-21.
 */
public class PuzzleHardnessClasificationDialog  extends DialogFragment {
    private String puzzleType;
    public void setPuzleType(String _puzzleType){

        puzzleType = _puzzleType;
    }
    public PuzzleHardnessClasificationDialog(){

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your puzzle was clasified as" + puzzleType)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        startActivity(new Intent(getActivity(),MainMenuActivity.class));

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}
