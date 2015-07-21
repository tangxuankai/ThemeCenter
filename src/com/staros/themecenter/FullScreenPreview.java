package com.staros.themecenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FullScreenPreview extends Activity{
	private ViewPager  mViewPager;  //对应的viewPager  
	private List<ImageView> mViewList;//view数组 
	private int[] mImgIds;
	private LayoutInflater mInflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_creen_preview);
		mInflater  = LayoutInflater.from(this);
		
		initData();
	}
	private void initData() {
		// TODO Auto-generated method stub
		mImgIds    = new int[] {R.drawable.a, R.drawable.b, R.drawable.c,
	             R.drawable.d, R.drawable.e};
		mViewList  = new ArrayList<ImageView>();
		mViewPager = (ViewPager) findViewById(R.id.vp_full_sreen); 

		for (int i = 0; i < mImgIds.length; i++){
	        View view = mInflater.inflate(R.layout.viewpager_item,  
	        		mViewPager, false);
	        ImageView img = (ImageView)view.findViewById(R.id.iv_viewpaer_item);
			img.setImageResource(mImgIds[i]);
			if (i == 0){
				img.setTransitionName("transition_share");
			} else {
				img.setTransitionName("transition_share" + i);
			}
			mViewList.add(img);
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		PagerAdapter pagerAdapter = new PagerAdapter() {  
		      
		    @Override  
		    public boolean isViewFromObject(View arg0, Object arg1) {  
		        // TODO Auto-generated method stub  
		        return arg0 == arg1;  
		    }  
		      
		    @Override  
		    public int getCount() {  
		        // TODO Auto-generated method stub  
		        return mViewList.size();  
		    }  
		      
		    @Override  
		    public void destroyItem(ViewGroup container, int position,  
		            Object object) {  
		        // TODO Auto-generated method stub  
		        container.removeView(mViewList.get(position));  
		    }  
		      
		    @Override  
		    public Object instantiateItem(ViewGroup container, int position) {  
		        // TODO Auto-generated method stub  
		        container.addView(mViewList.get(position));  
		        return mViewList.get(position);  
		    }  
		};  
		  
		  
		mViewPager.setAdapter(pagerAdapter);
	}

}
