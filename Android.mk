LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

OPENCV_INSTALL_MODULES:=on 
include C:/Users/home/Desktop/softwares/android_opencv_integration/OpenCV-android-sdk/sdk/native/jni/OpenCV.mk

LOCAL_MODULE    := mixed_sample
LOCAL_SRC_FILES := RPS.cpp SkinDetector.cpp opticalFlow.cpp main.cpp 
LOCAL_LDLIBS +=  -llog -ldl

include $(BUILD_SHARED_LIBRARY)
