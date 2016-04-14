package com.example.activity.experience;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.androidtest.R;

public class ContentProviderActivity extends Activity {

    Uri uri= MyContentProvider.CONTENT_URI;
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        resolver=this.getContentResolver();

        // test1 insert
        ContentValues values=new ContentValues();
        values.put(MyDataBaseHelper.COL_NAME, "liuyao");
        values.put(MyDataBaseHelper.COL_SEX, "male");
        resolver.insert(uri, values);

        ContentValues values1=new ContentValues();
        values1.put(MyDataBaseHelper.COL_NAME, "luming");
        values1.put(MyDataBaseHelper.COL_SEX, "female");
        resolver.insert(uri, values1);

        // test1 update
        ContentValues newValues=new ContentValues();
        newValues.put(MyDataBaseHelper.COL_NAME,"ly");
        newValues.put(MyDataBaseHelper.COL_SEX, "male");

        Uri update_uri=ContentUris.withAppendedId(uri,1);
        resolver.update(update_uri, newValues, null, null);

        // test1 delete
        Uri delete_uri= ContentUris.withAppendedId(uri,2);
        resolver.delete(delete_uri, null, null);

        // test1 query
        String[] projection=new String[]{MyDataBaseHelper.COL_NAME,MyDataBaseHelper.COL_SEX};
        Cursor cursor=resolver.query(uri,projection,null,null,null);
        while(cursor.moveToNext()){

            String name=cursor.getString(0);
            String sex=cursor.getString(1);
            Log.i("ly", "name,sex:" + name + " " + sex);

        }
        cursor.close();
    }

}
