package com.info5059.casestudy.purchaseorder;

import com.info5059.casestudy.vendor.Vendor;
import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.product.QRCodeGenerator;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PoPDFGenerator - a class for creating dynamic purchase order output in
 * PDF format using the iText 7 library
 *
 * @author Evan
 */
public abstract class PoPDFGenerator extends AbstractPdfView {
        public static ByteArrayInputStream generatePO(String repid,
                        PurchaseOrderRepository poRepository,
                        VendorRepository vendorRepository,
                        ProductRepository productRepository)
                        throws IOException {
                URL imageUrl = PoPDFGenerator.class.getResource("/static/images/box.png");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter writer = new PdfWriter(baos);
                // Initialize PDF document to be written to a stream not a file
                PdfDocument pdf = new PdfDocument(writer);
                // Document is the main object
                Document document = new Document(pdf);
                PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                // add the image to the document
                PageSize pg = PageSize.A4;
                Image img = new Image(ImageDataFactory.create(imageUrl)).scaleAbsolute(80, 80)
                                .setFixedPosition(pg.getWidth() / 2 - 200, 750);
                document.add(img);
                // now let's add a big heading
                document.add(new Paragraph("\n\n"));
                Locale locale = new Locale("en", "US");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
                LocalDateTime dateCreated = null;
                Image qrcode;
                byte[] qrcodebin = null;
                try {
                        document.add(new Paragraph("\n"));
                        Optional<PurchaseOrder> optPo = poRepository.findById(Long.parseLong(repid));
                        if (optPo.isPresent()) {
                                PurchaseOrder po = optPo.get();
                                dateCreated = po.getPodate();
                                document.add(new Paragraph("PO# " + repid).setFont(font).setFontSize(18).setBold()
                                                .setMarginRight(pg.getWidth() / 2 - 75).setMarginTop(-10)
                                                .setTextAlignment(TextAlignment.RIGHT));
                                document.add(new Paragraph("\n\n"));
                                Optional<Vendor> optVendor = vendorRepository.findById(po.getVendorid());
                                if (optVendor.isPresent()) {
                                        Vendor vendor = optVendor.get();
                                        qrcodebin = addSummaryQRCode(vendor, po);
                                        Table table = new Table(2);
                                        table.setWidth(new UnitValue(UnitValue.PERCENT, 30));
                                        Cell cell = new Cell().add(new Paragraph("Vendor:")
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold())
                                                        .setBorder(Border.NO_BORDER)
                                                        .setTextAlignment(TextAlignment.CENTER);
                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vendor.getName())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold())
                                                        .setBorder(Border.NO_BORDER)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        table.addCell(cell);
                                        cell = new Cell().setBorder(Border.NO_BORDER);
                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vendor.getAddress1())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold())
                                                        .setBorder(Border.NO_BORDER)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        table.addCell(cell);
                                        cell = new Cell().setBorder(Border.NO_BORDER);
                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vendor.getCity())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold())
                                                        .setBorder(Border.NO_BORDER)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        table.addCell(cell);
                                        cell = new Cell().setBorder(Border.NO_BORDER);
                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vendor.getProvince())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold())
                                                        .setBorder(Border.NO_BORDER)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        table.addCell(cell);
                                        cell = new Cell().setBorder(Border.NO_BORDER);
                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vendor.getEmail())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold())
                                                        .setBorder(Border.NO_BORDER)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        table.addCell(cell);
                                        document.add(table);
                                        document.add(new Paragraph("\n\n"));
                                }
                                // table setup
                                BigDecimal subtot = new BigDecimal(0.0);
                                BigDecimal tax = new BigDecimal(0.0);
                                BigDecimal taxpercent = new BigDecimal(0.13);
                                BigDecimal tot = new BigDecimal(0.0);
                                BigDecimal ext = new BigDecimal(0.0);
                                Table table = new Table(5);
                                table.setWidth(new UnitValue(UnitValue.PERCENT, 100));
                                // table headings
                                Cell cell = new Cell().add(new Paragraph("Product Code")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Description")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Qty Sold")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Price")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Ext. Price")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                // table details
                                for (PurchaseOrderLineitem line : po.getItems()) {
                                        Optional<Product> optprd = productRepository.findById(line.getProductid());
                                        if (optprd.isPresent()) {
                                                Product product = optprd.get();
                                                cell = new Cell().add(new Paragraph(product.getId())
                                                                .setFont(font)
                                                                .setFontSize(12))
                                                                .setTextAlignment(TextAlignment.CENTER);
                                                table.addCell(cell);
                                                cell = new Cell().add(new Paragraph(product.getName())
                                                                .setFont(font)
                                                                .setFontSize(12))
                                                                .setTextAlignment(TextAlignment.CENTER);
                                                table.addCell(cell);
                                                cell = new Cell().add(new Paragraph(Integer.toString(product.getQoo()))
                                                                .setFont(font)
                                                                .setFontSize(12))
                                                                .setTextAlignment(TextAlignment.CENTER);
                                                table.addCell(cell);
                                                cell = new Cell().add(
                                                                new Paragraph(formatter.format(product.getCostprice()))
                                                                                .setFont(font)
                                                                                .setFontSize(12))
                                                                .setTextAlignment(TextAlignment.RIGHT);
                                                table.addCell(cell);
                                                ext = BigDecimal.valueOf(product.getQoo()).multiply(
                                                                product.getCostprice(),
                                                                new MathContext(8, RoundingMode.UP));
                                                cell = new Cell().add(new Paragraph(formatter.format(ext))
                                                                .setFont(font)
                                                                .setFontSize(12))
                                                                .setTextAlignment(TextAlignment.RIGHT);
                                                table.addCell(cell);
                                                subtot = subtot.add(ext, new MathContext(8, RoundingMode.UP));
                                        }
                                }
                                tax = subtot.multiply(taxpercent, new MathContext(8, RoundingMode.UP));
                                tot = subtot.add(tax, new MathContext(8, RoundingMode.UP));
                                // report subtotal, tax, total
                                cell = new Cell(1, 4).add(new Paragraph("Sub Total:"))
                                                .setBorder(Border.NO_BORDER)
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph(formatter.format(subtot)))
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell(1, 4).add(new Paragraph("Tax:"))
                                                .setBorder(Border.NO_BORDER)
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph(formatter.format(tax)))
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell(1, 4).add(new Paragraph("PO Total:"))
                                                .setBorder(Border.NO_BORDER)
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph(formatter.format(tot)))
                                                .setTextAlignment(TextAlignment.RIGHT)
                                                .setBackgroundColor(ColorConstants.YELLOW);
                                table.addCell(cell);
                                document.add(table);
                                document.add(new Paragraph("\n\n"));
                        }
                        qrcode = new Image(ImageDataFactory.create(qrcodebin)).scaleAbsolute(100, 100)
                                        .setFixedPosition(460, 60);
                        document.add(qrcode);
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
                        document.add(new Paragraph(dateFormatter.format(dateCreated))
                                        .setTextAlignment(TextAlignment.CENTER));
                        document.close();
                } catch (Exception ex) {
                        Logger.getLogger(PoPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                // finally send stream back to the controller
                return new ByteArrayInputStream(baos.toByteArray());
        }

        static byte[] addSummaryQRCode(Vendor vendor, PurchaseOrder po) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
                Locale locale = new Locale("en", "US");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
                String input = "Summary for Purchase Order:" + po.getId() + "\nDate:"
                                + dateFormatter.format(po.getPodate()) + "\nVendor:"
                                + vendor.getName()
                                + "\nTotal:" + formatter.format(po.getAmount());
                byte[] code = null;
                QRCodeGenerator qrgen = new QRCodeGenerator();
                code = qrgen.generateQRCode(input);
                return code;
        }
}