package com.staros.themecenter;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
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
			mGallery.addView(img);
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
}
