package root;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import javafx.scene.control.Slider;

import java.util.ArrayList;


public class Xbox{

    static Controller controller;
    static boolean a, b, x, y,back,rb,lb; //booleans to see if it's pressed at all.
    static boolean abo, bbo, xbo, ybo; //booleans for the counters so they go up by one.
    static float ac, bc, xc, yc;    //counters for buttons
    static float lal,lar,lau,lad,ral,rar,rad,rau;//counters for Left/Right Analogue Up/Down/Left/Right
    static float aa,ab,ax,ay,bb,bx,by,xx,xy,yy; //variables for combo buttons
    static int buttoncounter;
    static boolean lalb,larb,laub,ladb,ralb,rarb,radb,raub;
    static long timebetween,buttonms,minutecounterstart,minutecounterstop;
    static boolean minute;


    static int height,width,total;
    static boolean done;
    static long time,ctime;
    static long buttime1,buttime2;//for when the buttons are pressed.
    static double temptimer;
    static ArrayList<Integer> buttonvalues = new ArrayList<>();

    Xbox()
    {
        done=a=b=x=y=false;
        height=600;
        width=height/2;
        lal=lar=lau=lad=ral=rar=rad=rau=time=0;
        temptimer=total=0;
        buttoncounter=0;
        buttonms=550;//half a second to press a combo.
        lau=lal=0; //because for the first time it counts as 2 for some reason.
        minute=true;
    }

    public static void setvars()
    {
        ac=bc=xc=yc=0;
        done=false;
        buttoncounter=total=0;
        lal=lar=lau=lad=ral=rar=rad=rau=0;
        aa=ab=ax=ay=bb=bx=by=xx=xy=yy=0;//setting the combo counters to 0
          /*
        A==100
        B==1
        X==2
        Y==5

        We have no repeating values using these arbitrary  numbers
         */
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

            controller.poll();
            a = controller.isButtonPressed(0);
            b = controller.isButtonPressed(1);
            x = controller.isButtonPressed(2);
            y = controller.isButtonPressed(3);
            rb=controller.isButtonPressed(4);
            lb=controller.isButtonPressed(5);
            back=controller.isButtonPressed(6);

            if(buttoncounter>1)
            {
                buttoncounter=-1;
            }
            
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

                buttoncounter++;
                buttontime(100);
            }
            if(bbo==true &&b ==false)
            {
                bbo=false;
                bc++;
                total++;

                buttoncounter++;
                buttontime(1);
            }

            if(xbo==true &&x ==false)
            {
                xbo=false;
                xc++;
                total++;

                buttoncounter++;
                buttontime(2);
            }
            if(ybo==true && y ==false)
            {
                ybo=false;
                yc++;
                total++;

                buttoncounter++;
                buttontime(5);
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

    public static void buttonadder()
    {

            int combo = buttonvalues.get(0) + buttonvalues.get(1);
            switch (combo)
            {
                //A VALUE COMBOS
                case 200:
                    aa++;
                    System.out.println(aa);
                    break;
                case 101:
                    ab++;
                    System.out.println("AB"+ab);
                    break;
                case 102:
                    ax++;

                    break;
                case 105:
                    ay++;
                    break;

                //B value combos
                case 2:
                    bb++;
                    System.out.println("BB"+bb);
                    break;
                case 3:
                    bx++;
                    break;
                case 6:
                    by++;
                    break;

                //X VALUE COMBOS
                case 4:
                    xx++;
                    break;
                case 7:
                    xy++;
                    break;

                //Y VALUE COMBOS
                case 10:
                    yy++;
                    break;
            }
    }


    public static void buttontime(int number)
    {
        //Still buggy doesn't work 100% yet, it's about 85% accurate.
        if(buttoncounter==0)
        {
            //gets the first buttons time it was pressed and adds the value corresponding to it to the arraylist.
            buttime1=System.currentTimeMillis();
            buttonvalues.add(number);
        }
        if(buttoncounter==1)
        {
            //gets the second buttons time it was pressed and adds the value corresponding to it to the arraylist.
            buttime2=System.currentTimeMillis();
            buttonvalues.add(number);
            //calculates the timebetween the button presses. If it's less than the number it will then go into the buttonadder function which sees what combo it is and increments
            timebetween=buttime2-buttime1;
            if(timebetween < buttonms)
            {
                buttonadder();
                //resets the button count to -1 so when they press a button again for the first time it will be 0.
                buttoncounter=-1;
                //puts the timebetween to something high, precaution meassure.
                timebetween=buttonms*2;
                buttonvalues.clear();//clears the button combo.
            }
           else
            {
                buttonvalues.clear();
                buttoncounter=-1;
                timebetween=buttonms*2;
                //Sets the values to something really high so it won't go into buttonadder as a precautionary thing.
                buttime1=buttime2=buttonms*20;
            }
            buttonvalues.clear();
        }
    }
    static long tstart;
    public static void timer()
    {
        Slider Timelen = new Slider();
        Stage entertime = new Stage();
        entertime.setTitle("Select the time");
        entertime.initModality(Modality.APPLICATION_MODAL);

        entertime.setMinWidth(height);
        entertime.setMaxHeight(height*2);
        entertime.setMinHeight(height);
        entertime.setMaxWidth(height*2);

        int major,minor,increment,MAXMIN,MINMIN;
        major=15;
        MAXMIN=60;
        MINMIN=0;
        increment=5;
        minor=major%increment;

        if(minor==0)
        {
            minor=1;
        }

        Label timelable= new Label("Select how long you want:");
        Timelen.setMin(MINMIN);
        Timelen.setEffect(new Glow(0.4));
        Timelen.setMax(MAXMIN);
        Timelen.setValue(20);
        Timelen.setShowTickLabels(true);
        Timelen.setSnapToTicks(true);
        Timelen.setShowTickMarks(true);
        Timelen.setMajorTickUnit(major);
        Timelen.setBlockIncrement(increment);
        Timelen.setMinorTickCount(minor);


        Button set = new Button("Start timer");
        set.setEffect(new Glow(0.1));
        set.setOnAction(lam ->
                {
                    temptimer=(Timelen.getValue());
                    time=(long)temptimer;
                    System.out.println(time);
                    //get the current system time they start the box
                    time = time * 60000; //make it into MS for the timer
                    //tstart now has the value of the system time when it all should end.
                    tstart = System.currentTimeMillis();
                    tstart = time + tstart;
                    entertime.close();
                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(timelable,Timelen,set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        entertime.setScene(scene);

        entertime.showAndWait();
    }

    void statepoll()
    {
        ///checks the state to make sure the anal sticks are fine if not sets the values to 0.
        controller.poll();
        if(lau!=0) {lau=0;}
        if(lal!=0) {lal=0;}
        if(rau!=0) {rau=0;}
        if(ral!=0) {ral=0;}

    }

    public static void varprep(float a,float b,float x,float y)
    {
        ac=map(ac,0,total,0,100);
        bc=map(bc,0,total,0,100);
        yc=map(yc,0,total,0,100);
        xc=map(xc,0,total,0,100);
    }

   static float map(float value, float oldstart, float oldstop, float newstart, float newend)
    {
        float irange = oldstop-oldstart;
        float dis = value-oldstart;
        float percente = dis / irange;
        float outRange = newend-newstart;

        return newstart+(percente*outRange);
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