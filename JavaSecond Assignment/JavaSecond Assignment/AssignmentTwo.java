 

import java.io.File;
import java.util.*;
import java.util.Scanner;
import java.util.Collections;


public class AssignmentTwo
{
   private ArrayList<AssignmentTwo> list;
   private String fullName;
   private int id;
   private double a1,a2,a3;




   public AssignmentTwo()
   {

   }
   public AssignmentTwo(String lastName, String firstName,int id, double a1, double a2, double a3)
   {
       this.fullName = firstName + " " + lastName;
       this.id = id;
       this.a1 = a1;
       this.a2 = a2;
       this.a3 = a3;
   }

   public static void main(String[] args) throws Exception {
       AssignmentTwo at = new AssignmentTwo();
       at.mainMenu();
   }

   public void mainMenu() throws Exception {
       list = fileRead();
       Scanner option = new Scanner(System.in);
       System.out.println("\nSelect the menu"+
               "\n 1. Enter 1 to display student information and assignment marks"+
               "\n 2. Enter 2 to display total marks of all students assignment"+
               "\n 3. Enter 3 to display the list of students with the total marks less than certain threshold"+
               "\n 4. Enter 4 to display the 10 highest and the 10 lowest Student marks"+
               "\n 5. Enter 0 to exit the menu\n"
       );
       int optionId;
       optionId = option.nextInt();
       switch(optionId){
           case 1:
               System.out.println("Student data and marks");
               for(AssignmentTwo l:list){
                   System.out.println(l.toString());
               }
               mainMenu();
               break;
           case 2:
               System.out.println("Student details, Marks and Total Marks");
               for(AssignmentTwo l:list){
                   System.out.println(l.toString2());
               }
               mainMenu();
               break;
           case 3:
               System.out.println("Student details, Marks and Total Marks for whom less than provided threshold");
               float th;
               th = option.nextInt();
               for(AssignmentTwo l:list){
                   String s = l.toString3(th);
                   if(s != null)
                       System.out.println(s);
               }
               mainMenu();
               break;
           case 4:
               System.out.println("Top Ten Lowest Marks");
               ArrayList<Double> higestMarks = new ArrayList<>();
               for(AssignmentTwo l:list){
                   higestMarks.add(l.getTotalMarks());
               }
               Collections.sort(higestMarks);
               for(int i = 0; i < 10; i++){
                   System.out.println(higestMarks.get(i));
               }

               System.out.println("Top Ten Highest Marks");

               for(int i = higestMarks.size() - 1; i > higestMarks.size()-11; i--){
                   System.out.println(higestMarks.get(i));
               }
               break;
       }


   }
   public static ArrayList<AssignmentTwo> fileRead() throws Exception  {
       ArrayList<AssignmentTwo> list = new ArrayList<AssignmentTwo>();
       Scanner file = new Scanner(new File("C:/Users/jason/OneDrive/Desktop/assessmentTwo/xyz.csv"));
       file.useDelimiter(",");
       String unitName;

       while (file.hasNextLine()){
           String line = file.nextLine();
           String[] data;
           String initialLine;

           if (line.contains("Unit")) {
               initialLine = line;
           } else if (!line.contains("First Name")){
               data = line.split(",");

               if (data.length == 4){
                   AssignmentTwo A1 = new AssignmentTwo(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),0.0,0.0);
                   list.add(A1);
               } else if (data.length == 5) {
                   list.add(new AssignmentTwo(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]),0.0));
               } else if(data.length == 3){
                   list.add(new AssignmentTwo(data[0],data[1],Integer.parseInt(data[2]),0.0,0.0,0.0));
               }else {
                   String a1 = data[3];
                   String a2 = data[4];
                   String a3 = data[5];
                   if (data[3].isEmpty()){
                       a1 = "0.0";
                   }
                   if (data[4].isEmpty()){
                       a2 = "0.0";
                   }
                   if (data[5].isEmpty()){
                       a3 = "0.0";
                   }
                   list.add(new AssignmentTwo(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(a1),Double.parseDouble(a2),Double.parseDouble(a3)));

               }
           }
       }
       file.close();
       return list;

   }

   public String toString() {
       return String.format("id:%d, FullName: %s, A1:%.2f,A2:%.2f,A3:%.2f", id, fullName, a1,a2,a3);


   }
   public String toString2() {
       double totalMarks = getTotalMarks();
       return String.format("id:%d, FullName: %s, A1:%.2f,A2:%.2f,A3:%.2f,total:%.2f", id, fullName, a1,a2,a3,totalMarks);


   }

   public String toString3(float th) {
       double totalMarks = getTotalMarks();
       if(totalMarks<th){
           return String.format("id:%d, FullName: %s, A1:%.2f,A2:%.2f,A3:%.2f,total:%.2f", id, fullName, a1,a2,a3,totalMarks);
       }else
           return null;

   }


   public double getTotalMarks(){
       double total = a1+a2+a3;
       return total;
   }







}

