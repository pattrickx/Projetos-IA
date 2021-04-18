from copy import deepcopy as copy
class no:
    def __init__(self,estado,tipo,pai):
        self.estado = estado
        self.tipo = tipo
        self.peso=None
        self.filhos=[]
        self.Efolha = self.Folha()
        self.pai=pai
    def Folha(self):
        for i in range(len(self.estado)):
            s=0
            for j in range(len(self.estado)):
                s+= self.estado[i][j]
            if s==3:
                return 1
            if s==-3:
                return -1
        for i in range(len(self.estado)):
            s=0
            for j in range(len(self.estado)):
                s+= self.estado[j][i]
            if s==3:
                return 1
            if s==-3:
                return -1

        s=0
        for i in range(len(self.estado)):
            s+= self.estado[i][i]
        if s==3:
            return 1
        if s==-3:
            return -1

        s=0
        for i in range(len(self.estado)):
            s+= self.estado[i][(len(self.estado)-1)-i]
        if s==3:
            return 1
        if s==-3:
            return -1

        for i in self.estado:
            for j in i:
                if j==0:
                    return None
        return 0

class min_max:
    def __init__(self): 
        self.arvore=[]
    
    def criar_arvore(self,estado,tipo):
        self.arvore.append(no(estado,tipo,-1))

        pilha = []
        pilha.append(self.arvore[0])
        
        while pilha:
            aux = pilha.pop(-1)

            if aux.Efolha!= None:
                aux.peso = aux.Efolha
                if aux.pai.peso == None:
                    aux.pai.peso = aux.peso
                else:
                    if aux.pai.tipo == 1 and (aux.pai.peso< aux.peso):
                        aux.pai.peso = aux.peso
                    elif aux.pai.peso> aux.peso:
                        aux.pai.peso = aux.peso
            else :
                pilha+=self.sucessores(aux)
        # print(self.arvore[0].peso)
        self.print_arvore(self.arvore[0])
    def buscar_estado(self):
        ...
    
    def sucessores(self,N):
        Sucessores = []
        estado = N.estado
        proximo = 1 if (0 == N.tipo or N.tipo == -1)  else -1

        for i in range(len(estado)):
            for j in range(len(estado)):
                if estado[i][j]==0:
                    aux = copy(estado)
                    aux[i][j]=proximo
                    Sucessores.append(no(aux,proximo,N))
        N.filhos=Sucessores
        return Sucessores
    def print_arvore(self,Raiz):
        lista= []
        lista.append(Raiz)
        file = open("t.txt", 'w')
        text =""
        n=0
        while lista:
            n+=len(lista)
            for i in range(len(lista[0].estado)):
                
                for j in lista:
                    text += f"{j.peso}:"
                text+="\n"
                # print(text)
            l=[]
            for x in lista:
                l += x.filhos 
            lista=l

        print(n)
        file.write(text)
        file.close()






