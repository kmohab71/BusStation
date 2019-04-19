package Malak_Khaled;


import GUI.*;
import javafx.stage.Stage;

public class verify {
    public verify(){}
    public  void entameen(int choice, String Username, String Password, Stage primaryStage)
    {
        Alert mez = new Alert();
        switch (choice){
            case 0:

                mez.display("ERROR","PLEASE CHOOSE WHICH TYPE OF USER ARE YOU");

                break;
            case 1:
                User user = verifyUser.verifyUser(Username,Password);
                if (user!=null){
                    primaryStage.close();
                userWindow next = new userWindow();
                next.userWindow(user);
                }
                else
                    mez.display("ERROR","YOU ARE NOT A REGISTERED USER");
                break;
            case 2:
                Driver driver =verifyDriver.getDriver(Username,Password);
                if (driver!=null)
                {
                    primaryStage.close();
                DriverWindow next2 = new DriverWindow();
                next2.DriverWindow(driver);
                }
                else
                    mez.display("ERROR","YOU ARE NOT A REGISTERED DRIVER");
                break;
            case 3:
                manger manger =verifyManger.verifyManger(Username,Password);
                if (manger!=null){
                    primaryStage.close();
                MangerWindow next3 = new MangerWindow();
                next3.MangerWindowz(manger);
                }
                else
                    mez.display("ERROR","YOU ARE NOT A REGISTERED MANGER");
                break;

        }
    }


}
