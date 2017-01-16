package hand_dot.famicon_mini.Service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import hand_dot.famicon_mini.Enums.CheckType;
import hand_dot.famicon_mini.Enums.SiteType;
import hand_dot.famicon_mini.Exceptions.SiteWatcherException;

/**
 * サイトの監視に使用するクラス インスタンス化する際に必ずアクセスするURLで初期化をしてください。
 *
 * @author hand-dot
 *
 */
@Service
public class SiteWatcherService {

	/** チェックしたいURL */
	private String url;

	/** アクセスしたHTMLドキュメント */
	private Document document;

	/** アクセスするサイトの情報 */
	private SiteType siteType;

	/** ユーザーエージェント */
	private final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

	public SiteWatcherService() {
	}

	public SiteWatcherService(String url) {
		setUrl(url);
	}

	/**
	 * サイトの状態チェックを行います。
	 * サイトの状態の定義についてはSiteTypeを参照してください。
	 *
	 * @return boolean
	 * @throws SiteWatcherException
	 */
	public boolean check() throws SiteWatcherException {
		// サイトのhtmlを解析してIDで絞り込んだテキスト
		String str = null;
		if(siteType.getCheckType() == CheckType.text){
			str  = this.getTextByselect();
		}else if(siteType.getCheckType() == CheckType.html){
			str  = this.getHtmlByselect();
		}
		Boolean bool = null;
		if (isMatch(str, this.siteType.getInStockWord())) {
			bool = true;
		} else if (isMatch(str, this.siteType.getOutStockWord())) {
			bool = false;
		} else {
			throw new SiteWatcherException("サイトチェックの解析結果が異常です。\n" + this.siteType.toString() + "\n解析テキスト:" + str);
		}
		return bool;
	}

	/**
	 * URLからサイト情報の設定を行います。 どのサイトにも当てはまらない場合は例外を投げます。
	 *
	 * @param url
	 * @return
	 * @throws SiteWatcherException
	 */
	private SiteType divideSiteType(String url) throws SiteWatcherException {
		SiteType siteType = null;
		for (SiteType site : SiteType.values()) {
			if (this.isMatch(url, site.getDomain())) {
				siteType = site;
			}
		}
		if (siteType == null) {
			throw new SiteWatcherException("アクセスするURLはどのサイトタイプにも当てはまりません/URL:" + url);
		}
		return siteType;
	}

	/**
	 * アクセスしたページ内の文字列をサイト情報のクエリで絞り込んで取得する。
	 *
	 * @return テキスト部分だけを抽出した文字列
	 */
	private String getTextByselect() {
		String string = document.select(this.siteType.getQuery()).text();
		return string;
	}

	/**
	 * アクセスしたページ内のHTMLをサイト情報のクエリで絞り込んで取得する。
	 *
	 * @return テキスト部分だけを抽出した文字列
	 */
	private String getHtmlByselect() {
		String string = document.select(this.siteType.getQuery()).outerHtml();
		return string;
	}

	/**
	 * 文字列str1に文字列str2が含まれていればtrue。
	 *
	 * @param str1
	 * @param str2
	 * @return boolean
	 */
	private boolean isMatch(String str1, String str2) {
		if (str1.matches(".*" + str2 + ".*")) {
			return true;
		} else {
			return false;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		try {
			this.url = url;
			this.siteType = divideSiteType(url);
			this.document = Jsoup.connect(url).userAgent(UA).maxBodySize(0).get();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SiteWatcherException e) {
			e.printStackTrace();
		}
	}

	public SiteType getSiteType() {
		return siteType;
	}

	public void setSiteType(SiteType siteType) {
		this.siteType = siteType;
	}
}
