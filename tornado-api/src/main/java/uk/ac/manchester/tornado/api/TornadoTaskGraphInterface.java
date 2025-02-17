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
package uk.ac.manchester.tornado.api;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

import uk.ac.manchester.tornado.api.common.Access;
import uk.ac.manchester.tornado.api.common.SchedulableTask;
import uk.ac.manchester.tornado.api.common.TaskPackage;
import uk.ac.manchester.tornado.api.common.TornadoDevice;
import uk.ac.manchester.tornado.api.enums.ProfilerMode;
import uk.ac.manchester.tornado.api.memory.TaskMetaDataInterface;
import uk.ac.manchester.tornado.api.profiler.ProfileInterface;

public interface TornadoTaskGraphInterface extends ProfileInterface {

    SchedulableTask getTask(String taskNameID);

    TornadoDevice getDevice();

    void setDevice(TornadoDevice device);

    TornadoDevice getDeviceForTask(String id);

    void addInner(SchedulableTask task);

    boolean isLastDeviceListEmpty();

    void scheduleInner();

    void batch(String batchSize);

    void apply(Consumer<SchedulableTask> consumer);

    void mapAllToInner(TornadoDevice device);

    void dumpTimes();

    void dumpProfiles();

    void dumpEvents();

    void clearProfiles();

    void waitOn();

    void transferToDevice(int mode, Object... objects);

    void transferToHost(int mode, Object... objects);

    void dump();

    void warmup();

    void freeDeviceMemory();

    void syncRuntimeTransferToHost(Object... objects);

    String getId();

    TaskMetaDataInterface meta();

    TornadoTaskGraphInterface schedule();

    TornadoTaskGraphInterface schedule(GridScheduler gridScheduler);

    TornadoTaskGraphInterface scheduleWithProfile(Policy policy);

    TornadoTaskGraphInterface scheduleWithProfileSequential(Policy policy);

    void addTask(TaskPackage taskPackage);

    void addPrebuiltTask(String id, String entryPoint, String filename, Object[] args, Access[] accesses, TornadoDevice device, int[] dimensions);

    void addPrebuiltTask(String id, String entryPoint, String filename, Object[] args, Access[] accesses, TornadoDevice device, int[] dimensions, int[] atomics);

    void addScalaTask(String id, Object function, Object[] args);

    String getTaskGraphName();

    void replaceParameter(Object oldParameter, Object newParameter);

    void useDefaultThreadScheduler(boolean use);

    boolean isFinished();

    Set<Object> getArgumentsLookup();

    TornadoTaskGraphInterface createImmutableTaskGraph();

    Collection<?> getOutputs();

    void enableProfiler(ProfilerMode profilerMode);

    void disableProfiler(ProfilerMode profilerMode);
}
