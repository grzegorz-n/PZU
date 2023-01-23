
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class FindPolicyWindow extends JFrame {




    private JComboBox<Costumer> costumers;
    private JComboBox<Products> products;
    private JComboBox<Agent> agents;
    private JButton find;
    JList<Policy> result;



    public FindPolicyWindow(ReadSql readSql, int i) throws SQLException {

        setSize(200, 400);
        setLayout(new GridLayout(8, 1));

        JLabel klienci = new JLabel("Wybierz klienta");
        klienci.setPreferredSize(new Dimension(200, 100));
        add(klienci);
        costumers = new JComboBox<>();
        Costumer[] arrayC = new Costumer[readSql.loadCostumers().size()];
        readSql.loadCostumers().copyInto(arrayC);
        costumers.setModel(new DefaultComboBoxModel<>(arrayC));
        add(costumers);

        add(new JLabel("Wybierz produk"));
        products = new JComboBox<>();
        Products[] arrayP = new Products[readSql.loadProducts(i).size()];
        readSql.loadProducts(i).copyInto(arrayP);
        products.setModel(new DefaultComboBoxModel<>(arrayP));
        add(products);

        add(new JLabel("Wybierz Agenta"));
        agents = new JComboBox<>();
        Agent[] arrayA = new Agent[readSql.loadAgents().size()];
        readSql.loadAgents().copyInto(arrayA);
        agents.setModel(new DefaultComboBoxModel<>(arrayA));
        add(agents);


        find = new JButton("Pokaż");
        add(find);
        result = new JList<>();
        add(new JScrollPane(result), BorderLayout.CENTER);
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    result.setModel(readSql.loadPolicy(products.getItemAt(products.getSelectedIndex()).getKod(), costumers.getItemAt(costumers.getSelectedIndex()).getKod(), agents.getItemAt(agents.getSelectedIndex()).getKod()));
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
