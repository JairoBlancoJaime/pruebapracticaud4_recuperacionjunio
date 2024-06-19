package org.iesvdm.reservamesa;


public class ReservaMesa {

    private int tamanioMesa;
    private int[] array;

    public int[] getMesas() {
        return array;
    }

    public void setMesas(int[] mesas) {
        this.array = mesas;
    }

    public int getTamanioMesa() {
        return tamanioMesa;
    }

    public void setTamanioMesa(int tamanioMesa) {
        this.tamanioMesa = tamanioMesa;
    }

    // Rellena las mesas de manera aleatoria.
    void rellenarMesas()
    {
        for (int i = 0; i < array.length; i++)
        {
            int aleatorio = (int) (Math.random() * tamanioMesa+1);

            array[i]= aleatorio;
        }
    }

    // Imprime por pantalla los resultados.
    void imprimir()
    {
        String ocupacion = "";
        String cabecera1 = "";
        String cabecera2 = "";
        String cabecera3 = "";
        String cabecera4 = "";

        for (int i = 0; i < array.length; i++)
        {
            cabecera1 = cabecera1 + "────┬";
            cabecera2 = cabecera2 + " "+  (i+1) + (i+1<10 ? " ": "")+  " |";
            cabecera3 = cabecera3 + "────┼";
            cabecera4 = cabecera4 + "────┴";
            ocupacion = ocupacion + " " + array[i] + "  |" ;
        }

        System.out.println("\n");
        System.out.println("┌─────────┬" + cabecera1);
        System.out.println("│Mesa nº  │" + cabecera2);
        System.out.println("├─────────┼" +cabecera3);
        System.out.println("│Ocupación│" + ocupacion);
        System.out.println("└─────────┴" +cabecera4);
        System.out.println("\n");
    }

    // Busca la primera mesa vacia
    int buscarPrimeraMesaVacia()
    {
        boolean noEncontrada = true;
        int mesaVacia = -1;

        // Bucle para recorrer el array.
        for (int i = 0; i < array.length; i++)
        {
            //Establece una condicion en la que revisa que el array
            // este vacio y que la mesa no encontrada sea true
            if (array[i] == 0 && noEncontrada)
            {
                mesaVacia = i;
                noEncontrada = false;
            }
        }

        return mesaVacia;
    }

    int buscarMesaParaCompartir(int numPersonas)
    {
        boolean noEncontrada = true;
        int mesaParaCompartir = -1;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] + numPersonas <= tamanioMesa && noEncontrada)
            {
                mesaParaCompartir = i;
                noEncontrada = false;
            }
        }

        return mesaParaCompartir;
    }

    // Busca la mesa mas cerca de una posicion en concreto.
    int buscarMesaCompartirMasCercaDe(int mesaBuscada, int numPersonas)
    {
        boolean noEncontrada = true;
        int mesaSalida = -1;

        // Buscamos por la derecha y por la izquierda
        int iDer = mesaBuscada;
        int iIzq = mesaBuscada;

        // noEncontrada=true Y (2 >= 0 O 2<10)
        while ( noEncontrada && (iIzq >= 0 || iDer < array.length) )
        {
            // 2>=0 Y array[2]+1 <= 4
            if (iIzq >= 0 && array[iIzq] + numPersonas <= tamanioMesa )
            {
                // mesaSalida=2
                mesaSalida = iIzq;
                noEncontrada = false;

            }
            // 1
            iIzq--;

            // (SI NO ENTRA POR LA CONDICION ANTERIOR)
            // true Y 2<10 Y array[2]+1<=4
            if (noEncontrada && iDer < array.length && array[iDer] + numPersonas <= tamanioMesa)
            {
                // mesaSalida=2
                mesaSalida = iDer;
                noEncontrada = false;
            }
            // 3
            iDer++;

        }

        return mesaSalida;
    }

    // busca compartir un numero de mesas consecuivas.
    int buscarCompartirNMesasConsecutivas(int numMesasConsecutivas,int numPersonas) {

        boolean noEncontrada = true;
        int mesaSalida = -1;

        // Bucle 0 ; true Y 0<(10-7) ; 1
        for (int i = 0; noEncontrada && i < array.length-numMesasConsecutivas; i++) {

            // totalMesasConsecutivas = 0
            int totalMesasConsecutivas = 0;
            // Bucle 0 ; 0<7 ; 1
            for (int j = 0; j < numMesasConsecutivas; j++) {
                totalMesasConsecutivas += array[i+j];
                // j=0 totalMesasConsecutivas = 0+array[0+0]
                // j=1 totalMesasConsecutivas = array[0]+array[0+1]
                // j=2 totalMesasConsecutivas = (array[0]+array[1])+array[0+2]
            }

            // (x)<=(7*4) Y true
            if (totalMesasConsecutivas + numPersonas <= numMesasConsecutivas*tamanioMesa && noEncontrada)
            {
                // mesaSalida = 0;
                mesaSalida = i;
                noEncontrada = false;
            }

        }
        // return 0;
        return mesaSalida;
    }

    int comensalesTotales()
    {
        int comensalesTotales = 0;

        for (int i = 0; i < array.length; i++)
        {
            comensalesTotales = comensalesTotales + array[i];
        }
        return comensalesTotales;
    }

}

