package special.security;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Locale;
import javax.crypto.SecretKey;

public class DestroyableSecretKeySpec implements KeySpec, SecretKey {
  protected static final long serialVersionUID = 627708920242586801L;
  protected byte[] key;
  protected String algorithm;
  protected boolean isDestroyed;

  public DestroyableSecretKeySpec(byte[] key, String algorithm) {
    if (key != null && algorithm != null) {
      if (key.length == 0) {
        throw new IllegalArgumentException("Empty key");
      } else {
        this.key = key.clone();
        this.algorithm = algorithm;
        this.isDestroyed = false;
      }
    } else {
      throw new IllegalArgumentException("Missing argument");
    }
  }

  public DestroyableSecretKeySpec(byte[] key, int offset, int len, String algorithm) {
    if (key != null && algorithm != null) {
      if (key.length == 0) {
        throw new IllegalArgumentException("Empty key");
      } else if (key.length - offset < len) {
        throw new IllegalArgumentException("Invalid offset/length combination");
      } else if (len < 0) {
        throw new ArrayIndexOutOfBoundsException("len is negative");
      } else {
        this.key = new byte[len];
        System.arraycopy(key, offset, this.key, 0, len);
        this.algorithm = algorithm;
        this.isDestroyed = false;
      }
    } else {
      throw new IllegalArgumentException("Missing argument");
    }
  }

  public String getAlgorithm() {
    return this.algorithm;
  }

  public String getFormat() {
    return "RAW";
  }

  public byte[] getEncoded() {
    return this.key.clone();
  }

  public int hashCode() {
    int retval = 0;

    for (int i = 1; i < this.key.length; ++i) {
      retval += this.key[i] * i;
    }

    return this.algorithm.equalsIgnoreCase("TripleDES")
        ? retval ^ "desede".hashCode()
        : retval ^ this.algorithm.toLowerCase(Locale.ENGLISH).hashCode();
  }

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (!(obj instanceof SecretKey)) {
      return false;
    } else {
      String thatAlg = ((SecretKey) obj).getAlgorithm();
      if (thatAlg.equalsIgnoreCase(this.algorithm)
          || thatAlg.equalsIgnoreCase("DESede") && this.algorithm.equalsIgnoreCase("TripleDES")
          || thatAlg.equalsIgnoreCase("TripleDES") && this.algorithm.equalsIgnoreCase("DESede")) {
        byte[] thatKey = ((SecretKey) obj).getEncoded();
        return MessageDigest.isEqual(this.key, thatKey);
      } else {
        return false;
      }
    }
  }

  @Override
  public void destroy() {
    SecureRandom rnd = new SecureRandom();
    for (int j = 0; j < 1000; j++) {
      for (int i = 0; i < key.length; i++) {
        key[i] = (byte) rnd.nextInt();
      }
    }
    isDestroyed = true;
  }

  @Override
  public boolean isDestroyed() {
    return isDestroyed;
  }
}
