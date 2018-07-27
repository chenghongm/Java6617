package birthday;
//Chenghong Meng Assignment2
//Starting point for Program 2
//Birthday.java
//Die
//
import java.util.*;

//------------------------------------------------------------------------------
public class Birthday{
public static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May",
"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
public static final String[] days = { "Sunday", "Monday", "Tuesday",
"Wednesday", "Thursday", "Friday", "Saturday" };
public static final int[] startsOn = {1, 4, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6, };
public static final int[] daysOfmonths= {31,28,31,30,31,30,31,31,30,31,30,31};
// The collection of data members stores the STATE of an object.
// Data members of a class are normally private.   Document each one.
private String month;   // 3-letter abbreviation for the month.
private int date;       // Will be 1..31
private String day;     // The day of the week.
private int numberOfmonth;//add two more private member.
private int dayOfyear;

//--------------------------------------------------------------------------
// Compute a new random value for the die.
// Postcondition: the return value is between 1 and faces.
Birthday( String m, int d){
    month = m;
    date = d;
    calculateDay();
}

//--------------------------------------------------------------------------
private void calculateDay() {
    int found, k, answer;
    for(k=0; k<12; ++k) {
        if (months[k].equals(month))  break;
    }
    found = k;
    
    numberOfmonth = k+1;//intialize the number of the month;
    
    if (found == 12)
        System.out.println("Your month name was not a valid 3-letter abbreviation.");
    else {
        answer = (startsOn[k] + date -1)%7;
        day = days[answer];
    }
}

//---------- A get function gives read-only access to a private data member.
public String getDay(){  return day;  }

//--------------------------------------------------------------------------
// Define toString for every class.
// Return a string that reports the state of the class. Used for debugging.
public String toString(){
    return month +" " + date ;
}
public int getdayOfyear() {return dayOfyear;}// getter 
//create function to get day of year.
public int calcuteDayOfyear() {
	      int found, k, sum=0;
	    for(k=0; k<12; ++k) {
	        if (months[k].equals(month))  break;
	    }
	    found=k;
	    if(found==0) {return dayOfyear = date;}
	    else {
	    for(int i=0;i<found;i++) {
	    	sum =  sum +daysOfmonths[i] ;
	    }
	    dayOfyear = sum+date;
	    }
	    return dayOfyear;
}
//--------------------------------------------------------------------------
public static  void  main( String[] args ) {
    int date;
    String monthname;
    Scanner sc = new Scanner( System.in );

    System.out.println("\nBirthday Day Calculator, Welcome!");
    System.out.print ("Months are: ");
    for( String s : months) System.out.print( s+"  " );

    System.out.println("\n\nPlease enter your birth month and date:");
    monthname = sc.next();
    date = sc.nextInt();
    Birthday b = new Birthday (monthname, date);
    
// change output as instruction to get dayofyear;
    System.out.printf ( "Your %s birthday is on %s this year\n\n",
                       b.toString(), b.getDay() 
                       +"\n it is " + b.calcuteDayOfyear()+" day of the year");
}
}