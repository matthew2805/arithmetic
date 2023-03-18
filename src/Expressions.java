public class Expressions {

    public static Variable var(String name, int value) {
        return new Variable(name, value);
    }

    public static Expression val(int value) {
        String exp;
        if (value < 0) exp = String.format("(%d)", value);
        else exp = String.format("%d", value);
        return new Expression() {
            @Override
            public int evaluate() {
                return value;
            }

            @Override
            public String toExpressionString() {
                return exp;
            }
        };
    }

    public static Expression sum(Expression... members) {
        return new Expression() {
            @Override
            public int evaluate() {
                int sum = 0;
                for (Expression n : members) sum += n.evaluate();
                return sum;
            }

            @Override
            public String toExpressionString() {
                String exp = "";
                for (Expression n : members)
                    exp = exp + " + " + n.toExpressionString();
                return String.format("(%s)", exp.substring(3));
            }
        };
    }

    public static Expression product(Expression... members) {
        return new Expression() {
            @Override
            public int evaluate() {
                int sum = 1;
                for (Expression n : members) sum *= n.evaluate();
                return sum;
            }

            @Override
            public String toExpressionString() {
                String exp = "";
                for (Expression n : members)
                    exp = exp + " * " + n.toExpressionString();
                return String.format("(%s)", exp.substring(3));
            }
        };
    }

    public static Expression difference(Expression minuend, Expression subtrahend) {
        return new Expression() {
            @Override
            public int evaluate() {
                return minuend.evaluate() - subtrahend.evaluate();
            }

            @Override
            public String toExpressionString() {
                return String.format("(%s - %s)", minuend.toExpressionString(), subtrahend.toExpressionString());
            }
        };
    }

    public static Expression fraction(Expression dividend, Expression divisor) {
        return new Expression() {
            @Override
            public int evaluate() {
                return dividend.evaluate() / divisor.evaluate();
            }

            @Override
            public String toExpressionString() {
                return String.format("(%s / %s)", dividend.toExpressionString(), divisor.toExpressionString());
            }
        };
    }

}
