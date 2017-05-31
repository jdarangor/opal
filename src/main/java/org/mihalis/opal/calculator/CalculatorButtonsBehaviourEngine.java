package org.mihalis.opal.calculator;

public class CalculatorButtonsBehaviourEngine {

	private final CalculatorButtonsComposite composite;

	public CalculatorButtonsBehaviourEngine(final CalculatorButtonsComposite composite) {
		this.composite = composite;
	}

	public void processBackSpace() {
		if (composite.isReadyToEnterNewNumber()) {
			return;
		}
		String content = getContent();
		if (content.length() < 2) {
			return;

		}
		String newContent = content.substring(0, content.length() - 2);
		setContent(newContent.length() == 0 ? "0" : newContent);
		composite.setReadyToEnterNewNumber(false);
	}

	private String getContent() {
		return composite.getDisplayArea().getText();
	}

	private void setContent(final String newContent) {
		composite.getDisplayArea().setText(newContent);
		composite.fireModifyListeners();
	}

	public void clearResult() {
		setContent("0");
	}

	public void addDecimalPoint() {
		if (composite.isReadyToEnterNewNumber()) {
			return;
		}
		String content = getContent();
		if (content.indexOf('.') > 0) {
			return;
		}
		String newContent = content + ".";
		setContent(newContent);
	}

	public void addDigitToDisplay(final int digit) {
		String content = getContent();
		if (composite.isReadyToEnterNewNumber()) {
			setContent(String.valueOf(digit));
			return;
		}
		String newContent;
		if (content.length() == 1) {
			if (digit == 0) {
				return;
			}
			if (getContent().equals("0")) {
				newContent = String.valueOf(digit);
			} else {
				newContent = content + String.valueOf(digit);
			}
		} else {
			newContent = content + String.valueOf(digit);
		}
		setContent(newContent);
	}

}
