package com.moh.utils;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class PopulatePDF {

    public static void main( String[] args ) {
        try {
            PdfReader reader = new PdfReader( "rr_test.pdf" );
            PdfStamper stamper = new PdfStamper( reader, new FileOutputStream( "rr_stamped.pdf" ) );

            AcroFields form = stamper.getAcroFields();

            form.setField( "firstName", "John" );
            form.setField( "lastName", "Smith" );

            PdfContentByte content = stamper.getUnderContent( 1 );

            PdfPTable table = new PdfPTable( 3 );

            Font fontHeader = new Font( Font.HELVETICA, 12, Font.BOLD, new Color( 0, 0, 0 ) );
            Font fontBody = new Font( Font.HELVETICA, 10, Font.NORMAL, new Color( 0, 0, 0 ) );

            Phrase phrase1 = new Phrase( "Service Types" );
            phrase1.setFont( fontHeader );
            Phrase phrase2 = new Phrase( "# Rotations" );
            phrase2.setFont( fontHeader );
            Phrase phrase3 = new Phrase( "# of Weeks" );
            phrase3.setFont( fontHeader );

            PdfPCell cell1 = new PdfPCell( phrase1 );
            PdfPCell cell2 = new PdfPCell( phrase2 );
            PdfPCell cell3 = new PdfPCell( phrase3 );

            Color color = new Color( 236, 236, 236 );
            cell1.setBackgroundColor( color );
            cell2.setBackgroundColor( color );
            cell3.setBackgroundColor( color );

            table.addCell( cell1 );
            table.addCell( cell2 );
            table.addCell( cell3 );

            table.setHeaderRows( 1 );
            table.setFooterRows( 0 );

            //loop here
            Phrase data1 = new Phrase( "Emergency Care" );
            data1.setFont( fontBody );

            PdfPCell cellData1 = new PdfPCell( data1 );
            table.addCell( cellData1 );
            table.addCell( "7" );
            table.addCell( "6" );

            ColumnText ct = new ColumnText( content );
            ct.addElement( table );
            ct.setSimpleColumn( 36, 400, PageSize.A4.getWidth() - 36, PageSize.A4.getHeight() - 400, 18, Element.ALIGN_JUSTIFIED );
            ct.go();

            stamper.setFormFlattening( true );

            stamper.close();
        }
        catch( IOException ioex ) {
            System.out.println( ioex );
        }
        catch( DocumentException dex ) {
            System.out.println( dex );
        }
    }
}
