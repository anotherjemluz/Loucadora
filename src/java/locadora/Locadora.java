package locadora;

import java.util.ArrayList;
import java.util.List;

public class Locadora {

	public static void main(String[] args) {
		/*
		 * AluguelDAO a = new AluguelDAO(); Aluguel al = new Aluguel(a.incrementoID(),
		 * "14/06/2018", "17/06/2018", "151.302.625.30", "025.241.578-95");
		 * a.inserir(al);
		 */
		/*
		 * ClienteDAO c = new ClienteDAO();
		 * 
		 * Cliente c1 = new Cliente("Rosilene Moufer", "200.200.200-40", "06/03/1995");
		 * 
		 * Cliente c2 = new Cliente("Rosilene Fernandes", "051.864.303-40",
		 * "06/03/1995");
		 * 
		 * c.inserir(c1);
		 * 
		 * c.inserir(c2);
		 * 
		 * AluguelDAO a = new AluguelDAO(); Aluguel al = new Aluguel(a.incrementoID(),
		 * "16/06/2018", "19/06/2018", "151.302.625.30", "051.864.303-40");
		 * a.inserir(al);
		 */
		List<Cliente> clientes = new ArrayList<Cliente>();
		AluguelDAO a = new AluguelDAO();
		ClienteDAO c = new ClienteDAO();
		clientes = c.getClientesByNome("Rosilene");
		a.getAluguelByIdCliente("051.864.303.40");
		System.out.println(clientes.get(1).alugueis);

	}

}
