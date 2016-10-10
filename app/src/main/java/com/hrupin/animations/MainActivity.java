package com.hrupin.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

import com.hrupin.animations.domain.MockData;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

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
        appBarLayout.setVisibility(View.VISIBLE);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("PICKING"));
        tabLayout.addTab(tabLayout.newTab().setText("DELIVERED"));
        tabLayout.addTab(tabLayout.newTab().setText("IN TRANSIT"));
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, android.R.color.transparent));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                animateAppBarLayout();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void animateBicycle() {
        if(ivBicycle != null && tabLayout != null && tabIndicationViewForAnimation != null) {
            int tabWidth = tabLayout.getWidth() / 4;

            ObjectAnimator transitionIndicator = ObjectAnimator.ofFloat(tabIndicationViewForAnimation, "translationX", -tabWidth, 0);
            transitionIndicator.setInterpolator(new LinearInterpolator());
            transitionIndicator.setDuration(300);

            ObjectAnimator transitionBike = ObjectAnimator.ofFloat(ivBicycle, "translationX", 0, tabWidth);
            transitionBike.setInterpolator(new LinearInterpolator());
            transitionBike.setDuration(300);

            ObjectAnimator rotation1 = ObjectAnimator.ofFloat(ivBicycle, "rotation", 0, 25);
            rotation1.setDuration(300);
            rotation1.setStartDelay(100);
            rotation1.setInterpolator(new LinearInterpolator());

            ObjectAnimator rotation2 = ObjectAnimator.ofFloat(ivBicycle, "rotation", 25, 0);
            rotation2.setDuration(400);
            rotation2.setStartDelay(300);
            rotation2.setInterpolator(new LinearInterpolator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(transitionBike, transitionIndicator, rotation1, rotation2);
            animatorSet.addListener(new Animator.AnimatorListener() {
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
                        adapter.setFirstOnly(false);
                        adapter.setDuration(400);
                        adapter.setInterpolator(new OvershootInterpolator(.5f));
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
            animatorSet.start();
        }
    }

    private void animateAppBarLayout() {
        if(appBarLayout != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(appBarLayout, "translationY", -appBarLayout.getHeight(), 0);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(300);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (appBarLayout != null) {
                        appBarLayout.setVisibility(View.VISIBLE);
                    }
                    if (recyclerView != null) {
                        recyclerView.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animateBicycle();
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
