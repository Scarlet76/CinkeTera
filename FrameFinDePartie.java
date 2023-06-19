import javax.swing.JFrame;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class FrameFinDePartie extends JFrame
{
	private Controleur ctrl;
	private PanelFin panel;

	public FrameFinDePartie(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setSize ( 700, 200 );

		// Création des composants
		this.panel = new PanelFin  (this.ctrl,  this.ctrl.getGraph().getPts(), this);

		// Positionnement des composants
		this.add ( this.panel);
		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}