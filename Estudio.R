G = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_G_D0_H1.txt", header = TRUE, sep = ",", dec = ".")
O = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Ord_D0_H1.txt", header = TRUE, sep = ",", dec = ".")
R = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Rnd_D0_H1.txt", header = TRUE, sep = ",", dec = ".")

boxplot(G['BEN'], O['BEN'], R['BEN'])

