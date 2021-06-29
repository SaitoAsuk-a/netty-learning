package cn.jsbintask.netty.server.chatroom1.client;

import cn.hutool.core.util.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.util.Objects;

/**
 * @author liyu
 * date 2021/6/28 18:44
 * description
 */
public class EnCoderUtil {
    /**
     * 根据byteBuf的readerIndex和writerIndex计算校验码
     * 校验码规则：从消息头开始，同后一字节异或，直到校验码前一个字节，占用 1 个字节
     *
     * @param byteBuf
     * @return
     */
    public static byte xorSumBytes(ByteBuf byteBuf, int offset, int length) {
        byte sum = byteBuf.getByte(offset);
        for (int i = offset + 1; i < length; i++) {
            sum = (byte) (sum ^ byteBuf.getByte(i));
        }
        return sum;
    }

    public static byte xorSumBytes(ByteBuf byteBuf) {
        return xorSumBytes(byteBuf, 0, byteBuf.writerIndex() - 1);
    }

    public static boolean check(ByteBuf byteBuf, int offset, int length, byte checkSum) {
        return Objects.equals(xorSumBytes(byteBuf, offset, length), checkSum);
    }

    public static void main(String[] args) {
        ByteBuf e200199090190156173063 = Unpooled.copiedBuffer(ByteBufUtil.decodeHexDump("0e0403e80015f702000000"));
        byte b = xorSumBytes(e200199090190156173063);
        System.out.println("b = " + b);
        String s = byteToHex(b);
        System.out.println("s = " + s);
    }

    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }
}
