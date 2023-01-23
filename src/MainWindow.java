import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainWindow extends JFrame {
    private JList list;
    private JPanel left;
    private JPanel left1;
    private JPanel left2;
    private JPanel left3;
    private JRadioButton costumers;
    private JRadioButton products;
    private JRadioButton agents;
    private JButton add;
    private JButton edit;
    private JButton delete;
    private JButton sell;
    private JButton find;



    public MainWindow(int i, ReadSql readSql) throws SQLException {
        setSize(600, 450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        list = new JList(readSql.loadCostumers());

        left = new JPanel();
        left1 = new JPanel();
        left2 = new JPanel();
        left3 = new JPanel();

        costumers = new JRadioButton("Klienci");
        products = new JRadioButton("Produkty");
        agents = new JRadioButton("Agenci");

        costumers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    list.setModel(readSql.loadCostumers());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    list.setModel(readSql.loadProducts(i));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        agents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    list.setModel(readSql.loadAgents());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(costumers);
        bg.add(products);
        bg.add(agents);

        add = new JButton("Dodaj");
        edit = new JButton("Edytuj");
        delete = new JButton("Usuń");
        sell = new JButton("Sprzedaj Polisę");
        find = new JButton("Znajdż Polisę");

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (costumers.isSelected()) {
                    new AddCostumerWindow(readSql, list);
                } else if (products.isSelected()) {
                    new AddProductWindow(readSql, list, i);
                } else if (agents.isSelected()) {
                    new AddAgentWindow(readSql, list);
                }
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (costumers.isSelected()) {
                    new EditCostumerWindow(readSql, list, ((Costumer) list.getSelectedValue()).getKod());
                } else if (products.isSelected()) {
                    new EditProductWindow(readSql,list, ((Products) list.getSelectedValue()).getKod(), i);
                } else if (agents.isSelected()) {
                    new EditAgentWindow(readSql, list, ((Agent) list.getSelectedValue()).getKod());
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (costumers.isSelected()) {
                    try {
                        readSql.deleteCostumer(((Costumer) list.getSelectedValue()).getKod());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        list.setModel(readSql.loadCostumers());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (products.isSelected()) {
                    try {
                        readSql.deleteProducts(((Products) list.getSelectedValue()).getKod());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        list.setModel(readSql.loadProducts(i));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (agents.isSelected()) {
                    try {
                        readSql.deleteAgent(((Agent) list.getSelectedValue()).getKod());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        list.setModel(readSql.loadAgents());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SellPolicyWindow(readSql, i);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new FindPolicyWindow(readSql, i);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        left1.setLayout(new GridLayout(3,1));
        left1.add(costumers);
        left1.add(products);
        left1.add(agents);

        left2.setLayout(new GridLayout(2,2));
        left2.add(add);
        left2.add(edit);
        left2.add(delete);

        left3.setLayout(new GridLayout(2,1));
        left3.add(sell);
        left3.add(find);

        left.setLayout(new GridLayout(3,1));
        left.add(left1);
        left.add(left2);
        left.add(left3);
        add(left, BorderLayout.LINE_START);


        add(new JScrollPane(list), BorderLayout.CENTER);

        setVisible(true);
    }


}
