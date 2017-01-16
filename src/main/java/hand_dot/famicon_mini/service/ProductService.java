package hand_dot.famicon_mini.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hand_dot.famicon_mini.dto.ProductDto;
/**
 * 商品サービス
 * @author hand-dot
 *
 */
@Service
public class ProductService {
	/**
	 * 商品IDをもとに商品情報を取得します。
	 * @param id 商品ID
	 * @return 商品情報のリスト
	 */
	public List<ProductDto> getProductDtoListById(String id){
		//TODO DBから取得する
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
		List<ProductDto> dtoList = new ArrayList<ProductDto>();
		for(String url:urlList){
			ProductDto dto = new ProductDto();
			dto.setUrl(url);
			dtoList.add(dto);
		}
		return dtoList;
	}
}
