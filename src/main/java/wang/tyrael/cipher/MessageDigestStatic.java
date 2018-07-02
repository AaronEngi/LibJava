package wang.tyrael.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestStatic {

  public static String SHA1(String str) {
    try {
      MessageDigest digest = MessageDigest
          .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
      digest.update(str.getBytes());
      byte messageDigest[] = digest.digest();
      // Create Hex String
      StringBuffer hexStr = new StringBuffer();
      // 字节数组转换为 十六进制 数
      for (int i = 0; i < messageDigest.length; i++) {
        String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
        if (shaHex.length() < 2) {
          hexStr.append(0);
        }
        hexStr.append(shaHex);
      }
      return hexStr.toString();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }
}
