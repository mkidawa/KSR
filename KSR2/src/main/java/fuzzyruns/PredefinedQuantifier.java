package fuzzyruns;

import dao.RunDao;
import fuzzylogic.Quantifier;
import fuzzylogic.TrapezoidalFunction;
import fuzzylogic.TriangularFunction;

public class PredefinedQuantifier {
    public PredefinedQuantifier() {}

    public static Quantifier<RunDao> aboutHalf = new Quantifier<>(
            "About half",
            new TriangularFunction(0.4, 0.6, 0.5),
            false
    );

    public static Quantifier<RunDao> almostAll = new Quantifier<>(
            "Almost all",
            new TrapezoidalFunction(0.5, 1.0, 0.9, 1.0),
            false
    );
}
