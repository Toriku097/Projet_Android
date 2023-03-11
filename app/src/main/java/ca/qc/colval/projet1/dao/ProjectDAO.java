package ca.qc.colval.projet1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.Project;

public class ProjectDAO implements IProjectDAO{

    Singleton singleton;

    public ProjectDAO(Context context) {
        this.singleton = Singleton.getSingleInstance(context);
    }

    @Override
    public List<Project> getAllProjects() {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Projects";
        Cursor cursor = db.rawQuery(request, null);
        if(cursor != null) {
            cursor.moveToFirst();
            List<Project> projects = new ArrayList<>();
            while(!cursor.isAfterLast()){
                Project project = new Project();
                project.setProjectId(cursor.getInt(0));
                project.setName(cursor.getString(1));
                project.setTotalExpenses(cursor.getDouble(2));

                projects.add(project);
                cursor.moveToNext();
            }
            db.close();
            cursor.close();
            return projects;
        }
        return null;
    }

    @Override
    public Project getProjectbyId(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Projects WHERE idp = " + id;
        Cursor cursor = db.rawQuery(request, null);
        if(cursor != null) {
            cursor.moveToFirst();
            Project project = new Project();
            project.setProjectId(cursor.getInt(0));
            project.setName(cursor.getString(1));
            project.setTotalExpenses(cursor.getDouble(2));
            db.close();
            cursor.close();
            return project;
        }
        return null;
    }

    public Project addProject(Project project) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", project.getName());

        int id = (int) db.insert("Projects", null, values);
        return getProjectbyId(id);
    }

    public Project updateProjectbyId(int id, Project project) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", project.getName());

        db.update("Projects", values, "idp = ?", new String[]{id + ""});
        return getProjectbyId(id);
    }

    public Project deleteProjectbyId(int id) {
        Project project = getProjectbyId(id);
        if(project != null) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            db.delete("Projects", "idp = ?", new String[]{id + ""});
        }
        return project;
    }



}
