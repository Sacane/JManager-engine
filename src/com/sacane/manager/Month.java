package com.sacane.manager;
import java.util.Arrays;

/**
 * Enum class to manage the months and its information.
 * A month has a String text, a representation of according to its number and
 * a max number of day.
 */
public enum Month {

    JANUARY("January", "01", 31),
    FEBRUARY("February", "02", 29),
    MARCH("March", "03", 31),
    APRIL("April", "04", 30),
    MAY("May", "05", 31),
    JUNE("June", "06", 30),
    JULY("July", "07", 31),
    AUGUST("August", "08", 31),
    SEPTEMBER("September", "09", 30),
    OCTOBER("October", "10", 31),
    NOVEMBER("November", "11", 30),
    DECEMBER("December", "12", 31);

    private final String typeText;
    private final String representation;
    private final int numberDay;

    Month(String typeText, String representation, int numberDay){
        this.typeText = typeText;
        this.representation = representation;
        this.numberDay = numberDay;
    }

    /**
     *
     * @return the text representation of the month
     */
    public String getTypeText(){
        return typeText;
    }

    /**
     * @return the maximum number of day of the month
     */
    public int getNumberDay(){ return numberDay; }


    /**
     * @return the number's representation for this month
     */
    public String representation(){
        return representation;
    }

    /**
     * Take a day, month and a year to format it into a date representation.
     * @param day : int representation of the day use in the formatted String
     * @param month : Month use in the formatted String
     * @param year : int of the year to return in the formatted String
     * @return a String using the date representation : YYYY/MM/DD
     */
    public static String formattedDate(int day, Month month, int year){
        if(month == null){
            throw new IllegalArgumentException("Month null");
        }
        var formatDay = (day <= 9) ? "0" + day : String.valueOf(day);

        return year + "-" + month.representation() + "-" + formatDay;
    }

    /**
     * get a month according to the number which represents it.
     * @param rep : int representation of the day of the month to get
     * @return Month of the representation.
     */
    public static Month getMonthByRep(int rep){
        if(rep > 12){
            rep = rep%12;
        }
        if(rep < 0){
            throw new IllegalArgumentException("representation can't be negative");
        }
        int finalRep = rep;
        return Arrays.stream(Month.values()).filter(it -> Integer.valueOf(it.representation).equals(finalRep)).findFirst().orElse(null);
    }

    /**
     *
     * @param symbol String of the text representing the month to get
     * @return Month
     */
    public static Month get(String symbol) {
        return Arrays.stream(Month.values()).filter(it -> it.typeText.equals(symbol)).findFirst().orElse(null);
    }

}
