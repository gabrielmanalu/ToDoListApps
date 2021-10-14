package com.example.todolistapps.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "toDoListDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TO_DO_LIST_TABLE = "ToDoList";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRIORITY_KEY = "price";
    private static final String COLOR_KEY = "color";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createDatabaseSQL = "create table " + TO_DO_LIST_TABLE +
                "( " + ID_KEY + " integer primary key autoincrement" +
                ", " + NAME_KEY + " text" + ", " + PRIORITY_KEY + " real" +
                ", " + COLOR_KEY + " text" + " )";
        db.execSQL(createDatabaseSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TO_DO_LIST_TABLE);
        onCreate(db);

    }

    public void addToDoList(ToDoList toDoList){

        SQLiteDatabase database = getWritableDatabase();
        String addToDoListSQLCommand = "insert into " + TO_DO_LIST_TABLE +
                " values(null, '" + toDoList.getToDoListName()
                + "', '" + toDoList.getToDoListDeadline() +
                "', '" + toDoList.getToDoListPriority() +
                "')";
        database.execSQL(addToDoListSQLCommand);
        database.close();

    }

    public void deleteToDoListDatabaseByID(int id){

        SQLiteDatabase database = getWritableDatabase();
        String deleteToDoListSQLCommand = "delete from " + TO_DO_LIST_TABLE +
                                            " where " + ID_KEY + " = " + id;
        database.execSQL(deleteToDoListSQLCommand);
        database.close();

    }

    public void modifyToDoListDatabaseByID(int martialArtID, String martialArtName, double martialArtPrice, String martialArtColor){

        SQLiteDatabase database = getWritableDatabase();
        String modifyToDoListSQLCommand = "update " + TO_DO_LIST_TABLE + " set "
                                            + NAME_KEY + " = '" + martialArtName + "', "
                                            + PRIORITY_KEY + " = '" + martialArtPrice + "',"
                                            + COLOR_KEY + " = '" + martialArtColor + "' "
                                            + "where " + ID_KEY + " = " + martialArtID;
        database.execSQL(modifyToDoListSQLCommand);
        database.close();

    }

    public ArrayList<ToDoList> returnAllToDoListObject(){

        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " + TO_DO_LIST_TABLE;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);

        ArrayList<ToDoList> toDoLists = new ArrayList<>();

        while (cursor.moveToNext()) {
            ToDoList currentToDoListObject = new ToDoList(
                    Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getDouble(2), cursor.getString(3));
            toDoLists.add(currentToDoListObject);
        }

        database.close();
        return toDoLists;

    }

    public ToDoList returnToDoListByID(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " + TO_DO_LIST_TABLE +
                                " where " + ID_KEY + " = " + id;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);

        ToDoList toDoListObject = null;

        if(cursor.moveToFirst()){
            toDoListObject = new ToDoList(
                    Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getDouble(2), cursor.getString(3));

        }
        database.close();
        return toDoListObject;

    }

}
