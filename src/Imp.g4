grammar Imp;

// <Prog> ::= int <VarList> ; <Stmt>
prog:
    'int' varlist ';\n' stmt
    ;

// <VarList> ::= <Var> | <Var> , <VarList>
varlist:
    Var
    | Var ',' varlist
    ;

/* <Stmt> ::= <Var> = <AExpr> ; |
              <Block> |
              if ( <BExpr> ) <Block> else <Block> |
              while ( <BExpr> ) <Block> |
              <Stmt> <Stmt> */
stmt:
    Var '=' assigned=aexpr ';\n'
    | stmtblock=block
    | 'if (' ifcondition=bexpr ')' ifblock=block 'else' elseblock=block
    | 'while (' whilecondition=bexpr ')' whileblock=block
    | left=stmt right=stmt
    ;

// <Block> ::= {} | { <Stmt> }
block:
    '{}'
    | '{' stmt '}'
    ;

/* <BExpr> ::= <BVal> |
               <BExpr> && <BExpr>  |
               <AExpr> > <AExpr> |
               ! <BExpr> |
               ( <BExpr> ) */
bexpr:
    BVal
    | '(' expr=bexpr ')'
    | '!' notexpr=bexpr
    | gleft=aexpr '>' gright=aexpr
    | aleft=bexpr '&&' aright=bexpr
    ;

/* <AExpr> ::= <Var> |
               <AVal> |
               <AExpr> + <AExpr> |
               <AExpr> / <AExpr> |
               ( <AExpr> ) */
aexpr:
    Var
    | AVal
    | '(' expr=aexpr ')'
    | dleft= aexpr '/' dright=aexpr
    | aleft=aexpr '+' aright=aexpr
    ;

WS : [ \t\r\n]+ -> skip;
AVal : [0-9]+;
BVal : 'true'|'false';
Var : [a-z]+;