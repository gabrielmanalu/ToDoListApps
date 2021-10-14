package com.example.todolistapps.model;


public class ToDoList {
    private int toDoListID;
    private double toDoListDeadline;
    private String toDoListName;
    private String toDoListPriority;

    public ToDoList(int id, String name, double deadline, String priority){
        setToDoListPriority(priority);
        setToDoListID(id);
        setToDoListName(name);
        setToDoListDeadline(deadline);
    }

    public ToDoList(){
    }

    public int getToDoListID() {
        return toDoListID;
    }

    public void setToDoListID(int toDoListID) {
        this.toDoListID = toDoListID;
    }

    public double getToDoListDeadline() {
        return toDoListDeadline;
    }

    public void setToDoListDeadline(double toDoListDeadline) {
        this.toDoListDeadline = toDoListDeadline;
    }

    public String getToDoListName() {
        return toDoListName;
    }

    public void setToDoListName(String toDoListName) {
        this.toDoListName = toDoListName;
    }

    public String getToDoListPriority() {
        return toDoListPriority;
    }

    public void setToDoListPriority(String toDoListPriority) {
        this.toDoListPriority = toDoListPriority;
    }

    @Override
    public String toString() {
        return getToDoListID() + " " + getToDoListName() + " " +
               getToDoListDeadline() + " " +
               getToDoListPriority();
    }
}
