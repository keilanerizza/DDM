package com.example.keilane.adivinheonumero;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnKeyListener, View.OnClickListener {

    private Button btnVerificar;
    private TextView mensagem;
    private EditText entrada;
    private TextView tentativas;
    private int numeroSorteado;
    private int tentativasRestantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preparacaoInicial();
    }

    private void preparacaoInicial() {
        mensagem = (TextView) findViewById(R.id.mensagem);
        entrada = (EditText) findViewById(R.id.entrada);
        entrada.setOnKeyListener(this);
        btnVerificar = (Button) findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(this);
        tentativas = (TextView) findViewById(R.id.tentativas);
        geraNumero();
    }

    private void geraNumero(){
        tentativasRestantes = 3;
        tentativas.setText("");
        numeroSorteado = (int) (Math.random() * 101);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent event) {
        if (i == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            verificar();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        verificar();
    }

    private void verificar() {
        int numero = Integer.parseInt(entrada.getText().toString());
        if (numero == numeroSorteado) {
            mensagem.setText(R.string.texto2);
            geraNumero();
        } else if (numero > numeroSorteado) {
            mensagem.setText(R.string.texto3);
        } else if (numero < numeroSorteado) {
            mensagem.setText(R.string.texto4);
        }
        entrada.setText("");
        contagemTentativas();
    }

    private void contagemTentativas() {
        tentativasRestantes--;
        tentativas.setText(String.format(getString(R.string.texto5), tentativasRestantes));

        if(tentativasRestantes == 0) {
            mensagem.setText(R.string.texto6);
            geraNumero();
        }
    }
}
