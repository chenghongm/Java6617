//ChenghongMeng_Assignment3_Family
import java.util.*;
public class Person { //create an class of Person with two private members;
	private String firstName;
	private int age;
	
     
	Person(String fN,int a){   //constructor;
		
	 firstName = fN;
      age = a;
		
	}
	public String toString(){
        return firstName +" " + age ;
    }
	
	public int getAge() {
		return age;
	}
	public static double computeAverage(int[] ages) {// calculate the average age of the family.
		double averageOfages=0,sum=0 ;
		for(int i=0;i<ages.length;i++) {
			sum=sum+ages[i];
		}
		averageOfages=sum/ages.length;
		return averageOfages;
	}
	public static void main(String args[]) {
	
      Scanner input=new Scanner(System.in);
      System.out.println("Chenghong Meng P3:Family:\n");//output a heading with my name. 
        int n;//define the size of family;
        
		System.out.println("How many people in your family? \n");
		n=input.nextInt();
		input.nextLine();
		String[] name= new String[n];//create an array to initialize each of the name;
		int [] ages=new int[n];//create an array to initialize each of the age;
		Person[] familyArray= new Person[n];//create an array for the Person object.
		for(int i=0;i<n;i++) {
			System.out.println("Please enter one of your family member's firstname: \n");
			name[i]=input.next();
			System.out.println("please enter her/his age: ");
			 ages[i]=input.nextInt();
			input.nextLine();//to get next line;
			Person p= new Person(name[i],ages[i]);//store name and age to Person object.
		   familyArray[i]= p;//build family array;
		   
		}
		input.close();//close input.
		System.out.println("Your family member are: ");
		for(int i=0;i<n;i++) {
			System.out.println(familyArray[i]);//output each family members;
		}
		double average=computeAverage(ages);//to call function.
		System.out.println("the Family average age is: " + average);
	}
}
