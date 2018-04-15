package info.androidhive.materialtabs.adapters;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.DragEvent;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.DialogCustomActivity;
import info.androidhive.materialtabs.activity.DialogMoveStorageActivity;
import info.androidhive.materialtabs.activity.StorageInfoActivity;
import info.androidhive.materialtabs.models.StorageInfo;

/**
 * Created by ThinhNK on 4/8/2018.
 */

public class StorageInfoAdapter extends RecyclerView.Adapter<StorageInfoAdapter.StorageInfoViewHolder> {

    private List<StorageInfo> lstStorageInfo;
    private Context context;
    private LayoutInflater mLayoutInflater;
    public StorageInfoAdapter(List<StorageInfo> lstStorageInfo,Context context) {
        this.lstStorageInfo = lstStorageInfo;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public StorageInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_in_container, parent, false);
        return new StorageInfoAdapter.StorageInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StorageInfoViewHolder holder, final int position) {
        final StorageInfo storageInfo = lstStorageInfo.get(position);
        holder.txt_container_empty.setText(String.valueOf(storageInfo.getNumEmpty()));
        holder.txt_location.setText(String.valueOf(storageInfo.getLocation()));
        if(storageInfo.getNumEmpty()==0){
            holder.img_item_storage.setImageResource(R.drawable.icon_full_container);
        }else if(storageInfo.getNumEmpty()==4){
            holder.img_item_storage.setImageResource(R.drawable.icon_empty_container);
        }else{
            holder.img_item_storage.setImageResource(R.drawable.icon_have_container);
        }
        holder.img_item_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LONGCLICK", "onClick: ");
                PopupMenu popup = new PopupMenu(context, holder.img_item_storage);
                popup.getMenuInflater().inflate(R.menu.menu_item_storage,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mn_add_item:
                                addContainerItem(position);
                                break;
                            case R.id.mn_move_item:
                                Intent intent = new Intent(context, DialogCustomActivity.class);
                                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                break;
                            case R.id.mn_drop_item:

                                deleteContainerItem(position);
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
        holder.img_item_storage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("","");
                View.DragShadowBuilder builder = new View.DragShadowBuilder(v);
                if(StorageInfoActivity.START_POS==-1){
                    StorageInfoActivity.START_POS = position;
                }
                holder.img_item_storage.startDrag(data,builder,v,0);
                return true;
            }
        });
        holder.img_item_storage.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch(action) {
                    case DragEvent.ACTION_DROP:
                        if(StorageInfoActivity.START_POS!=-1 && StorageInfoActivity.START_POS!=position){
                            if(lstStorageInfo.get(StorageInfoActivity.START_POS).getNumEmpty()<4 && lstStorageInfo.get(position).getNumEmpty()>0){
                                StorageInfo storageInfoStart = lstStorageInfo.get(StorageInfoActivity.START_POS);
                                storageInfoStart.setNumEmpty(lstStorageInfo.get(StorageInfoActivity.START_POS).getNumEmpty() + 1);
                                lstStorageInfo.set(StorageInfoActivity.START_POS, storageInfoStart);

                                StorageInfo storageInfoEnd = lstStorageInfo.get(position);
                                storageInfoEnd.setNumEmpty(lstStorageInfo.get(position).getNumEmpty() - 1);
                                lstStorageInfo.set(position, storageInfoEnd);
                                notifyDataSetChanged();
                            }else{
                                Toast.makeText(context,"Không thể thực hiện thao tác này",Toast.LENGTH_SHORT).show();
                            }
                        }
                        StorageInfoActivity.START_POS = -1;
                        break;
                }
                return true;
            }
        });

    }

    public void addContainerItem(int index) {
        if(lstStorageInfo.get(index).getNumEmpty()>0) {
            for (int i = 0; i < lstStorageInfo.size(); i++) {
                if (i == index) {
                    StorageInfo storageInfo = lstStorageInfo.get(i);
                    storageInfo.setNumEmpty(lstStorageInfo.get(index).getNumEmpty() - 1);
                    lstStorageInfo.set(index, storageInfo);
                    break;
                }
            }
            notifyDataSetChanged();
        }else {
            Toast.makeText(context,"Container đã đầy",Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteContainerItem(int index) {
        if(lstStorageInfo.get(index).getNumEmpty()<4){
            for (int i=0;i<lstStorageInfo.size();i++){
                if(i==index){
                    StorageInfo storageInfo = lstStorageInfo.get(i);
                    storageInfo.setNumEmpty(lstStorageInfo.get(index).getNumEmpty()+1);
                    lstStorageInfo.set(index,storageInfo);
                    break;
                }
            }
            notifyDataSetChanged();
        }else {
            Toast.makeText(context,"Không có container",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return lstStorageInfo.size();
    }

    public static class StorageInfoViewHolder extends RecyclerView.ViewHolder {

        protected TextView txt_location;
        protected ImageView img_item_storage;
        protected TextView txt_container_empty;
        protected FrameLayout frm_container;

        public StorageInfoViewHolder(View itemView) {
            super(itemView);
            txt_container_empty = (TextView) itemView.findViewById(R.id.txt_container_empty);
            txt_location = (TextView) itemView.findViewById(R.id.txt_location);
            img_item_storage = (ImageView) itemView.findViewById(R.id.img_item_storage);
            frm_container = (FrameLayout) itemView.findViewById(R.id.frm_container);
        }
    }
}
