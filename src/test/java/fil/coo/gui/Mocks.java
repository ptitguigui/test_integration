package fil.coo.gui;

import fil.coo.gui.factory.AnswerPanelFactory;
import fil.coo.model.AnswerModel;
import fil.coo.model.QuestionModel;
import fil.coo.model.QuizModel;
import fil.coo.model.impl.CLIQuestion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class Mocks {

    public static class MockQuestionView extends AbstractQuestionView {

        /**
         * @param questionText the model from which to create this question
         * @param answerView   the specific {@link AbstractAnswerView} that this views should hold
         */
        public MockQuestionView(String questionText, AbstractAnswerView answerView) {
            super(questionText, answerView);
        }

        @Override
        public Component getView() {
            return new JPanel();
        }
    }

    public static class MockAnswerView extends AbstractAnswerView {

        private String input;

        public MockAnswerView() {
            super();
            this.input = "";
        }

        @Override
        public String getUserInput() {
            return input;
        }

        @Override
        public void setUserInput(String input) {

            this.input = input;
        }

        @Override
        public void setBorder(Border border) {

        }

        @Override
        public Border getBorder() {
            return null;
        }

        @Override
        public Component getView() {
            return new JPanel();
        }
    }

    public static class MockQuizModel extends QuizModel {
        private List<Integer> points;
        private boolean correctOrValid;

        /**
         * @param points
         * @param correctOrValid make all queries to correct/valid return this
         */
        public MockQuizModel(List<Integer> points, boolean correctOrValid) {
            this.points = points;
            this.correctOrValid = correctOrValid;
        }

        @Override
        public List<Integer> getPoints() {
            return points;
        }

        @Override
        protected AnswerModel getAnswer(int questionIndex) {
            return new MockAnswer(correctOrValid, correctOrValid);
        }
    }

    public static class MockQuestion extends CLIQuestion {

        /**
         * @param text     the question text
         * @param answer   the answer
         * @param nbPoints the number of points
         */
        public MockQuestion(String text, MockAnswer answer, int nbPoints) {
            super(text, answer, nbPoints);
        }

        @Override
        public int ask() {
            return 0;
        }
    }

    public static class MockAnswer implements AnswerModel {

        private final boolean shouldReturnValid;
        private final boolean shouldReturnCorrect;

        public MockAnswer(boolean shouldReturnValid, boolean shouldReturnCorrect) {
            this.shouldReturnValid = shouldReturnValid;
            this.shouldReturnCorrect = shouldReturnCorrect;
        }

        @Override
        public AbstractAnswerView createAnswerPanel(AnswerPanelFactory answerPanelFactory) {
            return null;
        }

        @Override
        public String getPrompt() {
            return null;
        }

        @Override
        public boolean isValid(String userAnswer) {
            return shouldReturnValid;
        }

        @Override
        public boolean isCorrect(String userAnswer) {
            return shouldReturnCorrect;
        }

        @Override
        public String getCorrectAnswer() {
            return null;
        }
    }

    public static class MockQuizView extends AbstractQuizView {

        private List<String> inputs;

        public int onSubmissionFinishedCallCount;
        public int showInValidInputsCallCount;

        public MockQuizView(List<String> inputs) {
            this.inputs = inputs;
            onSubmissionFinishedCallCount = 0;
            showInValidInputsCallCount = 0;
        }

        @Override
        public List<String> getUserAnswerInput() {
            return inputs;
        }

        @Override
        protected void addQuestionViewToThisView(AbstractQuestionView questionView, boolean refresh) {

        }

        @Override
        public void setVisible(boolean visible) {

        }

        @Override
        public void showInvalidInputs(List<Integer> invalidInputIndexes) {
            this.showInValidInputsCallCount++;
        }

        @Override
        public void onSubmissionFinished(int pointsWon) {
            this.onSubmissionFinishedCallCount++;
        }

        @Override
        public Component getView() {
            return null;
        }

    }
}
