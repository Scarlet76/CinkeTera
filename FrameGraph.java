import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class FrameGraph extends JFrame
{
	private Controleur    ctrl;
	private PanelGraph  panelGr;
	private PanelJoueur PanelJoueur;

	public FrameGraph(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setSize ( 1500, 750 );

		// Création des composants
		this.panelGr     = new PanelGraph  (this.ctrl);
		this.PanelJoueur = new PanelJoueur (this.ctrl);
		this.setBackground(new Color(255, 255, 255));

		// Positionnement des composants

		this.setLayout (new BorderLayout(5,5));
		this.add ( this.panelGr, BorderLayout.CENTER  );
		this.add ( this.PanelJoueur, BorderLayout.EAST);
		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public PanelGraph  getPanelGr()			{ return this.panelGr;     }
	public PanelJoueur getPanelJoueur()		{ return this.PanelJoueur; }

    
}