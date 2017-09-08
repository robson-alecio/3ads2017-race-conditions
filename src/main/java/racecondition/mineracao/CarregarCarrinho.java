package racecondition.mineracao;

public class CarregarCarrinho implements Runnable {

	private CarrinhoCarga carrinho;

	public CarregarCarrinho(CarrinhoCarga carrinho) {
		this.carrinho = carrinho;
	}

	public void run() {
		try {
			efetuarCarga();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void efetuarCarga() throws InterruptedException {
		while (true) {
			carrinho.carregar(250);
			Thread.sleep(1000);
		}
	}

}
