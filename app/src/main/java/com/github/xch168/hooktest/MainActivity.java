package com.github.xch168.hooktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HookHelper.hookActivityInstrumentation(this);
        HookHelper.hookContextInstrumentation();

        Intent intent = new Intent(this, DetailActivity.class);
        getApplication().startActivity(intent);

    }
}
