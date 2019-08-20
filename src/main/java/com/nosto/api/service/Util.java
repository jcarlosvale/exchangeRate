package com.nosto.api.service;

import lombok.NonNull;

import java.text.NumberFormat;
import java.util.Locale;

class Util {

    static String formatValue(@NonNull double convertedValue, @NonNull String toCcy) {
        return NumberFormat.getCurrencyInstance(getLocale(toCcy)).format(convertedValue);
    }

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
