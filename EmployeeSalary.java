
/**
 * Programme description : To calculate the salary of the employee
 * Programmer : MMM
 * Date : 14 March 2024
 */

import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class EmployeeSalary
{
   public static void main(String[]args) throws IOException
    {
        DecimalFormat dF = new DecimalFormat("0.00");
        try
        {
            //input files
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));
            //output files
            PrintWriter fileoutput1 = new PrintWriter(new FileWriter("employeeData.txt"));
            
            
            //Declaration for all the variables
            String inputData = null;
            String employname = "";
            double employSalary = 0.00;
            int emploWorkingYear = 0;
            
            //variables for top performing employee
            String top_employname = "";
            double top_employSalary = 0.00;
            int top_emploWorkingYear = 0;
            
            //variables for newest employee
            String latest_employname = "";
            double latest_employSalary = 0.00;
            int latest_emploWorkingYear = 0;
            
            //Title header
            fileoutput1.println("----------------List of Employees-----------------------");
            
            while((inputData = inputFile.readLine()) != null)
            {
                StringTokenizer strT = new StringTokenizer(inputData,"|");
                employname= strT.nextToken();
                employSalary = Double.parseDouble(strT.nextToken());
                emploWorkingYear = Integer.parseInt(strT.nextToken());
                
                
                double annualSalary = employSalary + (employSalary * 0.05);
                
                //for the negative number
                if(employSalary < 0 || emploWorkingYear <0)
                  throw new IllegalArgumentException();
                
                //top performing employee
                if(annualSalary > top_employSalary){
                    top_employname = employname;
                    top_employSalary = annualSalary;
                    top_emploWorkingYear = emploWorkingYear;
                }
                //employee with the least years of service
                if(latest_emploWorkingYear == 0 || emploWorkingYear < latest_emploWorkingYear){
                    latest_employname = employname;
                    latest_employSalary = annualSalary;
                    latest_emploWorkingYear = emploWorkingYear;
                    
                }
                
                //store the list of employees
                String employData = employname+"\t\t RM "+annualSalary+"\t\t "+emploWorkingYear+" years";
                fileoutput1.println(employData);
    
            }
            //top performing employee
            fileoutput1.println("\n\n -------- Top Performing Employee Details -------");
            String top_employData = top_employname+"\t\t RM "+top_employSalary+"\t\t "+top_emploWorkingYear+" years";
            fileoutput1.println(top_employData);
            //display the top performing employee
            JOptionPane.showMessageDialog(null,"--------Top performing Employee Details ---------\n"+top_employData);
            
            //latest employee
            fileoutput1.println("\n\n -------- Details of Employee with the least years of service -------");
            String latest_employData = latest_employname+"\t\t RM "+latest_employSalary+"\t\t "+latest_emploWorkingYear+" years";
            fileoutput1.println(latest_employData);
            JOptionPane.showMessageDialog(null,"--------Details of Employee with the least years of service ---------\n"+latest_employData);

            //close all files
            inputFile.close();
            fileoutput1.close();
            
        }
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input!";
            JOptionPane.showMessageDialog(null, output);
        }
    }
}