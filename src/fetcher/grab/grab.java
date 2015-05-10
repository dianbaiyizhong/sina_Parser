package fetcher.grab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import fetcher.pojo.News;
import fetcher.queue.UnvisitedUrl;

public class grab {

	static parser parser = new parser();

	static UnvisitedUrl U = new UnvisitedUrl().getInstance();

	public static void main(String[] args) {
		// 使用循环把链接获取，放入队列

		for (int year = 2000; year <= 2015; year++) {

			for (int mouth = 1; mouth <= 12; mouth++) {

				String url = "http://search.sina.com.cn/?c=news&q=%D0%C2%BD%AE&range=title&time=custom&stime="
						+ year
						+ "-"
						+ mouth
						+ "-"
						+ "01"
						+ "&etime="
						+ year
						+ "-"
						+ mouth
						+ "-"
						+ "31"
						+ "&num=20&col=&source=&from=&country=&size=&a=&page=1";

				U.setTimeRangeUrl(url);

			}

		}

		DivideTimeRangeUrl();
		DivideResultCode();

	}

	private static int msxThread_ResultCode = 30;
	// 使用线程池
	private static ThreadPoolExecutor threadPool_ResultCode = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(msxThread_ResultCode);

	private static void DivideResultCode() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					if (threadPool_ResultCode.getActiveCount() < msxThread_ResultCode) {

						List<String> codeList = new ArrayList<String>();
						if (threadPool_ResultCode.getActiveCount() != 0) {
							System.out.println(threadPool_ResultCode
									.getActiveCount());
						}
						for (int i = 0; i < 20; i++) {

							// 从代抓队列中获取url给新的队列

							String url = U.getResultCode();
							if (url != null) {

								codeList.add(url);

							}

						}

						GatherTitleUrl gtu = new GatherTitleUrl(codeList);

						threadPool_ResultCode.execute(gtu);

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

	private static int maxThread_TimeRange = 30;
	// 使用线程池
	private static ThreadPoolExecutor threadPool_TimeRange = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(maxThread_TimeRange);

	// 分配TimeRangeUrl任务
	private static void DivideTimeRangeUrl() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (threadPool_TimeRange.getActiveCount() < maxThread_TimeRange) {

						for (int i = 0; i < 1; i++) {

							// 从代抓队列中获取url给新的队列

							String url = U.getTimeRangeUrl();

							if (url != null) {
								GatherTimeRange2Title gtr2t = new GatherTimeRange2Title(
										url);

								threadPool_TimeRange.execute(gtr2t);

							}

						}

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
