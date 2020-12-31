package onlineTest;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionMC extends Question{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] answer;
	public QuestionMC(String text, double points, String[] answer) {
		super(text, points);
		Arrays.sort(answer);
		this.answer = answer;
	}

	@Override
	public String getAnswer() {
		String str = "";
		str += "[";
		
		for (String s : answer) {
			str += s + ",";
		}
		
		str = str.substring(0, str.length() - 1) + "]";
		
		return str;
	}

	@Override
	public double getScore(ArrayList<String> answer) {
		double score = 0;
		if (Arrays.asList(this.answer).equals(answer)) {
			score = points;
		}
		else {
			score = 0;
		}
		
		return score;
	}

}
