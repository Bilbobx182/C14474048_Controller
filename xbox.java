package root;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class xbox{

    static Controller controller;
    static boolean Start;
    static boolean a, b, x, y;
    static float ac, bc, xc, yc;
    static int alta;

    public static void main(String[] args)
    {
        try
        {
            Controllers.create();
        }
        catch (LWJGLException exception)
        {
            exception.printStackTrace();
        }
        Controllers.poll();
        controller=Controllers.getController(0);

        for (int i=0; i<controller.getAxisCount(); i++)
        {
            //  System.out.println(controller.getAxisName(i));
            controller.setDeadZone(i,(float)0.4);
        }

        while(true)
        {
            controller.poll();
            Start=controller.isButtonPressed(7);
            //
//                  System.out.print("A1 UP DOWN "+ controller.getAxisValue(0));
//                  System.out.print("A1 LEFT RIGHT "+ controller.getAxisValue(1));
//                  System.out.print("A2 UP DOWN "+ controller.getAxisValue(2));
//                  System.out.print("A2 LEFT RIGHT "+ controller.getAxisValue(3));
//                  System.out.println();
            a=controller.isButtonPressed(0);
            b=controller.isButtonPressed(1);
            x=controller.isButtonPressed(2);
            y=controller.isButtonPressed(3);
          System.out.print("ALT"+alta+"CAS"+ac);
            //      System.out.print(bc);
            //      System.out.print(xc);
            //      System.out.print(yc);
            System.out.println();
            if (a)
            {
                ac++;
                if(ac % 90000 == 0)
                {
                    alta++;
                }
            }
            if (b)
            {
                bc++;
            }
            if (x)
            {
                xc++;
            }
            if (y)
            {
                yc++;
            }
        }
    }
}
