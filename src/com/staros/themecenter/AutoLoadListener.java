package com.staros.themecenter;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;

public class AutoLoadListener implements OnScrollListener{

	 public interface AutoLoadCallBack {  
	     void execute(String url);  
	 }  

//	 private int getLastVisiblePosition = 0,lastVisiblePositionY=0;  
	 private AutoLoadCallBack  mCallback;  
	 public AutoLoadListener(AutoLoadCallBack callback)  
	 {  
	     this.mCallback = callback;  
	 }  
	   
	 public void onScrollStateChanged(AbsListView view, int scrollState) {  

	     if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {  
	         //滚动到底部   
	         if (view.getLastVisiblePosition() == (view.getCount() - 3)) {  
	             mCallback.execute("hello");
	         }  
	           
	     }  
	 }  

	 public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {  
	       Log.d("txk","==================onScroll=================");
	 }  

}
