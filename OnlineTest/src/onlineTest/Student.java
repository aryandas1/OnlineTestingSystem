package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	//map exam to another map with question-answer pairs
	private HashMap<Integer, HashMap<Integer, ArrayList<String>>> studentAnswers; 
	
	public Student(String name) {
		studentAnswers = new HashMap<Integer, HashMap<Integer, ArrayList<String>>>();
		this.name = name;
	}
	
	public HashMap<Integer, HashMap<Integer, ArrayList<String>>> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(HashMap<Integer, HashMap<Integer, ArrayList<String>>> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	public void answerQuestionTF(int examId, int questionNumber, boolean answer) {
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(String.valueOf(answer));
		addQuestion(examId, questionNumber, answers);
	}
	
	public void answerQuestionMC(int examId, int questionNumber, String[] answer) {
		ArrayList<String> answers = toArrayList(answer);
		addQuestion(examId, questionNumber, answers);
	}	
	
	public void answerQuestionFill(int examId, int questionNumber, String[] answer) {
		ArrayList<String> answers = toArrayList(answer);
		addQuestion(examId, questionNumber, answers);
	}
	
	private ArrayList<String> toArrayList(String[] answer){
		ArrayList<String> list = new ArrayList<String>();
		Arrays.sort(answer);
		for (String s : answer) {
			list.add(s);
		}
		return list;
	}
	
	private void addQuestion(int examId, int qNum, ArrayList<String> answers) {
		if (studentAnswers.containsKey(examId)) {
			studentAnswers.get(examId).put(qNum, answers);
		}
		else {
			HashMap<Integer, ArrayList<String>> questionAnswerMap = new HashMap<Integer, ArrayList<String>>();
			questionAnswerMap.put(qNum, answers);
			studentAnswers.put(examId, questionAnswerMap);
		}
	}
	public String getName() {
		return name;
	}
}
