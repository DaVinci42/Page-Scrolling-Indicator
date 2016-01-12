package io.github.davinci42.pagescrolling;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Bind(R.id.vp) ViewPager mVp;
	@Bind(R.id.psi) PageScrollingIndicator mPsi;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		View view01 = LayoutInflater.from(this).inflate(R.layout.layout_view_pager, mVp, false);
		view01.findViewById(R.id.tv).setBackgroundColor(Color.parseColor("#90caf9"));
		View view02 = LayoutInflater.from(this).inflate(R.layout.layout_view_pager, mVp, false);
		view02.findViewById(R.id.tv).setBackgroundColor(Color.parseColor("#42a5f5"));
		View view03 = LayoutInflater.from(this).inflate(R.layout.layout_view_pager, mVp, false);
		view03.findViewById(R.id.tv).setBackgroundColor(Color.parseColor("#1e88e5"));

		List<View> viewList = new ArrayList<>();
		viewList.add(view01);
		viewList.add(view02);
		viewList.add(view03);
		ViewPagerAdapter adapter = new ViewPagerAdapter(viewList);
		mVp.setAdapter(adapter);

		mPsi.setUpWithViewPager(mVp);
	}
}
