/** 
*  Title:           DirectorySortDemo 
*  Semester:        COP3337 – Summer 2022 
*  @author          Niv Aiges 
*   
*  I affirm that this program is entirely my own work  
*  and none of it is the work of any other person. 
* 
* This program sorts an array by seperating its number and its respective word.
* It then determines if the numbers are equal, if they are then we check if the char is greater than
* Chars give a value based on where they are in the alphabet. example: a = 1 z = 26
* This sorting algorithm is similar to bubble sort in the way that it swaps the two values based on value
*/ 
import java.util.Comparator;

public class DirectoryComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        //number 1 and 2 is stirng o1 and o2 but only numbers
        int number1 = Integer.parseInt(o1.replaceAll("[^0-9]", ""));
        int number2 = Integer.parseInt(o2.replaceAll("[^0-9]", ""));
        char charPosOne = o1.charAt(0);
        char charPosTwo = o2.charAt(0);
        if (number1 > number2) return 0;
        //the chartAt functions are used to determine if the word is the same, if they are not, then it swaps using 1
        if (charPosOne > charPosTwo) return 1;
        //else if both if statements are not ran then it returns negative 1 because it that means number2>number1 and the word is the same
        return -1;
    }
}