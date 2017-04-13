
public class Main {

	public static void main(String[] args) {
		CartaLacaio lac1 = new CartaLacaio(1, "Frodo Bolseiro", 2, 1, 1);
		CartaLacaio lac2 = new CartaLacaio(2, "Aragorn", 5, 7, 6);
		CartaLacaio lac3 = new CartaLacaio(3, "Legolas", 8, 4, 6);
		CartaMagia mag1 =  new CartaMagia(4, "You shall not pass", 4, true, 7);
		CartaMagia mag2 =  new CartaMagia(5, "Telecinese", 3, false, 2);
		
		//item 1
		CartaLacaio lac4 = new CartaLacaio(6, "Gandalf", 9);
		System.out.println("Quarto lacaio:\n"+lac4);
		
		//item 2
		lac1.setAtaque(lac3.getAtaque());
		System.out.println("Primeiro lacaio:\n"+lac1);
		
		//item 3 com o método comentado
		System.out.println("Primeira magia:\n"+mag1);
		
		//item 4
		CartaLacaio lac5 = new CartaLacaio(lac2);
		System.out.println("Quinto lacaio:\n"+lac5);
		System.out.println("Segundo lacaio:\n"+lac2);
		
		//item 5
		System.out.println(mag2.nome);
		System.out.println(mag2.getNome() + '\n');
		
		//item 6
		lac2.buffar(5);
		System.out.println("Segundo lacaio:\n"+lac2);
		lac3.buffar(4, 5);
		System.out.println("Terceiro lacaio:\n"+lac3);
		
		
	}

}
