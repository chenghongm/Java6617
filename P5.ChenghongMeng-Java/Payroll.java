package payroll; 
import java.util.*;

import payroll.Employee.Hourly;
import payroll.Employee.Salaried;

import java.io.*;
import java.text.DecimalFormat;
//import java.text.DateFormat;
import java.text.ParseException;


public class Payroll
{
      static ArrayList<Employee> list = new ArrayList<Employee>();
      static ArrayList<Employee> listQuit= new ArrayList<Employee>();
      static Hourly pHourly;
      static Salaried pSalaried;
      static String  lName ; 
      static Scanner input=new Scanner(System.in);
	  Date creation =new Date();
	  static DecimalFormat dec = new DecimalFormat("#0.00");
      
      Payroll(String lN,double s,Date nw,int id,String eN) throws ParseException,IOException,ClassNotFoundException,FileNotFoundException
      {  
    	  try {
    		  
	      	  fileObjectInput();
	      	  
	      	  fileObjectInput_Quit( );
	         for(Employee p:list)
	      			System.out.println(p);	
	         fileObjectOutput();
	         fileObjectOutput_Quit();
	         fileTextOutput();
	        }catch(FileNotFoundException e) { System.out.println("File not Found!"); }
	         catch(ClassNotFoundException cfe) {System.out.println("Error!");}
	         catch(IOException ioe) {System.out.println("Something Wrong Happen!");ioe.printStackTrace(); }
     	     catch(ClassCastException cce) {System.out.println("error!");cce.printStackTrace();}
      };
    
 public static void newEmployee(String lName) throws FileNotFoundException,IOException, ClassNotFoundException, ParseException
 {
	      String inputName;
     	  String eName ;
     	  double salarys ;
     	  char hORs;
     	
 	    
 	        
 	    for (int i=0;i<list.size();)
 	    { 
 	      if(list.get(0).loginName.contains(lName))
 	      {
 	    	System.out.println("Now add your emploees: \n");  
 	    	
     	    System.out.println("Please create your employee's login name(no space allowed): \n");
			inputName=input.next();				
			input.nextLine();
			for(Employee p:list)
		     {
		    	 if(p.loginName.equals(inputName))
		    	 {   
		    		 System.out.println("This login name has been used, please create another one!\n");
		    		 return;
		    	 }
		     }
			
 	    	
     	    System.out.println("Please enter your employee's real name( with space): \n");
			eName=input.nextLine();     	   		
			
			System.out.println("Please enter her/his salary: \n");
			salarys=input.nextInt();	
			
			
			System.out.println("Hourly pay Or Salaried pay? h/s");
			 hORs= input.next().charAt(0);
			if(hORs=='h')
			{
				Employee pHourly= new Hourly(inputName,salarys,eName);
			    list.add(pHourly);
			}		
			else if(hORs=='s')
			{
				Employee pSalaried= new Salaried(inputName,salarys,eName);
			    list.add(pSalaried);
			}
			else
			{
				System.out.println("Wrong Enter! Only h or s !");return;
			}
			
			
			//System.out.println(list);
			fileObjectOutput();
			break;
 	      }
 	      else
 	    	  System.out.println("Boss Only! Please do another choice!");break;
 	   }
 };



public static void changeEmployeeData(String lName) throws FileNotFoundException, IOException
{     String realName;
      String rNameChangeto;
      double saralyChangeto;
      int flag=0;
      char yORn=0;
	 
	
	      if(list.get(0).loginName.contains(lName))
	      {  
	    	System.out.println("Now change your emploees' data: \n"); 
	    do {
	    	 
	    	System.out.println("Please enter the employee's original real name: \n");
	    	input.nextLine();
	    	realName=input.nextLine();
	    	
	    	for (Employee p :list)
	    	{if(p != null && p.getEmployeeName().contains(realName))
	    	{
	    		   
	    		   flag++;
	    		   System.out.println("what would you like to change? please select: \n1-Name\n2-Salary\n3-Both\n");
	    		   int choice=input.nextInt();
	    		   if(choice==1)
	    			{
	    			   System.out.println("\nChange  "+p.getEmployeeName()+"  to: \n");
	    			   input.nextLine();
	    			   rNameChangeto=input.nextLine();
	    			   p.setEmployeeName(rNameChangeto);
	    			}
	    		   else if(choice==2)
	    		   {
	    			System.out.println("Please change the emplyee's saraly to: \n");
	    			saralyChangeto = input.nextDouble();
	    			p.seEmployeeSalary(saralyChangeto);
	    			
	    		   }
	    		   else if(choice==3)
	    		   {
	    			   System.out.println("\nChange  "+p.getEmployeeName()+"  to: \n");
	    			   input.nextLine();
	    			   rNameChangeto=input.nextLine();
	    			   p.setEmployeeName(rNameChangeto);
	    			   System.out.println("Please change the emplyee's saraly to: \n");
		    		    saralyChangeto = input.nextDouble();
		    			p.seEmployeeSalary(saralyChangeto);
	    		   }
	    			
	    		  System.out.println("Would you like to update another employee? y/n");
	    		  			
	    		   yORn= input.next().charAt(0);
	    	         
	    	       if(yORn == 'n')
	    	    	 {    System.out.println("Update done!");
	    	    	      
	    	    	      fileObjectOutput();
	    				
	    			  }
	    			
	    		}
	    	 }
	    }while(yORn == 'y');
	        if(flag==0)
	    			System.out.println("Employee no found!"); 
	    	
	   
	     }	
	    else
	    {  System.out.println("Boss only!");}
	    	
	
	     
	    
}
public static void terminateEmployee(String lName) throws FileNotFoundException, IOException
{ 
	  int realID;

      int flag=0;

    if(list.get(0).loginName.contains(lName))
    {    
  	
  	System.out.println("Please enter the employee's ID you are going to fire: \n");
  	
  	realID=input.nextInt();
  	
  	String ID=Integer.toString(realID);
  
  	for (Employee p: list)
  	{if(p != null && Integer.toString(p.getEmployeeID()).contains(ID))
  	  {  
  		flag++;
  		System.out.println(p.getEmployeeName() + " was fired!\n");
  	   System.out.println("**Quit Employee list**");
  	    listQuit.add(p);
  		System.out.println(listQuit);
  		
  		System.out.println("\n**Employees' list**" );
  		list.remove(p);
  		System.out.println(list);
  		fileObjectOutput_Quit();
	    fileObjectOutput();
  		
			break;
		}
  		
	}	 
 

  }
    else 
  {
    for(Employee p:list)
   { if (p.loginName.contains(lName))
     {   flag++;
	     System.out.println(p.getEmployeeName() + ", Now you quit!");
	      listQuit.add(p);
	     list.remove(p);
	     fileObjectOutput_Quit();
	     fileObjectOutput();
	      break;
	 
      }
     
   }
 }   

 if(flag==0)
			System.out.println("Employee no found!"); 

}


public void doMenu() throws FileNotFoundException,IOException,ClassNotFoundException, ParseException
{		int choice=9;
        int loginCheck=0;
    	do {
    	    	showMenu();
    	    	System.out.print("Please enter your choice: ");
    	    	choice = input.nextInt();
    	    	switch(choice)
    	    	{
    	    	case 1: doLogin(list);
    	    	        loginCheck++;
    	    	        break;
    	    	        
    	    	case 2: 
    	    		   if( loginCheck==0)
    	    		   System.out.println("Boss Only! Please login to check!");
    	    	       else
    	    	       newEmployee(lName);
    	    	        break;    	    
    	    	        
    	    	case 3:
    	    		  if( loginCheck==0)
    	    		   System.out.println("Please login first!");
    	    	       else
    	    	       listEmployee(lName);
    	    	        break;
    	    	        
    	    	case 4:
    	    		   if( loginCheck==0)
	    		       System.out.println("Please login first!");
	    	           else
	    	    	   changeEmployeeData( lName);
    	    		   break;
    	    		   
    	    	case 5: 
    	    		   if( loginCheck==0)
	    		       System.out.println("Please login first!");
	    	           else
    	    		   terminateEmployee( lName);
    	    		     break;
    	    		    
    	    	case 6:
    	    		
    	    		  if( loginCheck==0)
	    		      System.out.println("Please login first!");
	    	          else    	    		
    	    		  payEmployee(lName);
    	    	      break;
    	    	      
    	    	case 0:
    	    		  exit(lName);   	    	       
    	    	       return;
    	    	default:
    	    		System.out.println("Wrong Enter!");
    	    	}
    	    	
    	  }while(choice!=0); 
}
public static void exit(String ln) throws FileNotFoundException, IOException {
	
	   if(list.get(0).loginName.contains(ln))
	   {    System.out.println("Thanks! Boss!" );
    	    System.out.println("**Employee list**");
    	    System.out.println(list.size());
    	     for(Employee p:list)
    	     System.out.println(p);
    	     System.out.println("\n**Quited Employee list**");
    	     if(listQuit.size()==0)
    	     {	 System.out.println("Empty list!");
    	        
    	     }
    	    else 
    	     {     System.out.println(listQuit.size());
    	    	   for(Employee p:listQuit)
    	    	  System.out.println(p);
    	     }
	   }
	   else
		   System.out.println("Thanks!");
	          fileObjectOutput();
	          fileObjectOutput_Quit();
}
public void payEmployee(String lgName) throws FileNotFoundException, IOException
{
	
	if(list.get(0).loginName.contains(lgName))
	{
		for(Employee p:list)
		{//System.out.println(p.getClass());
		   
		   System.out.println("\n"+ p.getEmployeeName() + " ");
			p.setPay();
		}
		System.out.println("\n\t***Payroll Report***\n");
		
		System.out.format("  Pay "+" \t"  +"  EmployeeID  "+"\t"+ "EmployeeName%n");
		System.out.print("\n---------------------------------------");
       for(Employee p:list)
      {
	   System.out.println("\n"+ dec.format(p.getPay())+"   \t" + p.getEmployeeID()+"      \t"+ p.getEmployeeName());
      }
       fileTextOutput();
		
	}
	else 
	{
		System.out.println("Boss Only! ");
		
	}
		
}
public static final String[] menu= 
{    	       	
		        "\n\n***Payroll Menu***"+ 
                "\n---------------------"+	
    	        "\t\n1.Log In"+
    			"\t\n2.Enter employees"+
    	        "\t\n3.List employees"+
    			"\t\n4.Change employees"+
    			"\t\n5.Terminate employees"+
    	        "\t\n6.Pay employees"+
    			"\t\n0.Exit system"+
    	        "\t\n--------------------- "
 };
    	
public void showMenu()
{
    		for(int k=0;k<menu.length;k++)
    		{
    			System.out.println(menu[k]);
    		}
}
public void listEmployee(String lnName) throws FileNotFoundException, IOException
{   int flag=0;
	for(int i=0;i<list.size();i++)
	{   flag++;
		if(list.get(0).loginName.contains(lnName))
		{   System.out.println("**Employee list**");
			for(Employee p:list)
				System.out.println(p);
			
			break;
		}
		else if(list.get(i).loginName.contains(lnName))
		{   flag++;
			System.out.println("Only Boss has to right to see whole employees list!\n");
			System.out.println("Your record found as following : ");
			System.out.println(list.get(i).toString());
		}
			
	}
	if(flag==0) System.out.println("Employee no found!");
}
public void doLogin(ArrayList<Employee> list) throws FileNotFoundException,IOException,ClassNotFoundException, ParseException
{           
    	
    	   
    	
    		System.out.println("Please enter your loginName: \n");
    		int flag=0;
    	    lName = input.next(); 
    	    for(int i=0;i<list.size();i++)
    	  {
    	    if(list.get(i).loginName.contains(lName))
    	    {
    	        flag++;
    	        if(list.get(0).loginName.contains(lName))
    	        {	
    	        System.out.println("Welcome, Boss! Your record found as following : \n");    	        
    	        System.out.println(list.get(0).toString());
    	        System.out.println("Please do other choice!");
    	        }
    	        else 
    	        {
    	    	System.out.println("Your record found as following : ");
    	    	System.out.println(list.get(i).toString());
    	        }
    	    	
    	    }
    	    		
    	  } 
    	    
    	    if (flag==0)
      		  System.out.println("No Found!Please do other choice!\n");
    	
}
public static void fileTextOutput() throws FileNotFoundException,IOException
{    File out = new File ("payroll.txt");
     PrintWriter pw = new PrintWriter( out ); 
     
    try { 
    	pw.println("\n\t***Payroll Report***\n");
		
		pw.format("  Pay "+" \t"  +"  EmployeeID  "+" \t"+ "EmployeeName%n");
		pw.print("\n---------------------------------------");
    	 for(Employee p:list)
         {
   	      pw.println( "\n"+dec.format(p.getPay())+"   \t" + p.getEmployeeID()+"        \t"+ p.getEmployeeName());
         }
    }finally {pw.close();}
    
}
public static  void fileObjectOutput_Quit() throws FileNotFoundException,IOException{
	  
	  FileOutputStream fos_quit = new FileOutputStream(new File("employeeQuit.txt"));
		ObjectOutputStream objectOut_quit = new ObjectOutputStream(fos_quit);
		
		try
		{		
				objectOut_quit. writeObject(listQuit);
			    objectOut_quit.flush();
       		  
		}
		finally 
		{ 
			objectOut_quit.close();
		    
		 }
}
public static  void fileObjectOutput() throws FileNotFoundException,IOException{
	  
	  FileOutputStream fos = new FileOutputStream(new File("employee.txt"));
		ObjectOutputStream objectOut = new ObjectOutputStream(fos);
		
		try
		{		
				objectOut. writeObject(list);
				objectOut.flush();
			
		}		finally 
		{ 
			objectOut.close();
		    
		 }
}

@SuppressWarnings("unchecked")
public static void fileObjectInput( ) throws FileNotFoundException,ClassNotFoundException, IOException, ParseException
{
	
	FileInputStream fi = new FileInputStream(new File("employee.txt"));
	ObjectInputStream oi = new ObjectInputStream(fi); 
    
   try {
	   
	ArrayList<Employee> alist=(ArrayList<Employee>)oi.readObject();
	
	Iterator<Employee> iter = alist.iterator();
	while(iter.hasNext())
	{   for(Employee pNew: alist)
	   {
		if(pNew.getClass().equals(Hourly.class))
		{  // System.out.println(pNew.getClass());
			pNew=new Hourly( (Employee)iter.next());
			
			list.add(pNew);
			//System.out.println(list);
		}
		else
		{   //System.out.println(pNew.getClass());
			pNew=new Salaried((Employee)iter.next());
			list.add(pNew);
		}
		
	   }
			
	}
	 	   
	} finally {oi.close();}

	
}

@SuppressWarnings("unchecked")
public static void fileObjectInput_Quit( ) throws FileNotFoundException,ClassNotFoundException, IOException, ParseException
{
	
	FileInputStream fi = new FileInputStream(new File("employeeQuit.txt"));
	ObjectInputStream oi = new ObjectInputStream(fi); 
    
   try {
	   
	ArrayList<Employee> qlist=(ArrayList<Employee>)oi.readObject();
	
	Iterator<Employee> iter = qlist.iterator();
	while(iter.hasNext())
	{   for(Employee pNew: qlist)
	   {
		if(pNew.getClass().equals(Hourly.class))
		{  // System.out.println(pNew.getClass());
			pNew=new Hourly( (Employee)iter.next());
			
			listQuit.add(pNew);
			//System.out.println(list);
		}
		else
		{   //System.out.println(pNew.getClass());
			pNew=new Salaried((Employee)iter.next());
			listQuit.add(pNew);
		}
		
	   }
			
	}
	 	   
	} finally {oi.close();}

	
}


public static void main(String[] args) throws FileNotFoundException,ClassNotFoundException, IOException, ParseException
{
    	System.out.println("P5: Payroll\n "+
                           "Author: Chenghong Meng\n");
    try {	
    	
    	String lN = new String ();
    	String eN =new String();
    	int id =0;
    	double s=3000.0;
    	Date creation = new Date();
    	
    	
     	Payroll pr = new Payroll(lN, s,creation,id, eN);
     	
     	if(list.size()==0)
     	{
     
     		System.out.println("Please create your employee's login name(no space allowed): \n");
			String lName=input.next();				
			input.nextLine();
			
     	    System.out.println("Please enter your employee's real name( with space): \n");
			String eName=input.nextLine();     	   		
			
			System.out.println("Please enter her/his salary: \n");
			double salarys=input.nextInt();	   		
		
	
		
		System.out.println(eName+ ", your record was created: \n");
		Employee pSalaried= new Salaried(lName,salarys,eName);
	    list.add(pSalaried);
		System.out.println(list +"\n");	
		System.out.println("\nNow you are the Boss!Please log in!");
		
		pr.doMenu();
     	}
     	else
    	pr.doMenu();
        
       
    	
 }catch(IOException ioe) {System.out.println("Error!");ioe.printStackTrace();}
}
}