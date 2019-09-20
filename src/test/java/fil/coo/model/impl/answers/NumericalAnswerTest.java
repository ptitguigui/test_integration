package fil.coo.model.impl.answers;

import fil.coo.exception.InvalidAnswerException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumericalAnswerTest extends SingleAnswerTest {

    private static final String DEFAULT_ANSWER = "1";
    private static final String EXPECTED_PROMPT = "(numerical)";

    protected SingleAnswer getSpecificSingleAnswer(String answer) throws NullPointerException, InvalidAnswerException {
        return new NumericalAnswer(answer);
    }

    protected String getDefaultAnswer() {
        return DEFAULT_ANSWER;
    }

    protected String getCorrectAnswer() {
        return getDefaultAnswer();
    }

    @Test
    public void testPrompt() {
        Assert.assertEquals(EXPECTED_PROMPT, singleAnswer.getPrompt());
    }

    @Test
    public void testNonNumberIsInvalid() {
        assertFalse(singleAnswer.isValid(""));
        assertFalse(singleAnswer.isValid("yes"));
        assertFalse(singleAnswer.isValid("no"));
        assertFalse(singleAnswer.isValid("oui"));
        assertFalse(singleAnswer.isValid("non"));
    }

    @Test
    public void testNumberIsValid() {
        assertTrue(singleAnswer.isValid("1"));
        assertTrue(singleAnswer.isValid("-1"));
    }

    @Test
    public void testDoesNotAcceptInCorrectAnswer() throws NullPointerException, InvalidAnswerException {
        NumericalAnswer textAnswer = new NumericalAnswer(DEFAULT_ANSWER);
        assertFalse(textAnswer.isCorrect(""));
        assertFalse(textAnswer.isCorrect("-1"));
        assertFalse(textAnswer.isCorrect("yes"));
        assertFalse(textAnswer.isCorrect("no"));
        assertFalse(textAnswer.isCorrect("oui"));
        assertFalse(textAnswer.isCorrect("non"));
    }

    @Test
    public void testDoesAcceptCorrectAnswer() throws NullPointerException, InvalidAnswerException {
        NumericalAnswer textAnswer = new NumericalAnswer(DEFAULT_ANSWER);
        assertTrue(textAnswer.isCorrect(DEFAULT_ANSWER));
    }
}