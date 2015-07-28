package brightedge_assignment;

public class casePreprocess implements contentProcess{
	//transform all character into lower case
	@Override
	public String preprocess(String text) {
		return text.toLowerCase();
	}

}
