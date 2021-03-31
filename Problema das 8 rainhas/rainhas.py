from random import randint
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

def FuncoesSucessoras(estado):
    Sucessores = []

    return Sucessores

def PrintEstado(estado):
    for i in estado:
        print(i)

def BuscaLocal(estado):

    ...

estado = GeradorDeEstado()
print(FuncaoObjetivo(estado))
PrintEstado(estado)