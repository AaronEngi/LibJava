package tyrael.cipher;

/**
 * 不同平台由不同实现
 * Created by 王超 on 2017/11/21.
 */
public interface IBase64 {
    public String encodeToString(byte[] input);
    public byte[] decode(String str);
}
