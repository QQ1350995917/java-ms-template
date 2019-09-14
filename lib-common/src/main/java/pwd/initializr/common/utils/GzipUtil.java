package pwd.initializr.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:12
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class GzipUtil {
    public static final int BUFFER = 1024;

    public GzipUtil() {
    }

    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        compress(byteArrayInputStream, byteArrayOutputStream);
        byte[] output = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byteArrayInputStream.close();
        return output;
    }

    public static void compress(InputStream is, OutputStream os) throws Exception {
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(os);
        byte[] data = new byte[1024];

        int count;
        while((count = is.read(data, 0, 1024)) != -1) {
            gzipOutputStream.write(data, 0, count);
        }

        gzipOutputStream.finish();
        gzipOutputStream.flush();
        gzipOutputStream.close();
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < 90; ++i) {
            sb.append("" + i);
        }

        String sourceStr = sb.toString();
        System.out.println("source length:" + sourceStr.getBytes().length + ",source str length:" + sourceStr.length());

        try {
            byte[] out1 = compress(sourceStr.getBytes());
            System.out.println("after compress:" + out1.length);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        System.out.println("gzip,deflate,sdch".indexOf("gzip"));
    }
}
