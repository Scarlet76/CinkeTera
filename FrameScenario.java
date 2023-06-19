//FrameScenario

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JLabel;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class FrameScenario extends JFrame implements ActionListener
{
    private ControleurDeb	ctrl;
    private JPanel		panel;

    private JPanel		text;

	private JButton		btnScen1;
	private JButton		btnScen2;
	private JButton		btnScen3;
	private JButton		btnRetMenu;

    public FrameScenario(ControleurDeb ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle   ( "Recommencer" );
        this.setSize ( 600, 200 );
        this.setLocation(300, 300);

        // Création des composants
		this.panel = new JPanel  ();

        this.text  = new JPanel();


		this.btnScen1    = new JButton(" Scénario 1 " );
		this.btnScen2    = new JButton(" Scénario 2 " );
		this.btnScen3    = new JButton(" Scénario 3 " );
		this.btnRetMenu  = new JButton(" Retour au Menu " );

		// Positionnement des composants
		this.text.setLayout (new GridLayout(3,2));
		this.text.add( new JLabel(" Scénario 1 : Partie dirigé " ));
		this.text.add( this.btnScen1 );
		this.text.add( new JLabel(" Scénario 2 : Partie presque fini " ));
		this.text.add( this.btnScen2 );
		this.text.add( new JLabel(" Scénario 3 : Début partie " ));
		this.text.add( this.btnScen3 );



 
		this.setLayout (new BorderLayout(10,10));

		this.add ( this.text,BorderLayout.CENTER );
		this.add ( this.btnRetMenu,BorderLayout.SOUTH );

		
		this.btnScen1.addActionListener(this);
		this.btnScen2.addActionListener(this);
		this.btnScen3.addActionListener(this);
		this.btnRetMenu.addActionListener(this);
		
		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnRetMenu)
		{
			this.dispose();
			this.ctrl.getFrameDeb().dispose();
			ControleurDeb c = new ControleurDeb();
		}
			
		if (e.getSource() == this.btnScen1)
		{
			new Controleur(5, 6);
			this.dispose();
            this.ctrl.getFrameDeb().dispose(); 
		}
		if (e.getSource() == this.btnScen2)
		{
			new Controleur(10, 10);
			this.dispose();
            this.ctrl.getFrameDeb().dispose(); 
		}
		if (e.getSource() == this.btnScen3)
		{
			new Controleur(0, 7);
			this.dispose();
            this.ctrl.getFrameDeb().dispose(); 
		}

	}
}

