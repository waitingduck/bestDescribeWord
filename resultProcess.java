package brightedge_assignment;

import java.util.HashMap;
import java.util.List;

public interface resultProcess {
	public void setThreshold(double threshold);
	public List<String> getResult(HashMap<String,Double> map);
}
