package info.androidhive.materialtabs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.models.Container;
import info.androidhive.materialtabs.models.Lo;

/**
 * Created by ThinhNK on 4/8/2018.
 */

public class ContainersAdapter extends RecyclerView.Adapter<ContainersAdapter.ContainersViewHolder>  {

    private List<Container> lstContainer;
    private Context context;
    private LayoutInflater mLayoutInflater;
    public ContainersAdapter(List<Container> lstContainer,Context context) {
        this.lstContainer = lstContainer;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public ContainersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_container, parent, false);
        return new ContainersAdapter.ContainersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContainersViewHolder holder, int position) {
        Container container = lstContainer.get(position);
        holder.txt_container_code.setText("Code: "+container.getCode());
        holder.txt_container_lo.setText("Lô: "+container.getIdLo());
        holder.txt_conatiner_kt.setText("KT: "+container.getKt());
    }

    @Override
    public int getItemCount() {
        return lstContainer.size();
    }

    public void updateData(ArrayList<Container> newsLstContainer) {
        lstContainer.clear();
        lstContainer.addAll(newsLstContainer);
        notifyDataSetChanged();
    }

    public static class ContainersViewHolder extends RecyclerView.ViewHolder {

        protected TextView txt_container_code;
        protected TextView txt_container_lo;
        protected TextView txt_conatiner_kt;

        public ContainersViewHolder(View itemView) {
            super(itemView);
            txt_container_code = (TextView) itemView.findViewById(R.id.txt_container_code);
            txt_container_lo = (TextView) itemView.findViewById(R.id.txt_container_lo);
            txt_conatiner_kt = (TextView) itemView.findViewById(R.id.txt_conatiner_kt);
        }
    }
}
