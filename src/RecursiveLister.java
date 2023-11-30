import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveLister extends JFrame {
    private JButton startButton;
    private JButton quitButton;
    private JTextArea fileTextArea;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public RecursiveLister() {
        super("Recursive Lister");

        setLayout(new BorderLayout());

        titleLabel = new JLabel("Recursive Lister");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        fileTextArea = new JTextArea();
        fileTextArea.setEditable(false);

        scrollPane = new JScrollPane(fileTextArea);
        add(scrollPane, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = fileChooser.showOpenDialog(RecursiveLister.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    listFiles(selectedDirectory);
                }
            }
        });

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the JFrame and exit the program
                dispose();
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void listFiles(File directory) {
        fileTextArea.setText("");

        if (directory != null && directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        listFiles(file);
                    } else {
                        fileTextArea.append(file.getAbsolutePath() + "\n");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RecursiveLister();
            }
        });
    }
}