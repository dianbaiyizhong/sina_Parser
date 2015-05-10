package fetcher.get;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fetcher.queue.UnvisitedUrl;

public class ContentUrl {
	private static final ContentUrl INSTANCE = new ContentUrl();

	public synchronized static final ContentUrl getInstance() {
		return ContentUrl.INSTANCE;
	}

	private List<String> urlList = new ArrayList<String>();

	public synchronized String getUrl() {

		Iterator it = urlList.iterator();

		if (it.hasNext()) {
			String url = (String) it.next();
			it.remove();

			return url;
		}

		return null;
	}

	public synchronized void setUrl(String url) {
		urlList.add(url);

	}

	public synchronized int getUrlSize() {
		return urlList.size();

	}

}
