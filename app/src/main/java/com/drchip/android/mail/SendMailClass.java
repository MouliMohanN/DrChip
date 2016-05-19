package com.drchip.android.mail;

public class SendMailClass {

	public static void mailTest() {

		try {

			String[] strArr = { "moulimohann@gmail.com" };

			SendMail.send(strArr, "API Test Results", "Hi, Find the attachment for test results ");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
