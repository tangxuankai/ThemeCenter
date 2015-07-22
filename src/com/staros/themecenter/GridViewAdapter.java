package com.staros.themecenter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GridViewAdapter extends BaseAdapter {
	Context mContext;
	private List<ThemeItemPreview> data;
	DisplayImageOptions mOptions;

	public void setData(List<ThemeItemPreview> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	public GridViewAdapter(Context context) {
		this.mContext = context;
		mOptions = new DisplayImageOptions.Builder()  
        .showImageOnLoading(R.drawable.load_begin)  
        .showImageOnFail(R.drawable.load_failed)  
        .cacheInMemory(true)  
        .cacheOnDisk(true)  
        .bitmapConfig(Bitmap.Config.RGB_565)  
        .build(); 

	}

	@Override
	public int getCount() {

		return data.size();
	}

	@Override
	public ThemeItemPreview getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.gridview_item, null);
			holder.tvThemeName  = (TextView) convertView.findViewById(R.id.tv_theme_name);
			holder.tvThemePrice = (TextView) convertView.findViewById(R.id.tv_theme_price);
			holder.ivThemePreview  = (ImageView) convertView.findViewById(R.id.iv_theme_preview);
			holder.ivDownloadImage = (ImageView) convertView.findViewById(R.id.iv_download);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.tvThemeName.setText(data.get(position).getThemeName());
		if (0.0 == data.get(position).getThemePrice()){
			holder.tvThemePrice.setText(R.string.free);
		} else {
			holder.tvThemePrice.setText(data.get(position).getThemePrice() + "");
		}
		if (data.get(position).getPreviewResourceID() != -1){
			holder.ivThemePreview.setImageResource(data.get(position).getPreviewResourceID());
		} else {
			ImageLoader.getInstance().displayImage(data.get(position).getThemePreviewUrl(), 
					holder.ivThemePreview, mOptions);  

			holder.ivDownloadImage.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(mContext, mContext.getResources().getString(R.string.begin_download) +
							data.get(position).getThemeName() , Toast.LENGTH_SHORT).show();
				}
			});
		}

		return convertView;

	}

	class ViewHolder{
		int       themeId;
		TextView  tvThemeName;
		TextView  tvThemePrice;
		ImageView ivThemePreview;
		ImageView ivDownloadImage;
	}


}
