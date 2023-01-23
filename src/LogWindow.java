import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LogWindow extends JFrame {
    private JButton add;
    private JButton load;
    private JButton delate;
    private JButton edit;
    private JList towU;
    private JPanel left;

    public LogWindow(String text, ReadSql readSql) throws SQLException {
        super(text);

        add = new JButton("Dodaj");
        load = new JButton("Załaduj");
        delate = new JButton("Usuń");
        edit = new JButton("Edytuj");
        towU = new JList(readSql.loadTowUbe());
        left = new JPanel();

        setSize(300, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTowUbeWindow(readSql, towU);

            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainWindow(((TowUbe) towU.getSelectedValue()).getId(), readSql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        delate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readSql.deleteTowUbe(((TowUbe) towU.getSelectedValue()).getId());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    towU.setModel(readSql.loadTowUbe());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditTowUbeWindow(((TowUbe) towU.getSelectedValue()).getId(), readSql, towU);
            }
        });

        left.setLayout(new GridLayout(6,1, 0,20));
        left.add(Box.createVerticalStrut(5));
        left.add(add);
        left.add(load);
        left.add(delate);
        left.add(edit);
        left.add(Box.createVerticalStrut(5));
        add(left, BorderLayout.LINE_START);

        add(new JScrollPane(towU), BorderLayout.CENTER);

        setVisible(true);










    }



}
