
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <math.h>

uint8_t blackjack_jugador = 0;

void calc_blackjack(uint32_t *array, uint32_t suma, uint32_t as, uint32_t jugador, uint32_t ind, uint8_t n_croupier, double *prob);

int main() {
    
    uint32_t jugador, croupier, nums[10];
    char jugador_char[10];
    
    uint8_t n_croupier = 1;
    
    // Lectura de datos
    if (!scanf("%d", &jugador)) {
	if (scanf("%s", jugador_char)) {
	    if (jugador_char[0] == 'B') {
		blackjack_jugador = 1;
        	jugador = 21;
	    }
	}
    }
    
    scanf("%d", &croupier);
    for (int i = 0; i < 10; i++) {
        scanf("%d", &(nums[i]));
    }
    
    uint32_t as = 0;
    if ((croupier == 1) || (croupier == 11)) {
        croupier = 0;
        as = 1;
    }
    
    // Probabilidades obtenidas
    double prob[3];
    // Inicializacion prob
    for (uint32_t j = 0; j < 3; j++) {
        prob[j] = 0;
    }
    
    calc_blackjack(nums, croupier, as, jugador, 10, n_croupier, prob);
    
    for (int i = 0; i < 3; i++) {
        prob[i] = floor(prob[i]*1000)/10;
    }
    
    printf("%.1f\n%.1f\n%.1f\n", prob[0], prob[1], prob[2]);
    
    return 0;
}


void calc_blackjack(uint32_t *array, uint32_t suma, uint32_t as, uint32_t jugador, uint32_t ind, uint8_t n_croupier,  double *prob) {
    
    // Copia local del array
    uint32_t local[10];
    memcpy(local, array, sizeof(uint32_t) * 10);
    
    // Robamos la carta
    if (ind < 10) {
        local[ind]--;
        if (ind == 0) {
            // Hemos robado AS
            as++;
        } else {
            // Suma el valor correcto.
            suma += ind+1;
        }
        n_croupier++;
    }
    
    uint32_t suma_max = as ? suma + as + 10 : suma;
    uint32_t suma_min = suma + as;
    
    // Calculamos las cartas en la baraja
    uint32_t n_cartas = 0;
    for (uint32_t i = 0; i < 10; i++) {
        n_cartas += local[i];
    }
    
    // Utilizamos la suma max para decidir si tomamos otra carta.
    // Parte recursiva
    if (((suma_max < 17) || ((suma_max > 21) && (suma_min < 17))) && n_cartas) {
        
        // Pedimos carta.
        for (uint32_t i = 0; i < 10; i++) {
            if (local[i] > 0) {
                // Podemos tomar esta carta
                double prob_camino = ((double)local[i])/((double)n_cartas);
                
                double prob_subcamino[3];
                
                // Inicializacion prob_subcamino
                for (uint32_t j = 0; j < 3; j++) {
                    prob_subcamino[j] = 0;
                }
                
                calc_blackjack(local, suma, as, jugador, i, n_croupier, prob_subcamino);
                
                // Actualizar Probabilidades de salida
                for (uint32_t j = 0; j < 3; j++) {
                    prob[j] += prob_camino * prob_subcamino[j];
                }
                
            }
        }
    } else {
        // Parte No recursiva
        if ((suma_max == 21) && (n_croupier == 2)) {
            if (blackjack_jugador == 1) {
                //Empate
                prob[2] = 0.0;
                prob[1] = 1.0;
                prob[0] = 0.0;
            } else {
                prob[2] = 1.0;
                prob[1] = 0.0;
                prob[0] = 0.0;
            }
        } else if (blackjack_jugador) {
            prob[2] = 0.0;
            prob[1] = 0.0;
            prob[0] = 1.0;
        } else {
            if (suma_min > 21) {
                prob[2] = 0.0;
                prob[1] = 0.0;
                prob[0] = 1.0;
            }
            else if (suma_max > 21) {
                if (suma_min > jugador) {
                    prob[2] = 1.0;
                    prob[1] = 0.0;
                    prob[0] = 0.0;
                }
                else if (suma_min == jugador) {
                    prob[2] = 0.0;
                    prob[1] = 1.0;
                    prob[0] = 0.0;
                } else {
                    prob[2] = 0.0;
                    prob[1] = 0.0;
                    prob[0] = 1.0;
                }
            }
            else {
                if (suma_max > jugador) {
                    prob[2] = 1.0;
                    prob[1] = 0.0;
                    prob[0] = 0.0;
                }
                else if (suma_max == jugador) {
                    prob[2] = 0.0;
                    prob[1] = 1.0;
                    prob[0] = 0.0;
                } else {
                    prob[2] = 0.0;
                    prob[1] = 0.0;
                    prob[0] = 1.0;
                }
            }
        }
    }
    
}
