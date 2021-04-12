package com.sandhu.appforall.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandhu.appforall.R;

public class ViewGradeAdapter extends RecyclerView.Adapter<ViewGradeAdapter.ViewHolder> {
    Context context;
    Cursor res;

    public ViewGradeAdapter(Context context, Cursor res) {
        this.context = context;
        this.res = res;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_grade_layout_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        while (res.moveToNext()) {
            holder.nameText.setText(res.getString(1));
            holder.pCodeText.setText(res.getString(2));
            holder.gradeText.setText(res.getString(3));
            holder.durationText.setText(res.getString(4));
            holder.feesText.setText(res.getString(5));
        }
    }

    @Override
    public int getItemCount() {
        return res.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, pCodeText, gradeText, durationText, feesText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            pCodeText = itemView.findViewById(R.id.pCodeText);
            gradeText = itemView.findViewById(R.id.gradeText);
            durationText = itemView.findViewById(R.id.durationText);
            feesText = itemView.findViewById(R.id.feeText);
        }
    }
}
