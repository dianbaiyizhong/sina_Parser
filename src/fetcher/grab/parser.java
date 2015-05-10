package fetcher.grab;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class parser {

	public String getSourseCode(String url) {
		String sourseCode = null;
		Document doc_sourseCode = null;
		try {
			doc_sourseCode = Jsoup.connect(url).timeout(60000).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		sourseCode = doc_sourseCode.html();

		return sourseCode.trim() + "huanghaoming" + url;
	}

	public String getPageBoxNextUrl(String sourseCode) {
		Document doc = Jsoup.parse(sourseCode);
		Elements el = doc.select("div[class=pagebox]");

		for (int j = 0; j < el.size(); j++) {

			// 获取每条结果的div
			String resultDiv = el.get(j).toString();
			// 获取每条内容的链接
			Document doc1 = Jsoup.parse(resultDiv);
			Elements el1 = doc1.select("a");

			for (int k = 0; k < el1.size(); k++) {

				String result = el1.get(k).attr("title");

				if (result.equals("下一页")) {

					return "http://search.sina.com.cn"
							+ el1.get(k).attr("href");

				}

			}

			return null;

		}

		return null;

	}

}
