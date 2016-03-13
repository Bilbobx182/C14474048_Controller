package root;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

/**
TESTING CLASS FOR WHEN I IMPLEMENT ANALOGUE STICKS GRAPH
 MAY BE REMOVED LATER ENTIRELY.
 */
public class Analstick {


    double startX,startY,endX,endY,test;

    Analstick()
    {
        startX=startY=endX=endY=0;
        test=5;
    }

    public void analmap(String name)
    {
        Stage amap = new Stage();

        amap.initModality(Modality.APPLICATION_MODAL);
        amap.setTitle(name);

        //make it so the window is big enough to bother people.
        amap.setMaxWidth(600);
        amap.setMaxHeight(600);
        amap.setMinHeight(600);
        amap.setMaxWidth(600);

        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);



        VBox layout = new VBox(10);
        layout.getChildren().addAll(line);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        amap.setScene(scene);
        scene.getStylesheets().add("style.css");
        amap.show();


    }
}
