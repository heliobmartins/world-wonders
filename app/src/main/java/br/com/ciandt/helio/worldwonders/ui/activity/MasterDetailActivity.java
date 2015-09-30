package br.com.ciandt.helio.worldwonders.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.ciandt.helio.worldwonders.ui.fragments.MasterDetailFragment;

import static br.com.ciandt.helio.worldwonders.ui.fragments.MasterDetailListFragment.*;

public class MasterDetailActivity extends AppCompatActivity implements OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);        
    }

    @Override
    public void onItemSelected(String item) {
        MasterDetailFragment masterDetailFragment = MasterDetailFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_container,masterDetailFragment).commit();
    }
}
