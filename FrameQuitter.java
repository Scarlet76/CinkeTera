import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
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

public class FrameQuitter extends JFrame implements ActionListener
{
    private Controleur  ctrl;
    private JPanel      panel;

    private JPanel      text;
    private JPanel      bout;
	private JButton     btnAnnuler;
	private JButton     btnQuiter;

    public FrameQuitter(Controleur ctrl )
    {

        this.ctrl = ctrl;
        this.setTitle   ( "Quitter" );
        this.setSize ( 500, 200 );
        this.setLocation(300, 300);

        // Création des composants
		this.panel = new JPanel  ();

        this.text  = new JPanel();
        this.bout  = new JPanel();

		this.btnAnnuler = new JButton(" Annuler "  );
		this.btnQuiter  = new JButton(" Quitter "  );

		// Positionnement des composants
        this.text.setLayout (new GridLayout(3,1));
        this.text.add( new JLabel("  " ));
        this.text.add( new JLabel(" Êtes-vous sûr de vouloir quitter ? " ));
        this.text.add( new JLabel(" Vous ne pourrez plus recupérer votre partie en cours ..." ));

        this.bout.setLayout (new GridLayout(1,2));
        this.bout.add( this.btnAnnuler);
        this.bout.add( this.btnQuiter);

        this.panel.setLayout (new GridLayout(2,2));

        this.panel.add ( this.text);
        this.panel.add ( this.bout);

		this.add ( this.panel);
        this.btnAnnuler.addActionListener(this);
        this.btnQuiter.addActionListener(this);
        
		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
		if (e.getSource() == this.btnAnnuler) 
        { 
            this.ctrl.getFrame().setEnabled(true);
            this.dispose(); 
        }
        if (e.getSource() == this.btnQuiter)
        {
            this.dispose();
            this.ctrl.getFrame().dispose();
        }
        
		
	}
}

