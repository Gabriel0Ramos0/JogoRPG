package rpg;

import javax.swing.JOptionPane;

public class Historia {
	
	private static RPGGame game;
	
	public static void eventosMontanhasSombrias(Player player) {
        JOptionPane.showMessageDialog(null, "Ao se aproximar das Montanhas Sombrias, " + player.getName() + " percebe uma sensação familiar, e continua seguindo a trilha."
                + "\nA trilha íngreme leva a uma clareira onde destroços de uma estrutura desconhecida estão espalhados."
                + "\nInvestigando os destroços, " + player.getName() + " encontra pistas que sugerem um ritual mágico interrompido.");
        Eventos.coletarItensAleatoriosComHistorias(player);
        JOptionPane.showMessageDialog(null, "Seguindo adiante, " + player.getName() + " descobre um acampamento de treino, a fumaça era proveniente de uma fogueira com chamas altas."
                + "\nGuerreiros habilidosos estão treinando suas técnicas de combate, e um mestre de treinamento se aproxima de você.");
        JOptionPane.showMessageDialog(null, "Sem pensar muito " + player.getName() + " pergunta sobre o grande estrondo que escutou perto daqui."
        		+ "\nO mestre conta não ter ouvido nada, mas menciona que aqueles destroços foram de um ritual antigo..."
        		+ "\nSem deixar tempo para pensar, o mestre ofereçe ao "+ player.getName()+", se juntar aos treinos");
        String[] opcoesTreino = {"Juntar-se ao treinamento", "Continuar a jornada"};
        
        int escolhaTreino = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Acampamento de Treino", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesTreino, opcoesTreino[0]);
        switch (escolhaTreino) {
            case 0:
                JOptionPane.showMessageDialog(null, player.getName() + " decide juntar-se ao treinamento."
                        + "\nO mestre de treinamento elogia sua coragem e concede a você uma técnica especial aprendida durante o treinamento."
                        + "\n Ataque +1 || Exeperiência +25");
                player.setAttack(player.getAttack() + 1);
                player.gainExperience(25);
                JOptionPane.showMessageDialog(null, "Com novas habilidades adquiridas, " + player.getName() + " agradece ao mestre e parte em sua jornada, "
                		+ "agora mais preparado para os desafios que o aguardam."
                		+ "Os guerreiros ali presentem, lhe entregam uma comida típica para ajudar em sua aventura!"
                		+ "Ganhou 2 espetinho!");
                Consumivel espetinho = new Consumivel("Espetinho", 3, 2, 10);
                player.getInventory().add(espetinho);
                JOptionPane.showMessageDialog(null, "Com os suprimentos e boas vibrações dos guerreiros, " + player.getName() + " parte determinado para explorar mais desse mundo misterioso.");
                break;

            case 1:
                JOptionPane.showMessageDialog(null, player.getName() + " decide continuar sua jornada, agradecendo pelo acolhimento no acampamento de treino."
                        + "\nOs guerreiros desejam boa sorte em sua jornada e oferecem suprimentos para a viagem."
                        + "Ganhou 2 espetinho!");
                Consumivel espetinhos = new Consumivel("Espetinho", 3, 2, 10);
                player.getInventory().add(espetinhos);
                
                JOptionPane.showMessageDialog(null, "Com os suprimentos e boas vibrações dos guerreiros, " + player.getName() + " parte determinado para explorar mais desse mundo misterioso.");
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como interagir com o acampamento de treino.");
        }
        
        // Continuação do jogo!!!
    }
	
	public static void eventosCavernaDaPerdicao (Player player) {
		JOptionPane.showMessageDialog(null, player.getName() + " escolhe explorar mais a Caverna das Luminescências."
	            + "\nAo adentrar as profundezas da caverna, " + player.getName() + " sente a presença de algo sinistro."
	            + "\nA escuridão parece ganhar vida ao seu redor, e uma voz sussurra na mente de " + player.getName() + ", instigando-o a continuar.");

	    String[] opcoesContinuar = {"Continuar explorando", "Retornar à entrada da caverna"};
	    int escolhaContinuar = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Presença Sinistra", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesContinuar, opcoesContinuar[0]);

	    switch (escolhaContinuar) {
	        case 0:
	            JOptionPane.showMessageDialog(null, player.getName() + " decide continuar explorando, seguindo o chamado sombrio."
	                    + "\nÀ medida que avança, a luz roxa dos cristais se transforma em uma luminosidade vermelha e pulsante.");
	            Historia historia = new Historia();
	            historia.confrontoMercadorSombrio(null, player);            
	            break;

	        case 1:
	            JOptionPane.showMessageDialog(null, player.getName() + " decide recuar e retornar à entrada da caverna."
	                    + "\nA sensação sinistra diminui à medida que se afasta das profundezas."
	                    + "\nAo sair da caverna, avista a fumaça na montanha e decide averiguar");
	            eventosMontanhasSombrias(player);
	            break;

	        default:
	            JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como reagir à presença sinistra.");
	    }
	}
	
	private void confrontoMercadorSombrio(Boolean vitoria, Player player) {
		Eventos eventos = new Eventos();
	    JOptionPane.showMessageDialog(null, "De repente, " + player.getName() + " se depara com uma figura encapuzada, emergindo das sombras."
	            + "\nO mercador peculiar agora revela sua verdadeira forma, envolto em uma aura vermelha e sinistra."
	            + "\nEle se autodenomina o Mercador Sombrio, mestre dos artefatos corrompidos pela escuridão.");
	    
        Monstro monstro = eventos.bossMercador();
        Batalha batalha = new Batalha(game, player, monstro);
        batalha.executarBatalhaEstendida(player, monstro);
	}
        
	public static void continuaHistoriaCave(boolean vitoria, Player player) {	    
	    if (vitoria) {
	        JOptionPane.showMessageDialog(null, player.getName() + " derrota o Mercador Sombrio, dissipando a aura sinistra que envolvia a caverna."
	                + "\nA luz roxa dos cristais volta ao seu brilho normal.");
	        
	        JOptionPane.showMessageDialog(null, "Você encontra alguns itens deixados pelo Mercador Sombrio:"
	                + "\n- Chapéu de Mercador (Aumenta a Vida em 5)"
	                + "\n- Bife (Regenera 40 de Vida)");
	        
	        Equipavel chapeuDeMercador = new Equipavel("Chapéu do Mercador", 10, 1, 0, 0, 5);
	        player.equipItem(chapeuDeMercador);
	        Consumivel bife = new Consumivel("Bife", 10, 1, 40);
	        player.getInventory().add(bife);
	        
	        JOptionPane.showMessageDialog(null, player.getName() + " sai da caverna e avista uma montanha à distância."
	                + "\nIntrigado pela montanha e pelo barulho que tinha feito, " + player.getName() + " decide explorar e se aproximar dela.");
	        eventosMontanhasSombrias(player);
	    }
	}
        
	public static void eventosDesastreTerrivel(Player player) {
		Eventos eventos = new Eventos();
		player.marcarPassagemPorParteDaHistoria("Caverna");
	    JOptionPane.showMessageDialog(null, "Enquanto " + player.getName() + " se afasta da caverna, uma flexa mágica rasga o ar e o atinge nas costas."
	            + "\nA dor é intensa, e " + player.getName() + " se vê enfraquecido pela energia mágica da flexa.");
	    JOptionPane.showMessageDialog(null, player.getName() + " perde 20 de vida!");
	    player.setHealth(player.getHealth() - 20);

	    JOptionPane.showMessageDialog(null, "Você se sente apreensivo, como se algo maligno estivesse observando cada movimento seu."
	            + "\nMesmo ferido, " + player.getName() + " decide continuar sua jornada, agora com um fardo adicional a carregar e com medo do terreno ao redor.");

	    JOptionPane.showMessageDialog(null, "De repente, uma sombra gigante surge das Montanhas Sombrias, um monstro épico de olhos flamejantes."
	            + "\nEle dispara outra flexa, mas, com sorte, você consegue se esquivar no último momento.");
	    JOptionPane.showMessageDialog(null, "O monstro épico está furioso e avança em sua direção!");
	    
	    Monstro monstro = eventos.escolherMonstroAleatorioEpic();
        Batalha batalha = new Batalha(game, player, monstro);
        batalha.executarBatalhaEstendida(player, monstro);
        
        continuaHistoriaForaCave(false, player);
	}
	
	public static void continuaHistoriaForaCave(boolean vitoria, Player player) {	    
	    if (vitoria) {
		    JOptionPane.showMessageDialog(null, player.getName() + " emerge vitorioso da batalha épica contra o Monstro Épico."
		    		+ "\nA atmosfera pesada que se formou perto da Montanhas Sombrias começa a se dissipar, e você sente um alívio momentâneo.");
	        JOptionPane.showMessageDialog(null, "Agora que está tudo bem, você ouve seu nome vindo do alto da montanha. Intrigado, "
	        		+ player.getName() + " segue em direção a montanha e a fumaça que consegue ver.");
	        eventosMontanhasSombrias(player);
	    }
	}
}
