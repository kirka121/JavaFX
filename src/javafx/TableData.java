package javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

 public class TableData {
        private StringProperty one;
        private StringProperty two;
        private StringProperty three;
        private StringProperty four;
 
        public TableData(String one, String two, String three, String four) {
            this.one = new SimpleStringProperty(one);
            this.two= new SimpleStringProperty(two);
            this.three = new SimpleStringProperty(three);
            this.four = new SimpleStringProperty(four);
        }
         
        public StringProperty oneProperty() { return one; }
        public StringProperty twoProperty() { return two; }
        public StringProperty threeProperty() { return three; }
        public StringProperty fourProperty() { return four; }
    }
