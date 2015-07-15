package com.staros.themecenter;

import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.staros.scrollad.MyImgScroll;
import com.staros.themecenter.MyScrollView.AutoLoadCallBack;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

public class ContentFragment extends Fragment {
	private Activity     mActivity;
	private MyScrollView mScrollView;
	private LinearLayout mFlowLayout;
	private RelativeLayout mTopView;
	private MyImgScroll    mScrollAd;
//	private PullToRefreshView mPullToRefreshView;
	private MyGridView     mGridView;
	private List<ThemeItemPreview> mGridViewData;
	private GridViewAdapter mGridAdapter;
	
	private boolean      isImgScrolling = false;
	private MyImgScroll  mMyPager;    // 图片容器
	private LinearLayout mOvalLayout; // 圆点容器
	private List<View>   mListViews;  // ImageView组
	private List<String> mUrlList;    //图片地址列表
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_layout, null);
//		initImageloader();
		initView(view);
		return view;
	}
	
    private void initImageloader() {
		// TODO Auto-generated method stub
    	ImageLoaderConfiguration configuration = ImageLoaderConfiguration  
    	        .createDefault(mActivity);  
    	//Initialize ImageLoader with configuration.   
    	ImageLoader.getInstance().init(configuration);
	}

	private void initView(View view) {
    	mScrollView = (MyScrollView) view.findViewById(R.id.scroll_view);
//    	mImageView  = (ImageView) view.findViewById(R.id.image_view);
    	mFlowLayout = (LinearLayout) view.findViewById(R.id.flow_llay);
    	mGridView   = (MyGridView) view.findViewById(R.id.gv_new_commend);
//    	mScrollView.smoothScrollTo(0,0);  

    	mScrollView.setCallback(callBack);
    	
		mGridView.setFocusable(false);
    	
		mMyPager = (MyImgScroll) view.findViewById(R.id.myvp);
		mOvalLayout = (LinearLayout) view.findViewById(R.id.vb);
		
//		AutoLoadListener autoLoadListener = new AutoLoadListener(callBack);
//		mGridView.setOnScrollListener(autoLoadListener);
		mGridView.setOnItemClickListener(new GridViewItemClick());
		getImageUrl();//获取图片地址
		initViewPager();//初始化图片
		//开始滚动
		mMyPager.start(mActivity, mListViews, 4000, mOvalLayout);
		isImgScrolling = true;
	}
    
	private void getImageUrl(){
		mUrlList = new ArrayList<String>();
		mUrlList.add("http://www.pp.dk/pp_mobler_logo.jpg");
		mUrlList.add("http://pic.58pic.com/58pic/15/16/00/49T58PICiZm_1024.jpg");
		mUrlList.add("http://www.6188.com/upload_6188s/Small_paper/tebie/3553/s800/2880view_008.jpg");
		mUrlList.add("http://img4.imgtn.bdimg.com/it/u=1254729582,2442676828&fm=21&gp=0.jpg");
	}

	public void stop(View v) {
		if (isImgScrolling){
			mMyPager.stopTimer();
			isImgScrolling = false;
		}
		
	}
	/**
	 * 初始化图片
	 */
	private void initViewPager() {
		mListViews = new ArrayList<View>();
		//循环添加ImageView
		DisplayImageOptions options = new DisplayImageOptions.Builder() 
		.showImageOnLoading(R.drawable.load_begin)
		.showImageOnFail(R.drawable.load_failed)
        .cacheInMemory(true)  
        .cacheOnDisk(true)  
        .bitmapConfig(Bitmap.Config.RGB_565)  
        .build();  

		for (int i = 0; i < mUrlList.size(); i++) {
			ImageView imageView = new ImageView(mActivity);
			ImageLoader.getInstance().displayImage(mUrlList.get(i), imageView, options); 
			mListViews.add(imageView);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		if (!isImgScrolling){
			mMyPager.startTimer();
			isImgScrolling = true;
		}
		
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initDate();
	}

	private void initDate() {
		// TODO Auto-generated method stub
		mGridViewData = new ArrayList<ThemeItemPreview>();
		
		for (int i = 0; i < 12; i++) {
			ThemeItemPreview itemPreview = new ThemeItemPreview();
			if (i % 2 == 0) {
				itemPreview.setPreviewResouceID(R.drawable.a);
			} else {
				itemPreview.setPreviewResouceID(R.drawable.b);
			}
			itemPreview.setThemeName(mActivity.getResources().getString(R.string.app_name) + i);
			itemPreview.setThemePrice("0");
			mGridViewData.add(itemPreview);

		}
		mGridAdapter = new GridViewAdapter(mActivity);
		mGridAdapter.setData(mGridViewData);
		mGridView.setAdapter(mGridAdapter);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		if (isImgScrolling){
			mMyPager.stopTimer();
			isImgScrolling = false;
		}
		
		super.onStop();
	}
	
	AutoLoadCallBack callBack=new AutoLoadCallBack(){  

	      public void execute(String url) {  
	          Toast.makeText(mActivity, url, 500).show();
	          int oldCount = mGridViewData.size();
	          for (int i = oldCount; i < oldCount + 12 ; i++){
	  			ThemeItemPreview itemPreview = new ThemeItemPreview();
				if (i % 2 == 0) {
					itemPreview.setPreviewResouceID(R.drawable.a);
				} else {
					itemPreview.setPreviewResouceID(R.drawable.b);
				}
				itemPreview.setThemeName(mActivity.getResources().getString(R.string.app_name) + i);
				itemPreview.setThemePrice("0");
				mGridViewData.add(itemPreview);
	          }
	          mGridAdapter.setData(mGridViewData);
	      }  
	        
	};  
	
	class GridViewItemClick implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Toast.makeText(mActivity, "hello world", Toast.LENGTH_SHORT).show();
		}
		
	}

}
