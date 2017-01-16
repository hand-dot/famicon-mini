package hand_dot.famicon_mini.facade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hand_dot.famicon_mini.dto.ProductDto;
import hand_dot.famicon_mini.exception.SiteWatcherException;
import hand_dot.famicon_mini.service.MailService;
import hand_dot.famicon_mini.service.ProductService;
import hand_dot.famicon_mini.service.SiteWatcherService;

/**
 * サイトを監視し在庫のチェックを担当するファサードクラス
 *
 * @author hand-dot
 *
 */
@Component
public class StockCheckFacade {
	/** 商品サービス */
	@Autowired
	ProductService productService;

	/** サイト監視サービス */
	@Autowired
	SiteWatcherService siteWatcherService;

	/** メールサービス */
	@Autowired
	MailService mailService;

	/**
	 * 10秒間隔でサイトをチェックし在庫があればメールを送信します。
	 */
	//TODO ここをAOPでロギングするようにしたい。DBに実行結果を書き込めるようにしたい
	@Scheduled(fixedRate = 10000)
	public void stockCheck() {
		/** ロガー */
		Logger logger = Logger.getLogger("operation");
		PropertyConfigurator.configure("log4j.properties");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		logger.info("在庫チェックを開始します。 - " + sdf.format(date));

		List<ProductDto> productList = productService.getProductDtoListById("");
		for (ProductDto product : productList) {
			try {
				siteWatcherService.setUrl(product.getUrl());
				String domain = siteWatcherService.getSiteType().getDomain();
				if (siteWatcherService.check()) {
					logger.info(domain + ":在庫あり");
					String subject = domain + "でファミコンミニの在庫がありました！";
					String content = "ファミコンミニの在庫が確認でしました！\n" + product.getUrl() + "\nにアクセスして購入してください。";
					MailService mailSend = new MailService();
					mailSend.send(subject, content, "example@test.com");
				} else {
					logger.info(domain + ":在庫なし");
				}
			} catch (SiteWatcherException e) {
				logger.error(e);
			}
		}

		date = new Date();
		logger.info("在庫チェックが終了しました。 - " + sdf.format(date));
	}
}
