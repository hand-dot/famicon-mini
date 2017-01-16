package hand_dot.famicon_mini.exception;

/**
 * サイトチェックで発生した例外クラス
 * @author hand-dot
 *
 */
public class SiteWatcherException extends Exception {
	/**
	 * サイトチェック中に起きた例外
	 * @param message
	 */
    public SiteWatcherException(String message) {
        super(message);
    }

}
