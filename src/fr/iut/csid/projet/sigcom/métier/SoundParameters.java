package fr.iut.csid.projet.sigcom.métier;

import fr.iut.csid.projet.sigcom.activity.MainActivity;


public class SoundParameters {
//	ATTRIBUTS
	private int id;
	private int dureeSignal;
	
	
//	CONSTRUCTEURS
	public SoundParameters () {
		
	}
	
	public SoundParameters (int id, int duree) {
		this.id = id;
		this.dureeSignal = duree;
	}

//	ACCESSEURS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDureeSignal() {
		return dureeSignal;
	}

	public void setDureeSignal(int dureeSignal) {
		this.dureeSignal = dureeSignal;
		MainActivity.getSoundParametersDAO().openForWrite();
		MainActivity.getSoundParametersDAO().updateSoundParameters(id, this);
		MainActivity.getSoundParametersDAO().close();
	}

}
