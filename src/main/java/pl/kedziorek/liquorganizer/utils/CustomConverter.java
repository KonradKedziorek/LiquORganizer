package pl.kedziorek.liquorganizer.utils;

public class CustomConverter {
    public static Double convertToDouble(String value) {
        if (value != null) {
            return Double.parseDouble(value);
        } else {
            return null;
        }
    }
}
