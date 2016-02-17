package root;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.util.ArrayList;

public class Profile
{
    Fileio fd = new Fileio();
    Pbox pb = new Pbox();
    String aval,bval,xval,yval,profilename;
    ArrayList<String> list = new ArrayList<String>();
    int height;

    Profile()
    {
        aval=bval=xval=yval="NOTHING";
        height=600;
    }


    public void title()
    {
        Stage procreate = new Stage();
        procreate.initModality(Modality.APPLICATION_MODAL);

        procreate.setMinWidth(height/2);
        procreate.setMaxHeight(height);
        procreate.setMinHeight(height);
        procreate.setMaxWidth(height/2);

        Label prolabel = new Label("Name the profile:");
        TextField proname = new TextField();
        proname.setPromptText("What you want the B button to be");


        Button set = new Button("Create the profile!");
        set.setOnAction(lam ->
        {
            profilename= String.valueOf(proname.getText());
            System.out.println(profilename);
            setup(profilename);
            procreate.close();
        }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(prolabel,proname,set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        procreate.setScene(scene);
        procreate.showAndWait();

    }

    public void setup(String profilename) {
        // ArrayList<String> binds = new ArrayList<String>();
        Stage pop = new Stage();
        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setTitle("Set values for "+profilename);
        //make it so the window is big enough to bother people.
        pop.setMinWidth((height-height/3));
        pop.setMaxHeight(height);
        pop.setMinHeight((height-height/3));
        pop.setMaxWidth(height);

        //A BUTTON
        Label abutton = new Label("A button:");
        TextField afield = new TextField();
        afield.setPromptText("What you want the A button to be");

        //B button
        Label bbutton = new Label("B button:");
        TextField bfield = new TextField();
        bfield.setPromptText("What you want the B button to be");

        //X button
        Label xbutton = new Label("X button:");
        TextField xfield = new TextField();
        xfield.setPromptText("What you want the X button to be");

        //
        Label ybutton = new Label("Y button:");
        TextField yfield = new TextField();
        yfield.setPromptText("What you want the Y button to be");

        Button set = new Button("Set the variables");
        set.setOnAction(lam ->
                {
                    System.out.println("VARIABLES SET");
                    aval = String.valueOf(afield.getText());
                    bval = String.valueOf(bfield.getText());
                    xval = String.valueOf(xfield.getText());
                    yval = String.valueOf(yfield.getText());
                    fd.write(profilename,aval,bval,xval,yval);
                    pop.close();
                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(abutton, afield, bbutton, bfield, xbutton, xfield, ybutton, yfield, set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        pop.setScene(scene);
        pop.showAndWait();
    }

    public void selector()
    {
        Stage select = new Stage();
        select.initModality(Modality.APPLICATION_MODAL);

        select.setMinWidth(height/2);
        select.setMaxHeight(height);
        select.setMinHeight(height);
        select.setMaxWidth(height/2);

        Label sellab = new Label("Enter in the profile you named:");
        TextField selname = new TextField();
        selname.setPromptText("Profile name goes here");


        Button set = new Button("Search for the profile!");
        set.setOnAction(lam ->
                {
                    String filename= String.valueOf(selname.getText());
                    System.out.println(filename);
                    boolean test = fd.read(filename+".txt");
                    if(test!=true)
                    {
                        pb.warning("ERROR ON SEARCH","Sorry, your search gave me no love :( ");
                    }
                    else
                    {
                        pb.warning("Found it!", "I found your profile :) ");
                    }
                    select.close();

                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(selname,sellab,set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        select.setScene(scene);
        select.showAndWait();

    }
}