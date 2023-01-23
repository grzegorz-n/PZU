import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddTowUbeWindow extends JFrame {

    private JTextField name;
    private JButton add;

    public AddTowUbeWindow(ReadSql readSql, JList towU) {

        setSize(200, 300);
        setLayout(new GridLayout(4,1));

        add(Box.createVerticalStrut(10));
        name = new JTextField();
        add(name);
        add(Box.createVerticalStrut(10));
        add = new JButton("Dodaj");
        add(add);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readSql.addTowUbe(name.getText());
                    towU.setModel(readSql.loadTowUbe());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
