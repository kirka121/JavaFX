package javafx;

import java.awt.Canvas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.util.Callback;


public class SampleController implements Initializable {
    @FXML
    private Label msgbar, army_name, specific_creature_amount_label, label_army1, label_army2, label_vs, label_s_f_army, labl_chose_a_file;
    @FXML
    private Button load_army, create_the_army, delete_army, new_army, start_fight, save_a_to_disk, load_a_from_disk, actually_load;
    @FXML
    private Pane make_new_army, battlefield;
    @FXML
    private ComboBox creature_type_selection;
    @FXML
    private RadioButton creature_type_specific, creature_type_random;
    @FXML
    private ListView<String> list_armies;
    @FXML
    private ColorPicker army_color;
    @FXML
    private TextField army_name_entry, specific_creature_amount, chose_a_file;
    @FXML
    private Circle display_army_color, circle_army1, circle_army2;
    @FXML
    private TableView army;
    
    private ArrayList<Army> myarmy = new ArrayList<>();
    private final ObservableList<String> listItems = FXCollections.observableArrayList(); 
    TableColumn zero = new TableColumn();
    TableColumn name = new TableColumn();
    TableColumn strength = new TableColumn();
    TableColumn speed = new TableColumn();
    TableColumn health = new TableColumn();
    TableColumn fieldx = new TableColumn();
    TableColumn fieldy = new TableColumn();
    private Integer battle_army1;
    private Integer battle_army2;
                   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list_armies.setItems(listItems);
        
        new_army.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                msgbar.setTextFill(Color.WHITE);
                msgbar.setText("Making new army");
                army_name_entry.setText("");
                army_color.setValue(Color.WHITE);
                make_new_army.setVisible(true);
            }
        });
        create_the_army.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Boolean error = false;
                Integer input_amount = 0;
                if (creature_type_specific.isSelected()){
                    try {
                        input_amount = Integer.parseInt(specific_creature_amount.getText().toString());
                    } catch (Exception e){
                        error = true;
                    }
                }
                if (error && creature_type_specific.isSelected()){
                     msgbar.setTextFill(Color.RED);
                     msgbar.setText("error: Amount: integers only");
                }else if ((input_amount < 1 || input_amount > 1000)&& creature_type_specific.isSelected()){
                     msgbar.setTextFill(Color.RED);
                     msgbar.setText("error: Amount: 1-1000 range only");
                }else if(army_name_entry.getText().equals("")){
                    System.out.println("error: army name left blank");
                    msgbar.setTextFill(Color.RED);
                    msgbar.setText("error: Army Name: can not be blank");
                } else {
                    if(creature_type_specific.isSelected()){
                        myarmy.add(new Army(creature_type_selection.getValue().toString(), Integer.parseInt(specific_creature_amount.getText()), army_name_entry.getText(), army_color.getValue().toString()));
                        msgbar.setTextFill(Color.WHITE);
                        msgbar.setText("Specific Army Added");
                    } else if (creature_type_random.isSelected()){
                        myarmy.add(new Army(1, army_name_entry.getText(), army_color.getValue().toString()));
                        msgbar.setTextFill(Color.WHITE);
                        msgbar.setText("Random Army Added");
                    }
                    Army a = myarmy.get(myarmy.size()-1);
                    System.out.println("army name: " + a.name_of_army + "\narmy color: " + a.army_color);
                    listItems.add(a.name_of_army);
                    make_new_army.setVisible(false);
                }
                
            }
        });
        creature_type_random.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               creature_type_selection.setVisible(false);
               specific_creature_amount_label.setVisible(false);
               specific_creature_amount.setVisible(false);
            }
        });
        creature_type_specific.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               creature_type_selection.setVisible(true);
               specific_creature_amount_label.setVisible(true);
               specific_creature_amount.setVisible(true);
            }
        });
        load_army.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //load army into battlefield code will be here
                int selectedItem = list_armies.getSelectionModel().getSelectedIndex();
                if (label_vs.isVisible() == false){
                    label_vs.setVisible(true);
                }
                if(label_army1.getText() == null){
                    label_army1.setText(myarmy.get(selectedItem).name_of_army);
                    battle_army1 = selectedItem;
                    circle_army1.setFill(Paint.valueOf(myarmy.get(selectedItem).army_color));
                    circle_army1.setVisible(true);
                } else if (label_army2.getText() == null){
                    label_army2.setText(myarmy.get(selectedItem).name_of_army);
                    battle_army2 = selectedItem;
                    circle_army2.setFill(Paint.valueOf(myarmy.get(selectedItem).army_color));
                    circle_army2.setVisible(true);
                    start_fight.setVisible(true);
                } else {
                    start_fight.setVisible(false);
                    circle_army1.setFill(null);
                    circle_army1.setVisible(true);
                    circle_army2.setFill(null);
                    circle_army2.setVisible(false);
                    label_army1.setText(null);
                    label_army2.setText(null);
                    label_army1.setText(myarmy.get(selectedItem).name_of_army);
                    battle_army1 = selectedItem;
                    circle_army1.setFill(Paint.valueOf(myarmy.get(selectedItem).army_color));
                    circle_army1.setVisible(true);
                }
            }
        });
        start_fight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // start a fight here
                battlefield.setStyle("-fx-background-color: black;");
                Army first_army = myarmy.get(battle_army1);
                Army second_army = myarmy.get(battle_army2);
                Circle[] first_army_circles = new Circle[first_army.actor.size()+1];
                Circle[] second_army_circles = new Circle[second_army.actor.size()+1];
                battlefield.getChildren().remove(0,battlefield.getChildren().size());
                  
                for (int i = 0; i < first_army.actor.size(); i++){
                    first_army_circles[i] = new Circle();
                    first_army_circles[i].setCenterX(first_army.actor.get(i).point_x);
                    first_army_circles[i].setCenterY(first_army.actor.get(i).point_y);
                    first_army_circles[i].setRadius(5.0f);
                    first_army_circles[i].setFill(Paint.valueOf(first_army.army_color));
                    battlefield.getChildren().addAll(first_army_circles[i]);
                }
                for (int i = 0; i < second_army.actor.size(); i++){
                    second_army_circles[i] = new Circle();
                    second_army_circles[i].setCenterX(second_army.actor.get(i).point_x);
                    second_army_circles[i].setCenterY(first_army.actor.get(i).point_y);
                    second_army_circles[i].setRadius(5.0f);
                    second_army_circles[i].setFill(Paint.valueOf(second_army.army_color));
                    battlefield.getChildren().addAll(second_army_circles[i]);
                }
            }
        });
        delete_army.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedItem = list_armies.getSelectionModel().getSelectedIndex();
                listItems.remove(selectedItem);
                myarmy.remove(selectedItem);
                army_name.setText("");
                msgbar.setTextFill(Color.WHITE);
                msgbar.setText("Army Deleted");
            }
        });
        save_a_to_disk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //save to disk button
                int selectedItem = list_armies.getSelectionModel().getSelectedIndex();
                Army a = myarmy.get(selectedItem);
                
                try{
                    FileOutputStream fileOut = new FileOutputStream(a.name_of_army + ".ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(a);
                    out.close();
                    fileOut.close();
                }catch(IOException i){
                    System.out.println(i);
                }
                listItems.remove(selectedItem);
                myarmy.remove(selectedItem);
                msgbar.setTextFill(Color.WHITE);
                msgbar.setText("Army Saved to Disk");
                System.out.println("army saved: '"+ a.name_of_army + ".ser'");
            }
        });
        load_a_from_disk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save_a_to_disk.setVisible(false);
                load_a_from_disk.setVisible(false);
                labl_chose_a_file.setVisible(true);
                chose_a_file.setVisible(true);
                actually_load.setVisible(true);
            }
        });
        actually_load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save_a_to_disk.setVisible(true);
                load_a_from_disk.setVisible(true);
                labl_chose_a_file.setVisible(false);
                chose_a_file.setVisible(false);
                actually_load.setVisible(false);
                Army e = null;
                try{
                   System.out.println(chose_a_file.getText());
                   FileInputStream fileIn = new FileInputStream(chose_a_file.getText());
                   ObjectInputStream in = new ObjectInputStream(fileIn);
                   e = (Army) in.readObject();
                   myarmy.add(new Army(e.type_of_actor.toString(), e.num_of_actors, e.name_of_army, e.army_color));
                   for (int i = 0; i < myarmy.get(myarmy.size()-1).actor.size(); i++){
                       myarmy.get(myarmy.size()-1).actor.get(i).aName = e.actor.get(i).aName;
                       myarmy.get(myarmy.size()-1).actor.get(i).aStrength = e.actor.get(i).aStrength;
                       myarmy.get(myarmy.size()-1).actor.get(i).aSpeed = e.actor.get(i).aSpeed;
                       myarmy.get(myarmy.size()-1).actor.get(i).aHealth = e.actor.get(i).aHealth;
                       myarmy.get(myarmy.size()-1).actor.get(i).aSequence = i;
                       myarmy.get(myarmy.size()-1).actor.get(i).point_x = e.actor.get(i).point_x;
                       myarmy.get(myarmy.size()-1).actor.get(i).point_y = e.actor.get(i).point_y;
                   }

                   in.close();
                   fileIn.close();
                }catch(IOException i){
                   System.out.println(i);
                }catch(ClassNotFoundException c){
                    System.out.println(c);
                }
                listItems.add(e.name_of_army);
                msgbar.setTextFill(Color.WHITE);
                msgbar.setText("Army Loaded from Disk");
            }
        });
        // Add a ChangeListener to ListView to look for change in focus
        list_armies.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(list_armies.isFocused()){
                    
                    save_a_to_disk.setVisible(true);
                    int selectedItem = list_armies.getSelectionModel().getSelectedIndex();
                    final Army a = myarmy.get(selectedItem);
                    army_name.setText(a.name_of_army);
                    display_army_color.setVisible(true);
                    display_army_color.setFill(Paint.valueOf(a.army_color));
                    label_s_f_army.setText(Integer.toString(a.actor.size()));
                            
                    army.setItems(null);
                    army.getColumns().removeAll(zero, name,strength,speed,health,fieldx,fieldy);
                    ObservableList<TableData> data = FXCollections.observableArrayList();
                    for(int i = 0; i<a.actor.size();i++){
                       //System.out.println("#" +a.actor.get(i).aSequence + "name: " + a.actor.get(i).aName + "str: " + a.actor.get(i).aStrength.toString() + "speed: " + a.actor.get(i).aSpeed.toString() + "hp: " + a.actor.get(i).aHealth.toString() +" x: " + a.actor.get(i).point_x.toString() +" | y: " + a.actor.get(i).point_y.toString());
                       data.add(new TableData(a.actor.get(i).aSequence.toString(), a.actor.get(i).aName, a.actor.get(i).aStrength.toString(), a.actor.get(i).aSpeed.toString(), a.actor.get(i).aHealth.toString(), a.actor.get(i).point_x.toString(), a.actor.get(i).point_y.toString()));
                    }
                    
                    zero.setText("#");
                    zero.setMinWidth(20);
                    name.setText("Name");
                    name.setMinWidth(80);
                    strength.setText("Strength");
                    strength.setMinWidth(80);
                    speed.setText("Speed");
                    speed.setMinWidth(80);
                    health.setText("Health");
                    health.setMinWidth(80);
                    fieldx.setText("X");
                    fieldx.setMinWidth(80);
                    fieldy.setText("Y");
                    fieldy.setMinWidth(80);
                    
                    zero.setCellValueFactory(new PropertyValueFactory("zero"));
                    name.setCellValueFactory(new PropertyValueFactory("one"));
                    strength.setCellValueFactory(new PropertyValueFactory("two"));
                    speed.setCellValueFactory(new PropertyValueFactory("three"));
                    health.setCellValueFactory(new PropertyValueFactory("four"));
                    fieldx.setCellValueFactory(new PropertyValueFactory("five"));
                    fieldy.setCellValueFactory(new PropertyValueFactory("six"));
                    
                    Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
                        @Override
                        public TableCell call(TableColumn p) {
                            return new EditingCell();
                        }
                    };
                    
                    name.setCellFactory(cellFactory);
                    name.setOnEditCommit(new EventHandler<CellEditEvent<TableData, String>>() {           
                        @Override public void handle(CellEditEvent<TableData, String> t) {
                            ((TableData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOne(t.getNewValue());
                            Integer selected_actor = Integer.valueOf((String) zero.getCellData(zero.getTableView().getItems().get(t.getTablePosition().getRow())));
                            a.actor.get(selected_actor).aName = t.getNewValue();
                        }
                    });
                    strength.setCellFactory(cellFactory);
                    strength.setOnEditCommit(new EventHandler<CellEditEvent<TableData, String>>() {           
                        @Override public void handle(CellEditEvent<TableData, String> t) {
                            ((TableData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTwo(t.getNewValue());
                            Integer selected_actor = Integer.valueOf((String) zero.getCellData(zero.getTableView().getItems().get(t.getTablePosition().getRow())));
                            a.actor.get(selected_actor).aStrength = Integer.valueOf((String) t.getNewValue());
                        }
                    });
                    speed.setCellFactory(cellFactory);
                    speed.setOnEditCommit(new EventHandler<CellEditEvent<TableData, String>>() {           
                        @Override public void handle(CellEditEvent<TableData, String> t) {
                            ((TableData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setThree(t.getNewValue());
                            Integer selected_actor = Integer.valueOf((String) zero.getCellData(zero.getTableView().getItems().get(t.getTablePosition().getRow())));
                            a.actor.get(selected_actor).aSpeed = Integer.valueOf((String) t.getNewValue());
                        }
                    });
                    health.setCellFactory(cellFactory);
                    health.setOnEditCommit(new EventHandler<CellEditEvent<TableData, String>>() {           
                        @Override public void handle(CellEditEvent<TableData, String> t) {
                            ((TableData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFour(t.getNewValue());
                            Integer selected_actor = Integer.valueOf((String) zero.getCellData(zero.getTableView().getItems().get(t.getTablePosition().getRow())));
                            a.actor.get(selected_actor).aHealth = Integer.valueOf((String) t.getNewValue());
                        }
                    });
                    fieldx.setCellFactory(cellFactory);
                    fieldx.setOnEditCommit(new EventHandler<CellEditEvent<TableData, String>>() {           
                        @Override public void handle(CellEditEvent<TableData, String> t) {
                            ((TableData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFive(t.getNewValue());
                            Integer selected_actor = Integer.valueOf((String) zero.getCellData(zero.getTableView().getItems().get(t.getTablePosition().getRow())));
                            a.actor.get(selected_actor).point_x = Integer.valueOf((String) t.getNewValue());
                        }
                    });
                    fieldy.setCellFactory(cellFactory);
                    fieldy.setOnEditCommit(new EventHandler<CellEditEvent<TableData, String>>() {           
                        @Override public void handle(CellEditEvent<TableData, String> t) {
                            ((TableData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSix(t.getNewValue());
                            Integer selected_actor = Integer.valueOf((String) zero.getCellData(zero.getTableView().getItems().get(t.getTablePosition().getRow())));
                            a.actor.get(selected_actor).point_y = Integer.valueOf((String) t.getNewValue());
                        }
                    });
                    
                    army.setItems(data);
                    army.setEditable(true);
                    army.getColumns().addAll(zero, name, strength, speed, health, fieldx, fieldy);
                }
            }
        });  
    }  
    
}



/* TO DO
 * - add code to account for different movement types of actors
 * - battle the armies
 */

/* BUGS:
 * - can load army when none is selected
 * - can delete army when it is in battlefield
 * - can battle empty armies
 * - documentation is missing
 */
