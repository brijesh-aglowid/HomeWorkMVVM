package com.aglowid.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aglowid.myapplication.model.User
import com.aglowid.myapplication.model.XAcc

@Dao
interface UsersDao {

    @Query("select * from XAcc")
    fun getXAcc(): LiveData<XAcc>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(xAcc: XAcc)

    @Query("select * from User")
    fun getUser(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}

@Database(entities = [User::class, XAcc::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}