package com.example.experience;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

    private SQLiteDatabase database;

    private static  final  String AUTHORITY="com.example.androidTest";
    public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/"+MyDataBaseHelper.TABLE_NAME);

    private static UriMatcher uriMatcher;
    private static final int ITEMS=1;
    private static final int ITEM=2;
    static{
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"people/",ITEMS);
        uriMatcher.addURI(AUTHORITY,"people/#",ITEM);
    }


    public MyContentProvider() {
    }
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        database=new MyDataBaseHelper(getContext(),MyDataBaseHelper.DB_NAME,null,MyDataBaseHelper.VERSION).getWritableDatabase();
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        database.insert(MyDataBaseHelper.TABLE_NAME,null,values);
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int count=0;

        switch (uriMatcher.match(uri)){
            case ITEMS:
                count = database.delete(MyDataBaseHelper.TABLE_NAME, selection, selectionArgs);
                break;
            case ITEM:
                String where="_id="+uri.getPathSegments().get(1);
                Log.i("ly",where);
                count = database.delete(MyDataBaseHelper.TABLE_NAME, where, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        int count = 0;
        switch (uriMatcher.match(uri)){
            case ITEMS:
                count = database.update(MyDataBaseHelper.TABLE_NAME, values, selection, selectionArgs);
                break;
            case ITEM:
                String where="_id="+uri.getPathSegments().get(1);
                Log.i("ly",uri.getPathSegments().get(0));//people
                count = database.update(MyDataBaseHelper.TABLE_NAME, values,where, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case ITEMS:
                cursor =database.query(MyDataBaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case ITEM:
                cursor =database.query(MyDataBaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }

        return cursor;
    }
    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
    }
}
