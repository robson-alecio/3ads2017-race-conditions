package racecondition.mineracao;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarrinhoCarga {
	
	private int capacidade;
	private int carga;
	
	private Lock emUsoLock;
	private Condition cheioCondition;
	private Condition vazioCondition;
	
	private boolean carregando;
	
	public CarrinhoCarga(int capacidade) {
		this.capacidade = capacidade;
		
		emUsoLock = new ReentrantLock();
		cheioCondition = emUsoLock.newCondition();
		vazioCondition = emUsoLock.newCondition();
	}

	public int carregar(int porcao) throws InterruptedException {
		int sobra = 0;
		emUsoLock.lock();
		try {
			while (carga > 0 && !carregando)
				vazioCondition.await();
			
			carregando = true;
			System.out.printf("carregando em carga %d a porção de %d.\n", carga, porcao);
			
			int porcaoAdicionar = porcao;
			if (carga + porcao > capacidade) {

				porcaoAdicionar = capacidade - carga;
				sobra = porcao - porcaoAdicionar;
				System.out.printf("realmente carregado +%d e sobrou %d.\n", porcaoAdicionar, sobra);
			}
			
			carga += porcaoAdicionar;
			
			System.out.printf("nova carga: %d\n", carga);
			
			cheioCondition.signalAll();
		} finally {
			emUsoLock.unlock();
		}
		
		return sobra;
	}

	public int descarregar() throws InterruptedException {
		int porcao;
		emUsoLock.lock();
		try {
			while (carga < capacidade && carregando)
				cheioCondition.await();
			
			carregando = false;
			porcao = 200;
			
			if (carga <= porcao)
				porcao = carga;
			
			carga -= porcao;
			System.out.printf("nova carga após remover porção: %d\n", carga);
			
			vazioCondition.signalAll();
		} finally {
			emUsoLock.unlock();
		}
		
		return porcao;
	}

}
