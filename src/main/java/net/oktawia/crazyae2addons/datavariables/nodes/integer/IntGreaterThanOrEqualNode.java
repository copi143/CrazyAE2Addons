package net.oktawia.crazyae2addons.datavariables.nodes.integer;

import net.oktawia.crazyae2addons.datavariables.*;

import java.util.List;
import java.util.Map;

public class IntGreaterThanOrEqualNode implements IFlowNode {

    private IFlowNode onTrue;
    private IFlowNode onFalse;

    public IntGreaterThanOrEqualNode() {
    }


    @Override
    public Map<String, FlowResult> execute(String where, Map<String, DataValue<?>> inputs) {
        DataValue<?> a = inputs.get("a");
        DataValue<?> b = inputs.get("b");

        if (a == null || b == null || a.getType() != DataType.INT || b.getType() != DataType.INT)
            return Map.of();

        boolean result = ((IntValue) a).getRaw() >= ((IntValue) b).getRaw();
        return Map.of(
                result ? "true" : "false",
                FlowResult.of(result ? onTrue : onFalse, new BoolValue(result))
        );
    }

    @Override
    public void setOutputNodes(List<IFlowNode> outputs) {
        if (!outputs.isEmpty()) this.onTrue = outputs.get(0);
        if (outputs.size() > 1) this.onFalse = outputs.get(1);
    }

    static
    public Map<String, String> getArgs() {
        return Map.of(
                "onTrue", "Name of the node that should be called on true",
                "onFalse", "Name of the node that should be called on false"
        );
    }

    static
    public String getDesc() {
        return "Compares INT marked with \"^A\" against one marked with \"^B\", true if A is the same or bigger than B";
    }

    static
    public int getOutputPaths() {
        return 2;
    }

    static
    public List<?> getInputTypes() {
        return List.of(Integer.class, Integer.class);
    }

    static
    public Map<String, DataType> getExpectedInputs() {
        return Map.of(
                "a", DataType.INT,
                "b", DataType.INT
        );
    }
}
