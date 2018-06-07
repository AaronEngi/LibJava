package wang.tyrael.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputUtil {
    public static byte[] read(InputStream inputStream){

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        int sum = 0;
        try {
            while((len = inputStream.read(bytes)) != -1){
                sum += len;
                byteOutputStream.write(bytes, 0, len);
            }
            System.out.println("读取字节数：" + sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("读取字节数：" + byteOutputStream.size());
        return  byteOutputStream.toByteArray();
    }
}
