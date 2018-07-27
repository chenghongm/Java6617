/**
 * Popup.java
 * @author  Anthony Riegel, simplified and modified by A. Fischer
 * @version fall, 2004, modified Winter 2012 converted to FX February 2015
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.*;   // VBox and HBox
import javafx.scene.text.*;     // Font
import javafx.geometry.Pos;     // Pos.CENTER

import javafx.scene.control.*;  // Label, TextField
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Popup extends Application {
    private static String[] msg = {
    "You can do better than that!",
    "Everybody starts here.",
    "The silver medal goes to you.",
    "It's your lucky day.",
    "Happy is a state of mind.",
    "Things are getting stranger.",
    "Prices are climbing.",
    "Today is tomorrow's yesterday~",
    "Please come again."
    };
    private static final int quit = msg.length-1;

    int choice;
    Font fn = Font.font ("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 20);

    Scene s1;   // Initial scene
    Scene s2;   // Retort scene

    TextField choiceField = new TextField("Enter an integer");
    Text retortField = new Text();

    Button enterBut = new Button( "Enter" );
    Button okBut = new Button( "OK" );
    Stage st;   // Need to switch scenes on this stage.

    //------------------------------------------------------------------------
    public void start(Stage st) {
        this.st = st;
        choiceField.setFont(fn);
        retortField.setFont(fn);

        VBox v1 = new VBox();
        v1.setSpacing( 5 );
        v1.setAlignment(Pos.CENTER);
        v1.getChildren().addAll( choiceField, enterBut );
        s1 = new Scene( v1, 300, 70 );   // Set window size.
        enterBut.setOnAction( new EnterAction() );

        VBox v2 = new VBox();
        v2.setSpacing( 5 );
        v2.setAlignment(Pos.CENTER);
        v2.getChildren().addAll( retortField,okBut );
        s2 = new Scene( v2, 300, 70 );   // Set window size.
        okBut.setOnAction( new OkAction() );

        st.setTitle("8-ball");
        st.setScene(s1);
        st.show();
    }
    //--------------------------------------------------------------------------
    class EnterAction implements EventHandler<ActionEvent> {
        @Override
        public void handle( ActionEvent e ){
            choice = Integer.parseInt(choiceField.getText());
            choice %= 10;
            if ( choice > quit ) System.exit(0);

            retortField.setText(msg[choice]);
            choiceField.setText("0");
            st.setScene(s2);
        }
    }

    //--------------------------------------------------------------------------
    class OkAction implements EventHandler<ActionEvent> {
        @Override
        public void handle( ActionEvent e ){
            st.setScene(s1);
        }
    }
}