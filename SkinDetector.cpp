//#include "SkinDetector.h"
//
//
//const int alpha_slider_max = 255;
///** c'tor */
//SkinDetector::SkinDetector()
//{
//}
//Mat copy;
//Mat yccC;
//bool stopped = false;
///** d'tor */
//SkinDetector::~SkinDetector()
//{
//}
//
//#ifdef DEBUG_VS
//void on_trackbar(int, void*)
//{
//	stopped = true;
//	minn = Scalar(min1[0], min2[0], min3[0]);
//	maxx = Scalar(max1[0], max2[0], max3[0]);
//	Mat skin = copy.clone();
//	cvtColor(skin, skin, COLOR_RGB2YCrCb);
//
//	//filter the image in YCrCb color space
//	cv::inRange(skin, minn, maxx, skin);
//
//	auto rect_12 = getStructuringElement(CV_SHAPE_RECT, Size(3, 3), Point(2, 2));
//	erode(skin, skin, rect_12);
//	auto rect_6 = getStructuringElement(CV_SHAPE_RECT, Size(9, 9), Point(4, 4));
//	dilate(skin, skin, rect_6);
//
//	//imshow("skindebug", skin);
//	//dilate(skin, skin, rect_6,Point(-1, -1), 2);
//	// print image to screen
//	imshow("skindebug", skin);
//	waitKey(1);
//}
//#endif
//int min1[] = { 0, 111 }, min2[] = { 133, 75 }, min3[] = { 77, 0 };
//int max1[] = { 255, 168 }, max2[] = { 173, 210 }, max3[] = { 127, 255 };
//
//bool SkinDetector::Detect(Mat& input, Mat& skin) {
//	Detect(input, skin, 0);
//	if (countNonZero(skin) > skin.rows * skin.cols * 0.9) return true;
//	Detect(input, skin, 1);
//	return true;
//}
//
//bool SkinDetector::Detect(Mat& input, Mat& skin, int idx) {
//	m_min = Scalar(min1[idx], min2[idx], min3[idx]);
//	m_max = Scalar(max1[idx], max2[idx], max3[idx]);
//
//	//first convert our RGB image to YCrCb
//	cv::cvtColor(input, skin, cv::COLOR_RGB2YCrCb);
//
//	//filter the image in YCrCb color space
//	cv::inRange(skin,m_min,m_max, skin);
//
//	auto rect_12 = getStructuringElement(CV_SHAPE_RECT, Size(3, 3), Point(2, 2));
//	erode(skin, skin, rect_12);
//	auto rect_6 = getStructuringElement(CV_SHAPE_RECT, Size(9, 9), Point(4, 4));
//	dilate(skin, skin, rect_6);
//
//#ifdef DEBUG_VS
//	imshow("skindebug", skin);
//	// print image to screen
//	namedWindow("skindebug", 1);
//	createTrackbar("min1", "skindebug", &min1[0], alpha_slider_max, on_trackbar);
//	createTrackbar("min3", "skindebug", &min3[0], alpha_slider_max, on_trackbar);
//	createTrackbar("min2", "skindebug", &min2[0], alpha_slider_max, on_trackbar);
//	createTrackbar("max1", "skindebug", &max1[0], alpha_slider_max, on_trackbar);
//	createTrackbar("max3", "skindebug", &max3[0], alpha_slider_max, on_trackbar);
//	createTrackbar("max2", "skindebug", &max2[0], alpha_slider_max, on_trackbar);
//	imshow("skindebug", skin);
//	if (stopped)
//	while (true) waitKey(1);
//#endif
//	return true;
//}
//
