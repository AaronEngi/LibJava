package wang.tyrael.library.cipher;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * 底层使用了密钥库保存密钥
 * https://developer.android.com/training/articles/keystore.html#SecurityFeatures
 * Created by 王超 on 2017/11/20.
 */

public class CipherSupport {
    private final String type;
    private final String keyName;
    private final AlgorithmParameterSpec params;
    private final String transformation;
    private final String algorithm;

    private SecretKey secretKey;
    private KeyStore keyStore;
    private byte[] iv;

    private final IBase64 base64;

    public CipherSupport(String type, String keyName, AlgorithmParameterSpec params, String transformation, String algorithm, IBase64 base64) {
        this.type = type;
        this.keyName = keyName;
        this.params = params;
        this.transformation = transformation;
        this.algorithm = algorithm;
        this.base64 = base64;

        try {
            getKey();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }

    private void getKey() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        keyStore = KeyStore.getInstance(type);
        keyStore.load(null);
        secretKey = (SecretKey) keyStore.getKey(keyName, null);
        if(secretKey == null){
            createKey();
        }
        secretKey = (SecretKey) keyStore.getKey(keyName, null);
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
        iv = base64.decode(encryptData.iv);
        System.out.println("iv[0]:" + iv[0]);
        byte[] src = base64.decode(encryptData.encrypt);
        byte[] dest = decrypt(src);
        return new String(dest);
    }

    /**
     * Tries to encrypt some data with the generated key in {@link #createKey} which
     * only works if the user has just authenticated via device credentials.
     */
    public byte[] encrypt(byte[] bytes) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
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
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
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

    /**
     * Creates a symmetric key in the Android Key Store which can only be used after the user has
     * authenticated with device credentials within the last X seconds.
     */
    private void createKey() {
        // Generate a key to decrypt payment credentials, tokens, etc.
        // This will most likely be a registration step for the user when they are setting up your app.
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    "AES", type);

            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder
            if(params == null){
                keyGenerator.init(new SecureRandom());
            }else{
                keyGenerator.init(params);
            }

            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException | NoSuchProviderException
                | InvalidAlgorithmParameterException e) {
            throw new RuntimeException("Failed to create a symmetric key", e);
        }
    }
}
