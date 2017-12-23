package pl.put.poznan.transformer.data;


/*
 * W tej klasie beda skladowane dane 
 * parent jest numerem rodzica w zagniezdzeniu {jak by co mozna zmienic :) }
 * 
 * Wiem że nazwy sa do dupy ale nigdy nie umialem ich nazywac
 */
public class DataContainer {
	private final String name ;
	private final String content;
	private final Integer parent;
	private Integer level;
	
	public DataContainer() {
		name = null;
		content = null;
		parent = null;
	}
	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}
	
	public Integer getParent() {
		return parent;
	}
	public void setLevel(Integer newLevel) {
		level = newLevel;
	}
	public Integer getLevel() {
		return level;
	}
}