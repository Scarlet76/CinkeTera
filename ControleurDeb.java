/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class ControleurDeb
{
    public FrameDeb        frameDeb;

    public ControleurDeb()  { this.frameDeb = new FrameDeb(this); }

    public FrameDeb getFrameDeb()   { return this.frameDeb; }

    public static void main (String[] a)   { ControleurDeb c = new ControleurDeb(); }
}