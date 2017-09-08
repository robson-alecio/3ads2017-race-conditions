package racecondition.mineracao;

public class Run {

	public static void main(String[] args) {
		CarrinhoCarga carrinho = new CarrinhoCarga(1500);
		
		CarregarCarrinho trabalhoCarga = new CarregarCarrinho(carrinho);
		DescarregarCarrinho trabalhoDescarga = new DescarregarCarrinho(carrinho);
		
		Thread execucaoCarga = new Thread(trabalhoCarga);
		Thread execucaoDescarga = new Thread(trabalhoDescarga);
		
		execucaoCarga.start();
		execucaoDescarga.start();
	}

}
