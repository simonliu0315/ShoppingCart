package com.waterproof.bjb.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

public class Test {

	
	public static void main(String[] args) throws Exception {

		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		System.setProperty("javax.net.ssl.trustStore", "D:\\Private\\Shopping\\ShoppingCart\\jssecacerts");
		SSLContext sc = sslcontext();
		 while (true) {
		 String url1 = "https://www.hypay.com.tw/mpi/return.jsp";
		 url1 = "https://www.hypay.com.tw/ezpostw/auth/Images.jsp";
		 //call(url1);
		 Thread.sleep(100);
		 String url5 = "https://eposn.hncb.com.tw/transaction/api-auth/";
		 
		 url5 = "https://eposn.hncb.com.tw/ezpos/generateCaptcha";
		 url5 = "https://eposn.hncb.com.tw/transaction/generateCaptcha";
		 //url5 = "https://eposn.hncb.com.tw/ezpos/";
		 //url5 = "https://eposn.hncb.com.tw/ezpos/login/";
		 callPost2(url5, sc);
		 }
//
//		while (true) {
//			Thread thread1 = new Thread(new Runnable() {
//				public void run() {
//					String url1 = "https://www.hypay.com.tw/mpi/return.jsp";
//					String url2 = "https://epos.hncb.com.tw/mpisite/return.jsp";
//					String url3 = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
//					String url4 = "https://eposn.hncb.com.tw/transaction/api-auth/";
//					call(url1);
//
//				}
//			});
//			Thread thread2 = new Thread(new Runnable() {
//				public void run() {
//					String url2 = "https://epos.hncb.com.tw/mpisite/return.jsp";
//					url2 = "https://epos.hncb.com.tw/ezpostw/include/title.jsp";
//					//call(url2);
//					callPost2(url2, sc);
//				}
//			});
//			Thread thread3 = new Thread(new Runnable() {
//				public void run() {
//
//					String url3 = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
//
//					call(url3);
//
//				}
//			});
//			Thread thread4 = new Thread(new Runnable() {
//				public void run() {
//
//					String url4 = "https://epos.hncb.com.tw/ezpostw/";
//
//					call(url4);
//
//				}
//			});
//			Thread thread5 = new Thread(new Runnable() {
//				public void run() {
//
//					String url5 = "https://eposn.hncb.com.tw/transaction/api-auth/";
//
//					//callPost(url5);
//					callPost2(url5, sc);
//				}
//			});
//			Thread thread6 = new Thread(new Runnable() {
//				public void run()
//
//				{
//
//					String url6 = "https://www.hypay.com.tw/ezpostw/";
//
//					call(url6);
//
//				}
//			}); // 必須使用 setDaemon() method 將 thread 設定為 daemon
////			thread1.setDaemon(true); 
////			thread1.start();
//			// thread2.setDaemon(true);
//			// thread2.start(); //
//			//thread3.setDaemon(true);
//			// thread3.start();
//			//thread4.setDaemon(true);
//			//thread4.start();
//			thread5.setDaemon(true);
//			thread5.start(); //
//			//thread6.setDaemon(true);
//			// thread6.start(); // try { //
//			// Thread.sleep(1000);
//
//		}
	}

	public static void call(String url) {
		// String url = "https://www.hypay.com.tw/mpi/return.jsp";
		// url = "https://epos.hncb.com.tw/mpisite/return.jsp";
		// url = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
		// url="https://eposn.hncb.com.tw/transaction/api-auth/";
		// System.out.println(url);
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
			SSLContext sc = SSLContext.getInstance("TLSv1.2");

			sc.init(null, trustAllCerts, new java.security.SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			URL myUrl = new URL(url);
			// URL myUrl = new URL(httpsURL);
			conn = (HttpsURLConnection) myUrl.openConnection();
			System.out.println(conn.getURL() + " ,code: " + conn.getResponseCode());
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
			// e.printStackTrace();
		} finally {
			System.out.println(conn.getURL() + " disconnect.");
			conn.disconnect();
			conn = null;
		}
	}

	public static void callPost(String url) {
		// String url = "https://www.hypay.com.tw/mpi/return.jsp";
		// url = "https://epos.hncb.com.tw/mpisite/return.jsp";
		// url = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
		// url="https://eposn.hncb.com.tw/transaction/api-auth/";
		// System.out.println(url);
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

			sc = sslcontext();
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			// sc.init(null, trustAllCerts, new java.security.SecureRandom());
			// HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			// Install the all-trusting host verifier
			// HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			String postDataStr = "MerchantID=1231231231&TerminalID=423424";
			URL myUrl = new URL(url);
			// URL myUrl = new URL(httpsURL);
			conn = (HttpsURLConnection) myUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Referer", "www.google.com.tw");
			conn.setRequestProperty("Content-Length", String.valueOf(postDataStr.length()));
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("x-forwarded-for","127.0.0.1");
			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			os.write("".getBytes("UTF-8"), 0, 0);
			os.flush();
			os.close();

			System.out.println(conn.getURL() + " ,code: " + conn.getResponseCode());
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
			// e.printStackTrace();
		} finally {
			System.out.println(conn.getURL() + " disconnect.");
			conn.disconnect();
			conn = null;
		}
	}

	private static SSLContext sslcontext() {
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContexts.custom().loadTrustMaterial(new File("D:\\HNCB\\my.store"), "123456".toCharArray(),
					new TrustSelfSignedStrategy()).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
				// .setDefaultCookieStore(cookieStore)
				// 异常重试机制 3次 （网络层面上的）

				.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
				// .setDefaultRequestConfig(defaultRequestConfig)
				.build();
		
		return sslcontext;
	}

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
	
	public static void callPost2(String url, SSLContext sc) {
		// String url = "https://www.hypay.com.tw/mpi/return.jsp";
		// url = "https://epos.hncb.com.tw/mpisite/return.jsp";
		// url = "https://epos.hncb.com.tw/ezpostw/auth/SSLAuthUI.jsp";
		// url="https://eposn.hncb.com.tw/transaction/api-auth/";
		// System.out.println(url);
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
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			// sc.init(null, trustAllCerts, new java.security.SecureRandom());
			// HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			// Install the all-trusting host verifier
			// HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			String postDataStr = "MerchantID=1231231231&TerminalID=423424";
			URL myUrl = new URL(url);
			// URL myUrl = new URL(httpsURL);
			conn = (HttpsURLConnection) myUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Referer", "www.google.com.tw");
			conn.setRequestProperty("Content-Length", String.valueOf(postDataStr.length()));
			conn.setRequestProperty("x-forwarded-for","127.0.0.10");	
			//conn.setDoInput(true);
//			conn.setDoOutput(true);
//			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//			os.write("".getBytes("UTF-8"), 0, 0);
//			os.flush();
//			os.close();

			System.out.println(conn.getURL() + " ,code: " + conn.getResponseCode());
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
			// e.printStackTrace();
		} finally {
			System.out.println(conn.getURL() + " disconnect.");
			conn.disconnect();
			conn = null;
		}
	}
}
