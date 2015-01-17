package br.edu.fcsl.projetosharedpreferences.listener;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;

public class EscondeTecladoListener implements OnFocusChangeListener {

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (!hasFocus) {
			InputMethodManager manager = (InputMethodManager) v.getContext().getSystemService(
					Context.INPUT_METHOD_SERVICE);
			manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

}
