package jiaqiwang.cs.brandies.edu.expenselogv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by yuchenrong on 11/19/17.
 */

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_add);
    }


    public void Save(View view) {
        Intent data = new Intent();
        String[] lastResult = new String[3];
        EditText lastResult1 = findViewById(R.id.text1);
        EditText lastResult2 = findViewById(R.id.text2);
        lastResult[0]=lastResult1.getText().toString();
        lastResult[1] = lastResult2.getText().toString();
        lastResult[2]= DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(new Date());
        data.putExtra("result",lastResult);
        setResult(RESULT_OK, data);
        finish();
    }

    public void Cancel(View view){
        finish();
    }
}
