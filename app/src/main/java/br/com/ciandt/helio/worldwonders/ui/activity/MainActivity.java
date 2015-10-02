package br.com.ciandt.helio.worldwonders.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.ciandt.helio.worldwonders.entity.vo.OperationError;
import br.com.ciandt.helio.worldwonders.listener.OperationListener;
import br.com.ciandt.helio.worldwonders.manager.WonderManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundleExtras = getIntent().getExtras();

        WonderManager wonderManager = new WonderManager();

        wonderManager.getAllWonders(new OperationListener<String>() {
            @Override
            public void onOperationSuccess(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOperationError(OperationError error) {
                Toast.makeText(MainActivity.this,error.getErrorMessage(),Toast.LENGTH_SHORT).show();
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
