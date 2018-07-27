/**
 *  All my Eggs in one Carton
 *  EggCarton.java
 *  @author  Alice Fischer 
 *  @version March, 2015, from earlier versions of March 2013, 3/18/08, 11/28/04.
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Ellipse;
import javafx.geometry.*;               // For Insets

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.*;                         // For ArrayList


public class EggCarton extends Application {
    // Model -------------------------------------------------------------------
    private ArrayList<Egg> bin  = new ArrayList<Egg>();      // To sell.
    private int nEggs           = bin.size();

    //--------------------------------------------------------------------------
    Button buyBut = new Button ("Buy");
    Button doneBut = new Button ("Done");

    //--------------------------------------------------------------------------
    Stage st;
    BorderPane sellPane = new BorderPane();
    Scene sellScene = new Scene( sellPane, 300, 300 );
    Pane grass      = new Pane();
    Text have       = new Text ("  I have 0 eggs today.");
    TextField need  = new TextField( "0" );

    Pane buyPane    = new Pane();
    Scene buyScene  = new Scene( buyPane, 300, 250 );
    //--------------------------------------------------------------------------
    Background bkIvory = new Background(
         new BackgroundFill( Color.IVORY, null, new Insets(2)) );
    Background bkGrass = new Background(
        new BackgroundFill(new Color(.929, 1., .855, 1.), null, new Insets(2)));

    //--------------------------------------------------------------------------
    private void buildGui(){
        Font fn = Font.font ("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 18);
        have.setFont(fn);

        Text query = new Text("  How many do you need?  ");
        query.setFont(fn);
        HBox orderBox = new HBox() ;
        orderBox.getChildren().addAll( query, need, buyBut, doneBut );

        HBox buttonBox = new HBox( 10 ) ;
        buttonBox.getChildren().addAll( buyBut, doneBut );
        buttonBox.setAlignment(Pos.CENTER);

        grass.setBackground(bkGrass);

        VBox info = new VBox() ;
        info.setMargin(have, new Insets(2,2,2,2));
        info.setMargin(orderBox, new Insets(0,2,0,2));
        info.setMargin(buttonBox, new Insets(2));

        info.getChildren().addAll( have, orderBox, buttonBox );

        sellPane.setCenter( grass );
        sellPane.setBottom( info );

        buyPane.setBackground(bkIvory);
        st.setScene(sellScene);
    }

    //--------------------------------------------------------------------------
    public void start( Stage st ) {
        this.st = st;
        st.setTitle("Very Fresh Eggs Today!");

		// Register listeners for buttons and mouse. -------------------------
        buyBut.setOnAction     (  e -> { buy( e ); }             );
        doneBut.setOnAction    (  e -> { st.setScene(buyScene); });
        grass.setOnMouseClicked(  e -> { doClick( e ); }         );

        buildGui();
		st.show();
    }

    //--------------------------------------------------------------------------
    public void doClick( MouseEvent e ){
        double  x = e.getX();
        double  y = e.getY();

        Egg egg1 = new Egg(e.getX(), e.getY());
        bin.add( egg1 );
        nEggs = bin.size();
        grass.getChildren().add( egg1 );
        have.setText( "  I have "+ nEggs + " eggs today." );
        System.out.println("Laying egg. " + nEggs);
   }

    //--------------------------------------------------------------------------
    public void buy ( ActionEvent e ) {
        int k, nBuy = Integer.parseInt (need.getText());
        System.out.println( "Selling " + nBuy );

        if (nBuy > nEggs) {
            have.setText( "  We don't have that many eggs!" );
            return;
        }

        for(k = nBuy; k > 0; --k) {
            if ( bin.size() == 0) break;    // Can't sell what you don't have.
            buyPane.getChildren().add(bin.remove(0)); // Sell oldest egg first.
            System.out.println("Sold one,");
        }
        System.out.println("Remaining: " + bin.size());
        nEggs = bin.size();
        have.setText( "  I have "+ nEggs + " eggs today." );
        need.setText("0");
    }
}

