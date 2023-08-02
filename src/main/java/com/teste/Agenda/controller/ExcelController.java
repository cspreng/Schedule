package com.teste.Agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.teste.Agenda.service.ExcelService;

@Controller
public class ExcelController {
	
	private final ExcelService excelService;

    @Autowired
    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/export")
    public String exportToExcel() {
        excelService.exportDataToExcel();
        return "Export completed!";
    }

}
