package fil.coo.model.impl.answers;

import fil.coo.exception.InvalidAnswerException;
import fil.coo.gui.AbstractAnswerView;
import fil.coo.gui.factory.AnswerPanelFactory;

public class TextAnswer extends SingleAnswer {

    private final static String[] yesNoAnswerText = new String[]{"vrai", "faux", "oui", "non", "yes", "no", "true",
            "false"};

    public TextAnswer(String answer) throws InvalidAnswerException {
        super(answer);
    }

    @Override
    protected boolean isQuizTextValid(String quizText) {
        return checkUserAnswerIsValid(quizText);
    }

    public String getPrompt() {
        return "(text)";
    }

    protected boolean checkUserAnswerIsValid(String userAnswer) {
        int number;
        try {
            number = Integer.parseInt(userAnswer);
            return false;
        } catch (NumberFormatException e) {
            // do nothing
        }
        for (String yesNoText : yesNoAnswerText) {
            if (yesNoText.equalsIgnoreCase(userAnswer)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public AbstractAnswerView createAnswerPanel(AnswerPanelFactory answerPanelFactory) {
        return answerPanelFactory.createAnswerPanel(this);
    }

    public String toString() {
        return this.correctAnswer;
    }

}
