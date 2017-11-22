package jiaqiwang.cs.brandies.edu.expenselogv2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import jiaqiwang.cs.brandies.edu.expenselogv2.database.ExpenseDataHelper;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Context context = MainActivity.this;
    ArrayList myList = new ArrayList();
    ExpenseDataHelper myDB;
    Cursor expenseCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new ExpenseDataHelper(context);
        list = (ListView) findViewById(R.id.list);
        getDataInList();
        ExpenseTrackerAdapter expenseTrackerAdapter = new ExpenseTrackerAdapter(context, myList);
        list.setAdapter(expenseTrackerAdapter);
    }


    private void getDataInList() {
        expenseCursor = myDB.getAllData();
        if (expenseCursor.getCount() == 0) {
            return;
        }

        while (expenseCursor.moveToNext()) {
            ExpenseLogEntryData ld = new ExpenseLogEntryData();
            ld.setItemId(expenseCursor.getString(0));
            ld.setItemDescription(expenseCursor.getString(1));
            ld.setItemNotes(expenseCursor.getString(2));
            ld.setItemTime(expenseCursor.getString(3));
            myList.add(ld);
        }
    }

    private void getNewData() {
        expenseCursor = myDB.getAllData();
        if (expenseCursor.getCount() == 0) {
            return;
        }

        if (expenseCursor.moveToLast()) {
            ExpenseLogEntryData ld = new ExpenseLogEntryData();
            ld.setItemId(expenseCursor.getString(0));
            ld.setItemDescription(expenseCursor.getString(1));
            ld.setItemNotes(expenseCursor.getString(2));
            ld.setItemTime(expenseCursor.getString(3));
            myList.add(ld);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.expense_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(new Intent(this, InputActivity.class), 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                String[] addItem;

                if (extras != null) {

                    addItem = extras.getStringArray("result");
                    boolean isInserted = myDB.insertData(addItem[0], addItem[1], addItem[2]);

                    if (isInserted) {
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }

                }
                getNewData();
                new ExpenseTrackerAdapter(context, myList).refreshResultList(myList);


            }
        }
    }
}

