package hand_dot.famicon_mini.Enums;

public enum SiteType {
	 amazon("www.amazon.co.jp","カートに入れる","すべての出品を見る","BUYBOX");

	private final String domain;
	/**在庫があった時のワード*/
    private final String inStockWord;
	/**在庫がなかった時のワード*/
    private final String outStockWord;
	/** 在庫チェックに有効なDOMを絞り込むID */
	private final String elemId;

    private SiteType(final String domain, final String inStockWord,final String outStockWord,final String elemId) {
    	this.domain = domain;
        this.inStockWord = inStockWord;
        this.outStockWord = outStockWord;
        this.elemId = elemId;
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

	public String getElemId() {
		return elemId;
	}
}
