/* ----------------------------------------------------------------------
 * Game.java
 * Created on March 20, 2015, to replace Swing version from April 6, 2007
 */
import javafx.application.Platform;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;             // for Text
import javafx.scene.layout.*;           // For Panes
import javafx.geometry.*;               // For Insets

import javafx.scene.paint.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.animation.*;              // For Timeline
import javafx.util.Duration;            // For Timeline

public class Game extends Application implements EventHandler<ActionEvent>
{
    // For use in the text-based display
    public enum Token {EEE, XXX, OOO};
    public enum State {PLAY, OVER, BAD, HUMAN, TIC};
    
    // The mdl.
    private Pane[] squares = new Pane[9];
    private Token[] mdl = new Token[9];
    private State S;
    private int movesLeft;
    private int gamesPlayed = 0;
    private int wonHuman = 0;
    private int wonTictac = 0;
    // -------------------------------------------------------------------------
    private Class handle;
    private AudioClip tweet, graze;
    Image empty     = new Image( "empty.jpg" );
    Image bird      = new Image( "eagle.jpg" );
    Image flower    = new Image( "purple.jpg" );
    
    // The Game Board ==========================================================
    Pane human = new Pane();
    Pane tictac = new Pane();
    
    //==========================================================================
    // Other GUI widget declarations.
    BorderPane  mainPane        = new BorderPane();
    Scene       sn              = new Scene( mainPane, 410, 350);
    VBox        leftPane        = new VBox(6);
    GridPane    scorePane       = new GridPane();
    GridPane    board           = new GridPane();
    HBox        buttonPane      = new HBox(8);
    Button      newButton       = new Button("New Game");
    Button      stopButton      = new Button("Quit");
    Text        outputField     = new Text("Choose a square");
    Text        scoreHuman      = new Text("0");
    Text        scoreTictac     = new Text("0");
    Text        playedField     = new Text("0");
    Label       humanLabel      = new Label ("Human");
    Label       tictacLabel     = new Label ("TicTac");
    Label       playedLabel     = new Label ("Games");
    
    // -------------------------------------------------------------------------
    // Called from within the constructor to initialize the GUI.
    private void buildGui() {
        Background bkLavender = new Background(
             new BackgroundFill( Color.LAVENDER, null, new Insets(1)) );
        mainPane.setBackground(bkLavender);
        
        buttonPane.setAlignment( Pos.CENTER );
        buttonPane.setPadding( new Insets(4) );
        buttonPane.getChildren().addAll(newButton, outputField, stopButton);
        scorePane.setHgap( 10 );
        scorePane.setVgap( 10 );
        scorePane.setAlignment( Pos.CENTER );
        scorePane.setMinSize( 100, 93 );
        scorePane.add(humanLabel, 0, 0);
        scorePane.add(scoreHuman, 1, 0);
        scorePane.add(playedLabel, 0, 1);
        scorePane.add(playedField, 1, 1);
        scorePane.add(tictacLabel, 0, 2);
        scorePane.add(scoreTictac, 1, 2);
        leftPane.getChildren().addAll(human, scorePane, tictac);
        
        resetBoard();
        board.setHgap( 3 );
        board.setVgap( 3 );
        board.add(human, 0, 0);
        board.add(squares[0], 1, 0);
        board.add(squares[1], 2, 0);
        board.add(squares[2], 3, 0);
        board.add(scorePane, 0, 1);
        board.add(squares[3], 1, 1);
        board.add(squares[4], 2, 1);
        board.add(squares[5], 3, 1);
        board.add(tictac, 0, 2);
        board.add(squares[6], 1, 2);
        board.add(squares[7], 2, 2);
        board.add(squares[8], 3, 2);
        mainPane.setLeft(leftPane);
        mainPane.setBottom(buttonPane);
        mainPane.setCenter(board);
        
        human.getChildren().add(new ImageView(flower));
        tictac.getChildren().add(new ImageView(bird));
        
        System.out.println("Gui is initialized.");
    }
    
    //==========================================================================
    public void start( Stage st ) {
        st.setTitle("Tic Tac Toe");
        handle = this.getClass();
        URL res1 = handle.getResource("birds.wav");
        tweet = new AudioClip(res1.toString());
        
        URL res2 = handle.getResource("graze.wav");
        graze = new AudioClip(res2.toString());
        System.out.println("In game start.");
        
        newButton.setOnAction( this );
        stopButton.setOnAction(e->{ Platform.exit(); });
        
        for( int k=0; k<9; ++k) {
            final int kk = k;
            squares[kk] = new Pane();
            squares[kk].setOnMouseClicked(e->{ move2Players(kk); });
        }
        buildGui();
        
        st.setScene( sn );
        st.show();
    }
    
    //==========================================================================
    public Token    getToken (int square)   { return mdl[square]; }
    public boolean  isOver ()               { return S==State.OVER; }
    public void     setText( String st)     { outputField.setText( st ); }
    
    //-------------------------------------------------------------------------
    public void handle( ActionEvent e ) {
        System.out.println("Starting a new game.");
        outputField.setText("Choose a square");
        resetBoard();
    }
    
    //==========================================================================
    // To produce the text-based display.
    public String toString(){
        StringBuffer tac = new StringBuffer(" ------------------\n");
        for(int k=0; k<9; ++k){
            tac.append("| "+mdl[k]);
            if (k%3 == 2) tac.append(" |\n");
        }
        tac.append(" ------------------");
        tac.append("  Moves left: "+ movesLeft );
        return tac.toString();
    }
    
    
    //==========================================================================
    // Play the game. ----------------------------------------------------------
    //--------------------------------------------------------------------------
    public void move2Players(int square){
        if (gameOver() || mdl[square] != Token.EEE) {
            System.out.println("Illegal move.");
            return;
        }
        
        movePlayer (0, square);
        if (gameOver()) {    // Maybe the human won the game on this turn.
            S = State.OVER;
            outputField.setText("You Won!");
            System.out.println("Human won.");
            wonHuman ++;
            scoreHuman.setText(Integer.toString( wonHuman ));
            graze.play();
        }
        else if (movesLeft == 0) {
            outputField.setText("It's a Draw!"); // All 9 squares are full.
            S = State.OVER;
        }
        else {  // !!! I have a variable set to a lambda function!
            EventHandler<ActionEvent> evh = (e-> {
                // Perform Tictac's move.
                int sq = findEmptySquare();
                movePlayer (1, sq);
                if (gameOver()) {    // Maybe the human won the game on this turn.
                    outputField.setText("Tictac Won!");
                    System.out.println("Tictac won.");
                    wonTictac ++;
                    scoreTictac.setText(Integer.toString( wonTictac ));
                    tweet.play();
                }
            });
            // Delay Tictac's move.
            Timeline t = new Timeline(new KeyFrame( Duration.millis(500), evh));
            t.setCycleCount(1);
            t.play();
        }
    }
    
    //--------------------------------------------------------------------------
    // Make one move------------------------------------------------------------
    private void movePlayer(int player, int square){
        System.out.println("In move, player "+ player + "  square "+ square);
        if (mdl[square] != Token.EEE) return;
        
        if (player==1)  {
            mdl[square] = Token.XXX;
            squares[square].getChildren().remove(0);
            squares[square].getChildren().add(new ImageView(bird));
        }
        else  {
            mdl[square] = Token.OOO;
            squares[square].getChildren().remove(0);
            squares[square].getChildren().add(new ImageView(flower));
        };
        //squares[square].setDisabled(true);
        --movesLeft;
        System.out.println(this);
    }
    
    //--------------------------------------------------------------------------
    // Find any square that is not already filled. -----------------------------
    int findEmptySquare(){
        if (mdl[4] == Token.EEE) return 4;  // The best move, if available.
        int n = (int)(Math.random()* movesLeft);
        
        // Select nth empty square.
        for( int k=0; k<9; ++k ) {
            if (mdl[k] != Token.EEE) continue;
            if (n == 0) return k;   // Found nth empty square -- return it.
            n--;                    // Otherwise count it.
        }
        // Control should never reach this line.
        return -1;
    }
    
    
    //--------------------------------------------------------------------------
    // Test for three-in-a-row. ------------------------------------------------
    public boolean gameOver() {
        S = State.PLAY;
        System.out.println("In gameOver function.");
        // Check the two diagonals.
        if (mdl[0]!=Token.EEE && mdl[0]==mdl[4] && mdl[0]==mdl[8]) {
            System.out.println("Checking 1st diagonal");
            S = State.OVER;
        }
        else if (mdl[2]!=Token.EEE && mdl[2]==mdl[4] && mdl[2]==mdl[6]) {
            System.out.println("Checking 2nd diagonal");
            S = State.OVER;
        }
        else {  // Check all three rows.
            System.out.println("Checking rows");
            for (int k = 0; k < 9; k+=3) {
                if (mdl[k] == Token.EEE) continue;
                if (mdl[k]==mdl[k+1] && mdl[k]==mdl[k+2])  {
                    S = State.OVER;
                    break;
                }
            }
            // Check all three columns.
            System.out.println("Checking columns");
            for (int k = 0; k < 3; k++) {
                if (mdl[k] == Token.EEE) continue;
                if (mdl[k] == mdl[k+3] && mdl[k] == mdl[k+6]) {
                    S = State.OVER;
                    break;
                }
            }
        }
        System.out.println("Returning from GameWon.");
        playedField.setText( Integer.toString(gamesPlayed) );
        scoreTictac.setText( Integer.toString(wonTictac) );
        scoreHuman.setText( Integer.toString(wonHuman) );
        return S==State.OVER;
    }
    
    //--------------------------------------------------------------------------
    // Reset the Model and the Board for the new game.
    public void resetBoard(){
        for (int k=0; k<9; ++k)  {
            squares[k].getChildren().add(new ImageView(empty));
            mdl[k] = Token.EEE;
        }
        gamesPlayed ++;
        S = State.PLAY;
        movesLeft = 9;
    }
}
