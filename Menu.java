import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Menu extends JFrame {
  public Menu() {
    setTitle("Choix du jeu");
    ImageIcon img = new ImageIcon("images/menu.png");
    setIconImage(img.getImage());
    setSize(700, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    JButton dominosButton = new JButton("Dominos");
    buttonPanel.add(dominosButton);

    JButton carcassonneButton = new JButton("Carcassonne");
    buttonPanel.add(carcassonneButton);

    JButton dominotermButton = new JButton("Dominos Terminal");
    buttonPanel.add(dominotermButton);

    add(buttonPanel, BorderLayout.NORTH);

    JPanel playerSelectionPanel = new JPanel();
    playerSelectionPanel.setLayout(new FlowLayout());

    JLabel label = new JLabel("Nombre de joueurs : ");
    playerSelectionPanel.add(label);

    String[] playerOptions = { "2", "3", "4" };
    JComboBox<String> playerComboBox = new JComboBox<>(playerOptions);
    playerSelectionPanel.add(playerComboBox);

    JLabel label2 = new JLabel("Nombre de bots parmi les joueurs : ");
    playerSelectionPanel.add(label2);
    String [] playerOptions2 = new String[playerOptions.length+1];
    for(int i=0;i<playerOptions2.length;i++){
        playerOptions2[i] = String.valueOf(i);
    }
    JComboBox<String> playerComboBox2 = new JComboBox<>(playerOptions2);
    playerSelectionPanel.add(playerComboBox2);

    add(playerSelectionPanel, BorderLayout.SOUTH);


    dominosButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String numPlayers = (String) playerComboBox.getSelectedItem();
        String numBots = (String) playerComboBox2.getSelectedItem();
        int players = Integer.parseInt(numPlayers);
        int bots = Integer.parseInt(numBots);
        if(players<=bots){
          JOptionPane.showMessageDialog(null, "Il y a autant ou plus de bots que de joueurs");
        }else{
          ViewDomino view = new ViewDomino(players, bots, new ModelDomino(players));
          view.setVisible(true);
        }

      }
    });
    carcassonneButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String numPlayers = (String) playerComboBox.getSelectedItem();
        int players = Integer.parseInt(numPlayers);
        String numBots = (String) playerComboBox2.getSelectedItem();
        int bots = Integer.parseInt(numBots);
        if(players<=bots){
          JOptionPane.showMessageDialog(null, "Il y a autant ou plus de bots que de joueurs");
        }else{
            ViewCarcassonne view = new ViewCarcassonne(players, bots, new ModelCarcassonne(players));
            view.setVisible(true);
        }
      }
    });
    dominotermButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String numPlayers = (String) playerComboBox.getSelectedItem();
        int players = Integer.parseInt(numPlayers);
        String numBots = (String) playerComboBox2.getSelectedItem();
        int bots = Integer.parseInt(numBots);
        if(players<=bots){
          JOptionPane.showMessageDialog(null, "Il y a autant ou plus de bots que de joueurs");
        }else{
            DominoTerm dominoterm = new DominoTerm(players, bots, new ModelDomino(players));
            dispose();
        }
      }
    });
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    Menu menu = new Menu();
    menu.setVisible(true);
  }
}