import pandas as pd
import os
import sys
class Memoria:
    def __init__(self,memoria):
        self.Memoria = memoria

    def get_state(state):
        self.Memoria[0]
    def add_state(self,state):
        # print(self.Memoria)
        self.Memoria = pd.concat([self.Memoria,state])
        # print(self.Memoria)
        self.Memoria.drop_duplicates(inplace=True)
        # print(self.Memoria)
        self.Memoria.to_csv("src/algoritmo/utils/Memorias.csv",index=False)


if __name__ == "__main__":
    arg = sys.argv

    memoria=[]
    columns=["v0","v1","v2","v3","v4","v5","v6","v7","v8","v9","v10",
             "v11","v12","v13","v14","v15","v16","v17","v18","v19","v20",
             "v21","v22","v23","o0","o1","o2","o3","o4","o5","o6","o7",
             "parado","cima","baixo","direita","esquerda"]
    if os.path.isfile("src/algoritmo/utils/Memorias.csv"):
        memoria= pd.read_csv("src/algoritmo/utils/Memorias.csv")
    else:
        memoria=pd.DataFrame(columns=columns)


    lista_state= arg[2].split(",")
    lista_state = list(map(int, lista_state))

    state = pd.DataFrame(data=[lista_state],columns=columns)
    men = Memoria(memoria)
    if arg[1] == "1":
        print(f"add state\n Estados: {state}")
        men.add_state(state)
    if arg[1] == "2":
        Dir = men.get_state()

    # print(Dir)