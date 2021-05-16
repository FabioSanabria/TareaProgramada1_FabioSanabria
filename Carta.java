/**
 * Clase utilzada para el control de las cartas dentro de la clase Main.
 * 
 * @author (Fabio Sanabria Valerin) 
 * @version (Version 5), 13/5/21 
 */
public class Carta
{
    private String Tipo;
    private String Valor;
    private boolean Visible;
     /**
     *Constructor Carta
     * @param String tipo se le coloca dentro de la variable Tipo que se encuentra en el encabezado de la clase inicializado,
     * dentro de tipo puede venir "par" o "trío"
     * @param String valor se le coloca dentro de la variable Valor que se encuentra en el encabezado de la clase inicializado,
     * dentro de valor puede venir "A3", "B5", etc.
     **/
    public Carta(String tipo, String valor)
        {
        this.Tipo = tipo;
        this.Valor = valor;
        this.Visible = false;
        }
    
    /**
     * 
     *Método String ObtenerTipo()
     * Devuelve el tipo de carta que es ya sea par o trio al lugar en donde se llama
     * @return this.Tipo = retorna el tipo de la carta ya sea trio o par
     **/
     
    public String ObtenerTipo()
    {
        return this.Tipo;
    }
    
    /**
     *Método String ObtenerValor()
     * Devuelve el valor de carta (en String) en donde se llama, ya sea A3,B4, etc. Es usado en el main mas que todo
     * para hacer la comparacion entre dos cartas.
     * @return this.Valor = retorna el valor de la carta ya sea A3,B4, etc.
     **/
     
    public String ObtenerValor()
    {
        return this.Valor;
    }
    
    /**
     *Método boolean ObtenerVisible()
     * Es un método utilizado para que la variable que lo llame en la clase Main se visibilice para el usuario, el valor de this.Visible depende del valor de
     * visible pasado por el Metodo SetVisible()
     * @return this.Visible = retorna true o false para que la variable se visibilice
     **/   
     
    public boolean ObtenerVisible()
    {
        return this.Visible;
    }
    
    /**
     *Método void SetVisible(boolean visible)
     * Este método es utilizado para que 
     * @param visible : Es una variable que recibe un true o false y que depende de lo que reciba cambia el valor de this.Visible para que el objeto se 
     * visibilice o se esconda
     **/   
    public void SetVisible(boolean visible)
    {
        this.Visible = visible;
    }
}