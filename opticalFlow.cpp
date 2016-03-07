

#include "opencv2/video/tracking.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"

#include <iostream>

using namespace cv;
using namespace std;

static int drawOptFlowMap(const Mat& flow, Mat& cflowmap, int step,
	double, const Scalar& color)
{
	int upCount = 0, downCount = 0, leftCount = 0, rightCount = 0;
	for (int y = 0; y < cflowmap.rows; y += step)
	for (int x = 0; x < cflowmap.cols; x += step)
	{
		const Point2f& fxy = flow.at<Point2f>(y, x);
		int ydiff = cvRound(y + fxy.y) - y;
		int xdiff = cvRound(x + fxy.x) - x;
		if (5 > abs(xdiff) && 5 > abs(ydiff)) continue;
		if (abs(ydiff) > abs(xdiff)) {
			if (ydiff > 0) {
				if (y > cflowmap.rows / 2) ++downCount; else ++upCount;
			}
			else {
				if (y > cflowmap.rows / 2) ++upCount; else ++downCount;
			}
		} else {
			if (xdiff > 0) ++leftCount; else ++rightCount;
		}

		//line(cflowmap, Point(x, y), Point(cvRound(x + fxy.x), cvRound(y + fxy.y)),
			//color);
		//circle(cflowmap, Point(x, y), 2, color, -1);
	}
	//cout << "Counters: Down: " << downCount << "Up:" << upCount << "Left:" << leftCount << "Right:" << rightCount << endl;
	int sum = upCount + downCount + leftCount + rightCount;
	if (sum < 5) return -1;
	if (downCount > upCount || sum < 15) return 0;
	if (sum > 55) return 1;
	return 2;
}

int calcOpticalFlow(Mat& gray, Mat& prevgray)
{
		Mat flow;
		calcOpticalFlowFarneback(prevgray, gray, flow, 0.5, 3, 15, 3, 5, 1.2, 0);
		int result = 0;
		result = drawOptFlowMap(flow, flow, 16, 1.5, Scalar(0, 255, 0));
		return result;
}
