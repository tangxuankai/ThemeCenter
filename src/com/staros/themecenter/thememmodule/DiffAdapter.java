package com.staros.themecenter.thememmodule;

import com.staros.themecenter.R;
import com.staros.viewflow.library.TitleProvider;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class DiffAdapter extends BaseAdapter implements TitleProvider {

        private static final int VIEW1 = 0;
        private static final int VIEW2 = 1;
        private static final int VIEW3 = 2;
        private static final int VIEW_MAX_COUNT = VIEW3 + 1;
    	private final int[] namesId = {R.string.classic, R.string.hot, R.string.sort};

    private LayoutInflater mInflater;
    private Resources      mResources;

    public DiffAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResources = context.getResources();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int view = getItemViewType(position);
        if (convertView == null) {
            switch (view) {
                case VIEW1:
                    convertView = mInflater.inflate(R.layout.activity_tab_viewflow_diff_view1, null);
                    break;
                case VIEW2:
                    convertView = mInflater.inflate(R.layout.activity_tab_viewflow_diff_view2, null);
                    break;
                case VIEW3:
                    convertView = mInflater.inflate(R.layout.activity_tab_viewflow_diff_view3, null);
                    break;
            }
        }
        return convertView;
    }



    /* (non-Javadoc)
	 * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
	public String getTitle(int position) {
		
		return mResources.getString(namesId[position]);
	}

}
