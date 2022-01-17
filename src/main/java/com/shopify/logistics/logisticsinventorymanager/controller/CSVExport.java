package com.shopify.logistics.logisticsinventorymanager.controller;

import com.google.common.net.HttpHeaders;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.shopify.logistics.logisticsinventorymanager.entity.Item;
import com.shopify.logistics.logisticsinventorymanager.service.ItemService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/export")
@Api("Export inventory items as CSV")
public class CSVExport {
    private static final Logger LOGGER = LogManager.getLogger(CSVExport.class);

    @Autowired
    private ItemService itemService;

    @GetMapping
    public void exportToCSV(HttpServletResponse response) throws Exception {
        LOGGER.debug("Trying to create CSV Export");

        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        String filename = "inventory-snapshot-"+date+".csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        StatefulBeanToCsv<Item> writer =
                new StatefulBeanToCsvBuilder
                        <Item>(response.getWriter())
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                        .withOrderedResults(false).build();

        // write all employees to csv file
        LOGGER.debug("Writing to file");
        writer.write(itemService.getAllItems());
    }
}
