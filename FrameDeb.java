import javax.swing.JFrame;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class FrameDeb extends JFrame
{
    private ControleurDeb    ctrl;
    private PanelDeb panel;

    public FrameDeb(ControleurDeb ctrl)
    {
        this.ctrl = ctrl;
        this.setSize ( 800, 400 );

        // Création des composants
		this.panel = new PanelDeb(ctrl);

		// Positionnement des composants
		this.add ( this.panel);
		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
}