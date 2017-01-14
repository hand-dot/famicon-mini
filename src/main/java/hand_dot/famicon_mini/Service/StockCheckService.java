package hand_dot.famicon_mini.Service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import hand_dot.famicon_mini.Enums.SiteType;
import hand_dot.famicon_mini.Exceptions.StockCheckException;

/**
 * 在庫のチェックに使用するクラス インスタンス化する際に必ずアクセスするURLで初期化をしてください。
 *
 * @author hand-dot
 *
 */
public class StockCheckService {

	/** 在庫をチェックしたいURL */
	private String url;

	/** アクセスしたHTMLドキュメント */
	private Document document;

	/** アクセスするサイトの情報 */
	private SiteType siteType;

	/** ユーザーエージェント*/
	private final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

	public StockCheckService(String url) {
		try {
			this.url = url;
			this.siteType =divideSiteType(url);
			this.document = Jsoup.connect(url)
					.userAgent(UA)
					.maxBodySize(0).get();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (StockCheckException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在庫チェックを行います。
	 *
	 * @return boolean
	 * @throws StockCheckException
	 */
	public boolean check() throws StockCheckException {
		//サイトのhtmlを解析してIDで絞り込んだテキスト
		String str = this.getTextById();
		Boolean bool = null;
		if(isMatch(str, this.siteType.getInStockWord())){
			bool = true;
		}else if(isMatch(str,this.siteType.getOutStockWord())){
			bool = false;
		}else{
			throw new StockCheckException("在庫が存在するもしくは存在しないのどちらかに当てはまりませんでした。"+""+"/解析テキスト:"+str);
		}
		return bool;
	}

	/**
	 *URLからサイト情報の設定を行います。
	 *どのサイトにも当てはまらない場合は例外を投げます。
	 * @param url
	 * @return
	 * @throws StockCheckException
	 */
	private SiteType divideSiteType(String url) throws StockCheckException {
		SiteType siteType=null;
		if (this.isMatch(url, SiteType.amazon.getDomain())) {
			siteType = SiteType.amazon;
		}
		if(siteType==null){
			throw new StockCheckException("アクセスするURLはどのサイトタイプにも当てはまりません/URL:"+url);
		}
		return siteType;
	}

	/**
	 * アクセスしたページ内の文字列をサイト情報のIdで絞り込んで取得する。
	 *
	 * @return テキスト部分だけを抽出した文字列
	 */
	private String getTextById() {
		return document.getElementById(this.siteType.getElemId()).text();
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
		this.url = url;
	}
}
