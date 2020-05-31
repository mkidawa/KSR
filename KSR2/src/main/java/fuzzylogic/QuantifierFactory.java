package fuzzylogic;

import dao.RunDao;

import java.util.List;

public class QuantifierFactory implements LabelFactory<Quantifier<RunDao>> {
    @Override
    public Quantifier<RunDao> CreateLabel(String Name, List<Double> params, boolean isAbsolute) {
        if (params.size() == 4) {
            return new Quantifier<>(
                    Name,
                    new TrapezoidalFunction(params.get(0), params.get(1), params.get(2), params.get(3)),
                    isAbsolute
            );
        } else {
            return new Quantifier<>(
                    Name,
                    new TriangularFunction(params.get(0), params.get(1), params.get(2)),
                    isAbsolute
            );
        }
    }
}
