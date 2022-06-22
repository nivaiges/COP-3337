/** 
*  Title:           BalanceTransactions.java 
*  Semester:        COP3337 – Summer 2022 
*  @author          Niv Aiges 
*   
*  I affirm that this program is entirely my own work  
*  and none of it is the work of any other person. 
* 

This program uses io.File and util.Scanner to read a specified text file to calculate cash at the end of the day
if the line ends with P that means we subtract the amount on that line, if the line ends with R we add the amount on that line
at the end we substract P values from R values
we use this to calculate the deficit, if we dont find that the deficit == the cash end value then we alert the user that
the amount expected as not recieved
*/ 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BalanceTransactions {

    //these are used at the end to calculate cash deficit
    //its better to define these now to save on memory
    public static double addCash;
    public static double subtractCash;
    public static double cashDeficit;

    public static void main(String[] args) {
        //new scanner
        Scanner in = new Scanner(System.in);
        //txt file to be used as parameter
        String txtFile;
        //cash start used as parameter (start money)
        double cashStart;
        //cash end used as parameter (expected end of day money)
        double cashEnd;
        System.out.println("Please type the name of the text file: ");
        txtFile = in.nextLine();
        System.out.println("Please type the amount of cash at the start of the day: ");
        cashStart = in.nextDouble();
        System.out.println("Please type the expected amount of cash at the end of the day: ");
        cashEnd = in.nextDouble();
       // System.out.println("Text filename: " + txtFile + "\nCash Start: " + cashStart + "\nCash End: " + cashEnd);
       //memory leak close
        in.close();
        //call to method using described variables
        readFile(txtFile, cashStart, cashEnd);
    }

    public static String readFile(String txtFileName, double cashStartValue, double cashEndValue) {
        //use try catch here incase of file not found
        try {
            //txtFileName is parameter from main method
            File inputFile = new File(txtFileName);
            Scanner fileReader = new Scanner(inputFile);
            //scans and runs code for each line in txt file
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                //put data into array so I dont have to use substring instead, this also allows for ints and doubles that have...
                //more decimals or less decimals
                String dataArr[] = data.split(" ");
                //if P then that means subtract (as per your email)
                if (dataArr[2].equals("P")) {
                    subtractCash += Double.parseDouble(dataArr[1]);
                    //if R then that means add (as per your email)
                } else if (dataArr[2].equals("R")) {
                    System.out.println(Double.parseDouble(dataArr[1]));
                    addCash += Double.parseDouble(dataArr[1]);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            //if file not found then it asks you to type it again and then run the same method until it works
            System.out.println("An error occurred.\nPlease type .txt file name");
            Scanner ins = new Scanner(System.in);
            txtFileName = ins.nextLine();
            ins.close();
            readFile(txtFileName, cashStartValue, cashEndValue);
        }
///1000, 100,  1000
//2900
//3000
        //this is how I calculate the total cash deficit from the expected end value
        cashDeficit = (addCash - subtractCash) + cashStartValue;
        //if the cash deficit != the expected end value, then it runs this if statement
        if (cashDeficit != cashEndValue) {
            System.out.println("\n=========================================================================================");
            System.out.println("The amount of cash at the end of the day was different than what was expected");
            System.out.println("Starting cash: " + cashStartValue + "\nCash Recieved: " + addCash + "\nCash payed: " + subtractCash);
            System.out.println("Total difference inbetween cash amount: +/-" + cashDeficit);
            System.out.println("=========================================================================================\n");
        }else
        {
            //if this block runs that means cash deficit == cash end
            System.out.println("The amount of money at the end of the day was expected");
        }
    
        return " ";
    }

}
