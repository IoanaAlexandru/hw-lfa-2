import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;

class MyVisitor extends ImpBaseVisitor<Integer> {
    private int tabs = 0;
    private FileWriter fileWriter;

    MyVisitor(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    private void printTabs() throws IOException {
        for (int i = 0; i < this.tabs; i++) {
            fileWriter.write("\t");
        }
    }

    @Override
    public Integer visitProg(ImpParser.ProgContext ctx) {
        try {
            fileWriter.write("<MainNode>\n");
            if (ctx.stmt() != null)
                visit(ctx.stmt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer visitStmt(ImpParser.StmtContext ctx) {
        try {
            tabs++;
            printTabs();
            if (ctx.assigned != null) {
                fileWriter.write("<AssignmentNode> =\n");
                tabs++;
                printTabs();
                fileWriter.write("<VariableNode> " + ctx.Var() + "\n");
                tabs--;
                visit(ctx.assigned);
            } else if (ctx.stmtblock != null) {
                visit(ctx.stmtblock);
            } else if (ctx.ifcondition != null) {
                fileWriter.write("<IfNode> if\n");
                tabs++;
                printTabs();
                fileWriter.write("<BracketNode> ()\n");
                visit(ctx.ifcondition);
                tabs--;
                visit(ctx.ifblock);
                visit(ctx.elseblock);
            } else if (ctx.whilecondition != null) {
                fileWriter.write("<WhileNode> while\n");
                tabs++;
                printTabs();
                fileWriter.write("<BracketNode> ()\n");
                visit(ctx.whilecondition);
                tabs--;
                visit(ctx.whileblock);
            } else if (ctx.left != null && ctx.right != null) {
                fileWriter.write("<SequenceNode>\n");
                visit(ctx.left);
                visit(ctx.right);
            }
            tabs--;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer visitBlock(ImpParser.BlockContext ctx) {
        try {
            tabs++;
            printTabs();
            fileWriter.write("<BlockNode> {}\n");
            if (ctx.stmt() != null) {
                visit(ctx.stmt());
            }
            tabs--;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer visitBexpr(ImpParser.BexprContext ctx) {
        try {
            tabs++;
            printTabs();
            if (ctx.BVal() != null) {
                fileWriter.write("<BoolNode> " + ctx.BVal() + "\n");
            } else if (ctx.expr != null) {
                fileWriter.write("<BracketNode> ()\n");
                visit(ctx.expr);
            } else if (ctx.notexpr != null) {
                fileWriter.write("<NotNode> !\n");
                visit(ctx.notexpr);
            } else if (ctx.gleft != null && ctx.gright != null) {
                fileWriter.write("<GreaterNode> >\n");
                visit(ctx.gleft);
                visit(ctx.gright);
            } else if (ctx.aleft != null && ctx.aright != null) {
                fileWriter.write("<AndNode> &&\n");
                visit(ctx.aleft);
                visit(ctx.aright);
            }
            tabs--;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer visitAexpr(ImpParser.AexprContext ctx) {
        try {
            tabs++;
            printTabs();
            if (ctx.Var() != null) {
                fileWriter.write("<VariableNode> " + ctx.Var() + "\n");
            } else if (ctx.AVal() != null) {
                fileWriter.write("<IntNode> " + ctx.AVal() + "\n");
            } else if (ctx.dleft != null && ctx.dright != null) {
                fileWriter.write("<DivNode> /\n");
                visit(ctx.dleft);
                visit(ctx.dright);
            } else if (ctx.aleft != null && ctx.aright != null) {
                fileWriter.write("<PlusNode> +\n");
                visit(ctx.aleft);
                visit(ctx.aright);
            } else if (ctx.expr != null) {
                fileWriter.write("<BracketNode> ()\n");
                visit(ctx.expr);
            }
            tabs--;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
