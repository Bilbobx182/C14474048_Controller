package root;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class Xbox{

    static Controller controller;
    static boolean a, b, x, y,back,rb,lb; //booleans to see if it's pressed at all.
    static boolean abo, bbo, xbo, ybo; //booleans for the counters so they go up by one.
    static float ac, bc, xc, yc;    //counters for buttons
    static float lal,lar,lau,lad,ral,rar,rad,rau;//counters for Left/Right Analogue Up/Down/Left/Right
    static boolean lalb,larb,laub,ladb,ralb,rarb,radb,raub;
    static int height,width,total;
    static boolean done;
    static long time;
    static long ctime;

    Xbox()
    {
        done=a=b=x=y=false;
        height=600;
        width=height/2;
        lal=lar=lau=lad=ral=rar=rad=rau=time=0;
        total=0;
        lau=lal=-1; //because for the first time it counts as 2 for some reason.

    }

    public static void setvars()
    {
        ac=bc=xc=yc=0;
        done=false;
        total=0;
    }

    public static void monitor()
    {

        //gui for basic stats like amount of times pressed.
        Stage live = new Stage();
        live.initModality(Modality.APPLICATION_MODAL);
        live.setTitle("Timer setup");

        live.setMinWidth(height/2);
        live.setMaxHeight(height);
        live.setMinHeight(height);
        live.setMaxWidth(height/2);

        Label alab = new Label("A: "+ac);
        Label blab = new Label("B: "+bc);
        Label xlab = new Label("X: "+xc);
        Label ylab = new Label("Y: "+yc);


        Button finish = new Button("Finish");
        finish.setOnAction(lam ->
                {
                    done=true;
                    live.close();
                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(alab,blab,xlab,ylab,finish);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);

        live.setScene(scene);
        scene.getStylesheets().add("style.css");
        live.showAndWait();
    }


    public static void setup()
    {
        try//Attempts to create an instance for the xbox controller
        {
            Controllers.create();
        }
        catch (LWJGLException e)//deals with problems if problems are there.
        {
            e.printStackTrace();
        }
        //setting it to the XBOX since I know xbox is controller 0
        controller = Controllers.getController(0);
        for (int i = 0; i < controller.getAxisCount(); i++)
        {
            //  System.out.println(controller.getAxisName(i));
            controller.setDeadZone(i, (float) 0.4);
        }
    }
    public static void polling()
    {
        while (done != true)
        {
            System.out.println(lau+lal+ral+rau);
            controller.poll();
            a = controller.isButtonPressed(0);
            b = controller.isButtonPressed(1);
            x = controller.isButtonPressed(2);
            y = controller.isButtonPressed(3);
            rb=controller.isButtonPressed(4);
            lb=controller.isButtonPressed(5);
            back=controller.isButtonPressed(6);


            //checking the state and which button is active.
        //    System.out.println();
            if(a)
            {
                abo=true;
            }
            if(b)
            {
                bbo=true;
            }
            if(x)
            {
                xbo=true;
            }
            if(y)
            {
                ybo=true;
            }
            if(rb)
            {
             //   rbo=true;
            }
            if(lb)
            {
       //       lbo=true;
            }

            if(controller.getAxisValue(1)>.70)
            {
                larb=true;
            }

            if(controller.getAxisValue(1)<=-0.70)
            {
                lalb=true;
            }
            if(controller.getAxisValue(0)>.70)
            {
                ladb=true;
            }

            if(controller.getAxisValue(0)<=-0.70)
            {
                laub=true;
            }
            // RIGHT ANALOGUE EVENTS
            if(controller.getAxisValue(3)>.70)
            {
                rarb=true;
            }

            if(controller.getAxisValue(3)<=-0.70)
            {
                ralb=true;
            }
            if(controller.getAxisValue(2)>.70)
            {
                radb=true;
            }

            if(controller.getAxisValue(2)<=-0.70)
            {
                raub=true;
            }

            //incrementing for each time Controller goes.
            //Left Analogue RIGHT
            if(controller.getAxisValue(1) <.70 && larb==true)
             {
                larb=false;
                 lar++;
            }

            //Left Analogue LEFT
            if(controller.getAxisValue(1)>=-.70 && lalb==true)
            {
                lalb=false;
                lal++;
            }
            //Left Analogue DOWN
            if(controller.getAxisValue(0) <.70 && ladb==true)
            {
                ladb=false;
                lad++;
            }
            //Left Analogue UP
            if(controller.getAxisValue(0)>=-.70 && laub==true)
            {
                laub=false;
                lau++;
            }

            // RIGHT ANALOGUE COUNTERS
            //Left Analogue RIGHT
            if(controller.getAxisValue(3) <.70 && rarb==true)
            {
                rarb=false;
                rar++;
            }

            //Left Analogue LEFT
            if(controller.getAxisValue(3)>=-.70 && ralb==true)
            {
                ralb=false;
                ral++;
            }
            //Left Analogue DOWN
            if(controller.getAxisValue(2) <.70 && radb==true)
            {
                radb=false;
                rad++;
            }
            //Left Analogue UP
            if(controller.getAxisValue(2)>=-.70 && raub==true)
            {
                raub=false;
                rau++;
            }

            //incrementing for each time a button is pressed.
            if(abo==true &&a==false)
            {
                abo=false;
                ac++;
                total++;
            }
            if(bbo==true &&b ==false)
            {
                bbo=false;
                bc++;
                total++;
            }

            if(xbo==true &&x ==false)
            {
                xbo=false;
                xc++;
                total++;
            }
            if(ybo==true && y ==false)
            {
                ybo=false;
                yc++;
                total++;
            }

          //  System.out.println(ac);
            if(back)
            {
                done=true;
            }

            //gets the current time for checking later.
            ctime = System.currentTimeMillis();

            //checking to see if the current time is the  same as the time end time
            if(ctime>=tstart)
            {
                done=true;
            }
        }
    }

    static long tstart;
    public static void timer()
    {
        Stage entertime = new Stage();
        entertime.initModality(Modality.APPLICATION_MODAL);

        entertime.setMinWidth(height/2);
        entertime.setMaxHeight(height);
        entertime.setMinHeight(height);
        entertime.setMaxWidth(height/2);

        Label ta= new Label("Timer amount in minutes:");
        TextField timefiled = new TextField();
        timefiled.setPromptText("5");

        Button set = new Button("Start timer");
        set.setOnAction(lam ->
                {
                    if (timefiled.getText()==null||timefiled.getText().trim().isEmpty())
                    {
                        time=999999999;
                        time = time * 60000;
                        tstart = System.currentTimeMillis();
                        tstart = time + tstart;
                        entertime.close();
                    }
                    else
                    {
                        time = Integer.valueOf(timefiled.getText());
                        //get the current system time they start the box
                        time = time * 60000; //make it into MS for the timer

                        //tstart now has the value of the system time when it all should end.
                        tstart = System.currentTimeMillis();
                        tstart = time + tstart;
                        entertime.close();
                    }
                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(ta,timefiled,set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        entertime.setScene(scene);

        entertime.showAndWait();
    }

    /* EXCESS CODE THAT MAY BE USEFUL
                System.out.print("A1 UP DOWN "+ controller.getAxisValue(0));
            System.out.print("A1 LEFT RIGHT "+ controller.getAxisValue(1));
            System.out.print("A2 UP DOWN "+ controller.getAxisValue(2));
            System.out.print("A2 LEFT RIGHT "+ controller.getAxisValue(3));
            System.out.println();

           // System.out.println("LAL"+lal+"LAR"+lar);
           // System.out.println("LAU"+lau+"LAD"+lad);
     */

}