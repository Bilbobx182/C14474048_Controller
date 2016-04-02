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
            activepro=Runtime.getRuntime().exec("tasklist.exe");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            BufferedReader tlist = new BufferedReader(new InputStreamReader(activepro.getInputStream()));

            while ((line = tlist.readLine()) != null)
            {
                if (!line.trim().equals(""))
                {
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
