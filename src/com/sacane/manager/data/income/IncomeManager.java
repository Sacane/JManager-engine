package com.sacane.manager.data.income;

public class IncomeManager { //TODO : turn it to a record

    private double value;
    private final String nameLabel;
    private final String date;
    /**private final double sold;**/

    /**
     * TODO: add sold in the last row
     * @param nameLabel
     * @param value
     * @param date
     */
    public IncomeManager(String nameLabel, double value, String date){
        this.nameLabel = nameLabel;
        this.value = value;
        this.date = date;
        //this.sold = sold;
    }

    public double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }


    public String getNameLabel() {
        return nameLabel;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /*
    public double getSold(){
        return actualSold;
    }
    */

}
