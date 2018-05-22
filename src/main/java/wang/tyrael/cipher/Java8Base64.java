package wang.tyrael.cipher;

import java.util.Base64;

/**
 * Created by 王超 on 2017/11/21.
 */

public class Java8Base64 implements IBase64 {
    @Override
    public String encodeToString(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    @Override
    public byte[] decode(String str) {
        return Base64.getDecoder().decode(str);
    }
}
