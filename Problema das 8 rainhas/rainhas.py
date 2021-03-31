from random import randint

# tabuleiro = [[1,1,1,1,1,1,1,1],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0]]
tabuleiro = [[0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0]]

for i in range(8):
    x = randint(0,7)
    y = randint(0,7)
    while tabuleiro[x][y] == 1:
        x = randint(0,7)
        y = randint(0,7)
    tabuleiro[x][y] = 1

class arestas:
    def __init__(self,origem,destino):
        self.origem = origem 
        self.destino = destino

class no:
    def __init__(self,arestas,x,y):
        self.arestas = arestas
        self.x=x
        self.y=y
        self.pai=None
        self.v=False

def get_arestas(x,y):
    As = []
    for i in range(x-1,x+2):
        for j in range(y-1,y+2):
            if (i!=x or j!=y) and( -1<i<len(tabuleiro) and -1<j<len(tabuleiro)):
                As.append(arestas(x*len(tabuleiro)+y,j+i*len(tabuleiro)))
    return As

def Atacando(x,y):
    f = 0
    for i in range(len(tabuleiro)):
        if i!=x and tabuleiro[i][y]==1:
            f+=1
    for j in range(len(tabuleiro)):
        if j!=y and tabuleiro[x][j]==1:
            f+=1
    # Diagonal Principal
    # print("Principal")
    MIN = -y
    MAX = len(tabuleiro)-x
    if x<y:
        MIN = -x
        MAX = len(tabuleiro)-y
    for i in range(MIN,MAX):
        # print(f"{[x+i]} : {[y+i]}")
        if (x+i!=x or y+i!=y) and tabuleiro[x+i][y+i]==1:
            f+=1

    # Diagonal Secondaria
    print("Secondaria")
    
    # if y == len(tabuleiro)-1 or x==0:
    #     X=x
    #     Y=y
    # else:
    if x+y<len(tabuleiro):
        X=0
        Y=x+y
    else:
        X = x+y-(len(tabuleiro)-1)
        Y = len(tabuleiro)-1

    while X<len(tabuleiro) and Y>=0:
        # print(f"{[X]} : {[Y]}")
        
        if (X!=x or Y!=y) and tabuleiro[X][Y]==1:
            f+=1
        X = X+1
        Y = Y-1
    print(f"SOMA um: {f}")
    return f

def fit(g):
    FIT=0
    lista = []
    lista.append(g[0])
    lista[0].pai=-1
    while lista:
        aux = lista.pop(0)

        if tabuleiro[aux.x][aux.y]==1 and not aux.v:
            FIT+=Atacando(aux.x,aux.y)
        aux.v = True
        
        for a in aux.arestas:
            fi = g[a.destino]
            if (not fi.v) and fi.pai != aux.pai:
                
                fi.pai = aux
                lista.append(fi)
    return FIT

'''
Minimizar função
Y = Soma de quantos ataques cada rainha consegue efetuar
'''
grafo = []

for i in range(len(tabuleiro)):
    for j in range(len(tabuleiro)):
        n = no(get_arestas(i,j),i,j)
        grafo.append(n)


print(fit(grafo))
for i in tabuleiro:
    print(i)