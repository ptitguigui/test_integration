package fil.coo.gui.impl.answers;

import fil.coo.gui.impl.AnswerPanel;

import java.awt.*;

public class TextAnswerPanel extends AnswerPanel {

	private TextArea textArea; 
	
    public TextAnswerPanel() {
        super();

        initCustomView();
    }

    @Override
    public String getUserInput() {
        return textArea.getText();
    }

    @Override
    public void setUserInput(String input) {
        textArea.setText(input);
    }

    @Override
    protected void initCustomView() {
    	textArea = new TextArea();
        textArea.setPreferredSize(new Dimension(200, 40));

        rootPanel.add(textArea);

    }

}
