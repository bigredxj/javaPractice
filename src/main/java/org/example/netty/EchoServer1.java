package org.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class EchoServer1 {
    private final  int port;
    public EchoServer1(int port) {
        this.port = port;
    }

    public void start() throws Exception{
        EchoInServerHandler1 inServerHandler1 = new EchoInServerHandler1();
        EchoInServerHandler2 inServerHandler2 = new EchoInServerHandler2();
        EchoOutServerHandler1 outServerHandler1 = new EchoOutServerHandler1();
        EchoOutServerHandler2 outServerHandler2 = new EchoOutServerHandler2();

        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println(socketChannel.pipeline().getClass().getName());
                            socketChannel.pipeline().addLast(inServerHandler1);
                            socketChannel.pipeline().addLast(outServerHandler1);
                            socketChannel.pipeline().addLast(inServerHandler2);
                            socketChannel.pipeline().addLast(outServerHandler2);
                        }
                    });
            ChannelFuture f = b.bind().sync();
            System.out.println("aaaaaaaaaaaa");
            f.channel().closeFuture().sync();
            System.out.println("bbbbbbbbbbbb");

        }finally {
            group.shutdownGracefully().sync();
            System.out.println("shutdownGracefully");
        }

    }

    @ChannelHandler.Sharable
    public class EchoInServerHandler1 extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
            System.out.println("************************in1 执行************************");
            ByteBuf in = (ByteBuf) msg;
            System.out.println("in Server1 received: "+in.toString(CharsetUtil.UTF_8));
            //ctx.channel().writeAndFlush(Unpooled.wrappedBuffer((in.toString(CharsetUtil.UTF_8)+"-in server1-response").getBytes()));
            super.channelRead(ctx, Unpooled.wrappedBuffer(("test11111111111111111").getBytes()));
        }

    }

    @ChannelHandler.Sharable
    public class EchoInServerHandler2 extends ChannelInboundHandlerAdapter{

        @Override
        public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
            System.out.println("************************in2 执行************************");
            ByteBuf in = (ByteBuf) msg;
            System.out.println("in Server2 received: "+in.toString(CharsetUtil.UTF_8));
            super.channelRead(ctx,msg);
        }
    }

    @ChannelHandler.Sharable
    public class EchoOutServerHandler1 extends ChannelOutboundHandlerAdapter{

        @Override
        public void write(ChannelHandlerContext ctx , Object msg , ChannelPromise promise) throws Exception {
            System.out.println("************************out1 执行************************");
            ByteBuf byteMsg = (ByteBuf) msg;
            System.out.println("out1 received:"+byteMsg.toString(Charset.forName("utf8")));
            super.write(ctx,msg,promise);
        }
    }

    @ChannelHandler.Sharable
    public class EchoOutServerHandler2 extends ChannelOutboundHandlerAdapter{

        @Override
        public void write(ChannelHandlerContext ctx , Object msg , ChannelPromise promise) throws Exception {
            System.out.println("************************out2 执行************************");

            ByteBuf byteMsg = (ByteBuf) msg;
            System.out.println("out2 received:"+byteMsg.toString(Charset.forName("utf8")));
            super.write(ctx,msg,promise);
        }
    }

    public static void main(String[] args){
        try {
            int port = 3333;
            System.out.println("cccccccccccccccc");
            new EchoServer1(port).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}