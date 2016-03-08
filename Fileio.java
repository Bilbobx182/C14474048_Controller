package root;

import java.io.*;
import java.util.ArrayList;



public class Fileio
{
    public static ArrayList<String> input = new ArrayList<>();
    public static ArrayList<String> profiles = new ArrayList<>();

    public void write(String profilename,String aval,String bval, String xval,String yval)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("files\\"+profilename+".txt", "UTF-8");
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

    public boolean read(String filename)
    {
        boolean test;
        int i=0;
        try

        {
            BufferedReader reader = new BufferedReader(new FileReader("files\\"+filename));
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
            System.out.println("ERROR1");
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

    public void newappend(String filename) throws IOException
    {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("files\\"+"prolist.txt", true))))
        {
            System.out.println("TRYING TO PRINT");
            out.println(filename);
            out.close();
            System.out.println("closed");
        }
        catch (IOException e)
        {
            System.out.println("ERROR");
        }
    }

    public void wrpcheck()
    {
        profiles.clear();

        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("files\\"+"prolist.txt"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String current="";
        try
        {
            while((current=reader.readLine()) !=null)
            {
                profiles.add(current);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}