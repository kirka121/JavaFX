package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class SampleController implements Initializable {
    @FXML
    private Label msgbar, army_name, specific_creature_amount_label;
    @FXML
    private Button load_army, create_the_army, delete_army, new_army;
    @FXML
    private Pane make_new_army;
    @FXML
    private ComboBox creature_type_selection;
    @FXML
    private RadioButton creature_type_specific, creature_type_random;
    @FXML
    private ListView<String> list_armies;
    @FXML
    private ColorPicker army_color;
    @FXML
    private TextField army_name_entry, specific_creature_amount;
    @FXML
    private Circle display_army_color;
    @FXML 
    private TableColumn ID,Type,Name,Health,Strength,Speed;
    @FXML
    private TableView army;
    @FXML
    private TableCell somesell;
    
    private ArrayList<Army> myarmy = new ArrayList<>();
    final ObservableList<String> listItems = FXCollections.observableArrayList(); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert new_army != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
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
        // Add a ChangeListener to ListView to look for change in focus
        list_armies.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(list_armies.isFocused()){
                    int selectedItem = list_armies.getSelectionModel().getSelectedIndex();
                    Army a = myarmy.get(selectedItem);
                    army_name.setText(a.name_of_army);
                    display_army_color.setVisible(true);
                    display_army_color.setFill(Paint.valueOf(a.army_color));
                    
                    /*
                    for(int i = 1; i<=a.actor.size();i++){
                        ID.setText(a.actor.get(i).aSequence.toString());
                        Name.setText(a.actor.get(i).aName);
                        Health.setText(a.actor.get(i).aHealth.toString());
                        Strength.setText(a.actor.get(i).aStrength.toString());
                        Speed.setText(a.actor.get(i).aSpeed.toString());
                    }
                    */
                }
            }
        });  
    }    
}


/* TO DO
 * - load armies into table when selected on left sidebar
 * - load armies into battlefield, only 2 at a time
 * - add code to account for X,Y coords of actors
 * - add code to account for different movement types of actors
 * - allow to edit armies
 * - save armies to hard disk
 * - load armies from hard disk
 * - battle the armies
 */
