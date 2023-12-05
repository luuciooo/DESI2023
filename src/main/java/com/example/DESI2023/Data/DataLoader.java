package com.example.DESI2023.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Cargar el script SQL desde el archivo
            ClassPathResource resource = new ClassPathResource("data.sql");
            String sqlScript = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            // Dividir el script en instrucciones SQL individuales y ejecutarlas
            String[] sqlStatements = sqlScript.split(";");

            for (String sqlStatement : sqlStatements) {
                if (!sqlStatement.trim().isEmpty()) {
                    jdbcTemplate.execute(sqlStatement);  // Ejecutar la instrucción SQL directamente
                }
            }

            System.out.println("Carga de datos exitosa");
        } catch (Exception e) {
            // Capturar y manejar cualquier excepción que ocurra durante la carga de datos.
            System.err.println("Error durante la carga de datos:");
            e.printStackTrace();
        }
    }
}
