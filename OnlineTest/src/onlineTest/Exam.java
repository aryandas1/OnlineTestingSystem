package onlineTest;

import java.io.Serializable;
import java.util.Set;
import java.util.HashMap;

public class Exam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private double score;
	private HashMap <Integer, Question> qMap;
	
	//creates a new exam with a name
	public Exam (String title) {
		qMap = new HashMap<Integer, Question>();
		this.title = title;
	}
	
	public void addQuestionTF(int questionNumber, String text, double points, boolean answer) {
		qMap.put(questionNumber, new QuestionTF(text, points, answer));
		score += points;
	}
	
	public void addQuestionMC(int questionNumber, String text, double points, String[] answer) {
		qMap.put(questionNumber, new QuestionMC(text, points, answer));
		score += points;
	}
	
	public void addQuestionFill(int questionNumber, String text, double points, String[] answer) {
		qMap.put(questionNumber, new QuestionFill(text, points, answer));
		score += points;
	}
	
	public String getKey() {
		String str = "";
		Set<Integer> qNumSet = qMap.keySet();
		
		for (int i : qNumSet) {
			Question q = qMap.get(i);
			str += q.getKey();
		}
		
		return str;
	}
	public String getTitle() {
		return title;
	}

	public HashMap<Integer, Question> getqMap() {
		return qMap;
	}
	
	public double getScore() {
		return score;
	}
}
