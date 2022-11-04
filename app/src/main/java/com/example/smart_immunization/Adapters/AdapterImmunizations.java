package com.example.smart_immunization.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_immunization.Models.Immunizations;
import com.example.smart_immunization.R;

import java.util.ArrayList;

public class AdapterImmunizations extends RecyclerView.Adapter<AdapterImmunizations.ViewHolderImmunizations>{


    // creating a variable for array list and context.
    private ArrayList<Immunizations> immunizationsArrayList;
    private Context context;

    public AdapterImmunizations(ArrayList<Immunizations> immunizationsArrayList, Context context) {
        this.immunizationsArrayList = immunizationsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderImmunizations onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_immunizations, parent, false);
        return new ViewHolderImmunizations(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderImmunizations holder, int position) {
        // setting data to our views of recycler view.
        Immunizations immunizations = immunizationsArrayList.get(position);

        // private String immunizationName, immunizationDosageLevel, immunizationDate, nextImmunizationDate, administeredDate;
        int id = immunizations.getId();
        String immunizationName = immunizations.getImmunizationName();
        String immunizationDosageLevel = immunizations.getImmunizationDosageLevel();
        String immunizationDate = immunizations.getImmunizationDate();
        String nextImmunizationDate = immunizations.getNextImmunizationDate();

        holder.idTv.setText(id+"");
        holder.immunizationNameTv.setText(immunizationName);
        holder.immunizationDosageLevelTv.setText(immunizationDosageLevel);
        holder.immunizationDateTv.setText(immunizationDate);
        holder.nextImmunizationDateTv.setText(nextImmunizationDate);
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return immunizationsArrayList.size();
    }


    public class ViewHolderImmunizations extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView idTv, immunizationNameTv, immunizationDosageLevelTv, immunizationDateTv, nextImmunizationDateTv;


        public ViewHolderImmunizations(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            idTv = itemView.findViewById(R.id.idTv);
            immunizationNameTv = itemView.findViewById(R.id.immunizationNameTv);
            immunizationDosageLevelTv = itemView.findViewById(R.id.immunizationDosageLevelTv);
            immunizationDateTv = itemView.findViewById(R.id.immunizationDateTv);
            nextImmunizationDateTv = itemView.findViewById(R.id.nextImmunizationDateTv);


        }
    }
}
