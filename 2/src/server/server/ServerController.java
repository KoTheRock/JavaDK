// ServerController.java
package server.server;

import server.client.ClientGUI;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private final LogRepository logRepository;
    private final ServerView view;
    private final List<ClientGUI> clientGUIList = new ArrayList<>();
    private boolean work;

    public ServerController() {
        this.logRepository = logRepository;
        this.view = view;
    }

    public void startServer() {
        if (work) {
            view.appendLog("Сервер уже был запущен");
        } else {
            work = true;
            view.appendLog("Сервер запущен!");
        }
    }

    public void stopServer() {
        if (!work) {
            view.appendLog("Сервер уже был остановлен");
        } else {
            work = false;
            while (!clientGUIList.isEmpty()) {
                disconnectUser(clientGUIList.remove(clientGUIList.size() - 1));
            }
            view.appendLog("Сервер остановлен!");
        }
    }

    public void connectUser(ClientGUI clientGUI) {
        if (work) {
            clientGUIList.add(clientGUI);
            view.appendLog("Пользователь подключен.");
        } else {
            view.appendLog("Сервер не работает. Подключение невозможно.");
        }
    }

    public void disconnectUser(ClientGUI clientGUI) {
        clientGUIList.remove(clientGUI);
        if (clientGUI != null) {
            clientGUI.disconnectedFromServer();
        }
        view.appendLog("Пользователь отключен.");
    }

    public void sendMessage(String text) {
        if (!work) return;

        logRepository.saveLog(text);
        for (ClientGUI client : clientGUIList) {
            client.answer(text);
        }
        view.appendLog(text);
    }
}
