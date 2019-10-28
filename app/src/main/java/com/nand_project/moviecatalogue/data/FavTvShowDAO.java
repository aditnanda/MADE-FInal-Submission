package com.nand_project.moviecatalogue.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;

import com.nand_project.moviecatalogue.model.FavoritTvShowModel;

@Dao
public interface FavTvShowDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertData(FavoritTvShowModel favoritTvShowModel);

    @Update
    int updateData(FavoritTvShowModel favoritTvShowModel);

    @Delete
    void deleteData(FavoritTvShowModel favoritTvShowModel);

    @Query("SELECT * FROM tb_fav_tv_show")
    FavoritTvShowModel[] selectAllData();

    @Query("SELECT * FROM tb_fav_tv_show WHERE id = :id LIMIT 1")
    FavoritTvShowModel selectDataDetail(String id);

    @Query("SELECT * FROM tb_fav_tv_show")
    Cursor selectAll();

    @Query("SELECT * FROM tb_fav_tv_show WHERE id = :id LIMIT 1")
    Cursor selectbyID(String id);

    @Query("DELETE FROM " + FavoritTvShowModel.TABLE_NAME_FAV_TVSHOW + " WHERE " + FavoritTvShowModel.COLUMN_ID + " = :id")
    int deleteById(String id);
}
