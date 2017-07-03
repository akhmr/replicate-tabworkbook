import java.util.ArrayList;
import java.util.List;

public class Worksheet {
	
	private String worksheetName;
	private List<Panes> Lpanes;
	
	Worksheet()
	{
		Lpanes = new ArrayList<Panes>();
	}
	
	public String getWorksheetName() {
		return worksheetName;
	}

	public void setWorksheetName(String worksheet) {
		this.worksheetName = worksheet;
	}

	public List getPanes() {
		return Lpanes;
	}

	public void setPanes(Panes panes) {
//		this.panes = panes;
		Lpanes.add(panes);
	}
}
