package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static Scanner scan = new Scanner(System.in);

    private static ArrayList<String> getStrings()
    {
        ArrayList<String> userInterfaceData = new ArrayList<>();
        userInterfaceData.add(0, "Display Market Categories And Products\n");
        userInterfaceData.add(1, "Add Category\n");
        userInterfaceData.add(2, "Delete Category\n");
        userInterfaceData.add(3, "Add Or Modify Product\n");
        userInterfaceData.add(4, "Remove Product\n");
        userInterfaceData.add(5, "Move Product To Another Category\n");
        userInterfaceData.add(6, "Sell Product\n");
        userInterfaceData.add(7, "EXIST");
        return userInterfaceData;
    }

    public static void ControlConsole()
    {
        Market myMarket = new Market();
        ArrayList<String> userInterfaceData = getStrings();

        if (!checkUserNameAndPassword())
            return;

        while (true)
        {
            printMenu(userInterfaceData);
            System.out.print("Enter your choice: ");
            String inputMoving = scan.nextLine();
            int choice;

            choice = Integer.parseInt(inputMoving)-1;
            if(choice >= 0 && choice < userInterfaceData.size())
            {
                TerminalUtils.clearTerminal();
                switch (choice)
                {
                    case 0:
                        myMarket.printCategoryAndProduct();
                        TerminalUtils.pause();
                        break;
                    case 1:
                        myMarket.addNewCategory();
                        break;
                    case 2:
                        myMarket.deleteCategory();
                        break;
                    case 3:
                        myMarket.addOrModifyNewProduct();
                        break;
                    case 4:
                        myMarket.deleteProduct();
                        break;
                    case 5:
                        myMarket.moveProductToAnotherCategory();
                        break;
                    case 6:
                        myMarket.makeOrder();
                        break;
                    case 7:
                        TerminalUtils.setColor(Colors.BLUE);
                        System.out.println("Thank you for using our system.");
                        return;

                }
            }
            System.out.println("----------------------------------");
        }
    }

    public static boolean checkUserNameAndPassword()
    {
        String userName = "abdonasr";
        String password = "1234";
        int countTrying = 3;

        while(true)
        {
            TerminalUtils.clearTerminal();

            TerminalUtils.setColor(Colors.BRIGHT_WHITE);
            System.out.print("Please Enter User Name  : ");
            TerminalUtils.setColor(Colors.LIGHT_GREEN);
            String userInput = scan.nextLine();
            TerminalUtils.setColor(Colors.BRIGHT_WHITE);

            System.out.print("Please Enter Password  : ");
            TerminalUtils.setColor(Colors.GREEN);
            String passwordInput = scan.nextLine();//inputPassword();
            TerminalUtils.setColor(Colors.BRIGHT_WHITE);

            if(!userInput.equals(userName) || !password.equals(passwordInput))
            {
                countTrying--;
                TerminalUtils.setColor(Colors.RED);
                System.out.print("   UserName or Password is Wrong  \n");
                TerminalUtils.setColor(Colors.BRIGHT_WHITE);
                System.out.print("---------------------------");
            }
            else
            {
                System.out.print("---------------------------");
                return true;
            }

            if(countTrying == 0)
                return false;
        }
    }
    public static void printMenu(ArrayList<String> userInterfaceData)
    {
        TerminalUtils.clearTerminal();
        TerminalUtils.setColor(Colors.GREEN);
        System.out.println("\t\t(((__Abdelkawy's Market__)))\n");
        TerminalUtils.setColor(Colors.BRIGHT_WHITE);
        for(int i = 0; i < userInterfaceData.size(); i++)
            System.out.print("==> " + (i + 1) + ' ' + userInterfaceData.get(i));
        System.out.println("\n");
    }


    public static void main(String[] args)
    {
        ControlConsole();
    }
}
