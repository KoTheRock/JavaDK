import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChatClientWindow extends JFrame {
    private static final String LOG_FILE = "chat_history.txt";
    private JTextField tfLogin;
    private JTextField tfPassword;
    private JTextField tfServerIP;
    private JTextField tfPort;
    private JTextField tfMessage;
    private JTextArea taChat;
    private JButton btnConnect;
    private JButton btnSend;

    public ChatClientWindow() {
        setTitle("Chat Client");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Верхняя панель для ввода данных сервера
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Настройки сервера"));

        tfLogin = new JTextField();
        tfPassword = new JTextField();
        tfServerIP = new JTextField("127.0.0.1");
        tfPort = new JTextField("8080");
        btnConnect = new JButton("Подключиться");

        topPanel.add(new JLabel("Логин:"));
        topPanel.add(tfLogin);
        topPanel.add(new JLabel("Пароль:"));
        topPanel.add(tfPassword);
        topPanel.add(new JLabel("IP сервера:"));
        topPanel.add(tfServerIP);
        topPanel.add(new JLabel("Порт:"));
        topPanel.add(tfPort);
        add(topPanel, BorderLayout.NORTH);

        // Область для чата
        taChat = new JTextArea();
        taChat.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taChat);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Сообщения чата"));
        add(scrollPane, BorderLayout.CENTER);

        // Загружаем историю из файла
        loadChatHistory();

        // Нижняя панель для отправки сообщений
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Отправка сообщения"));

        tfMessage = new JTextField();
        btnSend = new JButton("Отправить");
        bottomPanel.add(tfMessage, BorderLayout.CENTER);
        bottomPanel.add(btnSend, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Обработчик подключения к серверу
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = tfLogin.getText();
                String password = tfPassword.getText();
                String serverIP = tfServerIP.getText();
                String port = tfPort.getText();

                logMessage("Подключение к серверу " + serverIP + ":" + port + " с логином " + login);
            }
        });

        // Обработчик отправки сообщения
        ActionListener sendMessageAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = tfMessage.getText();
                if (!message.isEmpty()) {
                    logMessage("Вы: " + message);
                    tfMessage.setText("");
                }
            }
        };

        btnSend.addActionListener(sendMessageAction);
        tfMessage.addActionListener(sendMessageAction); // Отправка по нажатию Enter
    }

    private void logMessage(String message) {
        taChat.append(message + "\n");
        appendToLogFile(message);
    }

    private void appendToLogFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка записи в файл: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadChatHistory() {
        Path logPath = Paths.get(LOG_FILE);
        if (Files.exists(logPath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    taChat.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Ошибка чтения файла: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}