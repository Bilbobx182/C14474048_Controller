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

    public void render(String piepro)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        double width= scene.getWidth();
        stage.setTitle(piepro+" Stats");


        stage.setWidth(500);
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        fixvars();

        box.ac=map(box.ac,0,box.total,0,100);
        box.bc=map(box.bc,0,box.total,0,100);
        box.yc=map(box.yc,0,box.total,0,100);
        box.xc=map(box.xc,0,box.total,0,100);

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                        new PieChart.Data("(A)"+fd.input.get(0),box.ac),
                        new PieChart.Data("(B)"+fd.input.get(1),box.bc),
                        new PieChart.Data("(X)"+fd.input.get(2),box.xc),
                        new PieChart.Data("(Y)"+fd.input.get(3),box.yc));
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

    float map(float value, float oldstart, float oldstop, float newstart, float newend)
    {
        float irange = oldstop-oldstart;
        float dis = value-oldstart;
        float percente = dis / irange;
        float outRange = newend-newstart;

        return newstart+(percente*outRange);
    }


        void fixvars()
        {
            box.ac=Math.round(box.ac);
            box.bc=Math.round(box.bc);
            box.xc=Math.round(box.xc);
            box.yc=Math.round(box.yc);
        }

}