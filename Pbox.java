package root;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Pbox
{
//USED as a Pop-up Box style warning or system for when the user does something wrong or something unexpected happens.
    public void warning(String name, String text)
    {
        Stage pop = new Stage();

        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setTitle(name);

        //make it so the window is big enough to bother people.
        pop.setMaxWidth(600);
        pop.setMaxHeight(600);
        pop.setMinHeight(600);
        pop.setMaxWidth(600);

        Label label = new Label();
        label.setText(text);

        Button close = new Button("I understand");
        close.setOnAction(lam -> pop.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        pop.setScene(scene);
        scene.getStylesheets().add("style.css");
        pop.showAndWait();
    }
}
