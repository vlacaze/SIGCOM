package fr.iut.csid.projet.sigcom.activity;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.iut.csid.projet.sigcom.R;
import fr.iut.csid.projet.sigcom.manager.SoundReceiveManager;

public class ReceiveActivity extends Activity {
//	ATTRIBUTS
	private Button bt_recevoirDebut;
	private MediaRecorder mRecorder = null;
    private boolean mStartRecording = true;
	private SoundReceiveManager soundReceiveManager;
	//private TextView tv_receveurMessage;
   
    
//   METHODES
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receive);
		
		//tv_receveurMessage = (TextView) findViewById(R.id.tv_receveurMessage);
		soundReceiveManager = new SoundReceiveManager(mRecorder);
		
//		LISTENER SUR LE BOUTON DEBUT RECEVOIR
		bt_recevoirDebut = (Button) findViewById(R.id.bt_recevoirDebut);
		bt_recevoirDebut.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				soundReceiveManager.onRecord(mStartRecording);
                if (mStartRecording) {
                    bt_recevoirDebut.setText("Stopper réception");
                } else {
                	bt_recevoirDebut.setText("Démarrer réception");
                }
                mStartRecording = !mStartRecording;
			}
		});

//		LISTENER SUR LE BOUTON RETOUR
		Button bt_receveurRetour = (Button) findViewById(R.id.bt_receveurRetour);
		bt_receveurRetour.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	@Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }
}
