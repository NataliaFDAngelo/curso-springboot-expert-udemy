package br.com.curso.springboot.sistemavendas;

import br.com.curso.springboot.sistemavendas.domain.entity.Cliente;
import br.com.curso.springboot.sistemavendas.domain.entity.Pedido;
import br.com.curso.springboot.sistemavendas.domain.repository.Clientes;
import br.com.curso.springboot.sistemavendas.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SistemaVendasApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired Clientes clientes,
			@Autowired Pedidos pedidos){
		return args -> {

			System.out.println("Salvando os clientes");
			Cliente cliente1 = new Cliente("Natalia");
			clientes.save(cliente1);

			Cliente cliente2 = new Cliente("Leonardo");
			clientes.save(cliente2);

			Cliente cliente3 = new Cliente("Rodrigo");
			clientes.save(cliente3);

			Cliente cliente4 = new Cliente("Leia");
			clientes.save(cliente4);


			Pedido p = new Pedido();
			p.setCliente(cliente1);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			pedidos.save(p);

//			Cliente cliente = clientes.findClienteFetchPedidos(cliente1.getId());
//			System.out.println(cliente);
//			System.out.println(cliente.getPedidos());

			pedidos.findByCliente(cliente1).forEach(System.out::println);

//			List<Cliente> todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
//

//			System.out.println("-----------------------");
//			System.out.println("Atualizando os clientes");
//			todosClientes.forEach(c ->{
//				c.setNome(c.getNome() + " Atualizado");
//				clientes.save(c);
//			});
//			todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
//
//			System.out.println("-----------------------");
//			System.out.println("Buscando um cliente por nome");
//			clientes.findByNomeLike("dri").forEach(System.out::println);
//
//			System.out.println("-----------------------");
//			System.out.println("Deletando um cliente");
//			clientes.deleteById(4);
//			todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
//
//			System.out.println("-----------------------");
//			System.out.println("Deletando todos os cliente");
//			clientes.findAll().forEach(c ->{
//				clientes.delete(c);
//			});
//			todosClientes = clientes.findAll();
//			if(todosClientes.isEmpty()){
//				System.out.println("Nenhum cliente encontrado");
//			}else {
//				todosClientes.forEach(System.out::println);
//			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaVendasApplication.class, args);
	}

}
