package lsieun.bytecode.core;

import lsieun.utils.radix.HexUtils;

public abstract class Node {
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getHexCode() {
        return HexUtils.fromBytes(this.bytes);
    }
}
