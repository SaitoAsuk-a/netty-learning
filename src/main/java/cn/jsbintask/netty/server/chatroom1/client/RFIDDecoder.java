package cn.jsbintask.netty.server.chatroom1.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j(topic = "RFIDDecoder")
public class RFIDDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        log.info("<<<<< ip:{},hex:{}", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(in));
        System.out.println("in = " + ByteBufUtil.hexDump(in));
        byte[] b = new byte[in.readableBytes()];
        ;
        RFIDMessageModel msg = decode(in.readBytes(b));
        if (msg != null) {
            out.add(msg);
        }
    }

    private RFIDMessageModel decode(ByteBuf in) {
        //校验
        byte checkSum = in.getByte(in.writerIndex() - 3);
//        in.writerIndex(in.writerIndex() - 3);//排除校验码
        boolean check = EnCoderUtil.check(in, 2, 12, checkSum);
        if (!check) {
            ReferenceCountUtil.safeRelease(in);
            return null;
        }
        //解码
        return parse(in);
    }

    /**
     * 标签规则：12个字节长度
     * 01 02 03 04 05 06 07 08 09 10 11 12
     * 其中：
     * 01  ：线路编号（14号线、16号线…… ）
     * 02 ： 线别（左线，右线，出场线……）
     * 03  04:   设备序列号   0-65025
     * 05 06 07 ：位置（0- 1677721.5 米，精度0.1)
     * 08 ：用途 （铁鞋识别，线别绑定，线别解绑。。。）
     * 09 属性 00代表小里程 01代表大里程
     * 10 保留字段
     * 11 12 校验码
     */
    private RFIDMessageModel parse(ByteBuf in) {
        RFIDMessageModel model = new RFIDMessageModel();
        in.resetReaderIndex();
        in.readByte();
        model.setReaderDeviceSerialNumber(in.readByte());
        model.setProjectId(in.readByte());
        model.setLineType(in.readByte());
        model.setDeviceSerialNumber(in.readShort());
        //todo 检测是否能正常读取负数
        model.setPosition(Arith.div(in.readMedium(), 10));
        model.setApplicationType(in.readByte());
        model.setAttributes(in.readByte());
        in.readShort();
        in.readByte();
        model.setAntennaSerialNumber(in.readByte());
        model.setReadTime(LocalDateTime.now());
        return model;
    }

}
