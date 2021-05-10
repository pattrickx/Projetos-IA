import pygame
pygame.font.init()  
from min_max import min_max
from copy import deepcopy as copy
import time
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
            if tabuleiro[j][i] == 1:
                pygame.draw.circle(janela, (255,0,0), ((i+1)*tamanho-raio, (j+1)*tamanho-raio),raio-20, 8)
            if tabuleiro[j][i] == -1:
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

def desenhar_arvore(janela,arvore,x,y):
    janela.fill((255,255,255))
    w=75
    h=75
    espaco=10
    tamanho = w/3
    X = int(x)
    Y = int(y)
    
    for id_no,n in enumerate(arvore):
        tabuleiros = []
        tabuleiros.append(n.estado)
        if n.filhos != None:
            for f in n.filhos: 
                tabuleiros.append(f.estado)
        for d,tabuleiro in enumerate(tabuleiros): 
            if d>0:
                x=(X-int((len(tabuleiros))*(w+espaco)/2))+(w+espaco)*d
                y=Y+(w+espaco)
            else:
                pygame.draw.rect(janela,(0,255,0),(x,y,w,h),3)
            for i in range(1,3):
                pygame.draw.line(janela,(0,0,0),(x,i*tamanho+y),(w+x,i*tamanho+y),3) #horizontal
                pygame.draw.line(janela,(0,0,0),(i*tamanho+x,y),(i*tamanho+x,h+y),3)

            raio = int(tamanho/2)
            for i in range(len(tabuleiro)):
                for j in range(len(tabuleiro)):
                    if tabuleiro[j][i] == 1:
                        pygame.draw.circle(janela, (255,0,0), (((i+1)*tamanho-raio)+x, ((j+1)*tamanho-raio)+y),int(raio*0.8), 2)
                    if tabuleiro[j][i] == -1:
                            pygame.draw.line(janela,(0,0,255),(x+tamanho*i,y+tamanho*j),(x+tamanho*(i+1),y+tamanho*(j+1)),2)
                            pygame.draw.line(janela,(0,0,255),(x+tamanho*i,y+tamanho*(j+1)),(x+tamanho*(i+1),y+tamanho*j),2)
        Y+=(w+espaco)
        if n.filhos != None:
            for d,f in enumerate(n.filhos): 
                igual=True
                for i in range(len(f.estado)):
                    if not f.estado[i]==arvore[id_no+1].estado[i]:
                        igual=False
                        break
                if igual:
                    X=(X-int((len(n.filhos)+1)*(w+espaco)/2))+(w+10)*(d+1)
                    x=int(X)
                    break
        
def main(tabuleiro):
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
                    jogador = 1
                if 309<i<511 and 249<j<451:
                    jogador = -1
                    
    jogada=0
    g=0
    IA = min_max()
    if jogador == 1:
        IA.criar_arvore(tabuleiro.copy(),1)
    while jogada<9 and g==0:
        
        desenhar_jogo(janela,tabuleiro)
        pygame.display.update()
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                return 
            if evento.type== pygame.MOUSEBUTTONUP and jogada<9 and g==0:
                p = pygame.mouse.get_pos()
                j = int(p[0]/200)
                i = int(p[1]/200)
                
                if tabuleiro[i][j]==0:
                    jogada+=1
                    if jogador == -1:
                        tabuleiro[i][j]=-1
                        jogador=1
                        if jogada==1:
                            IA.criar_arvore(tabuleiro.copy(),1)
                        g = ganhador(tabuleiro)
        if jogador == 1 and g == 0 and jogada <9:
            jogada+=1
            tabuleiro = IA.buscar_estado(tabuleiro.copy())
            jogador=-1

        g = ganhador(tabuleiro)
        if g!=0:
            print(f"ganhador: {g}")
        if g==0 and jogada ==9:
            print("Empate")
    ga = "Ninguem"
    if g==1:
        ga = "IA"
    if g==-1:
        ga = "Você"
    IA.buscar_estado(tabuleiro.copy())
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
    x=600
    y=0
    escolha= True
    cm = False
    p0=[]
    p=[]
    DISPLAYSURF = pygame.display.set_mode((1200, 600), pygame.RESIZABLE)
    while escolha:
        
        pygame.display.update()
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                return 

            if evento.type == pygame.MOUSEBUTTONDOWN:
                p0 = pygame.mouse.get_pos()
                cm = True
            if evento.type == pygame.MOUSEBUTTONUP:
                cm = False
                x = x-(p0[0]-p[0])
                y = y-(p0[1]-p[1])
                
        if cm:
            p = pygame.mouse.get_pos()
            desenhar_arvore(janela,IA.arvore,x-(p0[0]-p[0]),y-(p0[1]-p[1]))
        else:
            desenhar_arvore(janela,IA.arvore,x,y)


main(tabuleiro)
