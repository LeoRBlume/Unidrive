package br.com.unidrive.infrastructure.enums;

import lombok.Getter;

public enum StringToLongEnum {
    VALUE_TOYOTA("TOYOTA", 0),
    VALUE_VOLKSWAGEN("VOLKSWAGEN", 1),
    VALUE_FORD("FORD", 2),
    VALUE_HONDA("HONDA", 3),
    VALUE_HYUNDAI("HYUNDAI", 4),
    VALUE_NISSAN("NISSAN", 5),
    VALUE_CHEVROLET("CHEVROLET", 6),
    VALUE_KIA("KIA", 7),
    VALUE_MERCEDES("MERCEDES-BENZ", 8),
    VALUE_BMW("BMW", 9);

    private final String stringValue;
    @Getter
    private final int intValue;

    StringToLongEnum(String stringValue, int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public static long getLongValueFromString(String inputString) {
        for (StringToLongEnum entry : StringToLongEnum.values()) {
            if (entry.stringValue.equalsIgnoreCase(inputString)) {
                return entry.intValue;
            }
        }
        throw new IllegalArgumentException("String not found in enum: " + inputString);
    }
}

