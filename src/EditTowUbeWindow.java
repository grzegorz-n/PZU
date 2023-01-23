import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditTowUbeWindow extends JFrame {
    private JTextField name;
    private JButton edit;

    public EditTowUbeWindow(int i, ReadSql readSql, JList towU) {

        setSize(200, 300);
        setLayout(new GridLayout(4,1));

        add(Box.createVerticalStrut(10));
        name = new JTextField();
        add(name);
        add(Box.createVerticalStrut(10));
        edit = new JButton("Edytuj");
        add(edit);

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readSql.editTowUbe(name.getText(),i);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
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
