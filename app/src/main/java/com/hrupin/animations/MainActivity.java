package com.hrupin.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;

import com.hrupin.animations.domain.DishItem;
import com.hrupin.animations.domain.MockData;

import java.util.ArrayList;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private ImageView ivBicycle;
    private View tabIndicationViewForAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        ivBicycle = (ImageView)findViewById(R.id.iv_bicycle);
        tabIndicationViewForAnimation = findViewById(R.id.tab_indication_view_for_animation);

        appBarLayout = (AppBarLayout)findViewById(R.id.app_bar_layout);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("PICKING"));
        tabLayout.addTab(tabLayout.newTab().setText("DELIVERED"));
        tabLayout.addTab(tabLayout.newTab().setText("IN TRANSIT"));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, android.R.color.transparent));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                animateAppBarLayout();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void animateAppBarLayout() {
        if(appBarLayout != null && ivBicycle != null && tabLayout != null && tabIndicationViewForAnimation != null) {
            int tabWidth = tabLayout.getWidth() / 4;
            LinearInterpolator mLinearInterpolator = new LinearInterpolator();
            ObjectAnimator animator = ObjectAnimator.ofFloat(appBarLayout, "translationY", -appBarLayout.getHeight(), 0);
            animator.setInterpolator(mLinearInterpolator);
            animator.setDuration(300);

            ObjectAnimator transitionIndicator = ObjectAnimator.ofFloat(tabIndicationViewForAnimation, "translationX", -tabWidth, 0);
            transitionIndicator.setInterpolator(mLinearInterpolator);
            transitionIndicator.setDuration(300);
            transitionIndicator.setStartDelay(300);

            ObjectAnimator bikeMove = ObjectAnimator.ofFloat(ivBicycle, "translationX", 0, tabWidth);
            bikeMove.setInterpolator(mLinearInterpolator);
            bikeMove.setDuration(300);
            bikeMove.setStartDelay(300);

            ObjectAnimator bikeUp = ObjectAnimator.ofFloat(ivBicycle, "rotation", 0, 25);
            bikeUp.setDuration(300);
            bikeUp.setStartDelay(400);
            bikeUp.setInterpolator(mLinearInterpolator);

            ObjectAnimator bikeDown = ObjectAnimator.ofFloat(ivBicycle, "rotation", 25, 0);
            bikeDown.setDuration(400);
            bikeDown.setStartDelay(600);
            bikeDown.setInterpolator(mLinearInterpolator);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator, transitionIndicator, bikeMove, bikeUp, bikeDown);
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (ivBicycle != null) {
                        ivBicycle.setVisibility(View.INVISIBLE);
                    }
                    if (tabIndicationViewForAnimation != null) {
                        tabIndicationViewForAnimation.setVisibility(View.INVISIBLE);
                    }
                    if (appBarLayout != null) {
                        appBarLayout.setVisibility(View.VISIBLE);
                    }
                    if(tabLayout != null){
                        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(tabLayout.getContext(), R.color.tab_indicator_color));
                    }
                    if (recyclerView != null) {
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new MainAdapter(MainActivity.this, MockData.getDishList());
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            set.start();
        }
    }
}
