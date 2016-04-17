package root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Process
{
    public static String line;
    public static ArrayList<String> prolist = new ArrayList<>();
    String result;

    void tasklist()
    {
        java.lang.Process activepro = null;
        try
        {
            //trys to run and access tasklist.exe
            activepro=Runtime.getRuntime().exec("tasklist.exe");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            //if it is successful it reads in the contents of tasklist
            BufferedReader tlist = new BufferedReader(new InputStreamReader(activepro.getInputStream()));
            while ((line = tlist.readLine()) != null)
            {
                //because there are some lines that are null we don't want to add those to the list.
                if (!line.trim().equals(""))
                {
                    //we then trim the string down removing the excess stuff from it other than the program name
                   result=(line.substring(0, line.indexOf(" ")));
                    if(result.contains(".exe"))
                    {
                        result=result.substring(0,result.indexOf(".exe"));
                        //adds the program without the exe to the arraylist
                        prolist.add(result);
                      //System.out.println(prolist.get(i));
                    }
                }
                else
                {
                    System.out.println("blank task");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
