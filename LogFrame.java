import java.awt.*;
import java.util.*;
import javax.swing.*;



public class LogFrame extends JFrame implements ActionListener {
  private JButton sunny = new JButton("Sunny");
  private JButton cloudy = new JButton("Cloudy");

  public LogFrame() {
    cloudy.setActionCommand("Sunny");
    sunny.setActionCommand("Cloudy");
    sunny.addActionListener(sunny);
    cloudy.addActionListener(this);
  }

  public void actionPerformed(ActionEvent evt) {
    Object src = evt.getSource();
    if (src == sunny) {
      int weather = 0;
    } else if (src == cloudy) {
      int weather = 1;
    }
  }

}