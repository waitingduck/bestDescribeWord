package brightedge_assignment;

import java.util.List;
import java.util.Map;

public interface prioritizeProcess {
	public void setExtraScore(double score);
	public void prioritize(Map<String,Double> map, List<String> list);
}
