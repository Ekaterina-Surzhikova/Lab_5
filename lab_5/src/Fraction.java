import java.util.Objects;

public class Fraction implements FractionInterface {
    private int numerator;
    private int denominator;
    private Double cachedDoubleValue;
    private boolean cacheValid;

    public Fraction() {
        this(1, 2);
    }

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
        this.cacheValid = false;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
        simplify();
        invalidateCache();
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        this.denominator = denominator;
        simplify();
        invalidateCache();
    }

    @Override
    public void setNumeratorAndDenominator(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
        invalidateCache();
    }

    @Override
    public double getDoubleValue() {
        if (!cacheValid || cachedDoubleValue == null) {
            cachedDoubleValue = (double) numerator / denominator;
            cacheValid = true;
        }
        return cachedDoubleValue;
    }

    private void invalidateCache() {
        cacheValid = false;
        cachedDoubleValue = null;
    }

    private void simplify() {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        if (gcd != 0) {
            numerator /= gcd;
            denominator /= gcd;
        }
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction fraction = (Fraction) obj;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}