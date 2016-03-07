//#include <jni.h>
//#include "Common.h"
//#include "RPS.h"
//#include "opticalFlow.h"
//
//
//using namespace cv;
//extern "C" {
//JNIEXPORT int JNICALL Java_org_opencv_rps_CameraActivity_CountFingers(JNIEnv*, jobject, jlong addrRgba);
//JNIEXPORT int JNICALL Java_org_opencv_rps_CameraActivity_CalcFlow(JNIEnv*, jobject, jlong addrGray, jlong addrGrayPrev);
//
//JNIEXPORT int JNICALL Java_org_opencv_rps_CameraActivity_CountFingers(JNIEnv*, jobject, jlong addrRgba)
//
//{
//	static RPS rps;
//    Mat& img = *(Mat*)addrRgba;
//    Mat resized;
//	resize(img, resized, Size(), 0.3, 0.3);
//	return rps.Detect(resized);
//}
//
//JNIEXPORT int JNICALL Java_org_opencv_rps_CameraActivity_CalcFlow(JNIEnv*, jobject, jlong addrGray, jlong addrGrayPrev)
//
//{
//    Mat& img = *(Mat*)addrGray;
//    Mat& prev = *(Mat*)addrGrayPrev;
//    Mat resized, resizedPrev;
//
//	resize(img, resized, Size(), 0.3, 0.3);
//	resize(prev, resizedPrev, Size(), 0.3, 0.3);
//
//	return calcOpticalFlow(resized, resizedPrev);
//}
//
//}
