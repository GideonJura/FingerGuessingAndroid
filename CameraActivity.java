package org.opencv.rps;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorMOG2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;



public class CameraActivity extends Activity implements CvCameraViewListener2 {

	private static final Scalar	   FACE_TEXT_COLOR	   = new Scalar(255,0,255,0);
	
    private CameraBridgeViewBase   mOpenCvCameraView;
    
	private Button playAgainButton;
    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {

            		Log.e("Preetish", "Successful connection");

                    // Load native library after(!) OpenCV initialization
                    System.loadLibrary("mixed_sample");

                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShowCamera();
		
	
	}
	
    @Override
    protected void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        
    }
	
	@Override
	protected void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

	
	private void ShowCamera(){		
	    
	    setContentView(R.layout.camera_view);
	    mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial2_activity_surface_view);
	    mOpenCvCameraView.setCvCameraViewListener(this);
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
	
	}	
	
	@Override
	public void onCameraViewStarted(int width, int height) {
		Log.e("Preetish", "View Started");
		mRgba = new Mat(height, width, CvType.CV_8UC4);		
	}

	@Override
	public void onCameraViewStopped() {
         mRgba.release();	
	}

	
	private int shouldStopCamera = 81;
	private int fingers;
	private int lastGuess = -2;
    private Mat mRgba;
    private Mat mPrevGray;
    
    Mat src; 
    Mat hsv;
    Mat hist;
    Mat backproj;
    Mat mask;
    Mat flood_mask;
    int backgroundFrame = 30; 
    int foreCount = 0;
    int myflag = 0;
    int b_lo = 30; 
    int b_up = 30;
    int g_lo = 30;
    int g_up = 30;
    int r_lo = 30;
    int r_up = 30;
    int th = 20;
    
    int finger = -1;
	int detect = 0;	
	int undetect = 0;
	Mat back;
	Mat fore ;
	BackgroundSubtractorMOG2 bg;
	@Override
	public Mat onCameraFrame(CvCameraViewFrame inputFrame)  {
		
		if (shouldStopCamera == 0){
			int counter = 0;
			while (lastGuess==-2) counter++;
			Log.e("Preetish", "Ticks waited for Guessing: " + counter);
			  Intent intent = new Intent(this, ReplayActivity.class);
				Bundle b = new Bundle();
				b.putInt("fingers", fingers);
				b.putInt("guess", lastGuess); 
				intent.putExtras(b); //Put your id to your next Intent
	            startActivity(intent);
	            finish();			
			return inputFrame.rgba();
		}
			

        int guess = -1;
        
        if (shouldStopCamera == 64) {
        	Mat grey = inputFrame.gray().clone();
        	mPrevGray = grey;        	
        }
        if (shouldStopCamera == 37) {

			Log.e("Preetish", "Starting Guess Thread");
        	Runnable r = new GuessThread(this, inputFrame.gray(), mPrevGray);
        	new Thread(r).start();       
        	try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	
        }
        mRgba = inputFrame.rgba();
        Imgproc.putText(mRgba, Integer.toString(guess), new Point(30,30), Core.FONT_HERSHEY_COMPLEX, 5, FACE_TEXT_COLOR);	 	
        if (shouldStopCamera > 10) Imgproc.circle(mRgba, new Point(100,100), 50, FACE_TEXT_COLOR,8,3,0);
        if (shouldStopCamera > 35) Imgproc.circle(mRgba, new Point(100,250), 50, FACE_TEXT_COLOR,8,3,0);
        if (shouldStopCamera > 54) Imgproc.circle(mRgba, new Point(100,400), 50, FACE_TEXT_COLOR,8,3,0);
        if (shouldStopCamera == 1) {
            fingers = CountFingers(mRgba.getNativeObjAddr());
        }
        shouldStopCamera--;
        return mRgba;
	    
	}
	public void OnLastGuess(int guess) {
		lastGuess = guess;		
	}
    public native int CountFingers(long matAddrGr);
    public native int CalcFlow(long matAddrGr, long matAddrGrPrev);
}
