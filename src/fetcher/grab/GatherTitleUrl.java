package fetcher.grab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import fetcher.database.DataImpl;
import fetcher.pojo.News;
import fetcher.queue.UnvisitedUrl;

public class GatherTitleUrl implements Runnable {

	static DataImpl dataImpl = new DataImpl();
	static UnvisitedUrl U = new UnvisitedUrl().getInstance();

	private List<String> n_list;

	public GatherTitleUrl(List<String> n_list) {
		this.n_list = n_list;

	}

	@Override
	public void run() {

		if (n_list.size() != 0) {

			for (int i = 0; i < n_list.size(); i++) {

				List<News> list = new ArrayList<News>();

				// 获取
				String arr[] = n_list.get(i).split("huanghaoming");

				String code = arr[0];
				String url = arr[1];

				Document doc = Jsoup.parse(code);

				Elements el = doc.select("div[class=box-result clearfix]");

				for (int j = 0; j < el.size(); j++) {
					News news = new News();

					news.setKeyword("新疆");

					String time = "";
					Pattern p = Pattern
							.compile("[2][0][0-9]{2}[年|\\-|/][0-9]{1,2}[月|\\-|/][0-9]{1,2}");

					Matcher m = p.matcher(url);
					while (m.find()) {
						if (!"".equals(m.group())) {
							time = m.group();
							break;

						}
					}

					news.setTime(time);
					// 获取每条结果的div
					String resultDiv = el.get(j).toString();

					// 获取每条内容的链接
					Document doc1 = Jsoup.parse(resultDiv);
					Elements el1 = doc1.select("h2>a");
					for (int k = 0; k < el1.size(); k++) {

						String title = "";
						title = el1.get(k).text();

						news.setTitle(title);
						String contenturl = el1.get(k).attr("href");
						System.out.println("获取内容链接:" + contenturl);
						// 去掉图片链接
						int index = contenturl.indexOf("?img=");

						if (index != -1) {
							news.setUrl(contenturl.substring(0, index));

						} else {
							news.setUrl(contenturl);

						}

					}
					list.add(news);

				}

				for (int j = 0; j < list.size(); j++) {
					U.setTextUrl(list.get(j));

					System.out.println("已经获取：" + U.getTextUrlSize() + "个结果");

				}

			}

		}
	}
}
