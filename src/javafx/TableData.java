package javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

 public class TableData {
     private StringProperty zero;
        private StringProperty one;
        private StringProperty two;
        private StringProperty three;
        private StringProperty four;
        private StringProperty five;
        private StringProperty six;
 
        public TableData(String zero, String one, String two, String three, String four, String five, String six) {
            this.zero = new SimpleStringProperty(zero);
            this.one = new SimpleStringProperty(one);
            this.two= new SimpleStringProperty(two);
            this.three = new SimpleStringProperty(three);
            this.four = new SimpleStringProperty(four);
            this.five = new SimpleStringProperty(five);
            this.six = new SimpleStringProperty(six);
        }
        
        public StringProperty zeroProperty() { return zero; }
        public StringProperty oneProperty() { return one; }
        public StringProperty twoProperty() { return two; }
        public StringProperty threeProperty() { return three; }
        public StringProperty fourProperty() { return four; }
        public StringProperty fiveProperty() { return five; }
        public StringProperty sixProperty() { return six; }
        
        public void setZero(String zero) { this.zero.set(zero); }
        public void setOne(String one) { this.one.set(one); }
        public void setTwo(String two) { this.two.set(two); }
        public void setThree(String three) { this.three.set(three); }
        public void setFour(String four) { this.four.set(four); }
        public void setFive(String five) {this.five.set(five); }
        public void setSix(String six) { this.six.set(six); }
    }

