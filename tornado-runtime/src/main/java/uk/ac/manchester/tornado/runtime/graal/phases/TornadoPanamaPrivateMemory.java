/*
 * Copyright (c) 2020, APT Group, Department of Computer Science,
 * School of Engineering, The University of Manchester. All rights reserved.
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

import java.util.Optional;

import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.nodes.GraphState;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.java.NewArrayNode;
import org.graalvm.compiler.nodes.java.NewInstanceNode;
import org.graalvm.compiler.phases.BasePhase;

import uk.ac.manchester.tornado.runtime.graal.nodes.PanamaPrivateMemoryNode;

public class TornadoPanamaPrivateMemory extends BasePhase<TornadoSketchTierContext> {

    @Override
    public Optional<NotApplicable> notApplicableTo(GraphState graphState) {
        return ALWAYS_APPLICABLE;
    }

    @Override
    protected void run(StructuredGraph graph, TornadoSketchTierContext context) {
        // replace PanamaPrivateMemoryNodes with NewArrayNodes
        for (PanamaPrivateMemoryNode privatePanama : graph.getNodes().filter(PanamaPrivateMemoryNode.class)) {
            NewArrayNode privateArray = new NewArrayNode(privatePanama.getResolvedJavaType(), privatePanama.getLength(), true);
            graph.addOrUnique(privateArray);
            if (privatePanama.predecessor() instanceof NewInstanceNode) {
                NewInstanceNode newPanamaTypeInstance = (NewInstanceNode) privatePanama.predecessor();
                newPanamaTypeInstance.replaceAtUsages(privateArray);
                removeFixed(newPanamaTypeInstance);
            }
            insertFixed(privatePanama, privateArray);
        }

    }

    public static void removeFixed(Node n) {
        if (!n.isDeleted()) {
            Node pred = n.predecessor();
            Node suc = n.successors().first();

            n.replaceFirstSuccessor(suc, null);
            n.replaceAtPredecessor(suc);
            pred.replaceFirstSuccessor(n, suc);

            for (Node us : n.usages()) {
                n.removeUsage(us);
            }
            n.clearInputs();

            n.safeDelete();
        }
    }

    public static void insertFixed(Node panama, Node array) {
        Node pred = panama.predecessor();
        pred.replaceFirstSuccessor(panama, array);
        array.replaceFirstSuccessor(null, panama);
    }
}
