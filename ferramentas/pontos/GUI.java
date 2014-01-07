package pontos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import config.Mundo_Reader;
import database.Cores;
import database.Edifício;
import database.Ferramenta;

@SuppressWarnings("serial")
public class GUI extends Ferramenta {

	List<Edifício> edifíciosUtilizados = new ArrayList<Edifício>();
	List<PanelEdifício> panelEdifícioList = new ArrayList<PanelEdifício>();
	
	Map<String, BigInteger> somaTotal = new HashMap<String, BigInteger>();
	
	PanelSoma total = new PanelSoma();
	
	/**
	 * Ferramenta que calcula a pontuação e a população utilizada com base
	 * nos níveis dos edifícios inseridos.
	 * Também calcula a população restante através do nível fornecido da fazenda
	 */
	public GUI() {
		
		super("Cálculo de Pontos"); 
		
		setBackground(Cores.FUNDO_CLARO);
		setEdifícios();
		
		total.setPanelListAndColor(panelEdifícioList, getNextColor());
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0 ,0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		addHeader(true, gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		add(unitePanels("identificadores"), gbc);
		
		gbc.gridx++;
		add(unitePanels("dadosPanel"), gbc);
	
		gbc.gridy = 2;
		gbc.gridx = 0;
		addHeader(false, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		addPanelTotal(gbc);
		
	}
	
	/**
	 * Adiciona um cabeçalho com os nomes das informações de cada coluna
	 * @param boolean com "edifício" e "nível", para ser usável embaixo 
	 */
	private void addHeader(boolean topHeader, GridBagConstraints gbc) {
		 
		PanelEdifício header = new PanelEdifício(!topHeader);
		
		if (topHeader) {
			header.getIdentificadores().setBorder(new LineBorder(Cores.SEPARAR_ESCURO,1,true));
			add(header.getIdentificadores(), gbc);
		}
		
		gbc.gridx++;
		header.getDadosPanel().setBorder(new LineBorder(Cores.SEPARAR_ESCURO,1,true));
		add(header.getDadosPanel(), gbc);
		
		if (!topHeader) {
			gbc.gridx++;
			header.getPopulaçãoRestantePanel().setBorder(new LineBorder(Cores.SEPARAR_ESCURO,1,true));
			add(header.getPopulaçãoRestantePanel(), gbc);
		}
		
	}
	
	/**
	 * Junta os panels de todas as unidades num único panel
	 * 
	 * @param String qual panel pegar
	 */
	private JPanel unitePanels(String s) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		panel.setBorder(new LineBorder(Cores.SEPARAR_ESCURO, 1, true));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		
		for (PanelEdifício i : panelEdifícioList) {
			
			gbc.gridy++;
			if (s.toLowerCase().equals("identificadores"))
				panel.add(i.getIdentificadores(), gbc);
			else if (s.toLowerCase().equals("dadospanel"))
				panel.add(i.getDadosPanel(), gbc);
				
		}
		
		return panel;
		
	}
	
	/**
	 * Adiciona o PanelTotal no gui
	 */
	private void addPanelTotal(GridBagConstraints gbc) {		
		
		gbc.gridx++;
		total.getDadosPrincipais().setBorder(new LineBorder(Cores.SEPARAR_ESCURO,1,true));
		add(total.getDadosPrincipais(), gbc);
		
		gbc.gridx++;
		total.getPopulaçãoRestantePanel().setBorder(new LineBorder(Cores.SEPARAR_ESCURO,1,true));
		add(total.getPopulaçãoRestantePanel(), gbc);
			
	}
	
	/**
	 * Cria a lista de PanelEdifício, utilizando os edifícios do mundo
	 */
	private void createPanelEdifício() {
		
		for (Edifício i : edifíciosUtilizados)
			panelEdifícioList.add(new PanelEdifício(getNextColor(),i, total));
		
	}
	
	
	
	/**
	 * Define quais edifícios serão utilizadas, com as configurações do mundo
	 */
 	private void setEdifícios() {
	
		edifíciosUtilizados.add(Edifício.EDIFÍCIO_PRINCIPAL);
		edifíciosUtilizados.add(Edifício.QUARTEL);
		edifíciosUtilizados.add(Edifício.ESTÁBULO);
		edifíciosUtilizados.add(Edifício.OFICINA);
		
		if (Mundo_Reader.MundoSelecionado.hasIgreja()) {
			edifíciosUtilizados.add(Edifício.IGREJA);
			edifíciosUtilizados.add(Edifício.PRIMEIRA_IGREJA);
		}
		
		if (Mundo_Reader.MundoSelecionado.isAcademiaDeNíveis())
			edifíciosUtilizados.add(Edifício.ACADEMIA_3NÍVEIS);
		else
			edifíciosUtilizados.add(Edifício.ACADEMIA_1NÍVEL);
		
		edifíciosUtilizados.add(Edifício.FERREIRO);
		edifíciosUtilizados.add(Edifício.PRAÇA_DE_REUNIÃO);
		
		if (Mundo_Reader.MundoSelecionado.hasPaladino())
			edifíciosUtilizados.add(Edifício.ESTÁTUA);
		
		edifíciosUtilizados.add(Edifício.MERCADO);
		edifíciosUtilizados.add(Edifício.BOSQUE);
		edifíciosUtilizados.add(Edifício.POÇO_DE_ARGILA);
		edifíciosUtilizados.add(Edifício.MINA_DE_FERRO);
		edifíciosUtilizados.add(Edifício.FAZENDA);
		edifíciosUtilizados.add(Edifício.ARMAZÉM);
		edifíciosUtilizados.add(Edifício.ESCONDERIJO);
		edifíciosUtilizados.add(Edifício.MURALHA);
		
		createPanelEdifício();
	}
	
}
