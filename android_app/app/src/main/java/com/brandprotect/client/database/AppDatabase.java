package com.brandprotect.client.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;

import com.brandprotect.client.database.dao.AccountDao;
import com.brandprotect.client.database.dao.FavoriteTokenDao;
import com.brandprotect.client.database.dao.WalletDao;
import com.brandprotect.client.database.model.AccountModel;
import com.brandprotect.client.database.model.FavoriteTokenModel;
import com.brandprotect.client.database.model.WalletModel;

@Database(entities = {
        WalletModel.class,
        AccountModel.class,
        FavoriteTokenModel.class
}, version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 2;

    public abstract AccountDao accountDao();
    public abstract WalletDao walletDao();
    public abstract FavoriteTokenDao favoriteTokenDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE account ADD COLUMN address TEXT");

            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS favorite_token "
                        + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, account_id INTEGER NOT NULL, token_name TEXT)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}