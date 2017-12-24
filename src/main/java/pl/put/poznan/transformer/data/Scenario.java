package pl.put.poznan.transformer.data;

import java.util.ArrayList;
import java.util.List;




public abstract class Scenario {
	private Scenario parent;
	private List<Scenario> childList;
	private String name ;
	private String content;
	private String firstWord;//not implemented 
	private Integer level;
	
	public Scenario(Scenario parent, String name, String content, Integer level ) {
		this.parent = parent;
		childList = new ArrayList<Scenario>();
		this.name = name;
		this.content = content;
		this.level = level;
	}
	
	public void addStep(Scenario newStep) {
		childList.add(newStep);
	}
	

	
	public void removeStep(Integer number) {
		//#todo usuniecie wszystkich dzieci lub przestawienie ich wyzej
		childList.remove(number);
	}
	
	public void removeStep(Scenario scenario) {
		//#todo usuniecie wszystkich dzieci lub przestawienie ich wyzej
		childList.remove(scenario);
	}
	
	public String getName() {
		return name;
	}
	
	public String getContent(){
		return content;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public List<Scenario> getChildList(){
		return childList;
	}
	
	public Scenario getInstanse() {
		return this;
	}

}