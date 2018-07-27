
//Author: Chenghong Meng

//Assignment: Program6_Password and GUI

package payrollSystem;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;


public abstract class EmployeeG implements Serializable 
{
	  
	private static final long serialVersionUID = 1L;
	
	 protected String loginName;
	 protected byte[] password;
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
     public void setPassword(byte[] bs)
     {
    	 password = bs;
     }
     public byte[] getPassword()
     {
    	 return password;
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
    
     EmployeeG(String lN,double s,String eName)
     {   byte[] passw = { 0};
    	 password = passw;
    	 loginName = lN;
    	 salary = s;
    	 date = new Date();		
    	 employeeName = eName;
    	 employeeID=count ;
    	 count++;
     }
    EmployeeG(EmployeeG pNew)
     { password = pNew.password;
      loginName = pNew.loginName;
      salary =pNew.salary;
      date = pNew.date;
      employeeName=pNew.employeeName;
	  employeeID =pNew.employeeID ;
	  count++;
	 }
    
public static class Hourly extends EmployeeG
{

	private static final long serialVersionUID = 1L;
	private static final int payRateOfhour = 24;

	Hourly(String lN, double s, String eName) 
	{
		super(lN, s, eName);
		// TODO Auto-generated constructor stub
	}
	Hourly(EmployeeG p){
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
public static class Salaried extends EmployeeG
{

	private static final long serialVersionUID = 1L;
	

	Salaried(String lN, double s, String eName)
	{
		super(lN, s, eName);
		
		// TODO Auto-generated constructor stub
	}
	
	Salaried(EmployeeG p){
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
