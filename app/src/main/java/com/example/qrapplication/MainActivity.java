package com.example.qrapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * proyecto de qr
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button_escanear)
    Button mBotonEscanear;
    @BindView(R.id.textView_codigo_escaneado)
    TextView mCodigoEscaneado;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            mCodigoEscaneado.setText("El codigo de barras escaneado es:  " + result.getContents());
        }else{
            mCodigoEscaneado.setText("Error al leer el codigo de barras");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBotonEscanear.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_escanear:
                    new IntentIntegrator(MainActivity.this).initiateScan();
                    break;
            }

        }
    };
}
