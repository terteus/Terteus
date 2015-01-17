package br.edu.fcsl.projetointegrado;

import br.edu.fcsl.entidade.variaveisGlobais;
import br.edu.fcsl.projetosharedpreferences.listener.EscondeTecladoListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TelaSugestao extends Activity {

	private EditText sugestoes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_sugestao);
		sugestoes= (EditText)findViewById(R.id.editText1);
		sugestoes.setText(variaveisGlobais.sugestao);
		sugestoes.setOnFocusChangeListener(new EscondeTecladoListener());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_sugestao, menu);
		return true;
	}
	
	public void avancar(View v){// realiza as operações para avançar a tela
		variaveisGlobais.posicao++;
		variaveisGlobais.sugestao=sugestoes.getText().toString();
		variaveisGlobais.opcao=1;
		finish();
	}
	public void voltar(View v){// realiza as operaçõeses para voltar a tela
		variaveisGlobais.posicao--;
		variaveisGlobais.sugestao=sugestoes.getText().toString();// grava a sugestão na variável global
		variaveisGlobais.opcao=0;
		finish();// fecha a tela
	}

}
