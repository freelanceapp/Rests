package com.semicolon.rests.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.semicolon.rests.R;
import com.semicolon.rests.activities.RegisterActivity;
import com.semicolon.rests.models.CityModel;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter <CityAdapter.MyHolder>{

    private Context context;
    private List<CityModel> cityModelList;
    private RegisterActivity activity;
    private int lastSelectedPos=-1;

    public CityAdapter(Context context, List<CityModel> cityModelList) {
        this.context = context;
        this.activity = (RegisterActivity) context;
        this.cityModelList = cityModelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_row,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        CityModel cityModel = cityModelList.get(position);
        holder.BindData(cityModel);
        holder.rb.setChecked(lastSelectedPos==position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPos = holder.getAdapterPosition();
                notifyDataSetChanged();
                CityModel cityModel = cityModelList.get(holder.getAdapterPosition());
                activity.setItemCity(cityModel);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cityModelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private RadioButton rb;
        public MyHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            rb = itemView.findViewById(R.id.rb);

        }

        public void BindData(CityModel cityModel)
        {
            tv_title.setText(cityModel.getCity_title());
        }
    }
}
