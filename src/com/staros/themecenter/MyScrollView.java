package com.staros.themecenter;

import com.staros.themecenter.AutoLoadListener.AutoLoadCallBack;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	
	private AutoLoadCallBack  mCallback;  
	View  mTopView;
	View  mFlowView;
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		
		super.onScrollChanged(l, t, oldl, oldt);
//		if( mTopView != null && mFlowView != null) {
//			if(t >= mTopView.getHeight()) {
//				mFlowView.setVisibility(View.VISIBLE);
//			} else {
//				mFlowView.setVisibility(View.GONE);
//			}
//		}
		if(t + getHeight() >=  computeVerticalScrollRange() - 400){  
		//滑动到底部，doSomething();   
			mCallback.execute("");
		}  

	}
	
	
	public void listenerFlowViewScrollState(View topView, View flowView) {
//		mTopView  = topView;
//		mFlowView = flowView;
	}
	

	 public interface AutoLoadCallBack {  
	     void execute(String url);  
	 }  
	 
	 public void setCallback(AutoLoadCallBack callback){
		 this.mCallback = callback;
	 }
	
}
