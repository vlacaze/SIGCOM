package fr.iut.csid.projet.sigcom.manager;

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class SoundReceiveManager {
//	ATTRIBUTS
	private MediaRecorder mediaRecorder = null;
	private static final String LOG_TAG = "ReceiveActivity";
    private static String mFileName = null;
    
    
//  CONSTRUCTEURS
    public SoundReceiveManager(MediaRecorder mRecorder) {
		super();
		mediaRecorder = mRecorder;
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
	    mFileName += "/audiorecordtest.3gp";
	}
    	
	
//  METHODES
//	ENREGISTREMENT DU SON
	public void onRecord(boolean start) {
		if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

	private void startRecording() {
		mediaRecorder = new MediaRecorder();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setOutputFile(mFileName);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
        	mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mediaRecorder.start();
    }

    private void stopRecording() {
    	mediaRecorder.stop();
    	mediaRecorder.release();
    	mediaRecorder = null;
    }
}
