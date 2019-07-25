package com.example.mediaescolar;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class QuartoBiActivity extends AppCompatActivity  {

// Declarando os tipos de variáveis

    double notaProv4, notaTrab4, medfim4;
    boolean ok = true;
    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto_bi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App Media Escolar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

// Instanciando elementos do layout

        TextView txtTit = findViewById(R.id.txtBI4);
        TextView txtMat4 = findViewById(R.id.txtMat4);
        TextView txtProv4 = findViewById(R.id.txtProv4);
        TextView txtTrab4 = findViewById(R.id.txtTrab4);
        Spinner s = findViewById(R.id.Spinner04);
        final EditText edtProv4 = findViewById(R.id.edtProv4);
        final EditText edtTrab4 = findViewById(R.id.edtTrab4);
        final TextView txtMed4 = findViewById(R.id.txtMed4);
        TextView txtSit4 = findViewById(R.id.txtSit4);
        final TextView txtMedFim4 = findViewById(R.id.txtMedFim4);
        final TextView txtSitFim4 = findViewById(R.id.txtSitFim4);
        Button btnCalc4 = findViewById(R.id.btnCalc4);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(),
                "print_bold_tt.ttf");

        txtTit.setTypeface(font);
        txtMat4.setTypeface(font);
        txtProv4.setTypeface(font);
        txtTrab4.setTypeface(font);
        edtProv4.setTypeface(font);
        edtTrab4.setTypeface(font);
        txtMed4.setTypeface(font);
        txtSit4.setTypeface(font);
        txtMedFim4.setTypeface(font);
        txtSitFim4.setTypeface(font);
        btnCalc4.setTypeface(font);

// Aqui é toda a declaração das listas do dropbox com o nome das matérias

        array_spinner = new String[]{Constant.MATEMATICA, Constant.BIOLOGIA, Constant.FILOSOFIA, Constant.FISICA, Constant.GEOGRAFIA, Constant.HISTORIA,
                Constant.INGLES, Constant.LITERATURA, Constant.PORTUGUES, Constant.QUIMICA, Constant.SOCIOLOGIA, Constant.ARTES,Constant.ESPORTES};
        Arrays.sort(array_spinner);


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_materia, array_spinner);
        s.setAdapter(adapter);

        btnCalc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//mudando icone de error

                Drawable ic =
                        getResources().getDrawable(R.drawable.ic_error);
                if (ic != null){
                    ic.setBounds(0,0,ic.getIntrinsicWidth(),ic.getIntrinsicHeight());
                }

// evitando o crash da aplicação

                try {

// verificando se as notas estão inseridas

                    if (edtProv4.getText().toString().length() > 0) {
                        notaProv4 = Double.parseDouble(edtProv4.getText().toString());
                    } else {
                        edtProv4.setError("Campo obrigatório",ic);
                        edtProv4.requestFocus();
                        ok = false;
                    }
                    if (edtTrab4.getText().toString().length() > 0) {
                        notaTrab4 = Double.parseDouble(edtTrab4.getText().toString());
                    } else {
                        edtTrab4.setError("Campo obrigatório",ic);
                        edtTrab4.requestFocus();
                        ok = false;
                    }

//Aquisitando notas digitadas
                    if (ok) {
                        medfim4 = (notaProv4 + notaTrab4) / 2;
                        txtMedFim4.setText(String.valueOf(medfim4));

                        if (medfim4 >= 7) txtSitFim4.setText("APROVADO");
                        else txtSitFim4.setText("REPROVADO");
                    }

                    // convertendo as minhas variáveis
                    notaProv4 = Double.parseDouble(edtProv4.getText().toString());
                    notaTrab4 = Double.parseDouble(edtTrab4.getText().toString());

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Os campos indicados são obrigatórios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sair) {

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}