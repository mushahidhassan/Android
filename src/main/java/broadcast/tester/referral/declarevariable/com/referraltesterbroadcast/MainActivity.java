package broadcast.tester.referral.declarevariable.com.referraltesterbroadcast;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//test
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et_packageName);
        Button btn = (Button) findViewById(R.id.btn_send);
        btn.setOnClickListener(this);
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



    public void broadcastIntent(String packageName) {

        Intent i = new Intent("com.android.vending.INSTALL_REFERRER");
        i.setPackage(packageName);
        // referrer is a composition of the parameter of the campaing
        i.putExtra(
                "referrer",
                "utm_source=test_source&utm_medium=test_medium&utm_term=test_term&utm_content=test_content&utm_campaign=test_name"
        );
        sendBroadcast(i);
        openNextApp(packageName);
    }

    private void openNextApp(String packageName) {
        // this is awesome
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);//"test.reciever.referral.declarevariable.com.referralrecievertest");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        String packageName = et.getText().toString();
        broadcastIntent(packageName);
    }
}
