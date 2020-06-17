package fuzzylogic;

import com.mongodb.lang.Nullable;
import dao.RunDao;
import fuzzyruns.PredefinedSummarizer;

import java.util.List;
import java.util.function.Function;

public class SummarizerFactory<T> implements LabelFactory<Label<RunDao>> {
    private PredefinedSummarizer summarizerGlobal;

    public SummarizerFactory(PredefinedSummarizer summarizer) {
        summarizerGlobal = summarizer;
    }

    @Override
    public Label<RunDao> CreateLabel(String Name, List<Double> params, @Nullable boolean isAbsolute, String linguisticVariableName) {
        if (params.size() == 4) {
            switch (linguisticVariableName) {
                case "horse age":
                    return new Label<>(summarizerGlobal.age, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "declared weight":
                    return new Label<>(summarizerGlobal.declaredWeight, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "horse rating":
                    return new Label<>(summarizerGlobal.rating, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "finish time":
                    return new Label<>(summarizerGlobal.finishTime, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "win odds":
                    return new Label<>(summarizerGlobal.winOdds, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "lengths behind winner":
                    return new Label<>(summarizerGlobal.lengthsBehind, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "actual weight":
                    return new Label<>(summarizerGlobal.actualWeight, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "position in section":
                    return new Label<>(summarizerGlobal.positionInSection, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                case "lengths behind in section":
                    return new Label<>(summarizerGlobal.lengthsBehindInSection, new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)), Name);
                default:
                    return null;
            }
        } else {
            switch (linguisticVariableName) {
                case "horse age":
                    return new Label<>(summarizerGlobal.age, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "declared weight":
                    return new Label<>(summarizerGlobal.declaredWeight, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "horse rating":
                    return new Label<>(summarizerGlobal.rating, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "finish time":
                    return new Label<>(summarizerGlobal.finishTime, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "win odds":
                    return new Label<>(summarizerGlobal.winOdds, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "lengths behind winner":
                    return new Label<>(summarizerGlobal.lengthsBehind, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "actual weight":
                    return new Label<>(summarizerGlobal.actualWeight, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "position in section":
                    return new Label<>(summarizerGlobal.positionInSection, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                case "lengths behind in section":
                    return new Label<>(summarizerGlobal.lengthsBehindInSection, new TriangularFunction(params.get(0), params.get(1), params.get(2)), Name);
                default:
                    return null;
            }
        }
    }
}
