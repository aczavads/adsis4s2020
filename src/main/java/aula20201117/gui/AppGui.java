package aula20201117.gui;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppGui implements CommandLineRunner {
    @Autowired
    private EditarProdutoDialog editarProdutoDialog; 


    public static void main(String[] args) {
        new SpringApplicationBuilder(AppGui.class)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(args)
            .close();
    }

    @Override
    public void run(String... args) throws Exception {
        editarProdutoDialog.setVisible(true);
    }

}
