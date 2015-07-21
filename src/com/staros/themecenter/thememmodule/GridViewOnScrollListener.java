package com.staros.themecenter.thememmodule;

import com.staros.themecenter.MyScrollView.AutoLoadCallBack;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;

public class GridViewOnScrollListener implements OnScrollListener{

	private boolean mIsFirstLoad = true;
	public interface GridviewCallback {  
	    void execute(String url);  
	} 
	private int getLastVisiblePosition = 0,lastVisiblePositionY=0;  
	private GridviewCallback  mCallback;  
	public GridViewOnScrollListener(GridviewCallback callback)  
	{  
	    this.mCallback = callback;  
	}  
	  
	public void onScrollStateChanged(AbsListView view, int scrollState) {  

	    if (scrollState == OnScrollListener.SCROLL_STATE_FLING) {  
	        //滚动到底部   

	    }  
	}  

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		int tmp = totalItemCount - (firstVisibleItem + visibleItemCount) ;
        if (totalItemCount != 0 && tmp == 3 ) { 
        	if (mCallback != null)
        		mCallback.execute("");
        		Log.d("txk", "onScroll totalItemCount="+ totalItemCount+ 
        				";firstVisibleItem="+ firstVisibleItem+ ";visibleItemCount="+visibleItemCount);
        }
	}
	 
	 public void setCallback(GridviewCallback callback){
		 this.mCallback = callback;
	 }

}
