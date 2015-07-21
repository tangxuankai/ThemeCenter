package com.staros.themecenter;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuDrawable.Stroke;
import com.balysv.materialmenu.MaterialMenuIcon;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.R.menu;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity{

	private DrawerLayout   mDrawerLayout;
	private Fragment       mContentFragemnt;
	private ListView       mLeftMenu;
	private String[] items = null;
	private ArrayAdapter<String> mAdapter;
//	private MaterialMenuIcon mMaterialMenu;
	private boolean      isDrawerOpened;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stu
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		initImageLoader();
		setTransturestatus(true);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mLeftMenu     = (ListView) findViewById(R.id.left_drawer);
		items         = getResources().getStringArray(R.array.menu_array);
		mAdapter      = new ArrayAdapter<String>(this, R.layout.drawer_list_item, items);
		mLeftMenu.setAdapter(mAdapter);
		
//		mMaterialMenu = new MaterialMenuIcon(this, Color.WHITE, Stroke.THIN);
		
		mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
	        @Override
	        public void onDrawerSlide(View drawerView, float slideOffset) {
//	        	mMaterialMenu.setTransformationOffset(
//	                MaterialMenuDrawable.AnimationState.BURGER_ARROW,
//	                isDrawerOpened ? 2 - slideOffset : slideOffset
//	            );
	        }

	        @Override
	        public void onDrawerOpened(View drawerView) {
	            isDrawerOpened = true;
	            
	        }

	        @Override
	        public void onDrawerClosed(View drawerView) {
	            isDrawerOpened = false;
	            
	        }

	        @Override
	        public void onDrawerStateChanged(int newState) {
	            if(newState == DrawerLayout.STATE_IDLE) {
	                if(isDrawerOpened) {
//	                	mMaterialMenu.setState(MaterialMenuDrawable.IconState.ARROW);
//	                	setTitle(R.string.menu_name);
	                }  else {
//	                	mMaterialMenu.setState(MaterialMenuDrawable.IconState.BURGER);
//	                	setTitle(R.string.app_name);
	                }
	                	
	            }
	        }
	    });
		
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		getActionBar().setHomeButtonEnabled(true);
		initFragment();

	}
	
	private void initImageLoader() {        // TODO Auto-generated method stub	    
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()        
											.cacheInMemory(true)  //1.8.6包使用时候，括号里面传入参数true        
											.cacheOnDisk(true)    //同上        
											.build();	    	    
		ImageLoaderConfiguration config2 = new ImageLoaderConfiguration.Builder(this)	    
											.defaultDisplayImageOptions(defaultOptions)        
											.threadPriority(Thread.NORM_PRIORITY - 2)        
											.denyCacheImageMultipleSizesInMemory()        
											.diskCacheFileNameGenerator(new Md5FileNameGenerator())        
											.tasksProcessingOrder(QueueProcessingType.LIFO)        
											.build();	    	    
			ImageLoader.getInstance().init(config2);    
	}
	
	private void initFragment() {
		// TODO Auto-generated method stub
		mContentFragemnt = new ContentFragment();  
		FragmentManager fragmentManager = getFragmentManager();  
		fragmentManager.beginTransaction().replace(R.id.content_frame, mContentFragemnt).commit(); 
	}
	private void setTransturestatus(boolean on) {
		// TODO Auto-generated method stub
		if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
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
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    isDrawerOpened = mDrawerLayout.isDrawerOpen(Gravity.START); // or END, LEFT, RIGHT
//	    mMaterialMenu.syncState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
//	    mMaterialMenu.onSaveInstanceState(outState);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    if (item.getItemId() == android.R.id.home) {
	        // Handle your drawable state here
	    	isDrawerOpened = mDrawerLayout.isDrawerOpen(Gravity.START);
	    	if (isDrawerOpened){
	    		mDrawerLayout.closeDrawer(mLeftMenu);
	    	} else {
	    		mDrawerLayout.openDrawer(mLeftMenu);
	    	}
	    }
	    return super.onOptionsItemSelected(item);  

	}

}
