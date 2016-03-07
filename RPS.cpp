//
//#include "RPS.h"
//
//
///** : */
//int RPS::Detect(Mat& frame)
//{
//	m_width = frame.cols;
//	m_height = frame.rows;
//	m_current = frame;
//
//	Mat skin = Mat::zeros((int)frame.cols, frame.rows, frame.type());
//	if (!m_detector.Detect(frame, skin)) { return 0;}
//	ExtractContourAndHull(skin, true);
//	if (m_box.size.height*m_box.size.width < 1000) return -1;
//	int fingers = DrawAndComputeFingersNum();
//	return fingers;
//}
//
///** skin is changed to mask of the finger */
//bool RPS::DetectGesture(Mat& skin)
//{
//	Rect biggestSize = Rect(0, 0, 0, 0), contourSize;
//
//	findContours(skin, m_contours, m_hierarchy, CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
//	if (m_contours.empty()) {
//		cout << "Warning: Could not find any skin colored contours !! put ya finger in da screen boy!";
//		return false;
//	}
//	VectorOfPoints_t::iterator contour = m_contours.begin(), end = m_contours.end();
//
//	for (; contour != end; ++contour) {
//		contourSize = boundingRect(*contour);
//		if (m_height - (contourSize.y + contourSize.height) <= 5 && contourSize.area() > biggestSize.area()) {
//			biggestSize = contourSize;
//			m_biggestContour = contour;
//		}
//	}
//
//	approxPolyDP(*m_biggestContour, *m_biggestContour, arcLength(*m_biggestContour, true) * .0025, true);
//	convexHull(*m_biggestContour, m_hull, true);
//
//	return true;
//}
//
///** : */
//void RPS::ExtractContourAndHull(Mat& skin, bool retry)
//{
//	// remove the clone for efficiency once algo's working good
//	findContours(skin.clone(), m_contours, m_hierarchy, CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
//	if (m_contours.empty()) {
//		cout << "Warning: Could not find any skin colored contours !! put ya finger in da screen boy!";
//		if (retry) {
//			Mat retSkin = Mat::zeros((int)m_current.cols, m_current.rows, m_current.type());
//			m_detector.Detect(m_current, retSkin, 1);
//			ExtractContourAndHull(retSkin, false);
//		}
//		return;
//	}
//	VectorOfPoints_t::iterator contour = m_contours.begin(), end = m_contours.end();
//	Rect biggestSize = Rect(0, 0, 0, 0), contourSize;
//	//drawContours(skin, m_contours, -1, Scalar(123, 123, 123));
//	m_biggestContour = m_contours.end();
//	for (; contour != end; ++contour) {
//		contourSize = boundingRect(*contour);
//		// for vertical
//		//if (m_height - (contourSize.y + contourSize.height) <= 5 && contourSize.area() > biggestSize.area())
//		// for horizontal
//		if (m_width - (contourSize.x + contourSize.width) <= 5 && contourSize.area() > biggestSize.area())
//		{
//			biggestSize = contourSize;
//			m_biggestContour = contour;
//		}
//	}
//	if (m_biggestContour == m_contours.end()) {
//		if (retry) {
//			Mat retSkin = Mat::zeros((int)m_current.cols, m_current.rows, m_current.type());
//			m_detector.Detect(m_current, retSkin, 1);
//			ExtractContourAndHull(retSkin, false);
//		} else {
//			m_biggestContour = m_contours.begin();
//			biggestSize = boundingRect(*m_biggestContour);
//		}
//
//	}
//	approxPolyDP(*m_biggestContour, *m_biggestContour, arcLength(*m_biggestContour, true) * .0025, true);
//
//	convexHull(*m_biggestContour, m_hull, true);
//	convexHull(*m_biggestContour, m_hullI, true, true);
//
//	m_box = minAreaRect(*m_biggestContour);
//	polylines(m_current, m_hull, true, Scalar(200, 125, 75), 2);
//	circle(m_current, Point(m_box.center.x, m_box.center.y), 3, Scalar(200, 125, 75), 2);
//
//	// filter hull
//	Points_t::iterator point = m_hull.begin();
//	Ints_t::iterator hullI = m_hullI.begin();
//	int len = m_hull.size();
//	for (int i = 0; i < len - 1; ++i){
//		Point p = *point;
//		Point p2 = *(point + 1);
//		if (std::sqrt(std::pow(p.x - p2.x, 2) + std::pow(p.y - p2.y, 2)) <= m_box.size.width / 10){
//			point = m_hull.erase(point);
//			hullI = m_hullI.erase(hullI);
//		}
//		else {
//			++point;
//			++hullI;
//		}
//	}
//
//	convexityDefects(*m_biggestContour, m_hullI, m_defects);
//}
//
///**: */
// int RPS::DrawAndComputeFingersNum()
//{
//	Points4_t::iterator defect = m_defects.begin(), end = m_defects.end();
//	int fingers = 0;
//	for (; defect != end; ++defect)
//	{
//		Vec4i& point = *defect;
//		VectorOfPoints_t::value_type& contour = *m_biggestContour;
//		Point& point0 = contour[point[0]];
//		Point& point1 = contour[point[1]];
//		Point& point2 = contour[point[2]];
//		Point2f startPoint(point0.x, point0.y);
//		Point2f endPoint(point1.x, point1.y);
//		Point2f depthPoint(point2.x, point2.y);
//
//
//
//		// horizontal
////		if ((startPoint.y < m_box.center.y || depthPoint.y < m_box.center.y || pow(startPoint.x - depthPoint.x,2) > m_box.size.width/5.5 ) && startPoint.y < depthPoint.y &&
//	//		(sqrt(pow(startPoint.x - depthPoint.x, 2) + pow(startPoint.y - depthPoint.y, 2)) > m_box.size.height / 6.5))
//		// vertical
//		//if ((startPoint.x < m_box.center.x || depthPoint.x < m_box.center.x || pow(startPoint.y - depthPoint.y,2) > m_box.size.width/5.5 ) && startPoint.x < depthPoint.x &&
//			//(sqrt(pow(startPoint.y - depthPoint.y, 2) + pow(startPoint.x - depthPoint.x, 2)) > m_box.size.height / 6.5))
//		// corrected vertical
//		if ((startPoint.x < m_box.center.x || depthPoint.x < m_box.center.x || pow(-1*startPoint.y - -1*depthPoint.y,2) > m_box.size.height/5.5 ) && startPoint.x < depthPoint.x &&
//					(sqrt(pow(-1*startPoint.y - -1*depthPoint.y, 2) + pow(startPoint.x - depthPoint.x, 2)) > m_box.size.width / 7))
//		{
//
//			circle(m_current, startPoint, 5, Scalar(0, 0, 255));
//			circle(m_current, depthPoint, 5, Scalar(0, 255, 0));
//			circle(m_current, endPoint, 5, Scalar(255, 0, 0));
//			fingers++;
//		}
//	}
//	return fingers;
//}
