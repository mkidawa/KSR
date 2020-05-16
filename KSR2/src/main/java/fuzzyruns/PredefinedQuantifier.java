package fuzzyruns;

import dao.RunDao;
import fuzzylogic.Quantifier;
import fuzzylogic.TrapezoidalFunction;
import fuzzylogic.TriangularFunction;

import java.util.LinkedList;
import java.util.List;

public class PredefinedQuantifier {
    private List<Quantifier<RunDao>> quantifiers;

    public List<Quantifier<RunDao>> getQuantifiers() {
        return quantifiers;
    }

    public PredefinedQuantifier() {
        quantifiers = new LinkedList<>();
        quantifiers.add(almostNone);
        quantifiers.add(some);
        quantifiers.add(aboutHalf);
        quantifiers.add(many);
        quantifiers.add(almostAll);
        quantifiers.add(about40k);
        quantifiers.add(about70k);
    }

    public static Quantifier<RunDao> almostNone = new Quantifier<>(
            "Almost none",
            new TrapezoidalFunction(0.0, 0.16, 0.0, 0.04),
            false
    );

    public static Quantifier<RunDao> some = new Quantifier<>(
            "Some",
            new TrapezoidalFunction(0.12, 0.4, 0.16, 0.32),
            false
    );

    public static Quantifier<RunDao> aboutHalf = new Quantifier<>(
            "About half",
            new TrapezoidalFunction(0.32, 0.68, 0.44, 0.56),
            false
    );

    public static Quantifier<RunDao> many = new Quantifier<>(
            "Many",
            new TrapezoidalFunction(0.6, 0.88, 0.68, 0.84),
            false
    );

    public static Quantifier<RunDao> almostAll = new Quantifier<>(
            "Almost all",
            new TrapezoidalFunction(0.84, 1.0, 0.96, 1.0),
            false
    );

    public static Quantifier<RunDao> about40k = new Quantifier<>(
            "About 40 thousand",
            new TrapezoidalFunction(30000.0, 50000.0, 35000.0, 45000.0),
            true
    );

    public static Quantifier<RunDao> about70k = new Quantifier<>(
            "About 70 thousand",
            new TrapezoidalFunction(60000.0, 80000.0, 65000.0, 75000.0),
            true
    );
}
