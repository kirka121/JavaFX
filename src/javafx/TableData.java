package javafx;

import javafx.beans.property.SimpleStringProperty;

public class TableData {
    private final SimpleStringProperty one,two,three,four,five,six,seven,eight;
    public TableData(String one, String two, String three, String four, String five, String six, String seven, String eight) {
        this.one = new SimpleStringProperty(one);
        this.two = new SimpleStringProperty(two);
        this.three = new SimpleStringProperty(three);
        this.four = new SimpleStringProperty(four);
        this.five = new SimpleStringProperty(five);
        this.six = new SimpleStringProperty(six);
        this.seven = new SimpleStringProperty(seven);
        this.eight = new SimpleStringProperty(eight);
    }
    public String getOne() {
        return one.get();
    }
    public String getTwo() {
        return two.get();
    }
    public String getThree() {
        return three.get();
    }
    public String getFour() {
        return four.get();
    }
    public String getFive() {
        return five.get();
    }
    public String getSix() {
        return six.get();
    }
    public String getSeven() {
        return seven.get();
    }
    public String getEight() {
        return eight.get();
    }
    
    public void setOne(String one) {
        this.one.set(one);
    }
    public void setTwo(String two) {
        this.two.set(two);
    }
    public void setThree(String three) {
        this.three.set(three);
    }
    public void setFour(String four) {
        this.four.set(four);
    }
    public void setFive(String five) {
        this.five.set(five);
    }
    public void setSix(String six) {
        this.six.set(six);
    }
    public void setSeven(String seven) {
        this.seven.set(seven);
    }
    public void setEight(String eight) {
        this.eight.set(eight);
    }
} 
