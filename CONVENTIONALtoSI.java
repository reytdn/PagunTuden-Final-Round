public class CONVENTIONALtoSI {

    //Conversion of FBS and RBS Conventional Units to SI Units
    public static double glucoseToSI(double value) {
        return value / 18.0;
    }

    //Conversion of Total Cholesterol Convention Units to SI Units
    public static double cholesterolToSI(double value) {
        return value / 38.67;
    }

    //Conversion of HDL Convention Units to SI Units
    public static double hdlToSI(double value) {
        return value / 38.67;
    }

    //Conversion of LDL Cholesterol Convention Units to SI Units
    public static double ldlToSI(double value) {
        return value / 38.67;
    }

    //Conversion of Triglycerides Convention Units to SI Units
    public static double triglyceridesToSI(double value) {
        return value / 88.57;
    }

    //Conversion of Creatinine Convention Units to SI Units
    public static double creatinineToSI(double value) {
        return value * 88.4;
    }

    //Conversion of Uric Acid Convention Units to SI Units
    public static double uricAcidToSI(double value) {
        return value / 16.81;
    }

    //Conversion of BUN Convention Units to SI Units
    public static double bunToSI(double value) {
        return value / 2.801;
    }

    //Conversion of AST and ALT Convention Units to SI Units
    public static double enzymeToSI(double value) {
        return value / 60.0;
    }

    //Conversion of Sodium, Potassium, Chloride Convention Units to SI Units
    public static double electrolyteToSI(double value) {
        return value;
    }

    //Conversion of Total Calcium Convention Units to SI Units
    public static double totalCalciumToSI(double value) {
        return value * 0.2495;
    }

    //Conversion of Ionized Calcium Convention Units to SI Units
    public static double ionizedCalciumToSI(double value) {
        return value * 0.2495;
    }
}