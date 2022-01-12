/*
 * MIT License
 *
 * Copyright (c) 2021, APT Group, Department of Computer Science,
 * The University of Manchester.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel */

#ifndef _Included_uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel
#define _Included_uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel
 * Method:    zeKernelSuggestGroupSize_native
 * Signature: (JIII[I[I[I)I
 */
JNIEXPORT jint JNICALL Java_uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel_zeKernelSuggestGroupSize_1native
        (JNIEnv *, jobject, jlong, jint, jint, jint, jintArray, jintArray, jintArray);

/*
 * Class:     uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel
 * Method:    zeKernelSetGroupSize_native
 * Signature: (JIII)I
 */
JNIEXPORT jint JNICALL Java_uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel_zeKernelSetGroupSize_1native
        (JNIEnv *, jobject, jlong, jint, jint, jint);

/*
 * Class:     uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel
 * Method:    zeKernelSetArgumentValue_nativePtrArg
 * Signature: (JIIJ)I
 */
JNIEXPORT jint JNICALL Java_uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel_zeKernelSetArgumentValue_1nativePtrArg
        (JNIEnv *, jobject, jlong, jint, jint, jlong);

/*
 * Class:     uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel
 * Method:    zeKernelSetCacheConfig_native
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_uk_ac_manchester_tornado_drivers_spirv_levelzero_LevelZeroKernel_zeKernelSetCacheConfig_1native
        (JNIEnv *, jobject, jlong, jint);

#ifdef __cplusplus
}
#endif
#endif
