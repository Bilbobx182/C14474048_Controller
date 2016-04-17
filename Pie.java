package root;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
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

        //sets the stage width an height limits so they cant resieze.
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        //rounds the math values so they aren't  x.yyyyyy%
        a=Math.round(a);
        b=Math.round(b);
        x=Math.round(x);
        y=Math.round(y);


        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                //creates the sections of the piecharts that can exist.
                new PieChart.Data("(A)"+fd.input.get(0),a),
                new PieChart.Data("(B)"+fd.input.get(1),b),
                new PieChart.Data("(X)"+fd.input.get(2),x),
                new PieChart.Data("(Y)"+fd.input.get(3),y));
        //creates the piechart itself
        PieChart chart = new PieChart(data);

        //sets the title,ledgend, and label visibility.
        chart.setTitle(piepro);
        chart.setLabelsVisible(false);
        chart.setLabelLineLength(width/3);
        chart.setLegendSide(Side.TOP);

        //the label for displaying the percentage of the button pressed.
        final Label percendisplay = new Label();
        percendisplay.setTextFill(Color.BLACK);

        //a loop that runs around finding the X and Y of where the user clicked the mouse.
        for (final PieChart.Data d : chart.getData())
        {
            d.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, lam ->
            {
                percendisplay.setTranslateX(lam.getSceneX());
                percendisplay.setTranslateY(lam.getSceneY());
                percendisplay.setText(String.valueOf(d.getPieValue()) + "%");
            });
        }

        ((Group) scene.getRoot()).getChildren().addAll(chart,percendisplay);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void complexrender(String piepro)
    {
        Stage stage = new Stage();
        Scene combographscene= new Scene(new Group());
        double width= combographscene.getWidth();
        stage.setTitle(piepro+" Stats");


        stage.setWidth(500);
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.setMinWidth(500);


        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("(AA)", box.combo[0]),
                new PieChart.Data("(AB)",box.combo[1]),
                new PieChart.Data("(AX)",box.combo[2]),
                new PieChart.Data("(AY)",box.combo[3]),
                new PieChart.Data("(BB)",box.combo[4]),
                new PieChart.Data("(BX)",box.combo[5]),
                new PieChart.Data("(BY)",box.combo[6]),
                new PieChart.Data("(XX)",box.combo[7]),
                new PieChart.Data("(XY)",box.combo[8]),
                new PieChart.Data("(YY)",box.combo[9]));

        PieChart chart = new PieChart(data);

        chart.setTitle(piepro);
        chart.setLabelsVisible(false);


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

        ((Group) combographscene.getRoot()).getChildren().addAll(chart,percendisplay);
        stage.setScene(combographscene);
        stage.showAndWait();
    }
}