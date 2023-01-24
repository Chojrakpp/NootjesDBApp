package be.ehb.nootjesdbapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NutDAO {

    @Query("SELECT * FROM Nut")
    public LiveData<List<Nut>> getAllNuts();

    @Delete
    public void deleteNut(Nut nut);

    @Insert
    public void insertNut(Nut nut);
}
