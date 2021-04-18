import pygame
pygame.font.init()  
from min_max import min_max
tabuleiro = [[0,0,0],
            [0,0,0],
            [0,0,0]]
jogadas=[]
jogadas.append(tabuleiro.copy())
def desenhar_X(janela,x,y,t):
    pygame.draw.line(janela,(0,0,255),(x*t+20,y*t+20),((x+1)*t-20,(y+1)*t-20),8)
    pygame.draw.line(janela,(0,0,255),(x*t+20,(y+1)*t-20),((x+1)*t-20,(y)*t+20),8)

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
                pygame.draw.circle(janela, (255,0,0), ((i+1)*tamanho-raio, (j+1)*tamanho-raio),raio-20, 8)
            if tabuleiro[i][j] == 1:
                desenhar_X(janela,i,j,tamanho)

def desenhar_jogo(janela,tabuleiro):
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

def desenhar_menu(janela):
    janela.fill((255,255,255))
    fonte = pygame.font.SysFont("Arial",60)
    texto = fonte.render("Quem começa ?",1,(50,60,50))
    janela.blit(texto,(80,50))

    pygame.draw.rect(janela,(255,0,0),(90,250,200,200))
    fonte = pygame.font.SysFont("Arial",60)
    texto = fonte.render("IA",1,(255,255,255))
    janela.blit(texto,(162,320))
    
    pygame.draw.rect(janela,(0,0,255),(310,250,200,200))
    fonte = pygame.font.SysFont("Arial",60)
    texto = fonte.render("EU",1,(255,255,255))
    janela.blit(texto,(370,320))
    

def desenhar_fim(janela,ganhador):
    janela.fill((255,255,255))
    fonte = pygame.font.SysFont("Arial",60)
    texto = fonte.render(f"{ganhador} Ganhou !!!",1,(50,60,50))
    janela.blit(texto,(80,50))

    pygame.draw.rect(janela,(0,0,255),(150,250,300,200))
    fonte = pygame.font.SysFont("Arial",60)
    texto = fonte.render("Árvore",1,(255,255,255))
    janela.blit(texto,(212,315))

def desenhar_arvore():
    ...
def main():
    janela = pygame.display.set_mode((600,600))

    jogador = 0
    while jogador==0:
        desenhar_menu(janela)
        pygame.display.update()
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                return 
            if evento.type== pygame.MOUSEBUTTONUP:
                p = pygame.mouse.get_pos()
                i = p[0]
                j = p[1]
                if 89<i<291 and 249<j<451:
                    jogador = -1
                if 309<i<511 and 249<j<451:
                    jogador = 1
                    
    jogada=0
    g=0
    min_max().criar_arvore(tabuleiro,jogador)
    while jogada<9 and g==0:
        desenhar_jogo(janela,tabuleiro)
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
                # print(i,j)
                # print(tabuleiro[i][j])
                # print(jogada)
                jogadas.append(tabuleiro.copy())
                g = ganhador(tabuleiro)
                if g!=0:
                    print(f"ganhador: {g}")
                if g==0 and jogada ==9:
                    print("Empate")
    ga = "Ninguem"
    if g==1:
        ga = "Você"
    if g==-1:
        ga = "Você"
    escolha = False
    while not escolha:

        desenhar_fim(janela,ga)
        pygame.display.update()
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                return 
            if evento.type== pygame.MOUSEBUTTONUP:
                p = pygame.mouse.get_pos()
                i = p[0]
                j = p[1]
                if 149<i<451 and 249<j<451:
                    escolha = True

main()