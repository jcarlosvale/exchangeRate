package com.nosto.api.service;

import lombok.NonNull;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Util class providing the method to locate the Locale used by a Currency and formatting i18n String.
 */
class Util {

    /**
     * Formats the double value to String i18n
     * @param value the value to be converted
     * @param toCcy the currency code to be used in the internationalization
     * @return the String i118n
     */
    static String formatValue(@NonNull double value, @NonNull String toCcy) {
        return NumberFormat.getCurrencyInstance(getLocale(toCcy)).format(value);
    }

    /**
     * @param toCcy the currency code (ex: EUR, BRL, USD)
     * @return the Locale of the currency code
     */
    private static Locale getLocale(@NonNull String toCcy) {
        for (Locale locale : NumberFormat.getAvailableLocales()) {
            String code = NumberFormat.getCurrencyInstance(locale).getCurrency().getCurrencyCode();
            if (toCcy.equals(code)) {
                return locale;
            }
        }
        return null;
    }
}
