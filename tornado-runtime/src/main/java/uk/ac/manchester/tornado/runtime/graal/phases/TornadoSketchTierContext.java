/*
 * Copyright (c) 2018, 2020, APT Group, Department of Computer Science,
 * The University of Manchester. All rights reserved.
 * Copyright (c) 2009, 2017, Oracle and/or its affiliates. All rights reserved.
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
package uk.ac.manchester.tornado.runtime.graal.phases;

import org.graalvm.compiler.phases.OptimisticOptimizations;
import org.graalvm.compiler.phases.PhaseSuite;
import org.graalvm.compiler.phases.tiers.HighTierContext;
import org.graalvm.compiler.phases.util.Providers;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import uk.ac.manchester.tornado.api.common.Access;

public class TornadoSketchTierContext extends HighTierContext {

    private final ResolvedJavaMethod method;

    /**
     * Contains the argument accesses of the {@link #method}. The array gets populated in the {@link TornadoDataflowAnalysis} phase.
     * It includes accesses of arguments passed to non-inlined callees of the {@link #method}.
     */
    private final Access[] argumentAccess;

    public TornadoSketchTierContext(Providers providers, PhaseSuite<HighTierContext> graphBuilderSuite, OptimisticOptimizations optimisticOpts, ResolvedJavaMethod method) {
        super(providers, graphBuilderSuite, optimisticOpts);
        this.method = method;
        int parameterCount = method.getParameters().length;
        this.argumentAccess = new Access[method.isStatic() ? parameterCount : parameterCount + 1];
    }

    public ResolvedJavaMethod getMethod() {
        return method;
    }

    public Access[] getAccesses() {
        return argumentAccess;
    }
}
