package onlineTest;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionFill extends Question{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] answer;
	public QuestionFill(String text, double points, String[] answer) {
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
		
		for (String s : answer) {
			for (int i = 0; i < this.answer.length; i++) {
				if (s.equals(this.answer[i])) {
					score += points / this.answer.length;
				}
			}
		}
		
		return score;
	}

}
