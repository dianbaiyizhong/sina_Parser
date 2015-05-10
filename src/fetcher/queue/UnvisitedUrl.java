package fetcher.queue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fetcher.database.DataImpl;
import fetcher.pojo.News;
import fetcher.util.MD5;

public class UnvisitedUrl {
	static DataImpl dataImpl = new DataImpl();
	static MD5 md5 = new MD5();
	private static Set<String> UnVisitedResultCodeList = new HashSet<String>();

	private static Set<String> UnVisitedTimeRangeList = new HashSet<String>();

	private static Set<String> UnVisitedTitleList = new HashSet<String>();
	private static Set<String> UnVisitedTextUrlList = new HashSet<String>();

	private static final UnvisitedUrl INSTANCE = new UnvisitedUrl();

	public synchronized static final UnvisitedUrl getInstance() {
		return UnvisitedUrl.INSTANCE;
	}

	public synchronized String getResultCode() {
		synchronized (this) {

			Iterator it = UnVisitedResultCodeList.iterator();

			if (it.hasNext()) {
				String s = (String) it.next();

				it.remove();
				return s;

			}

		}

		return null;
	}

	public synchronized void setResultCode(String s) {

		UnVisitedResultCodeList.add(s);

	}

	public synchronized String getTimeRangeUrl() {
		synchronized (this) {

			Iterator it = UnVisitedTimeRangeList.iterator();

			if (it.hasNext()) {
				String s = (String) it.next();

				it.remove();
				return s;

			}

			return null;
		}
	}

	public void setTimeRangeUrl(String s) {
		UnVisitedTimeRangeList.add(s);

	}

	public synchronized String getTextUrl() {
		synchronized (this) {
			if (UnVisitedTextUrlList.size() != 0) {
				System.out.println(UnVisitedTextUrlList.size());

			}
			Iterator it = UnVisitedTextUrlList.iterator();

			if (it.hasNext()) {
				String s = (String) it.next();

				it.remove();
				return s;

			}

			return null;
		}
	}

	public synchronized String getTitleCode() {
		synchronized (this) {

			Iterator it = UnVisitedTitleList.iterator();

			if (it.hasNext()) {
				String s = (String) it.next();

				it.remove();
				return s;

			}

			return null;
		}
	}

	public synchronized void setTitleCode(String code) {
		UnVisitedTitleList.add(code);

	}

	public synchronized void setTextUrl(News n) {

		 dataImpl.saveData(n);
		System.out.println("已经插入:" + n.getUrl());

		UnVisitedTextUrlList.add(n.getUrl());

	}

	public int getTitleCodeSize() {
		return UnVisitedTitleList.size();
	}

	public int getTextUrlSize() {
		return UnVisitedTextUrlList.size();
	}

	public int getTimeRangeSize() {
		return UnVisitedTimeRangeList.size();
	}

	public int getResultCodeSize() {
		return UnVisitedResultCodeList.size();
	}
}
