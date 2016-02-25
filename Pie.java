package root;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class Pie extends Menu {

    public void render()
    {
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle(pro1.ofn + "Stats");

        stage.setWidth(500);
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.setMinWidth(500);

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                        new PieChart.Data("(A)  "+fd.input.get(0), box.ac),
                        new PieChart.Data("(B)  "+fd.input.get(1), box.bc),
                        new PieChart.Data("(X)  "+fd.input.get(2), box.xc),
                        new PieChart.Data("(Y)  "+fd.input.get(3), box.yc));
         PieChart chart = new PieChart(data);
        chart.setTitle( pro1.ofn );

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.showAndWait();
    }
}