package java.ac.uk.tees.w9547666.retailstoreapplication.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * @author w9547666
 */

public class Money {

    private static final Currency GBP = Currency.getInstance(Locale.UK);
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private BigDecimal amount;
    private Currency currency;

    Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.amount = amount;
        this.currency = currency;

        this.amount = amount.setScale(currency.getDefaultFractionDigits(),
                rounding);
    }

    public static Money rupees(BigDecimal amount) {
        return new Money(amount, GBP);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return getCurrency().getSymbol() + " " + getAmount();
    }

    public String toString(Locale locale) {
        return getCurrency().getSymbol(locale) + " " + getAmount();
    }
}