public class rangeFandM {

    public static String interpret(double value, double low, double high) {

        if (value < low) {
            return "LOW";
        } else if (value > high) {
            return "HIGH";
        } else {
            return "NORMAL";
        }
    }
}