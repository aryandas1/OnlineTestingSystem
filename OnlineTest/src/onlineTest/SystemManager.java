package onlineTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashMap;

public class SystemManager implements Manager, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Exam> exams;
	private HashMap<String, Student> students;
	private String[] letterGrades;
	private double[] cutoffs;
	public SystemManager() {
		exams = new HashMap<>();
		students = new HashMap<>();
		letterGrades = null;
		cutoffs = null;
	}
	@Override
	public boolean addExam(int examId, String title) {
		Exam e = new Exam(title);
		if (e.getTitle().equals(title)) {
			exams.put(examId, e);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		exams.get(examId).addQuestionTF(questionNumber, text, points, answer);
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		exams.get(examId).addQuestionMC(questionNumber, text, points, answer);
		
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		exams.get(examId).addQuestionFill(questionNumber, text, points, answer);
		
	}

	@Override
	public String getKey(int examId) {
		String str = "";
		
		if (exams.containsKey(examId)) {
			str += exams.get(examId).getKey();
		}
		
		return str;
	}

	@Override
	public boolean addStudent(String name) {
		Student s = new Student(name);
		if (s.getName().equals(name)) {
			students.put(name, s);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		students.get(studentName).answerQuestionTF(examId, questionNumber, answer);
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		students.get(studentName).answerQuestionMC(examId, questionNumber, answer);
		
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		students.get(studentName).answerQuestionFill(examId, questionNumber, answer);
		
	}

	@Override
	public double getExamScore(String studentName, int examId) {
		double examScore = 0;
		ArrayList<String> answers = null;
		
		Set<Integer> qNumSet = exams.get(examId).getqMap().keySet();
		
		for (int i : qNumSet) {
			answers = students.get(studentName).getStudentAnswers().get(examId).get(i);
			examScore += exams.get(examId).getqMap().get(i).getScore(answers);
		}
		
		return examScore;
	}

	@Override
	public String getGradingReport(String studentName, int examId) {
		String gradingReport = "";
		ArrayList<String> answers = null;
		double questionScore = 0;
		double totalQuestionPoints = 0;
		double totalExamPoints = 0;
		double score = 0;
		
		Set<Integer> qNumSet = exams.get(examId).getqMap().keySet();
		
		for (int i : qNumSet) {
			totalQuestionPoints = exams.get(examId).getqMap().get(i).getPoints();
			totalExamPoints += totalQuestionPoints;
			
			gradingReport += "Question #" + i + " ";
			
			answers = students.get(studentName).getStudentAnswers().get(examId).get(i);
			questionScore = exams.get(examId).getqMap().get(i).getScore(answers);
			
			gradingReport += questionScore;
			gradingReport += " points out of " + totalQuestionPoints + "\n";
			
			score += questionScore;
		}
		
		gradingReport += "Final Score: ";
		gradingReport += score + " out of " + totalExamPoints;
		
		return gradingReport;
	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		//can just make instacne fields 
		this.letterGrades = letterGrades;
		this.cutoffs = cutoffs;
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		double grade = 0;
		double totalScore = 0;
		double examCount = 0;
		double courseNumericGrade = 0;
		Set<Integer> examNum = students.get(studentName).getStudentAnswers().keySet();
		ArrayList<Double> examScores = new ArrayList<Double>();
		
		for (int i : examNum) { //i = each examId
			totalScore = exams.get(i).getScore();
			grade = this.getExamScore(studentName, i);
			examScores.add(grade / totalScore);
			examCount++;
		}
		
		double sum = 0;
		for (double d : examScores) {
			sum += d;
		}
		
		courseNumericGrade = (sum / examCount) * 100;
		return courseNumericGrade;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		String letterGrade = "";
		for (int i = 0; i < cutoffs.length; i++) {
			if (getCourseNumericGrade(studentName) >= cutoffs[i]) {
				letterGrade = letterGrades[i];
				break;
			}
		}
		return letterGrade;
	}

	@Override
	public String getCourseGrades() {
		String courseGrades = "";
		
		ArrayList<String> grades = new ArrayList<String>();
		Set<String> names = students.keySet();
		
		for (String s : names) {
			grades.add(s + " " + getCourseNumericGrade(s) + " " + getCourseLetterGrade(s));
		}
		
		Collections.sort(grades);
		for (String s : grades) {
			courseGrades += s + "\n";
		}
		
		return courseGrades;
	}

	@Override
	public double getMaxScore(int examId) {
		double maxScore = Integer.MIN_VALUE;
		Set<String> names = students.keySet();
		ArrayList<Double> numericGrades = new ArrayList<Double>();
		
		for (String s : names) {
			numericGrades.add(this.getExamScore(s, examId));
		}
		
		for (double d : numericGrades) {
			if (d > maxScore) {
				maxScore = d;
			}
		}
		
		return maxScore;
	}

	@Override
	public double getMinScore(int examId) {
		double minScore = Integer.MAX_VALUE;
		Set<String> names = students.keySet();
		ArrayList<Double> numericGrades = new ArrayList<Double>();
		
		for (String s : names) {
			numericGrades.add(this.getExamScore(s, examId));
		}
		
		for (double d : numericGrades) {
			if (d < minScore) {
				minScore = d;
			}
		}
		
		return minScore;
	}

	@Override
	public double getAverageScore(int examId) {
		Set<String> names = students.keySet();
		ArrayList<Double> numericGrades = new ArrayList<Double>();
		double count = names.size();
		double avg = 0;
		double sum = 0;
		
		for (String s : names) {
			numericGrades.add(this.getExamScore(s, examId));
		}
		
		for (double d : numericGrades) {
			sum += d;
		}
		
		avg = sum / count;
		return avg;
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		File file = new File (fileName);
		
		ObjectOutputStream output;
		try {
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(manager);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Manager restoreManager(String fileName) {
		File file = new File (fileName);
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			SystemManager manager = (SystemManager) input.readObject();
			input.close();
			return manager;
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
