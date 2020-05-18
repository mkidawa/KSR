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
    private static Function<RunDao, Double> ratingFunc = run -> (double) run.getHorseRating();
    private static Function<RunDao, Double> finishTimeFunc = RunDao::getFinishTime;
    private static Function<RunDao, Double> winOddsFun = RunDao::getWinOdds;
    private static Function<RunDao, Double> lengthsBehindFunc = RunDao::getLengthsBehind;
    private static Function<RunDao, Double> actualWeightFunc = RunDao::getActualWeight;

    public PredefinedSummarizer() {
        summarizers = new ArrayList<>();
        summarizers.add(ageYoung);
        summarizers.add(ageMiddleAged);
        summarizers.add(ageOld);
    }

    public List<String> getAllSummarizerLabels() {
        List<String> labels = new ArrayList<>();
        labels.add(ageYoung.getName() + " " + ageYoung.getLabel());
        labels.add(ageMiddleAged.getName() + " " + ageMiddleAged.getLabel());
        labels.add(ageOld.getName() + " " + ageOld.getLabel());
        labels.add(declaredWeightVeryLight.getName() + " " + declaredWeightVeryLight.getLabel() );
        labels.add(declaredWeightLight.getName() + " " + declaredWeightLight.getLabel());
        labels.add(declaredWeightMedium.getName() + " " + declaredWeightMedium.getLabel());
        labels.add(declaredWeightHeavy.getName() + " " + declaredWeightHeavy.getLabel());
        labels.add(declaredWeightVeryHeavy.getName() + " " + declaredWeightVeryHeavy.getLabel());
        return labels;
    };

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

    public static LinguisticVariable<RunDao> ratingLow = new LinguisticVariable<>(
            "horse rating",
            "low rating",
            new TrapezoidalFunction(10.0, 40.0, 10.0, 30.0),
            ratingFunc
    );
    public static LinguisticVariable<RunDao> ratingMedium = new LinguisticVariable<>(
            "horse rating",
            "medium rating",
            new TrapezoidalFunction(30.0, 90.0, 50.0, 70.0),
            ratingFunc
    );
    public static LinguisticVariable<RunDao> ratingHigh = new LinguisticVariable<>(
            "horse rating",
            "high rating",
            new TrapezoidalFunction(80.0, 138.0, 110.0, 138.0),
            ratingFunc
    );

    public static LinguisticVariable<RunDao> finishTimeVeryFast = new LinguisticVariable<>(
            "finish time",
            "very fast",
            new TrapezoidalFunction(55.16, 70.0, 55.16, 65.0),
            finishTimeFunc
    );
    public static LinguisticVariable<RunDao> finishTimeFast = new LinguisticVariable<>(
            "finish time",
            "fast",
            new TrapezoidalFunction(65.0, 90.0, 70.0, 80.0),
            finishTimeFunc
    );
    public static LinguisticVariable<RunDao> finishTimeMedium = new LinguisticVariable<>(
            "finish time",
            "medium",
            new TrapezoidalFunction(80.0, 120.0, 90.0, 110.0),
            finishTimeFunc
    );
    public static LinguisticVariable<RunDao> finishTimeSlow = new LinguisticVariable<>(
            "finish time",
            "slow",
            new TrapezoidalFunction(110.0, 150.0, 130.0, 140.0),
            finishTimeFunc
    );
    public static LinguisticVariable<RunDao> finishTimeVerySlow = new LinguisticVariable<>(
            "finish time",
            "very slow",
            new TrapezoidalFunction(140.0, 163.58, 150.0, 163.58),
            finishTimeFunc
    );

    public static LinguisticVariable<RunDao> winOddsLow = new LinguisticVariable<>(
            "win odds",
            "low odds",
            new TrapezoidalFunction(1.0, 40.0, 1.0, 20.0),
            winOddsFun
    );
    public static LinguisticVariable<RunDao> winOddsMedium = new LinguisticVariable<>(
            "win odds",
            "medium odds",
            new TrapezoidalFunction(20.0, 80.0, 40.0, 720.0),
            winOddsFun
    );
    public static LinguisticVariable<RunDao> winOddsHigh = new LinguisticVariable<>(
            "win odds",
            "high odds",
            new TrapezoidalFunction(70.0, 99.9, 90.0, 99.9),
            winOddsFun
    );

    public static LinguisticVariable<RunDao> lengthsBehindVeryFew = new LinguisticVariable<>(
            "lengths behind winner",
            "very few",
            new TrapezoidalFunction(-0.5, 3.5, -0.5, 2.0),
            lengthsBehindFunc
    );
    public static LinguisticVariable<RunDao> lengthsBehindFew = new LinguisticVariable<>(
            "lengths behind winner",
            "few",
            new TrapezoidalFunction(1.5, 6.0, 3.0, 5.0),
            lengthsBehindFunc
    );
    public static LinguisticVariable<RunDao> lengthsBehindSome = new LinguisticVariable<>(
            "lengths behind winner",
            "some",
            new TrapezoidalFunction(3.0, 10.0, 4.0, 7.0),
            lengthsBehindFunc
    );
    public static LinguisticVariable<RunDao> lengthsBehindMany = new LinguisticVariable<>(
            "lengths behind winner",
            "many",
            new TrapezoidalFunction(7.0, 50.0, 10.0, 30.0),
            lengthsBehindFunc
    );
    public static LinguisticVariable<RunDao> lengthsBehindVeryMany = new LinguisticVariable<>(
            "lengths behind winner",
            "very many",
            new TrapezoidalFunction(35.0, 206.75, 50.0, 206.75),
            lengthsBehindFunc
    );

    public static LinguisticVariable<RunDao> actualWeightVeryLight = new LinguisticVariable<>(
            "actual weight",
            "very light",
            new TriangularFunction(103.0, 109.0, 103.0),
            actualWeightFunc
    );
    public static LinguisticVariable<RunDao> actualWeightLight = new LinguisticVariable<>(
            "actual weight",
            "light",
            new TriangularFunction(103.0, 115.0, 109.0),
            actualWeightFunc
    );
    public static LinguisticVariable<RunDao> actualWeightMedium = new LinguisticVariable<>(
            "actual weight",
            "medium",
            new TriangularFunction(109.0, 123.0, 115.0),
            actualWeightFunc
    );
    public static LinguisticVariable<RunDao> actualWeightHeavy = new LinguisticVariable<>(
            "actual weight",
            "heavy",
            new TriangularFunction(115.0, 127.0, 121.0),
            actualWeightFunc
    );
    public static LinguisticVariable<RunDao> actualWeightVeryHeavy = new LinguisticVariable<>(
            "actual weight",
            "very heavy",
            new TrapezoidalFunction(121.0, 133.0, 127.0, 133.0),
            actualWeightFunc
    );

}
