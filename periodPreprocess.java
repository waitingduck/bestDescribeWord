package brightedge_assignment;

public class periodPreprocess implements contentProcess{

	//To preprocess period. Replace period that come with a space to space. For example, “time. “ 
	//will become “time  ”,”Amazon.com” will still be “Amazon.com”
	@Override
	public String preprocess(String text) {
		StringBuilder sb = new StringBuilder(text);
		for(int i=0;i<sb.length();i++){
			if(sb.charAt(i)=='.' && ((i==sb.length()-1) || (i!=sb.length()-1 && sb.charAt(i+1)==' '))){
				sb.setCharAt(i, ' ');
			}
		}
		return sb.toString();
	}
	
}
