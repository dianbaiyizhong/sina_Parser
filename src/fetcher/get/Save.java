package fetcher.get;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import fetcher.database.LoadData;

public class Save {

	static ContentUrl CU = ContentUrl.getInstance();

	public static void main(String[] args) {
		LoadData ld = new LoadData();

		ArrayList<String> list = new ArrayList<String>();
		list = ld.LoadUrl();
		for (int i = 0; i < list.size(); i++) {
			CU.setUrl(list.get(i));
		}
		// 使用多线程采集
		 DivideContent();

	}

	private static int MaxThread = 10;
	// 使用线程池
	private static ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(MaxThread);

	private static void DivideContent() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					if (threadPool.getActiveCount() < MaxThread) {

						List<String> urlList = new ArrayList<String>();
						for (int i = 0; i < 10; i++) {

							// 从代抓队列中获取url给新的队列

							String url = CU.getUrl();

							if (url != null) {
								urlList.add(url);

							}

						}

						GatherContent gc = new GatherContent(urlList);
						threadPool.execute(gc);

					} else {
						// 如果线程池中的线程已满，则等待一段时间
						try {
							Thread.sleep(8000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}

		});

		t.start();
	}
}
