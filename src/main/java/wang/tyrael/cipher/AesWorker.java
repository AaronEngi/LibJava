package wang.tyrael.cipher;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AesWorker {
	public final String transformation;//"算法/模式/补码方式"
	public final byte[] key;
	public final String iv;
	
    public AesWorker(String transformation, byte[] key, String iv) {
		super();
		this.transformation = transformation;
		this.key = key;
		this.iv = iv;
	}

	// 加密
    public String encrypt(String sSrc){
    	byte[] raw = key;
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(transformation);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        IvParameterSpec ivS = new IvParameterSpec(iv.getBytes());
        try {
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivS);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] encrypted = null;
		try {
			encrypted = cipher.doFinal(sSrc.getBytes());
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return Base64.getEncoder().encodeToString(encrypted);
    }

    // 解密
    public String decrypt(String sSrc) {
            byte[] raw = key;
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = null;
			try {
				cipher = Cipher.getInstance(transformation);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            IvParameterSpec ivs = new IvParameterSpec(iv.getBytes());
            try {
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivs);
			} catch (InvalidKeyException | InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }

    }
}
