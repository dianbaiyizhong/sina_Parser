package fetcher.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import fetcher.database.LoadData;

public class Start {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 首先获取所有数据的时间
		LoadData ld = new LoadData();
		ArrayList<String> list = ld.LoadAllNews();

		ArrayList<Date> list_date = new ArrayList<Date>();

		for (int i = 0; i < list.size(); i++) {
			Date date = null;
			try {
				date = sdf.parse(list.get(i));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list_date.add(date);

		}

		ArrayList<Date> list_date1 = new ArrayList<Date>();

		for (int year = 100; year <= 115; year++) {
			for (int month = 0; month <= 11; month++) {

				String year_ss = String.valueOf(year).substring(1);
				 System.out.println(year_ss + "-" + month + "的数量为:");
				int count = 0;
				for (int i = 0; i < list_date.size(); i++) {
					int year_s = list_date.get(i).getYear();
					int month_m = list_date.get(i).getMonth();

					if (year_s == year && month_m == month) {
						count++;
						list_date1.add(list_date.get(i));
					}

				}

				//System.out.println(count);
				System.out.println();

			}

		}

		// //System.out.println(list_date1.size());
		//
		// Set<String> test = new HashSet<String>();
		// for (int i = 0; i < list_date.size(); i++) {
		// test.add(String.valueOf(list_date.get(i).getMonth()));
		//
		// }
		//
		// java.util.Iterator<String> it =test.iterator();
		// while(it.hasNext()){
		// String date = (String) it.next();
		// System.out.println(date);
		// }

	}
}
