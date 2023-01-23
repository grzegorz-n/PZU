import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditProductWindow extends JFrame {


    private JTextField name;
    private JButton add;

    public EditProductWindow(ReadSql readSql, JList towU, int id, int i) {

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
                    readSql.editProducts(name.getText(), id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    towU.setModel(readSql.loadProducts(i));
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
