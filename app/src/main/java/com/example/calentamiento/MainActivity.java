package com.example.calentamiento;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btEnviar;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEnviar = findViewById(R.id.btLanzar);
        tvResult = findViewById(R.id.tvResult);

        ActivityResultLauncher<Intent> lanzador = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        tvResult.setText(data.getStringExtra(ResultActivity.CLAVE_NUMERO));
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        tvResult.setText("El usuario ha cancelado la operación");
                        tvResult.setTextColor(Color.RED);
                    }
                }
        );

        btEnviar.setOnClickListener(view -> {
            Intent i = new Intent(this, ResultActivity.class);
            lanzador.launch(i);
        });
    }
}