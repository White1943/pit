package com.example.platform.uilt;

import org.springframework.stereotype.Component;
import java.util.Base64;
import java.io.*;

@Component
public class Base64Util {

    public static String imgs(String imgurl, String imgName) {
        InputStream in = null;
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        // 读取图片字节数组
        try {
            String url = imgurl + imgName;
            final byte[] by = new byte[1024];
            InputStream inputStream = new FileInputStream(new File(url));
            int len = -1;
            while ((len = inputStream.read(by)) != -1) {
                data.write(by, 0, len);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组 Base64 编码
        // 使用 java.util.Base64 类进行编码
        Base64.Encoder encoder = Base64.getEncoder();
        // 返回 Base64 编码过的字节数组字符串
        String encode = encoder.encodeToString(data.toByteArray());
        return encode;
    }
}