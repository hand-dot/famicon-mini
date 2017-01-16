package hand_dot.famicon_mini.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * サイトの情報管理クラス
 *
 * @author hand-dot
 *
 */
public enum SiteType {
	amazon("www.amazon.co.jp","カートに入れる","すべての出品を見る","#BUYBOX",CheckType.text),
	bigcamera("www.biccamera.com","カートに入れる","誠に申し訳ございません。","#RIGHT-SIDE",CheckType.text),
	yodobashi("www.yodobashi.com","ショッピングカートに入れる","販売休止中です","#js_buyBox",CheckType.text),
	rakutenbooks("books.rakuten.co.jp","買い物かごに入れる","ご注文できない商品","#purchaseBox",CheckType.text),
	sevennet("7net.omni7.jp","数量： 1 2","数量： 1","#cart_whole",CheckType.text),
	toysrus("www.toysrus.co.jp","<span id=\"isStock_a\">在庫あり</span>","<span id=\"isStock_a\" hidden>在庫あり</span>","#isStock_a",CheckType.html);

	private final String domain;
	/**在庫があった時のワード*/
    private final String inStockWord;
	/**在庫がなかった時のワード*/
    private final String outStockWord;
	/** 在庫チェックに有効なDOMを絞り込むクエリ */
	private final String query;
	/** 解析するときにhtmlで判断するかtextで判断するか */
	private final CheckType checkType;

	/**
	 * 必ずすべてを埋めてください
	 * @param domain サイトのドメイン（サイトタイプの判別に利用する）
	 * @param inStockWord 在庫があった時の文字
	 * @param outStockWord 在庫がなかった時の文字
	 * @param query 文字の判別で絞り込むクエリ
	 * @param checkType 解析するときにhtmlで判断するかtextで判断するか
	 */
    private SiteType(final String domain, final String inStockWord,final String outStockWord,final String query,CheckType checkType) {
    	this.domain = domain;
        this.inStockWord = inStockWord;
        this.outStockWord = outStockWord;
        this.query = query;
        this.checkType = checkType;
    }
	public String getDomain() {
		return domain;
	}
	public String getInStockWord() {
		return inStockWord;
	}

	public String getOutStockWord() {
		return outStockWord;
	}

	public String getQuery() {
		return query;
	}
	public CheckType getCheckType() {
		return checkType;
	}
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
