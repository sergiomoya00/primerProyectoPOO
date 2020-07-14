/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author jabre
 */
public class PDF {
    
    /**
     *
     * Constructor de clase vacío
     */

    public PDF() {
    }
    
    /**
     *
     * Método para crear el PDF
     * @param name Parametro que da como entrada el distrito
     * @param product Parametro que da como entrada el producto
     * @param quantity Parametro que da como entrada la cantidad de producto
     * @param price Parametro que da como entrada el precio del producto
     * @throws java.io.FileNotFoundException
     * @throws com.itextpdf.text.DocumentException
     */

    public void crearPDF(String name, String product, int quantity, int price) throws FileNotFoundException, DocumentException {
        Document documento = new Document();

        FileOutputStream ficheroPDF = new FileOutputStream(name+".pdf");

        PdfWriter.getInstance(documento, ficheroPDF);

        documento.open();

        Paragraph titulo = new Paragraph("Factura \n\n", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLUE));
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell("Nombre");
        tabla.addCell("Producto");
        tabla.addCell("Cantidad");
        tabla.addCell("Precio Total");
        tabla.addCell(name);
        tabla.addCell(product);
        tabla.addCell(String.valueOf(quantity));
        tabla.addCell(String.valueOf(price));

        documento.add(tabla);

        documento.close();

    }
}
