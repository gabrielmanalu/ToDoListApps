package com.example.todolistapps.model;


public class ToDoList {
    private int toDoListID;
    private String toDoListDeadline;
    private String toDoListName;
    private String toDoListPriority;

    public ToDoList(){
    }


    public int getToDoListID() {
        return toDoListID;
    }

    public void setToDoListID(int toDoListID) {
        this.toDoListID = toDoListID;
    }

    public String getToDoListDeadline() {
        return toDoListDeadline;
    }

    public void setToDoListDeadline(String toDoListDeadline) {
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
