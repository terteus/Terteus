package br.edu.fcsl.projetointegrado;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.edu.fcsl.entidade.variaveisGlobais;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class TelaFinal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_final);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_final, menu);

		return true;
	}

	public void enviarRespostas(View v) {// método para enviar as respostas

		String retorno = "";
		try {
			URL url = new URL(// consulta id do usuario
					"http://terteus.net.br/ListaIDUsuario.php?nome="
							+ variaveisGlobais.login);
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

		variaveisGlobais.idUsuario = Integer.parseInt(retorno);// id do user

		String retornoId = consultaPhp("http://terteus.net.br/listarIdPerguntas1.php");
		String vetId[] = retornoId.split("#");// vetor contendo todos os ids das
												// perguntas

		for (int i = 0; i < variaveisGlobais.qtdPerguntas; i++) {

			consultaPhp("http://terteus.net.br/gravarRespostas.php?resposta="
					+ variaveisGlobais.vetResp[i] + "&id_usuario="
					+ variaveisGlobais.idUsuario + "&id_pergunta=" + vetId[i]); // grava
			// no
			// banco
			// a
			// quantidade
			// de
			// respostas
			// sim
			// e
			// nao
			// Toast.makeText(this, String.valueOf(vetId[i]),
			// Toast.LENGTH_SHORT).show();
		}
		
		consultaPhp("http://terteus.net.br/cadastrarSugestao.php?descricao='"+ variaveisGlobais.sugestao+ "'&usuario="+ variaveisGlobais.idUsuario+"&questionario="+variaveisGlobais.questionario);// gravar a sugestão no banco no
											// servidor
		 
		// consultaPhp("http://terteus.com.br/updateStatus.php?id="+variaveisGlobais.idUsuario);//
		// bloqueia o usuário após responder o questionário
		finish();
	}

	public String consultaPhp(String url1) {// m�todo para realizar as conex�es
											// com o banco no servidor
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
}
