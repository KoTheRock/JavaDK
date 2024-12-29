import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        new GameWindow();

        SwingUtilities.invokeLater(() -> {
            ServerControlWindow serverControlWindow = new ServerControlWindow();
            serverControlWindow.setVisible(true);

        });
        SwingUtilities.invokeLater(() -> {
            ChatClientWindow chatClientWindow = new ChatClientWindow();
            chatClientWindow.setVisible(true);
        });
    }
}