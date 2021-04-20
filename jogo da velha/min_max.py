from copy import deepcopy as copy
import numpy as np
class no:
    def __init__(self,estado,tipo,pai,profundidade):
        self.profundidade = profundidade 
        self.dist_folha=None
        self.estado = estado
        self.tipo = tipo
        self.peso=None
        self.filhos=None
        self.Efolha = self.Folha(self.estado)
        self.pai=pai
    def Folha(self,t):
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
    def minimax(self,aux):
        if aux.pai == None:
            return 0
        if aux.pai.peso == None:
            aux.pai.peso = aux.peso
            # aux.pai.dist_folha=(aux.dist_folha+aux.profundidade)-aux.pai.profundidade
        else:
            if aux.pai.tipo == 1:
                if aux.pai.peso < aux.peso:
                    aux.pai.peso = aux.peso
                #     aux.pai.dist_folha=(aux.dist_folha+aux.profundidade)-aux.pai.profundidade
                # elif aux.pai.peso==aux.peso and aux.pai.dist_folha>(aux.dist_folha+aux.profundidade)-aux.pai.profundidade:
                #     aux.pai.dist_folha=(aux.dist_folha+aux.profundidade)-aux.pai.profundidade

            elif aux.pai.tipo == -1:
                if aux.pai.peso > aux.peso:
                    aux.pai.peso = aux.peso
                #     aux.pai.dist_folha=(aux.dist_folha+aux.profundidade)-aux.pai.profundidade
                # elif aux.pai.peso==aux.peso and aux.pai.dist_folha>(aux.dist_folha+aux.profundidade)-aux.pai.profundidade:
                #     aux.pai.dist_folha=(aux.dist_folha+aux.profundidade)-aux.pai.profundidade
        
    def criar_ramo(self,raiz):
        pilha=[]
        pilha.append(raiz)
        while pilha:
            aux = pilha[-1]
            if aux.Efolha!= None:
                aux.peso = aux.Efolha
                # aux.dist_folha = 0
                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            elif aux.peso!= None:
                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            else:
                pilha+=self.sucessores(aux)
    def criar_arvore(self,estado,tipo):
        print("Criando Arvore")
        self.arvore.append(no(estado,tipo,None,0))
        self.raiz = self.arvore[0]
        pilha = []
        pilha.append(self.arvore[0])
        
        while pilha:
            aux = pilha[-1]
            if aux.Efolha!= None:
                aux.peso = aux.Efolha
                # aux.dist_folha = 0
                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            elif aux.peso!= None:
                self.minimax(aux)
                self.poda_alpha_beta(pilha,aux)
                if aux in pilha: pilha.remove(aux)
            else:
                pilha+=self.sucessores(aux)
        # print(self.arvore[0].peso)
        # self.print_arvore(self.arvore[0])
        print("Arvore Criada")
    def igual(self,A,B):
        s=0
        for i in range(len(A)):
            for j in range(len(A[0])):
                if A[i][j]!=B[i][j]:
                    return False
        return True
    def buscar_estado(self,tabuleiro):
        print("buscando estado")
        
        if self.igual(tabuleiro,self.raiz.estado) == False:
            for i in self.raiz.filhos:
                if self.igual(tabuleiro,i.estado):
                    self.raiz = i
                    break
        if self.raiz.filhos == None:
            print("criando ramo")
            self.criar_ramo(self.raiz)
        s =[]
        for i in self.raiz.filhos:
            if i.peso!=None:
                s.append(i)
        s = sorted(s, key = lambda x:x.peso, reverse=True)
        # for jogada in self.raiz.filhos:
        #     if jogada.peso != None and jogada.peso>=score[0]:
        #         score=(jogada.peso,jogada)
        # self.raiz = score[1]
        # for i in s:
        #     print(i.peso,i.tipo)
        #     for j in i.estado:
        #         print(j)
        #     print("")


        self.raiz = s[0]

        return copy(self.raiz.estado)

    def sucessores(self,N):
        Sucessores = []
        estado = N.estado
        proximo = -1 if N.tipo == 1  else 1

        for i in range(len(estado)):
            for j in range(len(estado)):
                if estado[i][j]==0:
                    aux = copy(estado)
                    aux[i][j]= copy(N.tipo)
                    Sucessores.append(no(aux,proximo,N,N.profundidade+1))
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






