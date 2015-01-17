package br.edu.fcsl.projetointegrado;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class InformacoesUsuarioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacoes_usuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.informacoes_usuario, menu);
		return true;
	}
	
	public void abrirPerguntas(View v){
		Intent abrirPerguntas = new Intent(this, Pergunta1Activity.class);
		startActivity(abrirPerguntas);
	}
}
