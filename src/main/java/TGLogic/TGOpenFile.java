package TGLogic;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TGOpenFile extends JFrame{
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Excel", "xlsx");
    private  JButton  btnOpenDir    = null;

    private  JFileChooser fileChooser = null;
    String path ;

    private final String[][] FILTERS = {{"xlsx"}};
    public TGOpenFile() {
        super("Пример FileChooser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Кнопка создания диалогового окна для выбора директории
        btnOpenDir = new JButton("Открыть директорию");

        // Создание экземпляра JFileChooser
        fileChooser = new JFileChooser();
        // Подключение слушателей к кнопкам
        addFileChooserListeners();

        // Размещение кнопок в интерфейсе
        JPanel contents = new JPanel();
        contents.add(btnOpenDir   );
        setContentPane(contents);
        // Вывод окна на экран
        setSize(360, 110);
        setVisible(true);
    }

    private void addFileChooserListeners() {
        btnOpenDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                fileChooser.setFileFilter(filter);
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(TGOpenFile.this);
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION )
                    JOptionPane.showMessageDialog(TGOpenFile.this,
                            "Файл был выбран");
                setPather(fileChooser.getSelectedFile().getPath());
                System.out.println(getPather());
            }

        });
    }
    public String getPather() {
        return path;
    }

    public void setPather(String path) {
        this.path = path;
    }

}
