package br.edu.fcsl.projetointegrado;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.edu.fcsl.entidade.variaveisGlobais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class InformacoesUsuarioActivity extends Activity {

	
	public String sugestao = "";
	private String vetPerguntas[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacoes_usuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.informacoes_usuario, menu);
		return true;
	}

	public void abrirQuestoes(View v) {
		
		String retorno = "";
		try {
			URL url = new URL(//consulta a quantidade de perguntas
					"http://terteus.net.br/consultaQuantidadePerguntas1.php");
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			Scanner leitor = new Scanner(new InputStreamReader(inputStream));

			while (leitor.hasNextLine()) {
				retorno = leitor.nextLine();
			}
			urlConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int n = Integer.parseInt(retorno);
		variaveisGlobais.qtdPerguntas=n;


		String retornoPerguntas = consultaPhp("http://terteus.net.br/listarPerguntas1.php");
		vetPerguntas = retornoPerguntas.split("#");// vetor com todas as perguntas do banco
		
	Intent abrirPerguntas = new Intent(this, telaPerguntas.class);
		abrirPerguntas.putExtra("pergunta", vetPerguntas[variaveisGlobais.posicao]);
		startActivityForResult(abrirPerguntas, 0);// abre a tela da primeira pergunta
		
		
	}

	public String consultaPhp(String url1) {// método para realizar todas as consultas no banco
		String retornoPerguntas = "";
		try {
			URL url = new URL(url1);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			Scanner leitor = new Scanner(new InputStreamReader(inputStream));

			while (leitor.hasNextLine()) {
				retornoPerguntas += leitor.nextLine();
			}
			urlConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retornoPerguntas;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
				
		
		if (requestCode == 0) {// verifica se deve abrir mais telas de pergunta "fechada"
			
			if (variaveisGlobais.posicao < vetPerguntas.length) {
				Intent abrirPerguntas = new Intent(this, telaPerguntas.class);
				abrirPerguntas.putExtra("pergunta", vetPerguntas[variaveisGlobais.posicao]);
				startActivityForResult(abrirPerguntas, 0);// abre a tela de pergunta
				
			} else {// abre a tela de sugestão
				Intent abrirSugestao = new Intent(this, TelaSugestao.class);
				startActivityForResult(abrirSugestao, 1);
			}
		}else if (requestCode == 1 && variaveisGlobais.opcao == 0) {// abre da segunda tela de pergunta "fechada" em diante
			int posicaoAtual = variaveisGlobais.posicao;
			if (variaveisGlobais.posicao < vetPerguntas.length) {
				Intent abrirPerguntas = new Intent(this, telaPerguntas.class);
				abrirPerguntas.putExtra("pergunta", vetPerguntas[variaveisGlobais.posicao]);
				startActivityForResult(abrirPerguntas, 0);// abre a tela de pergunta
				variaveisGlobais.vetResp[posicaoAtual]=variaveisGlobais.resposta;
			} else {
				Intent abrirSugestao = new Intent(this, TelaSugestao.class);
				abrirSugestao.putExtra("sugestao", sugestao);
				startActivityForResult(abrirSugestao, 1);// abre a tela de sugestão
			}
		}
		else if (requestCode == 1 && variaveisGlobais.opcao == 1) {// depois de passar pela tela de sugestão
			Intent telaFinal = new Intent(this, TelaFinal.class);
			startActivity(telaFinal);// abre a tela final
			finish();
		}
		
	}
}
