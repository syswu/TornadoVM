/*
 * This file is part of Tornado: A heterogeneous programming framework:
 * https://github.com/beehive-lab/tornado
 *
 * Copyright (c) 2013-2017 APT Group, School of Computer Science,
 * The University of Manchester
 *
 * This work is partially supported by EPSRC grants:
 * Anyscale EP/L000725/1 and PAMELA EP/K008730/1.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: James Clarkson
 *
 */
package tornado.drivers.opencl.graal;

import tornado.drivers.opencl.graal.lir.OCLKind;

public class OCLStampFactory {

    private static final OCLStamp[] stamps = new OCLStamp[OCLKind.values().length];

    public static OCLStamp getStampFor(OCLKind kind) {
        int index = 0;
        for (OCLKind oclKind : OCLKind.values()) {
            if (oclKind == kind) {
                break;
            }
            index++;
        }

        if (stamps[index] == null) {
            stamps[index] = new OCLStamp(kind);
        }

//        System.out.printf("OCLStampFactory: kind=%s -> stamp=%s\n", kind, stamps[index]);
        return stamps[index];
    }

}
