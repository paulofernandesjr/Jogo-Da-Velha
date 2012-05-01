package br.org.gtugsp.jogodavelha.activity;

import android.view.View;
import android.view.View.OnClickListener;

public class MyClassListener implements OnClickListener  {

	private VelhaActivity velha;
	public void setVelhaActivity(VelhaActivity velha){
		this.velha = velha;
	}
	
	public void onClick(View v) {
		if( velha.isNecessaryTheReset() )
			velha.resetGame();
	}
	
}
