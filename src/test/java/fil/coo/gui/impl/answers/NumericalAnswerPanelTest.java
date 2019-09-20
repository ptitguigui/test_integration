package fil.coo.gui.impl.answers;

import fil.coo.gui.AbstractAnswerView;
import fil.coo.gui.impl.AnswerPanelTest;

import static org.junit.Assert.*;

public class NumericalAnswerPanelTest extends AnswerPanelTest {

    private static final String DUMMY_INPUT = "0";

    @Override
    protected AbstractAnswerView getSpecificAnswerView() {
        return new NumericalAnswerPanel();
    }

    @Override
    protected String getDummyInput() {
        return DUMMY_INPUT;
    }
}