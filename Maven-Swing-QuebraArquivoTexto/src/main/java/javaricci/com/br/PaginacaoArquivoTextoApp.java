package javaricci.com.br;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PaginacaoArquivoTextoApp extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField linesInput;
    private JTextField fileInput;
    private JTextField destDirInput;
    private JButton selectFileButton;
    private JButton selectDestDirButton;
    private JButton splitFileButton;
    private JTextArea statusArea;

    private File selectedFile;
    private File destDir;

    public PaginacaoArquivoTextoApp() {
        setTitle("Paginação Arquivo de Texto Grande");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Painel para entradas
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Campo de entrada de arquivo e botão
        fileInput = new JTextField();
        fileInput.setEditable(false);
        selectFileButton = new JButton("Selecione Arquivo");
        selectFileButton.addActionListener(new SelectFileAction());

        // Linhas por entrada de arquivo
        linesInput = new JTextField();
        JLabel linesLabel = new JLabel("Linhas por arquivo dividido:");

        // Entrada e botão do diretório de destino
        destDirInput = new JTextField();
        destDirInput.setEditable(false);
        selectDestDirButton = new JButton("Selecione Diretório Destino");
        selectDestDirButton.addActionListener(new SelectDestDirAction());

        // Botão Paginar/Dividir arquivo
        splitFileButton = new JButton("Dividir Arquivo");
        splitFileButton.addActionListener(new SplitFileAction());

        // Área de status/Terminal
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        // Adicionar entradas ao painel
        inputPanel.add(new JLabel("Selecione Arquivo:"));
        inputPanel.add(fileInput);
        inputPanel.add(selectFileButton);
        inputPanel.add(linesLabel);
        inputPanel.add(linesInput);
        inputPanel.add(new JLabel("Selecione o Diretório Destino:"));
        inputPanel.add(destDirInput);
        inputPanel.add(selectDestDirButton);

        // Adicionar componentes ao quadro
        add(inputPanel, BorderLayout.NORTH);
        add(splitFileButton, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Ação para selecionar o arquivo
    private class SelectFileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileInput.setText(selectedFile.getAbsolutePath());
                statusArea.setText("Arquivo Selecionado: " + selectedFile.getName());
            }
        }
    }

    // Ação para selecionar o diretório de destino
    private class SelectDestDirAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser dirChooser = new JFileChooser();
            dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = dirChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                destDir = dirChooser.getSelectedFile();
                destDirInput.setText(destDir.getAbsolutePath());
                statusArea.append("\nDiretório Destino Selecionado: " + destDir.getAbsolutePath());
            }
        }
    }

    // Ação para dividir o arquivo
    private class SplitFileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedFile == null || destDir == null || linesInput.getText().isEmpty()) {
                statusArea.append("\nSelecione um Arquivo, Diretório de Destino e informe o Número de Linhas.");
                return;
            }

            int linesPerFile;
            try {
                linesPerFile = Integer.parseInt(linesInput.getText());
            } catch (NumberFormatException ex) {
                statusArea.append("\nNúmero inválido de linhas.");
                return;
            }

            splitFile(selectedFile, linesPerFile, destDir);
        }
    }

    // Método para dividir o arquivo
    private void splitFile(File file, int linesPerFile, File destination) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            int filePart = 1;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                lineCount++;

                if (lineCount == linesPerFile) {
                    writePartFile(destination, lines, filePart);
                    lines.clear();
                    lineCount = 0;
                    filePart++;
                }
            }

            if (!lines.isEmpty()) {
                writePartFile(destination, lines, filePart);
            }

            statusArea.append("\nArquivo dividido em " + filePart + " partes com sucesso.");
        } catch (IOException ex) {
            statusArea.append("\nErro ao dividir arquivo: " + ex.getMessage());
        }
    }

    // Método para escrever os arquivos de partes divididas
    private void writePartFile(File destination, List<String> lines, int partNumber) throws IOException {
        String fileName = Paths.get(destination.getAbsolutePath(), "Arquivo_Paginado_" + partNumber + ".txt").toString();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        }
        statusArea.append("\nParte " + partNumber + " salvo como " + fileName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaginacaoArquivoTextoApp());
    }
}
