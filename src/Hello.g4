// Define a grammar called Hello
grammar Hello;
/* Definim regulile gramaticii (cu litere mici) */
main  : list ;  // Inceputul gramaticii, S -> list
list: '()' | '(' sequence ')' | cons | concat;
sequence : element | element ' ' sequence;
element
    :   integer
    |   cons
    |   concat
    |   list
    ;
cons : '(: ' integer ' ' list ')';
concat : '(++ ' left=list ' ' right=list ')';
integer : INTEGER;

/* Definim Tokenii ce pot aparea in timpul parsarii */
INTEGER : [0-9]+ ;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines


/* Alte lucruri ce pot fi folositoare, sugerez sa le incercati */
/*
    O varianta de a declara reguli este prin adnotarea lor
    In acest moment nu vom mai avea un nod element in arbore.
    Prin aceasta metoda vom avea 4 tipuri diferite de noduri

    Poate fi util cand parsam reguli de genul AExp + AExp (in cazul in care ANTLR da erori)
    Mai multe detalii despre astfel de erori in anul 4 -> CPL
    Cele 4 'ramuri' vor fi tratate ca reguli

    element
    :   integer     #int
    |   cons        #colon
    |   concat      #plusplus
    |   list;       #l
*/

/*
    concat : '(++ ' list ' ' list ')';
    Cele 2 'list' vor fi accesate in cadrul contextului prin ctx.list(0) si ctx,list(1)

        Daca dorim sa redenumim elementele, pentru a nu ne incurca cu indicii,
    putem folosi etichete pentru acestea:

    concat : '(++ ' left=list ' ' right=list ')';
    Acum accesarea lor se va face prin ctx.left si ctx.right
    Aceasta functionalitate este mai mult de design, daca v-ar simplifica munca.
*/
