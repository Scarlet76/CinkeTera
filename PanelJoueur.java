import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Image;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class PanelJoueur  extends JPanel 
{
	private Controleur			ctrl;
	private PanelControles      panelControles;
	private PanelCarte			panelCarte;
	private PanelMenu			panelMenu;

	public PanelJoueur(Controleur ctrl)
	{
		this.ctrl        = ctrl;  
		this.setBackground(new Color(170, 159, 151));

		// Création des composants	

		this.panelControles = new PanelControles(this.ctrl, this);
		this.panelCarte     = new PanelCarte(this.ctrl, this.panelControles);
		this.panelMenu      = new PanelMenu(this.ctrl);
		this.panelCarte.setOpaque(false);

		// Positionnement des composants
		this.setLayout (new GridLayout(3,1));
		
		this.add( this.panelControles);
		this.add( this.panelCarte);	
		this.add( this.panelMenu);

		//activation des composants
		this.setVisible(true);
	} 

	public PanelControles 	getPanelControles() 	{ return this.panelControles;	}
	public void 			pnlRepaint() 			{ this.panelCarte.repaint();}
	public PanelCarte	 	getPanelCarte() 		{return this.panelCarte;  	}

}