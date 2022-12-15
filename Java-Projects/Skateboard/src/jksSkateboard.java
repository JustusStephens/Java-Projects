//Justus Stephens
//May 3rd 2020
//Cps 230
//Skateboard proj.

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.collections.ObservableList;

public class jksSkateboard extends Application
{
   //Fields
	
   CheckBox griptapeCheckBox;			// misc
   CheckBox bearingsCheckBox;
   CheckBox riserpadsCheckBox;
   CheckBox NutsnBoltsKitCheckBox;
   
   RadioButton TheMasterThrasher;		//deck
   RadioButton TheDictator;
   RadioButton TheStreetKing;
   

   Label thankLabel;
   Label totalLabel;				//Labels for oder, thanking customer, and total
   Label orderLabel;
   
   String location;						//to find combobox value
   ListView<String> listView1;
   ComboBox<String> comboBox;
   TextField name;						//used in the order printout
      
   public static void main(String[] args)
   {
      launch(args);
   }
   
   @Override
   public void start(Stage primaryStage)
   {
      //Create Labels
	   
      Label lblWelcome = new Label("The Skate n' Bake, skateboard shop");	//main label, shop name
      lblWelcome.getStyleClass().add("label-heading");
      
      Label lblName = new Label("Name: ");	
      lblName.getStyleClass().add("label-heading2");			//cust name

      
      Label lblMisc = new Label("Misc: pick what you want");    //misc
      lblMisc.getStyleClass().add("label-heading2");
      
      Label lblDeck = new Label("Choose your deck");			//deck
      lblDeck.getStyleClass().add("label-heading2");
      
      totalLabel = new Label ("");								//total
      totalLabel.getStyleClass().add("label-heading2");
      
      Label comboLabel = new Label("Select a Truck Configuration:");	//truck
      comboLabel.getStyleClass().add("label-heading2");
      
      Label spaceLabel = new Label();				//for spacing
      
      Label listLabel = new Label("Choose your wheels");	//wheels
      listLabel.getStyleClass().add("label-heading2");
      
      
      name = new TextField();
      
      //RadioButtons
      TheMasterThrasher = new RadioButton("The Master Thrasher         $60");
      TheDictator = new RadioButton("The Dictator                       $45");
      TheStreetKing = new RadioButton("TheStreetKing                    $50");
      
      //Add RadioButtons to a ToggleGroup
      ToggleGroup radioGroup = new ToggleGroup();
      TheMasterThrasher.setToggleGroup(radioGroup);
      TheDictator.setToggleGroup(radioGroup);
      TheStreetKing.setToggleGroup(radioGroup);
      
      //Create CheckBoxes
      griptapeCheckBox = new CheckBox("Grip Tape                       $10");
      bearingsCheckBox = new CheckBox("Extra Bearings               $30");
      riserpadsCheckBox = new CheckBox("Riser Pads                      $2");
      NutsnBoltsKitCheckBox = new CheckBox("Nuts n' Bolts Kit            $3");
      
      //Put the CheckBoxes in a VBox
      VBox checkBoxVBox = new VBox(10,lblMisc, riserpadsCheckBox, bearingsCheckBox, griptapeCheckBox, NutsnBoltsKitCheckBox);
      
      //Create the Button Control
      Button totalButton = new Button("Get Total Price");
      Button orderButton = new Button("Order");   
      
      //create exit & reset button control
      Button exitButton = new Button ("Exit");
      exitButton.setOnAction(new ExitHandler());
      
      Button resetButton = new Button ("Reset");
      resetButton.setOnAction(new ResetHandler());
      
      //Register the event handler
      totalButton.setOnAction(new TotalButtonHandler());
      orderButton.setOnAction(eve-> new orderStage());
                 
      //Create a ComboBox 
      comboBox = new ComboBox<>();
      comboBox.setPromptText("Select a Truck Configuration");
      comboBox.getItems().addAll("7.75 inch axle: $35", "8 inch axle: $40", "8.5 inch axle: $45");
      comboBox.setOnAction(event ->
      {
            location = comboBox.getValue();
      });
      
      //Create a ListView of the wheels
      listView1 = new ListView<>();
      listView1.setPrefSize(50,120);
      listView1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      listView1.getItems().addAll("51mm    $20", "55mm    $22", "58mm    $24", "61mm    $28");
      
      //Create Vbox and Hbox for controls
      VBox listVBox = new VBox(10, spaceLabel, listLabel, listView1);
      HBox buttonsHBox = new HBox(10, totalButton, orderButton, resetButton, exitButton);
      HBox title = new HBox(10, lblWelcome );
      VBox radioVBox = new VBox(10, comboLabel, comboBox, lblDeck, TheMasterThrasher, TheDictator, TheStreetKing);
      HBox optionsHBox = new HBox(20, radioVBox, checkBoxVBox, listVBox);
      HBox nameHBox = new HBox(10, lblName, name);
      
      //Give padding
      radioVBox.setPadding(new Insets(30));
      checkBoxVBox.setPadding(new Insets(30));
      optionsHBox.setPadding(new Insets(30));
      
      //Add everything to mainVBox
      VBox mainVBox = new VBox(10, title, nameHBox, optionsHBox, buttonsHBox, totalLabel);
      
      //Set main VBox to center
      mainVBox.setAlignment(Pos.CENTER);
      
      //Set main VBOx's padding to 10 pixels
      mainVBox.setPadding(new Insets(10));

      //Create a Scene with the VBox as its root node
      Scene scene = new Scene(mainVBox, 1000, 700);
      
      //Add the Scene to the Stage
      primaryStage.setScene(scene);
      primaryStage.setTitle("Skate n' Bake");
      scene.getStylesheets().add("jksSB.css");
      
      //Show the window
      primaryStage.show();
   }
   
   
   /**
      Event handler class for TotalButton
   */
   
   class TotalButtonHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
         //Variable to hold the result
          double result = getTotal();           
         //Display the results
         totalLabel.setText(String.format("$%,.2f", result));
      }
   }
   
   //handler for reset button
   class ResetHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
          totalLabel.setText("");
          orderLabel.setText("");
          thankLabel.setText("");
          TheMasterThrasher.setSelected(false);
          TheDictator.setSelected(false);
          TheStreetKing.setSelected(false);
          griptapeCheckBox.setSelected(false);
          bearingsCheckBox.setSelected(false);
          riserpadsCheckBox.setSelected(false);
          NutsnBoltsKitCheckBox.setSelected(false);
          comboBox.setValue(null);
          comboBox.setPromptText("Select a Truck Assembly");
          listView1.getSelectionModel().clearSelection();
       }
   }
   
   //handler for exit button
   class ExitHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
         System.exit(0);
      }
   }  
   
   public double getTotal()
   {
         double result = 0;
         
         //Add the base pizza price
         if (TheMasterThrasher.isSelected())
            result += 60;
         if (TheDictator.isSelected())
            result += 45;
         if (TheStreetKing.isSelected())
            result += 50;
         
         //add the extra misc items
         
         if(griptapeCheckBox.isSelected())
            result += 10;
         if(bearingsCheckBox.isSelected())
            result += 30;            
         if(riserpadsCheckBox.isSelected())
            result += 2;
         if(NutsnBoltsKitCheckBox.isSelected())
            result += 3;
         
         
         // add wheels
         
         ObservableList<Integer> selections = listView1.getSelectionModel().getSelectedIndices();
         Integer[] itemArray = selections.toArray(new Integer[0]);
         
         for(int i = 0; i < itemArray.length; i++)
         {
           if (itemArray[i] == 0)
              result += 20;
           if (itemArray[i] == 1)
              result += 22;
           if (itemArray[i] == 2)
              result += 24;
           if (itemArray[i] == 3)
              result += 28;
         } 
         
         //add trucks
         
         String truck;
         truck = comboBox.getValue();
         if (truck == "7.75 inch axle: $35")
   		  result += 35;
     else if(truck == "8 inch axle: $40")
   	  result += 40;
     else if(truck == "8.5 inch axle: $45")
   	  result += 45;
         

      return result;
   }

class orderStage
{
   orderStage()
   {
      Stage orderStage = new Stage();
   
      orderLabel = new Label("");
      orderLabel.getStyleClass().add("label-heading2");
      thankLabel = new Label ("");
      thankLabel.getStyleClass().add("label-heading");
               
      //create exit & reset button control
      Button exitButton = new Button ("Exit");
      exitButton.setOnAction(new ExitHandler());
           
      
      //begin adding strings to final order printout
      
      String order = "Order:  \n";
      double result = getTotal();
      result = result * 1.06;
         
         if (TheMasterThrasher.isSelected())
              order += "The Master Thrasher Deck\nMisc:  ";
         if (TheDictator.isSelected())
             order += "The Dictator Deck\nMisc:  ";
         if (TheStreetKing.isSelected())
               order += "The Street King Deck\nMisc:  ";
         
         //add misc items
         
         if(griptapeCheckBox.isSelected())
            order += "Grip Tape\t";
         if(bearingsCheckBox.isSelected())
             order += "Extra Bearings\t";
         if(riserpadsCheckBox.isSelected())
            order += "Riser Pads\t";
         if(NutsnBoltsKitCheckBox.isSelected())
            order += "Nuts n' Bolts Kit\t";
         
         
         //wheels string order
         
         order += "\nWheels: "; 
         
          //Get the ObservableList of selected items
          ObservableList<Integer> selections = listView1.getSelectionModel().getSelectedIndices();
          Integer[] itemArray = selections.toArray(new Integer[0]);
          
          for(int i = 0; i < itemArray.length; i++)
          {
            if (itemArray[i] == 0)
               order += "51mm wheels\t";
            if (itemArray[i] == 1)
               order += "55mm wheels\t";
            if (itemArray[i] == 2)
               order += "58mm wheels\t";
            if (itemArray[i] == 3)
               order += "61mm wheels\t";
          } 
          
          
          //combo box order
          
          String truck;
          truck = comboBox.getValue();
          
          order+="\nTrucks: ";

          if (truck == "7.75 inch axle: $35")
        		  order += "7.75 inch axle";
          else if(truck == "8 inch axle: $40")
        	  order += "8 inch axle: $40";
          else if(truck == "8.5 inch axle: $45")
        	  order += "8.5 inch axle: $45";
        	  
          
         order += "\nTotal Price: " + String.format("$%,.2f", result);
         totalLabel.setText("");
         orderLabel.setText(order);
         thankLabel.setText("\nThank you " + name.getText() + ", for choosing Skate n' Bake!");
   
      HBox buttonsHBox = new HBox(10, exitButton);
      //Add to a VBox
      VBox mainVBox = new VBox(40, orderLabel, thankLabel, buttonsHBox);
      
      //Set the main VBox's alignment to center
      mainVBox.setAlignment(Pos.CENTER);
      
      //Set the main VBOx's padding to 10 pixels
      mainVBox.setPadding(new Insets(10));
     
      Scene scene = new Scene(mainVBox, 1100, 700);
      scene.getStylesheets().add("jksSB.css");
      orderStage.setScene(scene);
      orderStage.setTitle("Skate n' Bake order");
      orderStage.show();
   }
   }
}
