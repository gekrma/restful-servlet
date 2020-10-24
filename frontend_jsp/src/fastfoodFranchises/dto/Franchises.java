package fastfoodFranchises.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Franchises {
	private String brand;			// 브랜드
	private String company;			// 상호
	private int franchisesNum;		// 가맹점수
	private int avgSales;			// 평균매출액
	private int areaAvgSales;		// 면적당 평균매출액
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append( "brand" + ":'" + brand + "'," );
		builder.append( "company" + ":'" + company + "'," );
		builder.append( "franchisesNum" + ":" + franchisesNum + "," );
		builder.append( "avgSales" + ":" + avgSales + "," );
		builder.append( "areaAvgSales" + ":" + areaAvgSales );
		
		return builder.toString();
	}
}
