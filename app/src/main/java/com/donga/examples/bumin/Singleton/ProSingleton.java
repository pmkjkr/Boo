package com.donga.examples.bumin.Singleton;

import com.donga.examples.bumin.retrofit.retrofitProfessor.Professor;

import java.util.ArrayList;

/**
 * Created by pmk on 17. 2. 18.
 */
public class ProSingleton {
    private static ProSingleton mInstance = null;
    ArrayList<Professor> professorArrayList;

    public static ProSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new ProSingleton();
        }
        return mInstance;
    }

    private ProSingleton() {
    }

    public ArrayList<Professor> getProfessorArrayList() {
        return professorArrayList;
    }

    public void setProfessorArrayList(ArrayList<Professor> professorArrayList) {
        this.professorArrayList = professorArrayList;
    }
}
