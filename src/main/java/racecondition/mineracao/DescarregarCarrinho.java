package racecondition.mineracao;

public class DescarregarCarrinho implements Runnable {

	private CarrinhoCarga carrinho;
	
	public DescarregarCarrinho(CarrinhoCarga carrinho) {
		this.carrinho = carrinho;
	}

	public void run() {
		try {
			efetuarDescarga();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void efetuarDescarga() throws InterruptedException {
		while (true) {
			int porcao = carrinho.descarregar();
			System.out.printf("porção descarregada: %d\n", porcao);
				Thread.sleep(500);
		}
	}

}
