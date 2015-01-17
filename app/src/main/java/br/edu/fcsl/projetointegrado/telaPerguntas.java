package br.edu.fcsl.projetointegrado;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import br.edu.fcsl.entidade.variaveisGlobais;

public class telaPerguntas extends Activity {

	private RadioGroup resposta;
	private TextView edit;
	private TextView cont;
	int id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pergunta1);
		resposta = (RadioGroup) findViewById(R.id.radioGroup);
		edit = (TextView) findViewById(R.id.textoPergunta);
		cont = (TextView) findViewById(R.id.TextCont);
		cont.setText(String.valueOf(variaveisGlobais.posicao + 1) + " / "
				+ String.valueOf(variaveisGlobais.qtdPerguntas));

		if (variaveisGlobais.vetResp[variaveisGlobais.posicao] == null) {//verifica se o usuario já havia selecionado a resposta anteriormente
			resposta.check(R.id.radioSim);
		} else if (variaveisGlobais.vetResp[variaveisGlobais.posicao]
				.equals("sim")) {// pré-seleciona a resposta que o usuario havia marcado anteriormente
			resposta.check(R.id.radioSim);
		} else if (variaveisGlobais.vetResp[variaveisGlobais.posicao]
				.equals("nao")) {// pré-seleciona a resposta que o usuario havia marcado anteriormente
			resposta.check(R.id.radioNao);
		} else {
			resposta.check(R.id.radioSim);
		}

		String pergunta = getIntent().getStringExtra("pergunta");
		edit.setText(pergunta);// altera o campo pergunta para a pergunta que deve ser exibida

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pergunta1, menu);
		return true;
	}

	public void avancar(View v) {// realiza as operações para avançar a tela
		switch (resposta.getCheckedRadioButtonId()) {
		case R.id.radioSim:// caso seja selecionado a opção sim
			variaveisGlobais.vetResp[variaveisGlobais.posicao] = "sim";// grava a resposta no vetor global
			break;

		case R.id.radioNao:
			variaveisGlobais.resposta = "nao";// caso seja selecionado a opção nao
			variaveisGlobais.vetResp[variaveisGlobais.posicao] = "nao";// grava a resposta no vetor global
			break;
		}
		variaveisGlobais.posicao++;

		finish();// fecha a tela
	}

	public void voltar(View v) {// realiza operações para voltar a tela
		if (variaveisGlobais.posicao > 0) {
			variaveisGlobais.posicao--;
		}
		finish();// fecha a tela
	}
}
