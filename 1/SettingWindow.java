import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {

    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;

    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;

    private static final String FIELD_SIZE_PREFIX = "Размер поля: ";
    private static final String WIN_LENGTH_PREFIX = "Длина для победы: ";

    private JRadioButton pvc;
    private JRadioButton pvp;
    private JSlider slideFieldSize;
    private JSlider slideWinLen;
    private JLabel lbFieldSize;
    private JLabel lbWinLength;

    JButton btnStart;

    SettingWindow(GameWindow gameWindow) {
        btnStart = new JButton("Start new game");

        int mainWinX = gameWindow.getX();
        int mainWinY = gameWindow.getY();

        int mainWinWight = gameWindow.getWidth();
        int mainWinHeight = gameWindow.getHeight();

        int settingWight = WIDTH;
        int settingHeight = HEIGHT;

        int settingLocationX = mainWinX +(mainWinWight - settingWight) / 2;
        int settingLocationY = mainWinY +(mainWinHeight - settingHeight) / 2;

        setLocation(settingLocationX, settingLocationY);
        setSize(WIDTH, HEIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                int mode = pvc.isSelected() ? 0 : 1;
                int fieldSize = slideFieldSize.getValue();
                int winLen = slideWinLen.getValue();
                gameWindow.startNewGame(mode, fieldSize, fieldSize, winLen);
            }
        });

        setLayout(new GridLayout(10, 1));
        add(new JLabel("Выберите режим игры"));
        pvc = new JRadioButton("Человек против компьютера", true);
        pvp = new JRadioButton("Человек против человека");
        ButtonGroup bg = new ButtonGroup();
        bg.add(pvc);
        bg.add(pvp);
        add(pvc);
        add(pvp);

        add(new JLabel("Выберите размеры поля"));
        lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        add(lbFieldSize);
        slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int fieldSize = slideFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + slideFieldSize.getValue());


                slideWinLen.setMaximum(fieldSize);
                if (slideWinLen.getValue() > fieldSize) {
                }
                lbWinLength.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());
            }
        });
        add(slideFieldSize);

        add(new JLabel("Выберите длину для победы"));
        lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_FIELD_SIZE);
        add(lbWinLength);
        slideWinLen = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());
            }
        });
        add(slideWinLen);

        add(btnStart);
    }
}
