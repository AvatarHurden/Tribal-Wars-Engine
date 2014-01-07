package dados_de_unidade;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import config.Mundo_Reader;
import database.Cores;
import database.Unidade;

public class PanelUnidade{
	
	// JPanel com o nome e quantidade
	private JPanel identificadores;
	
	// JPanel com as caracter�sticas principais
	private JPanel dadosPrincipais;
	
	// JPanel com os dados de custo
	private JPanel dadosCusto;
	
	private JLabel nome;
	private JTextField quantidade;
	private JComboBox<Integer> n�vel;
	private JLabel dano, defGeral, defCavalo, defArqueiro, saque;
	private JLabel madeira, argila, ferro, popula��o;

	private Unidade unidade;
	private Color cor;
	
	private PanelSoma soma;
	
	/**
	 * Cria um panel para a inser��o de uma unidade, com textField para o quantidade e labels para as caracter�sticas
	 * 
	 * @param cor que o panel ter�
	 * @param unidade a que o panel corresponde
	 */
	public PanelUnidade(Color cor, Unidade unidade, PanelSoma soma) {
		
		this.unidade = unidade;
		this.cor = cor;
		this.soma = soma;
		
		identificadores = new JPanel();
		identificadores.setBackground(cor);
		
		setInser��o();
	
		dadosPrincipais = new JPanel();
		dadosPrincipais.setBackground(cor);
		
		setPrincipais();
		
		dadosCusto = new JPanel();
		dadosCusto.setBackground(cor);
		
		setCusto();
	}
	
	/**
	 * Cria um panel com apenas os nomes de cada campo, para ser usado como header
	 */
	public PanelUnidade() {
		
		// Creating "identificadores"
		
		identificadores = new JPanel();
		identificadores.setBackground(Cores.FUNDO_ESCURO);
		
		GridBagLayout gbl_inser��o = new GridBagLayout();
		if (Mundo_Reader.MundoSelecionado.isPesquisaDeN�veis())
			gbl_inser��o.columnWidths = new int[] {125, 1, 40, 1, 80};
		else
			gbl_inser��o.columnWidths = new int[] {125, 80};
		gbl_inser��o.rowHeights = new int[] {20};
		gbl_inser��o.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_inser��o.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		identificadores.setLayout(gbl_inser��o);
		
		GridBagConstraints gbc_inser��o = new GridBagConstraints();
		gbc_inser��o.insets = new Insets(5, 5, 5, 5);
		
		JLabel nome = new JLabel("Unidade");
		gbc_inser��o.gridx = 0;
		identificadores.add(nome, gbc_inser��o);
		
		if (Mundo_Reader.MundoSelecionado.isPesquisaDeN�veis()) {
			addSeparator(gbc_inser��o, identificadores);
		
			JLabel n�vel = new JLabel("N�vel");
			gbc_inser��o.gridx++;
			identificadores.add(n�vel, gbc_inser��o);
		
			addSeparator(gbc_inser��o, identificadores);
		}
		
		JLabel quantidade = new JLabel("Quantidade");
		gbc_inser��o.gridx++;
		identificadores.add(quantidade, gbc_inser��o);
		
		
		// Creating "dadosPrincipais"
		
		dadosPrincipais = new JPanel();
		dadosPrincipais.setBackground(Cores.FUNDO_ESCURO);
		
		GridBagLayout gbl = new GridBagLayout();
		
		// Caso o mundo tenha arqueiros, coloca lugar para a defesa de arqueiro
		if (Mundo_Reader.MundoSelecionado.hasArqueiro())
			gbl.columnWidths = new int[] {75, 1, 75, 1, 75, 1, 75, 1, 75};
		else
			gbl.columnWidths = new int[] {75, 1, 75, 1, 75, 1, 75};
		
		
		gbl.rowHeights = new int[] {20};
		gbl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		dadosPrincipais.setLayout(gbl);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		
		dano = new JLabel("Ataque");
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx = 0;
		dadosPrincipais.add(dano, constraints);
		
		addSeparator(constraints, dadosPrincipais);
		
		defGeral = new JLabel("Def. Geral");
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosPrincipais.add(defGeral, constraints);
		
		addSeparator(constraints, dadosPrincipais);
		
		defCavalo = new JLabel("Def. Cav.");
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosPrincipais.add(defCavalo, constraints);
		
		addSeparator(constraints, dadosPrincipais);
		
		defArqueiro = new JLabel("Def. Arq.");
		
		if (Mundo_Reader.MundoSelecionado.hasArqueiro()) {
		
			constraints.insets = new Insets(5, 0, 5, 5);
			constraints.gridx++;
			dadosPrincipais.add(defArqueiro, constraints);
		
			addSeparator(constraints, dadosPrincipais);
		
		}
		
		saque = new JLabel("Saque");
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosPrincipais.add(saque, constraints);
		
		// Creating "dadosCusto"
		
		dadosCusto = new JPanel();
		dadosCusto.setBackground(Cores.FUNDO_ESCURO);
		
		GridBagLayout gbl_custo = new GridBagLayout();
		gbl_custo.columnWidths = new int[] {75, 1, 75, 1, 75, 1, 75};
		gbl_custo.rowHeights = new int[] {20};
		gbl_custo.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_custo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		dadosCusto.setLayout(gbl_custo);
		
		GridBagConstraints constraints_custo = new GridBagConstraints();
		constraints_custo.gridy = 0;
		
		madeira = new JLabel("Madeira");
		constraints_custo.insets = new Insets(5, 0, 5, 5);
		constraints_custo.gridx = 0;
		dadosCusto.add(madeira, constraints_custo);
		
		addSeparator(constraints_custo, dadosCusto);
		
		argila = new JLabel("Argila");
		constraints_custo.insets = new Insets(5, 0, 5, 5);
		constraints_custo.gridx++;
		dadosCusto.add(argila, constraints_custo);
		
		addSeparator(constraints_custo, dadosCusto);
		
		ferro = new JLabel("Ferro");
		constraints_custo.insets = new Insets(5, 0, 5, 5);
		constraints_custo.gridx++;
		dadosCusto.add(ferro, constraints_custo);
		
		addSeparator(constraints_custo, dadosCusto);
		
		popula��o = new JLabel("Popula��o");
		constraints_custo.insets = new Insets(5, 0, 5, 5);
		constraints_custo.gridx++;
		dadosCusto.add(popula��o, constraints_custo);
		
	}
	
	@SuppressWarnings("serial")
	/**
	 * Adiciona o JPanel com o nome, n�vel e quantidade das unidades
	 */
	private void setInser��o() {
		
		GridBagLayout gbl = new GridBagLayout();
		if (Mundo_Reader.MundoSelecionado.isPesquisaDeN�veis())
			gbl.columnWidths = new int[] {125, 1, 40, 1, 80};
		else
			gbl.columnWidths = new int[] {125, 80};
		gbl.rowHeights = new int[] {20};
		gbl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		identificadores.setLayout(gbl);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		nome = new JLabel(unidade.nome());
		identificadores.add(nome, constraints);
		
		if (Mundo_Reader.MundoSelecionado.isPesquisaDeN�veis()) {
			addSeparator(constraints, identificadores);
			
			if (unidade.equals(Unidade.PALADINO) || unidade.equals(Unidade.NOBRE) 
					|| unidade.equals(Unidade.MIL�CIA))
				createComboBox(false, constraints, true);
			else
				createComboBox(true, constraints, true);
		
			addSeparator(constraints, identificadores);
		} else
			createComboBox(true, constraints, false);
		
		quantidade = new JTextField();
		quantidade.setHorizontalAlignment(SwingConstants.LEFT);
		quantidade.setDocument(new PlainDocument() {
			
			@Override
			 public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
				    if (str == null)
				      return;
				    
				    //Permite a entrada somente de n�meros e no m�ximo 9 d�gitos
				    if ((getLength() + str.length()) <= 9 && Character.isDigit(str.charAt(0))) {
				      super.insertString(offset, str, attr);
				    }
				  }
			
		});
		
		quantidade.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {}
			
			public void keyReleased(KeyEvent e) {
				
				// Apenas fazer modifica��es caso a key seja um n�mero ou o backspace
				if (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == KeyEvent.VK_BACK_SPACE )
				try {
					String formated = NumberFormat.getNumberInstance(Locale.GERMANY)
							.parse(quantidade.getText()).toString();
					
					//Caso a quantidade seja vazia, n�o tenta mudar os valores, mas sim zer�-los
					if (formated.equals(""))
						resetValues();
					else
						changeValues();
					
					quantidade.setText(NumberFormat.getNumberInstance(Locale.GERMANY)
							.format(Integer.parseInt(formated)));
					
				} catch (ParseException exc) {}
				
				soma.setTotal();
				
			}
			
			public void keyPressed(KeyEvent arg0) {}
		});
		
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		identificadores.add(quantidade, constraints);
		quantidade.setColumns(6);
		
	}

	/**
	 * Adiciona o JPanel com as caracter�sticas b�sicas das unidades
	 */
	private void setPrincipais() {
		
		GridBagLayout gbl = new GridBagLayout();
		
		// Caso o mundo tenha arqueiros, coloca lugar para a defesa de arqueiro
		if (Mundo_Reader.MundoSelecionado.hasArqueiro())
			gbl.columnWidths = new int[] {75, 1, 75, 1, 75, 1, 75, 1, 75};
		else
			gbl.columnWidths = new int[] {75, 1, 75, 1, 75, 1, 75};
		
		gbl.rowHeights = new int[] {30};
		gbl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		dadosPrincipais.setLayout(gbl);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		
		dano = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx = 0;
		dadosPrincipais.add(dano, constraints);
		
		addSeparator(constraints, dadosPrincipais);
		
		defGeral = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosPrincipais.add(defGeral, constraints);
		
		addSeparator(constraints, dadosPrincipais);
		
		defCavalo = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosPrincipais.add(defCavalo, constraints);
		
		addSeparator(constraints, dadosPrincipais);
		
		defArqueiro = new JLabel();
		
		if (Mundo_Reader.MundoSelecionado.hasArqueiro()) {
			
			constraints.insets = new Insets(5, 0, 5, 5);
			constraints.gridx++;
			dadosPrincipais.add(defArqueiro, constraints);
		
			addSeparator(constraints, dadosPrincipais);
			
		}
		
		saque = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosPrincipais.add(saque, constraints);
		
	}

	/**
	 * Adiciona o JPanel com as caracter�sitcas de custo das unidades
	 */
	private void setCusto() {
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {75, 1, 75, 1, 75, 1, 75};
		gbl.rowHeights = new int[] {30};
		gbl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		dadosCusto.setLayout(gbl);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		
		madeira = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx = 0;
		dadosCusto.add(madeira, constraints);
		
		addSeparator(constraints, dadosCusto);
		
		argila = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosCusto.add(argila, constraints);
		
		addSeparator(constraints, dadosCusto);
		
		ferro = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosCusto.add(ferro, constraints);
		
		addSeparator(constraints, dadosCusto);
		
		popula��o = new JLabel();
		constraints.insets = new Insets(5, 0, 5, 5);
		constraints.gridx++;
		dadosCusto.add(popula��o, constraints);
	}

	// Adiciona um JSeparator vertical para separar as caracter�sticas
	private void addSeparator(GridBagConstraints c, JPanel panel) {
		
		JSeparator test = new JSeparator(SwingConstants.VERTICAL);
		if (cor == null)
			test.setForeground(Cores.SEPARAR_ESCURO);
		else
			test.setForeground(Cores.SEPARAR_CLARO);
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx++;
		panel.add(test, c);
		
	}
	
	/**
	 * Cria o comboBox para sele��o de n�vel.
	 * @param hasLevels se a unidade possui n�vel ou n�o (paladino, nobre e mil�cia)
	 * @param c constraint para ser inserido
	 * @param addtoPanel se o comboBox deve ser adicionado ao panel (false se o mundo n�o possui
	 * n�vel, sendo criado para ficar sempre no n�vel 1)
	 */
	private void createComboBox(boolean hasLevels, GridBagConstraints c, boolean addtoPanel) {
		
		// Coloca a cor padr�o para os comboBox
		UIManager.put("ComboBox.selectionBackground", Cores.FUNDO_ESCURO); 
		UIManager.put("ComboBox.background", cor); 
		
		n�vel = new JComboBox<Integer>(new Integer[]{1,2,3});
		
		n�vel.setOpaque(false);
		n�vel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!quantidade.getText().equals("")) {
					changeValues();
				
					soma.setTotal();
				}
				
			}
		});
		
		// Cria um renderer para set usado no combox, centralizando o texto
		ListCellRenderer<Object> renderer = new DefaultListCellRenderer();
		((JLabel)renderer).setHorizontalAlignment( SwingConstants.CENTER );
		((JLabel)renderer).setOpaque(true);
		
		n�vel.setRenderer(renderer);
		
		// Zera a largura do bot�o
		n�vel.setUI(new BasicComboBoxUI() {
		    @SuppressWarnings("serial")
			@Override
		    protected JButton createArrowButton() {
		    	return new JButton() {
		    		@Override
		    		public int getWidth() {
		    			return 0;
		    		}
		    	};
		    }	
		});
		
		if (addtoPanel) {
			c.anchor = GridBagConstraints.CENTER;
			c.gridx++;
			if (hasLevels)
				identificadores.add(n�vel, c);
			else
				identificadores.add(new JLabel(), c);
				
		}
		
	}
	
	// Modifica os valores das caracter�sticas para adequar ao n�mero de unidades
	// (valor unit�rio * quantidade)
	private void changeValues() {
		
		String formated = "";
		try {
			formated = NumberFormat.getNumberInstance(Locale.GERMANY)
					.parse(quantidade.getText()).toString();
		} catch (ParseException e) {}
		
		BigDecimal quantia = new BigDecimal(formated);
		
		dano.setText(quantia.multiply(unidade.ataque((n�vel.getSelectedIndex()+1)))
				.setScale(0,RoundingMode.HALF_UP).toString());
		defGeral.setText(quantia.multiply(unidade.defGeral((n�vel.getSelectedIndex()+1)))
				.setScale(0,RoundingMode.HALF_UP).toString());
		defCavalo.setText(quantia.multiply(unidade.defCav((n�vel.getSelectedIndex()+1)))
				.setScale(0,RoundingMode.HALF_UP).toString());
		defArqueiro.setText(quantia.multiply(unidade.defArq((n�vel.getSelectedIndex()+1)))
				.setScale(0,RoundingMode.HALF_UP).toString());
		
		saque.setText(String.format("%,d",quantia.multiply(unidade.saque()).intValue()));
		
		madeira.setText(String.format("%,d",quantia.multiply(unidade.madeira()).intValue()));
		argila.setText(String.format("%,d",quantia.multiply(unidade.argila()).intValue()));
		ferro.setText(String.format("%,d",quantia.multiply(unidade.ferro()).intValue()));
		popula��o.setText(String.format("%,d",quantia.multiply(unidade.popula��o()).intValue()));
	}
	
	// Zera os valores quando a "quantidade" � nula
	private void resetValues() {
		
		dano.setText("");
		defGeral.setText("");
		defCavalo.setText("");
		defArqueiro.setText("");
		saque.setText("");
		
		madeira.setText("");
		argila.setText("");
		ferro.setText("");
		popula��o.setText("");
		
	}
	
	/**
	 * @return JPanel com nome e textField para inser��o da quantidade
	 */
	protected JPanel getIdentificadores() { return identificadores; }
	
	protected JTextField getQuantidade() { return quantidade; }
	
	/**
	 * @return JPanel com as labels dos dados principais
	 */
	protected JPanel getDadosPrincipais() { return dadosPrincipais; }
	
	protected JLabel getDano() { return dano; }
	protected JLabel getDefGeral() { return defGeral; }
	protected JLabel getDefCavalo() { return defCavalo; }
	protected JLabel getDefArqueiro() { return defArqueiro; }
	protected JLabel getSaque() { return saque;}
	
	/**
	 * @return JPanel com as labels dos dados de custo
	 */
	protected JPanel getDadosCusto() { return dadosCusto; }
	
	protected JLabel getMadeira() { return madeira; }
	protected JLabel getArgila() { return argila; }
	protected JLabel getFerro() { return ferro; }
	protected JLabel getPopula��o() { return popula��o; }
	
	
}
