package uk.ac.manchester.tornado.drivers.spirv.graal.compiler;

import org.graalvm.compiler.nodes.cfg.Block;
import org.graalvm.compiler.nodes.cfg.ControlFlowGraph;

import uk.ac.manchester.tornado.drivers.spirv.common.SPIRVLogger;
import uk.ac.manchester.tornado.drivers.spirv.graal.asm.SPIRVAssembler;

public class SPIRVBlockVisitor implements ControlFlowGraph.RecursiveVisitor<Block> {

    private final SPIRVCompilationResultBuilder crb;
    private SPIRVAssembler assembler;

    public SPIRVBlockVisitor(SPIRVCompilationResultBuilder resultBuilder) {
        this.crb = resultBuilder;
        this.assembler = resultBuilder.getAssembler();
    }

    @Override
    public Block enter(Block b) {
        SPIRVLogger.trace("ENTERING BLOCK: " + b);
        if (b.getPredecessorCount() != 0) {
            // Do not generate a label for the first block. This was already generated in
            // the SPIR-V preamble because we need the declaration of all variables.
            assembler.emitBlockLabel(b, assembler.functionScope);
        }
        crb.emitBlock(b);
        return null;
    }

    @Override
    public void exit(Block b, Block value) {
        SPIRVLogger.trace("EXIT BLOCK: " + b);
        // empty implementation
    }
}
