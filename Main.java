/**
 * Esta es la clase principal, la que opera, maneja y dirige el juego de las cartas de manera directa.
 * 
 * @author (Fabio Sanabria Valerin) 
 * @version (Version 6) 16/5/21
 */
import java.util.Random;
import java.util.Scanner;

public class Main
{
    private static Carta cartas[];
    private static Jugador jugadores[];
    private static boolean continuarJuego = true;
    
    public static void main(String[] args) 
    {
        int []comodin = {2,2,4,2,4,4,2,4,2,4};
        int comodinGanado = 1;
        boolean bandera = true;
        Scanner in = new Scanner(System.in);
        System.out.println("Reglas del juego: \n Intente usar como máximo 9 cartas en su juego \n Si elige tener trios entonces tiene que utilizar el comando: "
        + "x,yA para lograr sacarlos ya que así lo dicen las instrucciones\n Y por ultimo Disfrute :D");
        
        System.out.println("¿Cuantos pares de cartas quiere?");
        int cantidadPares = in.nextInt(); 
        
        System.out.println("¿Cuantos trios de cartas quiere?");
        int cantidadTrios = in.nextInt();
        
        int cantidadTotal = (2 * cantidadPares) + (3 * cantidadTrios); // Variable con la cantidad de cartas del juego
        
        cartas = new Carta[cantidadTotal]; //Array contenedora de todas las cartas del juego
        
        System.out.println("¿Cuantos jugadores van a jugar?");
        int cantidadJugadores = in.nextInt();
        
        String historial[] = new String[cantidadJugadores];
        jugadores = new Jugador[cantidadJugadores]; //Array contenedora de todos los jugadores  del juego
        
        //Inicializa los historiales para evitar un null a futuro
        for(int i = 0; i < cantidadJugadores; i++){
            historial[i] = "";
        }
        
        //For encargado de guardar el nombre de los jugadores en el array jugadores[] para enviarselo al constructor de la clase Jugadores
        for(int i = 0; i < cantidadJugadores; i++)
        {
            System.out.println("Digite el nombre del jugardor: " + (i + 1));
            String nombre = in.next();
            
            jugadores[i] = new Jugador(nombre);
        }
        
        Random random = new Random(); //Crea un objeto de tipo Random
        
        // Guardar Pares de manera ordenada
        for(int i = 0; i < cantidadPares * 2; i+=2)
        {
            String letra = String.valueOf((char)(random.nextInt(25) + 65));
            String numero = String.valueOf((int) (Math.random() * 9) + 1);
            
            cartas[i] = new Carta("Par", letra + numero);
            cartas[i+1] = new Carta("Par", letra + numero);
        }
        
        //Guardar Tríos de manera ordenada
        for(int i = cantidadPares * 2; i < cantidadTotal; i+=3)
        {
            String letra = String.valueOf((char)(random.nextInt(25) + 65));
            String numero = String.valueOf((int) (Math.random() * 9) + 1);
            
            cartas[i] = new Carta("Trio", letra + numero);
            cartas[i+1] = new Carta("Trio", letra + numero);
            cartas[i+2] = new Carta("Trio", letra + numero);
        }
       
        cartas = Barajar(cartas); //Ontiene las cartas barajadas del metodo Barajar 
        
        int posicionJugador = 0; 
        int contadorHistorial = 0;
        //Ciclo infinito encargado de mantener el juego hasta que el jugador indique lo contrario o todas las cartas sean volteadas. 
        do
        {
            System.out.println("Turno del jugador: " + jugadores[posicionJugador].ObtenerNombre() + ". Puntaje Actual: " + jugadores[posicionJugador].ObtenerPuntaje());
            
            //Imprime las cartas dependiendo del valor de.ObtenerVisible, si es true el if se ejecita, si es false entra en el else e imprime [**] 
            for(int i = 0; i < cantidadTotal; i++)
            {
                if (cartas[i].ObtenerVisible())
                    System.out.print("[" + cartas[i].ObtenerValor() +"] ");
                else
                    System.out.print("[**] ");
            }
        
            System.out.println(""); //Enter(Space)
            
            System.out.println("Ingrese las posiciones en el arreglo. Ej: 2,3N (Con todo y coma)");
            String comando = in.next(); //Comando encargado de recibir las indicaciones del jugador para voltear las cartas de la manera x,yA
            
            historial[contadorHistorial] += comando + " "; //Se le coloca a historial el comando correspondiente en la posicion "contadorHistorial"   
            
            // Compara si los números de las 2 posiciones indicadas coinciden mediante el comando N
            if (comando.charAt(3) == 'N')
            {
                int posicionPrimerCarta = Integer.parseInt(String.valueOf(comando.charAt(0)));
                int posicionSegundaCarta = Integer.parseInt(String.valueOf(comando.charAt(2)));
                
                Carta primerCarta = cartas[posicionPrimerCarta];
                Carta segundaCarta = cartas[posicionSegundaCarta];
                
                // Jugador ganó puntos por números
                if (primerCarta.ObtenerValor().charAt(1) == segundaCarta.ObtenerValor().charAt(1))
                {
                    int valorNumericoPrimerCarta = Integer.parseInt(String.valueOf(primerCarta.ObtenerValor().charAt(1)));
                    int valorNumericoSegundaCarta = Integer.parseInt(String.valueOf(segundaCarta.ObtenerValor().charAt(1)));
                    
                    primerCarta.SetVisible(true);
                    segundaCarta.SetVisible(true);
                    
                    
                    jugadores[posicionJugador].SumarPuntaje((valorNumericoPrimerCarta + valorNumericoSegundaCarta));
                    
                    System.out.println("Has acertado los números!!!");
                }
            }

             // Compara si las letras de las 2 posiciones indicadas coinciden mediante el comando L
            if (comando.charAt(3) == 'L')
            {
                int posicionPrimerCarta = Integer.parseInt(String.valueOf(comando.charAt(0)));
                int posicionSegundaCarta = Integer.parseInt(String.valueOf(comando.charAt(2)));
                
                Carta primerCarta = cartas[posicionPrimerCarta];//Carta 1        //Se comparan las dos cartas
                Carta segundaCarta = cartas[posicionSegundaCarta]; // Carta 2 
                
                // Jugador ganó puntos por letras
                if (primerCarta.ObtenerValor().charAt(0) == segundaCarta.ObtenerValor().charAt(0))
                {
                    int valorNumericoPrimerCarta = Integer.parseInt(String.valueOf(primerCarta.ObtenerValor().charAt(1)));
                    int valorNumericoSegundaCarta = Integer.parseInt(String.valueOf(segundaCarta.ObtenerValor().charAt(1)));
                        
                    String valorAlfaNumericoPrimerCarta = String.valueOf(primerCarta.ObtenerValor().charAt(0));
                    String valorAlfaNumericoSegundaCarta = String.valueOf(segundaCarta.ObtenerValor().charAt(0));
                    
                    primerCarta.SetVisible(true); //Comando utilizado para mantener visibles las cartas que el usuario logró encotrar
                    segundaCarta.SetVisible(true);
                    
                    jugadores[posicionJugador].SumarPuntaje(valorNumericoPrimerCarta + valorNumericoSegundaCarta);
                    
                    System.out.println("Has acertado las letras!!!");
                }
            }
           
           //* Compara si los números y las letras de las 2 posiciones indicadas coinciden mediante el comando A y si la carta seleccionada es un trio,
           //se le otorgara la oportunidad de encontrar su trio y si lo pega obtiene los puntos de las 3 cartas y un comodin, si no logra encotrar el trio,
           //no se le otorga ni un solo punto y se voltearan las cartas para el siguente jugador
           //*
            if (comando.charAt(3) == 'A')
            {
                int posicionPrimerCarta = Integer.parseInt(String.valueOf(comando.charAt(0)));
                int posicionSegundaCarta = Integer.parseInt(String.valueOf(comando.charAt(2)));
                
                Carta primerCarta = cartas[posicionPrimerCarta];//Carta 1        //Se comparan las dos cartas
                Carta segundaCarta = cartas[posicionSegundaCarta]; // Carta 2 
                
                // Jugador ganó puntos por ambos
                if (primerCarta.ObtenerValor().equals(segundaCarta.ObtenerValor()))
                {
                    int valorNumericoPrimerCarta = Integer.parseInt(String.valueOf(primerCarta.ObtenerValor().charAt(1)));
                    int valorNumericoSegundaCarta = Integer.parseInt(String.valueOf(segundaCarta.ObtenerValor().charAt(1)));    
                    String valorAlfaNumericoPrimerCarta = String.valueOf(primerCarta.ObtenerValor().charAt(0));
                    String valorAlfaNumericoSegundaCarta = String.valueOf(segundaCarta.ObtenerValor().charAt(0));
                    primerCarta.SetVisible(true); //Comando utilizado para mantener visibles las cartas que el usuario logró encotrar
                    segundaCarta.SetVisible(true);
                    
                if(primerCarta.ObtenerTipo().equals("Trio")){ //El caso de que las cartas tengan un trio
                        
                        System.out.println("Usted ha encontrado una carta que pertenece a una de los trios.\n"+ 
                        "Encuentre la otra carta colocando una de las posiciones. Ej: 3");
                        int posicionTrio = in.nextInt();
                        
                        Carta terceraCarta = cartas[posicionTrio];  
                        int valorNumericoTerceraCarta = Integer.parseInt(String.valueOf(terceraCarta.ObtenerValor().charAt(1)));
                        terceraCarta.SetVisible(true);
                    if(primerCarta.ObtenerValor().equals(terceraCarta.ObtenerValor())){
                                int num_aleatoriatrio = random.nextInt(10);
                                comodinGanado = comodin[num_aleatoriatrio];
                                System.out.println("Felicidades encontraste el trío, y tu premio es....." + "multiplicar tu puntaje por " + comodinGanado + "!!!!!");
                                //terceraCarta.SetVisible(true); 
                                jugadores[posicionJugador].SumarPuntaje(((valorNumericoPrimerCarta + valorNumericoSegundaCarta + valorNumericoTerceraCarta) * comodinGanado));
                    }
                    else{
                    
                                System.out.println("Lo siento mucho pero no encontraste el trio, mejor suerte para la próxima");
                                primerCarta.SetVisible(false); //Comando utilizado para esconder las cartas en caso de que el usuario falle
                                segundaCarta.SetVisible(false);
                                terceraCarta.SetVisible(false);
                    } 
                    
                }else{ //Caso en donde las cartas no tienen un trio
                
                    jugadores[posicionJugador].SumarPuntaje((valorNumericoPrimerCarta + valorNumericoSegundaCarta));
                    System.out.println("Has acertado AMBOS que carga!!!");
                
                }
             }
           }
            
            for(int i = 0; i < cantidadTotal; i++)
            {
                if (comando.charAt(0) == i + '0' || comando.charAt(2) == i + '0' || cartas[i].ObtenerVisible())
                    System.out.print("[" + cartas[i].ObtenerValor() +"] ");
                else
                    System.out.print("[**] ");
            }
            
            System.out.println("");
            //For encargado de que cuando todas las cartas se volteen, el juego termine. 
            for(int i = 0; i < cantidadTotal; i++){
                if(cartas[i].ObtenerVisible() == true){
            
                bandera = false;
                }
                else if(cartas[i].ObtenerVisible() == false)
                {
                bandera = true;
                break; 
                }
            }
            //Si la bandera es false entonces el juego acaba de una vez
            if(bandera == false)
            {
                continuarJuego = false;
                System.out.println("Felicidades has volteado todas las cartas!!!, Presiona cualquier tecla para terminar el juego");
            }
               
            System.out.println("¿Deseas continuar jugando? S/N");
            String continuar = in.next();
            
            if (continuar.equals("N".trim()))
                continuarJuego = false;
        
            // Se resetea el índice que recorre los jugadores para volver a iniciar
            if (posicionJugador == cantidadJugadores - 1)
                posicionJugador = 0;
            else
                posicionJugador++;
           
            // Se resetea el índice que recorre los historiales para volver a iniciar    
            if(contadorHistorial == cantidadJugadores - 1)
                contadorHistorial = 0;
            
            else
                contadorHistorial++;
        }
        while(continuarJuego) ; //Condicion que mantiene el juego en funcionamiento
        System.out.println("FIN DEL JUEGO\n");
        
        System.out.println("Puntajes finales y movimientos: \n"); 
        //Ciclo que imprime el nombre, puntajes e historiales de los jugadores
        for(int contador = 0; contador < cantidadJugadores; contador++)
        {
        
        System.out.println("El jugador " + jugadores[contador].ObtenerNombre() +" tiene un puntaje " + jugadores[contador].ObtenerPuntaje());
        jugadores[contador].ImprimirHistorial(historial[contador] + "\n");
    
        }
    }
    
    /**
     * Metodo static Barajar
     * Este metodo se encarga de barajar, es decir cambiar las posiciones del array que se le pasa
     * como parámetro para lograr hacer que el juego tenga mas sentido.
     * @param cartas[] : Variable que contiene las cartas ordenadas de manera uniforme, son pasadas al metodo Barajar para revolverse de manera aleatoria
     * 
     */
    public static Carta [] Barajar(Carta cartas[])
    {
        Carta cartasBarajadas[] = new Carta[cartas.length];
        
        int pos;
        for (int i = 0; i < cartas.length; i++){
            do
            {
                pos = (int)(Math.random() * cartas.length);
            }
            while (cartasBarajadas[pos] != null);
            
            cartasBarajadas[pos] = cartas[i];
        }
        
        return cartasBarajadas;
    }
}