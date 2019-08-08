package grades;

import javafx.beans.property.SimpleStringProperty;

public class GradesClass {

		private final SimpleStringProperty IDstudent;
		private final SimpleStringProperty math;
		private final SimpleStringProperty physics;
		private final SimpleStringProperty science;
		private final SimpleStringProperty business;
		private final SimpleStringProperty history;
		private final SimpleStringProperty management;
		private final SimpleStringProperty biology;
		private final SimpleStringProperty philoposhy;
		private final SimpleStringProperty  french;
		
		public GradesClass() {
			this.IDstudent=new SimpleStringProperty();
			this.math=new SimpleStringProperty();
			this.physics=new SimpleStringProperty();
			this.science=new SimpleStringProperty();
			this.business=new SimpleStringProperty();
			this.history=new SimpleStringProperty();
			this.management=new SimpleStringProperty();
			this.biology=new SimpleStringProperty();
			this.philoposhy=new SimpleStringProperty();
			this.french=new SimpleStringProperty();
		}

		public void setIDstudent(String ID) {
			this.IDstudent.set(ID);
}
public void setMath(String math) {
	this.math.set(math);
}
public void setPhysics(String physics) {
	this.physics.set(physics);
}
public void setScience(String science) {
	this.science.set(science);
}
public void setBusiness(String business) {
	this.business.set(business);
}
public void setHistory(String history) {
	this.history.set(history);
}
public void setManagement(String management) {
	this.management.set(management);
}
public void setBiology(String bioogy) {
	this.biology.set(bioogy);
}
public void setPhilosophy(String phy) {
	this.philoposhy.set(phy);
}
public void setFrench(String french) {
	this.french.set(french);
}

public String getIDstudent(){
	return this.IDstudent.get();
}
public String getMath() {
 return this.math.get();
}
public String getPhysics() {
 return this.physics.get();
}
public String getScience() {
 return this.science.get();
}
public String getBusiness() {
return this.business.get();
}
public String getHistory() {
return this.history.get();
}
public String getManagement() {
return this.management.get();
}
public String getBiology() {
return this.biology.get();
}
public String getPhilosophy() {
return this.philoposhy.get();
}
public String getFrench() {
return this.french.get();
}



}
