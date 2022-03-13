package com.travel.model;

public class Deal {
    private Integer id;
    private String name;
    private Integer cost;
    private String dept;
    private String arrival;

    public Deal() {}

    public Deal(String name, Integer cost, String dept, String arrival) {
        this.name = name;
        this.cost = cost;
        this.dept = dept;
        this.arrival = arrival;
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