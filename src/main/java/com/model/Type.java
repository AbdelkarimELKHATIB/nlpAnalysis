package com.model;

public enum Type {
	PERSON("Person"),
	CITY("City"),
	STATE_OR_PROVINCE("state_of_province"),
	COUNTRY("Country"),
	EMAIL("Email"),
	PHONE_NUMBER("Phone_number"),
	TITLE("Title");
   
	


		private String type;
		     Type(String type) {
			  this.type = type;
		}
		public String getName() {
			return this.type;
		}
}
