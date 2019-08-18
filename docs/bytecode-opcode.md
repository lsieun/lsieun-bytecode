# bytecode-opcode

## Instruction

classifier

- Instruction
  - ArithmeticInstruction
  - ArrayInstruction
  - BranchInstruction
    - IfInstruction
    - GotoInstruction
    - SelectInstruction
    - JsrInstruction
  - CompareInstruction
  - ConstantPushInstruction
  - ConversionInstruction
  - CPInstruction
    - FieldInstruction
    - InvokeInstruction
  - LocalVariableInstruction
    - LoadInstruction
    - StoreInstruction
  - ReturnInstruction
  - StackInstruction

facet

- AllocationInstruction
- IndexedInstruction
- LoadClass
- StackConsumer
    - PopInstruction

## Handle

Handle是“Instruction Handle”的缩写

