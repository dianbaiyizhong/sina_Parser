package fetcher.grab;

import java.util.ArrayList;
import java.util.List;

import fetcher.pojo.News;
import fetcher.queue.UnvisitedUrl;

public class GatherTimeRange2Title implements Runnable {
	static parser parser = new parser();
	static UnvisitedUrl U = new UnvisitedUrl().getInstance();

	private String url;

	public GatherTimeRange2Title(String url) {
		this.url = url;

	}

	@Override
	public void run() {

		String nextPageUrl = url;

		while (true) {

			String code = null;

			while (true) {
				code = parser.getSourseCode(nextPageUrl);
				if (code != null) {
					break;

				} else {
					System.out.println("重新获取【时间范围页面url】源代码:" + nextPageUrl);
				}
			}
			if (code != null) {

				U.setResultCode(code);

				System.out.println("已经获取" + U.getResultCodeSize()
						+ "个【搜索结果页面url】源代码:" + nextPageUrl);

			}
			nextPageUrl = parser.getPageBoxNextUrl(code);

			if (nextPageUrl == null) {
				break;

			}

		}

	}
}
