import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class Login extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;

    Login() {
        userLabel = new JLabel();
        userLabel.setText("    Username :");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("    Password :");
        textField2 = new JPasswordField(8);
        b1 = new JButton("   SUBMIT   ");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);
        setTitle("Login Form");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if (!passValue.equals(""))
            new OnlineTestBegin(userValue);
        else {
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }
}

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton b1, b2, log;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer = new Timer();

    OnlineTestBegin(String s) {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Save and Next");
        b2 = new JButton("Save for later");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        l.setBounds(30, 40, 450, 20);
        l1.setBounds(20, 20, 450, 20);
        jb[0].setBounds(50, 80, 100, 20);
        jb[1].setBounds(50, 110, 100, 20);
        jb[2].setBounds(50, 140, 100, 20);
        jb[3].setBounds(50, 170, 100, 20);
        b1.setBounds(95, 240, 140, 30);
        b2.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 600;

            public void run() {
                l1.setText("Time left: " + i);
                i--;
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Out");
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 9) {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Save for later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "Score =" + count);
            System.exit(0);
        }
    }

    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText("Que1: Which game engine is widely used for 2D game development?");
            jb[0].setText("Unity");jb[1].setText("Unreal Engine");jb[2].setText("Godot");jb[3].setText("GameMaker Studio");
        }
        if (current == 1) {
            l.setText("Que2: What programming language is commonly used in game development?");
            jb[0].setText("Java");jb[1].setText("C++");jb[2].setText("Python");jb[3].setText("Ruby");
        }
        if (current == 2) {
            l.setText("Que3: Which platform is known for indie game development?");
            jb[0].setText("PlayStation");jb[1].setText("Xbox");jb[2].setText("Steam");jb[3].setText("Nintendo Switch");
        }
        if (current == 3) {
            l.setText("Que4: What is the term for the process of creating 3D models for games?");
            jb[0].setText("Modeling");jb[1].setText("Texturing");jb[2].setText("Rendering");jb[3].setText("Animation");
        }
        if (current == 4) {
            l.setText("Que5: Which of the following is not a game development tool?");
            jb[0].setText("Unity");jb[1].setText("Blender");jb[2].setText("Photoshop");jb[3].setText("Eclipse");
        }
        if (current == 5) {
            l.setText("Que6: What is a game engine?");
            jb[0].setText("A type of video game");jb[1].setText("Software used to develop video games");jb[2].setText("A puzzle in a game");jb[3].setText("A virtual reality headset");
        }
        if (current == 6) {
            l.setText("Que7: Which of the following is an example of a game genre?");
            jb[0].setText("Database Management");jb[1].setText("Platformer");jb[2].setText("Spreadsheets");jb[3].setText("Word Processing");
        }
        if (current == 7) {
            l.setText("Que8: In game development, what does AI stand for?");
            jb[0].setText("Artificial Intelligence");jb[1].setText("Active Interface");jb[2].setText("Advanced Inventory");jb[3].setText("All Instances");
        }
        if (current == 8) {
            l.setText("Que9: Which company developed the game 'Minecraft'?");
            jb[0].setText("Electronic Arts");jb[1].setText("Ubisoft");jb[2].setText("Mojang");jb[3].setText("Epic Games");
        }
        if (current == 9) {
            l.setText("Que10: What is the name of the first video game ever created?");
            jb[0].setText("Pong");jb[1].setText("Space Invaders");jb[2].setText("Pac-Man");jb[3].setText("Tetris");
        }
        l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);
    }

    boolean check() {
        if (current == 0)
            return (jb[2].isSelected());
        if (current == 1)
            return (jb[1].isSelected());
        if (current == 2)
            return (jb[2].isSelected());
        if (current == 3)
            return (jb[0].isSelected());
        if (current == 4)
            return (jb[3].isSelected());
        if (current == 5)
            return (jb[1].isSelected());
        if (current == 6)
            return (jb[1].isSelected());
        if (current == 7)
            return (jb[0].isSelected());
        if (current == 8)
            return (jb[2].isSelected());
        if (current == 9)
            return (jb[0].isSelected());
        return false;
    }
}

public class OnlineExamSystem {
    public static void main(String args[]) {
        try {
            Login form = new Login();
            form.setSize(400, 150);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
