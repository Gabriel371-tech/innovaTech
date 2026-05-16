package br.com.faculdadeinovatech.inovatech.service;

import br.com.faculdadeinovatech.inovatech.entity.Aluno;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

@Service
public class RelatorioService {

    public void exportarAlunosPdf(HttpServletResponse response, List<Aluno> alunos) throws IOException {
        generatePdf(response, alunos, "RELATÓRIO GERAL DE ALUNOS");
    }

    public void exportarFichaAlunoPdf(HttpServletResponse response, Aluno aluno) throws IOException {
        generatePdf(response, List.of(aluno), "FICHA INDIVIDUAL DO ALUNO");
    }

    private void generatePdf(HttpServletResponse response, List<Aluno> alunos, String title) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        
        writer.setPageEvent(new PdfPageEventHelper() {
            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                        new Phrase("Página " + writer.getPageNumber() + " - Faculdade Inovatech", FontFactory.getFont(FontFactory.HELVETICA, 8)),
                        (document.right() - document.left()) / 2 + document.leftMargin(),
                        document.bottom() - 10, 0);
            }
        });

        document.open();
        
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.GRAY);
        Paragraph headerInfo = new Paragraph("Faculdade Inovatech - Sistema de Gestão Acadêmica", fontHeader);
        headerInfo.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(headerInfo);
        
        Paragraph dateInfo = new Paragraph("Emissão: " + new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()), 
                FontFactory.getFont(FontFactory.HELVETICA, 10, Color.GRAY));
        dateInfo.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(dateInfo);

        document.add(new Paragraph(" "));

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, new Color(0, 51, 102));
        Paragraph p = new Paragraph(title, fontTitle);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p.setSpacingAfter(20);
        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 3.5f, 3.0f, 2.0f, 2.0f, 2.0f, 2.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, alunos);

        document.add(table);

        if (alunos.size() == 1) {
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph signature = new Paragraph("__________________________________________\nAssinatura da Secretaria Acadêmica", 
                    FontFactory.getFont(FontFactory.HELVETICA, 10));
            signature.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(signature);
        }

        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(0, 51, 102));
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        font.setSize(11);

        String[] headers = {"ID", "Nome", "Email", "Telefone", "CPF", "Cidade", "Curso"};
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }

    private void writeTableData(PdfPTable table, List<Aluno> alunos) {
        Font fontData = FontFactory.getFont(FontFactory.HELVETICA, 10);
        boolean zebra = false;
        
        for (Aluno aluno : alunos) {
            Color bgColor = zebra ? new Color(240, 240, 240) : Color.WHITE;
            
            addCell(table, String.valueOf(aluno.getIdAluno()), fontData, bgColor);
            addCell(table, aluno.getNomeAluno(), fontData, bgColor);
            addCell(table, aluno.getEmailAluno(), fontData, bgColor);
            addCell(table, aluno.getTelefoneAluno(), fontData, bgColor);
            addCell(table, aluno.getCpfAluno(), fontData, bgColor);
            addCell(table, aluno.getCidadeAluno(), fontData, bgColor);
            addCell(table, aluno.getCurso() != null ? aluno.getCurso().getNomeCurso() : "N/A", fontData, bgColor);
            
            zebra = !zebra;
        }
    }

    private void addCell(PdfPTable table, String text, Font font, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text != null ? text : "", font));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }
}
