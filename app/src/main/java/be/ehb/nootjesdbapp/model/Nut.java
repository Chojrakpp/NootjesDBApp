package be.ehb.nootjesdbapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Nut {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Nut() {
    }

    @Ignore
    public Nut(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
