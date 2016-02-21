package root;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.*;

public class Menu extends Application{

    Stage mainmenu;
    int counter=0;
    Scene sq,mm;
    String[] current = new String[5];
    Label activepro = new Label();

    //Classes
    public static Profile pro1 = new Profile();
    public static Xbox box = new Xbox();

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
        Button select = new Button("Select profile");
        select.setOnAction(sel ->
        {
            pro1.selector();
            String keeper=pro1.ofn;
            String[] holder=new String[5];
            if(pro1.test==true)
            {
                if(counter < 5)
                {
                    holder[counter] = pro1.ofn;
                    counter++;
                    System.out.println(counter);
                    for(int i=0;i<5;i++)
                    {
                        System.out.println(holder[i]);
                    }
                }

                activepro.setText("Active profile:"+keeper);
            }
            else
            {
                activepro.setText("NO ACTIVE PROFILE");
            }

        });

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
                        box.setup();
                        box.polling();
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
        mmlayout.getChildren().addAll(begin, profile, select, quit,activepro);
        StackPane.setAlignment(begin, Pos.TOP_CENTER);
        StackPane.setAlignment(activepro,Pos.CENTER);
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
        mm.getStylesheets().add("style.css");
        sq = new Scene(quitscreen, 600, 600);
        mainmenu.setScene(mm);
        mainmenu.show();
    }
}
