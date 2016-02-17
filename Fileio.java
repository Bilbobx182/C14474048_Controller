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
        boolean test;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(filename);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            test=true;
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename);
            test=false;
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" +filename);

          //   ex.printStackTrace();
            test=false;
        }
        return test;
    }
}

