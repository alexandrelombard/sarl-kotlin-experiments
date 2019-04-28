package fr.utbm.kotlinjanus;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * @author Alexandre
 */
@SarlSpecification("0.9")
@SarlElementType(15)
@SuppressWarnings("all")
public class TestEvent extends Event {
    @SyntheticMember
    public TestEvent() {
        super();
    }

    @SyntheticMember
    public TestEvent(final Address source) {
        super(source);
    }

    @SyntheticMember
    private final static long serialVersionUID = 588368462L;
}
