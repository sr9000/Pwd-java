package special.security;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;

public class PBKDF2HmacSHA256 {

  public static final String HMAC_SHA_256 = "HmacSHA256";

  public static byte[] deriveKey(byte[] password, byte[] salt, int iterCount, int keyLengthInBit) {
    Mac prf = null;
    try {
      prf = Mac.getInstance(HMAC_SHA_256);
    } catch (NoSuchAlgorithmException ignored) {
    }

    int keyLength = keyLengthInBit / 8;
    byte[] key = new byte[keyLength];

    try {
      int hlen = prf.getMacLength();
      int intL = (keyLength + hlen - 1) / hlen;
      int intR = keyLength - (intL - 1) * hlen;
      byte[] ui = new byte[hlen];
      byte[] ti = new byte[hlen];
      Mac finalPrf = prf;
      SecretKey macKey =
          new SecretKey() {
            private static final long serialVersionUID = 728165015303944874L;

            public String getAlgorithm() {
              return finalPrf.getAlgorithm();
            }

            public String getFormat() {
              return "RAW";
            }

            public byte[] getEncoded() {
              return password;
            }

            public int hashCode() {
              return Arrays.hashCode(password) * 41
                  + finalPrf.getAlgorithm().toLowerCase(Locale.ENGLISH).hashCode();
            }

            public boolean equals(Object obj) {
              if (this == obj) {
                return true;
              } else if (this.getClass() != obj.getClass()) {
                return false;
              } else {
                SecretKey sk = (SecretKey) obj;
                return finalPrf.getAlgorithm().equalsIgnoreCase(sk.getAlgorithm())
                    && MessageDigest.isEqual(password, sk.getEncoded());
              }
            }
          };

      finalPrf.init(macKey);

      { // clear confidential memory
        SecureRandom rnd = new SecureRandom();
        for (int j = 0; j < 1000; j++) {
          for (int i = 0; i < password.length; i++) {
            password[i] = (byte) rnd.nextInt();
          }
        }
      }

      byte[] ibytes = new byte[4];

      for (int i = 1; i <= intL; ++i) {
        finalPrf.update(salt);
        ibytes[3] = (byte) i;
        ibytes[2] = (byte) (i >> 8 & 255);
        ibytes[1] = (byte) (i >> 16 & 255);
        ibytes[0] = (byte) (i >> 24 & 255);
        finalPrf.update(ibytes);
        finalPrf.doFinal(ui, 0);
        System.arraycopy(ui, 0, ti, 0, ui.length);

        for (int j = 2; j <= iterCount; ++j) {
          finalPrf.update(ui);
          finalPrf.doFinal(ui, 0);

          for (int k = 0; k < ui.length; ++k) {
            ti[k] ^= ui[k];
          }
        }

        if (i == intL) {
          System.arraycopy(ti, 0, key, (i - 1) * hlen, intR);
        } else {
          System.arraycopy(ti, 0, key, (i - 1) * hlen, hlen);
        }
      }

      finalPrf.reset();

      return key;
    } catch (GeneralSecurityException var17) {
      throw new RuntimeException("Error deriving PBKDF2 keys");
    }
  }
}
