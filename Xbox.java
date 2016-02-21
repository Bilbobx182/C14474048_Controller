package root;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;


public class Xbox{

    static Controller controller;
    static  boolean Start;
    static boolean a, b, x, y;
    static  int ac, bc, xc, yc;
    static int height,width;
    static boolean done;
    public static int timer;

    Xbox()
    {
        done=a=b=x=y=false;
        height=600;
        width=height/2;
        timer=0;
    }

    public static void monitor()
    {
        //gui for basic stats like amount of times pressed.
        Stage live = new Stage();
        live.initModality(Modality.APPLICATION_MODAL);

        live.setMinWidth(height/2);
        live.setMaxHeight(height);
        live.setMinHeight(height);
        live.setMaxWidth(height/2);

        Label alab = new Label("A: "+ ac);
        Label blab = new Label("B: "+bc);
        Label xlab = new Label("X: "+xc);
        Label ylab = new Label("Y: "+yc);


        Button finish = new Button("Finish");
        finish.setOnAction(lam ->
                {
                    done=true;
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
            try
            {
                while (done != true)
                {
                    //delay so it polls the controller, so that each time they hit a button it will pick it up, but it wont pick up more than 1 time on a tap of a button
                    //puts the thread to sleep for 250 miliseconds.
                    Thread.sleep(250);
                    timer+=5;
                    controller.poll();
                    a = controller.isButtonPressed(0);
                    b = controller.isButtonPressed(1);
                    x = controller.isButtonPressed(2);
                    y = controller.isButtonPressed(3);
                    System.out.print("A" + ac);
                    System.out.print("B" + bc);
                    System.out.print("X" + xc);
                    System.out.print("Y" + yc);
                  //  System.out.println(timer);
                    System.out.println();
                    if (a)
                    {
                        ac++;
                    }
                    if (b)
                    {
                        bc++;
                    }
                    if (x)
                    {
                        xc++;
                    }
                    if (y)
                    {
                        yc++;
                    }

                    if(timer==5000)
                    {
                        done=true;
                        monitor();
                    }
                }
            }
            catch(InterruptedException ie)
            {
                System.out.println("ERROR SLEEPING THREAD");
            }
        }
    }
