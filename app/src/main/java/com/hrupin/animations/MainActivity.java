package com.hrupin.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.hrupin.animations.domain.MockData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_sample);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        appBarLayout = (AppBarLayout)findViewById(R.id.app_bar_layout);
        appBarLayout.setVisibility(View.VISIBLE);
        appBarLayout.post(new Runnable() {
            @Override
            public void run() {
                animateDiagonalPan(appBarLayout);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("PICKING"));
        tabLayout.addTab(tabLayout.newTab().setText("DELIVERED"));
        tabLayout.addTab(tabLayout.newTab().setText("IN TRANSIT"));
        tabLayout.setOnTabSelectedListener(this);

        adapter = new MainAdapter(this, MockData.getDishList());
        adapter.setFirstOnly(false);
        adapter.setDuration(400);
        adapter.setInterpolator(new OvershootInterpolator(.5f));

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void animateDiagonalPan(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", -v.getHeight(), 0);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(300);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(appBarLayout != null){
                    appBarLayout.setVisibility(View.VISIBLE);
                }
                if(recyclerView != null){
                    recyclerView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(appBarLayout != null){
                    appBarLayout.setVisibility(View.VISIBLE);
                }
                if(recyclerView != null){
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.start();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
