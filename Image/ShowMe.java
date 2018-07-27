/**
 *  Show Me a Picture and Play me a Sound
 *  ShowMe.java
 *  @author  Alice Fischer
 *  @version March, 2015.
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;           // For FlowPane

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.*;               // For Insets
import javafx.scene.media.AudioClip;
import java.net.URL;

public class ShowMe extends Application {

    GridPane    field   = new GridPane();
    Scene       sn      = new Scene (field, 206, 206);
    Image       empty   = new Image( "empty.jpg" );
    Image       bird    = new Image( "eagle.jpg" );
    Image       flower  = new Image( "purple.jpg" );

    ImageView iv0 = new ImageView( empty );
    ImageView iv1 = new ImageView( bird );
    ImageView iv2 = new ImageView( flower );
    ImageView iv3 = new ImageView( bird );
    ImageView iv4 = new ImageView( flower );

    URL res1 = getClass().getResource("birds.wav");
    AudioClip tweet = new AudioClip(res1.toString());
    URL res2 = getClass().getResource("graze.wav");
    AudioClip graze = new AudioClip(res2.toString());

    public void start( Stage st ) {
        iv2.setRotate(90);
        iv3.setRotate(180);
        iv4.setRotate(270);

        field.setHgap(3);
        field.setVgap(3);
        field.add(iv0, 0, 0);   // Experiment-- can we change the image?
        field.add(iv2, 1, 0);
        field.add(iv4, 0, 1);
        field.add(iv3, 1, 1);
        //field.setPadding( new Insets(3, 3, 3, 3));

        st.setTitle("Show Me");
        st.setScene(sn);
        st.show();
        field.getChildren().remove(iv4);
        // field.add(iv1, 0, 0);   // Change the image.
        graze.play();
        // tweet.play();
    }
}
