package fr.iut.csid.projet.sigcom.métier.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.iut.csid.projet.sigcom.métier.SoundParameters;

public class SoundParametersDAO {
//	ATTRIBUTS
	private static final int VERSION = 1;
	private static final String NOM_BDD = "transaction.db";
	
	private static final String TABLE_PARAMETERS = "table_parameters";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_SIGNAL_DURATION = "SIGNAL_DURATION";
	private static final int NUM_COL_SIGNAL_DURATION = 1;
	
	private SQLiteDatabase bdd;
	private SoundParametersSQLite soundParametersSQLite;
	
	
//	CONSTRUCTEURS
	public SoundParametersDAO(Context context) {
		soundParametersSQLite = new SoundParametersSQLite(context, NOM_BDD, null, VERSION);
	}
	
	
//	ACCESSEURS
	public SQLiteDatabase getBdd() {
		return bdd;
	}


//	METHODES
	public void openForWrite() {
		bdd = soundParametersSQLite.getWritableDatabase();
	}
	
	public void openForRead() {
		bdd = soundParametersSQLite.getReadableDatabase();
	}
	
	public void close() {
		bdd.close();
	}
	
//	public long insertTransaction(SoundParameters soundParameters) {
//		ContentValues content = new ContentValues();
//		content.put(COL_SIGNAL_DURATION, String.valueOf(soundParameters.getDureeSignal()));		
//		return bdd.insert(TABLE_PARAMETERS, null, content);
//	}
	
	public int updateSoundParameters(int id, SoundParameters soundParameters) {
		ContentValues content = new ContentValues();
		content.put(COL_SIGNAL_DURATION, soundParameters.getDureeSignal());
		return bdd.update(TABLE_PARAMETERS, content, COL_ID + " = " + id, null);
	}
	
	public SoundParameters getSoundParameters() {
		Cursor c = bdd.query(TABLE_PARAMETERS, new String[] {COL_ID, COL_SIGNAL_DURATION},null, null, null, null, COL_ID);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		SoundParameters soundParameters = new SoundParameters();
		while (c.moveToNext()) {
			soundParameters.setId(c.getInt(NUM_COL_ID));
			soundParameters.setDureeSignal(c.getInt(NUM_COL_SIGNAL_DURATION));
		}
		c.close();
		return soundParameters;
	}
}
