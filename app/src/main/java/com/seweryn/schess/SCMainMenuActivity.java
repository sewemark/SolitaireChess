package com.seweryn.schess;

/**
 * Created by sew on 2015-10-25.
 */

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;
        import android.view.View.OnClickListener;

public class SCMainMenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceeState) {
        super.onCreate(savedInstanceeState);
        setContentView(R.layout.menu_main);
        Button quickPlayButton = (Button) findViewById(R.id.Button02);
        Button createMapButton = (Button) findViewById(R.id.Button04);
        quickPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(SCMainMenuActivity.this, GameActivity.class);
                SCMainMenuActivity.this.startActivity(mainMenu);
                SCMainMenuActivity.this.finish();
            }
        });
        createMapButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(SCMainMenuActivity.this, GameActivity.class);
                SCMainMenuActivity.this.startActivity(mainMenu);
                SCMainMenuActivity.this.finish();

            }
        });

    }

}