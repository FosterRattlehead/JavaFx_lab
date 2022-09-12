package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Presents the user with the game graphical user interface
 */
public class ViewGameBoard extends Application
{
    private Canvas gameCanvas;
    private ControllerGameBoard controller;
    private GameBoardLabel fishRemaining;
    private GameBoardLabel guessesRemaining;
    private GameBoardLabel message;

    public static void main(String[] args)
    {
        // TODO: launch the app
        //simple launch
        launch(args);
    }

    public void updateHeader() {
        //TODO update labels
        //using set text
        fishRemaining.setText("Fish: " + controller.modelGameBoard.getFishRemaining());
        guessesRemaining.setText("Bait: " + controller.modelGameBoard.getGuessesRemaining());
        if(controller.fishWin()) {
            message.setText("Fishes win!");
        } else if(controller.playerWins()) {
            message.setText("You win!");
        } else {
            message.setText("Find the fish!");
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        controller = new ControllerGameBoard();
        // TODO initialize gameCanvas
        gameCanvas= new Canvas();

        fishRemaining = new GameBoardLabel();
        guessesRemaining = new GameBoardLabel();
        message = new GameBoardLabel();

        // TODO display game there are infinite ways to do this, I used BorderPane with HBox and VBox.

        //for holidng the hbox
        VBox hboxHolders = new VBox();
        //for the text above the program not to be confused by the name in the stage
        HBox topLabs=new HBox();



        BorderPane borderPane = new BorderPane();


        // Adding the needed informatin Thanks to Kyle for helping me with this part
        //thanks to kyle for help with this part of the code
        topLabs.getChildren().addAll(fishRemaining,guessesRemaining,message);


        //setting the top labeles to the top
        borderPane.setTop(topLabs);
        //holds the hboxies that we will use in the botton
        borderPane.setCenter(hboxHolders);



        updateHeader();
        //thanks to John De Castro for help with this part of the code
        for (int row=0; row < ModelGameBoard.DIMENSION; row++) {
            // TODO: create row container
            // new hbox that will hold the bottons
            HBox rowCon = new HBox();
            rowCon.setAlignment(Pos.CENTER);



            for (int col=0; col < ModelGameBoard.DIMENSION; col++) {
                GameBoardButton button = new GameBoardButton(row, col, controller.modelGameBoard.fishAt(row,col));
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> {
                    controller.makeGuess(finalRow, finalCol);
                    if(!controller.isGameOver()) {
                        button.handleClick();
                        updateHeader();
                    }
                });

                // TODO: add button to row
                //adding bottons to our new h box
                rowCon.getChildren().add(button);



            }
            // TODO: add row to column
            //adding the h box that we created to the v box holder
            hboxHolders.getChildren().add(rowCon);




        }

        // TODO: create scene, stage, set title, and show
        // naming our stage
        stage.setTitle("Gone Fishing");
        //giving the window size
        Scene scene = new Scene(borderPane,300,300);
        // adding the sceans
        stage.setScene(scene);
        //shwoing the stage
        stage.show();

    }
}