package com.nand_project.moviecatalogue.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;

import com.nand_project.moviecatalogue.model.FavoritMovieModel;

@Dao
public interface FavMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertData(FavoritMovieModel favoritMovieModel);

    @Update
    int updateData(FavoritMovieModel favoritMovieModel);

    @Delete
    void deleteData(FavoritMovieModel favoritMovieModel);

    @Query("SELECT * FROM tb_fav_movie")
    FavoritMovieModel[] selectAllData();

    @Query("SELECT * FROM tb_fav_movie WHERE id = :id LIMIT 1")
    FavoritMovieModel selectDataDetail(String id);

    @Query("SELECT * FROM tb_fav_movie")
    Cursor selectAll();

    @Query("SELECT * FROM tb_fav_movie WHERE id = :id LIMIT 1")
    Cursor selectbyID(String id);

    @Query("DELETE FROM " + FavoritMovieModel.TABLE_NAME_FAV_MOVIE + " WHERE " + FavoritMovieModel.COLUMN_ID + " = :id")
    int deleteById(String id);
}
