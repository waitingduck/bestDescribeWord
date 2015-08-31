
public class CasePreprocess implements ContentPreprocess{
	//transform all character into lower case
	@Override
	public String preprocess(String text) {
		return text.toLowerCase();
	}

}