package cn.jsbintask.netty.server.chatroom1.client;

/**
 * rfid标签相关常量
 */
public class RFIDTagConstant {
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
    /**
     * 1:14号线；2:16号线
     */
    public static byte PROJECT_ID_14 = 0x14;
    public static byte PROJECT_ID_16 = 0x16;

    /**
     * 线别 4:左线；5:右线；6：出线段；7：入线段；
     */
    public static byte LINE_TYPE_LEFT = 0x04;
    public static byte LINE_TYPE_RIGHT = 0x05;
    public static byte LINE_TYPE_ENTRANCE = 0x06;
    public static byte LINE_TYPE_EXIT = 0x07;
    /**
     * 应用类型
     * 1：铁鞋识别；2：线别绑定；3：线别解绑；
     */
    public static byte APPLICATION_TYPE_IRON_SHOE_RECOGNITION = 0x01;
    public static byte APPLICATION_TYPE_LINE_TYPE_BINDING = 0x02;
    public static byte APPLICATION_TYPE_LINE_TYPE_UNBINDING = 0x03;

    /**
     * safeControl
     * 车辆识别后绑定的线路类型
     */
    public static String RFID_CAR_LINE_TYPE_BINDING = "rfid:car:line:binding";
    /**
     * safetyManage
     * 记录每个tag绑定的线路类型
     */
    public static String TAG_LINE_TYPE_BINDING_PREFIX = "tag:line:binding:";

    /**
     * 超出距离自动解绑 单位m
     */
    public static double RFID_AUTO_UNBINDING_DISTANCE = 100;
    public static final short PKG_DELIMITER = 0xff;

}
