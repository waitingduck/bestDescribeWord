package brightedge_assignment;

import java.util.HashSet;
import java.util.Set;

public class specialCharacterPreprocess implements contentProcess{

	//Remove special character.
	@Override
	public String preprocess(String text) {
		Set<Character> set = new HashSet<Character>();
		set.add('(');
		set.add(')');
		set.add(';');
		set.add(':');
		set.add('"');
		set.add('!');
		set.add('^');
		set.add('%');
		set.add('#');
		set.add('@');
		set.add('[');
		set.add(']');
		set.add('{');
		set.add('}');
		set.add('*');
		set.add('~');
		set.add('?');
		set.add(',');
		set.add('<');
		set.add('>');
		set.add('=');
		StringBuilder sb = new StringBuilder(text);
		for(int i=0;i<sb.length();i++){
			if(set.contains(sb.charAt(i))){
				sb.setCharAt(i, ' ');
			}
		}
		return sb.toString();
	}
	
}
