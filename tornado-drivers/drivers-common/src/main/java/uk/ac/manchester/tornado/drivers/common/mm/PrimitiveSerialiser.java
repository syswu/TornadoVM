/*
 * This file is part of Tornado: A heterogeneous programming framework:
 * https://github.com/beehive-lab/tornadovm
 *
 * Copyright (c) 2013-2020, APT Group, Department of Computer Science,
 * The University of Manchester. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
package uk.ac.manchester.tornado.drivers.common.mm;

import java.nio.ByteBuffer;

import uk.ac.manchester.tornado.runtime.common.Tornado;

public class PrimitiveSerialiser {

    private static void align(ByteBuffer buffer, int align) {
        while (buffer.position() % align != 0) {
            buffer.put((byte) 0);
        }
    }

    public static void put(ByteBuffer buffer, Object value) {
        put(buffer, value, 0);
    }

    public static void put(ByteBuffer buffer, Object value, int alignment) {
        if (value instanceof Integer) {
            buffer.putInt((int) value);
        } else if (value instanceof Long) {
            buffer.putLong((long) value);
        } else if (value instanceof Short) {
            buffer.putShort((short) value);
        } else if (value instanceof Float) {
            buffer.putFloat((float) value);
        } else if (value instanceof Double) {
            buffer.putDouble((double) value);
        } else {
            Tornado.warn("unable to serialise: %s (%s)", value, value.getClass().getName());
        }

        if (alignment != 0) {
            align(buffer, alignment);
        }
    }
}
