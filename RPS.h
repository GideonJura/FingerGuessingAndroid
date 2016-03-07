//#ifndef _RPS_Included
//#define _RPS_Included
//#include "Common.h"
//#include "SkinDetector.h"
////////////////////
//// RPS:
////
//class RPS
//{
//public: // types / typdefs:
//	enum Gestures
//	{
//		eUnknown,
//		eRock,
//		ePaper,
//		eScissors
//	};
//public: // methods:
//
//	/** starts the game, maybe gui callbacks maybe whatever */
//	int Detect(Mat& frame);
//
//private:
//	bool DetectGesture(Mat& frame);
//	void ExtractContourAndHull(Mat& skin, bool retry);
//	int DrawAndComputeFingersNum();
//
//private: // members:
//	typedef std::vector<Vec4i>							Points4_t;
//	typedef std::vector<Point>							Points_t;
//	typedef std::vector<Points_t>						VectorOfPoints_t;
//	typedef std::vector<int>								Ints_t;
//
//	SkinDetector				m_detector;
//	VectorOfPoints_t			m_contours;
//	Points4_t					m_hierarchy;
//	double						m_width;
//	double						m_height;
//	VectorOfPoints_t::iterator	m_biggestContour;
//	Points_t					m_hull;
//	Mat							m_current;
//	Points4_t					m_defects;
//	Ints_t						m_hullI;
//	RotatedRect					m_box;
//}; // RPS
//#endif
