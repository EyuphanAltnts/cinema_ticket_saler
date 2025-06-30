import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SinemaRezervasyon {
    private JFrame frame;
    private JPanel panel;
    private JLabel toplamödemeler;
    private int toplamödeme = 0;
    private final int Vip_Fiyat = 100;
    private final int Normal_Fiyat = 50;
    private HashMap<JButton, Integer> koltukDurumu;

    public SinemaRezervasyon() {
        frame = new JFrame("Sinema Salonu");
        panel = new JPanel(new GridLayout(5, 8, 5, 5));
        JButton[][] koltuk = new JButton[5][8];
        koltukDurumu = new HashMap<>();

        toplamödemeler = new JLabel("Toplam Fiyat: 0 TL");
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(toplamödemeler, BorderLayout.SOUTH);

        initializeSeats();

        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void initializeSeats() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                JButton koltuk = new JButton((i + 1) + "-" + (j + 1));
                koltuk.setOpaque(true);
                koltuk.setBorderPainted(true);

                if (i < 2) {
                    koltuk.setBackground(Color.CYAN);
                    koltukDurumu.put(koltuk, Vip_Fiyat);
                } else {
                    koltuk.setBackground(Color.GREEN);
                    koltukDurumu.put(koltuk, Normal_Fiyat);
                }

                koltuk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSeatClick(koltuk);
                    }
                });


                panel.add(koltuk);
            }
        }
    }

    private void handleSeatClick(JButton koltuk) {
        if (koltuk.getBackground() == Color.ORANGE) {
            int onay = JOptionPane.showConfirmDialog(frame, "Koltuk iptal edilsin mi?", "İptal", JOptionPane.YES_NO_OPTION);
            if (onay == JOptionPane.YES_OPTION) {
                int fiyat = koltukDurumu.get(koltuk);
                toplamödeme -= fiyat;
                toplamödemeler.setText("Toplam Fiyat: " + toplamödeme + " TL");

                if (fiyat == Vip_Fiyat) {
                    koltuk.setBackground(Color.CYAN);
                } else {
                    koltuk.setBackground(Color.GREEN);
                }
                koltuk.setText(koltuk.getName());
            }
        } else {
            int onay = JOptionPane.showConfirmDialog(frame, "Bu koltuğu satın almak istiyor musunuz?", "Satın Alma", JOptionPane.YES_NO_OPTION);
            if (onay == JOptionPane.YES_OPTION) {
                int fiyat = koltukDurumu.get(koltuk);
                toplamödeme += fiyat;
                toplamödemeler.setText("Toplam Fiyat: " + toplamödeme + " TL");
                koltuk.setBackground(Color.ORANGE);
                koltuk.setText("Satıldı");
            }
        }
    }

    public static void main(String[] args) {
        new SinemaRezervasyon();
    }
}
