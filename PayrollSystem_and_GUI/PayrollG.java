
//Author: Chenghong Meng

//Assignment: Program6_Password and GUI


package payrollSystem;

import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import payrollSystem.EmployeeG.Hourly;
import payrollSystem.EmployeeG.Salaried;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.security.*;

public class PayrollG extends Application  
{
      static ArrayList<EmployeeG> list = new ArrayList<EmployeeG>();
      static ArrayList<EmployeeG> listQuit= new ArrayList<EmployeeG>();
      static Hourly pHourly;
      static Salaried pSalaried;
      static String  lName ; 
      static Scanner input=new Scanner(System.in);
	  Date creation =new Date();
	  static DecimalFormat dec = new DecimalFormat("#0.00");
	  boolean search;
	  static boolean createpw;
	  static boolean yesPass;
      
	  Stage st;
	  static Scene s0;
	  Scene s1;
	  Scene s2;
	  Scene s3;
	  Scene s4;
	  Scene s5;
	  Scene s6;
	//------------------------------------s1_elements;
	 
	  GridPane grids1 = new GridPane();
	  Text scenetitle1 = new Text("Welcome!");
	  TextField userTextField1 = new TextField();
	  Label userName1 = new Label("Login Name:");
	  Text tipForp1 = new Text(" Create your password: ");
	  Text tipForp2 = new Text(" Re-enter your password: ");
	  static TextField pass1 = new TextField();
	  static TextField pass2 = new TextField();
	  Button create = new Button("Create");
	  static Text tipFortrue = new Text();
	  Button exits0 = new Button("Exit");
	  Button login =new Button("Login");
	  CheckBox checkBox = new CheckBox("Show/Hide password");
	  PasswordField pass1p = new PasswordField();
	  PasswordField pass2p = new PasswordField();
	  HBox shbox = new HBox();
	  
	
      
    //------------------------------------s2_elements;       
	
      GridPane grids2 = new GridPane();
      TextArea taNote = new TextArea("Employee List: \n\n");
      ScrollPane scrollPane = new ScrollPane(taNote);
     Text s2title = new Text("WelCome, Boss! \n") ;
     Button s2munubt2 = new Button("Enter employees");
     Button s2munubt3 = new Button("Change employees");
     Button s2munubt4 = new Button("Teminate employees");
     Button s2munubt5 = new Button("Pay employees");
     Button s2munubt6 = new Button("Exit");
     VBox s2vbmunubt = new VBox(10);
     
     //----------------------------------s3_elements;
     GridPane grids3 = new GridPane();
     Text noEtip = new Text();
     Text enter = new Text("Please enter data : ");
     Text hip = new Text("H-hourly  S-Salary");
     Text logName = new Text("LoginName: ");
     Text reName = new Text("RealName: ");
     Text salary = new Text("Salary: ");
     TextField  logNamef = new TextField();
     TextField  reNamef = new TextField();
     TextField  salaryf = new TextField();
     RadioButton rbH = new RadioButton("H");
     RadioButton rbS = new RadioButton("S");
     HBox hbrb = new HBox();
    
     
     //-----------------------------------s4_elements;
     GridPane grids4 = new GridPane();
     VBox textBox = new VBox();
     Text hipSearch = new Text("Please enter Employee's Name: ");
     TextField searchName = new TextField();
     TextArea eName = new TextArea();
     Label oname = new Label("OldName");
     Label osalary = new Label("OldSalary");
    
     Label nName = new Label("NewName");
     Label downArrow1 = new Label ("↓");
     Label downArrow2 = new Label("↓");
     Label nSalary = new Label("NewSalary");
        
     TextArea eSalary = new TextArea();
     Text displayQuit = new Text();
     HBox searchbox = new HBox();
     HBox firebox = new HBox();
     Button searchbt = new Button("Search");
     Button firebt = new Button("Fire");
     VBox nameChange = new VBox();
     VBox salaryChange = new VBox();
     TextField eNameTo = new TextField();
     TextField eSalaryTo = new TextField();
     
     Button returnbt1 = new Button("Return");
     Text tipSearch = new Text();
     Button okbt = new Button("OK");
     HBox  okbox = new HBox();
     
     //------------------------------------s5_elements;
     
     GridPane grids5 = new GridPane();
     TextArea payrollta = new TextArea("\n    Payroll Report : \n");
     ScrollPane scrollPane2 = new ScrollPane(payrollta);
     Button paybt = new Button("Pay");
     Button returnbt = new Button("Return");
     HBox paybtbox = new HBox();
     
     //-----------------------------------s6_elements;
     BorderPane borders6 = new BorderPane();
     TextArea edata = new TextArea("Your information is :  \n\n");
     Button quitbt = new Button("Quit");
     Text quittx = new Text();
     Button exitbt = new Button("Exit");
   
    //-------------------------------------s7-terminateScene
     
     
     @Override
     public void start(Stage st) throws Exception {
     	// TODO Auto-generated method stub
    	buildGui();
    	 this.st = st;
    	
    	noEtip.setText("");
     	 
     	s1 = new Scene(grids1, 450, 400); 
     	s2 = new Scene(grids2,500,400);       
        s3 = new Scene(grids3,500,400);
        s4 = new Scene(grids4,500,400);
        s5 = new Scene(grids5,500,400);
        s6 = new Scene(borders6,500,400); 
    	 
    	 
     	 fileObjectInput();
     	for(EmployeeG p:list)
  			System.out.println(p);		
     	
        
        System.out.println(list.size()+"  size");
        
       // if there is no data,create Boss;
        if(list.size()==0)
        {
        	st.setScene(s3);
        	noEtip.setText("No Employee! Please create your inforamtion!");
        }
        // data was created, create password;
        else
        {  	
        st.setScene(s1);
        st.setTitle("Payroll System");
       
        
        }
         st.show();
         
        
        
     }
     	   
    
     

 	public void buildGui() 
 {
 	//------------------------------------------s1
 		 grids1.setAlignment(Pos.CENTER);
 	     grids1.setHgap(10);
 	     grids1.setVgap(10);
 	     grids1.setPadding(new Insets(15, 15, 15, 15));
 	     
 	     scenetitle1.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
 	     
 	  grids1.add(scenetitle1, 0, 0,2,1); 	  
 	 grids1.add(userName1, 0, 2);        
 	 grids1.add(userTextField1, 1, 2);
 	 grids1.add(tipForp1,0,3);
 	 grids1.add(pass1,1,3);
 	 grids1.add(pass1p,1,3);
 	 grids1.add(tipForp2,0,4);
 	 grids1.add(pass2,1,4);
 	grids1.add(pass2p,1,4);
 	 grids1.add(tipFortrue,0,7);
 	 grids1.add(create, 2, 4);
 	 grids1.add(login, 2, 6);
 	 grids1.add(exits0, 1, 8);
 	 shbox.getChildren().add(checkBox);
 	 grids1.add(shbox, 1, 7);
 	pass1.setVisible(false);
 	pass1.setVisible(false);
 	pass1.managedProperty().bind(checkBox.selectedProperty());
    pass1.visibleProperty().bind(checkBox.selectedProperty());

    pass1p.managedProperty().bind(checkBox.selectedProperty().not());
    pass1p.visibleProperty().bind(checkBox.selectedProperty().not());

    pass1.textProperty().bindBidirectional(pass1p.textProperty());
 	 
    pass2.setVisible(false);
 	pass2.setVisible(false);
 	pass2.managedProperty().bind(checkBox.selectedProperty());
    pass2.visibleProperty().bind(checkBox.selectedProperty());

    pass2p.managedProperty().bind(checkBox.selectedProperty().not());
    pass2p.visibleProperty().bind(checkBox.selectedProperty().not());

    pass2.textProperty().bindBidirectional(pass2p.textProperty());
 	 
 	 login.setOnAction(new login1Action());
 	 exits0.setOnAction(new exitAction());
 	 create.setOnAction(new createAction());
 	
 
 	
    //---------------------------------------------s2
        grids2.setAlignment(Pos.CENTER);
        grids2.setHgap(10);
        grids2.setVgap(10);
        grids2.setPadding(new Insets(25, 25, 25, 25));	
      s2title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
      grids2.add(s2title, 0, 0, 2, 1);
      taNote.setEditable(false);
      grids2.add(scrollPane, 0, 2);
      
      s2vbmunubt.setSpacing(10);
      s2vbmunubt.setPrefWidth(150);
      s2munubt2.setMinWidth(s2vbmunubt.getPrefWidth());
      s2munubt3.setMinWidth(s2vbmunubt.getPrefWidth());
      s2munubt4.setMinWidth(s2vbmunubt.getPrefWidth());
      s2munubt5.setMinWidth(s2vbmunubt.getPrefWidth());
      s2munubt6.setMinWidth(s2vbmunubt.getPrefWidth());
      s2vbmunubt.setAlignment(Pos.BOTTOM_RIGHT);
     // taNote.setOnAction(new displayEmployeeAction());
      s2vbmunubt.getChildren().addAll(s2munubt2,s2munubt3,s2munubt4,s2munubt5,s2munubt6);
      grids2.add(s2vbmunubt, 1, 2);
 	
 	  s2munubt2.setOnAction(new enterEaction());
 	  s2munubt3.setOnAction(new changeAction());
 	  s2munubt4.setOnAction(new terminateAction());
 	  s2munubt5.setOnAction(new payAction());
 	  s2munubt6.setOnAction(new exitAction());
 	 
 //-----------------------------------------------s3
 	  grids3.setAlignment(Pos.CENTER);
      grids3.setHgap(10);
      grids3.setVgap(10);
      grids3.setPadding(new Insets(25, 25, 25, 25));
      
      enter.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
      hip.setFont(Font.font("Tahoma", FontWeight.NORMAL, 11));
      grids3.add(enter, 0, 1);
      grids3.add(logName, 0, 2);
      grids3.add(logNamef, 1, 2);
      grids3.add(reName, 0, 3);
      grids3.add(reNamef, 1, 3);
      grids3.add(salary, 0, 4);
      grids3.add(salaryf, 1, 4);
      hbrb.getChildren().addAll(rbH,rbS);
      grids3.add(hbrb, 1, 5);
      grids3.add(hip, 1, 6);
      grids3.add(noEtip, 0, 0,2,1);
     
      
      hbrb.setSpacing(10);
      
      rbH.setOnAction(new rbHAction());
      rbS.setOnAction(new rbSAction());
     
      
      //------------------------------------------s4
      
      grids4.setAlignment(Pos.CENTER);
      grids4.setHgap(10);
      grids4.setVgap(10);
      grids4.setPadding(new Insets(25, 25, 25, 25));
       
      textBox.getChildren().add(0, hipSearch);
      textBox.getChildren().add(1,searchName);
      grids4.add(textBox,0,1);
      searchbox.getChildren().add(searchbt);
      
      searchbox.setAlignment(Pos.BOTTOM_RIGHT);
      firebox.getChildren().add(firebt);
      grids4.add(searchbox, 1, 1);
      grids4.add(firebt,0,3);
      firebox.setAlignment(Pos.BOTTOM_LEFT);
      eName.setPrefHeight(3);
      eName.setPrefWidth(30);
      nameChange.getChildren().add(0,oname);
      nameChange.getChildren().add(1,eName);
      nameChange.getChildren().add(2,downArrow1);
      nameChange.getChildren().add(3,nName);
      nameChange.getChildren().add(4,eNameTo);
      grids4.add(nameChange, 0, 2);
      eSalary.setPrefHeight(3);
      eSalary.setPrefWidth(30);
      salaryChange.getChildren().add(0,osalary);
      salaryChange.getChildren().add(1,eSalary);
      salaryChange.getChildren().add(2,downArrow2);
      salaryChange.getChildren().add(3,nSalary);
      salaryChange.getChildren().add(4,eSalaryTo);
      grids4.add (salaryChange,1,2);
      okbox.getChildren().addAll(okbt,returnbt1) ;   
      okbox.setAlignment(Pos.BOTTOM_CENTER);
       grids4.add(okbox, 1, 3);
       grids4.add(tipSearch, 0, 4);
       
       searchbt.setOnAction(new searchNameAction());
       firebt.setOnAction(new fireAction());
      okbt.setOnAction(new okAction());
      returnbt1.setOnAction(new returnAction());
       
       
       //------------------------------------s5
      
       grids5.setAlignment(Pos.CENTER);
       grids5.setHgap(10);
       grids5.setVgap(10);
       grids5.setPadding(new Insets(25, 25, 25, 25));
        
       grids5.add(scrollPane2, 0,0);
       grids5.add(paybtbox, 0, 1);
       paybtbox.getChildren().addAll(paybt,returnbt);
       paybtbox.setAlignment(Pos.BOTTOM_RIGHT);
       
       payrollta.setEditable(false);
       paybt.setOnAction(new paybtAction());
       returnbt.setOnAction(new returnAction());
       
       //---------------------------------------s6
       borders6.setTop(edata);
       
       borders6.setPadding(new Insets(50, 50, 150, 50));
       borders6.setLeft(quitbt);
       quitbt.setOnAction(new quitAction());
       borders6.setRight(exitbt);
       exitbt.setOnAction(new exitAction());
       borders6.setBottom(quittx);
       
      
 }	
  
 class createAction implements EventHandler<ActionEvent> {

 	  	@Override
 	  	public void handle(ActionEvent a) {
 	  	
 	  	int flag=0;
 try {
	 
	 if(!createpw)
	   {
 	  			for(int i=0;i<(int)list.size();i++)
 	       	{
 	  			if(list.get(i).loginName.equals(userTextField1.getText()))
 	  		    {  
 	  			  flag++;
 	  		      
 	  		      if(pass1.getText().equals("") && pass2.getText().equals(""))
 	  		      {
 	  		    	tipFortrue.setText("No empty password!"); 
 	  		      }
 	  		      else 
 	  		      {
 	  		    	
 	  		    	 list.get(i).setPassword(getNewPassword(pass1.getText(),pass2.getText()));
 	  		         
 	  		         break;
 	  		      }
				  
 	  		    
 	  		    }
 	  				
 	  		}  if(flag==0)
 	  			{ tipFortrue.setText("Employee no found!");
 	  		      userTextField1.setText("");
 	  		      pass1.setText("");
 	  		      pass2.setText("");
 	  			}
	   }
	 else
		 tipFortrue.setText("Please login!");
 	  		   
 	  		} catch (NoSuchAlgorithmException e) {
				System.out.print("Yes!");
				e.printStackTrace();
			}
 	  	 	  		
 	  		
 	  		
 	  	}
 }
 class login1Action implements EventHandler<ActionEvent> {

	  	@Override
	  	public void handle(ActionEvent a) {
	  	
	  		int flag=0;
	  		
	  		
	  		quittx.setText("");
	 if(createpw) 
	 {
	  	if(yesPass)
		{
			if(list.get(0).loginName.equals(userTextField1.getText() ) )
				
			{	
				
				  st.setScene(s2);
				
				  for(EmployeeG p :list)
				  {
					taNote.appendText(p.toString()+"\n");
				  }
				
			  
			}
			else
			{
				
				for(int i=0;i<(int)list.size();i++)
				{
					if(list.get(i).loginName.equals(userTextField1.getText() ))
					{	flag++;
						 st.setScene(s6);
					     edata.appendText(list.get(i).toString());
					     break;
					   
					}
					
					
				}
				  if(flag==0)
				  {	
					  tipFortrue.setText("Employee no found!");
					   userTextField1.setText("");
					
				  }
					
				
			}
			
		}else
			tipFortrue.setText("Password incorrect!");
	 }
	 else
	  	{
		 tipFortrue.setText("Create password first!");	
	  	}
  }
}	
 
 
  class enterEaction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent a)
 {
			 
			 st.setScene(s3);
			 noEtip.setText("");
			   logNamef.setText("");
			    reNamef.setText("");
			    salaryf.setText("");
			 
			 		
 }
	  
	  
 }     
 
  class changeAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a)
	{ 
		st.setScene(s4);
		searchName.setText("");
		eName.setText("");
		eSalary.setText("");
		eNameTo.setText("");
		eSalaryTo.setText("");
		eNameTo.setEditable(true);
		eSalaryTo.setEditable(true);
		eName.setEditable(false);		
		eSalary.setEditable(false);
	}  
		  
 } 
  
  class terminateAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			st.setScene(s4);
			searchName.setText("");
			eName.setText("");
			eSalary.setText("");
			eNameTo.setText("");
			eSalaryTo.setText("");
			
			eNameTo.setEditable(false);
			eName.setEditable(false);
			eSalaryTo.setEditable(false);
			eSalary.setEditable(false);
			
		}
		  
		  
	  } 
  class payAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {

           st.setScene(s5);
			
		}
		  
		  
	  } 
  class exitAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			
			try {
				fileObjectOutput();
				fileObjectOutput_Quit();
			} catch (FileNotFoundException e) {e.printStackTrace();}
			 catch (IOException e) {e.printStackTrace();}
	         
			System.exit(0);
			
		}
		  
		  
	  } 
  class rbHAction implements EventHandler<ActionEvent> {

	  
	  
		@Override
		public void handle(ActionEvent a) {
			 String lName ;String eName; double salarys;
			  
				lName = logNamef.getText();				
				
				eName = reNamef.getText(); 		
				 		
			    salarys = Double.parseDouble(salaryf.getText());
			    
			    
			
				EmployeeG pHourly= new Hourly(lName,salarys,eName);
			    list.add(pHourly);
		
			   
			    // update list
			    
				if(list.size()==1)
				   st.setScene(s1);
			   else
			       {  st.setScene(s2);
					 taNote.appendText(pHourly.toString()+"\n");
			       }
			rbH.setSelected(false);
					 try {
							fileObjectOutput();
							
						} catch (FileNotFoundException e) {
							
							e.printStackTrace();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
			
			
			
		}
		  
		  
	  }  
  class rbSAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			 String lName ;String eName; double salarys;
			  
				lName = logNamef.getText();				
				
				eName = reNamef.getText(); 		
				 		
			    salarys = Double.parseDouble(salaryf.getText());
			    
			
				EmployeeG pSalaried= new Salaried(lName,salarys,eName);
			    list.add(pSalaried);
			    
			   
			    
			    // update list;
			    if(list.size()==1)
					 st.setScene(s1);  
				else
				{ 
					st.setScene(s2);
			      taNote.appendText(pSalaried.toString()+"\n");
				}
			    rbS.setSelected(false);
					 try {
							fileObjectOutput();
							
						} catch (FileNotFoundException e) {
							
							e.printStackTrace();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
	
				
		}
		  
		  
	  }   
  
  class searchNameAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			
			int flag=0;
			tipSearch.setText("");
			
			for(int i=0;i<(int)list.size();i++)
			{
				if(list.get(i).employeeName.contains(searchName.getText()))
				{   flag++;
					eName.setText(list.get(i).employeeName);
					double s = list.get(i).salary;
					String str_s = String.valueOf(s);
					eSalary.setText(str_s);
					eName.setEditable(false);
					eSalary.setEditable(false);
					search = true;
					break;
				}
				
					
				
			}
			if(flag==0)
			tipSearch.setText("Employee no found!");
		}
		  
		  
	  } 
  class fireAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			int flag=0;
			
			tipSearch.setText("");
			if(!search)
			{	 
			   tipSearch.setText("Please search first!");
			
			}
			else
	    	{		
			
			   for(int i=0;i<(int)list.size();i++)
			      {
					if(list.get(i).employeeName.contains(searchName.getText()))
		  		     {
					  flag++;
					  listQuit.add(list.get(i));		  	
		  		      list.remove(list.get(i));
		  		      st.setScene(s2);
					
					  taNote.setText("Employee List: \n\n");
					  for(EmployeeG p :list)
					  {
						taNote.appendText(p.toString()+"\n");
					  }
									
					  try {
						fileObjectOutput();
						fileObjectOutput_Quit();
					 } catch (FileNotFoundException e) {e.printStackTrace();} 
					  catch (IOException e) {e.printStackTrace();}		  		      
		  		      break;
		  		    }	
				
			 }
			    if(flag==0)
				tipSearch.setText("Employee no Found!");
		  }
              
			
			
		}
		  
		  
	  } 

  class okAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			tipSearch.setText("");
			
			if(!search)
				st.setScene(s2);
			
			for(int i=0;i<(int)list.size();i++)
			{
				if(list.get(i).employeeName.contains(searchName.getText()))
				{
					list.get(i).setEmployeeName(eNameTo.getText());
					list.get(i).seEmployeeSalary(Double.parseDouble(eSalaryTo.getText()));
				    break;
				
				}
			}
			
			st.setScene(s2);
			
			taNote.setText("Employee List: \n\n");
			for(EmployeeG p :list)
			{
				taNote.appendText(p.toString()+"\n");
			}
			  try {
				fileObjectOutput();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		  
		  
	  } 
  class paybtAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			
			for(EmployeeG p:list)
			{
			   
			   System.out.println("\n"+ p.getEmployeeName() + " ");
				p.setPay();
			}
			
             
                 payrollta.setText("\n\t***Payroll Report***\n");
         		
                 payrollta.appendText("  Pay"+"\t "  +"EmployeeID  "+"\t"+ "EmployeeName");
                 payrollta.appendText("\n---------------------------------------");
                for(EmployeeG p:list)
               {
                	payrollta.appendText("\n"+ dec.format(p.getPay())+"      " + p.getEmployeeID()+"       "+ p.getEmployeeName());
               }
			
			  try {
				fileObjectOutput();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		  
		  
	  } 
  
  class returnAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a)
	{  tipSearch.setText("");
	   payrollta.setText("\n    Payroll Report : \n");
		st.setScene(s2);
		
	}  
		  
} 

  class quitAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent a) {
			for(int i=0;i<(int)list.size();i++)
  	  		{
  	  			if(userTextField1.getText().equals( list.get(i).loginName))
  	  			{
				   listQuit.add(list.get(i));
				  
				   quittx.setText(list.get(i).employeeName + " , you quit!");
				   list.remove(list.get(i));
  	  			}
  	  			
  	  			  	  			
  	  		}
			 try {
					fileObjectOutput();
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			// System.exit(0);
			
		}
		  
		  
	  } 
 public static byte[] getNewPassword(String inputPass1, String inputPass2) throws NoSuchAlgorithmException
 {   
	 MessageDigest md1,md2;
	 
     byte byteData1[],byteData2[]; 


	 md1 = MessageDigest.getInstance("SHA-256");
	 md1.update(inputPass1.getBytes());
	 
	 byteData1 = md1.digest();
	 
	 StringBuffer HashPassW1 = new StringBuffer();
	 
	 for(int i=0;i<byteData1.length;i++)
	 {
		 HashPassW1.append(Integer.toString((byteData1[i] & 0xff) + 0x100, 16).substring(1));
	 }
	 String str_has1 = HashPassW1.toString();
	 System.out.println(str_has1);
		 
	  md2 = MessageDigest.getInstance("SHA-256");
	 md2.update(inputPass2.getBytes());
	 
	 byteData2 = md2.digest();
	 
	 StringBuffer HashPassW2 = new StringBuffer();
	 
	 for(int i=0;i<byteData2.length;i++)
	 {
		 HashPassW2.append(Integer.toString((byteData2[i] & 0xff) + 0x100, 16).substring(1));
	 }
	 

	 String str_has2 = HashPassW2.toString();
	System.out.println(str_has2);
	
	 if(str_has1.equals(str_has2))
	 {
		 	    
		 tipFortrue.setText("Password set done!");
		 yesPass = true;
		 createpw = true;
		 
	 }
	 else {
	 tipFortrue.setText("No match,try again!");
	 createpw = false;
	  pass1.setText("");
	  pass2.setText("");
	 }
	 return byteData2;
	
    }
 

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
			for(EmployeeG p:list)
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
				EmployeeG pHourly= new Hourly(inputName,salarys,eName);
			    list.add(pHourly);
			}		
			else if(hORs=='s')
			{
				EmployeeG pSalaried= new Salaried(inputName,salarys,eName);
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
	    	
	    	for (EmployeeG p :list)
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
  
  	for (EmployeeG p: list)
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
    for(EmployeeG p:list)
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

public void doMenu() throws FileNotFoundException,IOException,ClassNotFoundException, ParseException, NoSuchAlgorithmException
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
    	     for(EmployeeG p:list)
    	     System.out.println(p);
    	     System.out.println("\n**Quited Employee list**");
    	     if(listQuit.size()==0)
    	     {	 System.out.println("Empty list!");
    	        
    	     }
    	    else 
    	     {     System.out.println(listQuit.size());
    	    	   for(EmployeeG p:listQuit)
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
		for(EmployeeG p:list)
		{//System.out.println(p.getClass());
		   
		   System.out.println("\n"+ p.getEmployeeName() + " ");
			p.setPay();
		}
		System.out.println("\n\t***Payroll Report***\n");
		
		System.out.format("  Pay "+" \t"  +"  EmployeeID  "+"\t"+ "EmployeeName%n");
		System.out.print("\n---------------------------------------");
       for(EmployeeG p:list)
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
			for(EmployeeG p:list)
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
public void doLogin(ArrayList<EmployeeG> list) throws FileNotFoundException,IOException,ClassNotFoundException, ParseException, NoSuchAlgorithmException
{           
    	
    	   
    	
    		System.out.println("Please enter your loginName: \n");
    		int flag=0;
    	    lName = input.next();
    	    
    	    String input1=input.nextLine();
    	    String input2 = input.nextLine();
    	    for(int i=0;i<list.size();i++)
    	  {
    	    if(list.get(i).loginName.contains(lName))
    	    {
    	        flag++;
    	        if(list.get(0).loginName.contains(lName))
    	        {	
    	        
    	        list.get(0).setPassword(getNewPassword(input1,input2));
    	        System.out.println("Welcome, Boss! Your record found as following : \n");    	        
    	        System.out.println(list.get(0).toString());
    	        System.out.println("Please do other choice!");
    	        }
    	        else 
    	        {
    	        System.out.println("Would you like to reset your password? y/n\n");
    	        char yORn;
    	        yORn = input.next().charAt(0);
    	        if(yORn=='y')
    	        list.get(i).setPassword(getNewPassword(input1,input2));
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
    	 for(EmployeeG p:list)
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
	   
	ArrayList<EmployeeG> alist=(ArrayList<EmployeeG>)oi.readObject();
	
	Iterator<EmployeeG> iter = alist.iterator();
	while(iter.hasNext())
	{   for(EmployeeG pNew: alist)
	   {
		if(pNew.getClass().equals(Hourly.class))
		{  // System.out.println(pNew.getClass());
			pNew=new Hourly( (EmployeeG)iter.next());
			
			list.add(pNew);
			//System.out.println(list);
		}
		else
		{   //System.out.println(pNew.getClass());
			pNew=new Salaried((EmployeeG)iter.next());
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
	   
	ArrayList<EmployeeG> qlist=(ArrayList<EmployeeG>)oi.readObject();
	
	Iterator<EmployeeG> iter = qlist.iterator();
	while(iter.hasNext())
	{   for(EmployeeG pNew: qlist)
	   {
		if(pNew.getClass().equals(Hourly.class))
		{  // System.out.println(pNew.getClass());
			pNew=new Hourly( (EmployeeG)iter.next());
			
			listQuit.add(pNew);
			//System.out.println(list);
		}
		else
		{   //System.out.println(pNew.getClass());
			pNew=new Salaried((EmployeeG)iter.next());
			listQuit.add(pNew);
		}
		
	   }
			
	}
	 	   
	} finally {oi.close();}

	
}

/*
public static void main(String[] args) throws FileNotFoundException,ClassNotFoundException, IOException, ParseException, NoSuchAlgorithmException
{
    	System.out.println("P6: Payroll+Password\n "+
                           "Author: Chenghong Meng\n");
    try {	
    	
    	String lN = new String ();
    	String eN =new String();
    	int id =0;
    	double s=3000.0;
    	Date creation = new Date();
    	
    	
     	PayrollG pr = new PayrollG(lN, s,creation,id, eN);
     	
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
		EmployeeG pSalaried= new Salaried(lName,salarys,eName);
	    list.add(pSalaried);
		System.out.println(list +"\n");	
		System.out.println("\nNow you are the Boss!Please log in!");
		
		pr.doMenu();
     	}
     	else
    	pr.doMenu();
        
       
    	
 }catch(IOException ioe) {System.out.println("Error!");ioe.printStackTrace();}
}
*/
public static void main(String[] args)
{
 Application.launch(args);
 }




}




























