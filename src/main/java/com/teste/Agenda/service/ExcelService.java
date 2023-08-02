package com.teste.Agenda.service;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.teste.Agenda.entities.Clientes;
import com.teste.Agenda.respository.DataRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelService {
	
	private final DataRepository dataRepository;

    @Autowired
    public ExcelService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
    
    @Scheduled(cron = "0 55 05 * * *") // Executes at 5:55 AM daily
    public void exportDataToExcel() {
        List<Clientes> clientesList = dataRepository.selectDate(LocalDate.now().minusDays(1));

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Cliente");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Creation Date");
            headerRow.createCell(2).setCellValue("Address");
            headerRow.createCell(3).setCellValue("Document");
            
         // Populate data rows
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            int rowNum = 1;
            for (Clientes cliente : clientesList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cliente.getId());
                row.createCell(1).setCellValue(dateFormatter.format(cliente.getCreate_date()));
                row.createCell(2).setCellValue(cliente.getAdress());
                row.createCell(3).setCellValue(cliente.getDocument());
            }

            // Write to Excel file
            try (FileOutputStream fileOut = new FileOutputStream("clientes.xlsx")) {
                workbook.write(fileOut);
                fileOut.close();
                System.out.println("Arquivo Excel criado com sucesso!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado!");
        }	catch (IOException e) {
        	e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }
    }
                
            
            
                


}
