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

/**
 * Object created when the {@link TornadoExecutionPlan#execute()} is finished.
 * This objects stores the results of the execution. Additionally, if the
 * execution plan enabled the profiler information, this object also stores all
 * profiler information (e.g., read/write time, kernel time, etc.) through the
 * {@link TornadoProfilerResult} object.
 *
 * @since TornadoVM-0.15
 */
public class TornadoExecutionResult {

    private TornadoProfilerResult tornadoProfilerResult;

    TornadoExecutionResult(TornadoProfilerResult profilerResult) {
        this.tornadoProfilerResult = profilerResult;
    }

    /**
     * Method to obtain the profiler information associated to the latest execution
     * plan. Note that, all timers associated to the profiler are enabled only if
     * the execution plan enables the profiler.
     *
     * @return {@link TornadoProfilerResult}
     */
    public TornadoProfilerResult getProfilerResult() {
        return tornadoProfilerResult;
    }

    /**
     * Transfer data from device to host. This is applied for all immutable
     * task-graphs within an executor. This method is used when a task-graph defines
     * transferToHost using the
     * {@link uk.ac.manchester.tornado.api.enums.DataTransferMode#USER_DEFINED}.
     * This indicates the runtime to not to copy-out the data en every iteration and
     * transfer the data under demand.
     *
     * @param objects
     *     Host objects to transfer the data to.
     *
     * @return {@link TornadoExecutionResult}
     */
    public TornadoExecutionResult transferToHost(Object... objects) {
        tornadoProfilerResult.getExecutor().transferToHost(objects);
        return this;
    }

    /**
     * It returns true if all task-graphs associated to the executor finished
     * execution.
     *
     * @return boolean
     */
    public boolean isReady() {
        return tornadoProfilerResult.getExecutor().isFinished();
    }

}
