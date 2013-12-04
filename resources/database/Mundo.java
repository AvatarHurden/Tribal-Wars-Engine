package database;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Classe para a criação de diferentes mundos
 * 
 * @author Arthur
 */
public class Mundo {

	private String nome;
	private boolean hasArqueiro;
	private boolean hasMilícia;
	private boolean hasPaladino;
	private boolean hasItensAprimorados;
	private boolean hasIgreja;
	private boolean academiaDeNíveis;
	private boolean pesquisaDeNíveis;
	private boolean hasMoral;
	private boolean hasBandeira;
	private boolean hasBonusNoturno;
	private BigDecimal velocidade;
	private BigDecimal modificarUnidaes;
	
	
	public Mundo(String nome) {
		
		this.nome = nome;
		
	}
	
	public Mundo() {}
	
	public void setAll(Properties prop) {
		
		nome = prop.getProperty("nome");
		
		hasArqueiro = Boolean.parseBoolean(prop.getProperty("arqueiro"));
		
		hasMilícia = Boolean.parseBoolean(prop.getProperty("milicia"));
		
		hasPaladino = Boolean.parseBoolean(prop.getProperty("paladino"));
		
		hasItensAprimorados = Boolean.parseBoolean(prop.getProperty("itensAprimorados"));
		
		hasIgreja = Boolean.parseBoolean(prop.getProperty("igreja"));
		
		academiaDeNíveis = Boolean.parseBoolean(prop.getProperty("academiaDeNiveis"));
		
		pesquisaDeNíveis = Boolean.parseBoolean(prop.getProperty("pesquisaDeNiveis"));
		
		hasMoral = Boolean.parseBoolean(prop.getProperty("moral"));
		
		hasBandeira = Boolean.parseBoolean(prop.getProperty("bandeira"));
		
		hasBonusNoturno = Boolean.parseBoolean(prop.getProperty("bonusNoturno"));
		
		String speed = prop.getProperty("velocidade");
		speed = speed.replaceAll(",", ".");
		velocidade = new BigDecimal(speed);
		
		String modifier = prop.getProperty("modificador");
		modifier = modifier.replaceAll(",", ".");
		modificarUnidaes = new BigDecimal(modifier);
		
	}
	
	public void printAll() {
		
		System.out.println("Nome: "+nome);
		System.out.println("Velocidade: "+velocidade.toString());
		System.out.println("Modificador: "+modificarUnidaes.toString());
		
		System.out.println("Arqueiros: "+hasArqueiro);
		System.out.println("Milícia: "+hasMilícia);
		System.out.println("Moral: "+hasMoral);
		System.out.println("Paladino: "+hasPaladino);
		System.out.println("Itens Aprimorados: "+hasItensAprimorados);
		System.out.println("Igreja: "+hasIgreja);
		System.out.println("Academia de Níveis: "+academiaDeNíveis);
		System.out.println("Pesquisa de Níveis: "+pesquisaDeNíveis);
		System.out.println("Bandeira: "+hasBandeira);
		System.out.println("Bônus Noturno: "+hasBonusNoturno);
		
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @param boolean possui arqueiros
	 */
	public void setHasArqueiro(boolean hasArqueiro) {
		this.hasArqueiro = hasArqueiro;
	}
	/**
	 * @param boolean possui milícia
	 */
	public void setHasMilícia(boolean hasMilícia) {
		this.hasMilícia = hasMilícia;
	}
	/**
	 * @param boolean possui paladino
	 */
	public void setHasPaladino(boolean hasPaladino) {
		this.hasPaladino = hasPaladino;
	}
	/**
	 * @param boolean possui itens aprimorados
	 */
	public void setHasItemnsprimorados(boolean hasItemAprimorado) {
		this.hasItensAprimorados = hasItemAprimorado;
	}
	/**
	 * @param  boolean possui igreja
	 */
	public void setHasIgreja(boolean hasIgreja) {
		this.hasIgreja = hasIgreja;
	}
	/**
	 * @param boolean possui academia de níveis (armazenamento)
	 */
	public void setAcademiaDeNíveis(boolean academiaDeNíveis) {
		this.academiaDeNíveis = academiaDeNíveis;
	}
	
	public void setPesquisaDeNíveis(boolean pesquisaDeNíveis) {
		this.pesquisaDeNíveis = pesquisaDeNíveis;
	}

	/**
	 * @param boolean possui moral
	 */
	public void setHasMoral(boolean hasMoral) {
		this.hasMoral = hasMoral;
	}
	
	/**
	 * @param boolean possui bandeira
	 */
	public void setHasBandeira(boolean hasBandeira) {
		this.hasBandeira = hasBandeira;
	}
	
	/**
	 * @param boolean possui bônus noturno
	 */
	public void setHasBonusNoturno(boolean hasBonusNoturno) {
		this.hasBonusNoturno = hasBonusNoturno;
	}
	
	/**
	 * @param BigDecimal velocidade do mundo
	 */
	public void setVelocidade(BigDecimal velocidade) {
		this.velocidade = velocidade;
	}
	/**
	 * @param BigDecimal modificador de unidades do mundo
	 */
	public void setModificarUnidaes(BigDecimal modificarUnidaes) {
		this.modificarUnidaes = modificarUnidaes;
	}

	public String getNome() {
		return nome;
	}

	public boolean hasArqueiro() {
		return hasArqueiro;
	}

	public boolean hasMilícia() {
		return hasMilícia;
	}

	public boolean hasPaladino() {
		return hasPaladino;
	}
	
	public boolean hasItensAprimorados() {
		return hasItensAprimorados;
	}

	public boolean hasIgreja() {
		return hasIgreja;
	}

	public boolean isAcademiaDeNíveis() {
		return academiaDeNíveis;
	}

	public boolean isPesquisaDeNíveis() {
		return pesquisaDeNíveis;
	}

	public boolean hasMoral() {
		return hasMoral;
	}
	
	public boolean hasBandeira() {
		return hasBandeira;
	}
	
	public boolean hasBonusNoturno() {
		return hasBonusNoturno;
	}

	public BigDecimal getVelocidade() {
		return velocidade;
	}

	public BigDecimal getModificarUnidaes() {
		return modificarUnidaes;
	}


	
}
