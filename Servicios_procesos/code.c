#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>

int main(){
    pid_t pid, hijo_pid, pid2, hijo2_pid;
    int chacho[2], primo[2];
    char buffer[100];
    char saludoAbuelo[] = "saludo del abuelo";
    char saludoPadre[] = "saludo del padre";
    char saludoHijo[] = "saludo del hijo";
    char saludoNieto[] = "saludo del nieto";

    pipe(chacho);
    pipe(primo);
    pid = fork();
    if(pid == -1){ perror("Error en fork"); exit(-1); }
    if(pid == 0){ //Hijo 1
        pid2 = fork();
        switch(pid2){
            case -1: perror("Error en fork"); exit(-1);
            case 0: //Hijo 2
                close(primo[1]);
                read(primo[0], buffer, sizeof(buffer));
                printf("Nieto recibe: %s\n", buffer);
                
                printf("Nieto envia saludo al Padre %s\n", buffer);
                close(chacho[0]);
                write(chacho[1], buffer, strlen(buffer));
                break;
            default: //Hijo 1
                close(chacho[1]);
                read(chacho[0], buffer, sizeof(buffer));
                printf("Padre recibe de abuelo: %s\n", buffer);
                
                printf("Padre envia saludo al Nieto %s\n", saludoPadre);
                close(primo[0]);
                write(primo[1], buffer, strlen(buffer));
                
                close(chacho[1]);
                read(chacho[0], buffer, sizeof(buffer));
                printf("Padre recibe del Nieto: %s\n", buffer);

                printf("Padre envia saludo al Abuelo %s\n", saludoPadre);
                close(primo[0]);
                write(primo[1], buffer, strlen(buffer));
                break;
        }
    } else { //Abuelo
        printf("Abuelo envia saludo al Padre %s\n", saludoAbuelo);
        close(chacho[0]);
        write(chacho[1], saludoAbuelo, strlen(saludoAbuelo));
        hijo_pid = wait(NULL); // Espera a que el hijo termine

        close(primo[1]);
        read(primo[0], buffer, sizeof(buffer));
        printf("Abuelo recibe del Padre: %s\n", buffer);
    }
}