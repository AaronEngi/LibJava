package tyrael.cipher;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * 底层使用了密钥库保存密钥
 * https://developer.android.com/training/articles/keystore.html#SecurityFeatures
 * Created by 王超 on 2017/11/20.
 */

public class CipherSupport {

    private final String transformation;


    private final SecretKey secretKey;
    private byte[] iv;

    private final IBase64 base64;

    public CipherSupport(String transformation, SecretKey secretKey, IBase64 base64) {
        System.out.println("CipherSupport:secretKey:" + secretKey);
        this.transformation = transformation;
        this.secretKey = secretKey;
        this.base64 = base64;
    }

    public EncryptData encrypt(String plaintext){
        byte[] src = plaintext.getBytes();
        byte[] dest = encrypt(src);
        EncryptData encryptData = new EncryptData();
        encryptData.encrypt = base64.encodeToString(dest);
        encryptData.iv = base64.encodeToString(iv);
        System.out.println("iv[0]:" + iv[0]);
        return encryptData;
    }

    public String decrypt(EncryptData encryptData){
        if(encryptData == null || !encryptData.isValid()){
            System.out.println(encryptData == null || !encryptData.isValid());
            return null;
        }
        iv = base64.decode(encryptData.iv);
        System.out.println("iv[0]:" + iv[0]);
        byte[] src = base64.decode(encryptData.encrypt);
        byte[] dest = decrypt(src);
        return new String(dest);
    }

    /**
     * Tries to encrypt some data with the generated key in which
     * only works if the user has just authenticated via device credentials.
     */
    public byte[] encrypt(byte[] bytes) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            // Try encrypting something, it will only work if the user authenticated within
            // the last AUTHENTICATION_DURATION_SECONDS seconds.
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            iv = cipher.getIV();
            byte[] result = cipher.doFinal(bytes);
            //iv = cipher.getIV();
            return result;

        } catch (BadPaddingException | IllegalBlockSizeException
                | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(byte[] bytes) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            // Try encrypting something, it will only work if the user authenticated within
            // the last AUTHENTICATION_DURATION_SECONDS seconds.
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
            return cipher.doFinal(bytes);
        } catch (BadPaddingException | IllegalBlockSizeException
                | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }  catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
