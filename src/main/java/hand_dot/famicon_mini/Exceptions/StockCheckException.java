package hand_dot.famicon_mini.Exceptions;

/**
 * 在庫のチェックで発生した例外クラス
 * @author hand-dot
 *
 */
public class StockCheckException extends Exception {
	/**
	 * 在庫チェック中に起きた例外
	 * @param message
	 */
    public StockCheckException(String message) {
        super(message);
    }

}
