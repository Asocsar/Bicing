G1_D0 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_G_D0_H1.txt", header = TRUE, sep = ",", dec = ".")
O1_D0 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Ord_D0_H1.txt", header = TRUE, sep = ",", dec = ".")
R1_D0 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Rnd_D0_H1.txt", header = TRUE, sep = ",", dec = ".")

G1_D1 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_G_D1_H1.txt", header = TRUE, sep = ",", dec = ".")
O1_D1 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Ord_D1_H1.txt", header = TRUE, sep = ",", dec = ".")
R1_D1 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Rnd_D1_H1.txt", header = TRUE, sep = ",", dec = ".")

G2_D0 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_G_D0_H2.txt", header = TRUE, sep = ",", dec = ".")
O2_D0 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Ord_D0_H2.txt", header = TRUE, sep = ",", dec = ".")
R2_D0 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Rnd_D0_H2.txt", header = TRUE, sep = ",", dec = ".")

G2_D1 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_G_D1_H2.txt", header = TRUE, sep = ",", dec = ".")
O2_D1 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Ord_D1_H2.txt", header = TRUE, sep = ",", dec = ".")
R2_D1 = read.table("/home/asocar/Desktop/Universidad/Bicing/Estadisticas_Rnd_D1_H2.txt", header = TRUE, sep = ",", dec = ".")


BG1_D0 <- G1_D0$BEN
BO1_D0 <- O1_D0$BEN
BR1_D0 <- R1_D0$BEN
BG2_D0 <- G2_D0$BEN
BO2_D0 <- O2_D0$BEN
BR2_D0 <- R2_D0$BEN
BG1_D1 <- G1_D1$BEN
BO1_D1 <- O1_D1$BEN
BR1_D1 <- R1_D1$BEN
BG2_D1 <- G2_D1$BEN
BO2_D1 <- O2_D1$BEN
BR2_D1 <- R2_D1$BEN
TG1_D0 <- G1_D0$TIME
TO1_D0 <- O1_D0$TIME
TR1_D0 <- R1_D0$TIME
TG2_D0 <- G2_D0$TIME
TO2_D0 <- O2_D0$TIME
TR2_D0 <- R2_D0$TIME
TG1_D1 <- G1_D1$TIME
TO1_D1 <- O1_D1$TIME
TR1_D1 <- R1_D1$TIME
TG2_D1 <- G2_D1$TIME
TO2_D1 <- O2_D1$TIME
TR2_D1 <- R2_D1$TIME
NG1_D0 <- G1_D0$NODE
NO1_D0 <- O1_D0$NODE
NR1_D0 <- R1_D0$NODE
NG2_D0 <- G2_D0$NODE
NO2_D0 <- O2_D0$NODE
NR2_D0 <- R2_D0$NODE
NG1_D1 <- G1_D1$NODE
NO1_D1 <- O1_D1$NODE
NR1_D1 <- R1_D1$NODE
NG2_D1 <- G2_D1$NODE
NO2_D1 <- O2_D1$NODE
NR2_D1 <- R2_D1$NODE

boxplot(BG1_D0,BO1_D0,BR1_D0,
        main = "Comparacion de Beneficios Demanda 0 Heuristic 0",
        at = c(1,2,3),
        names = c("Greedy", "Ordenat", "Random")
)

boxplot(TG1_D0,TO1_D0,TR1_D0,
        main = "Comparacion de Tiempos Demanda 0 Heuristic 0",
        at = c(1,2,3),
        names = c("Greedy", "Ordenat", "Random")
)

boxplot(NG1_D0,NO1_D0,NR1_D0,
        main = "Comparacion de Nodos abiertos Demanda 0 Heuristic 0",
        at = c(1,2,3),
        names = c("Greedy", "Ordenat", "Random")
)

boxplot(BG1_D1,BO1_D1,BR1_D1,
main = "Comparacion de Beneficios Demanda 1 Heuristic 0",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(TG1_D1,TO1_D1,TR1_D1,
main = "Comparacion de Tiempos Demanda 1 Heuristic 0",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(NG1_D1,NO1_D1,NR1_D1,
main = "Comparacion de Nodos abiertos Demanda 1 Heuristic 0",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(BG2_D0,BO2_D0,BR2_D0,
main = "Comparacion de Beneficios Demanda 0 Heuristic 2",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(TG2_D0,TO2_D0,TR2_D0,
main = "Comparacion de Tiempos Demanda 0 Heuristic 2",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(NG2_D0,NO2_D0,NR2_D0,
main = "Comparacion de Nodos abiertos Demanda 0 Heuristic 2",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(BG2_D1,BO2_D1,BR2_D1,
main = "Comparacion de Beneficios Demanda 1 Heuristic 2",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(TG2_D1,TO2_D1,TR2_D1,
main = "Comparacion de Tiempos Demanda 1 Heuristic 2",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(NG2_D1,NO2_D1,NR2_D1,
main = "Comparacion de Nodos abiertos Demanda 1 Heuristic 2",
at = c(1,2,3),
names = c("Greedy", "Ordenat", "Random")
)

boxplot(BO1_D0, BO2_D0, BG1_D0, BG2_D0, BR1_D0, BR2_D0,
main = "Comparacion de Beneficios entre Heuristic 1 y 2 Demand = 0",
at = c(1,2,3,4,5,6),
names = c("Order_H1_D0", "Order_H2_D0", "Greedy_H1_D0", "Greedy_H2_D0", "Random_H1_D0", "Random_H2_D0")
)

boxplot(BO1_D1, BO2_D1, BG1_D1, BG2_D1, BR1_D1, BR2_D1,
main = "Comparacion de Beneficios entre Heuristic 1 y 2 Demand = 1",
at = c(1,2,3,4,5,6),
names = c("Order_H1_D1", "Order_H2_D1", "Greedy_H1_D1", "Greedy_H2_D1", "Random_H1_D1", "Random_H2_D1")
)

