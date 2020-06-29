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

    public PDF() {
    }

    public void crearPDF(String nombre, String producto, int cantidad, int precio) throws FileNotFoundException, DocumentException {
        Document documento = new Document();

        FileOutputStream ficheroPDF = new FileOutputStream(nombre+".pdf");

        PdfWriter.getInstance(documento, ficheroPDF);

        documento.open();

        Paragraph titulo = new Paragraph("Factura \n\n", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLUE));
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell("Nombre");
        tabla.addCell("Producto");
        tabla.addCell("Cantidad");
        tabla.addCell("Precio Total");
        tabla.addCell(nombre);
        tabla.addCell(producto);
        tabla.addCell(String.valueOf(cantidad));
        tabla.addCell(String.valueOf(precio));

        documento.add(tabla);

        documento.close();

    }
}
