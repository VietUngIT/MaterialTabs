package info.androidhive.materialtabs.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.StorageInfoActivity;
import info.androidhive.materialtabs.models.Lo;

/**
 * Created by ThinhNK on 4/8/2018.
 */

public class StorageAdapter extends RecyclerView.Adapter<StorageAdapter.StorageViewHolder> {

    private List<Lo> lstLo;
    private Context context;
    private LayoutInflater mLayoutInflater;
    public StorageAdapter(List<Lo> lstLo,Context context) {
        this.lstLo = lstLo;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public StorageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_storage, parent, false);
        return new StorageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StorageViewHolder holder, int position) {
        final Lo lo = lstLo.get(position);
        holder.txt_lo.setText("Lô: "+lo.getId());
        holder.txt_blanks.setText("Blanks: "+lo.getBlanks());
        holder.txt_kt.setText("KT: "+lo.getKt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StorageInfoActivity.class);
                intent.putExtra("lo_code",lo.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstLo.size();
    }

    public static class StorageViewHolder extends RecyclerView.ViewHolder {

        protected TextView txt_kt;
        protected TextView txt_blanks;
        protected TextView txt_lo;

        public StorageViewHolder(View itemView) {
            super(itemView);
            txt_kt = (TextView) itemView.findViewById(R.id.txt_kt);
            txt_blanks = (TextView) itemView.findViewById(R.id.txt_blanks);
            txt_lo = (TextView) itemView.findViewById(R.id.txt_lo);
        }
    }
}
