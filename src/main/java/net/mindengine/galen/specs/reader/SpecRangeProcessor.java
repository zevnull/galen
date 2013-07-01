package net.mindengine.galen.specs.reader;

import net.mindengine.galen.specs.Spec;

public class SpecRangeProcessor implements SpecProcessor {

    private SpecRangeInit specInit;

    public SpecRangeProcessor(SpecRangeInit specRangeInit) {
        this.specInit = specRangeInit;
    }

    @Override
    public Spec processSpec(String paramsText) {
        return specInit.init(new RangeProcessor().process(new StringCharReader(paramsText)));
    }

}
