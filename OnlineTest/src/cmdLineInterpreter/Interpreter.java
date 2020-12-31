package cmdLineInterpreter;

import java.util.Scanner;

import onlineTest.SystemManager;

/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 *Add a student
Add an exam
Add a true/false question
Answer a true/false question
Get the exam score for a student
 */
public class Interpreter {

	public static void main(String[] args) {
		SystemManager manager = new SystemManager();
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		
		String menu = "";
		while (true) {
			menu += "Enter 1 to Add a student\n";
			menu += "Enter 2 to Add an exam\n";
			menu += "Enter 3 to Add a true/false question\n";
			menu += "Enter 4 to Answer a true/false question\n";
			menu += "Enter 5 to Get the exam score for a student\n";
			menu += "Enter anything else to exit\n";
			
			System.out.println(menu);
			int choice = sc.nextInt();
			String input = sc.nextLine();
			int examId, questionNumber;
		    double points, grade;
			String text, name;
			boolean answer;
			
			if (choice == 1) {
				System.out.println("Enter the student name:");
				input = sc.nextLine();
				manager.addStudent(input);
			}
			else if (choice == 2) {
				System.out.println("Enter the Exam Id:");
				input = sc.nextLine();
				examId = Integer.parseInt(input);
				System.out.println("Enter the Exam Title:");
				input = sc.nextLine();
				manager.addExam(examId, input);
			}
			else if (choice == 3) {
				System.out.println("Enter the exam id you want to add a question:");
				input = sc.nextLine();
				examId = Integer.parseInt(input);
				System.out.println("Enter the question number:");
				input = sc.nextLine();
				questionNumber = Integer.parseInt(input);
				System.out.println("Enter the question:");
				text = sc.nextLine();
				System.out.println("Enter the points:");
				input = sc.nextLine();
				points = Double.valueOf(input);
				System.out.println("Enter the answer:");
				input = sc.nextLine();
				answer = Boolean.valueOf(input);
				manager.addTrueFalseQuestion(examId, questionNumber, text, points, answer);
			}
			else if (choice == 4) {
				System.out.println("Enter the student name:");
				name = sc.nextLine();
				System.out.println("Enter the exam Id:");
				input = sc.nextLine();
				examId = Integer.parseInt(input);
				System.out.println("Enter the question number:");
				input = sc.nextLine();
				questionNumber = Integer.parseInt(input);
				System.out.println("Enter the question answer:");
				input = sc.nextLine();
				answer = Boolean.valueOf(input);
				manager.answerTrueFalseQuestion(name, examId, questionNumber, answer);
			}
			else if (choice == 5) {
				System.out.println("Enter the student name:");
				name = sc.nextLine();
				System.out.println("Enter the exam Id:");
				input = sc.nextLine();
				examId = Integer.parseInt(input);
				grade = manager.getExamScore(name, examId);
				System.out.println("[Student name: " + name + " Exam: " + examId);
			}
			else {
				System.out.println("Goodbye");
				sc.close();
				break;
			}
		}

		
		
	}
}
