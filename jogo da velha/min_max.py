from copy import deepcopy as copy
import numpy as np
class no:
    def __init__(self,estado:list,tipo:int,pai:object or int,profundidade:int ):
        """[summary]

        Args:
            estado (list): [description]
            tipo (int): [description]
            pai (no or int): [description]
            profundidade (int): [description]
        """
        self.profundidade = profundidade 
        self.dist_folha=None
        self.estado = estado
        self.tipo = tipo
        self.peso=None
        self.filhos=None
        self.Efolha = self.Folha(self.estado)
        self.pai=pai
    def Folha(self,t:list)->int or None:
        #linhas
        for i in range(3):
            s=t[i][0]+t[i][1]+t[i][2]
            if s==3:
                return 1
            if s==-3:
                return -1
        #colunas
        for i in range(3):
            s=t[0][i]+t[1][i]+t[2][i]
            if s==3:
                return 1
            if s==-3:
                return -1

        #diagonal principal
        s = t[0][0]+t[1][1]+t[2][2]
        if s==3:
            return 1
        if s==-3:
            return -1

        #diagonal secundaria
        s = t[0][2]+t[1][1]+t[2][0]
        if s==3:
            return 1
        if s==-3:
            return -1
        
        # não folha
        for i in self.estado:
            for j in i:
                if j==0:
                    return None
        # Velha
        return 0

class min_max:
    def __init__(self): 
        self.arvore=[]
        self.raiz=[]
    def poda_alpha_beta(self,pilha,aux):
        if aux.pai == None:
            return 0
        avo = aux.pai.pai
        if avo!= None and avo.peso:
            if avo.tipo==1 and (avo.peso > aux.pai.peso):
                for x in aux.pai.filhos:
                    if x in pilha: pilha.remove(x)
            elif avo.tipo == -1 and (avo.peso < aux.pai.peso):
                for x in aux.pai.filhos:
                    if x in pilha: pilha.remove(x)
    def minimax(self,aux:no):
        if aux.pai == None:
            return 0
        if aux.pai.peso == None:
            aux.pai.peso = aux.peso
        else:
            if aux.pai.tipo == 1:
                if aux.pai.peso < aux.peso:
                    aux.pai.peso = aux.peso
            elif aux.pai.tipo == -1:
                if aux.pai.peso > aux.peso:
                    aux.pai.peso = aux.peso

    def criar_ramo(self,raiz:no):
        pilha=[]
        pilha.append(raiz)
        while pilha:
            aux = pilha[-1]
            if aux.Efolha!= None:
                aux.peso = aux.Efolha

                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            elif aux.peso!= None:
                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            else:
                pilha+=self.sucessores(aux)
    def criar_arvore(self,estado:list,tipo:int):
        print("Criando Arvore")
        self.raiz =no(estado,tipo,None,0)
        self.arvore.append(self.raiz)
        pilha = []
        pilha.append(self.arvore[0])
        
        while pilha:
            aux = pilha[-1]
            if aux.Efolha!= None:
                aux.peso = aux.Efolha

                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            elif aux.peso!= None:
                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            else:
                pilha+=self.sucessores(aux)

        # self.save_arvore(self.arvore[0])
        print("Arvore Criada")
    def igual(self,A:list,B:list)->bool:
        s=0
        for i in range(len(A)):
            for j in range(len(A[0])):
                if A[i][j]!=B[i][j]:
                    return False
        return True
    def buscar_estado(self,tabuleiro:list)-> list or int:
        if self.raiz.Efolha!=None:
            return -1
        print("buscando estado")
        igual=True
        for i in range(len(tabuleiro)):
            if not tabuleiro[i]==self.raiz.estado[i]:
                igual=False
                break
        if not igual:
            for i in self.raiz.filhos:
                igual=True
                for j in range(len(tabuleiro)):
                    if not tabuleiro[j]==i.estado[j]:
                        igual=False
                        break
                if igual:
                    self.raiz = i
                    self.arvore.append(self.raiz)
                    if self.raiz.Efolha!=None:
                        return -1
                    break
        if self.raiz.filhos == None:
            print("criando ramo")
            self.criar_ramo(self.raiz)
        s =[]
        for i in self.raiz.filhos:
            if i.peso!=None:
                s.append(i)
        s = sorted(s, key = lambda x:-x.peso)

        self.raiz = s[0]
        self.arvore.append(self.raiz)
        return np.copy(self.raiz.estado).tolist()

    def sucessores(self,N:no)->list:
        Sucessores = []
        estado = N.estado
        proximo = -1 if N.tipo == 1  else 1

        for i in range(len(estado)):
            for j in range(len(estado)):
                if estado[i][j]==0:
                    aux = np.copy(estado).tolist()
                    aux[i][j]= int(N.tipo)
                    Sucessores.append(no(aux,proximo,N,N.profundidade+1))
        N.filhos=Sucessores
        return Sucessores
    def save_arvore(self,Raiz:no):
        lista= []
        lista.append(Raiz)
        file = open("t.txt", 'w')
        text =""
        n=0
        while lista:
            n+=len(lista)
            for j in range(4):
                for i in lista:
                    if j<3:
                        text += f"{i.estado[j]}:"
                    else:
                        text += f"{i.peso}:"
                text+="\n"
            text+="\n"
            l=[]
            for x in lista:
                if x.filhos != None:
                    l += x.filhos 
            lista=l

        print(n)
        file.write(text)
        file.close()






