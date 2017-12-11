package com.jk.progressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jk.progressview.view.ProgressView;

public class MainActivity extends AppCompatActivity {

    private ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        progressView = ((ProgressView) findViewById(R.id.progressView));
        progressView.setDuration(6000);
        progressView.start();
    }
}
