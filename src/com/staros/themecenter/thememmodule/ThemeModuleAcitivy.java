package com.staros.themecenter.thememmodule;

import java.util.ArrayList;
import java.util.List;

import com.staros.themecenter.GridViewAdapter;
import com.staros.themecenter.R;
import com.staros.themecenter.SystemBarTintManager;
import com.staros.themecenter.ThemeItemPreview;
import com.staros.themecenter.thememmodule.GridViewOnScrollListener.GridviewCallback;
import com.staros.viewflow.library.TitleFlowIndicator;
import com.staros.viewflow.library.ViewFlow;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class ThemeModuleAcitivy extends Activity implements GridviewCallback{

	private ViewFlow mViewFlow;
	private GridView mGridView1, mGridView2, mGridView3;
	private List<ThemeItemPreview> mGridViewData1, mGridViewData2;
	private HotAdapter             mHotAdapter;
	private ClassicAdapter         mClassicAdapter;
	private SortAdapter            mSortAdapter;

	private GridviewCallback mCallBack;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.theme_moudel);
        setContentView(R.layout.activity_tab_viewflow_title_layout);
        setTransturestatus(true);
        
		mViewFlow = (ViewFlow) findViewById(R.id.viewflow);
        DiffAdapter adapter = new DiffAdapter(this);
        mViewFlow.setAdapter(adapter, 1);
		TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		mViewFlow.setFlowIndicator(indicator);
		
		/** To populate ListView in diff_view1.xml */
		
		mGridView1 = (GridView) findViewById(R.id.gv_thememodule1);
		mGridView2 = (GridView) findViewById(R.id.gv_thememodule2);
		mGridView3 = (GridView) findViewById(R.id.gv_thememodule3);
		
		mGridView1.setOnScrollListener(new GridViewOnScrollListener(this));
		
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
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		mGridViewData1 = new ArrayList<ThemeItemPreview>();
		mGridViewData2 = new ArrayList<ThemeItemPreview>();
		
		for (int i = 0; i < 15; i++) {
			ThemeItemPreview itemPreview = new ThemeItemPreview();
			if (i % 2 == 0) {
				itemPreview.setPreviewResouceID(R.drawable.a);
			} else {
				itemPreview.setPreviewResouceID(R.drawable.b);
			}
			itemPreview.setThemeName(getResources().getString(R.string.app_name) + i);
			itemPreview.setThemePrice(0.0);
			mGridViewData1.add(itemPreview);
			mGridViewData2.add(itemPreview);

		}
		
		mHotAdapter = new HotAdapter(this);
		mHotAdapter.setData(mGridViewData1);
		mGridView1.setAdapter(mHotAdapter);
		mClassicAdapter = new ClassicAdapter(this);
		mClassicAdapter.setData(mGridViewData2);
		mGridView2.setAdapter(mClassicAdapter);
		
		mSortAdapter = new SortAdapter(this);
		mGridView3.setAdapter(mSortAdapter);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
	}
	
	

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
    
	@Override
	public void execute(String url) {
		// TODO Auto-generated method stub
        Toast.makeText(ThemeModuleAcitivy.this, url, 500).show();
        int oldCount = mGridViewData1.size();
        for (int i = oldCount; i < oldCount + 6 ; i++){
			ThemeItemPreview itemPreview = new ThemeItemPreview();
			if (i % 2 == 0) {
				itemPreview.setPreviewResouceID(R.drawable.a);
			} else {
				itemPreview.setPreviewResouceID(R.drawable.b);
			}
			itemPreview.setThemeName(getResources().getString(R.string.app_name) + i);
			itemPreview.setThemePrice(0.0);
			mGridViewData1.add(itemPreview);
        }
        mHotAdapter.setData(mGridViewData1);
	}  

}
