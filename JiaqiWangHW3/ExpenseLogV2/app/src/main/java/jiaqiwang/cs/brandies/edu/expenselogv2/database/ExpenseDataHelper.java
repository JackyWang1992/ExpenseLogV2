package jiaqiwang.cs.brandies.edu.expenselogv2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yuchenrong on 11/20/17.
 */

public class ExpenseDataHelper extends SQLiteOpenHelper {

    // static  vars
    private static final String DATABASE_NAME = "myDatabase.db";
    private static int DATABASE_VERSION = 1;

    // SQL Database table and column names
    public static final String TABLE_NAME = "Expense_Log";
    public static final String DATABASE_TABLE_ID_COLUMN = "_id";
    public static final String DATABASE_TABLE_DESCRIPTION_COLUMN = "description";
    public static final String DATABASE_TABLE_NOTES_COLUMN = "notes";
    public static final String DATABASE_TABLE_TIME_COLUMN = "time";
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                  + DATABASE_TABLE_DESCRIPTION_COLUMN+" TEXT, "
                                                                  + DATABASE_TABLE_NOTES_COLUMN+" TEXT, "
                                                                  + DATABASE_TABLE_TIME_COLUMN+" TEXT)";


    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly. The database is not actually
     * created or opened until one of (@link #getWritableDatabase) or
     * (@link #getReadbaleDatabase) is called
     * @param context to use to open or create the database
     *
     */

    public ExpenseDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    /**
     * Called when no database exists in disk and the helper class needs
     * to create a new one
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }
    /**
     * Called when there is a database version mismatch meaning that the version of the
     * database on disk needs to to be updated to the current version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " +
              newVersion + ", which will destroy all old data");
        /* Upgrading the existing database to conform to the new version. Multiple previous versions
         * can be handled by comparing oldVersion and newVersion values.
         */

        // the simplest case is to drop the old table and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create a new one
        onCreate(db);
    }

    public boolean insertData(String description, String notes, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATABASE_TABLE_DESCRIPTION_COLUMN, description);
        contentValues.put(DATABASE_TABLE_NOTES_COLUMN, notes);
        contentValues.put(DATABASE_TABLE_TIME_COLUMN, time);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

    public Integer deleteData (String description){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,DATABASE_TABLE_DESCRIPTION_COLUMN + " = ?", new String[]{description} );
    }
}
