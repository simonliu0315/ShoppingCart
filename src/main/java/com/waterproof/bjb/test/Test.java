package com.waterproof.bjb.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Test {
/*
	public static void main(String[] args) throws Exception {
				
		while(true){ 
			Thread thread1 = new Thread(new Runnable() {
				public void run() {
					String url1 = "https://www.hypay.com.tw/mpi/return.jsp";
					String url2 = "https://epos.hncb.com.tw/mpisite/return.jsp";
					String url3 = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
					String url4 = "https://eposn.hncb.com.tw/transaction/api-auth/";
					call(url1);
					
					
				}
			});
			Thread thread2 = new Thread(new Runnable() {
				public void run() {
					String url2 = "https://epos.hncb.com.tw/mpisite/return.jsp";
					url2 = "https://epos.hncb.com.tw/ezpostw/include/title.jsp";
					call(url2);
					
					
				}
			});
			Thread thread3 = new Thread(new Runnable() {
				public void run() {
					
					String url3 = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
							
					call(url3);
				
				}
			});
			Thread thread4 = new Thread(new Runnable() {
				public void run() {
					
					String url4 = "https://epos.hncb.com.tw/ezpostw/";
				
					call(url4);
			
				}
			});
			Thread thread5 = new Thread(new Runnable() {
				public void run() {
					
					String url5 = "https://eposn.hncb.com.tw/transaction/api-auth/";
				
					callPost(url5);
			
				}
			});
			Thread thread6 = new Thread(new Runnable() {
				public void run() {
					
					String url6 = "https://www.hypay.com.tw/ezpostw/";
				
					call(url6);
			
				}
			});
			// 必須使用 setDaemon() method 將 thread 設定為 daemon
			// 否則 main thread 結束後，此 thread 還是會繼續執行
			thread1.setDaemon(true);
			thread1.start();
//			thread2.setDaemon(true);
//			thread2.start();
//			thread3.setDaemon(true);
//			thread3.start();
//			thread4.setDaemon(true);
//			thread4.start();
//			thread5.setDaemon(true);
//			thread5.start();
			thread6.setDaemon(true);
			thread6.start();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

	}

	public static void call(String url) {
		//String url = "https://www.hypay.com.tw/mpi/return.jsp";
		//url = "https://epos.hncb.com.tw/mpisite/return.jsp";
		//url = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
		//url="https://eposn.hncb.com.tw/transaction/api-auth/";
		//System.out.println(url);
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection conn = null;
		try {
			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			URL myUrl = new URL(url);
			// URL myUrl = new URL(httpsURL);
			conn = (HttpsURLConnection) myUrl.openConnection();
			System.out.println(conn.getURL() + " ,code: "+conn.getResponseCode());
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);

			}
			br.close();
		} catch (Exception e) {
			System.out.println(url + "-" + e.getMessage());
			//e.printStackTrace();
		}
		finally {
			System.out.println(conn.getURL() + " disconnect.");
			conn.disconnect();
			conn = null;
		}
	}
	
	public static void callPost(String url) {
		//String url = "https://www.hypay.com.tw/mpi/return.jsp";
		//url = "https://epos.hncb.com.tw/mpisite/return.jsp";
		//url = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
		//url="https://eposn.hncb.com.tw/transaction/api-auth/";
		//System.out.println(url);
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection conn = null;
		try {
			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			String postDataStr = "MerchantID=1231231231&TerminalID=423424";
			URL myUrl = new URL(url);
			// URL myUrl = new URL(httpsURL);
			conn = (HttpsURLConnection) myUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Referer","www.google.com.tw");
			conn.setRequestProperty("Content-Length", String.valueOf(postDataStr.length()));
			System.out.println(conn.getURL() + " ,code: "+conn.getResponseCode());
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);

			}
			br.close();
		} catch (Exception e) {
			System.out.println(url + "-" + e.getMessage());
			//e.printStackTrace();
		}
		finally {
			System.out.println(conn.getURL() + " disconnect.");
			conn.disconnect();
			conn = null;
		}
	}
	*/
}
