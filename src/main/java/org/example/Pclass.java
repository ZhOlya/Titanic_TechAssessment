package org.example;

public enum Pclass {
    FIRST("1"), SECOND("2"), THIRD("3");

    private final String value;

    Pclass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Pclass fromValue(String value) { // Возвращает значение перечисления на основе строки
        for (Pclass pclass : Pclass.values()) {
            if (pclass.getValue().equals(value)) {
                return pclass;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }

    public static Pclass fromDbValue(Integer dbValue) { // Возвращает значение перечисления на основе числа
        return switch (dbValue) {
            case 1 -> FIRST;
            case 2 -> SECOND;
            case 3 -> THIRD;
            default -> throw new IllegalArgumentException("Unknown enum value " + dbValue);
        };
    }

    public Integer toDbValue() {
        return switch (this) {
            case FIRST -> 1;
            case SECOND -> 2;
            case THIRD -> 3;
            default -> throw new IllegalArgumentException("Unknown enum type " + this);
        };
    }
}
