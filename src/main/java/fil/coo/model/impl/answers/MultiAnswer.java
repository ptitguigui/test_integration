package fil.coo.model.impl.answers;

import fil.coo.gui.AbstractAnswerView;
import fil.coo.model.impl.Answer;
import fil.coo.exception.InvalidAnswerException;
import fil.coo.gui.factory.AnswerPanelFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Has several answers that are correct
 */
public class MultiAnswer extends Answer {

    List<TextAnswer> answers;

    public MultiAnswer(String allAnswers) throws InvalidAnswerException {
        super(allAnswers);
        answers = new ArrayList<>();
        for (String oneAnswer : allAnswers.split(" ; ")) {
            if (!("".equals(oneAnswer))) {
                answers.add(new TextAnswer(oneAnswer));
            }
        }
        if (answers.isEmpty()) {
            throw new InvalidAnswerException();
        }
    }

    @Override
    protected boolean isQuizTextValid(String quizText) {
        return quizText.contains(";");
    }

    public String getPrompt() {
        return "(" + answers.size() + " possible answers)";
    }

    protected boolean checkUserAnswerIsValid(String userAnswer) {
        return answers.get(0).isValid(userAnswer);
    }

    protected boolean checkUserAnswerIsCorrect(String userAnswer) {
        boolean found = false;
        Iterator<TextAnswer> textAnswerIterator = answers.iterator();
        while (textAnswerIterator.hasNext() && !found) {
            found = textAnswerIterator.next().isCorrect(userAnswer);
        }
        return found;
    }

    @Override
    public AbstractAnswerView createAnswerPanel(AnswerPanelFactory answerPanelFactory) {
        return answerPanelFactory.createAnswerPanel(this);
    }

    @Override
	public String getCorrectAnswer() {
		String allAnswer = "";
		for (TextAnswer textAnswer : answers) {
			allAnswer += textAnswer.toString()+" ";
		}
		return allAnswer;
	}

}
