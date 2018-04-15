package info.androidhive.materialtabs.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.adapters.StorageAdapter;
import info.androidhive.materialtabs.adapters.StorageInfoAdapter;
import info.androidhive.materialtabs.models.Lo;
import info.androidhive.materialtabs.models.StorageInfo;

public class StorageInfoActivity extends AppCompatActivity {

    RecyclerView rcv_info_storage;
    List<StorageInfo> lstStorageInfo;
    private StorageInfoAdapter storageInfoAdapter;
    private GridLayoutManager layoutRecycleView;
    private Toolbar toolbar;
    int lo_code;
    public static int START_POS = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_info);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lo_code = getIntent().getIntExtra("lo_code",1);
        Toast.makeText(this,"Lô: "+lo_code,Toast.LENGTH_SHORT).show();
        lstStorageInfo = new ArrayList<>();
        layoutRecycleView = new GridLayoutManager(StorageInfoActivity.this, 3);
        bindView();
        rcv_info_storage.setHasFixedSize(true);
        rcv_info_storage.setLayoutManager(layoutRecycleView);
        createData();
        storageInfoAdapter = new StorageInfoAdapter(lstStorageInfo,getApplicationContext());
        rcv_info_storage.setAdapter(storageInfoAdapter);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_item_storage,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_add_item:
                Toast.makeText(this,"Add item: ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_move_item:
                Toast.makeText(this,"Move item: ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_drop_item:
                Toast.makeText(this,"Drop item: ",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);

    }

    private void createData() {
        lstStorageInfo.add(new StorageInfo(1,2));
        lstStorageInfo.add(new StorageInfo(2,0));
        lstStorageInfo.add(new StorageInfo(3,1));
        lstStorageInfo.add(new StorageInfo(4,3));
        lstStorageInfo.add(new StorageInfo(5,2));
        lstStorageInfo.add(new StorageInfo(6,2));
        lstStorageInfo.add(new StorageInfo(7,0));
        lstStorageInfo.add(new StorageInfo(8,4));
        lstStorageInfo.add(new StorageInfo(9,1));
    }

    private void bindView() {
        rcv_info_storage = (RecyclerView) findViewById(R.id.rcv_info_storage);
    }
}
