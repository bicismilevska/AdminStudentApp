package admin;

import javafx.beans.property.SimpleStringProperty;

public class StudentData {
private final SimpleStringProperty ID;
private final SimpleStringProperty firstName;
private final SimpleStringProperty lastName;
private final SimpleStringProperty email;
private final SimpleStringProperty dateofbirth;
public StudentData() {
	this.ID=new SimpleStringProperty();
	this.firstName=new SimpleStringProperty();
	this.lastName=new SimpleStringProperty();
	this.email=new SimpleStringProperty();
	this.dateofbirth=new SimpleStringProperty();
}
public String getID() {
	return this.ID.get();
}
public SimpleStringProperty getIDProperty() {
	return ID;
}
public void setID(String ID) {
	this.ID.set(ID);
}
public String getFirstName() {
	return this.firstName.get();
}
public SimpleStringProperty getFirstNameProperty() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName.set(firstName);
}
public String getLastName() {
	return this.lastName.get();
}
public SimpleStringProperty getLastNameProperty() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName.set(lastName);
}
public String getEmail() {
	return this.email.get();
}
public SimpleStringProperty getEmailProperty() {
	return email;
}
public void setEmail(String email) {
	this.email.set(email);
}

public SimpleStringProperty getDateofbirthProperty() {
	return dateofbirth;
}

public String getDateofbirth() {
	return this.dateofbirth.get();
}
public void setDateofbirth(String dob) {
	this.dateofbirth.set(dob);
}

}
