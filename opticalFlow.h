#ifndef _OPTICAL_FLOW
#define _OPTICAL_FLOW


#include "opencv2/video/tracking.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"

#include <iostream>

using namespace cv;
using namespace std;

int calcOpticalFlow(Mat& gray, Mat& prevgray);
#endif
