package special.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class OpenSSLDecryptor {

  private static final Charset ASCII = Charset.forName("ASCII");
  public static final int INDEX_KEY = 0;
  public static final int INDEX_IV = 1;
  public static final int ITERATIONS = 1;

  private static final int KEY_SIZE_BITS = 256;
  public static final int aesBlockSize = 16;

  /**
   * Thanks go to Ola Bini for releasing this source on his blog. The source was obtained from <a
   * href="http://olabini.com/blog/tag/evp_bytestokey/">here</a> .
   */
  private static byte[][] EVP_BytesToKey(
      int keyLen, int ivLen, MessageDigest md, byte[] salt, byte[] data, int count) {
    byte[][] both = new byte[2][];
    byte[] key = new byte[keyLen];
    int keyIx = 0;
    byte[] iv = new byte[ivLen];
    int ivIx = 0;
    both[0] = key;
    both[1] = iv;
    byte[] mdBuf = null;
    int nkey = keyLen;
    int niv = ivLen;
    int i = 0;
    if (data == null) {
      return both;
    }
    int addmd = 0;
    while (true) {
      md.reset();
      if (addmd++ > 0) {
        md.update(mdBuf);
      }
      md.update(data);
      if (null != salt) {
        md.update(salt, 0, 8);
      }
      mdBuf = md.digest();
      for (i = 1; i < count; i++) {
        md.reset();
        md.update(mdBuf);
        mdBuf = md.digest();
      }
      i = 0;
      if (nkey > 0) {
        while (true) {
          if (nkey == 0) {
            break;
          }
          if (i == mdBuf.length) {
            break;
          }
          key[keyIx++] = mdBuf[i];
          nkey--;
          i++;
        }
      }
      if (niv > 0 && i != mdBuf.length) {
        while (true) {
          if (niv == 0) {
            break;
          }
          if (i == mdBuf.length) {
            break;
          }
          iv[ivIx++] = mdBuf[i];
          niv--;
          i++;
        }
      }
      if (nkey == 0 && niv == 0) {
        break;
      }
    }
    for (i = 0; i < mdBuf.length; i++) {
      mdBuf[i] = 0;
    }
    return both;
  }

  public static byte[][] openSSLEVP(byte[] salt, byte[] password, MessageDigest md) {
    return EVP_BytesToKey(KEY_SIZE_BITS / Byte.SIZE, aesBlockSize, md, salt, password, ITERATIONS);
  }
}
