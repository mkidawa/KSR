package fuzzyruns;

import dao.RunDao;
import fuzzylogic.Label;
import fuzzylogic.LinguisticVariable;
import fuzzylogic.TrapezoidalFunction;
import fuzzylogic.TriangularFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PredefinedSummarizer {
    private List<Label<RunDao>> summarizers;

    public List<RunDao> getObjects() {
        return objects;
    }

    private List<RunDao> objects;

    public List<Label<RunDao>> getSummarizers() {
        return summarizers;
    }

    private LinguisticVariable<RunDao> age = new LinguisticVariable<RunDao>("horse age", objects, ageFunc);

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

    public PredefinedSummarizer(List<RunDao> objects) {
        this.objects = objects;
    }

    public List<String> getAllSummarizerLabels() {
        List<String> labels = new ArrayList<>();
        labels.add(ageYoung.getLinguisticVariableName() + " " + ageYoung.getLabelName());
        labels.add(ageMiddleAged.getLinguisticVariableName() + " " + ageMiddleAged.getLabelName());
        labels.add(ageOld.getLinguisticVariableName() + " " + ageOld.getLabelName());
        labels.add(declaredWeightVeryLight.getLinguisticVariableName() + " " + declaredWeightVeryLight.getLabelName() );
        labels.add(declaredWeightLight.getLinguisticVariableName() + " " + declaredWeightLight.getLabelName());
        labels.add(declaredWeightMedium.getLinguisticVariableName() + " " + declaredWeightMedium.getLabelName());
        labels.add(declaredWeightHeavy.getLinguisticVariableName() + " " + declaredWeightHeavy.getLabelName());
        labels.add(declaredWeightVeryHeavy.getLinguisticVariableName() + " " + declaredWeightVeryHeavy.getLabelName());
        return labels;
    };

//    public static Label<RunDao> ageYoung = new Label<>(
//            "horse age",
//            "young",
//            new TrapezoidalFunction(0.0, 4.5, 0.0, 3.5),
//            ageFunc
//    );

    public Label<RunDao> ageYoung = new Label<>(age, new TrapezoidalFunction(0.0, 4.5, 0.0, 3.5), "young");

    public static Label<RunDao> ageMiddleAged = new Label<>(
            "horse age",
            "middle-aged",
            new TriangularFunction(3.5, 6.5, 5.0),
            ageFunc
    );
    public static Label<RunDao> ageOld = new Label<>(
            "horse age",
            "old",
            new TrapezoidalFunction(5.5, 7.0, 6.0, 7.0),
            ageFunc
    );

    public static Label<RunDao> declaredWeightVeryLight = new Label<>(
            "declared weight",
            "very light",
            new TrapezoidalFunction(693.0, 850.0, 693.0, 800.0),
            declaredWeightFunc
    );
    public static Label<RunDao> declaredWeightLight = new Label<>(
            "declared weight",
            "light",
            new TrapezoidalFunction(800.0, 1000.0, 850.0, 950.0),
            declaredWeightFunc
    );
    public static Label<RunDao> declaredWeightMedium = new Label<>(
            "declared weight",
            "medium",
            new TrapezoidalFunction(950.0, 1150.0, 1025.0, 1075.0),
            declaredWeightFunc
    );
    public static Label<RunDao> declaredWeightHeavy = new Label<>(
            "declared weight",
            "heavy",
            new TrapezoidalFunction(1100.0, 1300.0, 1200.0, 1250.0),
            declaredWeightFunc
    );
    public static Label<RunDao> declaredWeightVeryHeavy = new Label<>(
            "declared weight",
            "very heavy",
            new TrapezoidalFunction(1250.0, 1396.0, 1350.0, 1396.0),
            declaredWeightFunc
    );

    public static Label<RunDao> ratingLow = new Label<>(
            "horse rating",
            "low rating",
            new TrapezoidalFunction(10.0, 40.0, 10.0, 30.0),
            ratingFunc
    );
    public static Label<RunDao> ratingMedium = new Label<>(
            "horse rating",
            "medium rating",
            new TrapezoidalFunction(30.0, 90.0, 50.0, 70.0),
            ratingFunc
    );
    public static Label<RunDao> ratingHigh = new Label<>(
            "horse rating",
            "high rating",
            new TrapezoidalFunction(80.0, 138.0, 110.0, 138.0),
            ratingFunc
    );

    public static Label<RunDao> finishTimeVeryFast = new Label<>(
            "finish time",
            "very fast",
            new TrapezoidalFunction(55.16, 70.0, 55.16, 65.0),
            finishTimeFunc
    );
    public static Label<RunDao> finishTimeFast = new Label<>(
            "finish time",
            "fast",
            new TrapezoidalFunction(65.0, 90.0, 70.0, 80.0),
            finishTimeFunc
    );
    public static Label<RunDao> finishTimeMedium = new Label<>(
            "finish time",
            "medium",
            new TrapezoidalFunction(80.0, 120.0, 90.0, 110.0),
            finishTimeFunc
    );
    public static Label<RunDao> finishTimeSlow = new Label<>(
            "finish time",
            "slow",
            new TrapezoidalFunction(110.0, 150.0, 130.0, 140.0),
            finishTimeFunc
    );
    public static Label<RunDao> finishTimeVerySlow = new Label<>(
            "finish time",
            "very slow",
            new TrapezoidalFunction(140.0, 163.58, 150.0, 163.58),
            finishTimeFunc
    );

    public static Label<RunDao> winOddsLow = new Label<>(
            "win odds",
            "low odds",
            new TrapezoidalFunction(1.0, 40.0, 1.0, 20.0),
            winOddsFun
    );
    public static Label<RunDao> winOddsMedium = new Label<>(
            "win odds",
            "medium odds",
            new TrapezoidalFunction(20.0, 80.0, 40.0, 720.0),
            winOddsFun
    );
    public static Label<RunDao> winOddsHigh = new Label<>(
            "win odds",
            "high odds",
            new TrapezoidalFunction(70.0, 99.9, 90.0, 99.9),
            winOddsFun
    );

    public static Label<RunDao> lengthsBehindVeryFew = new Label<>(
            "lengths behind winner",
            "very few",
            new TrapezoidalFunction(-0.5, 3.5, -0.5, 2.0),
            lengthsBehindFunc
    );
    public static Label<RunDao> lengthsBehindFew = new Label<>(
            "lengths behind winner",
            "few",
            new TrapezoidalFunction(1.5, 6.0, 3.0, 5.0),
            lengthsBehindFunc
    );
    public static Label<RunDao> lengthsBehindSome = new Label<>(
            "lengths behind winner",
            "some",
            new TrapezoidalFunction(3.0, 10.0, 4.0, 7.0),
            lengthsBehindFunc
    );
    public static Label<RunDao> lengthsBehindMany = new Label<>(
            "lengths behind winner",
            "many",
            new TrapezoidalFunction(7.0, 50.0, 10.0, 30.0),
            lengthsBehindFunc
    );
    public static Label<RunDao> lengthsBehindVeryMany = new Label<>(
            "lengths behind winner",
            "very many",
            new TrapezoidalFunction(35.0, 206.75, 50.0, 206.75),
            lengthsBehindFunc
    );

    public static Label<RunDao> actualWeightVeryLight = new Label<>(
            "actual weight",
            "very light",
            new TriangularFunction(103.0, 109.0, 103.0),
            actualWeightFunc
    );
    public static Label<RunDao> actualWeightLight = new Label<>(
            "actual weight",
            "light",
            new TriangularFunction(103.0, 115.0, 109.0),
            actualWeightFunc
    );
    public static Label<RunDao> actualWeightMedium = new Label<>(
            "actual weight",
            "medium",
            new TriangularFunction(109.0, 123.0, 115.0),
            actualWeightFunc
    );
    public static Label<RunDao> actualWeightHeavy = new Label<>(
            "actual weight",
            "heavy",
            new TriangularFunction(115.0, 127.0, 121.0),
            actualWeightFunc
    );
    public static Label<RunDao> actualWeightVeryHeavy = new Label<>(
            "actual weight",
            "very heavy",
            new TrapezoidalFunction(121.0, 133.0, 127.0, 133.0),
            actualWeightFunc
    );

}
