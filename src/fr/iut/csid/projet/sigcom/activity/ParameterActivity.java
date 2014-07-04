package fr.iut.csid.projet.sigcom.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.iut.csid.projet.sigcom.R;

public class ParameterActivity extends Activity {
//	ATTRIBUTS
	private int dureeSignal;
	private EditText txt_dureeSignal;
	
	
	
//	METHODES
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parameter);
		
		txt_dureeSignal = (EditText) findViewById(R.id.txt_dureeSignal);
		txt_dureeSignal.setText(String.valueOf(MainActivity.getSoundParameters().getDureeSignal()));
				
		
//		LISTENER SUR LE BOUTON ENREGISTRER
		Button bt_enregistrerParametres = (Button) findViewById(R.id.bt_enregistrerParametres);
		bt_enregistrerParametres.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (!TextUtils.isEmpty(txt_dureeSignal.getText().toString())) {
					try {
						dureeSignal = Integer.parseInt(txt_dureeSignal.getText().toString());
						MainActivity.getSoundParameters().setDureeSignal(dureeSignal);
						finish();
					}
					catch(NumberFormatException e) {
						messageErreur("Erreur de saisie!");
					}
				}
				else {
					messageErreur("Saisir une valeur!");
				}
			}
		});
	}
	
	private void messageErreur(String message) {
		Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
	}
}
