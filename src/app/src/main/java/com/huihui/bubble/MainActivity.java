package com.huihui.bubble;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private View.OnTouchListener onTouchListener = new TouchListenerConn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestView testView = (TestView) findViewById(R.id.testview);
        testView.setOnTouchListener(onTouchListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class TouchListenerConn implements View.OnTouchListener {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d(TAG, "onTouch() returned: " + true);
                    break;
                case MotionEvent.ACTION_UP:
                    TestView testView = (TestView) findViewById(R.id.testview);
                    testView.testTouch(event.getX(), event.getY());
                    Log.d(TAG, event.getX() + ":" + event.getY());
                    break;
            }
            return true;
        }
    }
}
