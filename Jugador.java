/**
 * Clase utilzada para el control de los jugadores y los puntajes de los jugadores, ademas de que se encarga de imprimir el historial en la clase Main.
 * 
 * @author (Fabio Sanabria Valerin) 
 * @version (Version 5) 13/5/21
 */
public class Jugador
{
    private String Nombre;
    private int Puntaje;
    private String Historial;

    /**
     * Constructor Jugador
     * Hace que la variable nombre se guarde dentro de la variable this.Nombre
     * @param nombre = Variable pasada por el usuario en la clase Main
     */
    
    public Jugador(String nombre)
    {
        this.Nombre = nombre;
    }
    
        /**
     *Método String ObtenerNombre()
     * Devuelve el nombre asignado dentro del constructor donde es llamado en la clase Main.
     * @return Nombre  = Retorna el nombre
     **/
    public String ObtenerNombre()
    {
        return this.Nombre;
    }
    
    /**
     *Método int ObtenerPuntaje()
     *Devuelve el puntaje asignado dentro del metodo SumarPuntaje donde es llamado en la clase Main.
     *@return Puntaje = Retorna el puntaje actual
     **/
    public int ObtenerPuntaje()
    {
        return this.Puntaje;
    }
    
        
    /**
     *Método void ImprimirHistorial(String historial)
     *Imprime los nombres y los movimientos realizados de los jugadores dentro de la clase Main
     *@param historial = Todos los movimientos realizados por el usuario dentro de la clase Main
     **/
    public void ImprimirHistorial(String historial)
    {
        System.out.println("Historial del Jugador: " + this.Nombre);
        this.Historial = historial; 
        System.out.println(this.Historial);
    }
    
    /**
     *Método void SumarPuntaje(int puntaje)
     *Obtiene el puntaje ganado por el usuario y lo va asignando a la variable this.Puntaje a medida de que el juego va progresando
     *@param puntaje = Puntaje del jugador correspondiente a lo largo del juego
     **/
    public void SumarPuntaje(int puntaje)
    {
        this.Puntaje += puntaje;
    }
}