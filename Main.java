package root;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.util.Duration;

import java.util.Random;

import static java.lang.Math.random;

public class Main extends Application
{
    Stage mainmenu;
    Scene mm,pm,em;


    boolean auto=false;
    boolean active=false;
    String autoprofile;
    Label activepro = new Label();
    int minbuttonpress=6;
    int res,max;
    Label randompro = new Label();
    float tota,totb,totx,toty;
    boolean OS=false;

    String overallprofile;

    //Classes
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
        OStest();

        Fileio fd = new Fileio();
        Profile pro1 = new Profile();

        //SOUNDS
        String filelocation = getClass().getResource("sound.wav").toString(); //Path to the soundfile
       // System.out.println(filelocation);
        AudioClip timedone = new AudioClip(filelocation);

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
                    mainmenu.setTitle("Controller Misc options");
                    mainmenu.setScene(em);
                }
        );

        Button selector = new Button("Graph Selector");
        selector.setOnAction(pm ->
                {
                    pro1.graphlist();
                }
        );

        Button about = new Button("About me");
        about.setOnAction(pm ->
                {
                    pb.warning("About the program/Dev","Hi this program was written by Ciaran Ó Nuallain. \n" + "I did this for my 2nd year end of year assignment. \n" + "\n" + "A hidden tip is: Hitting \"select\" will stop the timer");
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
        Button b2m2 = new Button("Back to main menu");
        b2m2.setOnAction(pm ->
                {
                    mainmenu.setScene(mm);
                    mainmenu.setTitle("Controller Home Screen");
                    res = gen.nextInt(max);
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
            if(OS==true)
            {
                Gameopen();
            }

             if(pro1.start==true)
            {
                overallprofile=pro1.ofn;
            }

            if (active == true || pro1.start == true)
            {

                box.setvars();
                box.timer();
                box.statepoll();
                box.polling();
                timedone.play(1.0);
                prep();


                if(box.total > minbuttonpress)
                {
                    switch (pro1.combo)
                    {
                        case 1:
                                pie.render(overallprofile, box.ac, box.bc, box.xc, box.yc);
                            break;

                        case 3:
                                pie.render(overallprofile, tota, totb, totx, toty);
                            break;

                        case 4:
                                pie.render(overallprofile, box.ac, box.bc, box.xc, box.yc);
                                pie.render(overallprofile, tota, totb, totx, toty);
                            break;

                        case 5:
                                pie.complexrender(overallprofile+"Combo");
                            break;

                        case 6:
                                pie.complexrender(overallprofile+"Combo");
                                pie.render(overallprofile, box.ac, box.bc, box.xc, box.yc);
                            break;

                        case 8:
                                pie.complexrender(overallprofile+"Combo");
                                pie.render(overallprofile, tota, totb, totx, toty);
                            break;

                        case 10:
                                as.analmap(overallprofile);
                            break;

                        case 9:
                                pie.complexrender(overallprofile+"Combo");
                                pie.render(overallprofile, box.ac, box.bc, box.xc, box.yc);
                                pie.render(overallprofile+"Total", tota, totb, totx, toty);
                            break;

                        case 11:

                                as.analmap(overallprofile);
                                pie.render(overallprofile, box.ac, box.bc, box.xc, box.yc);
                            break;

                        case 13:
                                as.analmap(overallprofile);
                                pie.render(overallprofile+"Total", tota, totb, totx, toty);
                            break;

                        case 14:
                                as.analmap(overallprofile);
                                pie.render(overallprofile+"Total", tota, totb, totx, toty);
                                pie.render(overallprofile, box.ac, box.bc, box.xc, box.yc);
                            break;

                        case 15:
                                as.analmap(overallprofile);
                                pie.complexrender(overallprofile+"Combo");
                                pie.render(overallprofile+"Total", tota, totb, totx, toty);
                            break;

                        case 18:
                                as.analmap(overallprofile);
                                pie.complexrender(overallprofile+"Combo");
                            break;
                        case 19:

                                as.analmap(overallprofile);
                                pie.complexrender(overallprofile+"Combo");
                                pie.render(overallprofile, box.ac, box.bc, box.xc, box.yc);
                                pie.render(overallprofile+"Total", tota, totb, totx, toty);

                            break;
                    }
                }
                else
                {
                    pb.warning("BUTTON ERROR","Please press the buttons more for better graphs :( ");
                    box.total=minbuttonpress+1;
                    box.setvars();
                }
            }
        });

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
           // String keeper=pro1.ofn;

            if(pro1.test==true)
            {
                fd.inputgetter();
                activepro.setText("Active profile:"+pro1.ofn);
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

       //BACKGROUND FIREFLY VISUALS

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        Group fireflies = new Group();
        for (int i = 0; i <182; i++)
        {
            //Gives the circle a random radius, and sets it to a golden colour.
            Circle firefly = new Circle(Math.random()*2.5, Color.web("#383838", 0.15));
            //colouring the stroke and saying how big it is.
            firefly.setStroke(Color.web("gold", 0.25));
            firefly.setStrokeWidth(1.5);
            //adding it to the colective.
            fireflies.getChildren().add(firefly);
        }

        fireflies.setEffect(new BoxBlur(15,15,15));
        fireflies.setEffect(new Glow(random()*random()*25));

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        for (Node circle:fireflies.getChildren())
        {
            timeline.getKeyFrames().addAll(
                    // set start position at 0
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(circle.translateXProperty(), random()*screen.getWidth()),
                            new KeyValue(circle.translateYProperty(), random()*screen.getWidth())),
                    //Setting the end point
                    new KeyFrame(new Duration(30000),
                            new KeyValue(circle.translateXProperty(), random()*screen.getWidth()),
                            new KeyValue(circle.translateYProperty(), random()*screen.getWidth()))
            );
        }

        timeline.play();

        StackPane mmlayout = new StackPane();

        StackPane.setAlignment(begin, Pos.TOP_CENTER);
        StackPane.setAlignment(activepro,Pos.CENTER);
        StackPane.setAlignment(etc,Pos.BOTTOM_CENTER);
        StackPane.setAlignment(select, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(quit, Pos.BOTTOM_RIGHT);

        mmlayout.getChildren().addAll(fireflies,begin,promenu,etc, quit,activepro);
        mm = new Scene(mmlayout, 600, 600);
        StackPane emlayout = new StackPane();

        StackPane.setAlignment(selector, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(about, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(b2m2, Pos.BOTTOM_RIGHT);
        emlayout.getChildren().addAll(selector,about,b2m2);
        em = new Scene(emlayout, 600, 600);

        StackPane pmlayout = new StackPane();

        StackPane.setAlignment(promenu, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(profile, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(randompro, Pos.CENTER);
        StackPane.setAlignment(b2m, Pos.BOTTOM_RIGHT);
        pmlayout.getChildren().addAll(select,profile,b2m,randompro);

        pm = new Scene(pmlayout, 600, 600);

        mm.getStylesheets().add("style.css");
        pm.getStylesheets().add("style.css");
        em.getStylesheets().add("style.css");

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
                    autoprofile=fd.profiles.get(i);
                    //adds a small glowing effect to it
                    activepro.setEffect(new Glow(0.3));
                    activepro.setText("AUTO-DETECTED: "+autoprofile);
                    overallprofile=autoprofile;

                    fd.read(autoprofile+".txt");
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

    void prep()
    {
        //updating the permanent variables before graphing
        fd.writestats(autoprofile,box.ac,box.bc,box.xc,box.yc);

        box.varprep();// maps the xbox vars
        totprep(); //maps the total vars
    }

    void totprep()
    {
        float total=fd.a+fd.b+fd.x+fd.y;
        tota=box.map(fd.a,0,total,0,100);
        totb=box.map(fd.b,0,total,0,100);
        totx=box.map(fd.x,0,total,0,100);
        toty=box.map(fd.y,0,total,0,100);
    }

    void OStest()
    {
        String Operating=System.getProperty("os.name");
        OS=Operating.toLowerCase().contains("WINDOWS".toLowerCase());
    }
}//END OF CLASS