package com.example.prueba01recuperacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWeb extends AppCompatActivity {

    //Declaramos un componente de tipo web view.
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Cambia el diseño al de activity_web que se encuentra en la carpeta de layout.
        setContentView(R.layout.activity_web);

        /*Buscamos el web view que se encuentra en el layout por el id
        y lo asociamos a la variable web view antes declarada. */
        wv1 = (WebView) findViewById(R.id.wv1);

        //Recoge la informacion enviada por el secondfragment
        String url = getIntent().getStringExtra("url");

        //Crea una nueva web de busqueda
        wv1.setWebViewClient(new WebViewClient());

        /*Carga el url antes especificado agregando a la cadena la seccion https
        para ahorrar que el usuario escriba esa parte*/
        wv1.loadUrl("https://" + url);
    }

    /* funcion para volver atras luego de estar en el navegador
    se asocia al boton Anterior que se ve en el activity web.*/
    public void back(View view) {
        finish();
    }
}