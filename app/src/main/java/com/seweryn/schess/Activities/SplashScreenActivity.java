package com.seweryn.schess.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Handler;

import com.seweryn.schess.R;

public class SplashScreenActivity extends AppCompatActivity {
    /**
     * overridden onCreate method that
     * set up control adapters
     * and sets UI event handlers
     * @param  savedInstanceeState bundle
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Thread(){
            @Override
             public void run() {
                Intent mainMenu = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
                SplashScreenActivity.this.startActivity(mainMenu);
                SplashScreenActivity.this.finish();
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
