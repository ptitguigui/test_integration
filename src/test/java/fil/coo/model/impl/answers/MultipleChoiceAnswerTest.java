package fil.coo.model.impl.answers;

import fil.coo.exception.InvalidAnswerException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MultipleChoiceAnswerTest extends SingleAnswerTest {


    private final static String ONE = "Robert";
    private final static String TWO = "Bourricot";
    private final static String THREE = "Bill";
    private final static String FOUR = "Jolly Jumper";

    private final static String[] DEFAULT_ANSWER_LIST = {ONE, TWO, THREE, FOUR};
    private static String DEFAULT_ANSWER_VALUE;

    private final static String CORRECT_ANSWER = ONE;
    private final static String INCORRECT_ANSWER = TWO;


    @BeforeClass
    public static void setupDefaultAnswer() {
        DEFAULT_ANSWER_VALUE = "";
        for (int i = 0; i < DEFAULT_ANSWER_LIST.length - 1; i++) {
            DEFAULT_ANSWER_VALUE += DEFAULT_ANSWER_LIST[i] + " | ";
        }
        DEFAULT_ANSWER_VALUE += DEFAULT_ANSWER_LIST[DEFAULT_ANSWER_LIST.length - 1];
    }


    @Override
    protected String getDefaultAnswer() {
        return DEFAULT_ANSWER_VALUE;
    }

    @Override
    protected String getCorrectAnswer() {
        return CORRECT_ANSWER;
    }

    @Override
    protected SingleAnswer getSpecificSingleAnswer(String answer)
            throws NullPointerException, InvalidAnswerException {
        return new MultipleChoiceAnswer(answer);
    }

    @Test
    public void testCorrectParse() throws InvalidAnswerException {
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer(DEFAULT_ANSWER_VALUE);
        assertEquals(4, answer.getChoices().size());

        for (int i = 0; i < DEFAULT_ANSWER_LIST.length; i++) {
            assertEquals(DEFAULT_ANSWER_LIST[i], answer.getChoices().get(i).getCorrectAnswer());
        }
    }

    @Test
    public void testWhenIsValid() {
        assertTrue(singleAnswer.isValid(CORRECT_ANSWER));
        assertTrue(singleAnswer.isValid(INCORRECT_ANSWER));
    }

    @Test
    public void testWhenIsNotValid() {
        assertFalse(singleAnswer.isValid(""));
        assertFalse(singleAnswer.isValid("-1"));
        assertFalse(singleAnswer.isValid("1"));
    }

    @Test
    public void testWhenIsCorrect() throws NullPointerException, InvalidAnswerException {
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer(DEFAULT_ANSWER_VALUE);
        assertTrue(answer.isCorrect(CORRECT_ANSWER));
    }

    @Test
    public void testWhenIsNotCorrect() throws NullPointerException, InvalidAnswerException {
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer(DEFAULT_ANSWER_VALUE);
        assertFalse(answer.isCorrect(INCORRECT_ANSWER));
    }

    @Test
    public void testGetPossibleAnswers() throws InvalidAnswerException {
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer(DEFAULT_ANSWER_VALUE);
        boolean containsResult = answer.getPossibleAnswers().containsAll(Arrays.asList(DEFAULT_ANSWER_LIST));
        assertThat(containsResult, is(true));
    }

    @Test
    public void testGetPrompt() throws InvalidAnswerException {
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer(DEFAULT_ANSWER_VALUE);
        String prompt = answer.getPrompt();
        String trimPrompt = prompt.substring(1, prompt.length() - 1).trim();

        ArrayList<String> strings = new ArrayList<>(Arrays.asList(trimPrompt.split("\\s*\\|\\s*")));
        strings.removeIf(String::isEmpty);

        boolean containsResult = strings.containsAll(Arrays.asList(DEFAULT_ANSWER_LIST));
        assertThat(containsResult, is(true));
    }
}
