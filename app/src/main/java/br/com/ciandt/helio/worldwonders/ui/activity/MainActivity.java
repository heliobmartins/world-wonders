package br.com.ciandt.helio.worldwonders.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.ciandt.helio.worldwonders.entity.Wonder;
import br.com.ciandt.helio.worldwonders.entity.vo.OperationError;
import br.com.ciandt.helio.worldwonders.listener.OperationListener;
import br.com.ciandt.helio.worldwonders.manager.WonderManager;
import br.com.ciandt.helio.worldwonders.ui.adapter.FeedBaseAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundleExtras = getIntent().getExtras();

        final ListView listView = (ListView) findViewById(R.id.list_view_wonders);

        WonderManager wonderManager = new WonderManager();

        wonderManager.getAllWonders(new OperationListener<List<Wonder>>() {
            @Override
            public void onOperationSuccess(List<Wonder> s) {
                FeedBaseAdapter feedBaseAdapter = new FeedBaseAdapter(MainActivity.this, s);
                listView.setAdapter(feedBaseAdapter);
                Toast.makeText(MainActivity.this, "Maravilhas Atualizadas com Sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOperationError(OperationError error) {
                Toast.makeText(MainActivity.this, error.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, bundleExtras.getString("email"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
