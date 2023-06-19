import java.util.ArrayList;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class Region
{
	private  ArrayList<Ile>     lstIle;
	private  String             nom;
	private  int             	posX;
	private  int             	posY;
	private  Controleur         ctrl;

	public Region(String nom, int posX, int posY, Controleur ctrl)
	{
		this.nom    = nom;
		this.lstIle = new ArrayList<Ile>();
		this.posX 	= posX;
		this.posY 	= posY;
		this.ctrl   = ctrl;
	}

	public void            addIle   (Ile i) { this.lstIle.add(i); }
	public ArrayList<Ile>  getLstIle() 		{ return this.lstIle; }
	public String          getNom	() 		{ return this.nom  ;  }
	public int             getPosX  () 		{ return this.posX  ; }
	public int             getPosY  () 		{ return this.posY  ; }
}