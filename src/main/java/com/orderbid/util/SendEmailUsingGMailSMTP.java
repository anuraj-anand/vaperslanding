package com.orderbid.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class SendEmailUsingGMailSMTP {
	
	public static int mailFlag = 0;
	public static String customMail = "";
	public static float pbiMarkup = 0;

	@SuppressWarnings("unused")
	private static Font boldFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
	@SuppressWarnings("unused")
	private static Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
	public static Font redFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL,
			Color.RED);

	private static Font subFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
	private static Font normalFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
	
	
	
	
	// End of main method.
	// Disables Border for particular cell.
	private static void pdfDisableBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void pdfDisableBordersBottom(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void pdfDisableBordersTop(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.TOP);

	}

	public static PdfPCell addValues(String cellText, boolean bold,
			int fontsize, int colSpan, int height, int align, PdfPTable target,
			int tblwidth, Color color, boolean border, Color color2, Color Bck) {
		Paragraph para = new Paragraph(cellText, FontFactory.getFont(
				"Times New Roman", fontsize));

		target.setWidthPercentage(tblwidth);
		Font parafont = para.getFont();
		if (bold) {
			parafont.setStyle(Font.BOLD);

		}
		// parafont.setFamily(redFont);
		parafont.setColor(color);
		// parafont.setFamily("Courier New");
		// para.setFont(parafont);
		PdfPCell cell = new PdfPCell(para);
		cell.setHorizontalAlignment(align);
		cell.setBackgroundColor(Bck);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(colSpan);
		cell.setMinimumHeight(18);
		if (border) {
			cell.setBorderColor(color2);
		} else {
			cell.setBorder(0);
		}
		target.addCell(cell);
		return cell;
	}

	public static boolean addValue(String cellText, boolean bold, int fontsize,
			int colSpan, int height, int align, PdfPTable target, int tblwidth,
			Color color, boolean border, Color color2) {
		Paragraph para = new Paragraph(cellText, FontFactory.getFont("Courier",
				fontsize));
		target.setWidthPercentage(tblwidth);
		Font parafont = para.getFont();
		if (bold) {
			parafont.setStyle(Font.BOLD);
		}
		// parafont.setFamily(redFont);
		parafont.setColor(color);
		// parafont.setFamily("Courier New");
		// para.setFont(parafont);
		PdfPCell cell = new PdfPCell(para);
		cell.setHorizontalAlignment(align);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(colSpan);
		cell.setMinimumHeight(18);
		pdfDisableBorders(cell);
		if (border) {
			cell.setBorderColor(color2);
		} else {
			cell.setBorder(0);
		}
		target.addCell(cell);
		return false;
	}

	public static boolean AddValues(float cellText, boolean bold, int fontsize,
			int colSpan, int height, int align, PdfPTable target, int tblwidth,
			Color color, boolean border, Color color2) {
		Paragraph para = new Paragraph(cellText, "", FontFactory.getFont(
				"Courier", fontsize));
		
		/* * Paragraph para = new Paragraph(cellText,
		 * FontFactory.getFont("Courier", fontsize));*/
		 
		target.setWidthPercentage(tblwidth);
		Font parafont = para.getFont();
		if (bold) {
			parafont.setStyle(Font.BOLD);

		}
		// parafont.setFamily(redFont);
		parafont.setColor(color);
		// parafont.setFamily("Courier New");
		// para.setFont(parafont);
		PdfPCell cell = new PdfPCell(para);
		cell.setHorizontalAlignment(align);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(colSpan);
		cell.setMinimumHeight(18);
		if (border) {
			cell.setBorderColor(color2);
		} else {
			cell.setBorder(0);
		}
		target.addCell(cell);
		return false;
	}

	public static boolean addSalSlip(String cellText, boolean bold,
			int fontsize, int colSpan, int height, int align, PdfPTable target,
			int tblwidth, Color color, boolean border, Color color2) {
		Paragraph para = new Paragraph(cellText, FontFactory.getFont("Arial",
				fontsize));
		target.setWidthPercentage(tblwidth);
		Font parafont = para.getFont();
		if (bold) {
			parafont.setStyle(Font.BOLD);
			// parafont.setStyle(Font.UNDERLINE);
		}
		// parafont.setFamily(redFont);
		parafont.setColor(color);
		// parafont.setFamily("Courier New");
		// para.setFont(parafont);
		PdfPCell cell = new PdfPCell(para);
		cell.setHorizontalAlignment(align);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(colSpan);
		cell.setMinimumHeight(18);
		// cell.setBackgroundColor(color.LIGHT_GRAY);
		pdfDisableBorders(cell);
		if (border) {
			cell.setBorderColor(color2);
		} else {
			cell.setBorder(0);
		}
		target.addCell(cell);
		return false;
	}

	/**
	 * @param blankspaceTable
	 * @param i
	 * @return
	 */
	private static boolean addBlankSpace(PdfPTable target, int height) {
		target.setWidthPercentage(100);
		PdfPCell blankLineCell = new PdfPCell();
		blankLineCell.setBorder(Rectangle.NO_BORDER);
		blankLineCell.setFixedHeight(height);
		target.addCell(blankLineCell);
		return false;
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// The directory is empty delete it.
		return dir.delete();
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		SendMailLogistic();
		//SendMailEsseler();
		
		
		
	}
	
	
	
	public static void SendMailLogistic() {
		
		
		String pdfPath = "";
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		
		String reportDate = df.format(date);
		Document document = new Document();
		String dateTime=reportDate.substring(0,10);
		String timehour=reportDate.substring(11,19);
		String arr[]=timehour.split(":");
		
		String Filepdf = "Report"+arr[0]+"_" +arr[1]+"_"+arr[2]+ "-" +".pdf";
		try {
			File[] roots = File.listRoots();

			// for (int i = 0; i < roots.length; i++) {
			// //System.out.println("someDir " + roots[i]);
			// }

			File file = new File(roots[0]
					+ "PDF_ORDER_BID\\Logistic\\" +dateTime);
			if (!file.exists()) {
				if (file.mkdirs()) {
					// System.out.println("Directory is created!");
				} else {
					// System.out.println("create directory!");
				}
			}
			pdfPath = file.getAbsolutePath();
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(pdfPath + "/" + Filepdf.trim()));
			document.open();
			
			PdfPTable header = new PdfPTable(4);
			
			Image image = Image.getInstance(SendEmailUsingGMailSMTP.class
					.getResource("/Capture-1.PNG"));
			image.scaleAbsolute(100, 60f);
			image.setAlignment(5);
			
			
			PdfPTable imageTable = new PdfPTable(4);
			
			

			PdfPCell imgCell2 = new PdfPCell();

			imgCell2.addElement(image);

			imageTable.addCell(new PdfPCell(image));
			
			document.add(image);
			
			
			
			
			addSalSlip("Generated On:" + "\n"+ dateTime+ "\n"+timehour 
					+ "", false, 10,2, 15,
					Element.ALIGN_LEFT, header, 100, Color.BLACK, true,
					Color.BLACK);
			document.add(header);
			
			addSalSlip("Prepared Report For" + "\n"+"Prakash Kumar"
					+ "", false, 10,2, 15,
					Element.ALIGN_RIGHT, header, 100, Color.BLACK, true,
					Color.BLACK);
			document.add(header);
			
			
			
			
			
			
			
			
			
			
      	PdfPTable table2header = new PdfPTable(10);
			
			
			
			
		
			
			
			addValues("Total No Of Order Bidded", true, 10, 10, 5,
					Element.ALIGN_LEFT, table2header, 100, Color.BLACK,
					false, Color.BLACK, Color.LIGHT_GRAY);
			
			
			document.add(table2header);
			
			PdfPTable table2 = new PdfPTable(10);
			
			
			addValues("User Name", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			
			addValues("BID_AMOUNT", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			addValues("BID_QTY", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			addValues("BID_TIME", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			addValues("ORDER_NO", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			
			
			
			
			
			document.add(table2);
			
			
			
			PdfPTable table2data = new PdfPTable(10);
			
			
			addValues("", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2data, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			
			addValues("", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2data, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			addValues("", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2data, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			addValues("", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2data, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			addValues("", true, 10, 2, 5,
					Element.ALIGN_LEFT, table2data, 100, Color.BLACK,
					true, Color.BLACK, Color.LIGHT_GRAY);
			
			
			
			
			
			document.add(table2data);
			

			
			
				
				
			PdfPTable table3header = new PdfPTable(10);	
			addValues("No Of Order Owned", true, 10, 10, 5,
					Element.ALIGN_LEFT, table3header, 100, Color.BLACK,
					false, Color.BLACK, Color.WHITE);
			

	
			document.add(table3header);
			
				PdfPTable table3 = new PdfPTable(10);
				
				
				addValues("Order Owned By", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				
				addValues("BID_AMOUNT", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				addValues("BID_QTY", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				addValues("BID_TIME", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				addValues("ORDER_NO", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				
				
						
				
				
				document.add(table3);	
				
				
			
				
PdfPTable table3data = new PdfPTable(10);
				
				
				addValues("Order Owned By", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3data, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				
				addValues("BID_AMOUNT", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3data, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				addValues("BID_QTY", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3data, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				addValues("BID_TIME", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3data, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				addValues("ORDER_NO", true, 10, 2, 5,
						Element.ALIGN_LEFT, table3data, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				
				
						
				
				
				document.add(table3data);	
				
				
			
			
				
				//no of order bidded but not owned
				
				
				PdfPTable table4header = new PdfPTable(10);	
				addValues("No Of Order Bidded but Not Owned", true, 10, 10, 5,
						Element.ALIGN_LEFT, table4header, 100, Color.BLACK,
						true, Color.BLACK, Color.WHITE);
				

		
				document.add(table4header);
				
					PdfPTable table4 = new PdfPTable(10);
					
					
					addValues("Order Bidded By", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					
					addValues("BID_AMOUNT", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					addValues("BID_QTY", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					addValues("BID_TIME", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					addValues("ORDER_NO", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					
					
							
					
					
					document.add(table4);	
					
					
				
					
	PdfPTable table4data = new PdfPTable(10);
					
					
					addValues("Order Owned By", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4data, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					
					addValues("BID_AMOUNT", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4data, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					addValues("BID_QTY", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4data, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					addValues("BID_TIME", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4data, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					addValues("ORDER_NO", true, 10, 2, 5,
							Element.ALIGN_LEFT, table4data, 100, Color.BLACK,
							true, Color.BLACK, Color.WHITE);
					
					
							
					
					
					document.add(table4data);	
					
					
				
				

				
				
				//
			

		

			PdfPTable agentLine3 = new PdfPTable(1);
			// addBlankSpace(agentLine3,0);
			document.add(agentLine3);
			agentLine3 = new PdfPTable(1);
			agentLine3.setWidthPercentage(100);
			agentLine3.setHorizontalAlignment(Element.ALIGN_CENTER);
			// //System.out.println("Today is " + sdf.format(date) );
			addSalSlip("" + "", false, 10, 1, 15, Element.ALIGN_LEFT,
					agentLine3, 100, Color.BLACK, true, Color.BLACK);

			document.add(agentLine3);

			
			Image imagefooter = Image.getInstance(SendEmailUsingGMailSMTP.class
					.getResource("/Capture-2.PNG"));
			imagefooter.scaleAbsolute(100, 30f);
			imagefooter.setAlignment(5);
			
			PdfPTable imageTablefooter = new PdfPTable(4);
			
			

			PdfPCell imgCell2footer = new PdfPCell();
		

			imgCell2footer.addElement(imagefooter);

			imageTablefooter.addCell(new PdfPCell(imagefooter));
			
			
			
			
			document.add(imagefooter);
			

			
			
			
			
			
			document.close();
			System.err.println("Pdf GOt Generated");
			
			
			
			
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		////////Email Sending code Start Here///////////////
		
		// Recipient's email ID needs to be mentioned.
		String to = "prakashpandey032@gmail.com";// change accordingly

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		// Sender's email ID needs to be mentioned
		String from = "pk741145@gmail.com";// change accordingly
		final String username = "pk741145@gmail.com";// change accordingly
		final String password = "@1212dilip";// change accordingly
		String user = "Prakash";

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setSubject("Session Summary");
			
			String msgText1 = "THIS IS AUTO GENERATED MAIL DO NOT REPLY.....";
			// Set To: header field of the header.
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);
			MimeBodyPart mbp2 = new MimeBodyPart();

			FileDataSource fds = new FileDataSource(pdfPath + "/"
					+ Filepdf.trim());
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			msg.setContent(mp);
			msg.setSentDate(new Date());
			Transport.send(msg);

			
			
			
			
			/*BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("This is Auto Generated Mail,Please Do Not Reply");
			messageBodyPart.addHeader("Content-Type",
					"text/html; charset=windows-1255");
			messageBodyPart.addHeader("Content-Transfer-Encoding", "base64");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			msg.setSubject("Testing");
			msg.setContent(multipart);
			Transport.send(msg);*/
			// System.out.println("sucess");

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	//sending email code for 
	

	
public static void SendMailEsseler(String mail) {
		
		
		String pdfPath = "";
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		
		String reportDate = df.format(date);
		String timehour=reportDate.substring(11,19);
		

		
		////////Email Sending code Start Here///////////////
		
		// Recipient's email ID needs to be mentioned.
		String to = mail;// change accordingly

		
		// Sender's email ID needs to be mentioned
		String from = "pk741145@gmail.com";// change accordingly
		final String username = "pk741145@gmail.com";// change accordingly
		final String password = "@1212dilip";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			

			// Set To: header field of the header.
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			msg.setSubject("Session Summary");
			StringBuffer msgText1 =new StringBuffer();
			msgText1.append("<html><body><h5>"
					+ "Dear Customer,"
					+ ""+"We thank you for your Sign Up </h5></body></html>");
			// Set To: header field of the header.
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1.toString());
					
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			msg.setContent(msgText1.toString(),"text/html");
		
			msg.setSentDate(new Date());
			Transport.send(msg);

			

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
}
