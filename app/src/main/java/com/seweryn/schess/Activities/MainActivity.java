package com.seweryn.schess.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Handler;

import com.seweryn.schess.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Thread(){
            @Override
             public void run() {
                Intent mainMenu = new Intent(MainActivity.this, SCMainMenuActivity.class);
                MainActivity.this.startActivity(mainMenu);
                MainActivity.this.finish();
                overridePendingTransition(R.animator.fadein, R.animator.fadeout);

                  }
            },4000);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
