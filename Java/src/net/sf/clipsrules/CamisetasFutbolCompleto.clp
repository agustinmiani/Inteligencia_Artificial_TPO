(deftemplate equipo 
                  (slot id-equipo) 
                  (slot nombre) 
                  (slot pais) 
                  (slot tipo-equipo (type SYMBOL) (allowed-values Nacional Seleccion NoSe) (default NoSe)) 
                  (slot categoria) 
                  (multislot colores-camisetas))



(deftemplate camiseta 
                   (slot id-equipo) 
                   (slot patron (type SYMBOL) (allowed-values BarraHorizontal BarraVertical BarraDiagonal Liso NoSe) (default NoSe)) 
                   (slot cantidadBarras))
                   
(deftemplate resultado_final
			(slot nombre)
			(multislot colores-camisetas)
			(slot cantidadBarras)
			(slot patron (type SYMBOL) (allowed-values BarraHorizontal BarraVertical BarraDiagonal Liso NoSe) (default NoSe)))
                   


(deffacts equipo1
          (equipo (id-equipo 1) (nombre Racing) (pais Argentina) (tipo-equipo Nacional) (categoria PrimeraA) (colores-camisetas Celeste Blanco))
          (camiseta (id-equipo 1) (patron BarraVertical) (cantidadBarras 5)))

(deffacts equipo2
          (equipo (id-equipo 2) (nombre Independiente) (pais Argentina) (tipo-equipo Nacional) (categoria PrimeraA) (colores-camisetas Rojo))
          (camiseta (id-equipo 2) (patron Liso) (cantidadBarras 0)))

(deffacts equipo3
          (equipo (id-equipo 3) (nombre Argentina) (pais Argentina) (tipo-equipo Seleccion) (categoria SeleccionadoDelPais) (colores-camisetas Celeste Blanco))
          (camiseta (id-equipo 3) (patron BarraVertical) (cantidadBarras 5)))

(deffacts equipo4
          (equipo (id-equipo 4) (nombre ArgentinosJunior) (pais Argentina) (tipo-equipo Nacional) (categoria PrimeraB) (colores-camisetas Rojo Blanco))
          (camiseta (id-equipo 4) (patron BarraDiagonal) (cantidadBarras 1)))

(deffacts equipo5
          (equipo (id-equipo 5) (nombre AtleticoRafaela) (pais Argentina) (tipo-equipo Nacional) (categoria PrimeraA) (colores-camisetas Celeste Blanco))
          (camiseta (id-equipo 5) (patron BarraVertical) (cantidadBarras 5)))

(deffacts equipo6
          (equipo (id-equipo 6) (nombre ManchesterCity) (pais Inglaterra) (tipo-equipo Nacional) 
(categoria PremierLeague) (colores-camisetas Celeste))
          (camiseta (id-equipo 6) (patron Liso) (cantidadBarras 0)))

(deffacts equipo7
          (equipo (id-equipo 7) (nombre AstonVilla) (pais Inglaterra) (tipo-equipo Nacional) 
 (categoria LeagueChampionship) (colores-camisetas Bordo Celeste))
          (camiseta (id-equipo 7) (patron Liso) (cantidadBarras 0)))

(deffacts equipo8
          (equipo (id-equipo 8) (nombre Inglaterra) (pais Inglaterra) (tipo-equipo Seleccion) (categoria SeleccionadoDelPais) (colores-camisetas Blanco))
          (camiseta (id-equipo 8) (patron Liso) (cantidadBarras 0)))

(deffacts equipo9
          (equipo (id-equipo 9) (nombre AthleticClub) (pais Espania) (tipo-equipo Nacional) 
(categoria LigaSantander) (colores-camisetas Rojo Blanco))
          (camiseta (id-equipo 9) (patron BarraVertical) (cantidadBarras 10)))

(deffacts equipo10
          (equipo (id-equipo 10) (nombre RealZaragoza) (pais Espania) (tipo-equipo Nacional) 
 (categoria Liga2) (colores-camisetas Blanco Azul))
          (camiseta (id-equipo 10) (patron Liso) (cantidadBarras 0)))


(deffacts equipo11
          (equipo (id-equipo 11) (nombre Espania) (pais Espania) (tipo-equipo Seleccion) 
(categoria SeleccionadoDelPais) (colores-camisetas Rojo))
          (camiseta (id-equipo 11) (patron Liso) (cantidadBarras 0)))

(deffacts equipo12
          (equipo (id-equipo 12) (nombre Roma) (pais Italia) (tipo-equipo Nacional) 
(categoria Calcio) (colores-camisetas Bordo))
          (camiseta (id-equipo 12) (patron Liso) (cantidadBarras 0)))

(deffacts equipo13
          (equipo (id-equipo 13) (nombre HellasVerona) (pais Italia) (tipo-equipo Nacional) 
 (categoria SerieB) (colores-camisetas Amarrillo Azul))
          (camiseta (id-equipo 13) (patron BarraHorizontal) (cantidadBarras 7)))

(deffacts equipo14
          (equipo (id-equipo 14) (nombre Italia) (pais Italia) (tipo-equipo Seleccion) 
(categoria SeleccionadoDelPais) (colores-camisetas Azul))
          (camiseta (id-equipo 14) (patron Liso) (cantidadBarras 0)))
          
          



(defrule buscar_equipos_del_Pais (patrones ?pat) (colores $?col) (categoria ?cat) (pais ?p) (cantidadBarras ?cant)
(equipo (id-equipo ?id) (nombre ?nom) (categoria ?cat) (pais ?p) (colores-camisetas $?col)) 
(camiseta (id-equipo ?id) (patron ?pat) (cantidadBarras ?cant))
=> 
(assert (resultado_final (nombre ?nom) (colores-camisetas ?col) (patron ?pat) (cantidadBarras ?cant))))



(defrule equipos_mismos_colores (patrones ?pat) (colores $?col)
(equipo (id-equipo ?id) (nombre ?nom) (categoria ?cat) (pais ?p) (colores-camisetas $?col)) 
(camiseta (id-equipo ?id) (patron ?pat) (cantidadBarras NoSe))
=>
(assert (resultado_final (nombre ?nom) (colores-camisetas ?col) (patron ?pat) (cantidadBarras ?cant))))


(defrule buscar_unico_equipo (patrones ?pat) (colores $?col) (cantidadBarras ?cant) (categoria ?cat) (pais ?p) 
(equipo (id-equipo ?id) (nombre ?nom) (categoria ?cat) (pais ?p) (colores-camisetas $?col)) 
(camiseta (id-equipo ?id) (patron ?pat) (cantidadBarras ?cant))
=> 
(assert (resultado_final (nombre ?nom) (colores-camisetas ?col) (patron ?pat) (cantidadBarras ?cant))))



                   
                   
