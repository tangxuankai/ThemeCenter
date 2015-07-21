package com.staros.themecenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.kevinsawicki.http.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.staros.scrollad.MyImgScroll;
import com.staros.themecenter.MyScrollView.AutoLoadCallBack;
import com.staros.themecenter.thememmodule.ThemeModuleAcitivy;

public class ContentFragment extends Fragment {
	private final static String SERVER_ADDRESS = "http://10.86.232.100:8080/client/theme/getList.jspx"; 
	private Activity       mActivity;
	private MyScrollView   mScrollView;
	private RelativeLayout mTopView;
	private MyImgScroll    mScrollAd;
	private ProgressBar    mProgressBar;
	private MyGridView     mGridView;
	private ImageButton    mIbTheme, mIbWallpaper, mIbMix, mIbLoacal;
	private List<ThemeItemPreview> mGridViewData;
	private GridViewAdapter mGridAdapter;
	private AnimationController mAnimationController;
	
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
		mAnimationController = new AnimationController();
    	mScrollView  = (MyScrollView) view.findViewById(R.id.scroll_view);
    	mProgressBar = (ProgressBar) view.findViewById(R.id.pb_load);
    	mGridView    = (MyGridView) view.findViewById(R.id.gv_new_commend);
    	mIbTheme     = (ImageButton) view.findViewById(R.id.ib_theme);
    	mIbWallpaper = (ImageButton) view.findViewById(R.id.ib_wallpaper);
    	mIbMix       = (ImageButton) view.findViewById(R.id.ib_ranklist);
    	mIbLoacal    = (ImageButton) view.findViewById(R.id.ib_local);
    	
    	setImageButtonOnClickLisenter();
//    	mProgressBar.setVisibility(View.VISIBLE);
 
    	mScrollView.setCallback(callBack);
		mGridView.setFocusable(false);
    	
		mMyPager = (MyImgScroll) view.findViewById(R.id.myvp);
		mOvalLayout = (LinearLayout) view.findViewById(R.id.vb);
		
		mGridView.setOnItemClickListener(new GridViewItemClick());
		getImageUrl();//获取图片地址
		initViewPager();//初始化图片
		mMyPager.start(mActivity, mListViews, 4000, mOvalLayout);
		isImgScrolling = true;
		getInfoFromServer();
	}
    
	private void setImageButtonOnClickLisenter() {
		// TODO Auto-generated method stub
		mIbTheme.setOnClickListener(new ImageButtonOnClick());
		mIbLoacal.setOnClickListener(new ImageButtonOnClick());
		mIbMix.setOnClickListener(new ImageButtonOnClick());
		mIbWallpaper.setOnClickListener(new ImageButtonOnClick());
	}

	private void getInfoFromServer() {
		// TODO Auto-generated method stub
//		String response = HttpRequest.get(SERVER_ADDRESS).body(); 
		
//		try {
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		mAnimationController.scaleOut(mProgressBar, 200, 10000);
//		mProgressBar.setVisibility(View.GONE);
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
			Intent intent = new Intent(mActivity, ThemeDetailActivity.class);
			ActivityOptions options = ActivityOptions.
					makeSceneTransitionAnimation(mActivity, view, "transition_share");
			mActivity.startActivity(intent, options.toBundle());
		}
		
	}
	
	class ImageButtonOnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch(id){
			case R.id.ib_theme:
				Intent intent = new Intent(mActivity, ThemeModuleAcitivy.class);
//				ActivityOptions options = ActivityOptions.
//						makeSceneTransitionAnimation(mActivity, v, "transition_share");
				mActivity.startActivity(intent);
				break;
			case R.id.ib_wallpaper:
				break;
			case R.id.ib_ranklist:
				break;
			case R.id.ib_local:
				break;
			}
		}
		
	}

}
