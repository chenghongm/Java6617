package payroll;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public abstract class Employee implements Serializable 
{
	  
	private static final long serialVersionUID = 1L;
	
	protected String loginName;
     protected double salary;
     Date date ;
     protected  String employeeName;
     final int employeeID;
     public static  int count;
     protected double pay;
     
     static Scanner input=new Scanner(System.in);   	
     
     
     public void setLoginName(String lN) {
    	 loginName = lN;
     }
     public String getEmployeeName()
     {
    	 return employeeName;
     }
     
     public double getSalary()
     {
    	 return salary;
     }
     
     public void seEmployeeSalary(double s)
     {
    	 this.salary = s;
     }
     
     public void setEmployeeName(String eName)
     {
    	 this.employeeName = eName;
     }
   
    public int getEmployeeID()
     {   
    	 return employeeID;
     }
    
    public abstract double getPay();
    
     Employee(String lN,double s,String eName)
     { 
    	
    	 loginName = lN;
    	 salary = s;
    	 date = new Date();		
    	 employeeName = eName;
    	 employeeID=count ;
    	 count++;
     }
    Employee(Employee pNew)
     { 
      loginName = pNew.loginName;
      salary =pNew.salary;
      date = pNew.date;
      employeeName=pNew.employeeName;
	  employeeID =pNew.employeeID ;
	  count++;
	 }
    
public static class Hourly extends Employee
{

	private static final long serialVersionUID = 1L;
	private static final int payRateOfhour = 24;

	Hourly(String lN, double s, String eName) 
	{
		super(lN, s, eName);
		// TODO Auto-generated constructor stub
	}
	Hourly(Employee p){
		super(p);
	}
	public void setPay()
  {
		int hoursOfworked; 
		Random rand = new Random();
		hoursOfworked = rand.nextInt(10)+96;
       date = new Date();
      
    System.out.println("The number of hours worked is : "+ hoursOfworked);
   
     pay = payRateOfhour * hoursOfworked;
    System.out.println(date + ", was payied $" + pay);
	
  }
   public double getPay()
{     
     return pay;	
	
}
}
public static class Salaried extends Employee
{

	private static final long serialVersionUID = 1L;
	

	Salaried(String lN, double s, String eName)
	{
		super(lN, s, eName);
		
		// TODO Auto-generated constructor stub
	}
	
	Salaried(Employee p){
		super(p);
		
	}
	
	public void setPay()
	{
      date = new Date();
      DecimalFormat dec = new DecimalFormat("#0.00");
		 pay = salary/24;
		System.out.println(date + ",  was payied $" + dec.format(pay));
	}
	
	public double getPay()
	{   
	  return pay;
		
	}
	
}   
public String toString()
{       
    	// System.out.println(loginName +" "+ salary +" "+ employeeName +" ");
    	String ID = String.format("%05d",employeeID);
    	 return  ID+"  "+ loginName +"  "+salary +"  "+date+"  "+employeeName+"  ";
}
public void setPay() {
	// TODO Auto-generated method stub
	
}

}


