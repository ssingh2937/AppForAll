package com.sandhu.appforall.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.sandhu.appforall.Adapters.ViewGradeAdapter;
import com.sandhu.appforall.DBHelper;
import com.sandhu.appforall.R;

import java.util.ArrayList;


public class SearchFragment extends Fragment {
    RadioGroup radioGroup;
    RadioButton idRadio, pcRadio;
    EditText idEdt;
    ListView pcListView;
    Button submitBtn;
    RelativeLayout idView;
    RecyclerView recyclerView;
    DBHelper myDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        radioGroup = v.findViewById(R.id.radioGroup);
        pcListView = v.findViewById(R.id.programCodeListView);
        idRadio = v.findViewById(R.id.id_radio);
        pcRadio = v.findViewById(R.id.pc_radio);
        idView = v.findViewById(R.id.id_view);
        idEdt = v.findViewById(R.id.idEdt);
        submitBtn = v.findViewById(R.id.submitBtn);
        recyclerView = v.findViewById(R.id.searchRecycler);

        myDb = new DBHelper(getContext());

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
                view.setSelected(true);
                Cursor res = myDb.searchByPCode(pcList.get(position).toString());

                if(res.getCount() == 0){
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
                } else {
                    ViewGradeAdapter viewGradeAdapter = new ViewGradeAdapter(getContext(), res);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(viewGradeAdapter);
                }
                //pcText.setText(pcList.get(position));
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.searchById(idEdt.getText().toString());

                if(res.getCount() == 0){
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
                } else {
                    ViewGradeAdapter viewGradeAdapter = new ViewGradeAdapter(getContext(), res);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(viewGradeAdapter);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.id_radio:
                        idView.setVisibility(View.VISIBLE);
                        pcListView.setVisibility(View.GONE);
                        break;

                    case R.id.pc_radio:
                        idView.setVisibility(View.GONE);
                        pcListView.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });


        return v;
    }
}