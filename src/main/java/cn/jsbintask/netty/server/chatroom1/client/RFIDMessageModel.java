package cn.jsbintask.netty.server.chatroom1.client;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

import static cn.jsbintask.netty.server.chatroom1.client.RFIDTagConstant.APPLICATION_TYPE_LINE_TYPE_BINDING;
import static cn.jsbintask.netty.server.chatroom1.client.RFIDTagConstant.APPLICATION_TYPE_LINE_TYPE_UNBINDING;

@Data
public class RFIDMessageModel {
    private Byte readerDeviceSerialNumber;
    private Byte projectId;
    private Byte lineType;
    private Short deviceSerialNumber;
    private Double position;
    private Integer triggerTagId;
    private Byte applicationType;
    private Byte attributes;
    private Byte antennaSerialNumber;
    private LocalDateTime readTime;

    public boolean isLineTypeBinding() {
        return Objects.equals(APPLICATION_TYPE_LINE_TYPE_BINDING, this.applicationType);
    }
    public boolean isLineTypeUnBind() {
        return Objects.equals(APPLICATION_TYPE_LINE_TYPE_UNBINDING, this.applicationType);
    }
}
