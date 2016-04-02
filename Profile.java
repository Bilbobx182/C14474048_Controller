package root;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.io.IOException;

public class Profile extends Main
{
    Fileio fd = new Fileio();
    Pbox pb = new Pbox();
    String aval,bval,xval,yval,profilename;
    int height;
    boolean test;
    String ofn;
    boolean start=false;
    String filename;
    int combo,ab1,ab2,ab3,analogue;

    Profile()
    {
        aval=bval=xval=yval="NOTHING";
        height=600;
        ab1=1;
        ab2=3;
        ab3=5;
        analogue=10;
        combo=19;//by default so all graphs will be set
    }

    public void title()
    {
        Stage procreate = new Stage();
        procreate.setTitle("Profile setup");
        procreate.initModality(Modality.APPLICATION_MODAL);

        procreate.setMinWidth(height);
        procreate.setMaxHeight(height/2);
        procreate.setMaxWidth(height);
        Label prolabel = new Label("Name the profile:");

        TextField proname = new TextField();
        proname.setPromptText("Example is Rocket League");

        Button set = new Button("Create the profile!");

        proname.setOnKeyPressed(key ->
        {
            if (key.getCode().equals(KeyCode.ENTER))
            {
                profilename= String.valueOf(proname.getText());
                boolean fine=create();
                if(fine ==false)
                {
                    prolabel.setText("Please put in a title with more than 4 characters");
                }
                else
                {
                    procreate.close();
                }

            }
        });


        set.setOnAction(lam ->
                {
                    profilename= String.valueOf(proname.getText());
                    boolean fine =create();
                    if(fine ==false)
                    {
                        prolabel.setText("Please put in a title with more than 4 characters");
                    }
                    else
                    {
                        procreate.close();
                    }

                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(prolabel,proname,set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        procreate.setScene(scene);
        scene.getStylesheets().add("style.css");
        procreate.showAndWait();
    }

    public void setup(String profilename)
    {
        Stage pop = new Stage();
        pop.setTitle("Profile setup");
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
                    if (afield.getText()==null||afield.getText().trim().isEmpty())
                    {
                        aval="Null";
                    }
                    else
                    {
                        aval = String.valueOf(afield.getText());
                    }

                    if (bfield.getText()==null||bfield.getText().trim().isEmpty())
                    {
                        bval="Null";
                    }
                    else
                    {
                        bval = String.valueOf(bfield.getText());
                    }

                    if (xfield.getText()==null||xfield.getText().trim().isEmpty())
                    {
                        xval="Null";
                    }
                    else
                    {
                        xval = String.valueOf(xfield.getText());
                    }
                    if (yfield.getText()==null||yfield.getText().trim().isEmpty())
                    {
                        yval="Null";
                    }
                    else
                    {
                        yval=String.valueOf(yfield.getText());
                    }
                    fd.write(profilename,aval,bval,xval,yval);
                    pop.close();
                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(abutton, afield, bbutton, bfield, xbutton, xfield, ybutton, yfield, set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene proscene2 = new Scene(layout);
        proscene2.getStylesheets().add("style.css");
        pop.setScene(proscene2);
        pop.showAndWait();
    }

    public void selector()
    {
        Stage select = new Stage();
        select.initModality(Modality.APPLICATION_MODAL);
        select.setTitle("Select your profile");

        select.setMinWidth(height/2);
        select.setMaxHeight(height);
        select.setMinHeight(height);
        select.setMaxWidth(height/2);
        TextField selname=new TextField();
        Label sellab = new Label("Enter in the profile you want to use:");
        selname.setPromptText("Profile name goes here");

        selname.setOnKeyPressed(key ->
        {
            if (key.getCode().equals(KeyCode.ENTER))
            {
                filename= String.valueOf(selname.getText());
                search();
                select.close();
            }
        });

        Button set = new Button("Search for the profile!");
        set.setOnAction(lam ->
                {
                    filename= String.valueOf(selname.getText());
                   search();
                   select.close();
                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(sellab,selname,set);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene proscene = new Scene(layout);
        proscene.getStylesheets().add("style.css");
        select.setScene(proscene);
        select.showAndWait();

    }

    void search()
    {

        System.out.println(filename);
        ofn=filename;
        test = fd.read(filename+".txt");
        if(test!=true)
        {
            pb.warning("ERROR ON SEARCH","Sorry, your search gave me no love :( ");
        }
        else
        {
            pb.warning("Found it!", "I found your profile :) ");
            start=true;
        }
    }

    boolean create()
    {
       boolean fine=true;
        int plen=profilename.length();
        if(plen > 4)
        {
            try
            {
                fd.newappend(profilename);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            setup(profilename);
            return fine;
        }
        else
        {
            fine=false;
            System.out.println(fine);
            return fine;
        }


    }

    void graphlist()
    {
        combo=0;
        Stage gselect = new Stage();
        gselect.initModality(Modality.APPLICATION_MODAL);
        gselect.setTitle("Select the active graphs");

        gselect.setMaxWidth(600);
        gselect.setMaxHeight(600);
        gselect.setMinHeight(600);
        gselect.setMaxWidth(600);

        CheckBox abxy = new CheckBox("ABXY per game piechart");
        CheckBox abxytot = new CheckBox("Total profile ABXY piechart");
        CheckBox abxycombo = new CheckBox("ABXY Combo piechart");
        CheckBox anal = new CheckBox("Analogue stick barchart");

        abxy.setSelected(true);
        abxytot.setSelected(true);
        anal.setSelected(true);
        abxycombo.setSelected(true);


        Button close = new Button("I am done selecting");
        close.setOnAction(lam ->
                {
                    if(abxy.isSelected()==true)
                    {
                        combo=ab1+combo;
                    }
                    if(abxytot.isSelected()==true)
                    {
                        combo=ab2+combo;
                    }
                    if(abxycombo.isSelected()==true)
                    {
                        combo=ab3+combo;
                    }
                    if(anal.isSelected()==true)
                    {
                        combo=analogue+combo;
                    }
                    System.out.println(combo);

                    gselect.close();
                }
        );



        VBox layout = new VBox(20);

        layout.getChildren().addAll(abxy,abxytot,abxycombo,anal,close);
        layout.setAlignment(Pos.CENTER_LEFT);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        gselect.setScene(scene);
        scene.getStylesheets().add("style.css");
        gselect.showAndWait();
    }

}