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

public class FrameRecommencer extends JFrame implements ActionListener
{
    private Controleur ctrl;
    private JPanel   panel;

    private JPanel    text;
    private JPanel    bout;
	private JButton btnAnnuler;
	private JButton btnRecommencer;

    public FrameRecommencer(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle   ( "Recommencer" );
        this.setSize ( 400, 200 );
        this.setLocation(300, 300);

        // Création des composants
		this.panel = new JPanel  ();

        this.text  = new JPanel();
		this.bout  = new JPanel();

		this.btnAnnuler      = new JButton(" Annuler "      );
		this.btnRecommencer  = new JButton(" Recommencer "  );

		// Positionnement des composants
		this.text.setLayout (new GridLayout(3,1));
		this.text.add( new JLabel("  " ));
		this.text.add( new JLabel(" Êtes-vous sûr de vouloir recommencer ? " ));
		this.text.add( new JLabel(" Vous ne pourrez plus recupérer votre partie en cours ..." ));


		this.bout.setLayout (new GridLayout(1,2));
		this.bout.add( this.btnAnnuler);
		this.bout.add( this.btnRecommencer);


        this.panel.setLayout (new GridLayout(2,2));

		this.panel.add ( this.text);
		this.panel.add ( this.bout);

		this.add ( this.panel);
		
		this.btnAnnuler    .addActionListener(this);
		this.btnRecommencer.addActionListener(this);
		
		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnRecommencer)
			{
				this.dispose();
				this.ctrl.getFrame().dispose();
				new Controleur(this.ctrl.getNumPaquetM1(), this.ctrl.getNumPaquetM2());
			}
			
		if (e.getSource() == this.btnAnnuler)
		{
			this.dispose();
			this.ctrl.getFrame().setEnabled(true);
		}
	}
}

