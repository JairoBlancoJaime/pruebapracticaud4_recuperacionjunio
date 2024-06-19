package org.iesvdm.reservamesa;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
class ReservaMesaTest {

    @Test
    void failTest() {
        fail("TEST SIN IMPLEMENTAR");
    }



    @Test
    void rellenarMesas() {
        ReservaMesa mesa = new ReservaMesa();
        assertThat(mesa.getTamanioMesa()).isBetween(0, 4);
    }

    @Test
    void buscarPrimeraMesaVacia() {
        ReservaMesa mesa = new ReservaMesa();
        mesa.buscarPrimeraMesaVacia();

        boolean noEncontrada = true;
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }

        for (int i = 0; i < array.length; i++) {
            assertThat(array[i]).isNotEqualTo(0);
        }
    }

    @Test
    void buscarMesaParaCompartir(){
        ReservaMesa mesa = new ReservaMesa();

        boolean noEncontrada = true;
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        int tamanioMesa = mesa.getTamanioMesa();

        for (int i = 0; i < array.length; i++) {
            for (int numPersonas = 1; numPersonas <= tamanioMesa; numPersonas++) {
                assertThat(array[i] + numPersonas).isLessThanOrEqualTo(tamanioMesa);
            }
        }
    }


    @Test
    void buscarMesaCompartirMasCercaDe(){
        ReservaMesa mesa = new ReservaMesa();

        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        int tamanioMesa = mesa.getTamanioMesa();

        for (int mesaBuscada = 0; mesaBuscada < array.length; mesaBuscada++) {
            int iDer = mesaBuscada;
            int iIzq = mesaBuscada;

            for (int numPersonas = 1; numPersonas <= tamanioMesa; numPersonas++) {
                if (iIzq >= 0 && array[iIzq] + numPersonas <= tamanioMesa) {
                    assertThat(array[iIzq] + numPersonas).isLessThanOrEqualTo(tamanioMesa);
                } else if (iDer < array.length && array[iDer] + numPersonas <= tamanioMesa) {
                    assertThat(array[iDer] + numPersonas).isLessThanOrEqualTo(tamanioMesa);
                }
            }
        }
    }

    @Test
    void buscarCompartirNMesasConsecutivas(){
        ReservaMesa mesa = new ReservaMesa();

        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        int tamanioMesa = mesa.getTamanioMesa();
        int mesaSalida=0;

        for (int numMesasConsecutivas = 0; numMesasConsecutivas < array.length; numMesasConsecutivas++) {
            for (int numPersonas = 1; numPersonas <= tamanioMesa; numPersonas++) {
                for (int i = 0; i < array.length-numMesasConsecutivas; i++) {
                    int totalMesasConsecutivas = 0;
                    for (int j = 0; j < numMesasConsecutivas; j++) {
                        totalMesasConsecutivas += array[i+j];
                        assertThat(totalMesasConsecutivas).isLessThanOrEqualTo(40);
                    }
                    if (totalMesasConsecutivas + numPersonas <= numMesasConsecutivas*tamanioMesa) {
                        mesaSalida = i;
                        assertThat(mesaSalida).isLessThan(array.length-numMesasConsecutivas);
                    }
                }
            }
        }
    }

    @Test
    void comensalesTotales(){
        ReservaMesa mesa = new ReservaMesa();
        assertThat(mesa.comensalesTotales()).isBetween(1, 40);
    }
}