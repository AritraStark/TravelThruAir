package model;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

public class deals {
	    private Integer id;
	    private String name;
	    private Integer cost;
	    private String dept;
	    private String arrival;
	    private String date;

	    public deals() {}

	    public deals(String name, Integer cost, String dept, String arrival) {
	        this.name = name;
	        this.cost = cost;
	        this.dept = dept;
	        this.arrival = arrival;
	        this.date = java.time.LocalDate.now().toString();
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    public String getDate() {
	        return date;
	    }

	    public void setDate() {
	        this.date = java.time.LocalDate.now().toString();
	    }
	    
	    public Integer getCost() {
	        return cost;
	    }

	    public void setCost(Integer cost) {
	        this.cost = cost;
	    }

	    public String getDept() {
	        return dept;
	    }

	    public void setDept(String dept) {
	        this.dept = dept;
	    }
	    
	    public String getArrival() {
	        return arrival;
	    }

	    public void setArrival(String arrival) {
	        this.arrival = arrival;
	    }
}
