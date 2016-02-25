package root;

import java.io.*;
import java.util.ArrayList;



public class Fileio
{
    public static ArrayList<String> input = new ArrayList<>();

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


            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String current="";
            while((current=reader.readLine()) !=null)
            {
                input.add(current);
              //  System.out.println(input.get(i) + i);
                i++;
            }
            test=true;
            reader.close();
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

    public void inputgetter()
    {
        int i=0;
        while(i<4)
        {
            System.out.println(input.get(i));
            i++;
        }
    }
}