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
    public List<RunDao> getObjects() {
        return objects;
    }

    private List<RunDao> objects;

    private LinguisticVariable<RunDao> age = new LinguisticVariable<>("horse age", objects, ageFunc);
    private LinguisticVariable<RunDao> declaredWeight = new LinguisticVariable<>("declared weight", objects, declaredWeightFunc);
    private LinguisticVariable<RunDao> rating = new LinguisticVariable<>("horse rating", objects, ratingFunc);
    private LinguisticVariable<RunDao> finishTime = new LinguisticVariable<>("finish time", objects, finishTimeFunc);
    private LinguisticVariable<RunDao> winOdds = new LinguisticVariable<>("win odds", objects, winOddsFun);
    private LinguisticVariable<RunDao> lengthsBehind = new LinguisticVariable<>("lengths behind winner", objects, lengthsBehindFunc);
    private LinguisticVariable<RunDao> actualWeight = new LinguisticVariable<>("actual weight", objects, actualWeightFunc);
    private LinguisticVariable<RunDao> positionInSection = new LinguisticVariable<>("position in section", objects, positionInSectionFunc);
    private LinguisticVariable<RunDao> lengthsBehindInSection = new LinguisticVariable<>("lengths behind in section", objects, lengthsBehindInSectionFunc);

    private static Function<RunDao, Double> ageFunc = run -> (double) run.getHorseAge();
    private static Function<RunDao, Double> declaredWeightFunc = RunDao::getDeclaredWeight;
    private static Function<RunDao, Double> ratingFunc = run -> (double) run.getHorseRating();
    private static Function<RunDao, Double> finishTimeFunc = RunDao::getFinishTime;
    private static Function<RunDao, Double> winOddsFun = RunDao::getWinOdds;
    private static Function<RunDao, Double> lengthsBehindFunc = RunDao::getLengthsBehind;
    private static Function<RunDao, Double> actualWeightFunc = RunDao::getActualWeight;
    private static Function<RunDao, Double> positionInSectionFunc = run -> (double) run.getPositionSec1();
    private static Function<RunDao, Double> lengthsBehindInSectionFunc = run -> (double) run.getBehindSec1();

    public PredefinedSummarizer() {
    }

    public PredefinedSummarizer(List<RunDao> objects) {
        this.objects = objects;
    }

    public List<String> getAllSummarizerLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("ageYoung");
        labels.add("ageMiddleAged");
        labels.add("ageOld");
        labels.add("declaredWeightVeryLight");
        labels.add("declaredWeightLight");
        labels.add("declaredWeightMedium");
        labels.add("declaredWeightHeavy");
        labels.add("declaredWeightVeryHeavy");
        labels.add("ratingLow");
        labels.add("ratingMedium");
        labels.add("ratingHigh");
        labels.add("finishTimeVeryFast");
        labels.add("finishTimeFast");
        labels.add("finishTimeMedium");
        labels.add("finishTimeSlow");
        labels.add("finishTimeVerySlow");
        labels.add("winOddsLow");
        labels.add("winOddsMedium");
        labels.add("winOddsHigh");
        labels.add("lengthsBehindVeryFew");
        labels.add("lengthsBehindFew");
        labels.add("lengthsBehindSome");
        labels.add("lengthsBehindMany");
        labels.add("lengthsBehindVeryMany");
        labels.add("actualWeightVeryLight");
        labels.add("actualWeightLight");
        labels.add("actualWeightMedium");
        labels.add("actualWeightHeavy");
        labels.add("actualWeightVeryHeavy");
        labels.add("positionInSectionHigh");
        labels.add("positionInSectionMiddle");
        labels.add("positionInSectionLow");
        labels.add("lengthsBehindInSectionClose");
        labels.add("lengthsBehindInSectionMedium");
        labels.add("lengthsBehindInSectionFarBehind");
        return labels;
    };

    public Label<RunDao> ageYoung = new Label<>(age, new TrapezoidalFunction(0.0, 4.5, 0.0, 3.5), "young");
    public Label<RunDao> ageMiddleAged = new Label<>(age, new TriangularFunction(3.5, 6.5, 5.0),"middle-aged");
    public Label<RunDao> ageOld = new Label<>(age, new TrapezoidalFunction(5.5, 7.0, 6.0, 7.0),"old");

    public Label<RunDao> declaredWeightVeryLight = new Label<>(declaredWeight, new TrapezoidalFunction(693.0, 850.0, 693.0, 800.0),"very light");
    public Label<RunDao> declaredWeightLight = new Label<>(declaredWeight, new TrapezoidalFunction(800.0, 1000.0, 850.0, 950.0),"light");
    public Label<RunDao> declaredWeightMedium = new Label<>(declaredWeight, new TrapezoidalFunction(950.0, 1150.0, 1025.0, 1075.0),"medium");
    public Label<RunDao> declaredWeightHeavy = new Label<>(declaredWeight, new TrapezoidalFunction(1100.0, 1300.0, 1200.0, 1250.0),"heavy");
    public Label<RunDao> declaredWeightVeryHeavy = new Label<>(declaredWeight, new TrapezoidalFunction(1250.0, 1396.0, 1350.0, 1396.0),"very heavy");

    public Label<RunDao> ratingLow = new Label<>(rating, new TrapezoidalFunction(10.0, 40.0, 10.0, 30.0), "low rating");
    public Label<RunDao> ratingMedium = new Label<>(rating, new TrapezoidalFunction(30.0, 90.0, 50.0, 70.0), "medium rating");
    public Label<RunDao> ratingHigh = new Label<>(rating, new TrapezoidalFunction(80.0, 138.0, 110.0, 138.0), "high rating");

    public Label<RunDao> finishTimeVeryFast = new Label<>(finishTime, new TrapezoidalFunction(55.16, 70.0, 55.16, 65.0), "very fast");
    public Label<RunDao> finishTimeFast = new Label<>(finishTime, new TrapezoidalFunction(65.0, 90.0, 70.0, 80.0), "fast");
    public Label<RunDao> finishTimeMedium = new Label<>(finishTime, new TrapezoidalFunction(80.0, 120.0, 90.0, 110.0), "medium");
    public Label<RunDao> finishTimeSlow = new Label<>(finishTime, new TrapezoidalFunction(110.0, 150.0, 130.0, 140.0),"slow");
    public Label<RunDao> finishTimeVerySlow = new Label<>(finishTime, new TrapezoidalFunction(140.0, 163.58, 150.0, 163.58),"very slow");

    public Label<RunDao> winOddsLow = new Label<>(winOdds, new TrapezoidalFunction(1.0, 40.0, 1.0, 20.0), "low odds");
    public Label<RunDao> winOddsMedium = new Label<>(winOdds, new TrapezoidalFunction(20.0, 80.0, 40.0, 720.0), "medium odds");
    public Label<RunDao> winOddsHigh = new Label<>(winOdds, new TrapezoidalFunction(70.0, 99.9, 90.0, 99.9), "high odds");

    public Label<RunDao> lengthsBehindVeryFew = new Label<>(lengthsBehind, new TrapezoidalFunction(-0.5, 3.5, -0.5, 2.0), "very few");
    public Label<RunDao> lengthsBehindFew = new Label<>(lengthsBehind, new TrapezoidalFunction(1.5, 6.0, 3.0, 5.0), "few");
    public Label<RunDao> lengthsBehindSome = new Label<>(lengthsBehind, new TrapezoidalFunction(3.0, 10.0, 4.0, 7.0), "some");
    public Label<RunDao> lengthsBehindMany = new Label<>(lengthsBehind, new TrapezoidalFunction(7.0, 50.0, 10.0, 30.0), "many");
    public Label<RunDao> lengthsBehindVeryMany = new Label<>(lengthsBehind, new TrapezoidalFunction(35.0, 206.75, 50.0, 206.75), "very many");

    public Label<RunDao> actualWeightVeryLight = new Label<>(actualWeight, new TriangularFunction(103.0, 109.0, 103.0), "very light");
    public Label<RunDao> actualWeightLight = new Label<>(actualWeight, new TriangularFunction(103.0, 115.0, 109.0), "light");
    public Label<RunDao> actualWeightMedium = new Label<>(actualWeight, new TriangularFunction(109.0, 123.0, 115.0), "medium");
    public Label<RunDao> actualWeightHeavy = new Label<>(actualWeight, new TriangularFunction(115.0, 127.0, 121.0), "heavy");
    public Label<RunDao> actualWeightVeryHeavy = new Label<>(actualWeight, new TrapezoidalFunction(121.0, 133.0, 127.0, 133.0), "very heavy");

    public Label<RunDao> positionInSectionHigh = new Label<>(positionInSection, new TrapezoidalFunction(1, 4.5, 1, 3.5), "at the front of the scoreboard");
    public Label<RunDao> positionInSectionMiddle = new Label<>(positionInSection, new TriangularFunction(3.5, 9.5, 7), "in the middle of the scoreboard");
    public Label<RunDao> positionInSectionLow = new Label<>(positionInSection, new TrapezoidalFunction(8.5, 14, 9.5, 14), "at the end of the scoreboard");

    public Label<RunDao> lengthsBehindInSectionClose = new Label<>(lengthsBehindInSection, new TrapezoidalFunction(0.15, 3.5, 0.15, 2.5), "close behind");
    public Label<RunDao> lengthsBehindInSectionMedium = new Label<>(lengthsBehindInSection, new TriangularFunction(2.5, 8.25, 4.75), "medium distance behind");
    public Label<RunDao> lengthsBehindInSectionFarBehind = new Label<>(lengthsBehindInSection, new TrapezoidalFunction(6.95, 9.75, 8.25, 9.75), "far behind");


}
