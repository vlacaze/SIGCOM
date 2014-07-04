package fr.iut.csid.projet.sigcom.métier.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SoundParametersSQLite extends SQLiteOpenHelper {
	private static final String TABLE_PARAMETERS = "table_parameters";
	private static final String COL_ID = "ID";
	private static final String COL_SIGNAL_DURATION = "SIGNAL_DURATION";
	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_PARAMETERS + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_SIGNAL_DURATION + " INT);";
	private static final String INSERT_VALUE = "INSERT INTO " + TABLE_PARAMETERS + " (" + COL_SIGNAL_DURATION + ") VALUES" + "(100);";
	
	public SoundParametersSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);
		db.execSQL(INSERT_VALUE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP_TABLE " + TABLE_PARAMETERS);
		onCreate(db);
	}
}
