package hand_dot.famicon_mini.Facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hand_dot.famicon_mini.Exceptions.SiteWatcherException;
import hand_dot.famicon_mini.Service.MailService;
import hand_dot.famicon_mini.Service.SiteWatcherService;
/**
 * サイトを監視し在庫のチェックを担当するファサードクラス
 * @author hand-dot
 *
 */
@Component
public class StockCheckFacade {

	/** サイト監視サービス*/
	 @Autowired
	 SiteWatcherService siteWatcherService;

	 /** メールサービス*/
	 @Autowired
	 MailService mailService;


	@Scheduled(fixedRate = 10000)
	public void stockCheck() {
		/** ロガー */
		Logger logger = Logger.getLogger("operation");
		PropertyConfigurator.configure("log4j.properties");

		String amazon = "https://www.amazon.co.jp/%E4%BB%BB%E5%A4%A9%E5%A0%82-CLV-S-HVCC-%E3%83%8B%E3%83%B3%E3%83%86%E3%83%B3%E3%83%89%E3%83%BC%E3%82%AF%E3%83%A9%E3%82%B7%E3%83%83%E3%82%AF%E3%83%9F%E3%83%8B-%E3%83%95%E3%82%A1%E3%83%9F%E3%83%AA%E3%83%BC%E3%82%B3%E3%83%B3%E3%83%94%E3%83%A5%E3%83%BC%E3%82%BF/dp/B01M1VMFUA/";
		String amazon2 = "https://www.amazon.co.jp/%E3%83%8B%E3%83%B3%E3%83%86%E3%83%B3%E3%83%89%E3%83%BC%E3%82%AF%E3%83%A9%E3%82%B7%E3%83%83%E3%82%AF%E3%83%9F%E3%83%8B-%E3%83%95%E3%82%A1%E3%83%9F%E3%83%AA%E3%83%BC%E3%82%B3%E3%83%B3%E3%83%94%E3%83%A5%E3%83%BC%E3%82%BF-Amazon-co-jp%E9%99%90%E5%AE%9A-%E3%82%AA%E3%83%AA%E3%82%B8%E3%83%8A%E3%83%AB%E3%83%9D%E3%82%B9%E3%83%88%E3%82%AB%E3%83%BC%E3%83%89-30%E6%9E%9A%E3%82%BB%E3%83%83%E3%83%88/dp/B01LYBYK0L/";
		String yodobashi = "http://www.yodobashi.com/%E4%BB%BB%E5%A4%A9%E5%A0%82-Nintendo-%E3%83%8B%E3%83%B3%E3%83%86%E3%83%B3%E3%83%89%E3%83%BC%E3%82%AF%E3%83%A9%E3%82%B7%E3%83%83%E3%82%AF%E3%83%9F%E3%83%8B-%E3%83%95%E3%82%A1%E3%83%9F%E3%83%AA%E3%83%BC%E3%82%B3%E3%83%B3%E3%83%94%E3%83%A5%E3%83%BC%E3%82%BF-%E3%82%B2%E3%83%BC%E3%83%A0%E6%A9%9F%E6%9C%AC%E4%BD%93/pd/100000001003263789/";
		String rakutenbooks = "http://books.rakuten.co.jp/rb/14484405/";
		String sevennet = "http://7net.omni7.jp/detail/2110592026";
		String toysrus = "http://www.toysrus.co.jp/s/dsg-558596100";
		List<String> urlList = new ArrayList<String>();
		urlList.add(amazon);
		urlList.add(amazon2);
		urlList.add(yodobashi);
		urlList.add(rakutenbooks);
		urlList.add(sevennet);
		urlList.add(toysrus);
		for (String url : urlList) {
			try {
				siteWatcherService.setUrl(url);
				String domain = siteWatcherService.getSiteType().getDomain();
				if (siteWatcherService.check()) {
					logger.info(domain + ":在庫あり");
					String subject = domain + "でファミコンミニの在庫がありました！";
					String content = "ファミコンミニの在庫が確認でしました！\n" + url + "\nにアクセスして購入してください。";
					MailService mailSend = new MailService();
					mailSend.send(subject, content, "example@test.com");
				} else {
					logger.info(domain + ":在庫なし");
				}
			} catch (SiteWatcherException e) {
				logger.error(e);
			}
		}
	}
}
