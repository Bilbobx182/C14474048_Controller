package root;

import java.io.*;
import java.lang.*;
import java.util.*;

public class Fileio
{


    public void write(String profilename,String aval,String bval, String xval,String yval)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(profilename+".txt", "UTF-8");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        writer.println(aval);
        writer.println(bval);
        writer.println(xval);
        writer.println(yval);
        writer.close();
    }

    String line;
    public boolean read(String filename)
    {
        Menu menu = new Menu();
        boolean test;
        int i=0;
        try
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null)
            {

                menu.current[i]=line;
                line =br.readLine();
                System.out.println(menu.current[i]);
            }
            test=true;
            br.close();
        }
        catch(FileNotFoundException ex)
        {
            test=false;
        }
        catch(IOException ex)
        {
            test=false;
        }
        return test;
    }
}