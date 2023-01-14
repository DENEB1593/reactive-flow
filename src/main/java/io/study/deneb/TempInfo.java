package io.study.deneb;

import java.util.Random;

/**
 * 임의의 온도를 연속덕으로 생성하여 fetch
 */
public class TempInfo {

  public static final Random random = new Random();

  private final String town;
  private final int temp;

  public TempInfo(String town, int temp) {
    this.town = town;
    this.temp = temp;
  }

  public static TempInfo fetch(String town) {
/*
    if (random.nextInt(10) == 0)
      throw new RuntimeException("Error");
*/
    return new TempInfo(town, random.nextInt(100));
  }

  public String getTown() {
    return town;
  }

  public int getTemp() {
    return temp;
  }

  @Override
  public String toString() {
    return "io.study.deneb.TempInfo{" +
      "town='" + town + '\'' +
      ", temp=" + temp +
      '}';
  }
}
