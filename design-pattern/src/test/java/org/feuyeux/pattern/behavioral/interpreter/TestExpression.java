package org.feuyeux.pattern.behavioral.interpreter;

import org.junit.Test;

public class TestExpression {
    @Test
    public void test() {
        Context context = new Context();
        Variable x = new Variable("X");
        Variable y = new Variable("Y");
        Constant constant = new Constant(true);
        context.assign(x, false);
        context.assign(y, true);

        Expression expression = new And(constant, x);
        System.out.println("X = " + x.interpret(context));
        System.out.println("Y = " + y.interpret(context));
        System.out.println(expression.toString() + " = " + expression.interpret(context));
    }
}
