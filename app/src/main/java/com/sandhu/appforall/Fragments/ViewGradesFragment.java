package com.sandhu.appforall.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sandhu.appforall.Adapters.ViewGradeAdapter;
import com.sandhu.appforall.DBHelper;
import com.sandhu.appforall.R;

public class ViewGradesFragment extends Fragment {
    RecyclerView recyclerView;
    DBHelper myDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myDb = new DBHelper(getContext());

        View v = inflater.inflate(R.layout.fragment_view_grades, container, false);
        recyclerView = v.findViewById(R.id.viewGradesRecycler);

        Cursor res = myDb.getData();
        if(res.getCount() == 0){
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            ViewGradeAdapter viewGradeAdapter = new ViewGradeAdapter(getContext(), res);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(viewGradeAdapter);
        }

        /*StringBuffer stringBuffer = new StringBuffer();
        while (res.moveToNext()){
            stringBuffer.append("Id " + res.getString(0) + "\n");
            stringBuffer.append("name " + res.getString(1) + "\n");
            stringBuffer.append("program code " + res.getString(2) + "\n");
            stringBuffer.append("grade " + res.getString(3) + "\n");
            stringBuffer.append("duration " + res.getString(4) + "\n");
            stringBuffer.append("fees " + res.getString(5) + "\n");
        }

        Toast.makeText(getContext(), stringBuffer.toString(), Toast.LENGTH_SHORT).show();*/


        return v;

    }
}