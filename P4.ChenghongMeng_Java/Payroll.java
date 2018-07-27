package employee;
import java.util.*;
import java.io.*;
//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Payroll
{
     static ArrayList<Employee> list = new ArrayList<Employee>();
      Employee p ;
      	
      Scanner input=new Scanner(System.in);
	  Date creation =new Date();
	 
      
      Payroll(String lN,double s,Date nw,int id,String eN) throws ParseException,IOException,ClassNotFoundException,FileNotFoundException
      { try {
    	  fileTextInput();
    	  for(Employee pNew:list)
    			System.out.println(pNew);	
    	 // System.out.println(length);
      }catch(FileNotFoundException e) { System.out.println("File not Found!"); }
       catch(ClassNotFoundException cfe) {System.out.println("Error!");}
       catch(IOException ioe) {System.out.println("Empty!Please add data!");}
      };
      
 public void newEmployee() throws FileNotFoundException,IOException, ClassNotFoundException, ParseException
 {
    	  String  lName ; 
     	  String eName ;
     	  double salarys ;
     	  
     	
 	    
 	    if(list.size()==0)
     	  
 	    {   
 	    	
 	    	System.out.println("Welcom,Boss! Now add your emploees: \n");  
     	    System.out.println("Please create your employee's login name(no space allowed): \n");
			lName=input.next();	
			
			input.nextLine();
     	    System.out.println("Please enter your employee's real name( with space): \n");
			eName=input.nextLine();     	   		
			
			System.out.println("Please enter her/his salary: \n");
			salarys=input.nextInt();	
			
			
			//System.out.println("Your record was created on: "+ creation);
						
			p= new Employee(lName,salarys,eName);
			list.add(p);
			//fileTextInput();
		//	length = list.size();
			System.out.println(list);
			fileTextOutput();
 	    }
 	    else
 	    { 	int lnum;     	
 		   System.out.println("Please enter your employeeID: "); 		
 	       lnum = input.nextInt();
 	       if(lnum==00000)
 	       {
 	    	    System.out.println("Welcom,Boss! Now add your emploees: \n");  
 	     	    System.out.println("Please create your employee's login name(no space allowed): \n");
 				lName=input.next();	
 				
 				input.nextLine();
 	     	    System.out.println("Please enter your employee's real name( with space): \n");
 				eName=input.nextLine();     	   		
 				
 				System.out.println("Please enter her/his salary: \n");
 				salarys=input.nextInt();	
 				
 				
 				//System.out.println("Your record was created on: "+ creation);
 							
 				p= new Employee(lName,salarys,eName);
 				list.add(p);
 				
 				//length = list.size();
 				System.out.println(list);
 				fileTextOutput();
 	       }
 	       else
 	        System.out.println("Sorry, only Boss has the right to add emploee!");
 	       
 	    }
 }
      
public void doMenu() throws FileNotFoundException,IOException,ClassNotFoundException, ParseException
{		int choice=9;
    	do {
    	    	showMenu();
    	    	System.out.print("Please enter your choice: ");
    	    	choice = input.nextInt();
    	    	switch(choice)
    	    	{
    	    	case 1: doLogin(list);
    	    	        break;
    	    	case 2: newEmployee();
    	    	        break;    	    		
    	    	case 3: System.out.println(list);
    	    	        
    	    	        break;
    	    	case 4: break;
    	    	case 5: break;
    	    	case 0:fileTextOutput();
    	    	       System.out.println("Thanks!");return;
    	    	default:
    	    		System.out.println("Wrong Enter!");
    	    	}
    	    	
    	  }while(choice!=0); 
}
      
public static final String[] menu= 
{    			"\n\n****Payroll Menu*****"+
    	        "\t\n1.Log In"+
    			"\t\n2.Enter employee"+
    	        "\t\n3.List Employees"+
    			"\t\n4.Terminate an employee"+
    	        "\t\n5.Pay employee"+
    			"\t\n0.Exit system"+
    	        "\t\n-------------"
 };
    	
public void showMenu()
{
    		for(int k=0;k<menu.length;k++)
    		{
    			System.out.println(menu[k]);
    		}
}
public void doLogin(ArrayList<Employee> list) throws FileNotFoundException,IOException,ClassNotFoundException
{
    	
    	    String nameln;
    	
    		System.out.println("Please enter your loginName: \n");
    		int flag=0;
    	    nameln = input.next();
    	    for(int i=0;i<list.size();i++)
    	  {
    	    if(list.get(i).loginName.contains(nameln))
    	    {
    	        flag++;
    	    	System.out.println("Your record found as following : ");
    	    	System.out.println(list.get(i).toString());
    	    	  	    	
    	    	
    	    }
    	  }
    	    if (flag==0)
      		  System.out.println("No Found!Please do other choice!\n");
    	
}
public static  void fileTextOutput() throws FileNotFoundException,IOException{
	  File out = new File("employee.txt");
		PrintWriter pw = new PrintWriter(out);
		
		try
		{		for(Employee p:list)  
				pw. println(p);
			
		}
		finally 
		{ 
			pw.close();
		    
		 }
}

public void fileTextInput() throws FileNotFoundException,ClassNotFoundException, IOException, ParseException
{
	 File in = new File ("employee.txt");
	 Scanner sc = new Scanner(in);
	 sc.useDelimiter("\t");
	while(sc.hasNext()) 
	 {   String eIDs = sc.next();
	     int id = Integer.parseInt(eIDs);
	     String lN = sc.next();
	     String s = sc.next();
	     double sd = Double.parseDouble(s);
	     String dNow = sc.next();
	     SimpleDateFormat formtter = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy",Locale.US);
	     Date creation = (Date)formtter.parse(dNow);
	     String eN = sc.nextLine();
	     Employee pNew =new Employee(lN,sd,eN);
		 list.add(pNew);
		 
	 };
	 
	
	 
	 sc.close();
	 
}

public static void main(String[] args) throws FileNotFoundException,ClassNotFoundException, IOException, ParseException
{
    	System.out.println("P4: An Employee Database\n "+
                           "Author: Chenghong Meng\n");
    	
    	String lN = new String ("c1");
    	String eN = "Ch m";
    	int id =00000;
    	double s = 3000.0;
    	Date creation = new Date();
    	//fileTextOutput();
     	Payroll pr = new Payroll(lN, s,creation,id, eN);
    	
    	pr.doMenu();
        
       
    	
 }
}
