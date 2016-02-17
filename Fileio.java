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
}

