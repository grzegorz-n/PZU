import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditCostumerWindow extends JFrame{



    private JTextField name;

    private JTextField address;
    private JButton add;

    public EditCostumerWindow(ReadSql readSql, JList list, int kod) {

        setSize(200, 300);
        setLayout(new GridLayout(6, 1));

        add(new JLabel("Nazwa"));
        name = new JTextField();
        address = new JTextField();
        add(name);
        add(new JLabel("Adres"));
        add(address);
        add(Box.createVerticalStrut(10));
        add = new JButton("Edytuj");
        add(add);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readSql.editCostumer(name.getText(), address.getText(), kod);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    list.setModel(readSql.loadCostumers());
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