package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;

abstract public class Question implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String text;
	protected double points;
	
	public Question(String text, double points) {
		this.text = text;
		this.points = points;
	}
	
	public String getText() {
		return text;
	}
	
	public String getKey() {
		String str = "";
		str += "Question Text: " + text;
		str += "\nPoints: " + points;
		str += "\nCorrect Answer: " + getAnswer() + "\n";
		return str;
	}	
	
	public double getPoints() {
		return points;
	}
	
	abstract public String getAnswer();
	abstract public double getScore(ArrayList<String> answer);


	
	
}
