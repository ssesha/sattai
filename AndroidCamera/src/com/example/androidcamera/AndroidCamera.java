package com.example.androidcamera;


import java.io.IOException;
import java.util.List;

import com.example.flipper.FullScreenImageAdapter;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class AndroidCamera extends Activity implements SurfaceHolder.Callback{

	Camera camera;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	boolean previewing = false;
	LayoutInflater controlInflater = null;
	FullScreenImageAdapter adapter;
	
	ViewPager viewPager;
	
	ToggleButton tgbutton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adroidcameraview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        
        
        
      /*  DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		float scaleWidth = metrics.scaledDensity;
		float scaleHeight = metrics.scaledDensity;
        
		
		SurfaceView sv=(SurfaceView)findViewById(R.id.camerapreview);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(scaleWidth, height);
	    surface.setLayoutParams(params);
		*/
		
        
        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.camerapreview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
        controlInflater = LayoutInflater.from(getBaseContext());
        View viewControl = controlInflater.inflate(R.layout.control, null);
        LayoutParams layoutParamsControl 
        	= new LayoutParams(LayoutParams.FILL_PARENT, 
        	LayoutParams.FILL_PARENT);
        
        
        
	    viewPager = (ViewPager) viewControl.findViewById(R.id.view_pager);
	    
	    
		
	    
		adapter = new FullScreenImageAdapter(this);

		viewPager.setAdapter(adapter);

		// displaying selected image first
		viewPager.setCurrentItem(0);
        
        
        
       // TouchImageView tiv=(TouchImageView)viewControl.findViewById(R.id.imageView1);
        //tiv.setBackgroundResource(R.drawable.shirt_bg);
        
        this.addContentView(viewControl, layoutParamsControl);
        
    }
    
    
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	
    	
        tgbutton = (ToggleButton) findViewById(R.id.toggleButton1);
        tgbutton.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 if (tgbutton.isChecked()) {
                	 int cameraCount = 0;
             	    //Camera cam = null;
             	    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
             	    cameraCount = Camera.getNumberOfCameras();
             	    for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
             	        Camera.getCameraInfo( camIdx, cameraInfo );
             	        if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK  ) {
             	            try {
             	            	camera = Camera.open( camIdx );
             	            } catch (RuntimeException e) {
             	               // Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
             	            	e.printStackTrace();
             	            }
             	        }
             	    }
                        
                    } else {
 
                    	try {
                	            	camera = Camera.open();
                	            } catch (RuntimeException e) {
                	               // Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
                	            	e.printStackTrace();
                	            }
                	        
                	    
                    }
                 
                 
                 
                 
                 
                 
                 
                 
                 if(previewing){
         			camera.stopPreview();
         			previewing = false;
         		}
         		
         		if (camera != null){
         			try {
         				camera.setPreviewDisplay(surfaceHolder);
         				
         		         				
         				camera.setDisplayOrientation(90);
         				camera.startPreview();
         				previewing = true;
         			} catch (IOException e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
                 
                 
         		}
                 
            }
        });
    	
    	
    	
    	
    	super.onResume();
    }
    

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if(previewing){
			camera.stopPreview();
			previewing = false;
		}
		
		if (camera != null){
			try {
				camera.setPreviewDisplay(surfaceHolder);
				
			/*	
				List<Camera.Size> mSupportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
					
				DisplayMetrics metrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(metrics);
				float scaleWidth = metrics.scaledDensity;
				float scaleHeight = metrics.scaledDensity;
				
				Size mPreviewSize=getOptimalPreviewSize(mSupportedPreviewSizes, (int)scaleWidth, (int)scaleWidth);
				
				Camera.Parameters parameters = camera.getParameters();
				parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
				camera.setParameters(parameters);
				*/
				
				
				
				
				camera.setDisplayOrientation(90);
				camera.startPreview();
				previewing = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//camera = Camera.open();
		
	    int cameraCount = 0;
	    //Camera cam = null;
	    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
	    cameraCount = Camera.getNumberOfCameras();
	    for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
	        Camera.getCameraInfo( camIdx, cameraInfo );
	        if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT  ) {
	            try {
	            	camera = Camera.open( camIdx );
	            } catch (RuntimeException e) {
	               // Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
	            	e.printStackTrace();
	            }
	        }
	    }
		
		
		
		camera.setDisplayOrientation(90);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera.stopPreview();
		camera.release();
		camera = null;
		previewing = false;
	}
	
/*	private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
	*/
	
	
}