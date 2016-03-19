package root;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.util.Random;

public class Main extends Application
{
    Stage mainmenu;
    Scene mm,pm,em;


    boolean auto=false;
    boolean active=false;
    String piepro;
    Label activepro = new Label();
    int minbuttonpress=6;
    int res,max;
    Label randompro = new Label();


    //Classes
    public static Profile pro1 = new Profile();
    public static Xbox box = new Xbox();
    public static Fileio fd = new Fileio();
    public static Process pr = new Process();
    Random gen = new Random();
    Pbox pb = new Pbox();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Fileio fd = new Fileio();
        mainmenu = primaryStage;
        mainmenu.setOnCloseRequest(lam -> mainmenu.close());
        box.setup();
        mainmenu.setTitle("Controller Home Screen");

        //EFFECTS
        InnerShadow shadow = new InnerShadow();
        shadow.setOffsetX(4);
        shadow.setOffsetY(4);
        shadow.setColor(Color.web("0x3b596d"));

        //-------------------------------------------------------MAIN MENU BUTTONS
        Pie pie=new Pie();
        //Quit button
        Button quit = new Button("Quit");
        quit.setOnAction(aquit->
                {
                    mainmenu.close();
                    System.exit(0);
                }
        );

        Button promenu = new Button("Profile Menu");
        promenu.setOnAction(proif ->
                {
                    mainmenu.setScene(pm);
                    mainmenu.setTitle("Controller Profile Screen");
                    max= fd.profiles.size();
                   res = gen.nextInt(max);


                }
        );

        Button etc = new Button("Other options");
        etc.setOnAction(pm ->
                {
                    pro1.graphlist();
                }
        );

        Button b2m = new Button("Back to main menu");
        b2m.setOnAction(pm ->
                {
                    mainmenu.setScene(mm);
                    mainmenu.setTitle("Controller Home Screen");
                    res = gen.nextInt(max);
                    randompro.setText(fd.profiles.get(res)+'\r' +"Is a profile you have");
                }
        );

        //START
        Analstick as=new Analstick();
        Button begin = new Button("Start");
        begin.setEffect(new Glow(0.25));
        begin.setMaxWidth(900000000);
        begin.setMaxHeight(50);
        begin.setOnAction(prof ->
                {
                    Gameopen();

                    if (active == true || pro1.start==true)
                    {
                        box.setvars();
                        box.timer();
                        box.statepoll();
                        box.polling();
                        box.varprep();


                        if(box.total > minbuttonpress)
                        {

                            if (auto == true)
                            {
                               fd.writestats(piepro,box.ac,box.bc,box.xc,box.yc);
                                pie.render(piepro,box.ac,box.bc,box.xc,box.yc);
                                as.analmap(piepro);
                            } else
                            {
                              fd.writestats(pro1.ofn,box.ac,box.bc,box.xc,box.yc);
                                pie.render(pro1.ofn,box.ac,box.bc,box.xc,box.yc);
                                as.analmap(pro1.ofn);
                            }
                            box.setvars();
                        }
                        else
                        {
                            pb.warning("BUTTON ERROR","Please press the buttons more for better graphs :( ");
                            box.total=minbuttonpress+1;
                            box.setvars();
                        }
                    }
                    else
                    {
                        activepro.setText("NO ACTIVE PROFILE");
                    }
                }
        );

        //-------------------------------------------------------PROFILE MENU BUTTONS
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
                fd.inputgetter();
                activepro.setText("Active profile:"+keeper);
                active=true;
            }
            else
            {
                activepro.setText("NO ACTIVE PROFILE");
            }

        });


        fd.wrpcheck();
        max= fd.profiles.size();
        res = gen.nextInt(max);
        randompro.setText(fd.profiles.get(res)+'\r' +"Is a profile you have");

        //-----------------------------------------------------------ETC MENU BUTTONS
        StackPane mmlayout = new StackPane();
        mmlayout.getChildren().addAll(begin,promenu,etc, quit,activepro);
        mm = new Scene(mmlayout, 600, 600);

        StackPane pmlayout = new StackPane();
        pm = new Scene(pmlayout, 600, 600);
        pmlayout.getChildren().addAll(select,profile,b2m,randompro);


        //------------------------------------------------------MAIN MENU ALLIGNMENTS
        StackPane.setAlignment(begin, Pos.TOP_CENTER);
        StackPane.setAlignment(activepro,Pos.CENTER);
        StackPane.setAlignment(profile, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(select, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(quit, Pos.BOTTOM_RIGHT);

        //------------------------------------------------------PROFILE ALLIGNMENTS
        StackPane.setAlignment(promenu, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(etc, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(randompro, Pos.CENTER);
        StackPane.setAlignment(b2m, Pos.BOTTOM_RIGHT);


        mm.getStylesheets().add("style.css");
        pm.getStylesheets().add("style.css");

        mainmenu.setScene(mm);
        mainmenu.show();
    }


    void Gameopen()
    {
        int i=0;
        //Checking to see if a game that the user set a profile for is created.
        fd.wrpcheck();
        pr.tasklist();

        int size=fd.profiles.size();

        while(i<size)
        {
            for (String value : pr.prolist)
            {
                if(value.contains(fd.profiles.get(i)))
                {
                    //sets the string piepro to the profile found
                    piepro=fd.profiles.get(i);
                    //adds a small glowing effect to it
                    activepro.setEffect(new Glow(0.3));
                    activepro.setText("AUTO-DETECTED: "+piepro);

                    fd.read(piepro+".txt");
                    active=true;//sets the auto detect to true.
                    System.out.println("found it");
                    box.setvars();
                    auto=true;
                    pr.prolist.clear();
                    break;
                }
                else
                {
                    //setting the value to false so it never gets confused.
                    active=false;
                }
            }
            i++;
        }
    }

}//END OF CLASS