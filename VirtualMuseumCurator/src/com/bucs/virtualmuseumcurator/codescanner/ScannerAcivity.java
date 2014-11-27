package com.bucs.virtualmuseumcurator.codescanner;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.bucs.virtualmuseumcurator.R;

public class ScannerAcivity extends ActionBarActivity {

	
	private Activity context;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=this;
        setContentView(R.layout.activity_scanner_acivity);
        
        try {
            Button scanner = (Button)findViewById(R.id.scanner);
            scanner.setOnClickListener(new OnClickListener() {
                
                public void onClick(View v) {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, 0);
                	
                   /* Intent QRResultintent= new Intent();
                    QRResultintent.setClass(context, QRResultActivity.class);
                    QRResultintent.putExtra("artlink", "http://edocent.herokuapp.com/curator/art/2/");
                    startActivity(QRResultintent);*/
                	
                }
 
            });
            
            Button scanner2 = (Button)findViewById(R.id.scanner2);
            scanner2.setOnClickListener(new OnClickListener() {
                
                public void onClick(View v) {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                    startActivityForResult(intent, 0);


               /* Intent QRResultintent= new Intent();
                QRResultintent.setClass(context, QRResultActivity.class);
                QRResultintent.putExtra("artlink", "http://edocent.herokuapp.com/curator/art/2/");
                startActivity(QRResultintent);*/
                    
                }
 
            });
            
        } catch (ActivityNotFoundException anfe) {
            Log.e("onCreate", "Scanner Not Found", anfe);
        }
        
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format , Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                Intent QRResultintent= new Intent();
                QRResultintent.setClass(this.context, QRResultActivity.class);
                QRResultintent.putExtra("artlink", contents);
                startActivity(QRResultintent);
                
                
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                
            }
        }
    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scanner_acivity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
