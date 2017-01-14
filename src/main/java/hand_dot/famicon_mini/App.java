package hand_dot.famicon_mini;

import hand_dot.famicon_mini.Service.StockCheckService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String url = "https://www.amazon.co.jp/%E3%83%8B%E3%83%B3%E3%83%86%E3%83%B3%E3%83%89%E3%83%BC%E3%82%AF%E3%83%A9%E3%82%B7%E3%83%83%E3%82%AF%E3%83%9F%E3%83%8B-%E3%83%95%E3%82%A1%E3%83%9F%E3%83%AA%E3%83%BC%E3%82%B3%E3%83%B3%E3%83%94%E3%83%A5%E3%83%BC%E3%82%BF-%E3%80%90Amazon-co-jp%E9%99%90%E5%AE%9A%E3%80%91-%E3%82%AA%E3%83%AA%E3%82%B8%E3%83%8A%E3%83%AB%E3%83%9D%E3%82%B9%E3%83%88%E3%82%AB%E3%83%BC%E3%83%89-30%E6%9E%9A%E3%82%BB%E3%83%83%E3%83%88/dp/B01LYBYK0L";
		String url2 = "https://www.amazon.co.jp/%E4%BB%BB%E5%A4%A9%E5%A0%82-%E3%83%8B%E3%83%B3%E3%83%86%E3%83%B3%E3%83%89%E3%83%BC%E3%82%AF%E3%83%A9%E3%82%B7%E3%83%83%E3%82%AF%E3%83%9F%E3%83%8B-%E3%83%95%E3%82%A1%E3%83%9F%E3%83%AA%E3%83%BC%E3%82%B3%E3%83%B3%E3%83%94%E3%83%A5%E3%83%BC%E3%82%BF-%E5%B0%82%E7%94%A8AC%E3%82%A2%E3%83%80%E3%83%97%E3%82%BF%E3%83%BC/dp/B01LX15F39/";
		StockCheckService cs = new StockCheckService(url);
		if(cs.check()){
			System.out.println("在庫あり");
		}else{
			System.out.println("在庫なし");
		}
	}
}
