package jiaqiwang.cs.brandies.edu.expenselogv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jiaqiwang.cs.brandies.edu.expenselogv2.database.ExpenseDataHelper;

/**
 * Created by yuchenrong on 11/19/17.
 */

public class ExpenseTrackerAdapter extends BaseAdapter {

    private ArrayList list = new ArrayList();

    private Context context;

    LayoutInflater inflater;

    ExpenseDataHelper myDB;

    ExpenseTrackerAdapter(Context context, ArrayList<ExpenseLogEntryData>list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(this.context);
        myDB = new ExpenseDataHelper(context);
    }


    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expense_entry, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ExpenseLogEntryData currentListData = (ExpenseLogEntryData)getItem(position);

        mViewHolder.heading.setText(currentListData.getItemDescription());
        mViewHolder.notes.setText(currentListData.getItemNotes());
        mViewHolder.time.setText(currentListData.getItemTime());

        Button deleteBtn = (Button)convertView.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteData(mViewHolder.heading.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(context,"Data not Deleted",Toast.LENGTH_SHORT).show();
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                }
        );

        return convertView;
    }

    public void refreshResultList(ArrayList<ExpenseLogEntryData> list){
        this.list = list;
        notifyDataSetChanged();

    }


    private class MyViewHolder {
        TextView heading, notes,time;

        public MyViewHolder(View item) {
            heading = (TextView) item.findViewById(R.id.heading);
            notes = (TextView) item.findViewById(R.id.notes);
            time = (TextView) item.findViewById(R.id.time);
        }

    }
}

