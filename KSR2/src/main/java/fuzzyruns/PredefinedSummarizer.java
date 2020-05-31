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
    public List<Label<RunDao>> getAllLabels() {return labels;}

    public List<RunDao> objects;
    private List<Label<RunDao>> labels = new ArrayList<>();

    public LinguisticVariable<RunDao> age = new LinguisticVariable<>("horse age", objects, ageFunc);
    public LinguisticVariable<RunDao> declaredWeight = new LinguisticVariable<>("declared weight", objects, declaredWeightFunc);
    public LinguisticVariable<RunDao> rating = new LinguisticVariable<>("horse rating", objects, ratingFunc);
    public LinguisticVariable<RunDao> finishTime = new LinguisticVariable<>("finish time", objects, finishTimeFunc);
    public LinguisticVariable<RunDao> winOdds = new LinguisticVariable<>("win odds", objects, winOddsFun);
    public LinguisticVariable<RunDao> lengthsBehind = new LinguisticVariable<>("lengths behind winner", objects, lengthsBehindFunc);
    public LinguisticVariable<RunDao> actualWeight = new LinguisticVariable<>("actual weight", objects, actualWeightFunc);
    public LinguisticVariable<RunDao> positionInSection = new LinguisticVariable<>("position in section", objects, positionInSectionFunc);
    public LinguisticVariable<RunDao> lengthsBehindInSection = new LinguisticVariable<>("lengths behind in section", objects, lengthsBehindInSectionFunc);

    private static Function<RunDao, Double> ageFunc = run -> (double) run.getHorseAge();
    private static Function<RunDao, Double> declaredWeightFunc = RunDao::getDeclaredWeight;
    private static Function<RunDao, Double> ratingFunc = run -> (double) run.getHorseRating();
    private static Function<RunDao, Double> finishTimeFunc = RunDao::getFinishTime;
    private static Function<RunDao, Double> winOddsFun = RunDao::getWinOdds;
    private static Function<RunDao, Double> lengthsBehindFunc = RunDao::getLengthsBehind;
    private static Function<RunDao, Double> actualWeightFunc = RunDao::getActualWeight;
    private static Function<RunDao, Double> positionInSectionFunc = run -> (double) run.getPositionSec1();
    private static Function<RunDao, Double> lengthsBehindInSectionFunc = run -> (double) run.getBehindSec1();

    public PredefinedSummarizer(List<RunDao> objects) {
        this.objects = objects;
        labels.add(new Label<>(age, new TrapezoidalFunction(0.0, 4.5, 0.0, 3.5), "young"));
        labels.add(new Label<>(age, new TriangularFunction(3.5, 6.5, 5.0),"middle-aged"));
        labels.add(new Label<>(age, new TrapezoidalFunction(5.5, 7.0, 6.0, 7.0),"old"));
        labels.add(new Label<>(declaredWeight, new TrapezoidalFunction(693.0, 850.0, 693.0, 800.0),"very light"));
        labels.add(new Label<>(declaredWeight, new TrapezoidalFunction(800.0, 1000.0, 850.0, 950.0),"light"));
        labels.add(new Label<>(declaredWeight, new TrapezoidalFunction(950.0, 1150.0, 1025.0, 1075.0),"medium"));
        labels.add(new Label<>(declaredWeight, new TrapezoidalFunction(1100.0, 1300.0, 1200.0, 1250.0),"heavy"));
        labels.add(new Label<>(declaredWeight, new TrapezoidalFunction(1250.0, 1396.0, 1350.0, 1396.0),"very heavy"));
        labels.add(new Label<>(rating, new TrapezoidalFunction(10.0, 40.0, 10.0, 30.0), "low rating"));
        labels.add(new Label<>(rating, new TrapezoidalFunction(30.0, 90.0, 50.0, 70.0), "medium rating"));
        labels.add(new Label<>(rating, new TrapezoidalFunction(80.0, 138.0, 110.0, 138.0), "high rating"));
        labels.add(new Label<>(finishTime, new TrapezoidalFunction(55.16, 70.0, 55.16, 65.0), "very fast"));
        labels.add(new Label<>(finishTime, new TrapezoidalFunction(65.0, 90.0, 70.0, 80.0), "fast"));
        labels.add(new Label<>(finishTime, new TrapezoidalFunction(80.0, 120.0, 90.0, 110.0), "medium"));
        labels.add(new Label<>(finishTime, new TrapezoidalFunction(110.0, 150.0, 130.0, 140.0),"slow"));
        labels.add(new Label<>(finishTime, new TrapezoidalFunction(140.0, 163.58, 150.0, 163.58),"very slow"));
        labels.add(new Label<>(winOdds, new TrapezoidalFunction(1.0, 40.0, 1.0, 20.0), "low odds"));
        labels.add(new Label<>(winOdds, new TrapezoidalFunction(20.0, 80.0, 40.0, 720.0), "medium odds"));
        labels.add(new Label<>(winOdds, new TrapezoidalFunction(70.0, 99.9, 90.0, 99.9), "high odds"));
        labels.add(new Label<>(lengthsBehind, new TrapezoidalFunction(-0.5, 3.5, -0.5, 2.0), "very few"));
        labels.add(new Label<>(lengthsBehind, new TrapezoidalFunction(1.5, 6.0, 3.0, 5.0), "few"));
        labels.add(new Label<>(lengthsBehind, new TrapezoidalFunction(3.0, 10.0, 4.0, 7.0), "some"));
        labels.add(new Label<>(lengthsBehind, new TrapezoidalFunction(7.0, 50.0, 10.0, 30.0), "many"));
        labels.add(new Label<>(lengthsBehind, new TrapezoidalFunction(35.0, 206.75, 50.0, 206.75), "very many"));
        labels.add(new Label<>(actualWeight, new TriangularFunction(103.0, 109.0, 103.0), "very light"));
        labels.add(new Label<>(actualWeight, new TriangularFunction(103.0, 115.0, 109.0), "light"));
        labels.add(new Label<>(actualWeight, new TriangularFunction(109.0, 123.0, 115.0), "medium"));
        labels.add(new Label<>(actualWeight, new TriangularFunction(115.0, 127.0, 121.0), "heavy"));
        labels.add(new Label<>(actualWeight, new TrapezoidalFunction(121.0, 133.0, 127.0, 133.0), "very heavy"));
        labels.add(new Label<>(positionInSection, new TrapezoidalFunction(1, 4.5, 1, 3.5), "at the front of the scoreboard"));
        labels.add(new Label<>(positionInSection, new TriangularFunction(3.5, 9.5, 7), "in the middle of the scoreboard"));
        labels.add(new Label<>(positionInSection, new TrapezoidalFunction(8.5, 14, 9.5, 14), "at the end of the scoreboard"));
        labels.add(new Label<>(lengthsBehindInSection, new TrapezoidalFunction(0.15, 3.5, 0.15, 2.5), "close behind"));
        labels.add(new Label<>(lengthsBehindInSection, new TriangularFunction(2.5, 8.25, 4.75), "medium distance behind"));
        labels.add(new Label<>(lengthsBehindInSection, new TrapezoidalFunction(6.95, 9.75, 8.25, 9.75), "far behind"));
    }
}
