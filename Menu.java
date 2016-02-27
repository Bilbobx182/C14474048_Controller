package root;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.util.ArrayList;

public class Menu extends Application
{

    Stage mainmenu;
    int counter=0;
    Scene sq,mm;
    Label activepro = new Label();
    public static ArrayList<String> prolist = new ArrayList<>();
    boolean active;
    String piepro;

    //Classes
    public static Profile pro1 = new Profile();
    public static Xbox box = new Xbox();
    public static Fileio fd = new Fileio();
    public static Process pr = new Process();


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainmenu = primaryStage;
        mainmenu.setOnCloseRequest(lam -> mainmenu.close());
        box.setup();
        mainmenu.setTitle("Controller Home Screen");

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

            if(pro1.test==true)
            {
                System.out.println("NULL CALLER");

              fd.inputgetter();
                activepro.setText("Active profile:"+keeper);
                active=true;
            }
            else
            {
                activepro.setText("NO ACTIVE PROFILE");
            }

        });

        Pie pie=new Pie();
        //Quit button
        Button quit = new Button("Quit");
        quit.setOnAction(aquit->
                {
                    mainmenu.close();
                    System.exit(0);
                }
        );

        //START
        Button begin = new Button("Start");
        begin.setMaxWidth(900000000);
        begin.setMaxHeight(50);
        begin.setOnAction(prof ->
                {
                    Gameopen();
                    if (active == true)
                    {
                        box.setvars();
                        box.timer();
                        box.polling();

                        pie.render(piepro);
                     //   System.out.println("YET TO BOMB OUT 4");
                        box.setvars();
                    }
                    else
                    {
                        activepro.setText("NO ACTIVE PROFILE");
                    }
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
        mm.getStylesheets().add("style.css");
        mainmenu.setScene(mm);
        mainmenu.show();
    }

    void Gameopen()
    {
        int i=0;
        int j=0;
        fd.wrpcheck();
       // System.out.println(fd.profiles.get(1));
        // System.out.println(pr.prolist.get(1));

        pr.tasklist();
        int size=fd.profiles.size();

        while(i<size)
        {
            for (String value : pr.prolist)
            {
             //   System.out.println(value);
                if(value.contains(fd.profiles.get(i)))
                {
                    piepro=fd.profiles.get(i);
                    System.out.println(piepro);
                    activepro.setText("AUTO-DETECTED: "+piepro);
                    fd.read(piepro+".txt");
                    active=true;
                    System.out.println("found it");
                    box.setvars();
                  }
            }
        i++;
        }
    }

}//END OF CLASS
