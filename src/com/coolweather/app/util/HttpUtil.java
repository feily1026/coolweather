package com.coolweather.app.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(address);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = httpResponse.getEntity();
						String response = EntityUtils.toString(entity, "utf-8");
						if (listener != null) {
							listener.onFinish(response.toString());
						}
					}
					// connection = (HttpsURLConnection) url.openConnection();
					// connection.setRequestMethod("GET");
					// connection.setConnectTimeout(8000);
					// connection.setReadTimeout(8000);
					// InputStream in = connection.getInputStream();
					// BufferedReader reader = new BufferedReader(
					// new InputStreamReader(in));
					// StringBuilder response = new StringBuilder();
					// String line;
					// while ((line = reader.readLine()) != null) {
					// response.append(line);
					// }
				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
				}
			}
		}).start();
	}

}
