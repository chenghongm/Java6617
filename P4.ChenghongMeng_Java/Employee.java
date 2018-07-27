package employee;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class Employee implements Serializable
{
	
	 String loginName;
     double salary;
     Date date ;
     String employeeName;
     final int employeeID;
     public static  int nextID=0;	
     
     Employee(String lN,double s,String eName)
     { 
    	 
    	 loginName = lN;
    	 salary = s;
    	 date = new Date();		
    	 employeeName = eName;
    	 employeeID = nextID++;
     }
     
public String toString()
{       
    	// System.out.println(loginName +" "+ salary +" "+ employeeName +" ");
    	String ID = String.format("%05d",employeeID);
    	 return  ID+"\t"+ loginName +"\t"+salary +"\t"+date+"\t"+employeeName+"\t";
}
}
