package wang.tyrael.cipher;

/**
 * 将一个string映射为另一个string
 * @Auther: wangchao
 * @Date: 2018/6/27 10:27
 * @Description:
 */
public abstract class MessageDigestTemplate {
    final String src;
    byte[] bSrc;
    byte[] bCompute;
    String result;

    protected MessageDigestTemplate(String src) {
        this.src = src;
    }

    public abstract byte[] toByte(String src);
    public abstract byte[] compute(byte[] bSrc);
    public abstract String byteToString(byte[] bCompute);

    public String digest(){
        bSrc = toByte(src);
        bCompute = compute(bSrc);
        result = byteToString(bCompute);
        return result;
    }

}
