import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*; 
import javafx.scene.control.*;
import java.util.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * @author Yerim Oh
 * @date March 6, 2023
 */

public class Calc2 extends Application {
	
	public static void main(String[] args) {
		launch(args); 
	}
	
	Button btnEqual;
	Button btnAC;
	Button btnAdd;
	Button btnSub;
	Button btnMulti;
	Button btnDiv;
	Button btnRemain;
	Button btnFloat;
	ArrayList<Button> btnDigits;
	TextField txtF;
	
	//Stores the operands and operator
	String s1, s2, s3;
	
	/**
	 * Sets the stage for the application by initializing buttons, textfield and their layout.
	 */
	
	@Override public void start(Stage primaryStage) {
	
		// Create the textfield
		txtF = new TextField();
		
		// Create the buttons
		btnEqual = new Button();
		btnAC = new Button();
		btnAdd = new Button();
		btnSub = new Button();
		btnMulti = new Button();
		btnDiv = new Button();
		btnRemain = new Button();
		btnFloat = new Button();
		btnDigits = new ArrayList<Button>(10);
		
		// Initialize the operands and operators
		s1="";
		s2="";
		s3="";
		
		//============== Label the Buttons ==============
		
		// Set labels for AC, +, -, =, *, /, %, . buttons
		btnAC.setText("AC"); 
		btnEqual.setText("="); 
		btnAdd.setText("+"); 
		btnSub.setText("-"); 
		btnMulti.setText("*");
		btnDiv.setText("/");
		btnRemain.setText("%");
		btnFloat.setText(".");

		// set labels for digit buttons
		for(int i = 0;i < 10; i++)
		{
			Button temp = new Button();
			temp.setText(String.valueOf(i));
			btnDigits.add(temp);
		}
		
		
		//============== Set background color for AC Button ==============
		
		btnAC.setStyle("-fx-background-color:Red");		
		
		
		//==== Set text alignment, Disable editing via typing, Set a width ====
		
		txtF.setAlignment(Pos.CENTER_RIGHT);
		txtF.setEditable(false);
		txtF.setMaxWidth(100);
		
		
		//============== Set Buttons to action ==============

		// Set actions for AC, +, -, =, *, /, %, . buttons
		btnAC.setOnAction(this::buttonClickReset);
		btnAdd.setOnAction(this::buttonClickAdd);
		btnSub.setOnAction(this::buttonClickSub);
		btnEqual.setOnAction(this::buttonClickEqual);
		btnMulti.setOnAction(this::buttonClickMultiply);
		btnDiv.setOnAction(this::buttonClickDivide);
		btnRemain.setOnAction(this::buttonClickRemainder);
		btnFloat.setOnAction(this::buttonClickFloat);
		
		// Set actions for digit buttons
		for(int i = 0;i<10;i++) {
			btnDigits.get(i).setOnAction(this::buttonClickDigit);
		}
		
		
		//============== Design ==============
		
		// Add the button to a layout pane BorderPane 
		VBox pane = new VBox(20); 
		pane.setAlignment(Pos.CENTER);
		
		HBox top_box = new HBox(30);
		HBox.setMargin(txtF, new Insets(0,0,0,15));
		top_box.getChildren().add(txtF);
		top_box.getChildren().add(btnAC);
		top_box.getChildren().add(btnEqual);		
		
		HBox[] mid_box = new HBox[3];//(40);
		mid_box[0] = new HBox(40);
		HBox.setMargin(btnDigits.get(1), new Insets(0,0,0,15));
		mid_box[0].getChildren().addAll(btnDigits.subList(1, 4));
		mid_box[0].getChildren().add(btnAdd);
		
		mid_box[1] = new HBox(40);
		HBox.setMargin(btnDigits.get(4), new Insets(0,0,0,15));
		mid_box[1].getChildren().addAll(btnDigits.subList(4, 7));
		mid_box[1].getChildren().add(btnSub);
		
		mid_box[2] = new HBox(40);
		HBox.setMargin(btnDigits.get(7), new Insets(0,0,0,15));
		mid_box[2].getChildren().addAll(btnDigits.subList(7, 10));
		mid_box[2].getChildren().add(btnMulti);
		
		HBox bot_box = new HBox(40);
		HBox.setMargin(btnFloat, new Insets(0,0,0,18));
		bot_box.getChildren().add(btnFloat);
		bot_box.getChildren().add(btnDigits.get(0));
		bot_box.getChildren().add(btnRemain);
		bot_box.getChildren().add(btnDiv);
		
		pane.getChildren().addAll(top_box, mid_box[0], mid_box[1], mid_box[2], bot_box);
		
		// Add the layout pane to a scene
		Scene scene = new Scene(pane, 250, 250);
		
		// Finalize and show the stage
		primaryStage.setScene(scene); 
		primaryStage.setTitle("Simplest Calculator"); 
		primaryStage.show();
	}
	
	/**
	 * Reset the operands, operators and the textfield when AC is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickReset(ActionEvent e) {		
		s1= "";
		s2 = "";
		s3 = "";
		txtF.setText("");		
	}	
	
	/**
	 * Determine the operands when digits are clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickDigit(ActionEvent e) {
		if (s3.equals("")) {
			s1 += ((Button)e.getSource()).getText();
			txtF.setText(s1);
		} else {
			s2 += ((Button)e.getSource()).getText();
			txtF.setText(s2);
		}
	}
	
	/**
	 * Determine the operators when "+" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickAdd(ActionEvent e) {	    
		s3 = "+";
		txtF.setText(s1);		
	}
	
	/**
	 * Determine the operator when "-" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickSub(ActionEvent e) {	    	
		s3 = "-";
		txtF.setText(s1);
	}
	
	/**
	 * Determine the operators when "*" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickMultiply(ActionEvent e) {	    
		s3 = "*";
		txtF.setText(s1);		
	}
	
	/**
	 * Determine the operators when "/" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickDivide(ActionEvent e) {	    
		s3 = "/";
		txtF.setText(s1);		
	}
	
	/**
	 * Determine the operators when "%" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickRemainder(ActionEvent e) {	    
		s3 = "%";
		txtF.setText(s1);		
	}
	
	/**
	 * Determine the operators when "." is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickFloat(ActionEvent e) {	    		
		if (s3.equals("")) {
			s1 += ".";
			txtF.setText(s1);
		} else {
			s2 += ".";
			txtF.setText(s2);
		}
	}
	
	/**
	 * Determine the result in the textfield when "=" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickEqual(ActionEvent e) {
		Float answer = 0f;
		if (s3.equals("+")) {
			answer = Float.parseFloat(s1) + Float.parseFloat(s2);
		} else if(s3.equals("-")) {
			answer = Float.parseFloat(s1) - Float.parseFloat(s2);
		} else if(s3.equals("*")) {
			answer = Float.parseFloat(s1) * Float.parseFloat(s2);
		} else if(s3.equals("/")) {
			answer = Float.parseFloat(s1) / Float.parseFloat(s2);
		} else if(s3.equals("%")) {
			answer = Float.parseFloat(s1) % Float.parseFloat(s2);
		}
		
		// when s2 is 0 and s3 is / or %, return NA
		if (answer == Double.POSITIVE_INFINITY || answer.isNaN()){
			txtF.setText("NA");
		// when the fractional part is not zero, return integer
		} else if (answer%1 == 0) {
			String res = String.valueOf(answer.intValue());
			txtF.setText(res);
		} else {
			String res = String.valueOf(answer);
			txtF.setText(res);
		}

		// Reset the operands and operator after the result is calculated.
		s3="";
		s2="";
		s1="";
	}
}