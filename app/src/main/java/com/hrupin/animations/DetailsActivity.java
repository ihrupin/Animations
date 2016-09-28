package com.hrupin.animations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "EXTRA_ITEM";
    private ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ivPreview = (ImageView) findViewById(R.id.iv_preview);
    }
}
