package org.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

public class Test {
    ByteToMessageDecoder byteToMessageDecoder = null;
    ReplayingDecoder replayingDecoder = null;
    MessageToMessageDecoder messageToMessageDecoder = null;

    ByteBuf byteBuf = null;

}
