package fetcher.get;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import fetcher.database.DataImpl;
import fetcher.database.LoadData;
import fetcher.database.pojo.Content;
import fetcher.grab.parser;

public class GatherContent implements Runnable {
	static parser parser = new parser();
	static ContentUrl CU = ContentUrl.getInstance();
	static DataImpl dataImpl = new DataImpl();
	static LoadData loadData = new LoadData();

	private List<String> urlList;

	public GatherContent(List<String> urlList) {
		this.urlList = urlList;

	}

	@Override
	public void run() {

		if (urlList.size() != 0) {

			for (int i = 0; i < urlList.size(); i++) {

				String url = urlList.get(i);
				// 获取源代码

				String sourseCode = parser.getSourseCode(url);
				if (sourseCode == null) {
					System.out.println("重新加载:" + url);
					CU.setUrl(url);
					continue;

				}

				// 获取日期
				String time = "";
				Pattern p = Pattern
						.compile("[2][0][0-9]{2}[年|\\-|/][0-9]{1,2}[月|\\-|/][0-9]{1,2}[日|\\s|\\S]");

				Matcher m = p.matcher(sourseCode);
				while (m.find()) {
					if (!"".equals(m.group())) {
						time = m.group();
						break;

					}
				}

				if (time == "") {
					System.out.println(url);
				} else {
					System.out.println(time);
					loadData.updateData(url, time);

				}

				// 获取时间
				String title = "";
				Document doc = Jsoup.parse(sourseCode);
				title = doc.title();

				// System.out.println(title);

				// 获取正文
				String text = "";
				text = doc.select("p").text();
				// System.out.println(text);

				Content content = new Content();
				content.setTitle(title);
				content.setUrl(url);
				content.setText(text);
				content.setTime(time);
				content.setKeyword("新疆");

				// dataImpl.saveData(content);
			}

		}
	}
}
