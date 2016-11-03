package com.example.anton.proyecto_browser;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private static AutoCompleteTextView et1;
    private static TextView t;
    private static WebView w;
    private static Button b;
    private static String s [];
    private static long nreg_afectados=-1;
    Vector v;
    BDBrowser bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(AutoCompleteTextView)findViewById(R.id.editText);
        t=(TextView)findViewById(R.id.textView);
        b=(Button)findViewById(R.id.button);
        b.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER)
                    cargarPagina(et1.getText().toString());
                return false;
            }
        });
        w=(WebView)findViewById(R.id.browser);
        bd=new BDBrowser(this);
        v=bd.obtenerSugerencias();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,v);
        et1.setAdapter(adapter);

        s=new String[4];
        w.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void buscar(View v){
            cargarPagina(et1.getText().toString());
            b.setVisibility(View.GONE);
            t.setVisibility(View.GONE);
            et1.setVisibility(View.GONE);
    }

    private void cargarPagina(String e){
        int n=-1;
        int m=-1;
        n=e.indexOf(".");
        m=e.indexOf(":");
        if(n<0 && m<0 ){
            w.loadUrl("https://www.google.es/search?q="+e);
            bd.insertar(e);
        }
        sugerencias(et1.getText().toString());
    }

    public void cargarAtras(View v){
        if (w.canGoBack())
            w.goBack();
            et1.setText(null);
    }

    public void cargarAdelante(View v){
        if(w.canGoForward()){
            w.goForward();
        }else{
            Toast.makeText(this,"No encuentra la pagina",Toast.LENGTH_LONG).show();
        }
    }

    public void home(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        et1.setText(null);
    }

    public void sugerencias(String s){
        nreg_afectados=bd.insertar(s);
        if (nreg_afectados <= 0) {
            Toast.makeText(this,"ERROR: No hay ninguna sugerencia.", Toast.LENGTH_LONG).show();
        }
    }
    public void ver(View v){
        Intent i = new Intent(this, Actividad2.class);
        i.putExtra("direccion", et1.getText().toString());
        startActivity(i);
    }




}
