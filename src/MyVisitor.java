import java.lang.Integer;

class MyVisitor extends HelloBaseVisitor<Integer> {
    private int tabs = 0;

    // Functie rudimentara pentru a printa tab-uri
    private void printTabs() {
        for (int i = 0; i < this.tabs; i++) {
            System.out.print("\t");
        }
    }

    @Override public Integer visitMain(HelloParser.MainContext ctx) {
        this.printTabs();
        System.out.println("<MainNode>");

        this.tabs++;
        visit(ctx.list());
        this.tabs--;

        return 0;
    }

    @Override public Integer visitList(HelloParser.ListContext ctx) {
        // Daca lista este un Cons sau un Concat, trecem direct in acel context
        if (ctx.cons() != null) {
            visit(ctx.cons());
        }
        else if (ctx.concat() != null) {
            visit(ctx.concat());
        } else {
            // Avem o lista simpla, printam normal
            this.printTabs();
            System.out.println("<List> ()");

            this.tabs++;
            if (ctx.sequence() != null) {
                visit(ctx.sequence());
            }
            this.tabs--;
        }

        return 0;
    }

    @Override public Integer visitSequence(HelloParser.SequenceContext ctx) {
        this.printTabs();
        System.out.println("<Seqeunce>");

        this.tabs++;
        visit(ctx.element());
        if (ctx.sequence() != null) {
            visit(ctx.sequence());
        }
        this.tabs--;

        return 0;
    }

    @Override public Integer visitElement(HelloParser.ElementContext ctx) {
        visit(ctx.getChild(0));

        return 0;
    }

    @Override public Integer visitCons(HelloParser.ConsContext ctx) {
        this.printTabs();
        System.out.println("<Cons> :");

        this.tabs++;
        visit(ctx.integer());
        visit(ctx.list());
        this.tabs--;

        return 0;
    }

    @Override public Integer visitConcat(HelloParser.ConcatContext ctx) {
        this.printTabs();
        System.out.println("<Concat> ++");

        this.tabs++;
        visit(ctx.list(0));
        visit(ctx.list(1));
        this.tabs--;

        return 0;
    }

    @Override public Integer visitInteger(HelloParser.IntegerContext ctx) {
        this.printTabs();
        System.out.println("<Integer> " + ctx.getText());

        return 0;
    }
}
