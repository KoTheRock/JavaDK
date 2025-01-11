package server.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerView extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private JTextArea log;
    private JButton btnStart;
    private JButton btnStop;
    private final ServerController controller;

    public ServerView(ServerController controller) {
        this.controller = controller;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startServer();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopServer();
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    public void appendLog(String text) {
        log.append(text + "\n");
    }
}