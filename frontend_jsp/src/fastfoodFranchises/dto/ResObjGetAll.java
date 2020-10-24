package fastfoodFranchises.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResObjGetAll {
	private int code;
	private ArrayList<Franchises> data;
}
