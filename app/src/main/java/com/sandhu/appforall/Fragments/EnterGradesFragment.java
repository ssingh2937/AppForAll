package com.sandhu.appforall.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sandhu.appforall.DBHelper;
import com.sandhu.appforall.MainActivity;
import com.sandhu.appforall.R;

import java.util.ArrayList;

public class EnterGradesFragment extends Fragment {
    EditText nameEdt, gradeEdt, durationEdt, feesEdt;
    TextView pcText;
    ListView pcListView;
    Button submitBtn;
    DBHelper myDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_enter_grades, container, false);

        myDb = new DBHelper(getContext());

        nameEdt = v.findViewById(R.id.nameEdt);
        gradeEdt = v.findViewById(R.id.gradeEdt);
        durationEdt = v.findViewById(R.id.durationEdt);
        feesEdt = v.findViewById(R.id.feeEdt);
        pcText = v.findViewById(R.id.programCodeText);
        submitBtn = v.findViewById(R.id.submitBtn);

        pcListView = v.findViewById(R.id.programCodeListView);

        ArrayList<String> pcList = new ArrayList<String>();
        pcList.add("PROG8080");
        pcList.add("PROG8081");
        pcList.add("PROG8082");
        pcList.add("PROG8083");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, pcList);
        pcListView.setAdapter(arrayAdapter);

        pcListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pcText.setText(pcList.get(position));
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(nameEdt.getText().toString().trim(), pcText.getText().toString().trim(), gradeEdt.getText().toString().trim(),
                        durationEdt.getText().toString().trim(),feesEdt.getText().toString().trim());
                if(isInserted)
                    Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                 else
                    Toast.makeText(getContext(), "Data not inserted", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}