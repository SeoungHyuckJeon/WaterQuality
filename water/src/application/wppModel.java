package application;

import javafx.beans.property.StringProperty;

public class wppModel {
	private StringProperty column_Address;
	private StringProperty column_Plant;
	
	public wppModel(StringProperty column_Address, StringProperty column_Plant) {
		this.column_Address = column_Address;
		this.column_Plant = column_Plant;
	}
	
	public StringProperty addressProperty() {
		return column_Address;
	}
	
	public StringProperty plantProperty() {
		return column_Plant;
	}
}
