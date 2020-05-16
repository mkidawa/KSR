package fuzzyruns;

import dao.RunDao;
import fuzzylogic.LinguisticVariable;
import fuzzylogic.TrapezoidalFunction;
import fuzzylogic.TriangularFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PredefinedSummarizer {
    private List<LinguisticVariable<RunDao>> summarizers;

    public List<LinguisticVariable<RunDao>> getSummarizers() {
        return summarizers;
    }

    private static Function<RunDao, Double> ageFunc = run -> (double) run.getHorseAge();
    private static Function<RunDao, Double> declaredWeightFunc = RunDao::getDeclaredWeight;

    public PredefinedSummarizer() {
        summarizers = new ArrayList<>();
        summarizers.add(ageYoung);
        summarizers.add(ageMiddleAged);
        summarizers.add(ageOld);
    }

    public static LinguisticVariable<RunDao> ageYoung = new LinguisticVariable<>(
            "horse age",
            "young",
            new TrapezoidalFunction(0.0, 4.5, 0.0, 3.5),
            ageFunc
    );
    public static LinguisticVariable<RunDao> ageMiddleAged = new LinguisticVariable<>(
            "horse age",
            "middle-aged",
            new TriangularFunction(3.5, 6.5, 5.0),
            ageFunc
    );
    public static LinguisticVariable<RunDao> ageOld = new LinguisticVariable<>(
            "horse age",
            "old",
            new TrapezoidalFunction(5.5, 7.0, 6.0, 7.0),
            ageFunc
    );

    public static LinguisticVariable<RunDao> declaredWeightVeryLight = new LinguisticVariable<>(
            "declared weight",
            "very light",
            new TrapezoidalFunction(693.0, 850.0, 693.0, 800.0),
            declaredWeightFunc
    );
    public static LinguisticVariable<RunDao> declaredWeightLight = new LinguisticVariable<>(
            "declared weight",
            "light",
            new TrapezoidalFunction(800.0, 1000.0, 850.0, 950.0),
            declaredWeightFunc
    );
    public static LinguisticVariable<RunDao> declaredWeightMedium = new LinguisticVariable<>(
            "declared weight",
            "medium",
            new TrapezoidalFunction(950.0, 1150.0, 1025.0, 1075.0),
            declaredWeightFunc
    );
    public static LinguisticVariable<RunDao> declaredWeightHeavy = new LinguisticVariable<>(
            "declared weight",
            "heavy",
            new TrapezoidalFunction(1100.0, 1300.0, 1200.0, 1250.0),
            declaredWeightFunc
    );
    public static LinguisticVariable<RunDao> declaredWeightVeryHeavy = new LinguisticVariable<>(
            "declared weight",
            "very heavy",
            new TrapezoidalFunction(1250.0, 1396.0, 1350.0, 1396.0),
            declaredWeightFunc
    );

}
