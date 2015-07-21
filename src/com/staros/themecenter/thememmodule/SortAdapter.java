package com.staros.themecenter.thememmodule;

import com.staros.themecenter.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class SortAdapter extends BaseAdapter {
	Context mContext;
	private Button mButton;
	private int[]  sortNameID = new int[]{R.string.sort1, R.string.sort2, R.string.sort3,
			                              R.string.sort4, R.string.sort5, R.string.sort6};
	private int[]  colors     = new int[]{
			R.color.stutasbar_background_blue, R.color.stutasbar_background_darkgreen, 
			R.color.stutasbar_background_orange, R.color.white,
			R.color.stutasbar_background_green, R.color.stutasbar_background_darkblue
	};
	public SortAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sortNameID.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.sort_item, null);
			holder.button = (Button) convertView.findViewById(R.id.bt_sort);
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		String str = mContext.getResources().getString(sortNameID[position]);
		holder.button.setText(str);
		
		return convertView;
	}
	
	class ViewHolder{
		Button button;
	}

}
