(deftemplate Requisitos
                       (slot duracion (type SYMBOL) (allowed-symbols Dia Semana Mes Anio))
                       (slot riesgo (type SYMBOL)(allowed-symbols Minimo Bajo Medio Alto))
                       (slot ganancia (type NUMBER))
                       )



(deftemplate Inversion
                  (slot identificacion (type STRING))
                  (slot nombre (type STRING))
                  (slot asesor (type STRING))
                  (slot porcentaje (type FLOAT))
              	(slot inversionSugerida (type SYMBOL)(allowed-symbols Plazos_Fijos Plazos_Fijos_Indexados_Por_CER
                                                                        Bonos_Estatales Bonos_Corporativos_En_Dolares
                                                                        Lebacs Fondos_De_Inversion_Tasa_Fija Lettes Fondos_De_Inversion_Tasa_Variable
                                                                        Cauciones_En_Dolares Cauciones_En_Pesos Commodities Indices Acciones ADR Futuros Opciones
                                                         ))
)

(deftemplate Regla_Ejecutada
                  (slot id_regla (type STRING))
                  (slot descripcion (type STRING)))
)



(defrule Regla_RMIN
           	(Requisitos(riesgo Minimo))

     	=>
     	(assert (Inversion (identificacion "Regla_RMIN") (inversionSugerida Plazos_Fijos) (porcentaje 0.7)))
     	(assert (Inversion (identificacion "Regla_RMIN") (inversionSugerida Plazos_Fijos_Indexados_Por_CER) (porcentaje 0.3)))
     	(assert (Regla_Ejecutada (id_regla "Regla_RMIN") (descripcion "Condiciones: Riesgo -> Minimo")))
      	(printout t "La Inversion sugerida es Plazos Fijos" crlf)
      	(printout t "La Inversion sugerida es Plazos Fijos Indexados por CER" crlf)
              	)


(defrule Regla_RBAJ
             	(Requisitos(riesgo Bajo))
     	=>
     	(assert (Inversion (identificacion "Regla_RBAJ") (inversionSugerida Lebacs) (porcentaje 0.6)))
     	(assert (Inversion (identificacion "Regla_RBAJ") (inversionSugerida Bonos_Estatales) (porcentaje 0.2)))
     	(assert (Inversion (identificacion "Regla_RBAJ") (inversionSugerida Bonos_Corporativos_En_Dolares) (porcentaje 0.2)))
     	(assert (Regla_Ejecutada (id_regla "Regla_RBAJ") (descripcion "Condiciones: Riesgo -> Bajo")))
     	(printout t "La Inversion sugerida es Lebacs" crlf)
     	(printout t "La Inversion sugerida es Bonos Estatales" crlf)
     	(printout t "La Inversion sugerida es Bonos Corporativos en dolares" crlf))


(defrule Regla_RMED
             	(Requisitos(riesgo Medio))
     	=>
     	(assert (Inversion (identificacion "Regla_RMED") (inversionSugerida Fondos_De_Inversion_Tasa_Fija) (porcentaje 0.3)))
     	(assert (Inversion (identificacion "Regla_RMED") (inversionSugerida Fondos_De_Inversion_Tasa_Variable) (porcentaje 0.2)))
     	(assert (Inversion (identificacion "Regla_RMED") (inversionSugerida Lettes) (porcentaje 0.15)))
     	(assert (Inversion (identificacion "Regla_RMED") (inversionSugerida Cauciones_En_Dolares) (porcentaje 0.0875)))
     	(assert (Inversion (identificacion "Regla_RMED") (inversionSugerida Cauciones_En_Pesos) (porcentaje 0.0875)))
     	(assert (Inversion (identificacion "Regla_RMED") (inversionSugerida Commodities) (porcentaje 0.0875)))
     	(assert (Inversion (identificacion "Regla_RMED") (inversionSugerida Indices) (porcentaje 0.0875)))
     	(assert (Regla_Ejecutada (id_regla "Regla_RMED") (descripcion "Condiciones: Riesgo -> Medio")))
     	(printout t "La Inversion sugerida es Fondos de inversion tasa fija" crlf)
     	(printout t "La Inversion sugerida es Lettes" crlf)
     	(printout t "La Inversion sugerida es Fondos de inversion tasa variable" crlf)
     	(printout t "La Inversion sugerida es Cauciones en dolares o pesos" crlf)
     	(printout t "La Inversion sugerida es Commodities" crlf)
     	(printout t "La Inversion sugerida es Indices" crlf))


(defrule Regla_RALT
              	(Requisitos(riesgo Alto))
     	=>
     	(assert (Inversion (identificacion "Regla_RALT") (inversionSugerida Acciones) (porcentaje 0.4)))
     	(assert (Inversion (identificacion "Regla_RALT") (inversionSugerida ADR) (porcentaje 0.25)))
     	(assert (Inversion (identificacion "Regla_RALT") (inversionSugerida Opciones) (porcentaje 0.175)))
     	(assert (Inversion (identificacion "Regla_RALT") (inversionSugerida Futuros) (porcentaje 0.175)))
     	(assert (Regla_Ejecutada (id_regla "Regla_RALT") (descripcion "Condiciones: Riesgo -> Alto")))
    	(printout t "La Inversion sugerida es Acciones" crlf)
    	(printout t "La Inversion sugerida es ADR" crlf)
    	(printout t "La Inversion sugerida es Opciones" crlf)
    	(printout t "La Inversion sugerida es Futuros" crlf))

(defrule Regla_YYAMIN10
              	(Requisitos (duracion Anio)
              	(riesgo Minimo)
              	(ganancia ?a))
                  (test(and(> ?a 10) (< ?a 20)))
    	=>
    	(assert (Inversion (identificacion "Regla_YYAMIN10") (inversionSugerida Plazos_Fijos) (porcentaje 0.7)))
    	(assert (Inversion (identificacion "Regla_YYAMIN10") (inversionSugerida Plazos_Fijos_Indexados_Por_CER) (porcentaje 0.3)))
    	(assert (Regla_Ejecutada (id_regla "Regla_YYAMIN10") (descripcion "Condiciones: Riesgo -> Minimo, Duracion -> Anio, 10%<Ganancia<20%")))
     	(printout t "La Inversion Sugerida es Plazos Fijos" crlf)
     	(printout t "La Inversion Sugerida es Plazos Fijos Indexados por CER" crlf)
     	)


(defrule Regla_YYMED20
       	 (Requisitos (duracion Anio)
        	(riesgo Medio)
        	(ganancia ?a))
        	(test (and (> ?a 20) (< ?a 30)))
     	=>
     	(assert (Inversion (identificacion "Regla_YYMED20") (inversionSugerida Fondos_De_Inversion_Tasa_Fija) (porcentaje 0.3)))
     	(assert (Inversion (identificacion "Regla_YYMED20") (inversionSugerida Fondos_De_Inversion_Tasa_Variable) (porcentaje 0.2)))
     	(assert (Inversion (identificacion "Regla_YYMED20") (inversionSugerida Lettes) (porcentaje 0.15)))
     	(assert (Inversion (identificacion "Regla_YYMED20") (inversionSugerida Cauciones_En_Dolares) (porcentaje 0.1167)))
     	(assert (Inversion (identificacion "Regla_YYMED20") (inversionSugerida Cauciones_En_Pesos) (porcentaje 0.1167)))
     	(assert (Inversion (identificacion "Regla_YYMED20") (inversionSugerida Indices) (porcentaje 0.1167)))
     	(assert (Regla_Ejecutada (id_regla "Regla_YYMED20") (descripcion "Condiciones: Riesgo -> Medio, Duracion -> Anio, 20%<Ganancia<30%")))
     	(printout t "La Inversion Sugerida es Fondos de inversion tasa fija" crlf)
     	(printout t "La Inversion Sugerida es Lettes" crlf)
     	(printout t "La Inversion Sugerida es Fondos de inversion tasa variable" crlf)
     	(printout t "La Inversion Sugerida es Cauciones en dolares" crlf)
     	(printout t "La Inversion Sugerida es Cauciones en pesos" crlf)
     	(printout t "La Inversion Sugerida es Indices" crlf)
     	)


(defrule Regla_YYALT20
        	 (Requisitos (duracion Anio)
        	(riesgo Alto)
        	(ganancia ?a))
            (test (and (> ?a 30) (< ?a 50)))
    	=>
    	(assert (Inversion (identificacion "Regla_YYALT20") (inversionSugerida Acciones) (porcentaje 1.0)))
    	(assert (Regla_Ejecutada (id_regla "Regla_YYALT20") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Anio, 30%<Ganancia<50%")))
     	(printout t "La Inversion sugerida es Acciones" crlf)
     	)


(defrule Regla_YYALT50
        	 (Requisitos (duracion Anio)
        	(riesgo Alto)
        	(ganancia ?a))
        	(test (> ?a 50))
     	=>
 		(assert (Inversion (identificacion "Regla_YYALT50") (inversionSugerida Futuros) (porcentaje 0.6)))
 		(assert (Inversion (identificacion "Regla_YYALT50") (inversionSugerida Opciones) (porcentaje 0.4)))
 		(assert (Regla_Ejecutada (id_regla "Regla_YYALT50") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Anio, Ganancia>50%")))
    	(printout t "La Inversion Sugerida es Futuros y Opciones" crlf)
     	)


(defrule Regla_MMMINO
           	 (Requisitos (duracion Mes)
           	(riesgo Minimo)
           	(ganancia ?a))
           	(test (and (> ?a 0) (< ?a 5)))
    	=>
    	(assert (Inversion (identificacion "Regla_MMMINO") (inversionSugerida Plazos_Fijos) (porcentaje 0.7)))
    	(assert (Inversion (identificacion "Regla_MMMINO") (inversionSugerida Plazos_Fijos_Indexados_Por_CER) (porcentaje 0.3)))
    	(assert (Regla_Ejecutada (id_regla "Regla_MMMINO") (descripcion "Condiciones: Riesgo -> Minimo, Duracion -> Mes, 0%<Ganancia<5%")))
     	(printout t "La inversion sugerida es Plazos Fijos" crlf)
     	(printout t "La inversion sugerida es Plazos Fijos Indexados por CER" crlf)
    	)

 (defrule Regla_MMBAJO
           	 (Requisitos (duracion Mes)
           	(riesgo Bajo)
           	(ganancia ?a))
           	(test (and (> ?a 0) (< ?a 5)))
     	=>
     	(assert (Inversion (identificacion "Regla_MMBAJO") (inversionSugerida Bonos_Estatales) (porcentaje 0.2)))
     	(assert (Inversion (identificacion "Regla_MMBAJO") (inversionSugerida Bonos_Corporativos_En_Dolares) (porcentaje 0.2)))
     	(assert (Inversion (identificacion "Regla_MMBAJO") (inversionSugerida Lebacs) (porcentaje 0.6)))
     	(assert (Regla_Ejecutada (id_regla "Regla_MMBAJO") (descripcion "Condiciones: Riesgo -> Bajo, Duracion -> Mes, 0%<Ganancia<5%")))
      	(printout t "La inversion sugerida es Bonos Estatales" crlf)
      	(printout t "La inversion sugerida es Bonos Corporativos en dolares" crlf)
      	(printout t "La inversion sugerida es Lebacs" crlf)
      	)

 (defrule Regla_MMMEDO
        	 (Requisitos (duracion Mes)
        	(riesgo Medio)
        	(ganancia ?a))
        	(test (and(> ?a 0) (< ?a 5)))
  	=>
  		(assert (Inversion (identificacion "Regla_MMMEDO") (inversionSugerida Fondos_De_Inversion_Tasa_Fija) (porcentaje 0.3)))
  		(assert (Inversion (identificacion "Regla_MMMEDO") (inversionSugerida Fondos_De_Inversion_Tasa_Variable) (porcentaje 0.2)))
  		(assert (Inversion (identificacion "Regla_MMMEDO") (inversionSugerida Cauciones_En_Dolares) (porcentaje 0.3)))
  		(assert (Inversion (identificacion "Regla_MMMEDO") (inversionSugerida Cauciones_En_Pesos) (porcentaje 0.1)))
  		(assert (Inversion (identificacion "Regla_MMMEDO") (inversionSugerida Commodities) (porcentaje 0.1)))
  		(assert (Regla_Ejecutada (id_regla "Regla_MMMEDO") (descripcion "Condiciones: Riesgo -> Medio, Duracion -> Mes, 0%<Ganancia<5%")))
     	(printout t "La Inversion Sugerida es Fondos de Inversion Tasa Fija" crlf)
     	(printout t "La Inversion Sugerida es Fondos de Inversion Tasa Variable" crlf)
     	(printout t "La Inversion Sugerida es Cauciones en dolares" crlf)
     	(printout t "La Inversion Sugerida es Cauciones en pesos" crlf)
     	(printout t "La Inversion Sugerida es Commodities" crlf)
     	)


(defrule Regla_MMMED5
           	 (Requisitos (duracion Mes)
           	(riesgo Medio)
           	(ganancia ?a))
           	(test(and (> ?a 5) (< ?a 10)))
   	=>
   		(assert (Inversion (identificacion "Regla_MMMED5") (inversionSugerida Indices) (porcentaje 1.0)))
   		(assert (Regla_Ejecutada (id_regla "Regla_MMMED5") (descripcion "Condiciones: Riesgo -> Medio, Duracion -> Mes, 5%<Ganancia<10%")))
     	(printout t "La Inversion Sugerida es Indices" crlf)
    	)


(defrule Regla_MMALT5
        	 (Requisitos (duracion Mes)
        	(riesgo Alto)
        	(ganancia ?a))
        	(test (and (> ?a 5) (< ?a 10)))
     	=>
     	(assert (Inversion (identificacion "Regla_MMALT5") (inversionSugerida ADR) (porcentaje 0.6)))
     	(assert (Inversion (identificacion "Regla_MMALT5") (inversionSugerida Futuros) (porcentaje 0.4)))
     	(assert (Regla_Ejecutada (id_regla "Regla_MMALT5") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Mes, 5%<Ganancia<10%")))
        (printout t "La Inversion Sugerida es ADR" crlf)
     	(printout t "La Inversion Sugerida es Futuros" crlf)
     	)

(defrule Regla_MMALT10
           	 (Requisitos (duracion Mes)
           	(riesgo Alto)
           	(ganancia ?a))
               (test (and (> ?a 10) (< ?a 20)))
     	=>
     	(assert (Inversion (identificacion "Regla_MMALT10") (inversionSugerida Acciones) (porcentaje 1.0)))
     	(assert (Regla_Ejecutada (id_regla "Regla_MMALT10") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Mes, 10%<Ganancia<20%")))
     	(printout t "La inversion sugerida es Acciones" crlf)
     	)


(defrule Regla_MMALT30
           	 (Requisitos (duracion Mes)
           	(riesgo Alto)
           	(ganancia ?a))
               (test (and (> ?a 30) (< ?a 50)))
    	=>
    	(assert (Inversion (identificacion "Regla_MMALT30") (inversionSugerida Opciones) (porcentaje 1.0)))
    	(assert (Regla_Ejecutada (id_regla "Regla_MMALT30") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Mes, 30%<Ganancia<50%")))
       	(printout t "La Inversion sugerida es Opciones" crlf)
     	)


(defrule Regla_DDMEDO
            	 (Requisitos (ganancia ?a)
              	(duracion ?b)
              	(riesgo Medio))
                (test (and (or(eq ?b Dia) (eq ?b Semana))
                        (and (> ?a 0) (< ?a 5))
                       	))
    	=>
    	(assert (Inversion (identificacion "Regla_DDMEDO") (inversionSugerida Indices) (porcentaje 1.0)))
    	(assert (Regla_Ejecutada (id_regla "Regla_DDMEDO") (descripcion "Condiciones: Riesgo -> Medio, Duracion -> Dia o Semana, 0%<Ganancia<5%")))
       	(printout t "La inversion sugerida es Indices" crlf)
     	)


(defrule Regla_DDMEDO2
            	(Requisitos (ganancia ?a)
          	 (duracion ?b)
     	       (riesgo Alto))
            	(test (and (or(eq ?b Dia) (eq ?b Semana))
                        (and (> ?a 0) (< ?a 5))
                        ))
             	(riesgo Alto)
    	=>

    	(assert (Inversion (identificacion "Regla_DDMEDO2") (inversionSugerida Acciones) (porcentaje 0.6)))
    	(assert (Inversion (identificacion "Regla_DDMEDO2") (inversionSugerida ADR) (porcentaje 0.4)))
    	(assert (Regla_Ejecutada (id_regla "Regla_DDMEDO2") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Dia o Semana, 0%<Ganancia<5%")))
       	(printout t "La inversion sugerida es Acciones" crlf)
       	(printout t "La inversion sugerido es ADR" crlf)
     	)


(defrule Regla_DDALT5
              	(Requisitos (duracion Dia)
              	(riesgo Alto)
              	(ganancia ?a))
              	(test (and (> ?a 5) (< ?a 10)))
   	 =>
   	 		(assert (Inversion (identificacion "Regla_DDALT5") (inversionSugerida Opciones) (porcentaje 1.0)))
   	 		(assert (Regla_Ejecutada (id_regla "Regla_DDALT5") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Dia, 5%<Ganancia<10%")))
        	(printout t "La inversion sugerida es Opciones 32132" crlf)
     	)


(defrule Regla_WWALT10
           	(Requisitos (duracion Semana)
           	(riesgo Alto)
          	(ganancia ?a))
           	(test (and (> ?a 10) (< ?a 20)))
    	=>
    		(assert (Inversion (identificacion "Regla_WWALT10") (inversionSugerida Opciones) (porcentaje 1.0)))
    		(assert (Regla_Ejecutada (id_regla "Regla_WWALT10") (descripcion "Condiciones: Riesgo -> Alto, Duracion -> Semana, 10%<Ganancia<20%")))
        	(printout t "La Inversion Sugerida es Opciones" crlf))
