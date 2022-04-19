package ayds.winchester.songinfo.moredetails.fulllogic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

  public static void testDB(){
    Connection connection = null;
    try{
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);
      ResultSet rs = statement.executeQuery("select * from artists");

      while(rs.next()){
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("artist = " + rs.getString("artist"));
        System.out.println("info = " + rs.getString("info"));
        System.out.println("source = " + rs.getString("source"));
      }
    }
    catch(SQLException e){
      System.err.println(e.getMessage());
    }

    finally{
      try{
        if(connection != null)
          connection.close();
      }
      catch(SQLException e){
        System.err.println(e);
      }
    }
  }

  public static void saveArtist(DataBase dbHelper, String artist, String info){
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("artist", artist);
    values.put("info", info);
    values.put("source", 1);
    long newRowId = db.insert("artists", null, values);
  }

  public static String getInfo(DataBase dbHelper, String artist){
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    String[] projection = {
            "id",
            "artist",
            "info"
    };

    String selection = "artist  = ?";
    String[] selectionArgs = { artist };
    String sortOrder = "artist DESC";
    Cursor cursor = db.query(
            "artists",
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
    );

    List<String> items = new ArrayList<String>();
    while(cursor.moveToNext()) {
      String info = cursor.getString(
              cursor.getColumnIndexOrThrow("info"));
      items.add(info);
    }

    cursor.close();

    if(items.isEmpty()) return null;
    else return items.get(0);
  }

  public DataBase(Context context) {
    super(context, "dictionary.db", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(
            "create table artists (id INTEGER PRIMARY KEY AUTOINCREMENT, artist string, info string, source integer)"
    );
    Log.i("DB", "DB created");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
