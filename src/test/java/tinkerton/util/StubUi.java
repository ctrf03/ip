package tinkerton.util;

public class StubUi extends Ui {
    private String lastPrintedMessage;

    @Override
    public void print(String message) {
        this.lastPrintedMessage = message;
    }

    public String getLastPrintedMessage() {
        return lastPrintedMessage;
    }
}
