/*
 * Copyright (c) 2013-2023, APT Group, Department of Computer Science,
 * The University of Manchester.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package uk.ac.manchester.tornado.api.types.arrays;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_INT;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import uk.ac.manchester.tornado.api.internal.annotations.SegmentElementSize;

/**
 * This class represents an array of bytes stored in native memory.
 * The byte data is stored in a {@link MemorySegment}, which represents a contiguous region of off-heap memory.
 * The class also encapsulates methods for setting and getting byte values,
 * for initializing the byte array, and for converting the array to and from different representations.
 */
@SegmentElementSize(size = 1)
public final class ByteArray extends TornadoNativeArray {
    private static final int BYTE_BYTES = 1;
    private MemorySegment segment;
    private int numberOfElements;
    private int arrayHeaderSize;

    private int baseIndex;
    private int arraySizeHeaderPosition;

    private long segmentByteSize;

    /**
     * Constructs a new instance of the {@code ByteArray} that will store a user-specified number of elements.
     * @param numberOfElements The number of elements in the array.
     */
    public ByteArray(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        arrayHeaderSize = (int) TornadoNativeArray.ARRAY_HEADER;
        baseIndex = arrayHeaderSize / BYTE_BYTES;
        arraySizeHeaderPosition = baseIndex - 4;
        segmentByteSize = numberOfElements * BYTE_BYTES + arrayHeaderSize;

        segment = Arena.ofAuto().allocate(segmentByteSize, 1);
        segment.setAtIndex(JAVA_INT, 0, numberOfElements);
    }

    /**
     * Internal method used to create a new instance of the {@code ByteArray} from on-heap data.
     * @param values The on-heap byte array to create the instance from.
     * @return A new {@code ByteArray} instance, initialized with values of the on-heap byte array.
     */
    private static ByteArray createSegment(byte[] values) {
        ByteArray array = new ByteArray(values.length);
        for (int i = 0; i < values.length; i++) {
            array.set(i, values[i]);
        }
        return array;
    }

    /**
     * Creates a new instance of the {@code ByteArray} class from an on-heap byte array.
     * @param values The on-heap byte array to create the instance from.
     * @return A new {@code ByteArray} instance, initialized with values of the on-heap byte array.
     */
    public static ByteArray fromArray(byte[] values) {
        return createSegment(values);
    }

    /**
     * Creates a new instance of the {@code ByteArray} class from a set of byte values.
     * @param values The byte values to initialize the array with.
     * @return A new {@code ByteArray} instance, initialized with the given values.
     */
    public static ByteArray fromElements(byte... values) {
        return createSegment(values);
    }

    /**
     * Creates a new instance of the {@code ByteArray} class from a {@link MemorySegment}.
     * @param segment The {@link MemorySegment} containing the off-heap byte data.
     * @return A new {@code ByteArray} instance, initialized with the segment data.
     */
    public static ByteArray fromSegment(MemorySegment segment) {
        long byteSize = segment.byteSize();
        int numElements = (int) (byteSize / BYTE_BYTES);
        ByteArray byteArray = new ByteArray(numElements);
        MemorySegment.copy(segment, 0, byteArray.segment, byteArray.baseIndex * BYTE_BYTES, byteSize);
        return byteArray;
    }

    /**
     * Converts the byte data from off-heap to on-heap, by copying the values of a {@code ByteArray}
     * instance into a new on-heap array.
     * @return A new on-heap byte array, initialized with the values stored in the {@code ByteArray} instance.
     */
    public byte[] toHeapArray() {
        byte[] outputArray = new byte[getSize()];
        for (int i = 0; i < getSize(); i++) {
            outputArray[i] = get(i);
        }
        return outputArray;
    }

    /**
     * Sets the byte value at a specified index of the {@code ByteArray} instance.
     * @param index The index at which to set the byte value.
     * @param value The byte value to store at the specified index.
     */
    public void set(int index, byte value) {
        segment.setAtIndex(JAVA_BYTE, baseIndex + index, value);
    }

    /**
     * Gets the byte value stored at the specified index of the {@code ByteArray} instance.
     * @param index The index of which to retrieve the byte value.
     * @return
     */
    public byte get(int index) {
        return segment.getAtIndex(JAVA_BYTE, baseIndex + index);
    }

    /**
     * Sets all the values of the {@code ByteArray} instance to zero.
     */
    @Override
    public void clear() {
        init((byte) 0);
    }

    /**
     * Initializes all the elements of the {@code ByteArray} instance with a specified value.
     * @param value The byte value to initialize the {@code ByteArray} instance with.
     */
    public void init(byte value) {
        for (int i = 0; i < getSize(); i++) {
            segment.setAtIndex(JAVA_BYTE, baseIndex + i, value);
        }
    }

    /**
     * Returns the number of byte elements stored in the {@code ByteArray} instance.
     * @return
     */
    @Override
    public int getSize() {
        return numberOfElements;
    }

    /**
     * Returns the underlying {@link MemorySegment} of the {@code ByteArray} instance.
     * @return The {@link MemorySegment} associated with the {@code ByteArray} instance.
     */
    @Override
    public MemorySegment getSegment() {
        return segment;
    }

    /**
     * Returns the total number of bytes that the {@link MemorySegment}, associated with the {@code ByteArray} instance, occupies.
     * @return The total number of bytes of the {@link MemorySegment}.
     */
    @Override
    public long getNumBytesOfSegment() {
        return segmentByteSize;
    }

    /**
     * Returns the number of bytes of the {@link MemorySegment} that is associated with the {@code ByteArray} instance,
     * excluding the header bytes.
     * @return The number of bytes of the raw data in the {@link MemorySegment}.
     */
    @Override
    public long getNumBytesWithoutHeader() {
        return segmentByteSize - TornadoNativeArray.ARRAY_HEADER;
    }
}
