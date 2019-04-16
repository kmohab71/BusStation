package Malak_Khaled;


import GUI.DriverWindow;
import GUI.MangerWindow;
import GUI.userWindow;

public class verify {
    public verify(){}
    public static void entameen(int choice, String Username,String Password)
    {
        switch (choice){
            case 0:
                System.out.println("please choose enta meen!!");
                break;
            case 1:
                User user = verifyUser.verifyUser(Username,Password);
                if (user!=null){
                System.out.println("enta user");
                userWindow next = new userWindow();
                next.userWindow(user);
                }System.out.println("enta mesh user");
                break;
            case 2:
                Driver driver =verifyDriver.getDriver(Username,Password);
                if (driver!=null)
                {
                System.out.println("enta driver");
                DriverWindow next2 = new DriverWindow();
                next2.DriverWindow(driver);
                }
                System.out.println("enta mesh driver");
                break;
            case 3:
                manger manger =verifyManger.verifyManger(Username,Password);
                if (manger!=null){
                System.out.println("enta manger");
                MangerWindow next3 = new MangerWindow();
                next3.MangerWindow(manger);
                }
                System.out.println("enta mesh manger");
                break;

        }
    }


}
