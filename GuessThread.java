package org.opencv.rps;

import org.opencv.core.Mat;

import android.util.Log;

public class GuessThread implements Runnable{
	
	private Mat greyFram, prevGreyFrame;
	private CameraActivity owner;
	
	   public GuessThread(CameraActivity cameraActivity, Mat greyFrame, Mat prevGreyFrame) {
		   this.greyFram = greyFrame;
		   this.prevGreyFrame = prevGreyFrame;
		   this.owner = cameraActivity;
	   }

	   public void run() {
			Log.e("Preetish", "Running in thread");
        	int guess = owner.CalcFlow(greyFram.getNativeObjAddr(), prevGreyFrame.getNativeObjAddr());
			Log.e("Preetish", "Thread Finished");
        	owner.OnLastGuess(guess);
	   }
}
