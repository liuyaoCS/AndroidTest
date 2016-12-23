package com.example.greendao.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.greendao.gen.DaoMaster;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {


    private static final SortedMap<Integer, Migration> ALL_MIGRATIONS = new TreeMap<>();
    static {
        ALL_MIGRATIONS.put(1, new V1Migration());
        ALL_MIGRATIONS.put(2, new V2Migration());
    }

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        executeMigrations(db, ALL_MIGRATIONS.keySet());
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrade from" + oldVersion + "to" + newVersion);
        SortedMap<Integer, Migration> migrations = ALL_MIGRATIONS.subMap(oldVersion+1, newVersion+1);
        executeMigrations(sqLiteDatabase, migrations.keySet());
    }
    private void executeMigrations(final SQLiteDatabase paramSQLiteDatabase, final Set<Integer>
            migrationVersions) {
        for (final Integer version : migrationVersions) {
            ALL_MIGRATIONS.get(version).migrate(paramSQLiteDatabase);
        }
    }
}
/**
 * Created by weijianxing on 9/22/15.
 */
 interface Migration {
    void migrate(SQLiteDatabase db);
    
}
 class V1Migration implements Migration {
    @Override
    public void migrate(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE USER ADD COLUMN test1");
    }
}
class V2Migration implements Migration {
    @Override
    public void migrate(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE USER ADD COLUMN test2");
    }
}

