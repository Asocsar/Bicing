G = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_G_D0_H1.txt", header = TRUE, sep = ",", dec = ".")
O = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Ord_D0_H1.txt", header = TRUE, sep = ",", dec = ".")
R = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Rnd_D0_H1.txt", header = TRUE, sep = ",", dec = ".")
BG <- G$BEN
BO <- O$BEN
BR <- R$BEN
TG <- G$TIME
TO <- O$TIME
TR <- R$TIME
NG <- G$NODE
NO <- O$NODE
NR <- R$NODE

boxplot(BG,BO,BR,
        main = "Comparacion de Beneficios",
        at = c(1,2,3),
        names = c("Greedy", "Ordenat", "Random")
)

boxplot(TG,TO,TR,
        main = "Comparacion de Beneficios",
        at = c(1,2,3),
        names = c("Greedy", "Ordenat", "Random")
)

boxplot(NG,NO,NR,
        main = "Comparacion de Beneficios",
        at = c(1,2,3),
        names = c("Greedy", "Ordenat", "Random")
)

