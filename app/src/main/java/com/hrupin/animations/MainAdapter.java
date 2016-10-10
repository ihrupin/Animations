package com.hrupin.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hrupin.animations.domain.DishItem;

import java.util.List;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements View.OnClickListener {

    private Activity mActivity;
    private List<DishItem> mDataSet;
    private static final float DEFAULT_SCALE_FROM = 3f;
    private float mFrom = DEFAULT_SCALE_FROM;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mLastPosition = -1;
    private boolean isFirstOnly = true;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public MainAdapter(Activity activity, List<DishItem> dataSet) {
        mActivity = activity;
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mActivity).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DishItem item = mDataSet.get(position);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mActivity != null && item != null && holder != null) {
                    Glide.with(mActivity).load(item.getImageUrl()).into(holder.image);
                    holder.root.setTag(item);
                    holder.name.setText(item.getName());
                    holder.time.setText(item.getTime());
                    holder.root.setOnClickListener(MainAdapter.this);

                    int adapterPosition = holder.getAdapterPosition();
                    if (!isFirstOnly || adapterPosition > mLastPosition) {
                        getAnimatorsScale(holder).start();
                        mLastPosition = adapterPosition;
                    } else {
                        clear(holder.itemView);
                    }
                }
            }
        }, position * 100);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void remove(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void add(DishItem item, int position) {
        mDataSet.add(position, item);
        notifyItemInserted(position);
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setStartPosition(int start) {
        mLastPosition = start;
    }

    private AnimatorSet getAnimatorsScale(final ViewHolder holder) {
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(mInterpolator);
        ObjectAnimator flipY = ObjectAnimator.ofFloat(holder.itemView, "rotationX", 180.0f, 0f);
        flipY.setDuration(300);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(150);
        valueAnimator.setInterpolator(mInterpolator);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(holder != null){
                    holder.image.setVisibility(View.VISIBLE);
                    holder.time.setVisibility(View.VISIBLE);
                    holder.name.setVisibility(View.VISIBLE);
                    holder.ivStatus.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.playTogether(flipY, valueAnimator);

        return set;
    }
    public void setFirstOnly(boolean firstOnly) {
        isFirstOnly = firstOnly;
    }

    @Override
    public void onClick(View v) {
        ImageView iv = (ImageView) (v.findViewById(R.id.image));
        Intent intent = new Intent(mActivity, DetailsActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, iv, "preview");
        mActivity.startActivity(intent, options.toBundle());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public ImageView ivStatus;
        public TextView time;
        public TextView name;
        public ConstraintLayout root;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            time = (TextView) itemView.findViewById(R.id.time);
            name = (TextView) itemView.findViewById(R.id.name);
            root = (ConstraintLayout) itemView.findViewById(R.id.item_root);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_in_transit);
        }
    }

    public static void clear(View v) {
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
    }
}
