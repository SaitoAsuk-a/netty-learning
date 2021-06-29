package cn.jsbintask.netty.server.chatroom1.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @Auther: hardenxu
 * @Date: 2021/4/21 17:03
 */
public class HexUtils {
    /**
     * @Title:bytes2HexString
     * @Description:字节数组转16进制字符串
     * @param b
     * 字节数组
     * @return 16进制字符串
     * @throws
     */
    public static String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(String.format("%02X",b[i]));
        }
        return result.toString();
    }

    /**
     * @Title:hexString2Bytes
     * @Description:16进制字符串转字节数组
     * @param src
     * 16进制字符串
     * @return 字节数组
     * @throws
     */
    public static byte[] hexString2Bytes(String src) {
        src = src.replaceAll(" ", "");
        byte[] bytes = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            String subStr = src.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }


    /**
     * @Title:string2HexUTF8
     * @Description:字符UTF8串转16进制字符串
     * @param strPart
     * 字符串
     * @return 16进制字符串
     * @throws
     */
    public static String string2HexUTF8(String strPart) {

        return string2HexString(strPart,"UTF-8");
    }

    /**
     * @Title:string2HexUTF8
     * @Description:字符UTF-16LE串转16进制字符串,此UTF-16LE等同于C#中的Unicode
     * @param strPart
     * 字符串
     * @return 16进制字符串
     * @throws
     */
    public static String string2HexUTF16LE(String strPart) {

        return string2HexString(strPart,"UTF-16LE");
    }

    /**
     * @Title:string2HexUnicode
     * @Description:字符Unicode串转16进制字符串
     * @param strPart
     * 字符串
     * @return 16进制字符串
     * @throws
     */
    public static String string2HexUnicode(String strPart) {

        return string2HexString(strPart,"Unicode");
    }
    /**
     * @Title:string2HexGBK
     * @Description:字符GBK串转16进制字符串
     * @param strPart
     * 字符串
     * @return 16进制字符串
     * @throws
     */
    public static String string2HexGBK(String strPart) {

        return string2HexString(strPart,"GBK");
    }

    /**
     * @Title:string2HexString
     * @Description:字符串转16进制字符串
     * @param strPart 字符串
     * @param tochartype hex目标编码
     * @return 16进制字符串
     * @throws
     */
    public static String string2HexString(String strPart,String tochartype) {
        try{
            return bytes2HexString(strPart.getBytes(tochartype));
        }catch (Exception e){
            return "";
        }
    }

    //

    /**
     * @Title:hexUTF82String
     * @Description:16进制UTF-8字符串转字符串
     * @param src
     * 16进制字符串
     * @return 字节数组
     * @throws
     */
    public static String hexUTF82String(String src) {

        return hexString2String(src,"UTF-8","UTF-8");
    }

    /**
     * @Title:hexUTF16LE2String
     * @Description:16进制UTF-8字符串转字符串，,此UTF-16LE等同于C#中的Unicode
     * @param src
     * 16进制字符串
     * @return 字节数组
     * @throws
     */
    public static String hexUTF16LE2String(String src) {

        return hexString2String(src,"UTF-16LE","UTF-8");
    }

    /**
     * @Title:hexGBK2String
     * @Description:16进制GBK字符串转字符串
     * @param src
     * 16进制字符串
     * @return 字节数组
     * @throws
     */
    public static String hexGBK2String(String src) {

        return hexString2String(src,"GBK","UTF-8");
    }

    /**
     * @Title:hexUnicode2String
     * @Description:16进制Unicode字符串转字符串
     * @param src
     * 16进制字符串
     * @return 字节数组
     * @throws
     */
    public static String hexUnicode2String(String src) {
        return hexString2String(src,"Unicode","UTF-8");
    }

    /**
     * @Title:hexString2String
     * @Description:16进制字符串转字符串
     * @param src
     * 16进制字符串
     * @return 字节数组
     * @throws
     */
    public static String hexString2String(String src,String oldchartype, String chartype) {
        byte[] bts=hexString2Bytes(src);
        try{if(oldchartype.equals(chartype)) {
            return new String(bts,oldchartype);
        } else {
            return new String(new String(bts,oldchartype).getBytes(),chartype);
        }
        }
        catch (Exception e){

            return"";
        }
    }

    /**
     * 十六进制高低位转换，返回十进制数
     * @param str
     * @return
     */
    public static String decodeHexStringToDec(String str) {
        str =HighLowHex(spaceHex(str));
        String value =new BigInteger(str, 16).toString();
        return value;
    }


    /**
     * 十六进制数隔空位
     * @param str
     * @return
     */
    private static String spaceHex(String str){
        char[] array= str.toCharArray();
        if(str.length()<=2) return str;
        StringBuffer buffer =new StringBuffer();
        for(int i=0;i<array.length;i++){
            int start =i+1;
            if(start%2==0){
                buffer.append(array[i]).append(" ");
            }else{
                buffer.append(array[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * 高位16进制转低位
     * @param str
     * @return
     */
    private static String HighLowHex(String str){
        if(str.trim().length()<=2) return str;
        List<String> list = Arrays.asList( str.split(" "));
        Collections.reverse(list);
        StringBuffer stringBuffer = new StringBuffer();
        for(String string:list){
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        byte[] ffs = ByteBufUtil.decodeHexDump("ff");
        System.out.println("ffs = " + ffs);
    }
}
