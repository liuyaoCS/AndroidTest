package com.example.helper;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class ExpandCollapseHelper {

	public static void animateCollapsing(final View view) {
		int origHeight = view.getHeight();

		ValueAnimator animator = createHeightAnimator(view, origHeight, 0);
		animator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationEnd(Animator animator) {
				view.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		animator.start();
	}

	public static void animateExpanding(final View view) {
		view.setVisibility(View.VISIBLE);

		final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(widthSpec, heightSpec);
		

		ValueAnimator animator = createHeightAnimator(view, 0, view.getMeasuredHeight());
		animator.start();
	}

	public static ValueAnimator createHeightAnimator(final View view, int start, int end) {
		ValueAnimator animator = ValueAnimator.ofInt(start, end);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();

				ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
				layoutParams.height = value;
				Log.i("ly", "height="+layoutParams.height);
				view.setLayoutParams(layoutParams);
				//view.invalidate();
			}
		});
		animator.setDuration(500);
		return animator;
	}
	}