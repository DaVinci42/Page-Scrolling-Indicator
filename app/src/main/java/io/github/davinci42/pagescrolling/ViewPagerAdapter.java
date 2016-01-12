package io.github.davinci42.pagescrolling;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by davinci42 on 2016/1/11.
 */
public class ViewPagerAdapter extends PagerAdapter {

	List<View> mViewList;

	public ViewPagerAdapter(List<View> views) {
		mViewList = views;
	}

	@Override public Object instantiateItem(ViewGroup container, int position) {
		View view = mViewList.get(position);
		container.addView(view);
		return view;
	}

	@Override public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override public int getCount() {
		return mViewList.size();
	}
}
