package special.data;

import sun.nio.cs.Surrogate.Parser;

public class UTF8_ENCODER {

  public static class EncoderOverflowException extends Exception {}

  private static final Parser sgp = new Parser();

  // hardcoded utf-8 converter
  public static void encodeArrayLoop(char[] var3, byte[] var6) throws EncoderOverflowException {

    int var4 = 0;
    int var5 = var3.length;
    int var7 = 0;
    int var8 = var6.length;

    for (int var9 = var7 + Math.min(var5 - var4, var8 - var7);
        var7 < var9 && var3[var4] < 128;
        var6[var7++] = (byte) var3[var4++]) {}

    for (; var4 < var5; ++var4) {
      char var10 = var3[var4];
      if (var10 < 128) {
        if (var7 >= var8) {
          throw new EncoderOverflowException();
        }

        var6[var7++] = (byte) var10;
      } else if (var10 < 2048) {
        if (var8 - var7 < 2) {
          throw new EncoderOverflowException();
        }

        var6[var7++] = (byte) (192 | var10 >> 6);
        var6[var7++] = (byte) (128 | var10 & 63);
      } else if (Character.isSurrogate(var10)) {

        int var11 = sgp.parse(var10, var3, var4, var5);
        if (var11 < 0) {
          if (!sgp.error().isUnderflow()) {
            throw new EncoderOverflowException();
          }
          return;
        }

        if (var8 - var7 < 4) {
          throw new EncoderOverflowException();
        }

        var6[var7++] = (byte) (240 | var11 >> 18);
        var6[var7++] = (byte) (128 | var11 >> 12 & 63);
        var6[var7++] = (byte) (128 | var11 >> 6 & 63);
        var6[var7++] = (byte) (128 | var11 & 63);
        ++var4;
      } else {
        if (var8 - var7 < 3) {
          throw new EncoderOverflowException();
        }

        var6[var7++] = (byte) (224 | var10 >> 12);
        var6[var7++] = (byte) (128 | var10 >> 6 & 63);
        var6[var7++] = (byte) (128 | var10 & 63);
      }
    }
  }
}
