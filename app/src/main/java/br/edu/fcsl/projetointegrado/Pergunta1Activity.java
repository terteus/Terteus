package br.edu.fcsl.projetointegrado;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Pergunta1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pergunta1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pergunta1, menu);
		return true;
	}

}
