package root;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.*;

public class Menu extends Application{

    Stage mainmenu;
    Scene sq,mm;

    //Classes
    Profile pro1 = new Profile();
    Fileio fd = new Fileio();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainmenu = primaryStage;
        mainmenu.setOnCloseRequest(lam -> mainmenu.close());
        //new profile
        Button profile = new Button("Create new profile");
        profile.setOnAction(prof ->
            {
               pro1.title();
            }
        );
        //select
        Button select = new Button("Select an existing profile");

        //Quit button
        Button quit = new Button("Quit");
        quit.setOnAction(aquit->
            {
                mainmenu.setScene(sq);
            }
        );

            //START
            Button begin = new Button("Start");
            begin.setMaxWidth(200);
            begin.setMaxHeight(200);
            begin.setOnAction(prof ->
                    {
                        System.out.println("I'd call the xbox function here");
                    }
            );
        Button nope = new Button("Stop");
        nope.setMaxWidth(200);
        nope.setMaxHeight(200);
        nope.setOnAction(prof ->
                {
                    System.out.println("I'd stop calling the xbox function here");
                }
        );


        //Layout 1 - children laid out in vertical column
        StackPane mmlayout = new StackPane();
        mmlayout.getChildren().addAll(begin, profile, select, quit);
        StackPane.setAlignment(begin, Pos.TOP_CENTER);
        StackPane.setAlignment(quit, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(profile, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(select, Pos.BOTTOM_LEFT);
        mm = new Scene(mmlayout, 600, 600);


        Button cancel = new Button("CANCEL");
        cancel.setOnAction(aquit->
                {
                mainmenu.setScene(mm);
                }
        );
        //Layout 2
        StackPane quitscreen = new StackPane();
        quitscreen.getChildren().add(cancel);
        StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
        sq = new Scene(quitscreen, 600, 600);
        mainmenu.setScene(mm);
        mainmenu.show();
    }


}
