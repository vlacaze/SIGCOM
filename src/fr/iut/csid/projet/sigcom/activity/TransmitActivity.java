package fr.iut.csid.projet.sigcom.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.iut.csid.projet.sigcom.R;
import fr.iut.csid.projet.sigcom.converter.MorseCodeConverter;
import fr.iut.csid.projet.sigcom.manager.SoundTransmitManager;

public class TransmitActivity extends Activity {
//	ATTRIBUTS
	private int tempsSignal;
	private EditText txt_emetteurMessage;
	private SoundTransmitManager soundTransmitManager;
	
//	METHODES
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transmit);
		
		txt_emetteurMessage = (EditText) findViewById(R.id.txt_emetteurMessage);
		tempsSignal = MainActivity.getSoundParameters().getDureeSignal();
		
//		LISTENER SUR LE BOUTON EMETTRE
		Button bt_emettre = (Button) findViewById(R.id.bt_emettre);
		bt_emettre.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!TextUtils.isEmpty(txt_emetteurMessage.getText().toString())) {
					String text = txt_emetteurMessage.getText().toString();
					
//					CONTROLE DE LA CHAINE
					if(controleChaine(text)) {
						String[] pattern = MorseCodeConverter.pattern(text);
						soundTransmitManager = new SoundTransmitManager(pattern, tempsSignal);
						soundTransmitManager.playMorse();
					}
					else {
						messageErreur("Caractères spéciaux interdits!");
					}
				}
				else {
					messageErreur("Saisir un massage!");
				}
			}
		});
		
//		LISTENER SUR LE BOUTON RETOUR
		Button bt_emetteurRetour = (Button) findViewById(R.id.bt_emetteurRetour);
		bt_emetteurRetour.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	private boolean controleChaine(String text) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
		    return false;
		}
		return true;
	}
	
	private void messageErreur(String message) {
		Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
	}
}
