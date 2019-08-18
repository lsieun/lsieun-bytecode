package lsieun.bytecode.opcode.facet;

public interface SelectInstruction extends BranchInstruction, VariableLengthInstruction, StackConsumer, StackProducer {
    int[] getMatches();
    int[] getOffsets();
}
