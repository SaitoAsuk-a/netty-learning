package cn.jsbintask.netty.server.chatroom1.client;

import cn.jsbintask.netty.server.chatroom1.common.Constants;
import cn.jsbintask.netty.server.chatroom1.common.Message;
import cn.jsbintask.netty.server.chatroom1.common.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

/**
 * @author jsbintask@gmail.com
 * @date 2019/1/30 14:38
 */
public class ClientMsgHandler extends SimpleChannelInboundHandler<RFIDMessageModel> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RFIDMessageModel msg) throws Exception {
        try {
            System.out.println("msg = " + msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
