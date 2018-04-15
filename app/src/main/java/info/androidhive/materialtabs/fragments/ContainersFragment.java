package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.adapters.ContainersAdapter;
import info.androidhive.materialtabs.models.Container;

public class ContainersFragment extends Fragment {

    private OnContainersFragmentInteractionListener mListener;
    private View view;
    RecyclerView rcv_container;
    ProgressBar prb_loaddata_container;
    EditText ed_container_search;
    List<Container> lstContainer;
    List<Container> lstContainerClone;
    private ContainersAdapter containersAdapter;
    private LinearLayoutManager layoutRecycleView;
    private ArrayList<Container> newsLstContainer = new ArrayList<>();

    public ContainersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_containers, container, false);
        lstContainer = new ArrayList<>();
        lstContainerClone = new ArrayList<>();
        layoutRecycleView = new LinearLayoutManager(getActivity());
        layoutRecycleView.setOrientation(LinearLayoutManager.VERTICAL);
        bindView();
        setChangeTextSearch();
        rcv_container.setHasFixedSize(true);
        rcv_container.setLayoutManager(layoutRecycleView);
        createData();
        cloneDataContainers();
        containersAdapter = new ContainersAdapter(lstContainer,getContext());
        rcv_container.setAdapter(containersAdapter);
        return view;
    }

    private void setChangeTextSearch() {
        ed_container_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newsLstContainer.clear();
                for (Container container: lstContainerClone){
                    if(container.getCode().toUpperCase().contains(s.toString().toUpperCase().trim())){
                        newsLstContainer.add(container);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                containersAdapter.updateData(newsLstContainer);
            }
        });
    }

    private void createData() {
        lstContainer.add(new Container("CTN001",10,"4x3x2"));
        lstContainer.add(new Container("CTN002",10,"4x3x2"));
        lstContainer.add(new Container("CTN003",10,"4x3x2"));
        lstContainer.add(new Container("CTN004",10,"4x3x2"));
        lstContainer.add(new Container("CTN005",10,"4x3x2"));
        lstContainer.add(new Container("CTN006",10,"4x3x2"));
        lstContainer.add(new Container("CTN007",10,"4x3x2"));
        lstContainer.add(new Container("CTN008",10,"4x3x2"));
        lstContainer.add(new Container("CTN009",10,"4x3x2"));
        lstContainer.add(new Container("CTN010",10,"4x3x2"));
        lstContainer.add(new Container("CTN011",10,"4x3x2"));
        lstContainer.add(new Container("CTN012",10,"4x3x2"));
        lstContainer.add(new Container("CTN013",10,"4x3x2"));
        lstContainer.add(new Container("CTN014",10,"4x3x2"));
        lstContainer.add(new Container("CTN015",10,"4x3x2"));
    }
    private void cloneDataContainers(){
        for (Container container: lstContainer){
            lstContainerClone.add(container);
        }
    }

    private void bindView() {
        rcv_container = (RecyclerView) view.findViewById(R.id.rcv_container);
        ed_container_search = (EditText) view.findViewById(R.id.ed_container_search);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onContainersFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContainersFragmentInteractionListener) {
            mListener = (OnContainersFragmentInteractionListener) context;
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

    public interface OnContainersFragmentInteractionListener {
        void onContainersFragmentInteraction(Uri uri);
    }
}
