package cn.jsbintask.netty.server.chatroom1.client;

import cn.hutool.core.io.checksum.CRC16;

import java.util.Objects;

public class CRC16Utils {

    /**
     * CRC16加密
     *
     * @param b
     * @return
     */
    public static int crc16Encoder(byte[] b) {
        return crc16Encoder(b, 0, b.length);
    }

    public static int crc16Encoder(byte[] b, int off, int length) {
        CRC16 crc16 = new CRC16();
        crc16.reset();
        crc16.update(b, off, length);
        return (int) crc16.getValue();
    }

    /**
     * crc16校检
     *
     * @param b
     * @param coder
     * @return
     */
    public static boolean crc16Check(byte[] b, int coder) {
        return Objects.equals(crc16Encoder(b), coder);
    }

    public static boolean crc16Check(byte[] b, int off, int length, int coder) {
        return Objects.equals(crc16Encoder(b, off, length), coder);
    }
}
