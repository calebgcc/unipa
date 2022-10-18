#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h> 
#include <sys/types.h> 
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <netinet/in.h>

#define PORT 9973
#define MAX_LINE 256

int main(){
    int sockfd, client_sock, client_size;
    struct sockaddr_in server_addr = {0};
    struct sockaddr_in client_addr = {0};
    char buffer[MAX_LINE];
    
    
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    
    if((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("Socket creation failed :(");
        exit(EXIT_FAILURE);
    }
    
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_addr.sin_port = htons(PORT);
    
    if(bind(sockfd, (struct sockaddr*)&server_addr, sizeof(server_addr))<0){
        perror("Socket bind failed :(");
        exit(EXIT_FAILURE);
    }
    
    if(listen(sockfd, 1) < 0){
        perror("Socket listen failed :(");
        exit(EXIT_FAILURE);
    }
    
    client_size = sizeof(client_addr);
    client_sock = accept(sockfd, (struct sockaddr*)&client_addr, &client_size);
    
    if (client_sock < 0){
        perror("Socket accept failed :(");
        exit(EXIT_FAILURE);
    }
    printf("Client connected at IP: %s and port: %i\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));

    int n; 
    while(1){
        n = recv(client_sock, buffer, MAX_LINE, 0);
        buffer[n] = '\0';
        
        if(strcmp(buffer,"close") == 0){
            printf("[Client says Bye Bye]\n");
            break;
        }

        printf(">>> %s\n", buffer);
        printf("::: ");
        gets(buffer);
        send(client_sock, buffer, strlen(buffer), 0);
        
        if(strcmp(buffer,"close") == 0)
            break;
    }
    
    // Closing the socket:
    close(client_sock);
    close(sockfd);
    
    return 0;
}