package com.hrupin.animations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hrupin.animations.domain.DishItem;
import com.hrupin.animations.domain.MockData;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class DetailsActivity extends AppCompatActivity {
    private ImageView ivPreview;
    private TextView tvName;
    private TextView tvTime;
    private DishItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        item = MockData.getItem();

        ivPreview = (ImageView) findViewById(R.id.iv_preview);
        Glide.with(this).load(item.getImageUrl()).into(ivPreview);
        tvName = (TextView) findViewById(R.id.name);
        tvName.setText(item.getName());
        tvTime = (TextView) findViewById(R.id.time);
        tvTime.setText(item.getTime());

    }
}
