import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerControlWindow extends JFrame {
    private boolean isServerWorking = false; // Состояние сервера
    private JTextArea serverLog; // Поле для логов сервера

    public ServerControlWindow() {
        setTitle("Server Control");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Создание текстового поля для логов
        serverLog = new JTextArea();
        serverLog.setEditable(false); // Поле только для чтения
        JScrollPane scrollPane = new JScrollPane(serverLog);
        add(scrollPane, BorderLayout.CENTER);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton btnStart = new JButton("Start Server");
        JButton btnStop = new JButton("Stop Server");

        // Обработчик для кнопки старта сервера
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    appendLog("Сервер уже запущен!");
                } else {
                    isServerWorking = true;
                    appendLog("Сервер запущен!");
                }
            }
        });

        // Обработчик для кнопки остановки сервера
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    appendLog("Сервер не запущен!");
                } else {
                    isServerWorking = false;
                    appendLog("Сервер остановлен!");
                }
            }
        });

        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Метод для добавления текста в лог
    private void appendLog(String message) {
        serverLog.append(message + "\n");
        serverLog.setCaretPosition(serverLog.getDocument().getLength()); // Скролл вниз
    }
}