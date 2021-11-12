/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_webdao_analyze_Analyze */

#ifndef _Included_com_webdao_analyze_Analyze
#define _Included_com_webdao_analyze_Analyze
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    analyzeOneWave
 * Signature: ([FLcom/webdao/analyze/ANALYZE_PARAMTER;)I
 */
JNIEXPORT jint JNICALL Java_com_webdao_analyze_Analyze_analyzeOneWave
  (JNIEnv *, jobject, jfloatArray, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculateC
 * Signature: (IILcom/webdao/analyze/ANALYZE_PARAMTER;)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_calculateC
  (JNIEnv *, jobject, jint, jint, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculateF
 * Signature: (ILcom/webdao/analyze/ANALYZE_PARAMTER;)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_calculateF
  (JNIEnv *, jobject, jint, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculateL
 * Signature: (IILcom/webdao/analyze/ANALYZE_PARAMTER;)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_calculateL
  (JNIEnv *, jobject, jint, jint, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculatePileBottomPoint
 * Signature: (ILcom/webdao/analyze/ANALYZE_PARAMTER;)I
 */
JNIEXPORT jint JNICALL Java_com_webdao_analyze_Analyze_calculatePileBottomPoint
  (JNIEnv *, jobject, jint, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculateR
 * Signature: (FFLcom/webdao/analyze/ANALYZE_PARAMTER;)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_calculateR
  (JNIEnv *, jobject, jfloat, jfloat, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculateSampleSpace
 * Signature: (Lcom/webdao/analyze/ANALYZE_PARAMTER;)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_calculateSampleSpace
  (JNIEnv *, jobject, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculateT
 * Signature: (IILcom/webdao/analyze/ANALYZE_PARAMTER;)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_calculateT
  (JNIEnv *, jobject, jint, jint, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    calculateV
 * Signature: (I[F)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_calculateV
  (JNIEnv *, jobject, jint, jfloatArray);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    exchangeOneWaveToFrequencyWave
 * Signature: ([F[FSLcom/webdao/analyze/ANALYZE_PARAMTER;[I)F
 */
JNIEXPORT jfloat JNICALL Java_com_webdao_analyze_Analyze_exchangeOneWaveToFrequencyWave
  (JNIEnv *, jobject, jfloatArray, jfloatArray, jshort, jobject, jintArray);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    findFirstWavePosition
 * Signature: ([FLcom/webdao/analyze/ANALYZE_PARAMTER;)I
 */
JNIEXPORT jint JNICALL Java_com_webdao_analyze_Analyze_findFirstWavePosition
  (JNIEnv *, jobject, jfloatArray, jobject);

/*
 * Class:     com_webdao_analyze_Analyze
 * Method:    findFrequencyPeak
 * Signature: ([FFI[I)I
 */
JNIEXPORT jint JNICALL Java_com_webdao_analyze_Analyze_findFrequencyPeak
  (JNIEnv *, jobject, jfloatArray, jfloat, jint, jintArray);

#ifdef __cplusplus
}
#endif
#endif
