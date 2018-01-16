package special.math;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Randomness {

  /**
   * @param n Length of sequence
   * @param m Number of unique elements in sequence
   * @return Low limit for frequency in conclusion of that sequence is uniform distributed.
   */
  private static double lowLimit(double n, double m) {
    return (n - 2.57582930354890075378 * Math.sqrt(n * (m - 1.0))) / m;
  }

  /**
   * @param n Length of sequence
   * @param m Number of unique elements in sequence
   * @return High limit for frequency in conclusion of that sequence is uniform distributed.
   */
  private static double highLimit(double n, double m) {
    return (n + 2.57582930354890075378 * Math.sqrt(n * (m - 1.0))) / m;
  }

  /**
   * @param n Length of sequence
   * @param m Number of unique elements in sequence
   * @param x Tested value
   * @return not ( (x >= lowLimit(n, m)) && (x <= highLimit(n, m)) )
   */
  private static boolean wrong(double n, double m, double x) {
    return (x < lowLimit(n, m)) || (x > highLimit(n, m));
  }

  /**
   * @param frq Sorted list of frequences
   * @return Subsequence of elements, that pass frequency randomness test
   */
  public static SimpleEntry<Integer, Integer> findOptimalSegment(List<Integer> frq) {
    if (frq.size() < 2) {
      return new SimpleEntry<>(0, frq.size());
    }

    int l = 0;
    int r = frq.size() - 1;

    int s = 0;
    for (Integer x : frq) {
      s += x;
    }

    while ((r - l > 1) && wrong(s, r - l + 1, frq.get(l)) && wrong(s, r - l + 1, frq.get(r))) {
      double x = ((double) s) / ((double) frq.size());
      if (Math.abs(x - frq.get(l)) > Math.abs(x - frq.get(r))) {
        s -= frq.get(l);
        l++;
      } else {
        s -= frq.get(r);
        r--;
      }
    }

    return new SimpleEntry<>(l, r + 1);
  }
}
