package wang.tyrael.cipher;

/**
 * Created by 王超 on 2017/11/21.
 */

public class EncryptData {
    public String encrypt;
    /**
     * 加密向量，加密时随机生成。解密是需要输入。
     * 相当于盐。
     */
    public String iv;

    public boolean isValid(){
        if(encrypt == null || iv == null){
            return false;
        }else {
            return true;
        }
    }
}
