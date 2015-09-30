package br.com.ciandt.helio.worldwonders.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.ciandt.helio.worldwonders.ui.activity.MasterDetailActivity;
import br.com.ciandt.helio.worldwonders.ui.activity.R;

public class MasterDetailListFragment extends Fragment {

    private MasterDetailActivity mMasterDefailActivity;
    private OnItemSelectedListener mListener;
    private ListView mListView;

    public MasterDetailListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMasterDefailActivity = (MasterDetailActivity) activity;
        try {
            mListener = mMasterDefailActivity;
        } catch (ClassCastException cce){
            cce.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_detail_list, container, false);

        mListView = (ListView) rootView.findViewById(R.id.list_view_feed);

        loadItems();
        setListeners();

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface  OnItemSelectedListener{
        public void onItemSelected(String item);
    }

    private void loadItems() {
        List<String> itemList = new ArrayList<>();
        itemList.add("Yamaha YZF-R1");
        itemList.add("Yamaha YZF-R6 ");
        itemList.add("Honda Hornet");
        itemList.add("Honda CBR 600RR");
        itemList.add("Honda CB 1000R");
        itemList.add("Ducati Panigale 1199");
        itemList.add("Ducati Diavel");
        itemList.add("MV Agusta F3");
        itemList.add("MV Agusta F4");
        itemList.add("BMW 1000RR S");
        itemList.add("BMW GS800");
        itemList.add("BMW GS1200");
        itemList.add("Bimota DB7");

        final ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(mMasterDefailActivity, android.R.layout.simple_list_item_1, itemList);
        mListView.setAdapter(itemAdapter);
    }

    private void setListeners() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    String item = (String) mListView.getAdapter().getItem(position);
                    mListener.onItemSelected(item);
                }
            }
        });
    }

}
