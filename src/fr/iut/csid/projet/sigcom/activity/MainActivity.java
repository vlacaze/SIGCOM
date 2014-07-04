package fr.iut.csid.projet.sigcom.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.iut.csid.projet.sigcom.R;
import fr.iut.csid.projet.sigcom.métier.SoundParameters;
import fr.iut.csid.projet.sigcom.métier.sqlite.SoundParametersDAO;

public class MainActivity extends Activity {
//	ATTRIBUTS
	private static SoundParametersDAO soundParametersDAO;
	private static SoundParameters soundParameters;
	
	
//	ACCESSEURS
	public static SoundParameters getSoundParameters() {
		return soundParameters;
	}
	
	public static SoundParametersDAO getSoundParametersDAO() {
		return soundParametersDAO;
	}



	//	METHODES
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		chargerBase();
		
//		LISTENER SUR LE BOUTON EMETTEUR
		Button bt_emetteur = (Button) findViewById(R.id.bt_emetteur);
		bt_emetteur.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, TransmitActivity.class);
				startActivity(intent);
			}
		});
		
//		LISTENER SUR LE BOUTON RECEPTEUR
		Button bt_recepteur = (Button) findViewById(R.id.bt_recepteur);
		bt_recepteur.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
				startActivity(intent);
			}
		});
		
//		LISTENER SUR LE BOUTON PARAMETRES
		Button bt_parametres = (Button) findViewById(R.id.bt_parametres);
		bt_parametres.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, ParameterActivity.class);
				startActivity(intent);
			}
		});
		
//		LISTENER SUR LE BOUTON QUITTER
		Button bt_quitter = (Button) findViewById(R.id.bt_quitter);
		bt_quitter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				System.exit(0);
			}
		});
	}
	
//	CHARGER LA BASE SQLITE
	private void chargerBase() {
		soundParametersDAO = new SoundParametersDAO(this);
		soundParametersDAO.openForRead();
		soundParameters = soundParametersDAO.getSoundParameters();
		soundParametersDAO.close();
	}
}
