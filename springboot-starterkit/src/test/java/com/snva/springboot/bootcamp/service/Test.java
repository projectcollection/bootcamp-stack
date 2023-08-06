package com.snva.springboot.bootcamp.service;



class Employee{
    public String name ;
    public Employee(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "The Name is : "+ this.getClass().getName()+" : "+ this.name;
    }
}

class Manager extends Employee{
    public Manager(String name){
        super(name);
    }
}

class Runner{
    public static void main(String[] args) {
        // Employee e = new Manager();
        java.util.List<Employee> employees = new java.util.ArrayList<Employee>();
        for(int i =0;i<20; i++){
            if (i%2==0){
                employees.add(new Employee("EMP"+(i+1)));
            }
            else {
                employees.add(new Manager("EMP"+(i+1)));
            }
        }

        for (Employee e:employees             ) {
                if (e instanceof  Manager){
                    // call manager specific
                    ((Manager)e).getClass();
                }
                else {
                    // call employee  specific
                    // System.out.println(e
                }
        }
    }
}