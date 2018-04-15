package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.adapters.StorageAdapter;
import info.androidhive.materialtabs.models.Lo;

public class StorageFragment extends Fragment {

    private OnStorageFragmentInteractionListener mListener;
    private View view;
    RecyclerView rcv_storage;
    List<Lo> lstLo;
    private StorageAdapter storageAdapter;
    private GridLayoutManager layoutRecycleView;

    public StorageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_storage, container, false);
        lstLo = new ArrayList<>();
        layoutRecycleView = new GridLayoutManager(getActivity(), 3);
        bindView();
        rcv_storage.setHasFixedSize(true);
        rcv_storage.setLayoutManager(layoutRecycleView);
        createData();
        storageAdapter = new StorageAdapter(lstLo,getContext());
        rcv_storage.setAdapter(storageAdapter);
        return view;
    }
    private void bindView() {
        rcv_storage = (RecyclerView) view.findViewById(R.id.rcv_storage);
    }
    private void createData(){
        lstLo.add(new Lo(1,10,"4x3x2"));
        lstLo.add(new Lo(2,19,"4x3x2"));
        lstLo.add(new Lo(3,18,"4x3x2"));
        lstLo.add(new Lo(4,17,"4x3x2"));
        lstLo.add(new Lo(5,9,"4x3x2"));
        lstLo.add(new Lo(6,8,"4x3x2"));
        lstLo.add(new Lo(7,12,"4x3x2"));
        lstLo.add(new Lo(8,11,"4x3x2"));
        lstLo.add(new Lo(9,10,"4x3x2"));
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onStorageFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStorageFragmentInteractionListener) {
            mListener = (OnStorageFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnStorageFragmentInteractionListener {
        // TODO: Update argument type and name
        void onStorageFragmentInteraction(Uri uri);
    }
}
