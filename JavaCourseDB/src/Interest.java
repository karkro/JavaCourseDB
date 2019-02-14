public class Interest {

	public static void main(String[] args) {
		
		final double STARTRATE = 10;
		final int NRATES = 6;
		final int NYEARS = 10;
		
		// Oprocentowanie
		double[] interestRate = new double[NRATES];
		for (int j = 0; j < interestRate.length; j++) {
			interestRate[j] = (STARTRATE + j) / 100.0;
		}
		//System.out.println(Arrays.toString(interestRate));
		
		// Przypisujemy podstawê oprocentowania w kwocie 10000 do pierwszego wiersza tabeli
		// Tworzymy tabelê z wynikami
		double[][] balances = new double[NYEARS][NRATES];
		for (int j = 0; j < balances[0].length; j++) {
			balances[0][j] = 10000;
			//System.out.print(balances[0][j]);
		}
		
		// Uzupe³nienie tabeli danymi
		for (int i = 1; i < balances.length; i++) {
			for (int j = 0; j < balances[0].length; j++) {
				double oldBalance = balances[i - 1][j];
				double interest = oldBalance * interestRate[j];
				balances[i][j] = oldBalance + interest;
			}
		}
		
		// Wydruk nag³ówka tabeli
		for (int j = 0; j < interestRate.length; j++) {
			System.out.printf("%9.0f%%", 100 * interestRate[j]);
		}
		System.out.println();
		
		// Wydruk zawartoœci tabeli
		for (double[] row : balances) {
			for (double b : row) {
				System.out.printf("%10.2f", b);
			}
			System.out.println();
		}
		
		
	}
}
