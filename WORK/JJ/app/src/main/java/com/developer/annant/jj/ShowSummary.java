package com.developer.annant.jj;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ShowSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_summary);

        Bundle bundle = getIntent().getExtras();
        String printOut = bundle.getString("printValue");


    TextView txtview = (TextView) findViewById(R.id.show_sumup);
        txtview.setText(printOut);
    }
}
