import pygame

pygame.font.init()



tabuleiro = [[0,0,0],
            [0,0,0],
            [0,0,0]]

def desenhar_X(janela,x,y,t):
    pygame.draw.line(janela,(255,0,0),(x*t,y*t),((x+1)*t,(y+1)*t),3)
    pygame.draw.line(janela,(255,0,0),(x*t,(y+1)*t),((x+1)*t,(y)*t),3)
def desenhar_tabuleiro(janela,tabuleiro):
    w = 600
    h = 600
    tamanho = 600/3
    for i in range(1,3):
        pygame.draw.line(janela,(0,0,0),(0,i*tamanho),(w,i*tamanho),3)
        pygame.draw.line(janela,(0,0,0),(i*tamanho,0),(i*tamanho,h),3)

    raio = int(tamanho/2)
    for i in range(len(tabuleiro)):
        for j in range(len(tabuleiro)):
            if tabuleiro[i][j] == -1:
                pygame.draw.circle(janela, (0,0,255), ((i+1)*tamanho-raio, (j+1)*tamanho-raio),raio, 3)
            if tabuleiro[i][j] == 1:
                desenhar_X(janela,i,j,tamanho)
def redesenhar(janela,tabuleiro):
    janela.fill((255,255,255))
    desenhar_tabuleiro(janela,tabuleiro)
def ganhador(t):
    for i in range(len(t)):
        s=0
        for j in range(len(t)):
            s+= t[i][j]
        if s==3:
            return 1
        if s==-3:
            return -1
    for i in range(len(t)):
        s=0
        for j in range(len(t)):
            s+= t[j][i]
        if s==3:
            return 1
        if s==-3:
            return -1

    s=0
    for i in range(len(t)):
        s+= t[i][i]
    if s==3:
        return 1
    if s==-3:
        return -1

    s=0
    for i in range(len(t)):
        s+= t[i][(len(t)-1)-i]
    if s==3:
        return 1
    if s==-3:
        return -1

    return 0

        
def main(jogador=1):
    janela = pygame.display.set_mode((600,600))
    
    jogada=0
    g=0
    while True:
        redesenhar(janela,tabuleiro)
        pygame.display.update()
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                return 
            if evento.type== pygame.MOUSEBUTTONUP and jogada<9 and g==0:
                p = pygame.mouse.get_pos()
                i = int(p[0]/200)
                j = int(p[1]/200)
                
                if tabuleiro[i][j]==0:
                    jogada+=1
                    if jogador == 1:
                        tabuleiro[i][j]=1
                        jogador=-1
                    else:
                        tabuleiro[i][j]=-1
                        jogador=1
                print(i,j)
                print(tabuleiro[i][j])
                print(jogada)
                g = ganhador(tabuleiro)
                if g!=0:
                    print(f"ganhador: {g}")
    
    


main()