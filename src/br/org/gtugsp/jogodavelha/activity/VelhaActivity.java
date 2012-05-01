package br.org.gtugsp.jogodavelha.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import br.org.gtugsp.jogodavelha.R;

public class VelhaActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	int[] ids = new int[] { R.id.quadrado1, R.id.quadrado2, R.id.quadrado3, 
							R.id.quadrado4, R.id.quadrado5, R.id.quadrado6, 
							R.id.quadrado7, R.id.quadrado8, R.id.quadrado9 };
	boolean jogador2 = false;
	boolean xWin = false;
	boolean oWin = false;
	boolean limpar = false;
	ArrayList<Integer> jogadasX = new ArrayList<Integer>();
	ArrayList<Integer> jogadasO = new ArrayList<Integer>();
	Map<Integer, View> jogadas = new HashMap<Integer, View>();
	
	public VelhaActivity getInstance(){
		return this;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		for (int id : ids) {
			findViewById(id).setOnClickListener(this);
		}
		MyClassListener myClassListener = new MyClassListener();
		myClassListener.setVelhaActivity(this.getInstance());
		findViewById(R.id.layout).setOnClickListener(myClassListener);
	}

	public void onClick(View view) {
		ImageView img = (ImageView) view;
		if (jogadas.size() == 9 || xWin || oWin ) {
			return;
		}
		if (jogadas.containsValue(view)) {
			Toast.makeText(this, "Jogada Não Permitida", Toast.LENGTH_SHORT).show();
		} else {
			jogadas.put(img.getId(), view);
			int imagem = 0;
			if( jogador2 ) {
				imagem = R.drawable.o;
				jogadasO.add(img.getId());
			} else {
				 imagem = R.drawable.x;
				 jogadasX.add(img.getId());
			}
			
			img.setImageResource( imagem );
			jogador2 = !jogador2;
			this.checkWinner();
		}
	}
	
	public boolean isNecessaryTheReset(){
		if( jogadas.size() == 9 )
			limpar = true;
		return jogadas.size() == 9 || xWin || oWin;
	}
	
	public void resetGame(){
		if( limpar ){
			for (int id : ids) {
				((ImageView) findViewById(id)).setImageResource(R.drawable.empty);
			}
			jogadas.clear();
			jogadasX.clear();
			jogadasO.clear();
			xWin = false;
			oWin = false;			
			limpar = false;
			Toast.makeText(this, "Novo Jogo", Toast.LENGTH_SHORT).show();
		} 
	}

	private boolean canWin(ArrayList<Integer> jogadas){
		return (jogadas.contains(R.id.quadrado1) && jogadas.contains(R.id.quadrado2) && jogadas.contains(R.id.quadrado3)) || 
		   (jogadas.contains(R.id.quadrado1) && jogadas.contains(R.id.quadrado5) && jogadas.contains(R.id.quadrado9)) || 
		   (jogadas.contains(R.id.quadrado1) && jogadas.contains(R.id.quadrado4) && jogadas.contains(R.id.quadrado7)) || 
		   (jogadas.contains(R.id.quadrado2) && jogadas.contains(R.id.quadrado5) && jogadas.contains(R.id.quadrado8)) || 
		   (jogadas.contains(R.id.quadrado3) && jogadas.contains(R.id.quadrado6) && jogadas.contains(R.id.quadrado9)) || 
		   (jogadas.contains(R.id.quadrado4) && jogadas.contains(R.id.quadrado5) && jogadas.contains(R.id.quadrado6)) || 
		   (jogadas.contains(R.id.quadrado7) && jogadas.contains(R.id.quadrado8) && jogadas.contains(R.id.quadrado9)) || 
		   (jogadas.contains(R.id.quadrado3) && jogadas.contains(R.id.quadrado5) && jogadas.contains(R.id.quadrado7));
	}
	
	public void checkWinner(){
		if( canWin(jogadasX) ){
			Toast.makeText(this, "Jogador X Ganhou", Toast.LENGTH_SHORT).show();
			xWin=true;
			limpar=true;
		}
		if( canWin(jogadasO) ){
			Toast.makeText(this, "Jogador O Ganhou", Toast.LENGTH_SHORT).show();
			oWin=true;
			limpar=true;
		}
	}

}