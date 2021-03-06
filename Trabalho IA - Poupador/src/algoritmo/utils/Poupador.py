import pandas as pd
import os
import sys
class Memoria:
    def __init__(self,memoria):
        self.Memoria = memoria

    def get_state(state):
        self.Memoria[]

if __name__ == "__main__":
    arg = sys.argv[1]
    memoria=[]
    if os.isfile(arg):
        memoria= pd.read_csv(arg)
    else:
        columns=["v0","v1","v2","v3","v4","v5","v6","v7","v8","v9","v10",
                 "v11","v12","v13","v14","v15","v16","v17","v18","v19","v20",
                 "v21","v22","v23","o0","o1","o2","o3","o4","o5","o6","o7",
                 "parado","cima","baixo","direita","esquerda"]
        memoria=pd.DataFrame(columns=columns)
    men = Memoria[memoria]

    Dir = men.get_state()

    print(Dir)