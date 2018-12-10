package com.example.abc.practice7.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.practice7.Model.StudentDetail;
import com.example.abc.practice7.R;
import com.example.abc.practice7.UpdateActivity;

import java.util.ArrayList;

public class CustomStudentAdapter extends BaseAdapter {
    Context context;
    ArrayList<StudentDetail> studentDetails;

    public CustomStudentAdapter(Context context,ArrayList<StudentDetail> studentDetails)
    {
        this.context = context;
        this.studentDetails = studentDetails;

    }

    private static class ViewHolder {
        public final LinearLayout rootView;
        public final TextView txtId;
        public final TextView txtName;
        public final TextView txtCity;

        private ViewHolder(LinearLayout rootView,TextView txtId, TextView txtName, TextView txtCity) {
            this.rootView = rootView;
            this.txtId = txtId;
            this.txtName = txtName;
            this.txtCity = txtCity;
        }

        public static ViewHolder create(LinearLayout rootView) {
            TextView txtId = (TextView)rootView.findViewById(R.id.txtId);
            TextView txtName = (TextView)rootView.findViewById( R.id.txtName );
            TextView txtCity = (TextView)rootView.findViewById( R.id.txtCity );
            return new ViewHolder( rootView,txtId, txtName, txtCity );
        }
    }
    @Override
    public int getCount() {
        return studentDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private LayoutInflater mInflater;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewHolder vh;
        if ( convertView == null ) {
            View view = mInflater.inflate( R.layout.listitem_student, parent, false );
            vh = ViewHolder.create( (LinearLayout)view );
            view.setTag( vh );
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        Object item = getItem( position );


        vh.txtCity.setText(studentDetails.get(position).getStudentCity());
        vh.txtName.setText(studentDetails.get(position).getStudentName());
        vh.txtId.setText(String.valueOf(studentDetails.get(position).getStudentId()));

        // TODOBind your data to the views here
        vh.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, UpdateActivity.class);
                in.putExtra("Id",String.valueOf(studentDetails.get(position).getStudentId()));
                in.putExtra("Name",studentDetails.get(position).getStudentName());
                in.putExtra("City",studentDetails.get(position).getStudentCity());
                context.startActivity(in);

            }
        });

        return vh.rootView;
    }

}



