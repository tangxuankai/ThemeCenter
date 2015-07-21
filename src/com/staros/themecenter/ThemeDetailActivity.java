package com.staros.themecenter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ThemeDetailActivity extends Activity {
	private LinearLayout mGallery;  
	private int[] mImgIds;  
	private LayoutInflater mInflater; 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theme_detail);
		setTransturestatus(true);
		mInflater = LayoutInflater.from(this); 
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		getActionBar().setHomeButtonEnabled(true);
		initData();  
		initView();
	}



	private void initView() {
		// TODO Auto-generated method stub
		mGallery = (LinearLayout) findViewById(R.id.id_gallery);

		for (int i = 0; i < mImgIds.length; i++){
	        View view = mInflater.inflate(R.layout.gallery_item,  
	        		 mGallery, false);
	        ImageView img = (ImageView)view.findViewById(R.id.iv_gallery_item);
			img.setImageResource(mImgIds[i]);
			if (i == 0){
				img.setTransitionName("transition_share");
				img.setTag("transition_share");
			} else {
				img.setTransitionName("transition_share" + i);
				img.setTag("transition_share" + i);
			}
			mGallery.addView(img);
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String TransitionName = (String)v.getTag();
					Intent intent = new Intent(ThemeDetailActivity.this, FullScreenPreview.class);
					ActivityOptions options = ActivityOptions.
							makeSceneTransitionAnimation(ThemeDetailActivity.this, v, TransitionName);
					startActivity(intent, options.toBundle());
				}
			});
		}
	}

	private void initData() {
		// TODO Auto-generated method stub
		mImgIds = new int[] {R.drawable.a, R.drawable.b, R.drawable.c,
	             R.drawable.d, R.drawable.e};
	}

	private void setTransturestatus(boolean on) {
		// TODO Auto-generated method stub
		if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			Window win = getWindow();  
			WindowManager.LayoutParams winParams = win.getAttributes();  
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;  
			if (on) {  
			    winParams.flags |= bits;  
			} else {  
			    winParams.flags &= ~bits;  
			}  
			win.setAttributes(winParams);  
			
		    SystemBarTintManager tintManager = new SystemBarTintManager(this);  
		    tintManager.setStatusBarTintEnabled(true);  
		    tintManager.setStatusBarTintResource(R.color.stutasbar_background_blue);//通知栏所需颜色   
		}	
	}



	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		finishAfterTransition();
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);

	}
	
}
