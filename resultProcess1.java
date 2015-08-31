import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;


public class ResultProcess1{

	private double threshold;
	
	public ResultProcess1(){
		this(0.0);
	}
	public ResultProcess1(double threshold){
		this.threshold = threshold;
	}
	
	
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public void getResult(HashMap<String, Double> word_score,JTextArea resultText, JTextArea topText) {
		
		allResult(resultText, word_score);
		top3Result(topText, word_score);	
	}
	
	private void allResult(JTextArea resultText, HashMap<String, Double> word_score){
		double max = 0;
		double min = Double.MAX_VALUE;
		
		for(String s:word_score.keySet()){
			max = Math.max(max, word_score.get(s));
			min = Math.min(min, word_score.get(s));
		}
		double thresholdScore = ((max-min)*(1-threshold))+min;
		for(String s:word_score.keySet()){
			if(word_score.get(s)>=thresholdScore){
				resultText.append(s + "\n");
			}
		}
	}
	
	private void top3Result(JTextArea topText, HashMap<String, Double> word_score){
		String[] topWord = new String[3];
		double[] topScore = new double[3];
		for(String r:word_score.keySet()){
        	if(word_score.get(r)>topScore[0]){
        		topScore[2] = topScore[1];
        		topScore[1] = topScore[0];
        		topScore[0] = word_score.get(r);
        		topWord[2] = topWord[1];
        		topWord[1] = topWord[0];
        		topWord[0] = r;
        	}else if(word_score.get(r)>topScore[1]){
        		topScore[2] = topScore[1];
        		topScore[1] = word_score.get(r);
        		topWord[2] = topWord[1];
        		topWord[1] = r;
        	}else if(word_score.get(r)>topScore[2]){
        		topScore[2] = word_score.get(r);
        		topWord[2] = r;
        	}
        }
		for(int i=0;i<3;i++){
			topText.append("Top " + (i+1) + ":   " + topWord[i] + "    " + topScore[i] + "\n");
		}
	}
	
}
