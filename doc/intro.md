# Introduction to funcional-template

[*] filtrar los facts y rules (fact-rule-test)
 lein test fact-rule-test:
    tratando de separar los parametros de entrada para el reeemplazo

Test
[*] incomplete-database-test
[ ] number-database-test
[ ] parent-database-test

[*] database-test
[x] fact-rule-test -> check-fact-rule-integrity me quede ahi !!
hay que ver por que no valida bien la DB
[*] basic-test
[*] reader-test

rule1(X,Y):-f1(Y,X),f2(X,Y).
## a map
{"rule1":
    {cantParam: 2,
     facts:("f1(2,1)", "f2(1,2)")
    }
"rule2":
    {.
    .
    }
}

- en la evaluacion de la rule:
    * buscas en el mapa de rules
    * recorres los parametros y reemplazas en la lista de facts
    * despues hay que evaluar la lista de facts (con un every?)
