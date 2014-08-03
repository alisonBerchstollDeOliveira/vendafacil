package com.mmidgard.vendas;

import android.app.Activity;
import android.content.Intent;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class GlobalActivity extends Activity{

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 1)
			Crouton.makeText(GlobalActivity.this, "Cliente inserido com sucesso!", Style.INFO).show();
		if (requestCode == 2 && resultCode == 2)
			Crouton.makeText(GlobalActivity.this, "Produto inserido com sucesso!", Style.INFO).show();
	}
}
