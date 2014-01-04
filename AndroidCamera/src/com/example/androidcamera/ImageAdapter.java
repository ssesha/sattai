package com.example.androidcamera;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;

    public ImageAdapter(Context c,Activity activity) {
        mContext = c;
        mActivity=activity;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	
        	DisplayMetrics metrics = new DisplayMetrics();
        	mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        	int fullscreenheight = metrics.heightPixels;
        	int fullscreenwidth = metrics.widthPixels;
        	
        	int block_width;
        	int block_height;
        	
        	//if(fullscreenheight>1024||fullscreenwidth>1024){

        		if(fullscreenheight>fullscreenwidth){
        			block_width=fullscreenwidth/2;
        			block_height=block_width+(block_width/2);//(fullscreenheight/3)+3;
        		}else{
        			block_width=fullscreenwidth/3;
        			block_height=block_width+(block_width/2);//(fullscreenheight/2)+5;
        		}
        		
        		
        	//}
        	
        	
        	
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(block_width, block_height));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
    

    
    
    
}





