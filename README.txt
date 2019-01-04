  _____                                 _    _   _ _____ _     ____  
 │_   _│__ _ __ ___   __ _             / \  │ \ │ │_   _│ │   │  _ \ 
   │ │/ _ \ '_ ` _ \ / _` │  _____    / _ \ │  \│ │ │ │ │ │   │ │_) │
   │ │  __/ │ │ │ │ │ (_│ │ │_____│  / ___ \│ │\  │ │ │ │ │___│  _ < 
   │_│\___│_│ │_│ │_│\__,_│         /_/   \_\_│ \_│ │_│ │_____│_│ \_\
                                                                     

    Din nou am plecat de la exemplul de pe forum și am găsit o soluție foarte
similară pentru parsare. Am transcris gramatica din enunț în sintaxă ANTLR -
cu litere mici regulile, cu prima literă mare tokenii relevanți - folosind
etichete pentru a simplifica codul. Am folosit o structură mai simplă decât
la JFLEX pentru că nu era nevoie de interpretare.

    Am creat câte un visitor pentru fiecare nod, care vizitează recursiv
copiii acestuia, pornind de la main (prog).

    Deoarece ANTLR interpretează, by default, expresiile recursive ca fiind
left-associative, arborele creat în urma parsării unei expresii de tip
<Stmt> = <Stmt> <Stmt> (a = 1; b = 2; c = 3) arăta așa:

                                      stmt
                          ┌────────────┴─────────────┐
                          │                          │
                         stmt                       stmt
                  ┌────────┴─────────┐                │
                  │                  │           assignment(a, 1)
                stmt               stmt
                  │                  │
          assignment(c, 3)   assignment(b, 2)

    Pentru a obține arborele corect, am folosit opțiunea <assoc=right> și am
obținut:


                                  stmt
                      ┌────────────┴─────────────┐
                      │                          │
                     stmt                       stmt
                      │                 ┌────────┴─────────┐ 
                assignment(c, 3)       stmt               stmt
                                        │                  │
                                assignment(b, 2)   assignment(a, 1)

============================ ALEXANDRU Ioana | 334CB ===========================