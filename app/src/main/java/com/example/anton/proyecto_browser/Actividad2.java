package com.example.anton.proyecto_browser;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {

    private WebView w;
    private static EditText et2=null;
    MainActivity Texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        w=(WebView)findViewById(R.id.webview);
        w.setWebViewClient(new WebViewClient());

        Bundle bundle = getIntent().getExtras();
        String dato =bundle.getString("direccion");
        w.loadUrl("http://"+dato);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_actividad2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void finalizar(View v){

        finish();
    }

}
