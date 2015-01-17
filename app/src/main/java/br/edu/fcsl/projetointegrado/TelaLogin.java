package br.edu.fcsl.projetointegrado;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.fcsl.projetosharedpreferences.listener.EscondeTecladoListener;

public class TelaLogin extends Activity {

	private EditText usuario;
	private EditText senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		usuario = (EditText) findViewById(R.id.editUsuario);
		senha = (EditText) findViewById(R.id.editSenha);

		senha.setOnFocusChangeListener(new EscondeTecladoListener());
		usuario.setOnFocusChangeListener(new EscondeTecladoListener());
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void abrirQuestionario(View v) throws Exception {// método para abrir
		
		 String IMEI = "";
		 TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		 IMEI = tm.getDeviceId();
	     
	    // Toast.makeText(this,IMEI,Toast.LENGTH_LONG).show();
		/* abrirTela = new Intent(this,
				telaPerguntas.class);
		variaveisGlobais.login = usuario.getText().toString();
		startActivity(abrirTela);
		finish();
		Intent abrirTela1 = new Intent(this,
				TelaSugestao.class);
		variaveisGlobais.login = usuario.getText().toString();
		startActivity(abrirTela1);
		finish();
		Intent abrirTela2 = new Intent(this,
				TelaFinal.class);
		variaveisGlobais.login = usuario.getText().toString();
		startActivity(abrirTela2);
		finish();
		Intent abrirTela3 = new Intent(this,
				InformacoesUsuarioActivity.class);
		variaveisGlobais.login = usuario.getText().toString();
		startActivity(abrirTela3);
		finish();*/
		/*												// o questionário
		if (usuario.getText().toString().equals("")) {// verifica se o usuario
														// não foi informado
			usuario.setError("Usuário não informado");
		} else if (senha.getText().toString().equals("")) {// verifica se a
															// senha não foi
															// informado
			senha.setError("Senha não informada");
		} else if (usuario.getText().toString().equals("")
				&& senha.getText().toString().equals("")) {// verifica se o
															// usuario e senha
															// não foram
															// informados
			senha.setError("Usuário e senha não informados");
		} else {// realiza a autenticação do login
			String retorno = "";
			try {
				URL url = new URL(
						"http://terteus.net.br/logar1.php?usuario="
								+ usuario.getText().toString() + "&senha="
								+ senha.getText().toString());
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				InputStream inputStream = urlConnection.getInputStream();
				Scanner leitor = new Scanner(new InputStreamReader(inputStream));

				while (leitor.hasNextLine()) {
					retorno += leitor.nextLine();
				}
				urlConnection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this,
						"Dados incorretos ou questionário já respondido",
						Toast.LENGTH_LONG).show();
			}

			if (retorno.equals("1")) {// autenticação realizada com sucesso
				Intent abrirTela = new Intent(this,
						InformacoesUsuarioActivity.class);
				variaveisGlobais.login = usuario.getText().toString();
				startActivity(abrirTela);
				finish();
			} else if (retorno.equals("0")) {// erro na autenticação com usuario
												// ou senha incorretos
				Toast.makeText(this,
						"Dados incorretos ou questionário já respondido",
						Toast.LENGTH_LONG).show();
			}
			
		}
		*/
	}

}
