package io.github.davinci42.pagescrolling;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by davinci42 on 2016/1/11.
 */
public class PageScrollingIndicator extends View {

	Paint mTransPaint;
	Paint mWhitePaint;
	int distance = 200;
	int radius = 30;
	int count = 3;
	int mCurrentIndex = 0;
	int mNewIndex = 0;
	float mPageOffset;

	float oldX;
	float newX;

	public PageScrollingIndicator(Context context) {
		super(context);
		init();
	}

	public PageScrollingIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void setUpWithViewPager(final ViewPager viewPager) {

		if (viewPager != null) {

			viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
					mCurrentIndex = viewPager.getCurrentItem();
					mNewIndex = position;
					mPageOffset = positionOffset;
					postInvalidate();
				}

				@Override public void onPageSelected(int position) {
				}

				@Override public void onPageScrollStateChanged(int state) {
				}
			});

			if (viewPager.getAdapter() != null) {
				count = viewPager.getAdapter().getCount();
			}
		}
	}

	private void init() {
		mTransPaint = new Paint();
		mTransPaint.setStyle(Paint.Style.FILL);
		mTransPaint.setAntiAlias(true);
		mTransPaint.setAlpha(30);

		mWhitePaint = new Paint();
		mWhitePaint.setStyle(Paint.Style.FILL);
		mWhitePaint.setAntiAlias(true);
		mWhitePaint.setColor(Color.WHITE);
	}

	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = 2 * distance + radius * 2;
		int height = radius * 2;
		setMeasuredDimension(width, height);
	}

	@Override protected void onDraw(Canvas canvas) {

		for (int i = 0; i < count; i++) {
			canvas.drawCircle(radius + i * distance, radius, radius, mTransPaint);
		}

		float currentXCenter = radius + mCurrentIndex * distance;

		if (mPageOffset != 0) {
			if (mCurrentIndex == mNewIndex) {
				// sliding right
				oldX = currentXCenter + distance * mPageOffset * mPageOffset;
				newX = currentXCenter + distance * mPageOffset;
			} else {
				// sliding left
				oldX = currentXCenter - distance * (1 - mPageOffset) * (1 - mPageOffset);
				newX = currentXCenter - distance * (1 - mPageOffset);
			}
			canvas.drawCircle(oldX, radius, radius, mWhitePaint);
			canvas.drawCircle(newX, radius, radius, mWhitePaint);
			canvas.drawRect(Math.min(oldX, newX), 0, Math.max(oldX, newX), radius * 2, mWhitePaint);
		} else {
			// stil
			canvas.drawCircle(radius + mNewIndex * distance, radius, radius, mWhitePaint);
		}
	}
}
