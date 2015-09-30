package br.com.ciandt.helio.worldwonders.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.ciandt.helio.worldwonders.ui.activity.R;

public class MasterDetailFragment extends Fragment{

    private static final String BUNDLE_KEY_SELECTED_ITEM = "selected_item";

    private Activity mActivity;

    public MasterDetailFragment() {
        // Required empty public constructor
    }

    public static MasterDetailFragment newInstance(final String valorLista) {
        MasterDetailFragment masterDetailFragment = new MasterDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_SELECTED_ITEM, valorLista);
        masterDetailFragment.setArguments(bundle);

        return masterDetailFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_detail, container, false);
        LinearLayout linearLayoutMasterDetail = (LinearLayout) rootView.findViewById(R.id.linear_layout_master_detail);

        String item = loadItem();

        if (item != null && !item.isEmpty()) {

            // Get the components from the screen
            TextView textViewItem = (TextView) linearLayoutMasterDetail.findViewById(R.id.text_view_master_detail_item);

            // Set values to the components
            textViewItem.setText(item);
        }

        return rootView;
    }

    private String loadItem() {
        String item = "";
        Bundle args = getArguments();

        if (args != null) {
            item = args.getString(BUNDLE_KEY_SELECTED_ITEM);
        }

        return item;
    }

}
