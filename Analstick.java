package root;


import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Analstick extends Main
{

    double startX,startY,endX,endY,test;
    String GE1 = "Left";
    String GE2 = "Right";
    String GE3 = "Up";
    String GE4 = "Down";

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

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> analchart = new BarChart<>(xAxis, yAxis);
        analchart.setId("ACHART");

        analchart.setTitle(name);
        xAxis.setLabel("Stick name");
        yAxis.setLabel("Times Moved");

        XYChart.Series LEFT = new XYChart.Series();
        LEFT.setName("Left Stick");
        LEFT.getData().add(new XYChart.Data(GE1, box.lal));
        LEFT.getData().add(new XYChart.Data(GE2, box.lar));
        LEFT.getData().add(new XYChart.Data(GE3, box.lau));
        LEFT.getData().add(new XYChart.Data(GE4, box.lad));

        XYChart.Series RIGHT = new XYChart.Series();
        RIGHT.setName("Right Stick");
        RIGHT.getData().add(new XYChart.Data(GE1, box.ral));
        RIGHT.getData().add(new XYChart.Data(GE2, box.rar));
        RIGHT.getData().add(new XYChart.Data(GE3, box.rau));
        RIGHT.getData().add(new XYChart.Data(GE4, box.rad));

        Scene scene  = new Scene(analchart,600,600);
        analchart.getData().addAll(LEFT,RIGHT);
        amap.setScene(scene);
        amap.show();
    }
}
