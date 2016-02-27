package root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Process
{
    public static String line;
    public static ArrayList<String> prolist = new ArrayList<>();


    void tasklist()
    {
        java.lang.Process activepro = null;
        try
        {
            activepro = Runtime.getRuntime().exec("tasklist.exe");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        BufferedReader tlist = new BufferedReader(new InputStreamReader(activepro.getInputStream()));
        try
        {
            int i=0;
            while ((line = tlist.readLine()) != null)
            {
                if (!line.trim().equals(""))
                {
                    prolist.add(line.substring(0, line.indexOf(" ")));
                    String result=prolist.get(i);
                    if(result.contains(".exe"))
                    {
                        //removes the exe
                        result=result.substring(0,result.indexOf(".exe"));
                        //deletes what originally was in prolist last
                        prolist.remove(prolist.size() - 1);
                        //adds the program without the exe to the arraylist
                        prolist.add(result);
                      //System.out.println(prolist.get(i));
                    }
                    i++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
