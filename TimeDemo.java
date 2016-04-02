package root;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Ciaran on 31/03/2016.
 */
public class TimeDemo extends Application
{

    Stage entertime = new Stage();


    long time;
    long tstart,tend;
    double temptimer;
    boolean timedone;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        entertime.setTitle("Select the time");

        entertime.setMinWidth(600);
        entertime.setMaxHeight(400);
        entertime.setMinHeight(400);
        entertime.setMaxWidth(1920);

        int majorline,smallline,MAXMIN,MINMIN;
        majorline=15;
        MAXMIN=60;
        MINMIN=0;
        smallline=1;


        Label timelabel= new Label("Select how long you want:");

        Slider timescale = new Slider();

        //effects that will be on the timeline
        timescale.setEffect(new Glow(0.9));

        timescale.setShowTickLabels(true);
        timescale.setShowTickMarks(true);

        //Range of the timeline.
        timescale.setMin(MINMIN);
        timescale.setMax(MAXMIN);
        //default value of where the marker will be if they only hit the button
        timescale.setValue(1);
        //Dealing the the numbers we're going to display on the timeline.
        timescale.setMajorTickUnit(majorline);
        timescale.setMinorTickCount(smallline);

        Button set = new Button("Start timer");
        set.setOnAction(lam ->
                {
                    timedone=false;

                    temptimer=(timescale.getValue());

                    time=(long)temptimer;
                    System.out.println(time);

                    time = time * 60000;

                    tstart = System.currentTimeMillis();

                    System.out.println(tstart);
                    tend = time + tstart;
                    checktime();
                    System.out.println("Done");
                    entertime.close();
                }
        );

        VBox layout = new VBox(100);
        layout.getChildren().addAll(timelabel,timescale,set);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

       scene.getStylesheets().add("style.css");
        entertime.setScene(scene);

        entertime.showAndWait();
    }

    void checktime()
    {
        while(timedone != true)
        {
            if(System.currentTimeMillis()>=tend)
            {
                System.out.println("The time has passed");
                timedone=true;
            }
        }
    }

}
