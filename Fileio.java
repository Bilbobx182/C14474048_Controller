package root;

import java.io.*;
import java.util.ArrayList;


public class Fileio
{
    public static ArrayList<String> input = new ArrayList<>();
    public static ArrayList<String> profiles = new ArrayList<>();
    Pbox pb=new Pbox();

    public void writestats(String profilename,float ac,float bc,float xc,float yc)
    {
        float[] provalues=new float[4];
        try
        {
            int i=0;
            BufferedReader reader = new BufferedReader(new FileReader("files\\"+profilename+"STATS"+".txt"));
            String current="";

            while((current=reader.readLine()) !=null)
            {
                float temp=Float.valueOf(current);
                provalues[i]=temp;
                i++;
            }
            reader.close();
        }
        catch(FileNotFoundException e)
        {
          pb.warning("FILE ERROR","Oops, couldn't find it, please email me");
        }
        catch(IOException e)
        {
            pb.warning("FILE ERROR","Oops, something went wrong while reading, please email me");
        }

        PrintWriter fd = null;
        try
        {
            fd = new PrintWriter("files\\"+profilename+"STATS"+".txt", "UTF-8");
        }
        catch (FileNotFoundException PROBLEM)
        {
            pb.warning("FILE ERROR","Oops, couldn't find it 2, please email me");
        }
        catch (UnsupportedEncodingException PROBLEM)
        {
            pb.warning("FILE ERROR","Oops,encoding is wrong, please email me");
        }

        ac+=provalues[0];
        bc+=provalues[1];
        xc+=provalues[2];
        yc+=provalues[3];

        for(int j=0;j<4;j++)
        {
            provalues[j]=0;
        }

        fd.println(ac);
        fd.println(bc);
        fd.println(xc);
        fd.println(yc);
        fd.close();
    }


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
        while(i<input.size())
        {
            System.out.println(input.get(i));
            i++;
        }
    }

    public void newappend(String filename) throws IOException
    {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("files\\"+"prolist.txt", true))))
        {
          //  System.out.println("TRYING TO PRINT");
            out.println(filename);
            out.close();
          //  System.out.println("closed");
        }
        catch (IOException e)
        {
            pb.warning("FILE ERROR","Oooopps, something went wrong appending, please email me");
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