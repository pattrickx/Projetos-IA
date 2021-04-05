from random import randint
import matplotlib.pyplot as plt
from copy import deepcopy as copy
# estado = [[1,1,1,1,1,1,1,1],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0],
#             [0,0,0,0,0,0,0,0]]

def GeradorDeEstado(P_size = 8):
    estado = [[0 for i in range(P_size)]for i in range(P_size)]
    for i in range(P_size):
        x = randint(0,P_size-1)
        y = randint(0,P_size-1)
        while estado[x][y] == 1:
            x = randint(0,P_size-1)
            y = randint(0,P_size-1)
        estado[x][y] = 1

    return estado

def Atacando(x,y,estado):
    f = 0
    for i in range(len(estado)):
        if i!=x and estado[i][y]==1:
            f+=1
    for j in range(len(estado)):
        if j!=y and estado[x][j]==1:
            f+=1
    # Diagonal Principal
    # print("Principal")
    MIN = -y
    MAX = len(estado)-x
    if x<y:
        MIN = -x
        MAX = len(estado)-y
    for i in range(MIN,MAX):
        # print(f"{[x+i]} : {[y+i]}")
        if (x+i!=x or y+i!=y) and estado[x+i][y+i]==1:
            f+=1

    # Diagonal Secondaria
    if x+y<len(estado):
        X=0
        Y=x+y
    else:
        X = x+y-(len(estado)-1)
        Y = len(estado)-1

    while X<len(estado) and Y>=0:
        # print(f"{[X]} : {[Y]}")
        
        if (X!=x or Y!=y) and estado[X][Y]==1:
            f+=1
        X = X+1
        Y = Y-1
    return f

def FuncaoObjetivo(estado):
    FO=0
    for i in range(len(estado)):
        for j in range(len(estado)):
            if estado[i][j]==1:
                FO+=Atacando(i,j,estado)
    return FO

def SucessoresIndividual(x,y,estado):
    new_estados = []
    # Positivo x
    for i in range(x+1,len(estado)):
        if estado[i][y]==0:
            aux =  copy(estado)
            aux[x][y]=0
            aux[i][y]=1
            new_estados.append(aux)
        else:
            break
            
    # Negativo x
    for i in range(x-1,-1,-1):
        if estado[i][y]==0:
            aux =  copy(estado)
            aux[x][y]=0
            aux[i][y]=1
            new_estados.append(aux)
        else:
            break
    

    # Positivo y
    for i in range(y+1,len(estado)):
        if estado[x][i]==0:
            aux =  copy(estado)
            aux[x][y]=0
            aux[x][i]=1
            new_estados.append(aux)
        else:
            break
    # Negativo y
    for i in range(y-1,-1,-1):
        if estado[x][i]==0:
            aux =  copy(estado)
            aux[x][y]=0
            aux[x][i]=1
            new_estados.append(aux)
        else:
            break
    
    # print("Principal")
    # Diagonal Principal baixo
    i=1
    while x+i<len(estado) and y+i<len(estado):
        # print(f"{[x+i]} : {[y+i]}")
        if estado[x+i][y+i]==0:
            aux = copy(estado)
            aux[x][y]=0
            aux[x+i][y+i]=1
            new_estados.append(aux)
        else:
            break
        i+=1
    # Diagonal Principal cima
    i=1
    while x-i>=0 and y-i>=0:
        # print(f"{[x+i]} : {[y+i]}")
        if estado[x-i][y-i]==0:
            aux = copy(estado)
            aux[x][y]=0
            aux[x-i][y-i]=1
            new_estados.append(aux)
        else:
            break
        i+=1

    # Diagonal Secundaria baixo
    i=1
    while x+i<len(estado) and y-i>=0:

        if estado[x+i][y-i]==0:
            aux = copy(estado)
            aux[x][y]=0
            aux[x+i][y-i]=1
            new_estados.append(aux)
        else:
            break
        i+=1
    # Diagonal Secundaria cima
    i=1
    while x-i>=0 and y+i<len(estado):

        if estado[x-i][y+i]==0:
            aux = copy(estado)
            aux[x][y]=0
            aux[x-i][y+i]=1
            new_estados.append(aux)
        else:
            break
        i+=1
    return new_estados
def FuncoesSucessoras(estado):
    Sucessores = []
    for i in range(len(estado)):
        for j in range(len(estado)):
            if estado[i][j]==1:
                Sucessores+=SucessoresIndividual(i,j,estado)
    return Sucessores

def PrintEstado(estado):
    for i in estado:
        print(i)

def BuscaLocal(n):
    list_mins =[]
    Melhores_Estados = []
    estado = GeradorDeEstado(n)
    # print("Estado inicial")
    # PrintEstado(estado)
    FO = FuncaoObjetivo(estado)
    Fmin = FO
    list_mins.append(Fmin)
    sucessores = FuncoesSucessoras(estado)
    run = True
    Melhor_Estado = estado
    
    Melhores_Estados.append(estado)
    while run:
        id_melhor = -1  
        FO = float('inf')
        for i,d in enumerate(sucessores):
            aux = FuncaoObjetivo(d)
            # list_mins.append(aux)
            if aux<FO:
                FO=aux
                id_melhor=i

        if FO>=Fmin:
            Melhores_Estados.append(sucessores[id_melhor])
            list_mins.append(FO)
            run = False
        else:
            Fmin=FO
            list_mins.append(Fmin)
            Melhores_Estados.append(sucessores[id_melhor])
            Melhor_Estado = sucessores[id_melhor]
            sucessores = FuncoesSucessoras(Melhor_Estado)
        
    return Melhores_Estados,list_mins
        
def BuscaEstocastica(n=8,t=100):
    run = True
    list_mins = []
    FO = n*n
    # while run:
    Melhores_Estados = []
    for i in range(t):
        print(i)
        ME,LM = BuscaLocal(n)
        list_mins+=LM
        Melhores_Estados+=ME
    return list_mins,Melhores_Estados

y,Melhores_Estados = BuscaEstocastica(8,100)
x = [i for i in range(len(y))] 

for i in range(len(y)):
    print(y[i])
    PrintEstado(Melhores_Estados[i])

# y.sort(reverse=True)
print(len(y))
print(y)
plt.subplot(1,2,1)
plt.plot(y)
plt.scatter(x,y,c='red')
plt.subplot(1,2,2)
y.sort(reverse=True)
plt.plot(y)
plt.scatter(x,y,c='red')

# print(FuncaoObjetivo(estado))
# PrintEstado(estado)

plt.show()


