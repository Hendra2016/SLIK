//package common.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.apache.commons.io.IOUtils;
//
//import com.maybank.dto.StpCustApplicationDto;
//import com.maybank.dto.StpMailReminderDto;
//import com.maybank.dto.StpTaskDto;
//import com.maybank.model.StpCekDokumen;
//
//public class Email {
//
//	private StpCustApplicationDto stpCustApplicationDto;
//	private StpTaskDto stpTaskDto;
//	private String email;
//
//	public Session getSessionMail() {
//		final String username = "stp";
//		final String password = "Maybank1!";
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "false");
//		props.put("mail.smtp.host", "10.225.16.153");
//		props.put("mail.smtp.port", "25");
//
//		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});
//		return session;
//	}
//
//	public void sendingEmailNewOpeningAccount(final StpCustApplicationDto custAppData, final StpMailReminderDto smrData,
//			final Integer current_step_val, final String files, String url) {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress("stp@biilab.net"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(custAppData.getEmail()));
//			message.setSubject("Pembukaan Rekening Baru");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\" + files + ".html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%nama", custAppData.getFullName())
//						.replace("%tglonline", "<strong>" + formatDate(custAppData.getCreatedDate()) + "</strong>")
//						.replace("%link", url + custAppData.getApplicationID() + "/" + smrData.getHashCode() + "")
//						.replace("%appid", "<strong>" + custAppData.getApplicationID() + "</strong>");
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void sendingEmailComplete(final StpCustApplicationDto custAppData, final String files) {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress("stp@biilab.net"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(custAppData.getEmail()));
//			message.setSubject("Pembukaan Rekening Baru");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\" + files + ".html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%nama", custAppData.getFullName())
//						.replace("%tglonline", "<strong>" + formatDate(custAppData.getCreatedDate()) + "</strong>")
//						.replace("%appid", "<strong>" + custAppData.getApplicationID() + "</strong>");
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void sendingTask7(final StpCekDokumen dto, String url) {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject("Kelengkapan Data Tidak Sesuai");
//			try {
//				FileInputStream file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\task7.html"));
//				String kata = IOUtils.toString(file);
//				String appl = cekDokument(dto);
//				kata = kata.replace("%customerName", stpCustApplicationDto.getFullName())
//						.replace("%applID", stpTaskDto.getApplicationID())
//						.replace("%dateAppl", formatDate(stpTaskDto.getTaskCreatedDate()))
//						.replace("%listKelengkapan", appl).replace("%url", url);
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private String cekDokument(StpCekDokumen dto) {
//		String kata = "";
//		if (dto.getKtp() == 1) {
//			kata += "<li> KTP </li>";
//		}
//		if (dto.getAkta() == 1) {
//			kata += "<li> Akta Lahir </li>";
//		}
//		if (dto.getKia() == 1) {
//			kata += "<li> KIA </li>";
//		}
//		if (dto.getKkBo() == 1) {
//			kata += "<li> Kartu Keluarga Orang Tua </li>";
//		}
//		if (dto.getKtpBo() == 1) {
//			kata += "<li> KTP Orang Tua </li>";
//		}
//		if (dto.getNpwp() == 1) {
//			kata += "<li> NPWP </li>";
//		}
//		if (dto.getNpwpBo() == 1) {
//			kata += "<li> NPWP Orang Tua </li>";
//		}
//		if (dto.getSpwBo() == 1) {
//			kata += "<li> SPW Orang Tua </li>";
//		}
//		if (dto.getTtd() == 1) {
//			kata += "<li> Tanda Tangan </li>";
//		}
//		return kata;
//	}
//
//	// RE_SCHEDULE
//	public void sendingReScheduleTask(final String url) {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject("Re-Schedule Skype");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\re-schedule.html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%customerName", stpCustApplicationDto.getFullName())
//						.replace("%dateAppl", formatDate(stpTaskDto.getTaskCreatedDate())).replace("%url", url);
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void sendingEmailApplication() {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject(stpTaskDto.getTaskStatusName() + " Letter");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\customer-email.html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%customerName", stpCustApplicationDto.getFullName())
//						.replace("%applID", stpTaskDto.getApplicationID())
//						.replace("%dateAppl", formatDate(stpTaskDto.getTaskCreatedDate()))
//						.replace("%status", stpTaskDto.getTaskStatusName());
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	// UNTUK SENDING KIRIM ID SKYPE KE CUSTOMER
//	public void sendingSkypeIdByEmail() {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject("Invitation Letter");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\skypeEmail.html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%tgl", formatDate(stpCustApplicationDto.getCreatedDate()))
//						.replace("%customerName", stpCustApplicationDto.getFullName())
//						.replace("%hari", getCurrentDay(stpCustApplicationDto.getVideoCallDate()))
//						.replace("%videoCall", formatDate(stpCustApplicationDto.getVideoCallDate()))
//						.replace("%showTime", stpCustApplicationDto.getStpVideoCallTimeDto().getCallTime());
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	// UNTUK KIRIM EMAIL PENOLAKAN TASK 1-4
//	public void sendingEmail14() {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject("Rejection Letter");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\task14.html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%customerName", stpCustApplicationDto.getFullName()).replace("%Appl",
//						formatDate(stpTaskDto.getTaskCreatedDate()));
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	// UNTUK KIRIM EMAIL PENOLAKAN TASK 5-6 di tanya
//	public void sendingEmail56() {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject("Reject Letter");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File("E:\\E-MAIL_TEMPLATE\\task56.html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%customerName", stpCustApplicationDto.getFullName()).replace("%Appl",
//						formatDate(stpTaskDto.getTaskCreatedDate()));
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	// UNTUK KIRIM EMAIL PENOLAKAN SKYPE
//	public void sendingEmailSkypeRejection() {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject("Penolakan Skype Data Tidak Sesuai");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File(
//						"E:\\E-MAIL_TEMPLATE\\Email penolakan pembukaan rekening karena hasil verifikasi face-to-face via Skype tidak sesuai.html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%customerName", stpCustApplicationDto.getFullName())
//						.replace("%tgl", formatDate(stpTaskDto.getTaskCreatedDate()))
//						.replace("%before", formatDate(stpCustApplicationDto.getVideoCallDate()));
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void sendingEmailKelengkapanDokumentRejection() {
//		try {
//			Message message = new MimeMessage(getSessionMail());
//			message.setFrom(new InternetAddress(email));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stpCustApplicationDto.getEmail()));
//			message.setSubject("Penolakan Kelengkapan Data Tidak Sesuai");
//			FileInputStream file;
//			try {
//				file = new FileInputStream(new File(
//						"E:\\E-MAIL_TEMPLATE\\Email penolakan pembukaan rekening karena ketidaksesuaian dokumen.html"));
//				String kata = IOUtils.toString(file);
//				kata = kata.replace("%customerName", stpCustApplicationDto.getFullName())
//						.replace("%tgl", formatDate(stpTaskDto.getTaskCreatedDate()))
//						.replace("%before", formatDate(stpCustApplicationDto.getVideoCallDate()));
//				message.setContent(kata, "text/html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public StpCustApplicationDto getStpCustApplicationDto() {
//		return stpCustApplicationDto;
//	}
//
//	public void setStpCustApplicationDto(StpCustApplicationDto stpCustApplicationDto) {
//		this.stpCustApplicationDto = stpCustApplicationDto;
//	}
//
//	public StpTaskDto getStpTaskDto() {
//		return stpTaskDto;
//	}
//
//	public void setStpTaskDto(StpTaskDto stpTaskDto) {
//		this.stpTaskDto = stpTaskDto;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	private String formatDate(Date date) {
//		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
//		return fmt.format(date);
//	}
//
//	private String getCurrentDate() {
//		Date current = new Date();
//		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
//		String dateString = fmt.format(current);
//		return dateString;
//	}
//
//	private String getCurrentDay(Date date) {
//		SimpleDateFormat fmt = new SimpleDateFormat("EEEE");
//		String dateString = fmt.format(date);
//		return dateString;
//	}
//
//}
