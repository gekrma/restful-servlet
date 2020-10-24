package fastfoodFranchises.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Franchises {
	private String brand;			// 브랜드
	private String company;			// 상호
	private int franchisesNum;		// 가맹점수
	private int avgSales;			// 평균매출액
	private int areaAvgSales;		// 면적당 평균매출액
}
