package fr.iut.csid.projet.sigcom.manager;

import android.media.ToneGenerator;


public class SoundTransmitManager extends Thread {
//	ATTRIBUTS
	private int DOT_TIME;
	private String[] pattern;
	private ToneGenerator toneGenerator;
	
	
//	CONSTRUCTEURS
	public SoundTransmitManager(String[] pattern, int temps) {
		this.pattern = pattern;
		DOT_TIME = temps;
		toneGenerator = new ToneGenerator(3,90);
	}
	
//	METHODES
//	EMISSION DU SON
	public void playMorse () {
		for (String signe : pattern) {
			if (signe != null) {
				try {
					if (signe.equals("DOT")) {
						playDot();
					}else if (signe.equals("DASH")) {
						playDash();
					}else if (signe.equals("GAP")) {
						playGap();
					}else if (signe.equals("LETTER_GAP")) {
						playLetterGap();
					}else if (signe.equals("WORD_GAP")) {
						playWordGap();
					}
				}
				catch (InterruptedException localInterruptedException) {
			        
			        return;
			    }
			}
			
		}
	}
	
	private void playDot() throws InterruptedException {
	    this.toneGenerator.startTone(1, this.DOT_TIME);
	    synchronized (this.toneGenerator)
	    {
	      this.toneGenerator.wait(2 * this.DOT_TIME);
	      return;
	    }
	}
	
	private void playDash() throws InterruptedException
	  {
	    this.toneGenerator.startTone(1, 3 * this.DOT_TIME);
	    synchronized (this.toneGenerator)
	    {
	      this.toneGenerator.wait(4 * this.DOT_TIME);
	      return;
	    }
	}
	
	private void playGap() throws InterruptedException {
	    synchronized (this.toneGenerator)
	    {
	      this.toneGenerator.wait(2 * this.DOT_TIME);
	      return;
	    }
	}
	
	private void playLetterGap() throws InterruptedException {
	    synchronized (this.toneGenerator)
	    {
	      this.toneGenerator.wait(3 * this.DOT_TIME);
	      return;
	    }
	 }
	
	private void playWordGap() throws InterruptedException {
	    synchronized (this.toneGenerator)
	    {
	      this.toneGenerator.wait(7 * this.DOT_TIME);
	      return;
	    }
	}
}
