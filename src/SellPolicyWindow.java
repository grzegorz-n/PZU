import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class SellPolicyWindow extends JFrame {



    private JComboBox<Costumer> costumers;
    private JComboBox<Products> products;
    private JComboBox<Agent> agents;
    private JButton add;
    private JTextField textField;
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl date;
    private String dateString;



    public SellPolicyWindow(ReadSql readSql, int i) throws SQLException {

        setSize(200, 400);
        setLayout(new GridLayout(11, 1));

        JLabel klienci = new JLabel("Klienci");
        klienci.setPreferredSize(new Dimension(200, 100));
        add(klienci);
        costumers = new JComboBox<>();
        Costumer[] arrayC = new Costumer[readSql.loadCostumers().size()];
        readSql.loadCostumers().copyInto(arrayC);
        costumers.setModel(new DefaultComboBoxModel<>(arrayC));
        add(costumers);

        add(new JLabel("Produk"));
        products = new JComboBox<>();
        Products[] arrayP = new Products[readSql.loadProducts(i).size()];
        readSql.loadProducts(i).copyInto(arrayP);
        products.setModel(new DefaultComboBoxModel<>(arrayP));
        add(products);

        add(new JLabel("Agent"));
        agents = new JComboBox<>();
        Agent[] arrayA = new Agent[readSql.loadAgents().size()];
        readSql.loadAgents().copyInto(arrayA);
        agents.setModel(new DefaultComboBoxModel<>(arrayA));
        add(agents);

        textField = new JTextField();
        add(textField);

        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model, new Properties());
        date= new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                return null;
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar calendar = (Calendar) value;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
                    dateString = format.format(calendar.getTime());
                    return dateString;
                }
                return "";
            }
        });
        add(new JLabel("Data"));
        add(date);
        add(Box.createVerticalStrut(10));

        add = new JButton("Dodaj");
        add.setMinimumSize(new Dimension(100, 100));
        add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readSql.sellPolicy(dateString, Integer.parseInt(textField.getText()), products.getItemAt(products.getSelectedIndex()).getKod(), costumers.getItemAt(costumers.getSelectedIndex()).getKod(), agents.getItemAt(agents.getSelectedIndex()).getKod());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
