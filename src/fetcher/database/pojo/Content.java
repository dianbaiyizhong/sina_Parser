package fetcher.database.pojo;

public class Content {
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {

		this.keyword = keyword;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text.replaceAll("<p>", "").replaceAll("</p>", "");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time.trim();
	}

	private String keyword;
	private String title;
	private String text;
	private String url;
	private String time;

}
