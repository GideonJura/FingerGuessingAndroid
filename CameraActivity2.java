//package org.opencv.rps;
//
//import org.opencv.android.BaseLoaderCallback;
//import org.opencv.android.CameraBridgeViewBase;
//import org.opencv.android.LoaderCallbackInterface;
//import org.opencv.android.OpenCVLoader;
//import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
//import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.content.Intent;
//import android.util.Log;
//import android.widget.Button;
//
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.TextView;
//
//import java.util.Random;
//
//
//
//public class CameraActivity2 extends Activity implements CvCameraViewListener2 {
//
//	private static final Scalar	   FACE_TEXT_COLOR	   = new Scalar(255,0,255,0);
//	
//	 private CameraBridgeViewBase   mOpenCvCameraView;
//	    TextView tvMonsterHP, tvPlayerHP, tvMonsterGesture, tvPlayerGesture, tvCounting, tvCompareResult;
//	    //Button btnReady, btnRock, btnScissor, btnPaper;
//	    int playerGesture = 0;
//	    int monsterGesture = -1;
//	    Animation anim3, anim2, anim1;
//	    Handler handler;
//	    int monsterHP, playerHP;
//	    int flagStarted = 0;
//    
//	private Button playAgainButton;
//    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
//        @Override
//        public void onManagerConnected(int status) {
//            switch (status) {
//                case LoaderCallbackInterface.SUCCESS:
//                {
//
//            		Log.e("Preetish", "Successful connection");
//
//                    // Load native library after(!) OpenCV initialization
//                    System.loadLibrary("mixed_sample");
//
//                    mOpenCvCameraView.enableView();
//                } break;
//                default:
//                {
//                    super.onManagerConnected(status);
//                } break;
//            }
//        }
//    };
//    
//    
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		ShowCamera();
//		setContentView(R.layout.camera_view2);
//        tvMonsterHP = (TextView)findViewById(R.id.tv_monster_hp);
//        tvPlayerHP = (TextView)findViewById(R.id.tv_player_hp);
//        tvMonsterGesture = (TextView)findViewById(R.id.tv_monster_gesture);
//        tvPlayerGesture = (TextView)findViewById(R.id.tv_player_gesture);
//        tvCounting = (TextView)findViewById(R.id.tv_counting);
//        tvCompareResult = (TextView)findViewById(R.id.tv_compare_result);
////        findViewById(R.id.btn_ready).setOnClickListener(this);
////        findViewById(R.id.btn_rock).setOnClickListener(this);
////        findViewById(R.id.btn_scissor).setOnClickListener(this);
////        findViewById(R.id.btn_paper).setOnClickListener(this);
//        monsterHP = 100;
//        playerHP = 100;
//
//        AnimInit();
//
//        handler = new Handler(Looper.getMainLooper()){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                //long counting end
//                //show compare result
//                if(msg.what == 3000){
//                    GenerateRandomMonsterGesture();
//                    DisplayMonsterGesture();
//                    if(CompareGestures()==1){
//                        monsterHP = monsterHP - 10;
//                        tvMonsterHP.setText("HP: "+monsterHP);
//                        tvCompareResult.setText("Win!");
//                    }
//                    if(CompareGestures()==-1){
//                        playerHP = playerHP - 10;
//                        tvPlayerHP.setText("HP:"+playerHP);
//                        tvCompareResult.setText("Lose!");
//                    }
//                    if(CompareGestures()==0){
//                        tvCompareResult.setText("Draw!");
//                    }
//                    new Thread(new MyCounter(1000)).start();
//                       /* new Thread(new MyCounter(3)).start();
//                        AnimStart();*/
//                }
//
//                //short counting end
//                //stop showing result
//                //see if another long counting will start
//                if(msg.what==1000){
//                    if(monsterHP!=0&&playerHP!=0){
//                        tvMonsterGesture.setText("Gesture: ");
//                        tvCompareResult.setText("");
//                        AnimStart();
//                        new Thread(new MyCounter(3000)).start();
//                    }
//                }
//            }
//        };
//    }
//	
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        if (mOpenCvCameraView != null)
//            mOpenCvCameraView.disableView();
//    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        
//    }
//	
//	@Override
//	protected void onDestroy() {
//        super.onDestroy();
//        if (mOpenCvCameraView != null)
//            mOpenCvCameraView.disableView();
//    }
//
//	
//	private void ShowCamera(){		
//	    
//	    setContentView(R.layout.camera_view);
//	    mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial2_activity_surface_view);
//	    mOpenCvCameraView.setCvCameraViewListener(this);
//        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
//	
//	}	
//	
//	@Override
//	public void onCameraViewStarted(int width, int height) {
//		Log.e("Preetish", "View Started");
//		mRgba = new Mat(height, width, CvType.CV_8UC4);		
//	}
//
//	@Override
//	public void onCameraViewStopped() {
//         mRgba.release();	
//	}
//
//	
//	private int shouldStopCamera = 81;
//	private int fingers;
//	private int lastGuess = -2;
//    private Mat mRgba;
//    private Mat mPrevGray;
//	@Override
//	public Mat onCameraFrame(CvCameraViewFrame inputFrame)  {
//		
//		if (shouldStopCamera == 0){
//			int counter = 0;
//			while (lastGuess==-2) counter++;
//			Log.e("Preetish", "Ticks waited for Guessing: " + counter);
//			  Intent intent = new Intent(this, SingleGameActivity.class);
//			  Intent intentStartedFrom = getIntent();
//				Bundle b = new Bundle();
//		    	Bundle bundle = intentStartedFrom.getExtras(); 
//		        String uname = bundle.getString("uname");  
//		        String currentStage = bundle.getString("stageChosen");
//		        
//		  	    b.putString("stageChosen",currentStage);  
//		        b.putString("uname",uname);
//				b.putInt("fingers", fingers);
//				b.putInt("guess", lastGuess); 
//				intent.putExtras(b); //Put your id to your next Intent
//	            startActivity(intent);
//	            finish();			
//			return inputFrame.rgba();
//		}
//			
//
//        int guess = -1;
//        
//        if (shouldStopCamera == 64) {
//        	Mat grey = inputFrame.gray().clone();
//        	mPrevGray = grey;        	
//        }
//        if (shouldStopCamera == 37) {
//
//			Log.e("Preetish", "Starting Guess Thread");
//        	Runnable r = new GuessThread(this, inputFrame.gray(), mPrevGray);
//        	new Thread(r).start();       
//        	try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//  	
//        }
//        mRgba = inputFrame.rgba();
//        Imgproc.putText(mRgba, Integer.toString(guess), new Point(30,30), Core.FONT_HERSHEY_COMPLEX, 5, FACE_TEXT_COLOR);	 	
//        if (shouldStopCamera > 10) Imgproc.circle(mRgba, new Point(100,100), 50, FACE_TEXT_COLOR,8,3,0);
//        if (shouldStopCamera > 35) Imgproc.circle(mRgba, new Point(100,250), 50, FACE_TEXT_COLOR,8,3,0);
//        if (shouldStopCamera > 54) Imgproc.circle(mRgba, new Point(100,400), 50, FACE_TEXT_COLOR,8,3,0);
//        if (shouldStopCamera == 1) {
//            fingers = CountFingers(mRgba.getNativeObjAddr());
//        }
//        shouldStopCamera--;
//        return mRgba;
//	    
//	}
//	public void OnLastGuess(int guess) {
//		lastGuess = guess;		
//	}
//    public native int CountFingers(long matAddrGr);
//    public native int CalcFlow(long matAddrGr, long matAddrGrPrev);
//    
//    public void AnimInit(){
//        anim3 = new AnimationUtils().loadAnimation(CameraActivity2.this, R.anim.anim_scale_alpha);
//        anim2 = new AnimationUtils().loadAnimation(CameraActivity2.this, R.anim.anim_scale_alpha);
//        anim1 = new AnimationUtils().loadAnimation(CameraActivity2.this, R.anim.anim_scale_alpha);
//
//        anim3.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                System.out.println("3 end");
//                tvCounting.setText("2");
//                tvCounting.startAnimation(anim2);
//            }
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//        });
//
//        anim2.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                System.out.println("2 end");
//                tvCounting.setText("1");
//                tvCounting.startAnimation(anim1);
//            }
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//        });
//
//        anim1.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                System.out.println("1 end");
//                tvCounting.setText("");
//                if(flagStarted==0){
//                    flagStarted = 1;
//                    AnimStart();
//                    new Thread(new MyCounter(3000)).start();
//                }
//            }
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//        });
//    }
//
//    public void AnimStart(){
//        tvCounting.setText("3");
//        tvCounting.startAnimation(anim3);
//    }
//
//    public class MyCounter implements Runnable {
//       int time = 0;
//
//        public MyCounter(int time) {
//            super();
//            this.time = time;
//        }
//
//        @Override
//        public void run() {
//            try {
//                Message message = new Message();
//                message.what = this.time;
//                Thread.sleep(this.time);
//                handler.sendMessage(message);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void GenerateRandomMonsterGesture(){
//		Bundle b = getIntent().getExtras();
//		int fingers = b.getInt("fingers");
//    	switch (fingers){
//    	case -1:
////			userChoice = Choice.UNKNOWN;
//			break;
//    		
//		case 0:
//		case 1:
//			monsterGesture = 0;
//			break;
//
//		case 2:
//		case 3:
//			monsterGesture = 2;
//			break;
//
//		default:
//			monsterGesture = 5;
//			break;
//        }
//    }
//
//    public void DisplayMonsterGesture(){
//        switch (monsterGesture){
//            case 0:
//                tvMonsterGesture.setText("Rock");
//                break;
//            case 2:
//                tvMonsterGesture.setText("Scissor");
//                break;
//            case 5:
//                tvMonsterGesture.setText("Paper");
//                break;
//            default:
//                break;
//        }
//    }
//
//    public int CompareGestures(){
//        if(monsterGesture==playerGesture) return 0;
//        if(monsterGesture==0 && playerGesture==2) return -1;
//        if(monsterGesture==0 && playerGesture==5) return 1;
//        if(monsterGesture==2 && playerGesture==0) return 1;
//        if(monsterGesture==2 && playerGesture==5) return -1;
//        if(monsterGesture==5 && playerGesture==0) return -1;
//        if(monsterGesture==5 && playerGesture==2) return 1;
//        return 100;
//    }
//}
