package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static DB instance = null;
    private static List < Enclos > EnclosList;
    protected DB() {
        // Exists only to defeat instantiation.
    }
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
            EnclosList = new ArrayList < Enclos > ();
            EnclosList.add(new Enclos("Enclos Bla bla 1", "200.45", "Description Enclos des blablabla # 1"));
            EnclosList.add(new Enclos("Enclos Bla bla 2", "20.05", "Description Enclos des blablabla # 2"));
            EnclosList.add(new Enclos("Enclos Bla bla 3", "40.25", "Description Enclos des blablabla # 3"));
        }
        return instance;
    }
    public Enclos getByIndex(int index) {
        return EnclosList.get(index);
    }
    public List < Enclos > getAll() {
        return EnclosList;
    }
    public void add(Enclos enclos) {
        EnclosList.add(enclos);
    }
    public void update(Enclos enclos, int position) {
        EnclosList.remove(position);
        EnclosList.add(enclos);
    }
    public void remove(int position) {
        EnclosList.remove(position);
    }
}