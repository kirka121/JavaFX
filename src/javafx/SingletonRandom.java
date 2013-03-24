package javafx;

import java.util.Random;

public class SingletonRandom {
  /** public final static gives immutable access to reference-to variable of the single object of type <i>SingletonRandom</i> */
  public final static SingletonRandom instance = new SingletonRandom();
  /** reference-to value for the java.util.Random object that will be created */
  private Random random;

  /** private constructor is a key element in <i>Singleton</i> design pattern. */
  private SingletonRandom() { random = new Random(); }

  /**
   *
   * @param dLowerLimit returned values will always be greater-than-or-equal-to this value
   * @param dUpperLimit returned values will always be greater-than-or-equal-to this value
   * @param dStdDevSpread dStdDevSpread influences the shape of the "normal" curve, example cases of values for dStdDevSpread: 1.0: distribution will be flatter, 68% of values naturally fall between
   * dLowerLimit and dUpperLimit, 32% (100%-68%) are outside limits and have to be snapped back into range 2.0: distribution will be moderately peaked, 95% of values naturally fall between dLowerLimit
   * and dUpperLimit, 5% (100%-95%) are outside limits and have to be snapped back into range 3.0: distribution will have sharp clustering around the mean, 99.7% of values naturally fall between
   * dLowerLimit and dUpperLimit, 0.3% (100%-99.7%) are outside limits and have to be snapped back into range since dStdDevSpread is double, it can contain fractional values, not just the integral
   * values shown here.
   *
   * @return a random number that follows a normal (Gaussian) distribution within the specified range.
   */
  public double getNormalDistribution(double dLowerLimit, double dUpperLimit, double dStdDevSpread) {
    if (dStdDevSpread < 1.0 || dStdDevSpread > 5.0) // if dStdDevSpread is < 1.0 there is a risk of excessive iterations of the range-checking do-while() loop
      throw new IllegalArgumentException();
    double dRange = dUpperLimit - dLowerLimit + 1.0;
    double dMean = (dLowerLimit + dUpperLimit) / 2.0;
    // next.Gaussian() returns a value where 1-standard deviation worth range between -1.0 and +1.0 (i.e. about 68% of values)
    double dCalculatedRandom;
    do {
      dCalculatedRandom = (random.nextGaussian() * (dRange / 2.0) / dStdDevSpread) + dMean;
    } while (dCalculatedRandom < dLowerLimit || dCalculatedRandom > dUpperLimit);
    return dCalculatedRandom;
  }
  
  public Boolean getRandomBool(){
      return random.nextBoolean();
  }
}
