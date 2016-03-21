package root;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class Pie extends Main {

    public void render(String piepro,float a,float b, float x, float y)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        double width= scene.getWidth();
        stage.setTitle(piepro+" Stats");

        System.out.println(a);

        stage.setWidth(500);
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.setMinWidth(500);

        a=Math.round(a);
        b=Math.round(b);
        x=Math.round(x);
        y=Math.round(y);

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                        new PieChart.Data("(A)"+fd.input.get(0),a),
                        new PieChart.Data("(B)"+fd.input.get(1),b),
                        new PieChart.Data("(X)"+fd.input.get(2),x),
                        new PieChart.Data("(Y)"+fd.input.get(3),y));
         PieChart chart = new PieChart(data);
        chart.setTitle(piepro);


        //the label for displaying the percentage of the button pressed.
        final Label percendisplay = new Label();
        percendisplay.setTextFill(Color.BLACK);

        for (final PieChart.Data d : chart.getData())
        {
            d.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, lam ->
            {
                percendisplay.setTranslateX(lam.getSceneX());
                percendisplay.setTranslateY(lam.getSceneY());
                percendisplay.setText(String.valueOf(d.getPieValue()) + "%");
            });
        }

        chart.setLabelLineLength(width/3);
        chart.setLegendSide(Side.TOP);

        ((Group) scene.getRoot()).getChildren().addAll(chart,percendisplay);
        stage.setScene(scene);
        stage.showAndWait();
    }
}