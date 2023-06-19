import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Toolkit;


/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class PanelMenu  extends JPanel implements ActionListener
{
    private Controleur			ctrl;
	private JButton				btnRetMenu;
	private JButton				btnQuitter;
	private JButton				btnRecommencer;

	public PanelMenu(Controleur ctrl)
	{
		this.ctrl        = ctrl;  
		this.setBackground(new Color(170, 159, 151));


		// Création des composants	

		this.btnRecommencer	= new JButton(" Recommencer ");
		this.btnRetMenu		= new JButton(" Retour Menu ");
		this.btnQuitter		= new JButton(" Quitter ");

		// Positionnement des composants
		this.setLayout (new GridLayout(3,2));
		

        this.add( this.btnRecommencer);
		this.add( this.btnRetMenu);
		this.add( this.btnQuitter);

		//activation des composants

        this.btnRecommencer.addActionListener(this);
		this.btnRetMenu.addActionListener(this);
		this.btnQuitter.addActionListener(this);

		this.setVisible(true);
	} 


	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == this.btnRecommencer)
		{
			this.ctrl.getFrame().setEnabled(false);
			new FrameRecommencer(this.ctrl);
		}

		if (e.getSource() == this.btnRetMenu) 
		{
			this.ctrl.getFrame().setEnabled(false);
			new FrameRetourMenu(this.ctrl);
		}

		if (e.getSource() == this.btnQuitter)
		{
			new FrameQuitter(this.ctrl);
			this.ctrl.getFrame().setEnabled(false);
		}
		
	}
}