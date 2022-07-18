
public class HolidayBonus {
	public HolidayBonus() {}
	
	/**
	 * Calculates the holiday bonus for each store
	 * 
	 * @param data - the two dimensional array of store sales
	 * @param high - bonus for the highest store in a category
	 * @param low - bonus for the lowest store in a category
	 * @param other - bonus for all other stores in a category
	 * @return - an array of the bonus for each store
	 */
	public static double[] calculateHolidayBonus(double[][] data, double high, double low, double other) {
		double[][] amount = new double[data.length][];
		
		for(int i = 0; i < data.length; i++) {
			amount[i] = new double[data[i].length];
		}
		
		for(int j = 0, k = 0; j < amount.length; j++, k++) {
			int hIndex = TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, j);
			int lIndex = TwoDimRaggedArrayUtility.getLowestInColumnIndex(data, j);
			
			if(j < amount[k].length) {
				amount[hIndex][j] = high;
				amount[lIndex][j] = low;
				
				if(k == hIndex || k == lIndex) {
					continue;
				} else {
					amount[k][j] = other;
				}
			}
		}
		
		double[] perStore = new double[data.length];
		
		for(int row = 0; row < amount.length; row++) {
			for(int col = 0; col < amount[row].length; col++) {
				perStore[row] += amount[row][col]; 
			}
		}
		return perStore;
		
	}
	
	/**
	 * Calculates the total holiday bonuses
	 * 
	 * @param data - the two dimensional array of store sales
	 * @param high - bonus for the highest store in a category
	 * @param low - bonus for the lowest store in a category
	 * @param other - bonus for all other stores in a category
	 * @return - the total of all holiday bonuses
	 */
	public static double calculateTotalHolidayBonus(double[][] data, double high, double low, double other) {
		double[][] amount = new double[data.length][];
		
		for(int i = 0; i < data.length; i++) {
			amount[i] = new double[data[i].length];
		}
		
		for(int j = 0, k = 0; j < amount.length; j++, k++) {
			int hIndex = TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, j);
			int lIndex = TwoDimRaggedArrayUtility.getLowestInColumnIndex(data, j);
			
			if(j < amount[k].length) {
				amount[hIndex][j] = high;
				amount[lIndex][j] = low;
				
				if(k == hIndex || k == lIndex) {
					continue;
				} else {
					amount[k][j] = other;
				}
			}
		}
		double hTotal = 0;
		
		for(int row = 0; row < amount.length; row++) {
			for(int col = 0; col < amount[row].length; col++) {
				hTotal += amount[row][col];
			}
		}
		return hTotal;
	}
		
}
