package wang.tyrael.cipher;

/**
 * @Auther: wangchao
 * @Date: 2018/6/27 10:34
 * @Description:
 */
public abstract class MD5Template extends MessageDigestTemplate {
    protected MD5Template(String src) {
        super(src);
    }

    @Override
    public byte[] compute(byte[] bSrc) {
        return Md5Util.computeByte(bSrc);
    }
}
