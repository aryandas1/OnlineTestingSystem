package onlineTest;

import java.util.ArrayList;

public class QuestionTF extends Question{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean answer;
	
	public QuestionTF(String text, double points, boolean answer) {
		super(text, points);
		this.answer = answer;
	}
	
	@Override
	public String getAnswer() {
		if (answer) {
			return "True";
		}
		else {
			return "False";
		}
	}
	
	@Override
	public double getScore(ArrayList<String> input) {
		if (input.get(0).equals(String.valueOf(answer))) {
			return points;
		}
		else {
			return 0;
		}
	}
	
}
